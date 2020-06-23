@file:JvmName("RefreshVersionsSetup")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import de.fayard.refreshVersions.core.internal.defaultVersionsPropertiesFile
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import java.io.File

@JvmOverloads
@JvmName("bootstrap")
fun Settings.bootstrapRefreshVersions(
    extraArtifactVersionKeyRules: List<String> = emptyList(),
    versionsPropertiesFile: File = defaultVersionsPropertiesFile()
) {
    bootstrapRefreshVersionsCore(
        artifactVersionKeyRules = if (extraArtifactVersionKeyRules.isEmpty()) {
            RefreshVersionsPlugin.artifactVersionKeyRules //Avoid unneeded list copy.
        } else {
            RefreshVersionsPlugin.artifactVersionKeyRules + extraArtifactVersionKeyRules
        },
        versionsPropertiesFile = versionsPropertiesFile
    )
    gradle.rootProject {
        apply<RefreshVersionsPlugin>()
    }
}
