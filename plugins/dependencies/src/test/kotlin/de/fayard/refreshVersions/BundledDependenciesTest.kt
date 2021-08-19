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
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Test
import testutils.getVersionCandidates
import testutils.isInCi
import java.io.File

class BundledDependenciesTest {

    @Test
    fun `Generate rule files for dependency groups with a rawRule`() {
        ALL_DEPENDENCIES_NOTATIONS
        val rulesDir = File(".").absoluteFile.resolve("src/main/resources/refreshVersions-rules")
        val file = rulesDir.resolve("dependency-groups-alias-rules.txt")
        val content = AbstractDependencyGroup.ALL_RULES
            .sorted()
            .distinct()
            .joinToString(separator = "\n\n")
        file.writeText(content)
    }

    @Test
    fun `We should never remove a property`() {
        val existingProperties = testResources.resolve("dependencies-mapping-validated.txt")
        val receivedProperties = testResources.resolve("dependencies-mapping-received.txt")

        val existingMapping = existingProperties.readLines().mapNotNull { DependencyMapping.fromLine(it) }
        val receivedMapping = getArtifactNameToConstantMapping()
        receivedProperties.writeText(receivedMapping.joinToString(separator = "\n", postfix = "\n"))

        val breakingChanges = existingMapping - receivedMapping
        withClue("diff -u ${existingProperties.absolutePath}  ${receivedProperties.absolutePath}") {
            breakingChanges should haveSize(0)
        }
        receivedProperties.copyTo(existingProperties, overwrite = true)
        receivedProperties.deleteOnExit()
    }

    @Test
    fun `We should not change version keys`() {
        val mainResources: File = File(".").absoluteFile.resolve("src/main/resources")
        val rulesDir = mainResources.resolve("refreshVersions-rules")
        val versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })

        val existingKeys = testResources.resolve("dependencies-versions-key-validated.txt")
        val receivedKeys = testResources.resolve("dependencies-versions-key-received.txt")

        val existingMapping = existingKeys.readLines().mapNotNull { DependencyMapping.fromLine(it) }
        val receivedMapping = getArtifactNameToConstantMapping().map {
            val key = versionKeyReader.readVersionKey(it.group, it.artifact) ?: "NO-RULE"
            it.copy(constantName = "version.$key")
        }.distinct()
        receivedKeys.writeText(receivedMapping.joinToString(separator = "\n", postfix = "\n"))

        val breakingChanges = existingMapping - receivedMapping
        withClue("diff -u ${existingKeys.absolutePath}  ${receivedKeys.absolutePath}") {
            breakingChanges should haveSize(0)
        }
        withClue("Changes to $existingKeys must be committed, but I got new entries") {
            if (isInCi()) (receivedMapping - existingMapping) should haveSize(0)
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
                    dependencyMapping.group to dependencyMapping.artifact
                }
                .distinct()
                .onEach { (group, name) ->
                    launch {
                        getVersionCandidates(
                            httpClient = defaultHttpClient,
                            moduleId = ModuleId(group, name),
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
