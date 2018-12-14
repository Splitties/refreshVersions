import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val kscript_support: String = "1.2.5"

    const val ktlint: String = "0.29.0"

    const val jsr305: String = "3.0.2"

    const val com_gradle_build_scan_gradle_plugin: String = "2.0.2" // available: "2.1"

    const val timber: String = "4.7.1"

    const val mockito_kotlin: String = "1.6.0" // exceed the version found: 1.5.0

    const val docopt: String = "0.6.0.20150202"

    const val moshi_lazy_adapters: String = "2.2"

    const val moshi: String = "1.8.0"

    const val com_squareup_okhttp3: String = "3.12.0"

    const val okio: String = "2.1.0"

    const val com_squareup_retrofit2: String = "2.5.0"

    const val kotlinpoet: String = "0.7.0"

    const val config: String = "1.3.3"

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3" // available: "0.3.0"

    const val fontawesomefx: String = "8.9" // exceed the version found: 8.0.6

    const val krangl: String = "0.6" // available: "0.10.3"

    const val config4k: String = "0.4.1"

    const val kotlintest: String = "2.0.7"

    const val rxjava: String = "2.2.4"

    const val rxkotlin: String = "2.3.0"

    const val joda_time: String = "2.10.1"

    const val junit: String = "4.12"

    const val tornadofx: String = "1.7.17"

    const val controlsfx: String = "9.0.0"

    const val jdom: String = "2.0.2"

    const val exposed: String = "0.11.2"

    const val org_jetbrains_kotlin_jvm_gradle_plugin: String = "1.3.10" // available: "1.3.11"

    const val org_jetbrains_kotlin: String = "1.3.10"

    const val kotlinx_coroutines_core: String = "1.0.1"

    const val kotlinx_coroutines_rx2: String = "1.0.1"

    const val kotlinx_html_jvm: String = "0.6.11"

    const val org_jlleitschuh_gradle_ktlint_gradle_plugin: String = "6.3.1"

    const val jtwig_core: String = "5.87.0.RELEASE"

    const val kodein_di_generic_jvm: String = "5.3.0" // available: "6.0.1"

    const val mockito_core: String = "2.23.4"

    const val postgresql: String = "42.2.5"

    const val selenium_java: String = "3.141.59"

    const val slf4j_simple: String = "1.7.25"

    const val zt_exec: String = "1.10"

    const val kotlin_coroutines_retrofit: String = "0.13.0-eap13" // available: "0.13.0"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.0"

        const val currentVersion: String = "5.0"

        const val nightlyVersion: String = "5.2-20181214000035+0000"

        const val releaseCandidate: String = "5.1-rc-1"
    }
}
