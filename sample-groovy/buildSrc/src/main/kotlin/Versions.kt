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

    const val ru_ztrap_iconics_core_ktx: String = "1.0.3" 

    /**
     *
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "5.1.1"

    const val gradleCurrentVersion: String = "5.1"
}
