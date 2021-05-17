package de.fayard.refreshVersions.core

import org.gradle.api.Incubating
import java.io.IOException

@Incubating
abstract class DependencyVersionsFetcher protected constructor(
    val moduleId: ModuleId,
    val repoKey: Any
) {

    companion object;

    @Throws(IOException::class, Exception::class)
    abstract suspend fun getAvailableVersionsOrNull(versionFilter: ((Version) -> Boolean)?): SuccessfulResult?

    data class SuccessfulResult(
        val lastUpdateTimestampMillis: Long,
        val availableVersions: List<Version>
    )

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DependencyVersionsFetcher) return false

        if (moduleId != other.moduleId) return false
        if (repoKey != other.repoKey) return false

        return true
    }

    final override fun hashCode(): Int {
        var result = moduleId.hashCode()
        result = 31 * result + repoKey.hashCode()
        return result
    }
}
