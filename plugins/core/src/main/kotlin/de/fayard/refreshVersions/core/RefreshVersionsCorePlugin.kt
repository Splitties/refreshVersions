package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.isBuildSrc
import de.fayard.refreshVersions.core.extensions.isRootProject
import de.fayard.refreshVersions.core.extensions.registerOrCreate
import de.fayard.refreshVersions.core.internal.writeUsedDependencies
import de.fayard.refreshVersions.core.internal.writeUsedRepositories
import org.gradle.api.Plugin
import org.gradle.api.Project

open class RefreshVersionsCorePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project.isRootProject) { "ERROR: de.fayard.refreshVersions.core should not be applied manually" }
        project.tasks.registerOrCreate<RefreshVersionsTask>(name = "refreshVersions") {
            group = "Help"
            description = "Search for new dependencies versions and update versions.properties"
        }
        if (project.isBuildSrc) project.afterEvaluate {
            writeUsedDependencies()
            writeUsedRepositories()
        }
    }
}
