package de.fayard.internal

import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter
import de.fayard.OrderBy
import de.fayard.RefreshVersionsExtension

internal open class RefreshVersionsExtensionImpl(
    override var propertiesFile: String? = null,
    var useFqqnFor: List<String> = emptyList(),
    var alwaysUpdateVersions: Boolean = false,
    override var orderBy: OrderBy = OrderBy.GROUP_AND_LENGTH,
    override var alignVersionsForGroups: MutableList<String> = PluginConfig.ALIGN_VERSION_GROUPS
) : RefreshVersionsExtension, java.io.Serializable {

    // Necessary because of https://github.com/jmfayard/buildSrcVersions/issues/92
    fun defensiveCopy(): RefreshVersionsExtensionImpl = RefreshVersionsExtensionImpl(
        propertiesFile, useFqqnFor, alwaysUpdateVersions, orderBy, alignVersionsForGroups
    )

    override fun alwaysUpdateVersions() {
        this.alwaysUpdateVersions = true
    }

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

    override fun isStable(version: String): Boolean {
        return isNonStable(version).not()
    }

    override fun useFqdnFor(vararg dependencyName: String) {
        useFqqnFor = dependencyName.toList()
    }

    companion object {
        private const val serialVersionUID = 20180617104400L
    }
}
