@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import org.gradle.api.Incubating

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
@Incubating
object Ktor {

    val client = Client

    /**
     * As of ktor 1.3.0, ktor server artifacts support only the JVM.
     */
    val server = Server

    val features = Features
    val network = Network

    private const val group = "io.ktor"
    private const val artifactBase = "$group:ktor"

    const val http = "$artifactBase-http:_"

    const val httpCio = "$artifactBase-http-cio:_"

    const val io = "$artifactBase-io:_"

    const val testDispatcher = "$artifactBase-test-dispatcher:_"

    const val utils = "$artifactBase-utils:_"

    object Client {
        private const val artifactPrefix = "$artifactBase-client"

        const val core = "$artifactPrefix-core:_"

        /**
         * As of ktor 1.3.0, this is experimental and supports JVM only.
         *
         * CIO stands for **Coroutines I/O**.
         */
        const val cio = "$artifactPrefix-cio:_"

        /** Supports JVM and Android because OkHttp supports only the JVM as of version 4. */
        const val okHttp = "$artifactPrefix-okhttp:_"

        /** Supports JVM only because Jetty is JVM only. */
        const val jetty = "$artifactPrefix-jetty:_"

        /** Supports iOS, macOS, watchOS and tvOS. */
        const val darwin = "$artifactPrefix-ios:_" // Named ios but actually supports watchOS, tvOS and macOS too.

        @Deprecated("The artifact supports more than just iOS.", ReplaceWith("darwin"))
        const val ios = darwin

        /** Supports Linux X64, Windows X64 and macOS. */
        const val curl = "$artifactPrefix-curl:_"

        /** Supports JVM only because Apache dependency is JVM only. */
        const val apache = "$artifactPrefix-apache:_"

        const val auth = "$artifactPrefix-auth:_"

        const val authBasic = "$artifactPrefix-auth-basic:_"

        const val json = "$artifactPrefix-json:_"


        /** Supports JVM only. */
        const val jsonTests = "$artifactPrefix-json-tests:_"

        const val encoding = "$artifactPrefix-encoding:_"

        const val logging = "$artifactPrefix-logging:_"

        const val mock = "$artifactPrefix-mock:_"

        const val serialization = "$artifactPrefix-serialization:_"

        const val tests = "$artifactPrefix-tests:_"

        const val websockets = "$artifactPrefix-websockets:_"

        @Deprecated("Use OkHttp or cio", ReplaceWith("okHttp"), DeprecationLevel.ERROR)
        const val android = "$artifactPrefix-android:_"
    }

    object Features {
        /** As of ktor 1.3.0, supports JVM only. */
        const val auth = "$artifactBase-auth:_"

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * JWT stands for **JSON Web Tokens**.
         *
         * ktor doc: [JWT and JWK authentication](https://ktor.io/servers/features/authentication/jwt.html)
         */
        const val authJwt = "$artifactBase-auth-jwt:_"

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * LDAP stands for **Lightweight Directory Access Protocol**.
         *
         * ktor doc: [LDAP authentication](https://ktor.io/servers/features/authentication/ldap.html).
         */
        const val authLdap = "$artifactBase-auth-ldap:_"

        /**
         * Supports JVM only (because Apache FreeMarker is JVM-only).
         *
         * ktor doc: [Using Freemarker Templates](https://ktor.io/servers/features/templates/freemarker.html)
         */
        const val freemarker = "$artifactBase-freemarker:_"

        /**
         * Supports JVM only (because Apache Velocity JVM-only).
         *
         * ktor doc: [Using Velocity Templates](https://ktor.io/servers/features/templates/velocity.html)
         */
        const val velocity = "$artifactBase-velocity:_"

        /**
         * Supports JVM only (because Gson is JVM-only).
         *
         * ktor doc: [JSON support using Gson](https://ktor.io/servers/features/content-negotiation/gson.html)
         */
        const val gson = "$artifactBase-gson:_"

        /**
         * Supports JVM only (because Jackson is JVM-only).
         *
         * ktor doc: [JSON support using Jackson](https://ktor.io/servers/features/content-negotiation/jackson.html)
         */
        const val jackson = "$artifactBase-jackson:_"

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Emit HTML with a DSL](https://ktor.io/servers/features/templates/html-dsl.html)
         */
        const val htmlBuilder = "$artifactBase-html-builder:_"

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Type-safe Routing](https://ktor.io/servers/features/locations.html)
         */
        const val locations = "$artifactBase-locations:_"

        /**
         * Supports JVM only (because Dropwizard is JVM-only).
         *
         * ktor doc: [Metrics with Dropwizard metrics](https://ktor.io/servers/features/metrics.html)
         */
        const val metrics = "$artifactBase-metrics:_"

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
        const val serverSessions = "$artifactBase-server-sessions:_"
    }

    /**
     * As of ktor 1.3.0, ktor server artifacts support only the JVM.
     */
    object Server {
        private const val artifactPrefix = "$artifactBase-server"

        /**
         * Core package where most of the application API and implementation is located.
         */
        const val core = "$artifactPrefix-core:_"

        /**
         * Supports a deployed or embedded Jetty instance.
         */
        const val jetty = "$artifactPrefix-jetty:_"

        /**
         * Supports Netty in embedded mode.
         */
        const val netty = "$artifactPrefix-netty:_"

        /**
         * Supports Tomcat servers.
         */
        const val tomcat = "$artifactPrefix-tomcat:_"

        /**
         * Used by Jetty and Tomcat and allows running in a generic servlet container.
         */
        const val servlet = "$artifactPrefix-servlet:_"

        /**
         * Allows running application tests faster without starting the full host.
         */
        const val testHost = "$artifactPrefix-test-host:_"
    }

    object Network {
        private const val artifactPrefix = "$artifactBase-network"

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Raw sockets](https://ktor.io/servers/raw-sockets.html)
         */
        const val network = "$artifactPrefix:_"

        /**
         * As of ktor 1.3.0, supports JVM only.
         *
         * ktor doc: [Raw sockets](https://ktor.io/servers/raw-sockets.html)
         */
        const val tls = "$artifactPrefix-tls:_"

        /**
         * [KDoc here](https://api.ktor.io/latest/io.ktor.network.tls.certificates/).
         */
        const val tlsCertificates = "$artifactPrefix-tls-certificates:_"
    }
}
