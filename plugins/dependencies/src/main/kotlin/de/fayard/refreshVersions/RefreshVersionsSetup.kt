@file:JvmName("RefreshVersionsSetup")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.internal.legacy.LegacyBootstrapMigrator
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.refreshVersions
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
    versionsPropertiesFile: File = rootDir.resolve("versions.properties")
) {
    require(settings.isBuildSrc.not()) {
        "This bootstrap is only for the root project. For buildSrc, please call " +
                "bootstrapRefreshVersionsForBuildSrc() instead (Kotlin DSL)," +
                "or RefreshVersionsSetup.bootstrapForBuildSrc() if you're using Groovy DSL."
    }
    extensions.create<RefreshVersionsExtension>("refreshVersions")
    refreshVersions {
        this.extraArtifactVersionKeyRules = extraArtifactVersionKeyRules
        this.versionsPropertiesFile = versionsPropertiesFile
    }
    apply(plugin = "de.fayard.refreshVersions")
    with(LegacyBootstrapMigrator) { replaceBootstrapWithPluginsDslSetup() }
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
    require(isBuildSrc)
    apply(plugin = "de.fayard.refreshVersions")
    with(LegacyBootstrapMigrator) { replaceBootstrapWithPluginsDslSetup() }
}
