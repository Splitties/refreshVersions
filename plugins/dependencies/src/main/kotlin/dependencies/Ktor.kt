@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import Ktor.server
import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * [Complete KDoc here](https://api.ktor.io/latest/).
 *
 * [Website here](https://ktor.io/).
 *
 * Unless specified otherwise, and excepting [server] artifacts, ktor artifacts support all the following platforms:
 * - JS
 * - JVM/Android
 * - Linux X64
 * - Windows X64
 * - Apple OSes (iOS, macOS, watchOS and tvOS)
 *
 * TODO: Finish KDoc of undocumented artifact constants. Also link to their KDoc.
 */
object Ktor : DependencyGroup(
    group = "io.ktor",
    rawRules = """
        io.ktor:*
           ^^^^
    """.trimIndent()
) {

    val testDispatcher = module("ktor-test-dispatcher")
    val utils = module("ktor-utils")
    val client = Client

    object Client : IsNotADependency {
        val android = module("ktor-client-android")
        val apache = module("ktor-client-apache")
        val auth = module("ktor-client-auth")
        val cio = module("ktor-client-cio")
        val contentNegotiation = module("ktor-client-content-negotiation")
        val contentNegotiationTests = module("ktor-client-content-negotiation-tests")
        val core = module("ktor-client-core")
        val curl = module("ktor-client-curl")
        val darwin = module("ktor-client-darwin")
        val encoding = module("ktor-client-encoding")
        val gson = module("ktor-client-gson")
        val jackson = module("ktor-client-jackson")
        val java = module("ktor-client-java")
        val jetty = module("ktor-client-jetty")
        val json = module("ktor-client-json")
        val jsonTests = module("ktor-client-json-tests")
        val logging = module("ktor-client-logging")
        val mock = module("ktor-client-mock")
        val okHttp = module("ktor-client-okhttp")
        val resources = module("ktor-client-resources")
        val serialization = module("ktor-client-serialization")
        val tests = module("ktor-client-tests")
    }

    val plugins = Plugins
    object Plugins : IsNotADependency {
        val events = module("ktor-events")
        val http = Http
        object Http : DependencyNotationAndGroup(group = group, name = "ktor-http") {
            val cio = module("ktor-http-cio")
        }
        val io = module("ktor-io")
        val network = module("ktor-network")
        val networkTls = module("ktor-network-tls")
        val networkTlsCertificates = module("ktor-network-tls-certificates")
        val resources = module("ktor-resources")

        val serialization = Serialization
        object Serialization : DependencyNotationAndGroup(
            group = group,
            name = "ktor-serialization"
        ) {
            val gson = module("ktor-serialization-gson")
            val jackson = module("ktor-serialization-jackson")
            val kotlinx = Kotlinx
            object Kotlinx : DependencyNotationAndGroup(
                group = group,
                name = "ktor-serialization-kotlinx"
            ) {
                val cbor = module("ktor-serialization-kotlinx-cbor")
                val json = module("ktor-serialization-kotlinx-json")
                val tests = module("ktor-serialization-kotlinx-tests")
                val xml = module("ktor-serialization-kotlinx-xml")
            }
        }

        val websocketSerialization = module("ktor-websocket-serialization")
        val websockets = module("ktor-websockets")
    }

    val server = Server
    object Server : DependencyNotationAndGroup(
        group = group,
        name = "ktor-server"
    ) {
        val auth = Auth
        object Auth : DependencyNotationAndGroup(
            group = group,
            name = "ktor-server-auth"
        ) {
            val jwt = module("ktor-server-auth-jwt")
            val ldap = module("ktor-server-auth-ldap")
        }
        val autoHeadResponse = module("ktor-server-auto-head-response")
        val cachingHeaders = module("ktor-server-caching-headers")
        val callId = module("ktor-server-call-id")
        val callLogging = module("ktor-server-call-logging")
        val cio = module("ktor-server-cio")
        val compression = module("ktor-server-compression")
        val conditionalHeaders = module("ktor-server-conditional-headers")
        val contentNegotiation = module("ktor-server-content-negotiation")
        val core = module("ktor-server-core")
        val cors = module("ktor-server-cors")
        val dataConversion = module("ktor-server-data-conversion")
        val defaultHeaders = module("ktor-server-default-headers")
        val doubleReceive = module("ktor-server-double-receive")
        val forwardedHeader = module("ktor-server-forwarded-header")
        val freeMarker = module("ktor-server-freemarker")
        val hostCommon = module("ktor-server-host-common")
        val hsts = module("ktor-server-hsts")
        val htmlBuilder = module("ktor-server-html-builder")
        val httpRedirect = module("ktor-server-http-redirect")
        val jetty = module("ktor-server-jetty")
        val jte = module("ktor-server-jte")
        val locations = module("ktor-server-locations")
        val methodOverride = module("ktor-server-method-override")
        val metrics = module("ktor-server-metrics")
        val metricsMicrometer = module("ktor-server-metrics-micrometer")
        val mustache = module("ktor-server-mustache")
        val netty = module("ktor-server-netty")
        val partialContent = module("ktor-server-partial-content")
        val pebble = module("ktor-server-pebble")
        val resources = module("ktor-server-resources")
        val servlet = module("ktor-server-servlet")
        val sessions = module("ktor-server-sessions")
        val statusPages = module("ktor-server-status-pages")
        val testHost = module("ktor-server-test-host")
        val testSuites = module("ktor-server-test-suites")
        val thymeleaf = module("ktor-server-thymeleaf")
        val tomcat = module("ktor-server-tomcat")
        val velocity = module("ktor-server-velocity")
        val webjars = module("ktor-server-webjars")
        val websockets = module("ktor-server-websockets")
    }
}
