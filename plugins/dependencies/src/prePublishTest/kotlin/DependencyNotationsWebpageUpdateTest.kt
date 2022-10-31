@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER") //TODO: Remove when https://youtrack.jetbrains.com/issue/KT-34102 is fixed.

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.opentest4j.TestAbortedException
import testutils.isInCi
import java.io.File

class DependencyNotationsWebpageUpdateTest {

    @Test
    fun `update the list of dependency notations on the website`() {
        if (pluginsVersion.endsWith("-SNAPSHOT")) {
            throw TestAbortedException("Only updated on release.")
        }

        val destination = File(".").absoluteFile.parentFile.parentFile.parentFile.resolve("docs/dependency-notations.md")
        val newText = generateLatestDependencyNotationsPage()
        val oldText = destination.readText()

        if (oldText != newText) {
            if (isInCi()) fail {
                "Changes to the following file must be committed locally before release: ${destination.absolutePath}"
            }

            destination.writeText(newText)
            println("Updated ${destination.canonicalPath}")
        }
    }

    private fun generateLatestDependencyNotationsPage(): String {
        val rulesDir = mainResources.resolve("refreshVersions-rules")
        val versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })
        val markdownDependencies = getArtifactNameToConstantMapping().map {
            val moduleId = it.moduleId
            val versionKey = versionKeyReader.readVersionKey(moduleId.group, moduleId.name) ?: "NO-RULE"
            MarkdownDependency(moduleId, versionKey, it.constantName)
        }

        val groups: List<String> = markdownDependencies
            .map { it.group }
            .distinct()
            .sorted()

        val subgroups: List<String> = markdownDependencies
            .map { it.subgroup }
            .distinct()
            .sorted()

        val dependenciesBySubGroup: Map<String, List<MarkdownDependency>> = markdownDependencies
            .groupBy { it.subgroup }

        val map: Map<String, List<String>> = groups.associateWith { group ->
            // "AndroidX" to mapOf("androidx.tools:tools-core" to "androidx.tools")
            // "AndroidX.test" to mapOf("androidx.test:test" to "androidx.test")
            subgroups.filter { it.startsWith("$group.") || it == group }
        }
        val markdownGroups = map.entries.map { (group, subgroups) ->
            val rows: Map<String, String> = subgroups.associateWith { subgroup ->
                val dependencies = dependenciesBySubGroup[subgroup]!!
                dependencies.joinToString("|        - ", transform = MarkdownDependency::markdown)
            }
            """
            |## [$group.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/$group.kt)
            |
            |${table(rows)}
            |
            |""".trimMargin()
        }
        val header = fileHeader(
            dependenciesCount = markdownDependencies.distinctBy { it.moduleId }.size,
            groupsCount = groups.size,
            subgroupsCount = subgroups.size
        )
        return "${header}\n\n${markdownGroups.joinToString("")}"
    }

    private fun fileHeader(
        dependenciesCount: Int,
        groupsCount: Int,
        subgroupsCount: Int
    ) = """
        |---
        |title: Built-in Dependency Notations
        |---
        |# Built-in Dependency Notations
        |
        |[**refreshVersions**](https://github.com/jmfayard/refreshVersions) provides **$dependenciesCount** Dependency Notations in **$groupsCount** groups and **$subgroupsCount** subgroups
        |
        |**Built-in Dependency Notations** are maven coordinates of popular libraries,
        |discoverable as for example `KotlinX.coroutines.core` in IntelliJ IDEA,
        |who will be configured in the `versions.properties` file with the latest available version
        |after the first Gradle sync.
        |They drastically cut the time it takes to add popular libraries to your Gradle build.
        |
        |[**See: Adding a Dependency Notation**](add-dependencies.md)
        |
        |---
        |
        |Below is the list of all available dependency notations.
        |
        |Use the table of contents to jump to the group you are interested in.
        |
        |Hover 🐁 on a dependency notation to see its `Triple(KotlinName, MavenCoordinate, VersionKey)`.
        |""".trimMargin()

    private fun table(rows: Map<String, String>): String {
        val rowsText = rows.entries.joinToString("\n") { (title, content) ->
            """|    <tr><td><b>$title</b></td><td>$content
               |    </td></tr>
            """
        }
        return """
                |<table style="width: 100%; table-layout:fixed;">
                |    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                $rowsText
                |</table>
            """
    }

    // todo: rename
    private data class MarkdownDependency(
        val moduleId: ModuleId.Maven,
        val versionKey: String,
        val constantName: String,
    ) {
        val group = constantName.substringBefore(".")
        val subgroup = constantName.substringBeforeLast(".")

        fun markdown(): String {
            val mavenCoordinates = "${moduleId.group}:${moduleId.name}:_"
            val lineBreak = "&#10;" // Tested in Firefox, Chrome, and Safari.
            return """
                |        <span
                |            title="$constantName$lineBreak$mavenCoordinates${lineBreak}version.$versionKey"
                |            style="text-decoration: underline;">
                |            ${constantName.substringAfterLast(".")}
                |        </span>
            """
        }
    }
}
