package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.FeatureFlag
import java.io.File

open class RefreshVersionsExtension {

    var versionsPropertiesFile: File? = null
    var extraArtifactVersionKeyRules: List<String> = emptyList()

    fun extraArtifactVersionKeyRules(file: File) {
        extraArtifactVersionKeyRules = extraArtifactVersionKeyRules + file.readText()
    }

    fun extraArtifactVersionKeyRules(rawRules: String) {
        extraArtifactVersionKeyRules = extraArtifactVersionKeyRules + rawRules
    }

    fun enable(vararg flags: FeatureFlag) {
        FeatureFlag.userSettings.putAll(flags.map { it to true })
    }
    fun disable(vararg flags: FeatureFlag) {
        FeatureFlag.userSettings.putAll(flags.map { it to false })
    }
}
