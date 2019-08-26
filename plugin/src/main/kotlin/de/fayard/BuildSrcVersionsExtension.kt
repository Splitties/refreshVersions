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

fun BuildSrcVersionsExtension.useBuildSrc() = versionsOnlyMode != null

enum class VersionsOnlyMode(val example: String) {
    KOTLIN_VAL("""
        val okhttp = "3.2.0" // available: "3.3.1"
        val com_squareup_moshi = "1.8.0"
    """.trimIndent()),
    GROOVY_DEF("""
        def okhttp = '3.2.0' // available: '3.3.1'
        def com_squareup_moshi = '1.8.0'
    """.trimIndent()),
    GROOVY_EXT("""
        ext {
            okhttp = '3.2.0' // available: '3.3.1'
            com_squareup_moshi = '1.8.0'
        }
    """.trimIndent())
    ;
    companion object
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

    override fun toString(): String {
        return "BuildSrcVersionsExtensionImpl(useFdqnFor=$useFdqnFor, renameLibs='$renameLibs', renameVersions='$renameVersions', indent='$indent', versionsOnlyMode=$versionsOnlyMode, versionsOnlyFile=$versionsOnlyFile, rejectedVersionKeywords=$rejectedVersionKeywords)"
    }


}
