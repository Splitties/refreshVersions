package de.fayard.buildSrcLibs

import de.fayard.refreshVersions.core.MissingEntriesTask
import de.fayard.refreshVersions.core.VersionsCatalogTask
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class BuildSrcLibsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register<MissingEntriesTask>(
            name = "refreshVersionsMissingEntries"
        ) {
            group = "refreshVersions"
            description = "Add missing entries to 'versions.properties'"
            outputs.upToDateWhen { false }
        }
        project.tasks.register<BuildSrcLibsTask>(
            name = "buildSrcLibs"
        ) {
            group = "refreshVersions"
            description = "Update buildSrc/src/main/kotlin/Libs.kt"
            outputs.upToDateWhen { false }
            dependsOn("refreshVersionsMissingEntries")
        }
        project.tasks.register<VersionsCatalogTask>(
            name = "refreshVersionsCatalog"
        ) {
            group = "refreshVersions"
            description = "Update gradle/libs.versions.toml"
            outputs.upToDateWhen { false }
            dependsOn("refreshVersionsMissingEntries")
        }
        project.tasks.register<DefaultTask>(
            name = "buildSrcVersions"
        ) {
            group = "refreshVersions"
            description = "Update buildSrc/src/main/kotlin/Libs.kt"
            dependsOn("buildSrcLibs")
        }
    }
}
