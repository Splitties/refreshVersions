package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter
import org.gradle.kotlin.dsl.withType

internal open class BuildSrcVersionsExtensionImpl(
    override var renameLibs: String = PluginConfig.DEFAULT_LIBS,
    override var renameVersions: String = PluginConfig.DEFAULT_VERSIONS,
    override var indent: String = PluginConfig.DEFAULT_INDENT,
    override var versionsOnlyMode: VersionsOnlyMode? = null,
    override var versionsOnlyFile: String? = null
) : BuildSrcVersionsExtension, java.io.Serializable {
    var useFqqnFor: List<String> = emptyList()

    // Use @Transient for fields that should not be present in toString()
    override fun toString(): String = PluginConfig.extensionAdapter.toJson(this)

    override fun rejectVersionIf(filter: ComponentFilter) {
        BuildSrcVersionsTask.theProject.tasks.withType<DependencyUpdatesTask> {
            this.rejectVersionIf(filter)
        }
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

    @Transient
    override var useFdqnFor: MutableList<String> = mutableListOf()
    @Transient
    override var rejectedVersionKeywords: MutableList<String> = mutableListOf()

    companion object {
        private const val serialVersionUID = 20180617104400L
    }
}
