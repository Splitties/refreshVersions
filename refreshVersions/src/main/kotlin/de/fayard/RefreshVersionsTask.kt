package de.fayard

import de.fayard.internal.Dependency
import de.fayard.internal.PluginConfig
import de.fayard.internal.RefreshVersionsExtensionImpl
import de.fayard.internal.UpdateProperties
import de.fayard.internal.logFileWasModified
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.getByType
import org.gradle.util.GradleVersion

@Suppress("UnstableApiUsage")
open class RefreshVersionsTask : DefaultTask() {

    @Input
    @Option(description = "Update all versions, I will check git diff afterwards")
    var update: Boolean = false

    @TaskAction
    fun taskActionGradleProperties() {
        val extension: RefreshVersionsExtensionImpl = extension()
        val updateGradleProperties = UpdateProperties()

        val specialDependencies =
            listOf(PluginConfig.gradleRefreshVersions, PluginConfig.gradleLatestVersion(GradleVersion.current().version, PluginConfig.GRADLE_LATEST_VERSION))

        val dependencies: List<Dependency> = (unsortedParsedDependencies + specialDependencies)
            .sortedBy { it.versionProperty }
            .distinctBy { it.versionProperty }

        val propertiesFile = project.file(PluginConfig.VERSIONS_PROPERTIES)
        val existed = propertiesFile.canRead()
        updateGradleProperties.generateVersionProperties(propertiesFile, dependencies)
        logFileWasModified(propertiesFile.name, existed)
    }


    private val unsortedParsedDependencies: List<Dependency> by lazy {
        val dependenciesTODO = emptyList<Dependency>()
        dependenciesTODO
            .map { d -> d.maybeUpdate(update || extension().alwaysUpdateVersions) }
    }

    @Input @Optional @Transient
    private lateinit var _extension: RefreshVersionsExtensionImpl

    fun configure(action: Action<RefreshVersionsExtension>) {
        val projectExtension = project.extensions.getByType<RefreshVersionsExtension>() as RefreshVersionsExtensionImpl
        this._extension = projectExtension.defensiveCopy()
        action.execute(this._extension)
    }


    private fun extension(): RefreshVersionsExtensionImpl = _extension

}
