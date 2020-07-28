@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object Square {

    val okHttp3 = OkHttp3
    val retrofit2 = Retrofit2
    val sqlDelight = SqlDelight
    val moshi = Moshi
    val wire = Wire
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

        const val android = "$group:leakcanary-android:_"
        const val androidProcess = "$group:leakcanary-android-process:_"

        const val androidInstrumentation = "$group:leakcanary-android-instrumentation:_"

        const val plumber = "$group:plumber-android:_"

        const val objectWatcher = "$group:leakcanary-object-watcher-android:_"

        val shark = Shark

        object Shark {
            private const val artifactPrefix = "$group:shark"

            const val hprof = "$artifactPrefix-hprof:_"
            const val graph = "$artifactPrefix-graph:_"
            const val shark = "$artifactPrefix:_"
            const val android = "$artifactPrefix-android:_"
            const val cli = "$artifactPrefix-cli:_"
        }
    }
}
