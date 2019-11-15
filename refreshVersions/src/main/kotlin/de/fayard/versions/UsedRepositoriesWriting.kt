package de.fayard.versions

import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.extensions.isRootProject
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.initialization.Settings
import org.gradle.plugin.management.PluginManagementSpec
import java.io.File

internal fun Project.readExtraUsedRepositories(): List<MavenRepoUrl> {
    require(isRootProject)
    require(isBuildSrc.not())
    val files = listOf(
        rootDir.resolve(getUsedMavenRepositoriesFilePath(Type.PluginManagement)),
        rootDir.resolve("buildSrc").resolve(getUsedMavenRepositoriesFilePath(Type.PluginManagement)),
        rootDir.resolve("buildSrc").resolve(getUsedMavenRepositoriesFilePath(Type.Project))
    )
    return files.flatMap { file ->
        if (file.exists()) file.readLines().map { line -> MavenRepoUrl(line) } else emptyList()
    }
}

/**
 * Writes repositories used in pluginManagement to a file so it can be used later in [readExtraUsedRepositories].
 */
internal fun PluginManagementSpec.writeUsedRepositories(settings: Settings) {
    writeUsedRepositories(
        repositories = repositories,
        file = settings.rootDir.resolve(getUsedMavenRepositoriesFilePath(Type.PluginManagement))
    )
}

/**
 * Writes repositories used in buildSrc to a file so it can be used later in [readExtraUsedRepositories].
 */
internal fun Project.writeUsedRepositories() {
    require(isBuildSrc)
    writeUsedRepositories(
        repositories = repositories,
        file = rootDir.resolve(getUsedMavenRepositoriesFilePath(Type.Project))
    )
}

private fun writeUsedRepositories(repositories: RepositoryHandler, file: File) {
    if (file.exists().not()) {
        file.parentFile.mkdirs()
        file.createNewFile()
    }
    file.bufferedWriter().use { writer ->
        repositories.forEach { repo ->
            if (repo is MavenArtifactRepository) writer.appendln(repo.url.toString())
        }
    }
}

private enum class Type(val qualifier: String) {
    PluginManagement("_plugins"),
    Project("")
}

private fun getUsedMavenRepositoriesFileName(type: Type): String {
    return "refreshVersions_used_repositories${type.qualifier}_maven.txt"
}

private fun getUsedMavenRepositoriesFilePath(type: Type): String {
    return "build/${getUsedMavenRepositoriesFileName(type)}"
}
