package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import java.io.File

internal class MavenDependencyVersionsFetcherFile(
    moduleId: ModuleId,
    repoUrl: String
) : MavenDependencyVersionsFetcher(
    moduleId = moduleId,
    repoUrl = repoUrl
) {
    private val baseFolder = File(repoUrl)

    init {
//        require(repoUrl.endsWith('/'))
    }

    override suspend fun getMetadata(): String {
        return baseFolder.resolve("${moduleId.group!!.replace('.', '/')}/${moduleId.name}/maven-metadata.xml").readText()
    }
}
