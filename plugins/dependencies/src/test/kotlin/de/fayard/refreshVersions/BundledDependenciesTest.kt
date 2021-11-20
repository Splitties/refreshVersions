package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.AbstractDependencyGroup
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.core.internal.DependencyMapping
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import dependencies.ALL_DEPENDENCIES_NOTATIONS
import io.kotest.assertions.fail
import io.kotest.assertions.withClue
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.haveSize
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Test
import testutils.getVersionCandidates
import testutils.isInCi
import testutils.parseRemovedDependencyNotations

class BundledDependenciesTest {

    @Test
    fun `Generate rule files for dependency groups with a rawRule`() {
        ALL_DEPENDENCIES_NOTATIONS // Ensure all objects are initialized.
        val rulesDir = mainResources.resolve("refreshVersions-rules")
        val file = rulesDir.resolve("dependency-groups-alias-rules.txt")
        val content = AbstractDependencyGroup.ALL_RULES
            .sorted()
            .distinct()
            .joinToString(separator = "\n\n")
        if (file.readText() != content) file.writeText(content)
    }

    @Test
    fun `The artifactVersionKeyRules property should contain all rules`() {
        val rulesDir = mainResources.resolve("refreshVersions-rules")
        val dirFileNames = rulesDir.listFiles { file -> file.extension == "txt" }!!.map { it.name }.toSet()
        RefreshVersionsPlugin.artifactVersionKeyRulesFileNames shouldContainAll dirFileNames
    }

    @Test
    fun `Removed dependency notations should be tracked`() {
        val validatedMappingFile = testResources.resolve("dependencies-mapping-validated.txt")

        val existingMapping = validatedMappingFile.readLines().mapNotNull { DependencyMapping.fromLine(it) }.toSet()
        val receivedMapping = getArtifactNameToConstantMapping().toSet()

        if (receivedMapping == existingMapping) return
        if (isInCi()) withClue("Run the tests locally and commit the changes to fix this") {
            fail("There are dependency mapping updates that haven't been committed!")
        }


        val removals = existingMapping - receivedMapping
        if (removals.isNotEmpty()) {
            val removalsRevisionsHistoryFile = mainResources.resolve("removals-revisions-history.md")
            val removalsRevisionsHistory = removalsRevisionsHistoryFile.readText()
            val hasWipHeading = removalsRevisionsHistory.lineSequence().any { it == "## [WIP]" }
            val extraText = buildString {
                run {
                    val lineBreaks = when {
                        removalsRevisionsHistory.endsWith("\n\n") -> ""
                        removalsRevisionsHistory.endsWith('\n') -> "\n"
                        else -> "\n\n"
                    }
                    append(lineBreaks)
                }
                if (hasWipHeading.not()) {
                    appendLine("## [WIP]")
                    appendLine()
                }
                val removedEntriesText = removals.joinToString(
                    separator = "\n\n",
                    postfix = "\n"
                ) { removedMapping ->
                    val group = removedMapping.moduleId.group
                    val name = removedMapping.moduleId.name
                    """
                        ~~${removedMapping.constantName}~~
                        **Remove this line when comments are complete.**
                        // TODO: Put guidance comment lines here.
                        // We recommend prefixing them with "FIXME:" if the user should take further action,
                        // such as using new maven coordinates, or stop depending on the deprecated library.
                        moved:[<insert replacement group:name here, or remove this line>]
                        id:[$group:$name]
                    """.trimIndent()
                }
                append(removedEntriesText)
            }
            removalsRevisionsHistoryFile.appendText(extraText)
        }

        validatedMappingFile.writeText(receivedMapping.joinToString(separator = "\n", postfix = "\n"))
    }

    @Test
    fun `removals-revisions-history should parse correctly`() {
        parseRemovedDependencyNotations(mainResources.resolve("removals-revisions-history.md"))
    }

    @Test
    fun `Version keys should be up to date`() {
        val rulesDir = mainResources.resolve("refreshVersions-rules")
        val versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })

        val existingKeys = testResources.resolve("dependencies-versions-key-validated.txt")
        val receivedKeys = testResources.resolve("dependencies-versions-key-received.txt")
        val removedKeys = mainResources.resolve("removed-dependencies-versions-keys.txt")

        val existingMapping = existingKeys.readLines().mapNotNull { DependencyMapping.fromLine(it) }.toSet()
        val receivedMapping = getArtifactNameToConstantMapping().map {
            val key = versionKeyReader.readVersionKey(it.group, it.artifact) ?: "NO-RULE"
            it.copy(constantName = "version.$key")
        }.toSet()
        receivedKeys.writeText(receivedMapping.joinToString(separator = "\n", postfix = "\n"))

        val breakingChanges = existingMapping - receivedMapping
        if (isInCi()) {
            withClue("diff -u ${existingKeys.absolutePath}  ${receivedKeys.absolutePath}") {
                breakingChanges should haveSize(0)
            }
            withClue("Changes to $existingKeys must be committed, but I got new entries") {
                (receivedMapping - existingMapping) should haveSize(0)
            }
        } else if (breakingChanges.isNotEmpty()) {
            removedKeys.appendText(
                text = breakingChanges.joinToString(separator = "\n", postfix = "\n")
            )
        }
        receivedKeys.copyTo(existingKeys, overwrite = true)
        receivedKeys.deleteOnExit()
    }

    @Test
    fun `Dependencies should not be in the 'dependencies' package`() {
        getArtifactNameToConstantMapping().forEach {
            if (it.constantName.startsWith("dependencies.")) {
                fail("This dependency should not be in the dependencies package: ${it.constantName}")
            }
            it.constantName.startsWith("dependencies.").shouldBeFalse()
        }
    }

    @Test
    fun `test bundled dependencies exist in standard repositories`() {

        val validatedDependencyMappingFile = testResources.resolve("bundled-dependencies-validated.txt")
        val validatedDependencyMapping = validatedDependencyMappingFile.readLines()
            .filter { it.isNotBlank() }
            .toSet()

        // "standard repositories" are mavenCentral and google
        val reposUrls = listOf(
            "https://repo.maven.apache.org/maven2/",
            "https://dl.google.com/dl/android/maven2/",
            "https://plugins.gradle.org/m2/"
        )

        val newValidatedMappings = runBlocking {

            getArtifactNameToConstantMapping()
                .filter { dependencyMapping ->
                    "${dependencyMapping.group}:${dependencyMapping.artifact}" !in validatedDependencyMapping
                }.map { dependencyMapping ->
                    ModuleId.Maven(dependencyMapping.group, dependencyMapping.artifact)
                }
                .distinct()
                .onEach { mavenModuleId ->
                    launch {
                        getVersionCandidates(
                            httpClient = defaultHttpClient,
                            mavenModuleId = mavenModuleId,
                            repoUrls = reposUrls,
                            currentVersion = Version("")
                        )
                    }
                }
        }

        when {
            newValidatedMappings.isEmpty() -> return
            isInCi() -> withClue(
                "Unit tests must be run and changes to bundled-dependencies-validated.txt must be committed, " +
                    "but that wasn't the case for those dependency notations."
            ) {
                newValidatedMappings shouldBe emptyList()
            }
            else -> {
                val mappings = getArtifactNameToConstantMapping().map {
                    "${it.group}:${it.artifact}"
                }.distinct().sorted().joinToString(separator = "\n")
                validatedDependencyMappingFile.writeText(mappings)
            }
        }
    }

    private val defaultHttpClient by lazy { createTestHttpClient() }

    private fun createTestHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(logger = object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    println(message)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    }
}
