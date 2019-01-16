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

    const val io_vertx_vertx_plugin_gradle_plugin: String = "0.3.1" 

    const val vertx_core: String = "none" // No version. See buildSrcVersions#23

    const val vertx_stack_depchain: String = "3.6.2" 

    const val vertx_web: String = "none" // No version. See buildSrcVersions#23

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1"

        const val currentVersion: String = "5.1.1"

        const val nightlyVersion: String = "5.2-20190116011919+0000"

        const val releaseCandidate: String = ""
    }
}
