@file:JvmName("RefreshVersionsSetup")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import de.fayard.refreshVersions.core.internal.defaultVersionsPropertiesFile
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
    versionsPropertiesFile: File = defaultVersionsPropertiesFile()
) {
    bootstrapRefreshVersionsCore(
        artifactVersionKeyRules = if (extraArtifactVersionKeyRules.isEmpty()) {
            RefreshVersionsPlugin.artifactVersionKeyRules // Avoid unneeded list copy.
        } else {
            RefreshVersionsPlugin.artifactVersionKeyRules + extraArtifactVersionKeyRules
        },
        versionsPropertiesFile = versionsPropertiesFile
    )
    gradle.rootProject {
        apply<RefreshVersionsPlugin>()
    }
}
