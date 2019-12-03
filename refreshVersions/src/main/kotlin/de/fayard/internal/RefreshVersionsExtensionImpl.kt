package de.fayard.internal

import de.fayard.ComponentFilter
import de.fayard.RefreshVersionsExtension

internal open class RefreshVersionsExtensionImpl(
    var alwaysUpdateVersions: Boolean = false,
    // TODO: use DEFAULT_MAPPING instead of ALIGN_VERSION_GROUPS
    override var versionsMapping: MutableMap<String, String> = PluginConfig.DEFAULT_MAPPING.toMutableMap()
) : RefreshVersionsExtension, java.io.Serializable {

    // Necessary because of https://github.com/jmfayard/buildSrcVersions/issues/92
    fun defensiveCopy(): RefreshVersionsExtensionImpl = RefreshVersionsExtensionImpl(
        alwaysUpdateVersions, versionsMapping
    )

    override fun alwaysUpdateVersions() {
        this.alwaysUpdateVersions = true
    }

    // Use @Transient for fields that should not be present in toString()
    override fun toString(): String = PluginConfig.extensionAdapter.toJson(this)

    override fun rejectVersionIf(filter: ComponentFilter) {
        // TODO: use the filter in RefreshVersionsTask
    }

    override fun isNonStable(version: String): Boolean {
        return PluginConfig.isNonStable(version)
    }

    override fun isStable(version: String): Boolean {
        return isNonStable(version).not()
    }

    companion object {
        private const val serialVersionUID = 20180617104400L
    }
}
