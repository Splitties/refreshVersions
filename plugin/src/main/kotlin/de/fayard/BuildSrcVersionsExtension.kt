package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter


interface BuildSrcVersionsExtension {
    fun useFqdnFor(vararg dependencyName: String)

    fun rejectVersionIf(filter: ComponentFilter)

    var renameLibs : String

    var renameVersions : String

    var indent: String

    var versionsOnlyMode : VersionsOnlyMode?

    var versionsOnlyFile : String?


    @Deprecated("Deprecated, see #64", ReplaceWith("useFqdnFor()"))
    var useFdqnFor: MutableList<String>

    @Deprecated("Deprecated, see #64", ReplaceWith("useFqdnFor(dependencyName)"))
    fun useFdqnFor(vararg dependencyName: String)


    @Deprecated("Remove or use rejectVersionIf { ... }", replaceWith = ReplaceWith(""))
    var rejectedVersionKeywords: MutableList<String>

    @Deprecated("Remove or use rejectVersionIf { selection -> ... }", replaceWith = ReplaceWith(""))
    fun rejectedVersionKeywords(vararg keyword: String)
}

internal open class BuildSrcVersionsExtensionImpl(
    override var renameLibs: String = PluginConfig.DEFAULT_LIBS,
    override var renameVersions: String = PluginConfig.DEFAULT_VERSIONS,
    override var indent: String = PluginConfig.DEFAULT_INDENT,
    override var versionsOnlyMode: VersionsOnlyMode? = null,
    override var versionsOnlyFile: String? = null
) : BuildSrcVersionsExtension {
    var useFqqnFor: List<String> = emptyList()

    // Use @Transient for fields that should not be present in toString()
    override fun toString() : String = PluginConfig.extensionAdapter.toJson(this)

    @Transient lateinit var upstream: DependencyUpdatesTask

    override fun rejectVersionIf(filter: ComponentFilter) {
        upstream.rejectVersionIf(filter)
    }

    override fun useFqdnFor(vararg dependencyName: String) {
        useFqqnFor = dependencyName.toList()
    }

    override fun rejectedVersionKeywords(vararg keyword: String) {
        println("Warning: rejectedVersionKeywords is deprecated, see ${PluginConfig.issue53PluginConfiguration}")
    }

    override fun useFdqnFor(vararg dependencyName: String) {
        println("Warning: useFdqnFor is deprecated, use useFqqnFor() instead. ${PluginConfig.issue53PluginConfiguration}")
        useFqdnFor(*dependencyName)
    }

    @Transient override var useFdqnFor: MutableList<String> = mutableListOf()
    @Transient override var rejectedVersionKeywords: MutableList<String> = mutableListOf()

}
