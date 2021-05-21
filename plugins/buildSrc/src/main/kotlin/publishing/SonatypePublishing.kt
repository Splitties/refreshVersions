@file:Suppress("PackageDirectoryMismatch")

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import java.net.URI


fun PublishingExtension.mavenCentralStagingPublishing(
    project: Project,
    sonatypeUsername: String? = project.propertyOrEnvOrNull("sonatype_username"),
    sonatypePassword: String? = project.propertyOrEnvOrNull("sonatype_password"),
    repositoryId: String?
) {
    repositories {
        maven {
            name = "MavenCentralStaging"
            url = when (repositoryId) {
                null -> URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                else -> URI("https://s01.oss.sonatype.org/service/local/staging/deployByRepositoryId/$repositoryId/")
            }
            credentials {
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
    }
}

fun PublishingExtension.sonatypeSnapshotsPublishing(
    project: Project,
    sonatypeUsername: String? = project.propertyOrEnvOrNull("sonatype_username"),
    sonatypePassword: String? = project.propertyOrEnvOrNull("sonatype_password")
) {
    repositories {
        maven {
            name = "SonatypeSnapshots"
            url = URI("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
    }
}
