@file:JvmName("RefreshVersionsSetup")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply

@JvmOverloads
fun Settings.bootstrapRefreshVersions(
    extraArtifactVersionKeyRules: List<String> = emptyList()
) {
    bootstrapRefreshVersionsCore(
        artifactVersionKeyRules = if (extraArtifactVersionKeyRules.isEmpty()) {
            RefreshVersionsPlugin.artifactVersionKeyRules //Avoid unneeded list copy.
        } else {
            RefreshVersionsPlugin.artifactVersionKeyRules + extraArtifactVersionKeyRules
        }
    )
    gradle.rootProject {
        apply<RefreshVersionsPlugin>()
    }
}
