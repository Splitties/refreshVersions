package de.fayard

interface BuildSrcVersionsExtension {
    var useFdqnFor: MutableList<String>

    fun useFdqnFor(vararg dependencyName: String)

    var rejectedVersionKeywords: MutableList<String>

    fun rejectedVersionKeywords(vararg keyword: String)

    var renameLibs : String

    var renameVersions : String

    var indent: String

    var singleFileMode : Boolean

    fun isValid() : Boolean {
        val regexp = "[a-zA-Z][a-zA-Z]+".toRegex()
        return regexp.matchEntire(renameLibs) != null && regexp.matchEntire(renameVersions) != null
    }

}

internal open class BuildSrcVersionsExtensionImpl(
    override var useFdqnFor: MutableList<String> = mutableListOf(),
    override var renameLibs: String = PluginConfig.DEFAULT_LIBS,
    override var renameVersions: String = PluginConfig.DEFAULT_VERSIONS,
    override var indent: String = "",
    override var singleFileMode: Boolean = false,
    override var rejectedVersionKeywords: MutableList<String> = PluginConfig.DEFAULT_REJECTED_KEYWORDS
) : BuildSrcVersionsExtension {

    override fun rejectedVersionKeywords(vararg keyword: String) {
        rejectedVersionKeywords = keyword.toMutableList()
    }

    override fun useFdqnFor(vararg dependencyName: String) {
        useFdqnFor = dependencyName.toMutableList()
    }

    override fun toString(): String {
        return "BuildSrcVersionsExtension(useFdqnFor=$useFdqnFor, renameLibs='$renameLibs', renameVersions='$renameVersions', indent='$indent', singleFileMode=$singleFileMode, rejectedVersionKeywords=$rejectedVersionKeywords)"
    }
}
