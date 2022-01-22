package de.fayard.refreshVersions.core.internal.npm

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.okhttp.await
import de.fayard.refreshVersions.core.internal.xor.Xor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.time.Instant

internal class NpmDependencyVersionsFetcherHttp(
    private val httpClient: OkHttpClient,
    moduleId: ModuleId.Npm,
    val repoUrl: String = "https://registry.npmjs.org/"
) : DependencyVersionsFetcher(
    moduleId = moduleId,
    repoUrlOrKey = repoUrl
) {
    init {
        require(repoUrl.endsWith('/'))
    }

    private val request = Request.Builder().apply {
        val metadataUrlForArtifact = moduleId.let { (group, name) ->
            if (group == null || group == "npm") {
                "$repoUrl$name"
            } else {
                "$repoUrl@$group/$name"
            }
        }
        url(metadataUrlForArtifact)
    }.build()

    override suspend fun attemptGettingAvailableVersions(versionFilter: ((Version) -> Boolean)?): Result {

        val metadata: NpmMetadata = when (val result = attemptGettingNpmMetadata()) {
            is Xor.First -> result.value ?: return Result.NotFound
            is Xor.Second -> return failure(result.value)
        }

        val allVersions = runCatching {
            parseVersionsFromNpmMetaData(metadata)
        }.getOrElse { return failure(FailureCause.ParsingIssue(it)) }
        return Result.Success(
            lastUpdateTimestampMillis = parseLastUpdatedFromNpmMetaData(metadata),
            availableVersions = if (versionFilter == null) {
                allVersions
            } else {
                allVersions.filter(versionFilter)
            }
        )
    }

    private suspend fun attemptGettingNpmMetadata(): Xor<NpmMetadata?, FailureCause> = runCatching {
        httpClient.newCall(request).await().use { response ->
            if (response.isSuccessful) {
                val result = response.use {
                    val jsonAdapter = moshi.adapter(NpmMetadata::class.java)
                    jsonAdapter.fromJson(it.body!!.source())
                }
                Xor.First(result)
            } else when (response.code) {
                404 -> Xor.First(null) // Normal not found result
                401 -> Xor.First(null) // Returned by some repositories that have optional authentication
                else -> {
                    val failure = FailureCause.CommunicationIssue.HttpResponse(
                        statusCode = response.code,
                        exception = HttpException(Response.error<Any?>(response.code, response.body!!))
                    )
                    Xor.Second(failure)
                }
            }
        }
    }.getOrElse {
        val failure = when (it) {
            is JsonEncodingException, is JsonDataException -> FailureCause.ParsingIssue(it)
            else -> FailureCause.CommunicationIssue.NetworkIssue(it as? IOException ?: IOException(it))
        }
        Xor.Second(failure)
    }

    companion object {
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private fun parseVersionsFromNpmMetaData(metadata: NpmMetadata): List<Version> {
            return metadata.versions.keys.mapNotNull {
                if (it.isNotBlank()) Version(it.trim()) else null
            }
        }

        private fun parseLastUpdatedFromNpmMetaData(metadata: NpmMetadata): Long {
            val timestamp = metadata.time["modified"]
            return Instant.parse(timestamp).epochSecond
        }
    }
}

internal data class NpmMetadata(
    val versions: Map<String, NpmVersion>,
    val time: Map<String, String>
)

internal data class NpmVersion(
    val name: String,
    val version: String
)
