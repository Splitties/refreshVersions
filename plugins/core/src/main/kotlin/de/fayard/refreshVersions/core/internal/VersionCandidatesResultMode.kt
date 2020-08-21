package de.fayard.refreshVersions.core.internal

internal data class VersionCandidatesResultMode(
    val filterMode: FilterMode,
    val sortingMode: SortingMode
) {

    enum class FilterMode { AllIntermediateVersions, LatestByStabilityLevel, Latest; }

    sealed class SortingMode {
        sealed class ByRepo : SortingMode() {
            companion object : ByRepo()
            object LastUpdated : ByRepo()
            object LastVersionComparison : ByRepo()
        }
        object ByVersion : SortingMode()
    }
}
