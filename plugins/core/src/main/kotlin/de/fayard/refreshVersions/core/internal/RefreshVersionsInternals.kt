package de.fayard.refreshVersions.core.internal

import java.io.File
import java.util.Properties

@InternalRefreshVersionsApi
object RefreshVersionsInternals {

    val versionKeyReader: ArtifactVersionKeyReader
        get() = privateArtifactVersionKeyReader

    val versionsPropertiesFile: File
        get() = privateVersionsPropertiesFile

    @Suppress("unchecked_cast")
    fun readVersionProperties(): Map<String, String> = Properties().apply {
        load(versionsPropertiesFile.reader())
    } as Map<String, String>

    internal fun initialize(
        artifactVersionKeyRules: List<String>,
        versionsPropertiesFile: File
    ) {
        privateVersionsPropertiesFile = versionsPropertiesFile.also {
            it.createNewFile() // Creates the file if it doesn't exist yet
        }
        privateArtifactVersionKeyReader = ArtifactVersionKeyReader.fromRules(filesContent = artifactVersionKeyRules)
    }

    private lateinit var privateArtifactVersionKeyReader: ArtifactVersionKeyReader
    private lateinit var privateVersionsPropertiesFile: File
}
