@file:JvmName("RefreshVersionsCoreSetup")

package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.internal.*
import de.fayard.refreshVersions.core.internal.migrations.runMigrationsIfNeeded
import de.fayard.refreshVersions.core.internal.removals_replacement.RemovedDependencyNotationsReplacementInfo
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.file.RegularFile
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.artifacts.dependencies.DefaultClientModule
import org.gradle.kotlin.dsl.apply
import org.gradle.tooling.UnsupportedVersionException
import org.gradle.util.GradleVersion
import java.io.File

/**
 * Boostrap refreshVersions-core **only** (excludes dependencies constants and version key rules).
 *
 * Supports both Kotlin and Groovy Gradle DSL.
 *
 * // **`settings.gradle.kts`**
 * ```kotlin
 * import de.fayard.refreshVersions.core.bootstrap
 *
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions-core:VERSION")
 * }
 *
 * settings.bootstrapRefreshVersionsCore()
 * ```
 *
 * // **`settings.gradle`**
 * ```groovy
 * import de.fayard.refreshVersions.core.RefreshVersionsCoreSetup
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions-core:VERSION")
 * }
 *
 * RefreshVersionsCoreSetup.bootstrap(settings)
 * ```
 */
@JvmOverloads
@JvmName("bootstrap")
fun Settings.bootstrapRefreshVersionsCore(
    artifactVersionKeyRules: List<String> = emptyList(),
    versionsPropertiesFile: File = rootDir.resolve("versions.properties"),
    getDependenciesMapping: () -> List<DependencyMapping> = { emptyList() },
    getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String> = { emptyMap() },
    getRemovedDependencyNotationsReplacementInfo: (() -> RemovedDependencyNotationsReplacementInfo)? = null,
    versionRejectionFilter: (DependencySelection.() -> Boolean)? = null
) {
    null.checkGradleVersionIsSupported()
    require(settings.isBuildSrc.not()) {
        "This bootstrap is only for the root project. For buildSrc, please call " +
            "bootstrapRefreshVersionsCoreForBuildSrc() instead (Kotlin DSL)," +
            "or RefreshVersionsCoreSetup.bootstrapForBuildSrc() if you're using Groovy DSL."
    }

    gradle.rootProject {
        // This ensures configuration cache is invalidated if versionsPropertiesFile is edited.
        // Without that, changes to dependencies versions would be ignored after the initial caching.
        val regularFile: RegularFile = layout.projectDirectory.file(versionsPropertiesFile.path)
        val provider = providers.fileContents(regularFile).asBytes.forUseAtConfigurationTime()
        provider.isPresent // Checking the isPresent property marks the provider as used.
        // If we didn't do it, the provider would be treated as unused,
        // and changes to the underlying file would not invalidate the configuration cache.
    }
    RefreshVersionsConfigHolder.initialize(
        settings = settings,
        artifactVersionKeyRules = artifactVersionKeyRules,
        getRemovedDependenciesVersionsKeys = getRemovedDependenciesVersionsKeys,
        versionsPropertiesFile = versionsPropertiesFile,
        versionRejectionFilter = versionRejectionFilter
    )
    val versionsPropertiesModel = RefreshVersionsConfigHolder.readVersionsPropertiesModel()
    getRemovedDependencyNotationsReplacementInfo?.let {
        runMigrationsIfNeeded(
            projectDir = rootDir,
            versionsPropertiesFile = versionsPropertiesFile,
            versionsPropertiesModel = versionsPropertiesModel,
            dependencyMapping = getDependenciesMapping(),
            getRemovedDependencyNotationsReplacementInfo = it
        )
    }
    setupRefreshVersions(
        settings = settings,
        versionsMap = RefreshVersionsConfigHolder.readVersionsMap(versionsPropertiesModel),
    )
}

/**
 * **For buildSrc only!**
 *
 * Boostrap refreshVersions-core **only** (excludes dependencies constants and version key rules).
 *
 * Supports both Kotlin and Groovy Gradle DSL.
 *
 * // **`settings.gradle.kts`**
 * ```kotlin
 * import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCoreForBuildSrc
 *
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions-core:VERSION")
 * }
 *
 * settings.bootstrapRefreshVersionsCoreForBuildSrc()
 * ```
 *
 * // **`settings.gradle`**
 * ```groovy
 * import de.fayard.refreshVersions.core.RefreshVersionsCoreSetup
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions-core:VERSION")
 * }
 *
 * RefreshVersionsCoreSetup.bootstrapForBuildSrc(settings)
 * ```
 */
@JvmOverloads
@JvmName("bootstrapForBuildSrc")
fun Settings.bootstrapRefreshVersionsCoreForBuildSrc(
    getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String> = { emptyMap() }
) {
    null.checkGradleVersionIsSupported()
    RefreshVersionsConfigHolder.initializeBuildSrc(this, getRemovedDependenciesVersionsKeys)
    setupRefreshVersions(settings = settings)
}

private const val minimumGradleVersionString = "6.8" // Because we require Kotlin 1.4
private val minimumGradleVersion = GradleVersion.version(minimumGradleVersionString)

/**
 * This is an extension on `Nothing?` to avoid polluting top-level.
 */
@InternalRefreshVersionsApi
fun @Suppress("unused") Nothing?.checkGradleVersionIsSupported() {
    minimumGradleVersion.version
    if (GradleVersion.current() < minimumGradleVersion) {
        throw UnsupportedVersionException(
            """
            The plugin "de.fayard.refreshVersions" only works with Gradle $minimumGradleVersionString and above.
            See https://jmfayard.github.io/refreshVersions/setup/#update-gradle-if-needed
            """.trimIndent()
        )
    }
}

/**
 * Sets up a resolution strategy for the plugins that does the following:
 * For each plugin, tries to find the corresponding version declared in the versions properties file, and uses it.
 *
 * The expected property key is the id of the plugin, prefixed with `plugin.` (e.g. `plugin.io.fabric`), excepted for
 * the Kotlin and Android gradle plugins where `version.kotlin` and `plugin.android` are used.
 *
 * Note that the properties can alias to another one from `version.properties` (hard limit is 5 property redirects),
 * you just need to put the key of the property you want to use (e.g. `plugin.some-plugin=plugin.android`).
 *
 * This function also sets up the module for the Android and Fabric (Crashlytics) Gradle plugins, so you can avoid the
 * buildscript classpath configuration boilerplate.
 */
private fun setupRefreshVersions(
    settings: Settings,
    versionsMap: Map<String, String> = RefreshVersionsConfigHolder.readVersionsMap()
) {
    UsedPluginsTracker.clearFor(settings)
    UsedVersionForTracker.clearFor(settings)
    @Suppress("unchecked_cast")
    setupPluginsVersionsResolution(
        settings = settings,
        properties = versionsMap
    )

    settings.gradle.setupVersionPlaceholdersResolving(versionsMap = versionsMap)

    settings.gradle.rootProject {
        apply<RefreshVersionsCorePlugin>()
    }
}

private fun setupPluginsVersionsResolution(
    settings: Settings,
    properties: Map<String, String>
) {
    settings.pluginManagement {
        resolutionStrategy.eachPlugin {
            val pluginId = requested.id.id
            if (pluginId == "de.fayard.refreshVersions") {
                return@eachPlugin // Already in the buildscript with a defined version that will be used.
            }
            val pluginNamespace = requested.id.namespace ?: ""
            fun String.namespaceStartsWith(prefix: String): Boolean {
                require(prefix.endsWith('.').not())
                return this == prefix || this.startsWith("$prefix.")
            }

            val versionKey = when {
                pluginNamespace.namespaceStartsWith("org.jetbrains.kotlin") -> "version.kotlin"
                pluginNamespace.namespaceStartsWith("com.android") -> "plugin.android"
                else -> "plugin.$pluginId"
            }
            val version = resolveVersion(properties, versionKey)
            if (version == null) {
                val pluginVersion = requested.version ?: return@eachPlugin
                UsedPluginsTracker.pluginHasNoEntryInVersionsFile(
                    settings = settings,
                    dependency = pluginIdToDependency(pluginId, pluginVersion)
                )
                return@eachPlugin
            }
            when {
                pluginNamespace.startsWith("com.android") -> {
                    val dependencyNotation = "com.android.tools.build:gradle:$version"
                    UsedPluginsTracker.noteUsedPluginDependency(
                        settings = settings,
                        dependencyNotation = dependencyNotation,
                        repositories = repositories
                    )
                    useModule(dependencyNotation)
                }
                else -> {
                    UsedPluginsTracker.noteUsedPluginDependency(
                        settings = settings,
                        dependencyNotation = "$pluginId:$pluginId.gradle.plugin:$version",
                        repositories = repositories
                    )
                    useVersion(version)
                }
            }
        }
    }
}

// TODO: try to centralize version key shorthands
internal fun pluginDependencyNotationToVersionKey(dependencyNotation: String): String? =
    when {
        dependencyNotation.startsWith("com.android") -> "plugin.android"
        dependencyNotation.startsWith("org.jetbrains.kotlin.") -> "version.kotlin"
        dependencyNotation.endsWith(".gradle.plugin") -> "plugin." + dependencyNotation.removeSuffix(".gradle.plugin")
        else -> null
    }

internal fun pluginIdToDependency(pluginId: String, version: String): ExternalDependency =
    DefaultClientModule(pluginId, "$pluginId.gradle.plugin", version)
