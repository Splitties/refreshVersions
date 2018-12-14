import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val com_android_tools_build_gradle =  "3.2.1"

    const val com_github_b3er_rxfirebase_firebase_database = "11.2.0"

    const val com_github_bumptech_glide_compiler = "4.8.0"

    const val com_google_firebase_firebase_database = "12.0.0" // available: "16.0.3"

    const val io_fabric_tools_gradle: String = "1.26.1"

    const val jmfayard_github_io_gradle_kotlin_dsl_libs_gradle_plugin = "0.2.6"
 
    const val kotlin_scripting_compiler_embeddable: String = "1.2.71"

    const val krangl: String = "0.10.3"

    const val okio = "2.0.0" // available: "2.1.0"

    const val org_jetbrains_kotlin_jvm_gradle_plugin = "1.2.71"

    const val rxjava: String = "2.2.0" // available: "2.2.2"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "4.10.2"

        const val currentVersion: String = "4.10.2"

        const val nightlyVersion: String = "5.1-20181015235836+0000"

        const val releaseCandidate: String = ""
    }
}
