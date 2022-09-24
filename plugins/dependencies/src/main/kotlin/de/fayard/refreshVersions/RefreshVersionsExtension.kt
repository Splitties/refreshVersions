package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.DependencySelection
import de.fayard.refreshVersions.core.FeatureFlag
import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.Incubating
import java.io.File

open class RefreshVersionsExtension {

    var versionsPropertiesFile: File? = null
    var extraArtifactVersionKeyRules: List<String> = emptyList()
    internal var isBuildSrcLibsEnabled = false
    internal var versionRejectionFilter: (DependencySelection.() -> Boolean)? = null

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

    fun rejectVersionIf(filter: Closure<Boolean>) {
        versionRejectionFilter = {
            filter.delegate = this
            filter.call()
        }
    }

    fun rejectVersionIf(filter: DependencySelection.() -> Boolean) {
        versionRejectionFilter = filter
    }
}

open class FeatureFlagExtension {
    fun enable(flag: FeatureFlag) {
        FeatureFlag.userSettings[flag] = true
    }
    fun disable(flag: FeatureFlag) {
        FeatureFlag.userSettings[flag] = false
    }
}
