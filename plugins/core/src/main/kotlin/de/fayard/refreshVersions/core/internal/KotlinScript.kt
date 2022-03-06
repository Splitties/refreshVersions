package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.KotlinScriptKind.*
import okhttp3.OkHttpClient
import java.io.File

internal class KotlinScript(
    val fileContent: String,
    val dependenciesUpdates: List<DependencyWithVersionCandidates>
) {

    val lines: List<KotlinScriptLine> = fileContent
        .lines()
        .map { KotlinScriptLine(it) }

    fun updateNewVersions(actual: File) {
        if (fileContent.isBlank()) return

        val newLines = mutableListOf<KotlinScriptLine>()
        for (line in lines) {
            when (line.kind) {
                Dependency -> newLines += showUpdates(line)
                Repository -> newLines += line
                Ignore -> newLines += line
                Delete ->  { }
            }
        }
        actual.writeText(newLines.toText())
    }

    private fun showUpdates(line: KotlinScriptLine): List<KotlinScriptLine> {
        val moduleId = line.moduleId() ?: error("Unexpected line $line in showUpdates()")
        val versions = dependenciesUpdates
            .firstOrNull { it.moduleId == moduleId }
            ?.versionsCandidates
            ?: emptyList()
        val newLines = versions.map {
            KotlinScriptLine("////${line.availableSpace()}# available:${it.value}\")")
        }
        return listOf(line) + newLines
    }

    fun cleanupComments(actual: File) {
        if (fileContent.isBlank()) return

        val newContent = lines
            .filter { it.kind != Delete }
            .toText()

        actual.writeText(newContent)
    }

    fun List<KotlinScriptLine>.toText(): String =
        joinToString("\n") { it.line }

    fun repositories() = (lines.mapNotNull { it.repository() } + MAVEN_CENTRAL).distinct()

    fun moduleIds() = lines.mapNotNull { it.moduleId() }

    fun mavenDependencyVersionsFetcher(okHttpClient: OkHttpClient): List<MavenDependencyVersionsFetcher> {
        return moduleIds().flatMap { moduleId ->
            repositories().map { repoUrl ->
                if (repoUrl.startsWith("http")) {
                    MavenDependencyVersionsFetcherHttp(okHttpClient, moduleId, repoUrl, null)
                } else {
                    MavenDependencyVersionsFetcherFile(moduleId, repoUrl)
                }
            }
        }
    }

    companion object {
        const val MAVEN_CENTRAL = "https://repo.maven.apache.org/maven2/"

        internal fun findKotlinScripts(fromDir: File): List<File> {
            require(fromDir.isDirectory) { "Expected a directory, got ${fromDir.canonicalPath}" }
            return fromDir.walkBottomUp()
                .onEnter { dir -> dir.name !in listOf("resources", "build") }
                .filter {
                    it.extension == "kts" && it.name.contains("gradle").not()
                }
                .filter { it.canRead() }
                .toList()
        }
    }
}

