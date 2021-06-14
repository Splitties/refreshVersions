@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project
import org.gradle.api.UnknownTaskException
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

object Publishing {
    const val gitUrl = "https://github.com/jmfayard/refreshVersions.git"
    const val siteUrl = "https://github.com/jmfayard/refreshVersions"
    const val libraryDesc = "Life is too short to Google for dependencies and versions."
}

fun PublishingExtension.setupAllPublications(project: Project) {
    val mavenPublications = publications.withType<MavenPublication>()
    mavenPublications.configureEach {
        artifact(project.tasks.emptyJavadocJar())
        setupPom()
    }
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

fun TaskContainer.emptyJavadocJar(): TaskProvider<Jar> {
    val taskName = "javadocJar"
    return try {
        named(name = taskName)
    } catch (e: UnknownTaskException) {
        register(name = taskName) { archiveClassifier by "javadoc" }
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
