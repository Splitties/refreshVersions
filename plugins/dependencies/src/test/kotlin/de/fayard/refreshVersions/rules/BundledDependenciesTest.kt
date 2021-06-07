package de.fayard.refreshVersions.rules

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.StabilityLevel
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import de.fayard.testResources
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Test
import testutils.getVersionCandidates

class BundledDependenciesTest {

    @Test
    fun `test bundled dependencies exist in standard repositories`() {

        val validatedDependencyMappingFile = testResources.resolve("bundled-dependencies-validated.txt")
        val validatedDependencyMapping = validatedDependencyMappingFile.readLines()
            .map { it.substringBeforeLast(':') }
            .filter { it.isNotBlank() }
            .toSet()

        // "standard repositories" are mavenCentral and google
        val reposUrls = listOf(
            "https://repo.maven.apache.org/maven2/",
            "https://dl.google.com/dl/android/maven2/",
            "https://plugins.gradle.org/m2/"
            //"https://jcenter.bintray.com/",
        )

        val mappings = runBlocking {

            getArtifactNameToConstantMapping()
                .filter { dependencyMapping ->
                    "${dependencyMapping.group}:${dependencyMapping.artifact}" !in validatedDependencyMapping
                }
                .map { dependencyMapping ->
                    async {
                        getVersionCandidates(
                            httpClient = defaultHttpClient,
                            moduleId = ModuleId(
                                group = dependencyMapping.group,
                                name = dependencyMapping.artifact
                            ),
                            repoUrls = reposUrls,
                            currentVersion = Version("")
                        ).run {
                            val version = lastOrNull { it.stabilityLevel == StabilityLevel.Stable } ?: lastOrNull()
                            dependencyMapping to version
                        }
                    }
                }.awaitAll()
        }

        val newValidatesMappings = mappings
            .filter { it.second != null }
            .joinToString(prefix = "\n", separator = "\n") { (mapping, version) ->
                "${mapping.group}:${mapping.artifact}:${version!!.value}"
            }
        validatedDependencyMappingFile.appendText(newValidatesMappings)

        val invalidMappings = mappings.filter { it.second == null }.map { "${it.first.group}:${it.first.artifact}" }
        withClue("We didn't find those dependency notations in the standard maven repositories") {
            invalidMappings shouldBe emptyList()
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
