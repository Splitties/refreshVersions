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
    private val repoDir = File(repoUrl.substringAfter("file:"))

    init {
        require(repoUrl.endsWith('/'))
    }

    override suspend fun getXmlMetadataOrNull(): String? = try {
        val targetDir = repoDir.resolve("${moduleId.group!!.replace('.', '/')}/${moduleId.name}")
        val fileNames: Array<String>? = targetDir.list()
        fileNames?.filter {
            it.startsWith("maven-metadata") && it.endsWith(".xml")
        }?.also {
            check(it.size <= 1) {
                "Expected only one maven-metadata xml file but got ${it.size} matching files!"
            }
        }?.singleOrNull()?.let { filename ->
            targetDir.resolve(filename).readText()
        }
    } catch (e: FileNotFoundException) {
        null
    }
}
