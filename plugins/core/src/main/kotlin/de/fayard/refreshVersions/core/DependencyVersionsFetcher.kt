package de.fayard.refreshVersions.core

import okio.IOException
import org.gradle.api.Incubating

@Incubating
abstract class DependencyVersionsFetcher protected constructor(
    val moduleId: ModuleId,
    val repoUrlOrKey: String
) {

    companion object;

    abstract suspend fun attemptGettingAvailableVersions(versionFilter: ((Version) -> Boolean)?): Result

    protected fun failure(cause: FailureCause): Result.Failure = Result.Failure(
        repoUrlOrKey = repoUrlOrKey,
        cause = cause
    )

    sealed class Result {
        object NotFound : Result()
        data class Success(
            val lastUpdateTimestampMillis: Long,
            val availableVersions: List<Version>
        ) : Result()

        data class Failure(
            val repoUrlOrKey: String,
            val cause: FailureCause
        ) : Result()
    }

    sealed class FailureCause {

        open val exception: Throwable? = null

        sealed class CommunicationIssue : FailureCause() {
            data class HttpResponse(
                val statusCode: Int,
                override val exception: Throwable? = null
            ) : CommunicationIssue()

            data class NetworkIssue(
                override val exception: IOException
            ) : CommunicationIssue()
        }

        data class ParsingIssue(
            override val exception: Throwable
        ) : FailureCause()
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DependencyVersionsFetcher) return false

        if (moduleId != other.moduleId) return false
        if (repoUrlOrKey != other.repoUrlOrKey) return false

        return true
    }

    final override fun hashCode(): Int {
        var result = moduleId.hashCode()
        result = 31 * result + repoUrlOrKey.hashCode()
        return result
    }
}
