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
}

internal open class BuildSrcVersionsExtensionImpl(
    override var useFdqnFor: MutableList<String> = mutableListOf(),
    override var renameLibs: String = "Libs",
    override var renameVersions: String = "Versions",
    override var indent: String = "",
    override var singleFileMode: Boolean = false,
    override var rejectedVersionKeywords: MutableList<String> = mutableListOf("alpha", "beta", "rc", "cr", "m", "preview", "eap")
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
