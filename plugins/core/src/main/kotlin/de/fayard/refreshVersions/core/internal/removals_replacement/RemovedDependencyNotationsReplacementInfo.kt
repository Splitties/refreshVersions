package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import java.io.InputStream

@InternalRefreshVersionsApi
class RemovedDependencyNotationsReplacementInfo(
    val readRevisionOfLastRefreshVersionsRun: (lastVersion: String, snapshotRevision: Int?) -> Int,
    val currentRevision: Int,
    val removalsListingResource: InputStream
)
