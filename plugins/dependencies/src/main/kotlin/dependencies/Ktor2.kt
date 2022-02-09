@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyGroup
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
 */
object Ktor2 : DependencyGroup("io.ktor") {

    val http = module("ktor-http")

    val httpCio = module("ktor-http-cio")

    val io = module("ktor-io")

    val testDispatcher = module("ktor-test-dispatcher")

    val utils = module("ktor-utils")

    val client = Client

    object Client : IsNotADependency {

        val core = module("ktor-client-core")

        /**
         * As of ktor 1.3.0, this is experimental and supports JVM only.
         *
         * CIO stands for **Coroutines I/O**.
         */
        val cio = module("ktor-client-cio")

        /** Supports JVM and Android because OkHttp supports only the JVM as of version 4. */
        val okHttp = module("ktor-client-okhttp")

        /** Supports JVM only because Jetty is JVM only. */
        val jetty = module("ktor-client-jetty")

        /** Supports iOS, macOS, watchOS and tvOS. */
        val darwin = module("ktor-client-darwin")

        /** Supports Linux X64, Windows X64 and macOS. */
        val curl = module ("ktor-client-curl")

        /** Supports JVM only because Apache dependency is JVM only. */
        val apache = module("ktor-client-apache")

        val auth = module("ktor-client-auth")

        val authBasic = module("ktor-client-auth-basic")

        val json = module("ktor-client-json")


        /** Supports JVM only. */
        val jsonTests = module("ktor-client-json-tests")

        val encoding = module("ktor-client-encoding")

        val logging = module("ktor-client-logging")

        val mock = module("ktor-client-mock")

        val serialization = module("ktor-client-serialization")

        val tests = module("ktor-client-tests")

        val websockets = module("ktor-client-websockets")

        @Deprecated("Use OkHttp or cio", ReplaceWith("okHttp"), DeprecationLevel.ERROR)
        val android = module("ktor-client-android")
    }

    val features = Features

    object Features : IsNotADependency {
        /** As of ktor 1.3.0, supports JVM only. */
        val auth = module("ktor-server-auth")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * JWT stands for **JSON Web Tokens**.
         *
         * ktor doc: [JWT and JWK authentication](https://ktor.io/servers/features/authentication/jwt.html)
         */
        val authJwt = module("ktor-server-auth-jwt")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * LDAP stands for **Lightweight Directory Access Protocol**.
         *
         * ktor doc: [LDAP authentication](https://ktor.io/servers/features/authentication/ldap.html).
         */
        val authLdap = module("ktor-server-auth-ldap")

        /**
         * Supports JVM only (because Dropwizard is JVM-only).
         *
         * ktor doc: [Metrics with Dropwizard metrics](https://ktor.io/servers/features/metrics.html)
         */
        val metrics = module("ktor-server-metrics")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * Provides the `directorySessionStorage` function.
         *
         * This artifact is not needed for other session features as they are built-in into ktor-server-core.
         *
         * ktor doc: [Storing a session id in a cookie, and storing session contents in a file
           ](https://ktor.io/servers/features/sessions.html#directorySessionStorage).
         */
        val serverSessions = module("ktor-server-sessions")

        /***
         * The Webjars plugin enables serving the client-side libraries provided by WebJars.
         * It allows you to package your assets such as JavaScript and CSS libraries as part of your fat JAR.
         *
         * https://ktor.io/docs/eap/webjars.html
         */
        val webjars = module("ktor-server-webjars")

        /**
         * The AutoHeadResponse plugin provides us with the ability to automatically respond to HEAD request for every route that has a GET defined.
         *
         * https://ktor.io/docs/eap/autoheadresponse.html
         */
        val autoHeadResponse = module("ktor-server-auto-head-response")
    }

    val serialization = Serialization

    object Serialization {

        /**
         * ktor doc: [kotlinx.serialization](https://ktor.io/docs/kotlin-serialization.html)
         */
        val contentNegotiation = module("ktor-server-content-negotiation")

        /**
         * Supports JVM only (because Gson is JVM-only).
         *
         * ktor doc: [JSON support using Gson](https://ktor.io/servers/features/content-negotiation/gson.html)
         */
        val gson = module("ktor-serialization-gson")

        /**
         * Supports JVM only (because Jackson is JVM-only).
         *
         * ktor doc: [JSON support using Jackson](https://ktor.io/servers/features/content-negotiation/jackson.html)
         */
        val jackson = module("ktor-serialization-jackson")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Type-safe Routing](https://ktor.io/servers/features/locations.html)
         */
        val locations = module("ktor-server-locations")

        /**
         * ktor doc: [serialization](https://ktor.io/docs/eap/serialization.html)
         */
        val kotlinxJson = module("ktor-serialization-kotlinx-json")

        /**
         * ktor doc: [serialization](https://ktor.io/docs/eap/serialization.html)
         */
        val kotlinxXml = module("ktor-serialization-kotlinx-xml")

        /**
         * ktor doc: [serialization](https://ktor.io/docs/eap/serialization.html)
         */
        val kotlinxCbor = module("ktor-serialization-kotlinx-cbor")
    }

    /**
     * As of ktor 1.3.0, ktor server artifacts support only the JVM.
     */
    val server = Server

    object Server : IsNotADependency {

        /**
         * Add all artifacts
         */
        val all = module("ktor-server")

        /**
         * Core package where most of the application API and implementation is located.
         */
        val core = module("ktor-server-core")

        /**
         * Supports a deployed or embedded Jetty instance.
         */
        val jetty = module("ktor-server-jetty")

        /**
         * Supports Netty in embedded mode.
         */
        val netty = module("ktor-server-netty")

        /**
         * Supports Tomcat servers.
         */
        val tomcat = module("ktor-server-tomcat")

        /**
         * Used by Jetty and Tomcat and allows running in a generic servlet container.
         */
        val servlet = module("ktor-server-servlet")

        /**
         * Allows running application tests faster without starting the full host.
         */
        val testHost = module("ktor-server-test-host")

        /**
         * The StatusPages plugin allows Ktor applications to respond appropriately to any failure state
         * based on a thrown exception or status code.
         *
         * https://ktor.io/docs/eap/status-pages.html
         */
        val statusPage = module("ktor-server-status-pages")


        /**
         * The CallId plugin allows you to trace client requests end-to-end by using unique request IDs or call IDs.
         *
         * https://ktor.io/docs/eap/call-id.html
         */
        val callId = module("ktor-server-call-id")

        /**
         * The DoubleReceive plugin provides the ability to receive a request body several times
         * with no RequestAlreadyConsumedException exception.
         *
         * https://ktor.io/docs/eap/double-receive.html
         */
        val doubleReceive = module("ktor-server-double-receive")

        /**
         * DataConversion is a plugin that allows to serialize and deserialize a list of values.
         *
         * https://ktor.io/docs/eap/data-conversion.html
         */
        val dataConversion = module("ktor-server-data-conversion")

        /**
         * The DefaultHeadersplugin adds the standard Server and Date headers into each response.
         * Moreover, you can provide additional default headers and override the Server header.
         *
         * https://ktor.io/docs/eap/default-headers.html
         */
        val defaultHeader = module("ktor-server-default-headers")

        /**
         * Ktor provides the capability to compress outgoing content by using the Compression plugin.
         * You can use different compression algorithms, including gzip and deflate,
         * specify the required conditions for compressing data (such as a content type or response size),
         * or even compress data based on specific request parameters.
         *
         * https://ktor.io/docs/eap/compression.html
         */
        val compression = module("ktor-server-compression")

        /**
         * The CachingHeaders plugin adds the capability to configure the Cache-Control and Expires headers used for HTTP caching.
         * You can introduce different caching strategies for specific content types,
         * such as images, CSS and JavaScript files, and so on.
         *
         * https://ktor.io/docs/eap/caching.html
         */
        val cachingHeaders = module("ktor-server-caching-headers")

        /**
         * The ConditionalHeaders plugin avoids sending the body of content if it has not changed since the last request.
         *
         * https://ktor.io/docs/eap/conditional-headers.html
         */
        val conditionalHeaders = module("ktor-server-caching-headers")

        /**
         * If your server supposes to handle cross-origin requests, you need to install and configure the CORS Ktor plugin.
         * This plugin allows you to configure allowed hosts, HTTP methods, headers set by the client, and so on.
         *
         * https://ktor.io/docs/eap/cors.html
         */
        val cors = module("ktor-server-cors")

        /**
         * The ForwardedHeaderSupport and XForwardedHeaderSupport plugins allow you to
         * handle reverse proxy headers to get information about the original request
         * when a Ktor server is placed behind a reverse proxy.
         * This might be useful for logging purposes.
         *
         * https://ktor.io/docs/eap/forward-headers.html
         */
        val forwardedHeaders = module("ktor-server-forwarded-header")

        /**
         * The HSTS plugin adds the required HTTP Strict Transport Security headers to the request according to the RFC 6797. When the browser receives HSTS policy headers, it no longer attempts to connect to the server with insecure connections for a given period.
         *
         * https://ktor.io/docs/eap/hsts.html
         */
        val hsts = module("ktor-server-hsts")

        /**
         * The HttpsRedirect plugin redirects all HTTP requests to the HTTPS counterpart before processing the call.
         * By default, a resource returns 301 Moved Permanently, but it can be configured to be 302 Found.
         *
         * https://ktor.io/docs/eap/https-redirect.html
         */
        val httpsRedirect = module("ktor-server-http-redirect")

        /**
         * The PartialContent plugin adds support for handling HTTP range requests used
         * to send only a portion of an HTTP message back to a client.
         * This plugin is useful for streaming content or resuming partial downloads.
         *
         * https://ktor.io/docs/eap/partial-content.html
         */
        val partialContent = module("ktor-server-partial-content")

        /**
         * Ktor supports the WebSocket protocol and allows you to create applications
         * that require real-time data transfer from and to the server.
         * For example, WebSockets can be used to create a chat application.
         *
         * https://ktor.io/docs/eap/websocket.html
         */
        val websockets = module("ktor-server-websockets")

        /**
         * Ktor provides the capability to log application events using the SLF4J library.
         * You can learn about general logging configuration from the Logging topic.
         * The CallLogging plugin allows you to log incoming client requests.
         *
         * https://ktor.io/docs/eap/call-logging.html
         */
        val callLoging = module("ktor-server-call-logging")

        /**
         * The MicrometerMetrics plugin enables Micrometer metrics in your Ktor server application and allows you to choose the required monitoring system, such as Prometheus, JMX, Elastic, and so on. By default, Ktor exposes metrics for monitoring HTTP requests and a set of low-level metrics for monitoring the JVM. You can customize these metrics or create new ones.
         *
         * https://ktor.io/docs/eap/micrometer-metrics.html
         */
        val metricsMicrometer = module("ktor-server-metrics-micrometer")
    }

    val template = Template

    object  Template: IsNotADependency {

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Emit HTML with a DSL](https://ktor.io/servers/features/templates/html-dsl.html)
         */
        val htmlBuilder = module("ktor-server-html-builder")

        /**
         * CSS DSL extends HTML DSL and allows you to author stylesheets in Kotlin by using the kotlin-css wrapper.
         *
         * Requires dependency [htmlBuilder]
         *
         * https://ktor.io/docs/eap/css-dsl.html
         */
        val kotlinCss = module("kotlin-css")

        /**
         * Ktor allows you to use Mustache templates as views within your application by installing the Mustache plugin.
         *
         * https://ktor.io/docs/eap/mustache.html
         */
        val mustache = module("ktor-server-mustache")

        /**
         * Ktor allows you to use Thymeleaf templates as views within your application by installing the Thymeleaf plugin.
         *
         * https://ktor.io/docs/eap/thymeleaf.html
         */
        val thymeleaf = module("ktor-server-thymeleaf")

        /**
         * Ktor allows you to use Pebble templates as views within your application by installing the Pebble plugin.
         *
         * https://ktor.io/docs/eap/pebble.html
         */
        val pebble = module("ktor-server-pebble")

        /**
         * Supports JVM only (because Apache FreeMarker is JVM-only).
         *
         * ktor doc: [Using Freemarker Templates](https://ktor.io/servers/features/templates/freemarker.html)
         */
        val freemarker = module("ktor-server-freemarker")

        /**
         * Supports JVM only (because Apache Velocity JVM-only).
         *
         * ktor doc: [Using Velocity Templates](https://ktor.io/servers/features/templates/velocity.html)
         */
        val velocity = module("ktor-server-velocity")
    }

    val network = Network

    object Network : IsNotADependency {

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Raw sockets](https://ktor.io/servers/raw-sockets.html)
         */
        val network = module("ktor-network")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Raw sockets](https://ktor.io/servers/raw-sockets.html)
         */
        val tls = module("ktor-network-tls")

        /**
         * [KDoc here](https://api.ktor.io/latest/io.ktor.network.tls.certificates/).
         */
        val tlsCertificates = module("ktor-network-tls-certificates")
    }
}
