#!/usr/bin/env kotlin

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.8.0")

import Build_main.GradleTasks.CHECK
import Build_main.GradleTasks.CONFIGURATION_CACHE
import Build_main.GradleTasks.HELP
import Build_main.GradleTasks.PUBLISH_PLUGINS
import Build_main.GradleTasks.PUBLISH_TO_MAVEN_LOCAL
import Build_main.GradleTasks.REFRESH_VERSIONS
import it.krzeminski.githubactions.actions.Action
import it.krzeminski.githubactions.actions.actions.CheckoutV2
import it.krzeminski.githubactions.actions.actions.SetupJavaV2
import it.krzeminski.githubactions.actions.gradle.GradleBuildActionV2
import it.krzeminski.githubactions.domain.RunnerType
import it.krzeminski.githubactions.domain.triggers.PullRequest
import it.krzeminski.githubactions.domain.triggers.Push
import it.krzeminski.githubactions.domain.triggers.WorkflowDispatch
import it.krzeminski.githubactions.dsl.JobBuilder
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.toYaml
import it.krzeminski.githubactions.yaml.writeToFile
import java.nio.file.Path
import java.nio.file.Paths


object Files {
    val source: Path = Paths.get("build.main.kts")
    val checkBuild: Path = Paths.get("check-build.yml")
    val buildMkdocs: Path = Paths.get("build-mkdocs-website.yml")
    val publishMkdocs: Path = Paths.get("publish-mkdocs-website.yml")
    val releasePlugins: Path = Paths.get("release-plugins.yml")
    val publishToSonatypeSnapshots: Path = Paths.get("publish-to-sonatype-snapshots.yml")
}

val checkBuild = workflow(
    name = "Check build and tests of plugins and samples",
    targetFile = Files.checkBuild,
    sourceFile = Files.source,
    on = listOf(
        WorkflowDispatch(
            inputs = Inputs.checkBuildInputs
        ),
        PullRequest(
            paths = Inputs.checkBuildPaths,
        ),
        Push(
            branches = listOf(Branches.MAIN),
            paths = Inputs.checkBuildPaths,
        ),
    ),
) {

    job("check-all", RunnerType.UbuntuLatest) {
        run(
            name = "Enable long paths for git Windows",
            command = "git config --global core.longpaths true",
            condition = "\${{ runner.os == 'Windows' }}"
        )

        step(Steps.checkout())

        step(Steps.setupJava)

        val stepWithBuildSrc = "Run refreshVersions on sample-multi-modules"

        val projectTaskMap: List<Triple<String, Project, String>> = listOf(
            Triple("Check plugins and publish them to MavenLocal", Project.plugins, "$CHECK $PUBLISH_TO_MAVEN_LOCAL"),
            Triple("Run refreshVersions on sample-kotlin", Project.`sample-kotlin`, REFRESH_VERSIONS),
            Triple("Check sample-kotlin", Project.`sample-kotlin`, "$CHECK $CONFIGURATION_CACHE"),
            Triple("Run refreshVersions on sample-groovy", Project.`sample-groovy`, REFRESH_VERSIONS),
            Triple("Check sample-groovy", Project.`sample-groovy`, "$CHECK $CONFIGURATION_CACHE"),
            Triple("Check buildSrc of sample-groovy (simulates IDE Gradle sync)", Project.`sample-groovy`, HELP),
            Triple(stepWithBuildSrc, Project.`sample-multi-modules`, REFRESH_VERSIONS),
            Triple("Check sample-multi-modules", Project.`sample-multi-modules`, "$CHECK $CONFIGURATION_CACHE"),
            Triple("Run refreshVersions on sample-kotlin-js", Project.`sample-kotlin-js`, REFRESH_VERSIONS),
            Triple("Check sample-kotlin-js", Project.`sample-kotlin-js`, CHECK),
            Triple("Run refreshVersions on sample-android", Project.`sample-android`, REFRESH_VERSIONS),
            Triple("Check sample-android", Project.`sample-android`, CHECK),
        )
        for ((stepName, project, arguments) in projectTaskMap) {
            step(
                step = Steps.gradle( "$arguments --stacktrace", project, stepName, buildSrc = stepName == stepWithBuildSrc),
                condition = conditionOf(project.name, arguments),
            )
        }
    }
}


val releasePlugins = workflow(
    name = "Publish plugins",
    targetFile = Files.releasePlugins,
    sourceFile = Files.source,
    on = listOf(
        WorkflowDispatch(),
        PullRequest(
            paths = listOf("plugins/version.txt"),
            branches = listOf("release"),
            //TODO:     types = listOf(opened)
        ),
    )) {
    println("""
        Warning: in ${Files.releasePlugins} you should add manually:
        on:  pull_request:  types: [opened]
           """.trimIndent())
    job("gradle-plugins-publishing", RunnerType.UbuntuLatest) {
        step(Steps.checkout())
        step(Steps.setupJava)

        val secrets = "${'$'}{{ secrets"
        val properties = """-Pgradle.publish.key=$secrets.gradle_publish_key }} -Pgradle.publish.secret=$secrets.gradle_publish_secret }}"""

        step(Steps.gradle(
            arguments = "$PUBLISH_PLUGINS --scan $properties",
            project = Project.plugins,
        ), env = Env.sonatypeCredentials)
    }
}

val publishToSonatypeSnapshots = workflow(
    name = "Publish to Sonatype Snapshots",
    targetFile = Files.publishToSonatypeSnapshots,
    sourceFile = Files.source,
    on = listOf(
        WorkflowDispatch(),
    )) {
    job("build-and-upload", RunnerType.UbuntuLatest) {
        step(Steps.checkout())
        step(Steps.setupJava)
        step(
            step = Steps.gradle(GradleTasks.publishToSonatype, Project.plugins),
            env = Env.sonatypeCredentials,
        )
    }
}


listOf(checkBuild, releasePlugins, publishToSonatypeSnapshots)
    .forEach { workflow ->
        println("Updating ${workflow.targetFile}")
        workflow.writeToFile()
    }

object GradleTasks {
    val publishToSonatype = "publishAllPublicationsToSonatypeSnapshotsRepository"
    val REFRESH_VERSIONS = "refreshVersions"
    val CHECK = "check"
    val HELP = "help"
    val CONFIGURATION_CACHE = "--configuration-cache"
    val PUBLISH_TO_MAVEN_LOCAL = "publishToMavenLocal"
    val PUBLISH_PLUGINS = "publishPlugins"

}


object Env {
    val secrets = "${'$'}{{ secrets"

    val sonatypeCredentials = linkedMapOf(
        "sonatype_username" to "$secrets.SONATYPE_USERNAME }}",
        "sonatype_password" to "$secrets.SONATYPE_PASSWORD }}",
        "GPG_key_id" to "$secrets.GPG_KEY_ID }}",
        "GPG_private_key" to "$secrets.GPG_PRIVATE_KEY }}",
        "GPG_private_password" to "$secrets.GPG_PRIVATE_PASSWORD }}",
    )

    val githubToken = linkedMapOf(
        "GITHUB_TOKEN" to "$secrets.GITHUB_TOKEN }}",
    )
}

object Inputs {
    val checkBuildPaths = listOf(
        "plugins/**",
        "sample-kotlin/**",
        "sample-groovy/**",
        "!**.md",
        "!.editorconfig",
        "!**/.gitignore",
        "!**.adoc",
        "!docs/**",
        )

    private fun inputFor(project: Project, default: Boolean = true) =
        project.name to WorkflowDispatch.Input(
            description = "Enable ${project.name}",
            required = true,
            type = WorkflowDispatch.Type.Boolean,
            default = "$default",
        )

    val refreshVersionsInput = "run-refreshVersions-task" to WorkflowDispatch.Input(
        description = "Run the refreshVersions task",
        required = true,
        type = WorkflowDispatch.Type.Boolean,
        default = "false",
    )

    val runOnInput = "run-on" to WorkflowDispatch.Input(
        description = "Where to run this workflow",
        required = true,
        type = WorkflowDispatch.Type.Choice,
        default = "ubuntu-latest",
        options = RunnerType.values().map { it.toYaml() }
    )

    val checkBuildInputs: Map<String, WorkflowDispatch.Input> = mapOf(
        refreshVersionsInput,
        inputFor(Project.`sample-kotlin`),
        inputFor(Project.`sample-groovy`),
        inputFor(Project.`sample-multi-modules`),
        inputFor(Project.`sample-kotlin-js`),
        inputFor(Project.`sample-android`, default = false),
        runOnInput,
    )
}

object Steps {

    fun checkout(branch: String? = null, fetchDepth: CheckoutV2.FetchDepth? = null) = Step(
        "check-out",
        CheckoutV2(
            ref = branch, fetchDepth = fetchDepth
        )
    )

    val setupJava: Step = Step(
        "setup-java",
        SetupJavaV2(
            distribution = SetupJavaV2.Distribution.Adopt, javaVersion = "11"
        )
    )

    fun gradle(arguments: String, project: Project, stepName: String? = null, buildSrc: Boolean = false): Step {
        val firstArg = arguments.split(" ").first()
        return Step(
            name = stepName ?: "$project gradle $firstArg",
            action = GradleBuildActionV2(
                arguments = arguments,
                gradleExecutable = "$project/gradlew",
                buildRootDirectory = if (buildSrc) "${project.name}/buidSrc" else project.name,
            )
        )
    }
}

@Suppress("EnumEntryName")
enum class Project {
    plugins, `sample-kotlin`, `sample-groovy`, `sample-multi-modules`, `sample-kotlin-js`, `sample-android`
}

object Branches {

    const val MAIN = "main"
}

fun conditionOf(project: String, arguments: String): String? {
    val conditionSample = when {
        project == "plugins" -> ""
        else -> " github.event.inputs.$project == 'true' "
    }

    val conditionRefreshVersions = when {
        arguments.contains("refreshVersions").not() -> ""
        else -> "&& github.event.inputs.run-refreshVersions-task == 'true' "
    }
    val combine = "$conditionSample$conditionRefreshVersions"
    return if (combine.isBlank()) null else "\${{ $combine }}"
}



/***
 * Below this comment are things that should hopefully become part of kotlin-dsl-github-actions
 */

data class Step(val name: String, val action: Action)

data class Command(val name: String, val command: String)

fun JobBuilder.step(name: String, env: Map<String, String> = emptyMap(), condition: String? = null, action: () -> Action) =
    uses(name, action(), LinkedHashMap(env), condition)

fun JobBuilder.step(step: Step, env: Map<String, String> = emptyMap(), condition: String? = null) =
    uses(step.name, step.action, LinkedHashMap(env), condition)

fun JobBuilder.run(command: Command, env: Map<String, String> = emptyMap(), condition: String? = null) =
    run(command.name, command.command, LinkedHashMap(env), condition)
