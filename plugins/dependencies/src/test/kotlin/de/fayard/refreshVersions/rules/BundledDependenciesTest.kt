package de.fayard.refreshVersions.rules

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Test
import testutils.getVersionCandidates

class BundledDependenciesTest {

    @Test
    fun `test bundled dependencies exist in standard repositories`(): Unit = runBlocking {
        // "standard repositories" are mavenCentral, jcenter and google
        val reposUrls = listOf(
            "https://repo.maven.apache.org/maven2/",
            "https://dl.google.com/dl/android/maven2/",
            "https://jcenter.bintray.com/",
            "https://plugins.gradle.org/m2/",
            "https://dl.bintray.com/louiscad/splitties-dev/"
        )
        getArtifactNameToConstantMapping().map { dependencyMapping ->
            async {
                getVersionCandidates(
                    httpClient = defaultHttpClient,
                    moduleId = ModuleId(
                        group = dependencyMapping.group,
                        name = dependencyMapping.artifact
                    ),
                    repoUrls = reposUrls,
                    currentVersion = Version("")
                ).also { check(it.isNotEmpty()) }
            }
        }.awaitAll().also { check(it.isNotEmpty()) }
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
