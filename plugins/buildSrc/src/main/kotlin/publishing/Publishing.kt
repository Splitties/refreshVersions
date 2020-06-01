@file:Suppress("PackageDirectoryMismatch")

import extensions.propertyOrEnv
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.withType

object Publishing {
    const val gitUrl = "https://github.com/jmfayard/refreshVersions.git"
    const val siteUrl = "https://github.com/jmfayard/refreshVersions"
    const val libraryDesc = "Life is too short to Google for dependencies and versions."
}

fun PublishingExtension.setupAllPublications(project: Project) {
    val mavenPublications = publications.withType<MavenPublication>()
    mavenPublications.configureEach { setupPom() }
    if (project.isDevVersion) setupDevPublishRepo(project)
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
    tasks.register("publishToAppropriateRepo") {
        group = "publishing"
        description = "Publishes the Gradle plugin to the appropriate repository, depending on the version."
        if (isSnapshot) {
            dependsOn("publishToMavenLocal")
        } else {
            dependsOn("publishAllPublicationsToBintrayRepository")
            if (isDevVersion.not()) dependsOn("publishPlugins")
        }
    }
}

private val Project.isDevVersion get() = version.let { it is String && it.contains("-dev-") }
private val Project.isSnapshot get() = version.let { it is String && it.endsWith("-SNAPSHOT") }

private fun PublishingExtension.setupDevPublishRepo(project: Project) {
    repositories {
        maven {
            name = "bintray"
            val bintrayUsername = "jmfayard"
            val bintrayRepoName = "maven"
            val bintrayPackageName = "de.fayard.refreshVersions"
            setUrl(
                "https://api.bintray.com/maven/" +
                    "$bintrayUsername/$bintrayRepoName/$bintrayPackageName/;" +
                    "publish=1;" +
                    "override=1"
            )
            credentials {
                username = project.propertyOrEnv("bintray_user")
                password = project.propertyOrEnv("bintray_api_key")
            }
        }
    }
}

@Suppress("UnstableApiUsage")
private fun MavenPublication.setupPom() = pom {
    name.set("refreshVersions")
    description.set(Publishing.libraryDesc)
    url.set(Publishing.siteUrl)
    licenses {
        license {
            name.set("MIT License")
            url.set("https://opensource.org/licenses/MIT")
        }
    }
    developers {
        developer {
            id.set("jmfayard")
            name.set("Jean-Michel Fayard")
            email.set("jmfayard@gmail.com")
        }
        developer {
            id.set("louiscad")
            name.set("Louis CAD")
            email.set("louis.cognault@gmail.com")
        }
    }
    scm {
        connection.set(Publishing.gitUrl)
        developerConnection.set(Publishing.gitUrl)
        url.set(Publishing.siteUrl)
    }
}
