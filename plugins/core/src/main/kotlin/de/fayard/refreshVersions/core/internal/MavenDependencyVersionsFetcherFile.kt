package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import java.io.File
import java.io.FileNotFoundException

internal class MavenDependencyVersionsFetcherFile(
    moduleId: ModuleId,
    repoUrl: String
) : MavenDependencyVersionsFetcher(
    moduleId = moduleId,
    repoUrl = repoUrl
) {
    private val baseFolder = File(repoUrl)

    init {
        require(repoUrl.endsWith('/'))
    }

    override suspend fun getXmlMetadataOrNull(): String? = try {
        val targetDir = baseFolder.resolve("${moduleId.group!!.replace('.', '/')}/${moduleId.name}")
        requireNotNull(targetDir.list()) {
            "Expected a readable directory for the file repository!"
        }.filter {
            it.startsWith("maven-metadata") && it.endsWith(".xml")
        }.also {
            check(it.size <= 1) {
                "Expected only one maven-metadata xml file but got ${it.size} matching files!"
            }
        }.singleOrNull()?.let { filename ->
            targetDir.resolve(filename).readText()
        }
    } catch (e: FileNotFoundException) {
        null
    }
}
