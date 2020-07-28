@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object Square {

    val okHttp3 = OkHttp3
    val retrofit2 = Retrofit2
    val sqlDelight = SqlDelight
    val moshi = Moshi
    val wire = Wire

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

    const val okio = "com.squareup.okio:okio:_"

    object OkHttp3 {
        private const val group = "com.squareup.okhttp3"
        const val okHttp = "$group:okhttp:_"
        const val loggingInterceptor = "$group:logging-interceptor:_"
        const val mockWebServer = "$group:mockwebserver:_"
    }

    object Retrofit2 {
        private const val group = "com.squareup.retrofit2"

        const val retrofit = "$group:retrofit:_"
        const val mock = "$group:retrofit-mock:_"

        val converter = Converter
        val adapter = Adapter

        object Converter {
            private const val artifactPrefix = "$group:converter"

            const val scalars = "$artifactPrefix-scalars:_"

            const val moshi = "$artifactPrefix-moshi:_"
            const val gson = "$artifactPrefix-gson:_"
            const val jackson = "$artifactPrefix-jackson:_"

            const val simpleXml = "$artifactPrefix-simplexml:_"
        }

        object Adapter {
            const val retrofitJava8 = "$group:adapter-java8:_"
            const val retrofitRxJava1 = "$group:adapter-rxjava:_"
            const val retrofitRxJava2 = "$group:adapter-rxjava2:_"
        }
    }

    object SqlDelight {
        private const val group = "com.squareup.sqldelight"

        const val gradlePlugin = "$group:gradle-plugin:_"

        val drivers = Drivers

        const val coroutinesExtensions = "$group:coroutines-extensions"

        object Drivers {
            const val android = "$group:android-driver:_"

            const val jdbc = "$group:jdbc-driver:_"
            const val jdbcSqlite = "$group:sqlite-driver:_"

            const val native = "$group:native-driver:_"
        }
    }

    object Moshi {
        private const val group = "com.squareup.moshi"

        const val javaReflect = "$group:moshi:_"
        const val kotlinReflect = "$group:moshi-kotlin:_"
        const val kotlinCodegen = "$group:moshi-kotlin-codegen:_"
    }

    object Wire {
        private const val group = "com.squareup.wire"

        const val runtime = "$group:wire-runtime:_"
    }

    object LeakCanary {
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

        val objectWatcher = ObjectWatcher

        object ObjectWatcher {
            private const val artifactPrefix = "$group:leakcanary-object-watcher"

            /**
             * [Code recipe](https://square.github.io/leakcanary/recipes/#counting-retained-instances-in-release-builds)
             *
             * [API reference](https://square.github.io/leakcanary/api/leakcanary-object-watcher-android/leakcanary/)
             */
            const val android = "$artifactPrefix-android:_"

            /**
             * [API reference](https://square.github.io/leakcanary/api/leakcanary-object-watcher/leakcanary/)
             */
            const val core = "$artifactPrefix:_"
        }

        /**
         * Shark: Smart Heap Analysis Reports for Kotlin
         *
         * [Official documentation](https://square.github.io/leakcanary/shark/)
         *
         * [Announcement blog post](https://developer.squareup.com/blog/announcing-shark-smart-heap-analysis-reports-for-kotlin/)
         */
        val shark = Shark

        object Shark {
            private const val artifactPrefix = "$group:shark"

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark-hprof/shark/)
             */
            const val hprof = "$artifactPrefix-hprof:_"

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark-graph/shark/)
             */
            const val graph = "$artifactPrefix-graph:_"

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark/shark/)
             *
             * [Logging API reference](https://square.github.io/leakcanary/api/shark-log/shark/)
             */
            const val shark = "$artifactPrefix:_"

            /**
             * [API reference](https://square.github.io/leakcanary/api/shark-android/shark/)
             */
            const val android = "$artifactPrefix-android:_"

            /**
             * [Official documentation](https://square.github.io/leakcanary/shark/#shark-cli)
             */
            const val cli = "$artifactPrefix-cli:_"
        }
    }
}
