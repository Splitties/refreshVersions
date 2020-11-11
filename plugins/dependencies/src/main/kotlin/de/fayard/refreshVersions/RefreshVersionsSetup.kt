@file:JvmName("RefreshVersionsSetup")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCoreForBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import java.io.File

/**
 * Boostrap refreshVersions.
 *
 * Supports both Kotlin and Groovy Gradle DSL.
 *
 * // **`settings.gradle.kts`**
 * ```kotlin
 * import de.fayard.refreshVersions.bootstrapRefreshVersions
 *
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
 * }
 *
 * settings.bootstrapRefreshVersions()
 * ```
 *
 * // **`settings.gradle`**
 * ```groovy
 * import de.fayard.refreshVersions.RefreshVersionsSetup
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
 * }
 *
 * RefreshVersionsSetup.bootstrap(settings)
 * ```
 */
@JvmOverloads
@JvmName("bootstrap")
fun Settings.bootstrapRefreshVersions(
    extraArtifactVersionKeyRules: List<String> = emptyList(),
    versionsPropertiesFile: File = rootDir.resolve("versions.properties"),
    pluginResolution: (id: String, String) -> String? = { _, _ -> null}
) {
    require(settings.isBuildSrc.not()) {
        "This bootstrap is only for the root project. For buildSrc, please call " +
                "bootstrapRefreshVersionsForBuildSrc() instead (Kotlin DSL)," +
                "or RefreshVersionsSetup.bootstrapForBuildSrc() if you're using Groovy DSL."
    }
    bootstrapRefreshVersionsCore(
        artifactVersionKeyRules = if (extraArtifactVersionKeyRules.isEmpty()) {
            RefreshVersionsPlugin.artifactVersionKeyRules // Avoid unneeded list copy.
        } else {
            RefreshVersionsPlugin.artifactVersionKeyRules + extraArtifactVersionKeyRules
        },
        versionsPropertiesFile = versionsPropertiesFile,
        pluginResolution = pluginResolution
    )
    gradle.rootProject {
        apply<RefreshVersionsPlugin>()
    }
}

/**
 * **For buildSrc only!**
 *
 * Boostrap refreshVersions.
 *
 * Supports both Kotlin and Groovy Gradle DSL.
 *
 * // **`settings.gradle.kts`**
 * ```kotlin
 * import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc
 *
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
 * }
 *
 * settings.bootstrapRefreshVersionsForBuildSrc()
 * ```
 *
 * // **`settings.gradle`**
 * ```groovy
 * import de.fayard.refreshVersions.RefreshVersionsSetup
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
 * }
 *
 * RefreshVersionsSetup.bootstrapForBuildSrc(settings)
 * ```
 */
@JvmName("bootstrapForBuildSrc")
fun Settings.bootstrapRefreshVersionsForBuildSrc() {
    bootstrapRefreshVersionsCoreForBuildSrc()
}
