package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.FeatureFlag
import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.HasImplicitReceiver
import org.gradle.api.Incubating
import org.gradle.api.artifacts.ComponentSelection
import org.gradle.api.artifacts.ExternalDependency
import java.io.File

open class RefreshVersionsExtension {

    var versionsPropertiesFile: File? = null
    var extraArtifactVersionKeyRules: List<String> = emptyList()
    internal var isBuildSrcLibsEnabled = false
    internal var versionFilter: ComponentFilter? = null

    @Incubating
    fun enableBuildSrcLibs() {
        isBuildSrcLibsEnabled = true
    }

    fun extraArtifactVersionKeyRules(file: File) {
        extraArtifactVersionKeyRules = extraArtifactVersionKeyRules + file.readText()
    }

    fun extraArtifactVersionKeyRules(rawRules: String) {
        extraArtifactVersionKeyRules = extraArtifactVersionKeyRules + rawRules
    }

    fun featureFlags(extension: Action<FeatureFlagExtension>) {
        extension.execute(FeatureFlagExtension())
    }

    fun rejectVersionIf(filter: Closure<ComponentFilter>) {
        versionFilter = filter.call()
    }

    fun rejectVersionIf(filter: ComponentSelectionWithCurrent.() -> Boolean) {
        versionFilter = object : ComponentFilter {
            override fun reject(candidate: ComponentSelectionWithCurrent): Boolean {
                return candidate.filter()
            }
        }
    }
}

@HasImplicitReceiver
interface ComponentFilter {
    fun reject(candidate: ComponentSelectionWithCurrent): Boolean
}

data class ComponentSelectionWithCurrent(
    val currentVersion: String,
    val candidate: ExternalDependency
)

open class FeatureFlagExtension {
    fun enable(flag: FeatureFlag) {
        FeatureFlag.userSettings[flag] = true
    }
    fun disable(flag: FeatureFlag) {
        FeatureFlag.userSettings[flag] = false
    }
}
