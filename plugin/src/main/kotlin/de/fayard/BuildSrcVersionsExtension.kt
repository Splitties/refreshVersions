package de.fayard

interface BuildSrcVersionsExtension {
    var useFdqnFor: MutableList<String>

    fun useFdqnFor(vararg dependencyName: String)

    var rejectedVersionKeywords: MutableList<String>

    fun rejectedVersionKeywords(vararg keyword: String)

    var renameLibs : String

    var renameVersions : String

    var indent: String

    var versionsOnlyMode : VersionsOnlyMode?

    var versionsOnlyFile : String?

}

internal open class BuildSrcVersionsExtensionImpl(
    override var useFdqnFor: MutableList<String> = mutableListOf(),
    override var renameLibs: String = PluginConfig.DEFAULT_LIBS,
    override var renameVersions: String = PluginConfig.DEFAULT_VERSIONS,
    override var indent: String = PluginConfig.DEFAULT_INDENT,
    override var versionsOnlyMode: VersionsOnlyMode? = null,
    override var versionsOnlyFile: String? = null,
    override var rejectedVersionKeywords: MutableList<String> = PluginConfig.DEFAULT_REJECTED_KEYWORDS
) : BuildSrcVersionsExtension {


    override fun rejectedVersionKeywords(vararg keyword: String) {
        rejectedVersionKeywords = keyword.toMutableList()
    }

    override fun useFdqnFor(vararg dependencyName: String) {
        useFdqnFor = dependencyName.toMutableList()
    }

    override fun toString() : String =
        PluginConfig.extensionAdapter.toJson(this)

}
