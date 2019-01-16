import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val okhttp: String = "3.12.1" 

    const val okio: String = "2.0.0" // available: "2.1.0"

    const val kubernetes_client: String = "3.1.12.fuse-730005" // available: "4.1.1"

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.11" 

    const val org_jetbrains_kotlin: String = "1.3.11" 

    /**
     *
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "5.1.1"

    const val gradleCurrentVersion: String = "5.1"
}
