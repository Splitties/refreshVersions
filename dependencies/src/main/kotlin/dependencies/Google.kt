@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object Google {

    val android = Android

    object Android {
        private const val artifactBase = "com.google.android"

        const val material = "$artifactBase.material:material:_"

        private const val wearOsVersion = "_"

        const val wearable = "$artifactBase.wearable:wearable:$wearOsVersion"
        const val supportWearable = "$artifactBase.support:wearable:$wearOsVersion"

        val playServices = PlayServices

        object PlayServices {
            private const val artifactPrefix = "$artifactBase.gms:play-services"

            const val base = "$artifactPrefix-base:_"

            const val auth = "$artifactPrefix-auth:_"
            const val location = "$artifactPrefix-location:_"

            const val tasks = "$artifactPrefix-tasks:_"

            private const val visionVersion = "_"

            const val vision = "$artifactPrefix-vision:$visionVersion"
            const val visionCommon = "$artifactPrefix-vision-common:$visionVersion"

            const val wearable = "$artifactPrefix-wearable:_"
        }
    }
}
