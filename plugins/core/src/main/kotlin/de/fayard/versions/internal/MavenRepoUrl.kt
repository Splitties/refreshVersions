package de.fayard.versions.internal

internal class MavenRepoUrl(url: String) {

    val url: String = if (url.endsWith('/')) url else "$url/"

    fun metadataUrlForArtifact(group: String, name: String): String =
        "$url${group.replace('.', '/')}/$name/maven-metadata.xml"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MavenRepoUrl) return false

        if (url != other.url) return false

        return true
    }

    override fun hashCode() = url.hashCode()
}
