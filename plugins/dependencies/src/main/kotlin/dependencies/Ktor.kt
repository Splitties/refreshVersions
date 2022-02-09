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
 * TODO: Finish KDoc of undocumented artifact constants. Also link to their KDoc.
 */
@Deprecated("Replace by Ktor2 if you use version >= 2.0.0", ReplaceWith("Ktor2"))
object Ktor : DependencyGroup("io.ktor",
    rawRules = """
        io.ktor:*
           ^^^^
    """.trimIndent()
    ) {

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
        val auth = module("ktor-auth")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * JWT stands for **JSON Web Tokens**.
         *
         * ktor doc: [JWT and JWK authentication](https://ktor.io/servers/features/authentication/jwt.html)
         */
        val authJwt = module("ktor-auth-jwt")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * LDAP stands for **Lightweight Directory Access Protocol**.
         *
         * ktor doc: [LDAP authentication](https://ktor.io/servers/features/authentication/ldap.html).
         */
        val authLdap = module("ktor-auth-ldap")

        /**
         * Supports JVM only (because Apache FreeMarker is JVM-only).
         *
         * ktor doc: [Using Freemarker Templates](https://ktor.io/servers/features/templates/freemarker.html)
         */
        val freemarker = module("ktor-freemarker")

        /**
         * Supports JVM only (because Apache Velocity JVM-only).
         *
         * ktor doc: [Using Velocity Templates](https://ktor.io/servers/features/templates/velocity.html)
         */
        val velocity = module("ktor-velocity")

        /**
         * Supports JVM only (because Gson is JVM-only).
         *
         * ktor doc: [JSON support using Gson](https://ktor.io/servers/features/content-negotiation/gson.html)
         */
        val gson = module("ktor-gson")

        /**
         * Supports JVM only (because Jackson is JVM-only).
         *
         * ktor doc: [JSON support using Jackson](https://ktor.io/servers/features/content-negotiation/jackson.html)
         */
        val jackson = module("ktor-jackson")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Emit HTML with a DSL](https://ktor.io/servers/features/templates/html-dsl.html)
         */
        val htmlBuilder = module("ktor-html-builder")

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Type-safe Routing](https://ktor.io/servers/features/locations.html)
         */
        val locations = module("ktor-locations")

        /**
         * ktor doc: [kotlinx.serialization](https://ktor.io/docs/kotlin-serialization.html)
         */
        val serialization = module("ktor-serialization")

        /**
         * Supports JVM only (because Dropwizard is JVM-only).
         *
         * ktor doc: [Metrics with Dropwizard metrics](https://ktor.io/servers/features/metrics.html)
         */
        val metrics = module("ktor-metrics")

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
    }

    /**
     * As of ktor 1.3.0, ktor server artifacts support only the JVM.
     */
    val server = Server

    object Server : IsNotADependency {

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
