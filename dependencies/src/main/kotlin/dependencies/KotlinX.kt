package dependencies

object KotlinX {
    val coroutines = Coroutines

    /**
     * The actual dependency version comes from `gradle.properties`
     * from either `version.org.jetbrains.kotlinx=xxx` or `version.$NAME=xxx` or `version.org.jetbrains.kotlinx..$NAME=xxx`
     **/
    object Coroutines {
        private const val artifactPrefix = "org.jetbrains.kotlinx:kotlinx-coroutines"
        const val core = "$artifactPrefix-core:$placeholderVersion"
        const val coreCommon = "$artifactPrefix-core-common:$placeholderVersion"
        const val coreNative = "$artifactPrefix-core-native:$placeholderVersion"
        const val coreJs = "$artifactPrefix-core-js:$placeholderVersion"
        const val android = "$artifactPrefix-android:$placeholderVersion"
        const val playServices = "$artifactPrefix-play-services:$placeholderVersion"
        const val test = "$artifactPrefix-test:$placeholderVersion"
    }
}
