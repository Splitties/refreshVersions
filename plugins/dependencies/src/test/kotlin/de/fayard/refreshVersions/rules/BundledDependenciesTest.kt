package de.fayard.refreshVersions.rules

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import testutils.getVersionCandidates
import testutils.isInCi

class BundledDependenciesTest {

    @Test
    @Disabled(
        "This test needs to be made incremental, " +
            "so it only checks new dependency notations, " +
            "and doesn't give a 1 minute penalty."
    )
    fun `test bundled dependencies exist in standard repositories`() {

        if (isInCi()) return
        /* Because running this test (almost always) fails on GitHub Actions with this error:
           java.net.SocketTimeoutException at JvmOkio.kt:143
               Caused by: javax.net.ssl.SSLException at Alert.java:127
                   Caused by: java.net.SocketException at SocketInputStream.java:183

        https://github.com/jmfayard/refreshVersions/runs/872495471?check_suite_focus=true
        https://gradle.com/s/l47xwdefpipo2
        */

        runBlocking {
            // "standard repositories" are mavenCentral, jcenter and google
            val reposUrls = listOf(
                "https://repo.maven.apache.org/maven2/",
                "https://dl.google.com/dl/android/maven2/",
                "https://jcenter.bintray.com/",
                "https://plugins.gradle.org/m2/"
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
