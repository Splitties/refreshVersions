package de.fayard.internal

import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter
import de.fayard.BuildSrcVersionsExtension
import de.fayard.VersionsOnlyMode

internal open class BuildSrcVersionsExtensionImpl(
    override var renameLibs: String = PluginConfig.DEFAULT_LIBS,
    override var renameVersions: String = PluginConfig.DEFAULT_VERSIONS,
    override var indent: String = PluginConfig.DEFAULT_INDENT,
    override var versionsOnlyMode: VersionsOnlyMode? = null,
    override var versionsOnlyFile: String? = null
) : BuildSrcVersionsExtension, java.io.Serializable {
    override fun alwaysUpdateVersions() {
        this.alwaysUpdateVersions = true
        println("After alwaysUpdateVersions, this=$this")
    }

    var useFqqnFor: List<String> = emptyList()
    var alwaysUpdateVersions = false

    // Use @Transient for fields that should not be present in toString()
    override fun toString(): String = PluginConfig.extensionAdapter.toJson(this)

    override fun rejectVersionIf(filter: ComponentFilter) {
        (PluginConfig.configureGradleVersions) {
            this.rejectVersionIf(filter)
        }
    }

    override fun isNonStable(version: String): Boolean {
        return PluginConfig.isNonStable(version)
    }

    override fun useFqdnFor(vararg dependencyName: String) {
        useFqqnFor = dependencyName.toList()
    }

    companion object {
        private const val serialVersionUID = 20180617104400L
    }
}
