@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating

/**
 * COIL stands for **Co**routine **I**mage **L**oader.
 *
 * It's a lightweight Android library made at Instacart.
 *
 * [Official website](https://coil-kt.github.io/coil/)
 *
 * GitHub Page: [coil-kt/coil](https://github.com/coil-kt/coil/)
 */
@Incubating
object COIL : DependencyNotationAndGroup(group = "io.coil-kt", name = "coil") {

    @JvmField val base = "$artifactPrefix-base:_"
    @JvmField val gif = "$artifactPrefix-gif:_"
    @JvmField val svg = "$artifactPrefix-svg:_"
    @JvmField val video = "$artifactPrefix-video:_"
}
