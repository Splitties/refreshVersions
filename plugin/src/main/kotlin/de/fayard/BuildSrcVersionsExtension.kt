package de.fayard

import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter


interface BuildSrcVersionsExtension {
    fun useFqdnFor(vararg dependencyName: String)

    fun rejectVersionIf(filter: ComponentFilter)

    var renameLibs: String

    var renameVersions: String

    var indent: String

    var versionsOnlyMode: VersionsOnlyMode?

    var versionsOnlyFile: String?

    fun isNonStable(version: String): Boolean

}
