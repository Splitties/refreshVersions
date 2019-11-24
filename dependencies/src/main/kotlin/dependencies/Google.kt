package dependencies

/**
 * The actual dependency version comes from `gradle.properties`
 * from either `version.$NAME=xxx` or `version.com.google.android.$GROUP=xxx`
 **/
object Google {
    const val material = "com.google.android.material:material:$placeholderVersion"
    const val wearable = "com.google.android.wearable:wearable:$placeholderVersion"
    const val supportWearable = "com.google.android.support:wearable:$placeholderVersion"



    /**
     * The actual dependency version comes from `gradle.properties`
     * from either `version.$NAME=xxx` or `version.com.google.android.gms=xxx`
     **/
    val playServices = PlayServices
    object PlayServices {
        private const val artifactPrefix = "com.google.android.gms:play-services"
        const val base = "$artifactPrefix-base:$placeholderVersion"
        const val auth = "$artifactPrefix-auth:$placeholderVersion"
        const val location = "$artifactPrefix-location:$placeholderVersion"
        const val tasks = "$artifactPrefix-tasks:$placeholderVersion"
        const val vision = "$artifactPrefix-vision:$placeholderVersion"
        const val visionCommon = "$artifactPrefix-vision-common:$placeholderVersion"
        const val wearable = "$artifactPrefix-wearable:$placeholderVersion"
    }
}
