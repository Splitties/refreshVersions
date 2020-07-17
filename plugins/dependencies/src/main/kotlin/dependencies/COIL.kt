package dependencies

/**
 * COIL stands for **Co**routine **I**mage **L**oader.
 *
 * It's a lightweight Android library made at Instacart.
 *
 * [Official website](https://coil-kt.github.io/coil/)
 *
 * GitHub Page: [coil-kt/coil](https://github.com/coil-kt/coil/)
 */
object COIL {
    private const val artifactPrefix = "io.coil-kt:coil"

    const val coil = "$artifactPrefix:_"
    const val base = "$artifactPrefix-base:_"
    const val gif = "$artifactPrefix-gif:_"
    const val svg = "$artifactPrefix-svg:_"
    const val video = "$artifactPrefix-video:_"
}
