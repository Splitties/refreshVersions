@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.withType

internal object Publishing {
    const val gitUrl = "https://github.com/Splitties/refreshVersions.git"
    const val siteUrl = "https://splitties.github.io/refreshVersions/"
    const val repoUrl = "https://github.com/Splitties/refreshVersions"
    const val libraryDesc = "Life is too short to Google for dependencies and versions."
}

internal fun PublishingExtension.setupAllPublications(project: Project) {
    publications.withType<MavenPublication>().configureEach { setupPom() }
    if (project.isSnapshot) {
        sonatypeSnapshotsPublishing(project = project)
    }
    project.registerPublishingTask()
    project.tasks.named("publishPlugins").configure {
        doFirst {
            check(project.isDevVersion.not()) {
                "Dev versions shall not be published to the Gradle plugin portal"
            }
            check(project.isSnapshot.not()) {
                "Snapshot versions shall not be published to the Gradle plugin portal"
            }
        }
    }
}

private fun Project.registerPublishingTask() {
    require(project != rootProject)

    val publishTaskName = when {
        isSnapshot -> "publishAllPublicationsToSonatypeSnapshotsRepository"
        else -> null // TODO: Replace with a dev repo making up for bintray?
    }
    val publishTask = publishTaskName?.let { tasks.named(it) }

    publishTask?.configure { dependsOn("validatePlugins") }

    tasks.register("publishToAppropriateRepo") {
        group = "publishing"
        description = "Publishes the Gradle plugin to the appropriate repository, depending on the version."
        publishTask?.let { dependsOn(it) }
        if (isSnapshot.not() && isDevVersion.not()) dependsOn("publishPlugins")
    }
}

private val Project.isDevVersion get() = version.let { it is String && it.contains("-dev-") }
private val Project.isSnapshot get() = version.let { it is String && it.endsWith("-SNAPSHOT") }

private fun MavenPublication.setupPom() = pom {
    name = "refreshVersions"
    description = Publishing.libraryDesc
    url = Publishing.siteUrl
    licenses {
        license {
            name = "MIT License"
            url = "https://opensource.org/licenses/MIT"
        }
    }
    developers {
        developer {
            id = "jmfayard"
            name = "Jean-Michel Fayard"
            email = "jmfayard@gmail.com"
        }
        developer {
            id = "louiscad"
            name = "Louis CAD"
            email = "louis.cognault@gmail.com"
        }
    }
    scm {
        connection = Publishing.gitUrl
        developerConnection = Publishing.gitUrl
        url = Publishing.repoUrl
    }
}
