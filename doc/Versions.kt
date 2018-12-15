import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val constraint_layout: String = "1.1.3"

    const val espresso_core: String = "3.0.2"

    const val com_android_support_test_runner: String = "1.0.2"

    const val appcompat_v7: String = "28.0.0"

    const val aapt2: String = "3.2.1-4818971"

    const val com_android_tools_build_gradle: String = "3.2.1"

    const val lint_gradle: String = "26.2.1"

    const val dexcount_gradle_plugin: String = "0.8.3" // available: "0.8.4"

    const val play_publisher: String = "1.2.2" // available: "2.0.0"

    const val com_gradle_build_scan_gradle_plugin: String = "1.15.1" // available: "2.1"

    const val com_jfrog_artifactory_gradle_plugin: String = "4.7.5" // available: "4.8.1"

    const val moshi: String = "1.8.0"

    const val com_squareup_okhttp3: String = "3.11.0" // available: "3.12.0"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.0"
    // exceed the version found: 0.3

    const val io_fabric_tools_gradle: String = "1.25.4" // available: "1.27.0"

    const val rxjava: String = "2.2.0" // available: "2.2.4"

    const val junit: String = "4.12"

    const val gradle_git: String = "0.2.3" // available: "1.7.2"

    const val org_gradle_kotlin_kotlin_dsl_gradle_plugin: String = "1.0-rc-5" // available: "1.1.0"

    const val org_jetbrains_kotlin: String = "1.2.61" // available: "1.3.11"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "4.10.2"

        const val currentVersion: String = "5.0"

        const val nightlyVersion: String = "5.2-20181215000041+0000"

        const val releaseCandidate: String = "5.1-rc-1"
    }
}
