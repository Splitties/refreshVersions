package de.fayard

import de.fayard.versions.RefreshVersionsPropertiesTask
import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.extensions.isRootProject
import de.fayard.versions.extensions.registerOrCreate
import de.fayard.versions.internal.setupVersionPlaceholdersResolving
import de.fayard.versions.internal.writeUsedDependencies
import de.fayard.versions.internal.writeUsedRepositories
import org.gradle.api.Plugin
import org.gradle.api.Project

open class RefreshVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project.isRootProject) { "ERROR: de.fayard.refreshVersions should not be applied manually" }
        project.tasks.registerOrCreate<RefreshVersionsPropertiesTask>(name = "refreshVersions") {
            group = "Help"
            description = "Search for new dependencies versions and update versions.properties"
        }
        if (project.isBuildSrc) project.afterEvaluate {
            writeUsedDependencies()
            writeUsedRepositories()
        }
    }
}
