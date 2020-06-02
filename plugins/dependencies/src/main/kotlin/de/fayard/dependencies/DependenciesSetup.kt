@file:JvmName("DependenciesSetup")

package de.fayard.dependencies

import de.fayard.versions.bootstrapRefreshVersions
import org.gradle.api.HasImplicitReceiver
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply

@JvmOverloads
fun Settings.bootstrapRefreshVersionsAndDependencies(
    extraArtifactVersionKeyRules: List<String> = emptyList()
) {
    bootstrapRefreshVersions(
        artifactVersionKeyRules = if (extraArtifactVersionKeyRules.isEmpty()) {
            DependenciesPlugin.artifactVersionKeyRules //Avoid unneeded list copy.
        } else {
            DependenciesPlugin.artifactVersionKeyRules + extraArtifactVersionKeyRules
        }
    )
    gradle.rootProject {
        apply<DependenciesPlugin>()
    }
}
