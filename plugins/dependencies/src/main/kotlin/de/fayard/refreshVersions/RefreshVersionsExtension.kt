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

    fun extraVersionRules(vararg rules: PrefixRule) {
        extraArtifactVersionKeyRules(rules.joinToString("\n\n") { it.toRawRules() })
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

data class PrefixRule(
    val versionName: String,
    val mavenGroup: String,
    val prefix: String? = null
) {
    fun toRawRules(): String {
        println("TODO: refreshVersions.extraVersionRules(PrefixRule(...)) not implemented. See https://github.com/jmfayard/refreshVersions/issues/580")
        return ""
    }
}
