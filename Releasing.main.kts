#!/usr/bin/env kotlin

@file:Repository("https://repo.maven.apache.org/maven2/")
//@file:Repository("https://oss.sonatype.org/content/repositories/snapshots")
//@file:Repository("file:///Users/louiscad/.m2/repository")
@file:DependsOn("com.louiscad.incubator:lib-publishing-helpers:0.2.4")

import Releasing_main.CiReleaseFailureCause.*
import java.io.File
import Releasing_main.ReleaseStep.*
import lib_publisher_tools.cli.AnsiColor
import lib_publisher_tools.cli.CliUi
import lib_publisher_tools.cli.defaultImpl
import lib_publisher_tools.cli.runUntilSuccessWithErrorPrintingOrCancel
import lib_publisher_tools.clipboard.copyToClipboard
import lib_publisher_tools.open.openUrl
import lib_publisher_tools.process.executeAndPrint
import lib_publisher_tools.vcs.*
import lib_publisher_tools.versioning.StabilityLevel
import lib_publisher_tools.versioning.Version
import lib_publisher_tools.versioning.checkIsValidVersionString
import lib_publisher_tools.versioning.stabilityLevel
import java.net.URLEncoder
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.Date

val gitHubRepoUrl = "https://github.com/Splitties/refreshVersions"

val dir = File(".")

val publishWorkflowFilename = "release-plugins.yml".also {
    check(dir.resolve(".github").resolve("workflows").resolve(it).exists()) {
        "The $it file expected in the `.github/workflows dir wasn't found!\n" +
            "The filename is required to be correct.\n" +
            "If the release workflow needs to be retried, it will be used to make a valid link."
    }
}

val publishWorkflowLink = "$gitHubRepoUrl/actions/workflows/$publishWorkflowFilename"

val cliUi = CliUi.defaultImpl
val git = Vcs.git

fun File.checkChanged() = check(git.didFileChange(this)) {
    "Expected changes in the following file: $this"
}

fun checkOnMainBranch() {
    check(git.isOnMainBranch()) { "Please, checkout the `main` branch first." }
}

@Suppress("EnumEntryName")
enum class ReleaseStep { // Order of the steps, must be kept right.
    `Update release branch`,
    `Update main branch from release`,
    `Change this library version`,
    `Run pre-publish tests`,
    `Request doc update confirmation`,
    `Request CHANGELOG update confirmation`,
    `Commit 'prepare for release' and tag`,
    `Push release to origin`,
    `Request PR submission`,
    `Wait for successful release by CI`,
    `Push tags to origin`,
    `Request PR merge`,
    `Request GitHub release publication`,
    `Change this library version back to a SNAPSHOT`,
    `Commit 'prepare next dev version'`,
    `Push, at last`;
}

sealed interface CiReleaseFailureCause {
    enum class RequiresNewCommits : CiReleaseFailureCause { BuildFailure, PublishingRejection }
    enum class RequiresRetrying : CiReleaseFailureCause { ThirdPartyOutage, NetworkOutage }
}


private class Files {
    val ongoingRelease = dir.resolve("ongoing_release.tmp.properties")

    val versions = dir.resolve("plugins/version.txt")
    val changelog = dir.resolve("CHANGELOG.md")

    val mainResourcesDir = dir.resolve("plugins/dependencies/src/main/resources")
    val versionToRemovalsMapping = mainResourcesDir.resolve("version-to-removals-revision-mapping.txt").also {
        check(it.exists()) { "Didn't find the ${it.name} file in ${it.parentFile}! Has it been moved or renamed?" }
    }
    val dependencyNotations = dir.resolve("docs/dependency-notations.md")
}

private val files = Files()

inner class OngoingReleaseImpl {
    fun load() = properties.load(files.ongoingRelease.inputStream())
    fun write() = properties.store(files.ongoingRelease.outputStream(), null)
    fun clear() = files.ongoingRelease.delete()

    private val properties = java.util.Properties()

    var versionBeforeRelease: String by properties
    var newVersion: String by properties

    var currentStepName: String by properties
}

//TODO: Make OngoingRelease and object again when https://youtrack.jetbrains.com/issue/KT-19423 is fixed.
@Suppress("PropertyName")
val OngoingRelease = OngoingReleaseImpl()

var startAtStep: ReleaseStep //TODO: Make a val again when https://youtrack.jetbrains.com/issue/KT-20059 is fixed

val versionTagPrefix = "v"

fun tagOfVersionBeingReleased(): String = "$versionTagPrefix${OngoingRelease.newVersion}"

if (files.ongoingRelease.exists()) {
    OngoingRelease.load()
    startAtStep = ReleaseStep.valueOf(OngoingRelease.currentStepName)
} else {
    checkOnMainBranch()
    with(OngoingRelease) {
        versionBeforeRelease = files.versions.bufferedReader().use { it.readLine() }.also {
            check(it.contains("-dev-") || it.endsWith("-SNAPSHOT")) {
                "The current version needs to be a SNAPSHOT version, but we got: $it"
            }
        }
        newVersion = askNewVersionInput(
            currentSnapshotVersion = versionBeforeRelease,
            tagPrefix = versionTagPrefix
        )
    }
    startAtStep = ReleaseStep.values().first()
}

fun extractChangelogForVersion(version: String): String = files.changelog.useLines { lines ->
    val startOfThisVersionHeading = "## Version $version"
    lines.dropWhile {
        it.startsWith(startOfThisVersionHeading).not()
    }.takeWhile {
        it.startsWith(startOfThisVersionHeading) || it.startsWith("## Version ").not()
    }.joinToString(separator = "\n")
}

fun String.urlEncode(charset: Charset = Charset.defaultCharset()): String = URLEncoder.encode(this, charset)

fun askNewVersionInput(
    currentSnapshotVersion: String,
    tagPrefix: String
): String = cliUi.runUntilSuccessWithErrorPrintingOrCancel {
    cliUi.printInfo("Current version: $currentSnapshotVersion")
    val nonSnapshotVersion = currentSnapshotVersion.removeSuffix("-SNAPSHOT")
    cliUi.printQuestion("Please enter the name of the new version you want to release,")
    cliUi.printQuestion("or leave blank to release version $nonSnapshotVersion:")
    val input = readln().trimEnd().ifBlank { nonSnapshotVersion }
    input.checkIsValidVersionString()
    when {
        "-dev-" in input -> error("Dev versions not allowed")
        "-SNAPSHOT" in input -> error("Snapshots not allowed")
    }
    val existingVersions = git.getTags().filter {
        it.startsWith(tagPrefix) && it.getOrElse(tagPrefix.length) { ' ' }.isDigit()
    }.sorted().toList()
    check("$tagPrefix$input" !in existingVersions) { "This version already exists!" }
    input
}


fun CliUi.runReleaseStep(step: ReleaseStep): Unit = when (step) {
    `Update release branch` -> {
        printInfo("Before proceeding to the release, we will ensure we merge changes from the release branch into the main branch.")
        printInfo("Will now checkout the `release` branch and pull from GitHub (origin) to update the local `release` branch.")
        requestUserConfirmation("Continue?")
        if (git.hasBranch("release")) {
            git.checkoutBranch("release")
            git.pullFromOrigin()
        } else {
            printInfo("The branch release doesn't exist locally. Fetching from remote…")
            git.fetch()
            if (git.hasRemoteBranch(remoteName = "origin", branchName = "release")) {
                printInfo("The branch exists on the origin remote. Checking out.")
                git.checkoutAndTrackRemoteBranch("origin", "release")
            } else {
                printInfo("Creating and checking out the release branch")
                git.createAndCheckoutBranch("release")
                printInfo("Pushing the new release branch…")
                git.push(repository = "origin", setUpstream = true, branchName = "release")
            }
        }
    }
    `Update main branch from release` -> {
        printInfo("About to checkout the main branch (and update it from release for merge commits).")
        requestUserConfirmation("Continue?")
        git.checkoutMain()
        git.mergeBranchIntoCurrent("release")
    }
    `Change this library version` -> {
        checkOnMainBranch()
        OngoingRelease.newVersion.let { newVersion ->
            printInfo("refreshVersions new version: \"$newVersion\"")
            requestUserConfirmation("Confirm?")
            files.versions.writeText(newVersion)
        }
    }
    `Run pre-publish tests` -> {
        val osName = System.getProperty("os.name").lowercase()
        val isWindows: Boolean = "win" in osName
        val commandPrefix = if (isWindows) "" else "./"
        val command = "${commandPrefix}gradlew prePublishTest --console=plain"
        printInfo("Will now run $command")
        requestUserConfirmation("Ready?")
        command.executeAndPrint(dir.resolve("plugins"))
        check(git.didFileChange(files.versionToRemovalsMapping)) {
            "Expected ${files.versionToRemovalsMapping} to be edited by " +
                "the command that just ran. Is something broken?"
        }
        printInfo("Successfully updated the following file: ${files.versionToRemovalsMapping}")
        if (git.didFileChange(files.dependencyNotations)) {
            printInfo("Also updated the following file: ${files.dependencyNotations}")
        } else Unit
    }
    `Request doc update confirmation` -> {
        arrayOf(
            "README.md",
            "mkdocs.yml"
        ).forEach { relativePath ->
            do {
                requestManualAction(
                    instructions = "Update the `$relativePath` file with the new version (if needed)," +
                        " and any other changes needed for this release."
                )
                if (git.didFileChange(dir.resolve(relativePath))) {
                    break
                }
                if (askIfYes(
                        yesNoQuestion = "Are you sure the $relativePath file doesn't need to be updated?"
                    )
                ) {
                    break
                }
            } while (true)
        }.also {
            if (askIfYes(
                    yesNoQuestion = "Apart from the changelog, are there any other files that " +
                        "need to be updated for this new release?"
                )
            ) {
                requestManualAction(
                    instructions = "Let's ensure all these other files are updated."
                )
            }
        }
    }
    `Request CHANGELOG update confirmation` -> {
        val file = files.changelog
        requestManualAction("Update the `${file.name}` for the impending release.")
        file.checkChanged()
        val version = OngoingRelease.newVersion
        val dateString = SimpleDateFormat("yyyy-MM-dd").format(Date())
        val startOfThisVersionHeading = "## Version $version ($dateString)"
        val expectedHeadingCount = file.useLines { lines -> lines.count { it == startOfThisVersionHeading } }
        check(expectedHeadingCount == 1) {
            when (expectedHeadingCount) {
                0 -> "Didn't find the header for the upcoming release in the ${file.name}.\n" +
                    "Is there a typo or, an extra character, or is it the wrong date?\n" +
                    "Expected to find ${AnsiColor.bold}$startOfThisVersionHeading${AnsiColor.RESET}."
                else -> "Found multiple occurrences of the header for the upcoming release in the ${file.name}.\n" +
                    "Keep only one."
            }
        }
    }
    `Commit 'prepare for release' and tag` -> with(OngoingRelease) {
        files.changelog.checkChanged()
        files.versionToRemovalsMapping.checkChanged()
        git.commitAllFiles(commitMessage = "Prepare for release $newVersion")
        git.tagAnnotated(tag = tagOfVersionBeingReleased(), annotationMessage = "Version $newVersion")
    }
    `Push release to origin` -> {
        printInfo("Will now push to origin repository")
        requestUserConfirmation("Continue?")
        git.pushToOrigin()
    }
    `Request PR submission` -> {
        printInfo("You now need to create a pull request from the `main` to the `release` branch on GitHub for the new version,")
        printInfo("if not already done.")
        printInfo("You can do so by heading over to the following url:")
        printInfo("$gitHubRepoUrl/compare/release...main")
        printInfo("Here's a title suggestion which you can copy/paste:")
        printInfo("Prepare for release ${OngoingRelease.newVersion}")
        printInfo("Once submitted, GitHub should kick-off the release GitHub Action that will perform the publishing.")
        requestManualAction("PR submitted?")
    }
    `Wait for successful release by CI` -> {
        printInfo("To perform this step, we need to wait for the artifacts building and uploading.")
        do {
            printInfo("The build and publishing workflow is expected to take about 3 minutes.")
            printInfo("")
            printInfo("We recommend to set a timer to not forget to check the status.")
            printInfo("Suggestion: In case it's not complete after that time, set a 2min timer to check again, until completion.")
            val succeeded = askIfYes("Did the publishing/release Github Action complete successfully?")
            if (succeeded.not()) {
                printQuestion("What was the cause of failure?")
                val failureCause: CiReleaseFailureCause = askChoice(
                    optionsWithValues = listOf(
                        "Outage of a third party service (GitHub actions, Gradle plugin portal, Sonatype, MavenCentral, Google Maven…)" to RequiresRetrying.ThirdPartyOutage,
                        "Network outage" to RequiresRetrying.NetworkOutage,
                        "Build failure that requires new commits to fix" to RequiresNewCommits.BuildFailure,
                        "Publication was rejected because of misconfiguration" to RequiresNewCommits.PublishingRejection
                    )
                )
                when (failureCause) {
                    is RequiresRetrying -> {
                        printInfo("The outage will most likely be temporary.")
                        when (failureCause) {
                            RequiresRetrying.ThirdPartyOutage -> "You can search for the status page of the affected service and check it periodically."
                            RequiresRetrying.NetworkOutage -> "You can retry when you feel or know it might be resolved."
                        }.let { infoMessage ->
                            printInfo(infoMessage)
                        }
                        printInfo("Once the outage is resolved, head to the following url to run the workflow again, on the right branch:")
                        printInfo(publishWorkflowLink)
                        requestManualAction("Click the `Run workflow` button, select the `main` branch and confirm.")
                    }
                    is RequiresNewCommits -> {
                        if (git.hasTag(tagOfVersionBeingReleased())) {
                            printInfo("Removing the version tag (will be put back later on)")
                            git.deleteTag(tag = tagOfVersionBeingReleased())
                            printInfo("tag removed")
                        }
                        printInfo("Recovering from that is going to require new fixing commits to be pushed to the main branch.")
                        printInfo("Note: you can keep this script waiting while you're resolving the build issue.")
                        requestManualAction("Fix the issues and commit the changes")
                        printInfo("Will now push the new commits")
                        requestUserConfirmation("Continue?")
                        git.pushToOrigin()
                        printInfo("Now, head to the following url to run the workflow again, on the right branch:")
                        printInfo(publishWorkflowLink)
                        requestManualAction("Click the `Run workflow` button, select the `main` branch and confirm.")
                    }
                }
            }
        } while (succeeded.not())
        printInfo("Alright, we take your word.")
    }
    `Push tags to origin` -> {
        printInfo("Will now push with tags.")
        requestUserConfirmation("Continue?")
        if (git.hasTag(tagOfVersionBeingReleased()).not()) with(OngoingRelease) {
            printInfo("The tag for the impeding release is missing, so we're putting it back too.")
            git.tagAnnotated(tag = tagOfVersionBeingReleased(), annotationMessage = "Version $newVersion")
        }
        git.pushToOrigin(withTags = true)
    }
    `Request PR merge` -> {
        requestManualAction("Merge the pull request for the new version on GitHub.")
        printInfo("Now that the pull request has been merged into the release branch on GitHub,")
        printInfo("we are going to update our local release branch")
        requestUserConfirmation("Ready?")
        git.updateBranchFromOrigin(targetBranch = "release")
    }
    `Request GitHub release publication` -> {
        printInfo("It's now time to publish the release on GitHub, so people get notified.")
        val newVersion = OngoingRelease.newVersion
        val changelogForThisRelease: String = extractChangelogForVersion(newVersion)
        val urlEncodedChangelogForThisRelease = changelogForThisRelease.urlEncode()
        // https://docs.github.com/en/repositories/releasing-projects-on-github/automation-for-release-forms-with-query-parameters
        val longUrl =
            "$gitHubRepoUrl/releases/new?tag=${tagOfVersionBeingReleased()}&title=$newVersion&body=$urlEncodedChangelogForThisRelease"
        val urlLengthLimit = 2000 // Magic number because it's complicated, see https://stackoverflow.com/a/417184/4433326
        do {
            if (longUrl.length > urlLengthLimit) {
                printInfo("The changelog content is too long to safely fit into the url, so you'll need to paste it.")
                printInfo("About to put it into the clipboard for you...")
                requestUserConfirmation("Ready to replace clipboard content?")
                changelogForThisRelease.copyToClipboard()
                printInfo("Changelog is in the clipboard!")
                printInfo("Now, you'll need to paste it on the webpage we are about to open and click publish.")
                requestUserConfirmation("Ready to open the tab in the default browser?")
                openUrl("$gitHubRepoUrl/releases/new?tag=${tagOfVersionBeingReleased()}&title=$newVersion")
            } else {
                printInfo("Will open the webpage, changelog will be pre-filled, you'll just have to click publish:")
                requestUserConfirmation("Ready to open the tab in the default browser?")
                openUrl(longUrl)
            }
        } while (!askIfYes("GitHub release published with changelog?"))
    }
    `Change this library version back to a SNAPSHOT` -> {
        val newVersion = Version(OngoingRelease.newVersion)

        val isNewVersionStable: Boolean = newVersion.stabilityLevel().let { level ->
            if (level == StabilityLevel.Stable) true
            else level == StabilityLevel.Unknown && askIfYes(
                yesNoQuestion = "The stabilityLevel of the new release is unknown. Is it a stable one?"
            )
        }
        val nextDevVersion: String = if (isNewVersionStable) {
            printInfo("Congratulations for this new stable release!")
            printInfo("Let's update the library for next development version.")
            runUntilSuccessWithErrorPrintingOrCancel {
                printInfo("Enter the name of the next target version (`-SNAPSHOT` will be added automatically)")
                val input = readLine()
                input.checkIsValidVersionString()
                when (Version(input).stabilityLevel()) {
                    StabilityLevel.Unknown, StabilityLevel.Stable -> Unit
                    else -> error("You need to enter a stable target version")
                }
                "$input-SNAPSHOT"
            }
        } else OngoingRelease.versionBeforeRelease.let {
            if (it.endsWith("-SNAPSHOT")) it
            else "${it.substringBefore("-dev-")}-SNAPSHOT"
        }
        files.versions.writeText(nextDevVersion)
        printInfo("${files.versions.path} has been edited with next development version ($nextDevVersion).")
    }
    `Commit 'prepare next dev version'` -> git.commitAllFiles(
        commitMessage = "Prepare next development version.".also {
            requestUserConfirmation(
                yesNoQuestion = """Will commit all files with message "$it" Continue?"""
            )
        }
    )
    `Push, at last` -> {
        requestUserConfirmation("Finally the last step: the last push. Continue?")
        git.pushToOrigin()
    }
}

fun performRelease() {
    var stepIndex = startAtStep.ordinal
    val enumValues = enumValues<ReleaseStep>().toList()
    while (stepIndex < enumValues.size) {
        val step = enumValues[stepIndex]
        OngoingRelease.currentStepName = step.name
        OngoingRelease.write()
        cliUi.runReleaseStep(step)
        stepIndex++
    }
    OngoingRelease.clear()
    cliUi.printQuestion("All Done! Let's brag about this new release!!")
}

performRelease()
