package de.fayard

import de.fayard.internal.AvailableDependency
import de.fayard.internal.Dependency
import de.fayard.internal.PluginConfig.AVAILABLE_DEPENDENCIES_FILE
import de.fayard.internal.RefreshVersionsExtensionImpl
import de.fayard.internal.VersionMode
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
open class SearchAvailableUpdatesTask : DefaultTask() {

    init {
        group = "help"
        description = "Search for availlable updates for your dependencies"
    }

    @OutputFile
    val file = project.file(AVAILABLE_DEPENDENCIES_FILE)

    @TaskAction
    fun taskActionGradleProperties() {
        val extension: RefreshVersionsExtensionImpl = extension()
        // TODO: use filter: ComponentFilter from the extension

        // TODO: use here
        //  https://github.com/LouisCAD/Splitties/compare/develop...dependencies-updates-poc
        val dependencies = EXPECTED_FILE_FORMAT

        file.writeText(dependencies)
        // should not crash
        dependencies.lines().forEach { line ->
            println("$line => ${line.asDependency()}")
        }
    }

    private fun extension(): RefreshVersionsExtensionImpl =
        (project.extensions.getByType<RefreshVersionsExtension>() as RefreshVersionsExtensionImpl).defensiveCopy()

}

// all dependencies of the whole project must be there even if they cannot be resolved or have no available updates
val EXPECTED_FILE_FORMAT = """
# specification for the new format
    
gradle.running=5.6.2          
gradle.stable=5.6.3
gradle.rc=6.0-rc-1
androidx.appcompat:appcompat:1.1.0
de.fayard.refreshVersions:de.fayard.refreshVersions.gradle.plugin:0.8.0
org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.50

# dependencies should be there even if they cannot be resolved
com.nhaarman.mockito:mockito-kotlin2:2.2.0

# convention to indicate that a newer dependency is available
com.android.tools.build:gradle:3.5.0 // 3.5.1
androidx.constraintlayout:constraintlayout:2.0.0-beta3 // 2.0.0
        """

// TODO: remove the report.json completly
// instead use an outputFormatter https://github.com/ben-manes/gradle-versions-plugin#report-format
// but as good first step, we will transform the lines from the new format in the old one
fun String.asDependency(fqdn: Boolean = false): Dependency? {
    if (startsWith("#") || isBlank() || startsWith("gradle.")) return null
    val available = this.substringAfter("// ", "").trim()
    val rest = this.substringBefore(" // ")
    val (group, name, version) = rest.split(":")
    return Dependency(
        group = group,
        version = version,
        reason = "",
        latest = available,
        projectUrl = "",
        name = name,
        escapedName = name,
        mode = if (fqdn) VersionMode.GROUP_MODULE else VersionMode.MODULE,
        available = if (available.isBlank()) null else AvailableDependency(release = available)
    )
}
