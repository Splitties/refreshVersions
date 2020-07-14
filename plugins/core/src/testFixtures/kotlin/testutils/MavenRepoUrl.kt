package testutils

@Suppress("DataClassPrivateConstructor")
data class MavenRepoUrl private constructor(val url: String) {

    fun metadataUrlForArtifact(group: String, name: String): String =
        "$url${group.replace('.', '/')}/$name/maven-metadata.xml"

    companion object {
        operator fun invoke(url: String): MavenRepoUrl {
            return MavenRepoUrl(
                url = if (url.endsWith('/')) url else "$url/"
            )
        }
    }
}
