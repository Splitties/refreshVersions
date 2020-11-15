package de.fayard.refreshVersions

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
}
