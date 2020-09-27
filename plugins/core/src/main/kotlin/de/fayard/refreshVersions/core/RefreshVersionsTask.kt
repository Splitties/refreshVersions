package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.*
import de.fayard.refreshVersions.core.internal.updateVersionsProperties
import kotlinx.coroutines.*
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Dependency
import org.gradle.api.tasks.TaskAction
import org.gradle.util.GradleVersion

open class RefreshVersionsTask : DefaultTask() {

    @TaskAction
    fun taskActionRefreshVersions() {

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance a little more.

        val result: VersionCandidatesLookupResult = runBlocking {
            lookupVersionCandidates(
                httpClient = RefreshVersionsConfigHolder.httpClient,
                project = project,
                versionProperties = RefreshVersionsConfigHolder.readVersionProperties(),
                versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
            )
        }
        project.rootProject.updateVersionsProperties(result.dependenciesWithVersionsCandidates)
        project.rootProject.updateGradleSettingsIncludingForBuildSrc(result.selfUpdates)

        warnAboutHardcodedVersionsIfAny(result.dependenciesWithHardcodedVersions)
        warnAboutDynamicVersionsIfAny(result.dependenciesWithDynamicVersions)
        warnAboutGradleUpdateAvailableIfAny()
    }

    private fun warnAboutGradleUpdateAvailableIfAny() = runBlocking {
        val checker = GradleUpdateChecker(RefreshVersionsConfigHolder.httpClient, RefreshVersionsConfigHolder.moshi)
        val version = checker.fetchGradleCurrentVersion() ?: return@runBlocking
        if (GradleVersion.version(version.version) > GradleVersion.current()) {
            println("""
                |
                |> Checking Gradle's version
                |You are currently running Gradle ${GradleVersion.current().version}
                |To update to the current stable version, run
                |${'$'} ./gradlew wrapper --gradle-version ${version.version}
            """.trimMargin())
        }
    }

    private fun warnAboutDynamicVersionsIfAny(dependenciesWithDynamicVersions: List<Dependency>) {
        if (dependenciesWithDynamicVersions.isNotEmpty()) {
            //TODO: Suggest running a diagnosis task to list the dynamic versions.
            logger.error(
                """Found ${dependenciesWithDynamicVersions.count()} dynamic dependencies versions!
                    |This makes builds unreproducible, and can make you use unstable versions unknowingly.
                    |
                    |The simple fix is to replace the dynamic version by the version placeholder, i.e. the underscore (_)
                    |
                    |Another solution if that fits your needs is to specify a non dynamic strict or preferred version constraint.
                    |
                    |If you're not convinced to stop using dynamic versions, here's an article for you:
                    |https://blog.danlew.net/2015/09/09/dont-use-dynamic-versions-for-your-dependencies/
                    """.trimMargin()
            )
        }
    }

    private fun warnAboutHardcodedVersionsIfAny(dependenciesWithHardcodedVersions: List<Dependency>) {
        if (dependenciesWithHardcodedVersions.isNotEmpty()) {
            //TODO: Suggest running a diagnosis task to list the hardcoded versions.
            val warnFor = (dependenciesWithHardcodedVersions).take(3).map {
                "${it.group}:${it.name}:${it.version}"
            }
            val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name
            logger.warn(
                """Found ${dependenciesWithHardcodedVersions.count()} hardcoded dependencies versions.
                    |
                    |$warnFor...
                    |
                    |To ensure single source of truth, refreshVersions only works with version placeholders,
                    |that is the explicit way of marking the version is not there (but in the $versionsFileName file).
                    |
                    |If you intentionally want to keep hardcoded versions so a module has a different version of a
                    |dependency than the rest of the project, you can safely ignore this warning for these artifacts,
                    |but keep in mind refreshVersions will not show available updates for these.
                    |
                    |Note that a migration task is planned in a future version of refreshVersions.
                    |
                    |See https://github.com/jmfayard/refreshVersions/issues/160""".trimMargin()
            )
            //TODO: Replace issue link above with stable link to explanation in documentation.
        }
    }
}
