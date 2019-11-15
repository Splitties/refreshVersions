package de.fayard.versions

import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.stabilityLevel
import de.fayard.versions.extensions.versionComparator
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.net.URI
import java.net.URL

internal class VersionCandidate(val stabilityLevel: StabilityLevel, val version: Version)

internal fun Project.getDependencyVersionsCandidates(
    extension: RefreshVersionsPropertiesExtension,
    repositories: List<MavenRepoUrl>,
    group: String,
    name: String,
    resolvedVersion: String?
): List<VersionCandidate> {
    val currentVersion = Version(resolvedVersion ?: "")
    return repositories.asSequence().map {
        URL(it.metadataUrlForArtifact(group = group, name = name))
    }.mapNotNull { url ->
        runCatching {
            url.readText()
        }.getOrNull()?.let { xml ->
            parseVersionsFromMavenMetaData(xml)
        }
    }.flatten()
        .sortedWith(versionComparator.reversed())
        .distinct()
        .takeWhile { candidate -> candidate > currentVersion }
        .fold<Version, List<VersionCandidate>>(emptyList()) { acc, candidateVersion ->
            val previousStabilityLevel = acc.lastOrNull()?.stabilityLevel
                ?: return@fold acc + VersionCandidate(candidateVersion.stabilityLevel(), candidateVersion)
            if (candidateVersion.stabilityLevel() isMoreStableThan previousStabilityLevel) {
                acc + VersionCandidate(candidateVersion.stabilityLevel(), candidateVersion)
            } else acc
        }.asReversed()
}

private operator fun Version.compareTo(currentVersion: Version): Int {
    return versionComparator.compare(this, currentVersion)
}

private fun parseVersionsFromMavenMetaData(xml: String): List<Version> {
    return xml.substringAfter("<versions>").substringBefore("</versions>")
        .split("<version>", "</version>")
        .mapNotNull { if (it.isBlank()) null else Version(it.trim()) }
}

private fun Project.getMavenMetadataUrls(dependency: Dependency): List<URI> {
    val group = dependency.group ?: return emptyList()
    val urlEnd = "${group.replace('.', '/')}/${dependency.name}/maven-metadata.xml"
    return repositories.mapNotNull { (it as? MavenArtifactRepository)?.url?.resolve(urlEnd) }.let {
        if (dependency.isGradlePlugin) listOf(URI("https://plugins.gradle.org/m2/$urlEnd")) + it else it
    }
}
