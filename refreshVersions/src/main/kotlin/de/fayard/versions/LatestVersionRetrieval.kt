package de.fayard.versions

import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.stabilityLevel
import de.fayard.versions.extensions.versionComparator
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.net.URI

internal class VersionCandidate(val stabilityLevel: StabilityLevel, val version: Version)

internal fun Project.getDependenciesVersionsCandidates(
    extension: RefreshVersionsPropertiesExtension,
    dependency: Dependency,
    resolvedVersion: String?
): List<VersionCandidate> {
    val currentVersion = Version(resolvedVersion ?: "")
    val mavenMetadataUris = getMavenMetadataUrls(dependency)
    return mavenMetadataUris.asSequence().mapNotNull { uri ->
        runCatching {
            uri.toURL().readText()
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

internal fun Project.getLatestDependencyVersionFromRepo(
    extension: RefreshVersionsPropertiesExtension,
    dependency: Dependency,
    resolvedVersion: String?
): String? {
    val mavenMetadataUris = getMavenMetadataUrls(dependency)
    val versions = mavenMetadataUris.asSequence().mapNotNull { uri ->
        runCatching {
            uri.toURL().readText()
        }.getOrNull()?.let { xml ->
            parseVersionsFromMavenMetaData(xml)
        }
    }.flatten().sortedWith(versionComparator).distinct()
    return versions.last().value
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
