package org.gradle.kotlin.dsl

import de.fayard.refreshVersions.RefreshVersionsExtension
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import org.gradle.api.initialization.Settings

inline fun Settings.refreshVersions(configure: RefreshVersionsExtension.() -> Unit) {
    // This function is needed because Gradle doesn't generate accessors for
    // settings extensions.
    require(isBuildSrc.not()) {
        "Configuring refreshVersions in buildSrc is not supported, " +
                "please configure it in the root project. " +
                "If you have a use case for a separate config in buildSrc, " +
                "please open an issue about that on GitHub."
    }
    extensions.getByType<RefreshVersionsExtension>().configure()
}
