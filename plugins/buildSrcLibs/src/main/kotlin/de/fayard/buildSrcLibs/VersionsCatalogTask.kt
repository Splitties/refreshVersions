package de.fayard.buildSrcLibs

import com.squareup.kotlinpoet.FileSpec
import de.fayard.buildSrcLibs.internal.Library
import de.fayard.buildSrcLibs.internal.PluginConfig
import de.fayard.buildSrcLibs.internal.checkModeAndNames
import de.fayard.buildSrcLibs.internal.kotlinpoet
import de.fayard.refreshVersions.core.internal.OutputFile
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.tasks.TaskAction
import org.gradle.util.GradleVersion

@Suppress("UnstableApiUsage")
open class VersionsCatalogTask : DefaultTask() {

    @TaskAction
    fun taskActionEnableSupport() {
        if (GradleVersion.current() < GradleVersion.version("7.0")) {
            error("""
                |Gradle versions catalogs are only supported in Gradle 7+
                |Upgrade first with this command
                |     ./gradlew wrapper --gradle-version 7.0
            """.trimMargin())
        }
        OutputFile.checkWhichFilesExist(project.rootDir)
        val outputDir = project.file(OutputFile.OUTPUT_DIR.path)
        // Enable Gradle's version catalog support
        // https://docs.gradle.org/current/userguide/platforms.html
        val file = OutputFile.SETTINGS
        if (file.existed.not()) return
        val settingsText = file.readText(project)
        val alreadyConfigured = settingsText.lines().any { it.containsVersionsCatalogDeclaration() }
        if (!alreadyConfigured) {
            val newText = (""""
                |${settingsText}
                |enableFeaturePreview("VERSION_CATALOGS")
                |""".trimMargin())
            file.writeText(newText, project)
            file.logFileWasModified()
        }

    }


    @TaskAction
    fun taskUpdateLibsKt() {

    }

    companion object {
        fun String.containsVersionsCatalogDeclaration() : Boolean {
            return this.substringBefore("//").contains("enableFeaturePreview.*VERSION_CATALOGS".toRegex())
        }
    }
}

