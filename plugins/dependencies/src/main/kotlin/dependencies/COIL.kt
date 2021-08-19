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

    val base = module("coil-base")

    val gif = module("coil-gif")

    val svg = module("coil-svg")

    val video = module("coil-video")
}
