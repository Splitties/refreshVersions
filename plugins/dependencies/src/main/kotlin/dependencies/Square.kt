@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Square {

    /**
     * A modern I/O library for Kotlin Multiplatform (JVM/Android, Linux, iOS, macOS, JS) and Java.
     *
     * Official website: [square.github.io/okio](https://square.github.io/okio/)
     *
     * [Change log](https://square.github.io/okio/changelog/)
     *
     * GitHub page: [square/okio](https://github.com/square/okio)
     */
    const val okio = "com.squareup.okio:okio:_"

    /**
     * Square's meticulous HTTP client for Java and Kotlin.
     *
     * Official website: [square.github.io/okhttp](https://square.github.io/okhttp/)
     *
     * [Change log](https://square.github.io/okhttp/changelog/)
     *
     * GitHub page: [square/okhttp](https://github.com/square/okhttp)
     */
    val okHttp3 = OkHttp3

    object OkHttp3 : DependencyNotationAndGroup(group = "com.squareup.okhttp3", name = "okhttp") {
        val bom = module("okhttp-bom", isBom = true)
        val okHttp = module("okhttp")
        val loggingInterceptor = module("logging-interceptor")
        val mockWebServer = module("mockwebserver")
    }

    /**
     * A type-safe HTTP client for Android and the JVM.
     *
     * Official website: [square.github.io/retrofit](https://square.github.io/retrofit/)
     *
     * [Change log](https://github.com/square/retrofit/blob/master/CHANGELOG.md)
     *
     * GitHub page: [square/retrofit](https://github.com/square/retrofit)
     *
     * [Javadoc](https://square.github.io/retrofit/2.x/retrofit/)
     */
    val retrofit2 = Retrofit2

    object Retrofit2 : DependencyNotationAndGroup(group = "com.squareup.retrofit2", name = "retrofit") {

        val retrofit = module("retrofit")
        val mock = module("retrofit-mock")

        val converter = Converter

        object Converter : IsNotADependency {

            val scalars = module("converter-scalars")

            val moshi = module("converter-moshi")
            val gson = module("converter-gson")
            val jackson = module("converter-jackson")

            val simpleXml = module("converter-simplexml")
        }

        val adapter = Adapter

        object Adapter : IsNotADependency {
            val java8 = module("adapter-java8")
            val rxJava1 = module("adapter-rxjava")
            val rxJava2 = module("adapter-rxjava2")
            val rxJava3 = module("adapter-rxjava3")
        }
    }

    /**
     * SQLDelight generates typesafe kotlin APIs from your SQL statements.
     *
     * Official Website: [cashapp.github.io/sqldelight](https://cashapp.github.io/sqldelight/)
     *
     * [Change log](https://cashapp.github.io/sqldelight/changelog/)
     *
     * GitHub page: [cashapp/sqldelight](https://github.com/cashapp/sqldelight)
     */
    val sqlDelight = SqlDelight

    object SqlDelight : IsNotADependency {
        private const val group = "com.squareup.sqldelight"

        const val gradlePlugin = "$group:gradle-plugin:_"

        const val coroutinesExtensions = "$group:coroutines-extensions:_"

        val drivers = Drivers

        object Drivers : IsNotADependency {
            const val android = "$group:android-driver:_"

            const val jdbc = "$group:jdbc-driver:_"
            const val jdbcSqlite = "$group:sqlite-driver:_"

            const val native = "$group:native-driver:_"
        }
    }

    /**
     * A modern JSON library for Kotlin and Java.
     *
     * [Change log](https://github.com/square/moshi/blob/master/CHANGELOG.md)
     *
     * GitHub page: [square/moshi](https://github.com/square/moshi)
     *
     * [Javadoc](https://square.github.io/moshi/1.x/moshi/)
     */
    val moshi = Moshi

    object Moshi : DependencyNotationAndGroup(group = "com.squareup.moshi", name = "moshi") {
        @JvmField val kotlinReflect = "$artifactPrefix-kotlin:_"
        @JvmField val kotlinCodegen = "$artifactPrefix-kotlin-codegen:_"
        val javaReflect = module("moshi")
    }


    /**
     * gRPC and protocol buffers for Android, Kotlin, and Java.
     *
     * Official Website: [square.github.io/wire](https://square.github.io/wire/)
     *
     * [Change log](https://square.github.io/wire/changelog/)
     *
     * GitHub page: [square/wire](https://github.com/square/wire)
     *
     * [Google's Protocol Buffers (aka. protobuf)](https://developers.google.com/protocol-buffers)
     */
    val wire = Wire

    object Wire : IsNotADependency {
        private const val artifactPrefix = "com.squareup.wire:wire"

        const val gradlePlugin = "$artifactPrefix-gradle-plugin:_"

        const val runtime = "$artifactPrefix-runtime:_"

        val grpc = Grpc

        object Grpc : IsNotADependency {
            const val client = "$artifactPrefix-grpc-client:_"
        }
    }

    /**
     * **LeakCanary is a memory leak detection library for Android.**
     *
     * **Official website:**
     * - [Home/Overview](https://square.github.io/leakcanary/)
     * - [Getting Started](https://square.github.io/leakcanary/getting_started/)
     * - [Fundamentals](https://square.github.io/leakcanary/fundamentals/)
     * - [Code recipes](https://square.github.io/leakcanary/recipes/)
     * - [FAQ](https://square.github.io/leakcanary/faq/)
     *
     * Browse the navigation menu on the website to see more.
     */
    val leakCanary = LeakCanary

    object LeakCanary : IsNotADependency {
        private const val group = "com.squareup.leakcanary"

        /**
         * [API reference](https://square.github.io/leakcanary/api/leakcanary-android-core/leakcanary/)
         */
        const val android = "$group:leakcanary-android:_"

        /**
         * [Code recipe](https://square.github.io/leakcanary/recipes/#running-the-leakcanary-analysis-in-a-separate-process)
         *
         * [API reference](https://square.github.io/leakcanary/api/leakcanary-android-process/leakcanary/)
         */
        const val androidProcess = "$group:leakcanary-android-process:_"

        /**
         * [Code recipe](https://square.github.io/leakcanary/recipes/#running-leakcanary-in-instrumentation-tests)
         *
         * [API reference](https://square.github.io/leakcanary/api/leakcanary-android-instrumentation/leakcanary/)
         */
        const val androidInstrumentation = "$group:leakcanary-android-instrumentation:_"

        /**
         * [Blog post "LeakCanary—Deobfuscation Feature Explained" by the contributors to this feature](https://www.polidea.com/blog/leakcanary-deobfuscation-feature-explained/)
         */
        const val deobfuscationGradlePlugin = "$group:leakcanary-deobfuscation-gradle-plugin:_"

        /**
         * [Plumber introduction in the changelog of the version 2.4](https://square.github.io/leakcanary/changelog/#version-24-2020-06-10)
         */
        const val plumber = "$group:plumber-android:_"

        /**
         * [API reference](https://square.github.io/leakcanary/api/leakcanary-object-watcher/leakcanary/)
         */
        val objectWatcher = ObjectWatcher

        object ObjectWatcher : DependencyNotationAndGroup(group = group, name = "leakcanary-object-watcher") {

            /**
             * [Code recipe](https://square.github.io/leakcanary/recipes/#counting-retained-instances-in-release-builds)
             *
             * [API reference](https://square.github.io/leakcanary/api/leakcanary-object-watcher-android/leakcanary/)
             */
            @JvmField val android = "$artifactPrefix-android:_"
        }

        /**
         * Shark: Smart Heap Analysis Reports for Kotlin
         *
         * [Official documentation](https://square.github.io/leakcanary/shark/)
         *
         * [Announcement blog post](https://developer.squareup.com/blog/announcing-shark-smart-heap-analysis-reports-for-kotlin/)
         *
         * [API reference](https://square.github.io/leakcanary/api/shark/shark/)
         *
         * [Logging API reference](https://square.github.io/leakcanary/api/shark-log/shark/)
         */
        val shark = Shark

        object Shark : DependencyNotationAndGroup(group = group, name = "shark") {

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark-hprof/shark/)
             */
            @JvmField val hprof = "$artifactPrefix-hprof:_"

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark-graph/shark/)
             */
            @JvmField val graph = "$artifactPrefix-graph:_"

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark-android/shark/)
             */
            @JvmField val android = "$artifactPrefix-android:_"

            /**
             * [Official documentation](https://square.github.io/leakcanary/shark/#shark-cli)
             */
            @JvmField val cli = "$artifactPrefix-cli:_"
        }
    }

    /**
     * `KotlinPoet` is a Kotlin and Java API for generating `.kt` source files.
     *
     * Official website: [square.github.io/kotlinpoet](https://square.github.io/kotlinpoet/)
     *
     * [Change Log](https://square.github.io/kotlinpoet/changelog/)
     *
     * GitHub page: [square/kotlinpoet](https://github.com/square/kotlinpoet)
     *
     * [API reference](https://square.github.io/kotlinpoet/1.x/kotlinpoet/)
     */
    val kotlinPoet = KotlinPoet

    object KotlinPoet : DependencyNotationAndGroup(group = "com.squareup", name = "kotlinpoet") {

        /**
         * [Official webpage](https://square.github.io/kotlinpoet/kotlinpoet_metadata/)
         *
         * [API reference](https://square.github.io/kotlinpoet/1.x/kotlinpoet-metadata/com.squareup.kotlinpoet.metadata/)
         */
        @JvmField val metadata = "$artifactPrefix-metadata:_"

        /**
         * [Official webpage](https://square.github.io/kotlinpoet/kotlinpoet_metadata_specs/)
         *
         * [API reference](https://square.github.io/kotlinpoet/1.x/kotlinpoet-metadata-specs/com.squareup.kotlinpoet.metadata.specs/)
         */
        @JvmField val metadataSpecs = "$artifactPrefix-metadata-specs:_"
    }

    /**
     * A powerful image downloading and caching library for Android.
     *
     * Official website: [square.github.io/picasso](https://square.github.io/picasso/)
     *
     * [Change log](https://github.com/square/picasso/blob/master/CHANGELOG.md)
     *
     * GitHub page: [square/picasso](https://github.com/square/picasso)
     */
    val picasso = Picasso

    object Picasso : DependencyNotationAndGroup(group = "com.squareup.picasso", name = "picasso") {

        /**
         * [Documentation page](https://github.com/square/picasso/blob/master/picasso-pollexor/README.md)
         */
        @JvmField val pollexor = "$artifactPrefix-pollexor:_"
    }
}
