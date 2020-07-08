package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.MavenDependencyVersionsFetcher
import de.fayard.refreshVersions.core.internal.VersionCandidatesResultMode
import de.fayard.refreshVersions.core.internal.getVersionCandidates
import de.fayard.refreshVersions.core.testutils.disabledBecauseIsAnExperiment
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class MavenDependencyVersionsFetchingTest {

    @Test
    @Disabled(disabledBecauseIsAnExperiment)
    fun testKotlin() {
        runBlocking {
            val versions = getVersionCandidates(
                moduleId = ModuleId(
                    group = "org.jetbrains.kotlin",
                    name = "kotlin-stdlib"
                ),
                currentVersion = Version("1.3.50"),
                repoUrls = listOf(
                    "https://dl.bintray.com/kotlin/kotlin-eap/",
                    "https://jcenter.bintray.com/"
                )
            ).joinToString(separator = "\n") { it.value }
            println(versions)
        }
    }

    private suspend fun getVersionCandidates(
        moduleId: ModuleId,
        currentVersion: Version,
        repoUrls: List<String>,
        resultMode: VersionCandidatesResultMode = VersionCandidatesResultMode(
            filterMode = VersionCandidatesResultMode.FilterMode.LatestByStabilityLevel,
            sortingMode = VersionCandidatesResultMode.SortingMode.ByVersion
        )
    ): List<Version> = repoUrls.map {
        MavenDependencyVersionsFetcher(
            httpClient = defaultHttpClient,
            moduleId = moduleId,
            repoUrl = it,
            repoAuthorization = null
        )
    }.getVersionCandidates(
        currentVersion = currentVersion,
        resultMode = resultMode
    )

    private val defaultHttpClient by lazy { createTestHttpClient() }

    private fun createTestHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }
}
