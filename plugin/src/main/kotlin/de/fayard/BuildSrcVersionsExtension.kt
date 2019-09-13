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

    fun isNonStable(version: String) : Boolean

    @Deprecated("Deprecated, see #64", ReplaceWith("useFqdnFor()"))
    var useFdqnFor: MutableList<String>

    @Deprecated("Deprecated, see #64", ReplaceWith("useFqdnFor(dependencyName)"))
    fun useFdqnFor(vararg dependencyName: String)


    @Deprecated("Remove or use rejectVersionIf { ... }", replaceWith = ReplaceWith(""))
    var rejectedVersionKeywords: MutableList<String>

    @Deprecated("Remove or use rejectVersionIf { selection -> ... }", replaceWith = ReplaceWith(""))
    fun rejectedVersionKeywords(vararg keyword: String)
}
