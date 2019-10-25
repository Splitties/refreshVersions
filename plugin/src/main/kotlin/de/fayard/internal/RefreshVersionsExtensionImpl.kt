package de.fayard.internal

import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter
import de.fayard.OrderBy
import de.fayard.RefreshVersionsExtension

internal open class RefreshVersionsExtensionImpl(
    override var propertiesFile: String? = null,
    var useFqqnFor: List<String> = emptyList(),
    var alwaysUpdateVersions: Boolean = false,
    override var orderBy: OrderBy = OrderBy.GROUP_AND_LENGTH,
    // TODO: use DEFAULT_MAPPING instead of ALIGN_VERSION_GROUPS
    override var versionsMapping: MutableMap<String, String> = PluginConfig.DEFAULT_MAPPING.toMutableMap()
) : RefreshVersionsExtension, java.io.Serializable {

    override var alignVersionsForGroups: MutableList<String>
        get() = versionsMapping.values.toMutableList()
        set(value) = println("WARNING: alignVersionsForGroups is deprecated, use instead versionsMapping")

    // Necessary because of https://github.com/jmfayard/buildSrcVersions/issues/92
    fun defensiveCopy(): RefreshVersionsExtensionImpl = RefreshVersionsExtensionImpl(
        propertiesFile, useFqqnFor, alwaysUpdateVersions, orderBy, versionsMapping
    )

    override fun alwaysUpdateVersions() {
        this.alwaysUpdateVersions = true
    }

    // Use @Transient for fields that should not be present in toString()
    override fun toString(): String = PluginConfig.extensionAdapter.toJson(this)

    override fun rejectVersionIf(filter: ComponentFilter) {
        // TODO: use the filter in RefreshVersionsTask
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
