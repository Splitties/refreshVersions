plugins {
    id("com.gradle.plugin-publish")
    signing
}

signing {
    isRequired = false
    useInMemoryPgpKeys(
        propertyOrEnvOrNull("GPG_key_id"),
        propertyOrEnvOrNull("GPG_private_key") ?: return@signing,
        propertyOrEnv("GPG_private_password")
    )
    sign(publishing.publications)
}

gradlePlugin {
    website = Publishing.siteUrl
    vcsUrl = Publishing.repoUrl
}

publishing {
    setupAllPublications(project)
}
