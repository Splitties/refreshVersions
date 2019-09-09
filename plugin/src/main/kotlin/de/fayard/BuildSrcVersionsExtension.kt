package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter


interface BuildSrcVersionsExtension {
    var useFdqnFor: MutableList<String>

    fun useFdqnFor(vararg dependencyName: String)

    fun rejectVersionIf(filter: ComponentFilter)

    @Deprecated("Remove or use rejectVersionIf { ... }", replaceWith = ReplaceWith(""))
    var rejectedVersionKeywords: MutableList<String>

    @Deprecated("Remove or use rejectVersionIf { selection -> ... }", replaceWith = ReplaceWith(""))
    fun rejectedVersionKeywords(vararg keyword: String)

    var renameLibs : String

    var renameVersions : String

    var indent: String

    var versionsOnlyMode : VersionsOnlyMode?

    var versionsOnlyFile : String?


}

open class BuildSrcVersionsExtensionImpl(
    override var useFdqnFor: MutableList<String> = mutableListOf(),
    override var renameLibs: String = PluginConfig.DEFAULT_LIBS,
    override var renameVersions: String = PluginConfig.DEFAULT_VERSIONS,
    override var indent: String = PluginConfig.DEFAULT_INDENT,
    override var versionsOnlyMode: VersionsOnlyMode? = null,
    override var versionsOnlyFile: String? = null
) : BuildSrcVersionsExtension {
    @Transient lateinit var upstream: DependencyUpdatesTask

    override var rejectedVersionKeywords: MutableList<String> = mutableListOf()

    override fun rejectVersionIf(filter: ComponentFilter) {
        upstream.rejectVersionIf(filter)
    }

    override fun rejectedVersionKeywords(vararg keyword: String) {
        rejectedVersionKeywords = keyword.toMutableList()
    }

    override fun useFdqnFor(vararg dependencyName: String) {
        useFdqnFor = dependencyName.toMutableList()
    }

    override fun toString() : String =
        PluginConfig.extensionAdapter.toJson(this)

}
