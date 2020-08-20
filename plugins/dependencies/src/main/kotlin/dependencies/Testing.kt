/*
 * Copyright 2019 Louis Cognault Ayeva Derman. Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating

@Incubating
object Testing {

    /**
     * JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
     *
     * Official website: [junit.org/junit4](https://junit.org/junit4/)
     */
    const val junit4 = "junit:junit:_"

    val junit = JunitJupiter
    
    val kotest = Kotest

    val spek = Spek
    val strikt = Strikt

    val mockK = MockK
    val mockito = Mockito

    /**
     * Run unit tests in the JVM with the Android environment.
     *
     * GitHub page: [robolectric/robolectric](https://github.com/robolectric/robolectric)
     */
    const val roboElectric = "org.robolectric:robolectric:_"

    /**
     * The new major version of the programmer-friendly testing framework for Java
     *
     * Official website: [junit.org/junit5](https://junit.org/junit5/)
     */
    object JunitJupiter : DependencyNotationAndGroup(group = "org.junit.jupiter", name = "junit-jupiter") {
        @JvmField val api = "$artifactPrefix-api:_"
        @JvmField val engine = "$artifactPrefix-engine:_"
        @JvmField val params = "$artifactPrefix-params:_"
        @JvmField val migrationSupport = "$artifactPrefix-migrationsupport:_"
    }

    /**
     * Powerful, elegant and flexible test framework for Kotlin
     *
     * GitHub page: [kotest/kotest](https://github.com/kotest/kotest)
     */
    object Kotest {
        private const val artifactBase = "io.kotest:kotest"

        val runner = Runner
        val plugins = Plugins
        val extensions = Extensions
        val assertions = Assertions

        const val core = "$artifactBase-core:_"
        const val property = "$artifactBase-property:_"
        const val propertyArrow = "$artifactBase-property-arrow:_"    

        object Runner {
            private const val artifactPrefix = "$artifactBase-runner"

            const val junit4 = "$artifactPrefix-junit4:_"
            const val junit5 = "$artifactPrefix-junit5:_"
        }

        object Plugins {
            private const val artifactPrefix = "$artifactBase-plugins"

            const val piTest = "$artifactPrefix-pitest:_"
        }

        object Extensions {
            private const val artifactPrefix = "$artifactBase-extensions"

            const val spring = "$artifactPrefix-spring:_"
            const val koin = "$artifactPrefix-koin:_"
            const val allure = "$artifactPrefix-allure:_"
            const val testContainers = "$artifactPrefix-testcontainers:_"
            const val http = "$artifactPrefix-http:_"
            const val mockServer = "$artifactPrefix-mockserver:_"
        }

        object Assertions {
            private const val artifactPrefix = "$artifactBase-assertions"
            
            const val core = "$artifactPrefix-core:_"
            const val ktor = "$artifactPrefix-ktor:_"
            const val json = "$artifactPrefix-json:_"
            const val arrow = "$artifactPrefix-arrow:_"
            const val konform = "$artifactPrefix-konform:_"
            const val jsoup = "$artifactPrefix-jsoup:_"
            const val klock = "$artifactPrefix-klock:_"
            const val sql = "$artifactPrefix-sql:_"
            const val compiler = "$artifactPrefix-compiler:_"
        }
    }    
    
    /**
     * A specification framework for Kotlin
     *
     * Official website :[spekframework.org](https://www.spekframework.org)
     * GitHub page: [spekframework/spek](https://github.com/spekframework/spek/)
     */
    object Spek {
        private const val artifactBase = "org.spekframework.spek2:spek"

        val dsl = Dsl
        val runner = Runner
        val runtime = Runtime

        object Dsl {
            private const val artifactPrefix = "$artifactBase-dsl"

            const val jvm = "$artifactPrefix-jvm:_"
            const val js = "$artifactPrefix-js:_"
            const val metadata = "$artifactPrefix-metadata:_"

            val native = Native

            object Native {
                private const val prefix = "$artifactPrefix-native"
                const val linux = "$prefix-linux:_"
                const val macos = "$prefix-macos:_"
                const val windows = "$prefix-windows:_"
            }
        }

        object Runner {
            private const val artifactPrefix = "$artifactBase-runner"

            const val junit5 = "$artifactPrefix-junit5:_"
        }

        object Runtime {
            private const val artifactPrefix = "$artifactBase-runtime"

            const val jvm = "$artifactPrefix-jvm:_"
            const val metadata = "$artifactPrefix-metadata:_"
        }
    }

    /**
     * Strikt is an assertion library for Kotlin intended for use with a test runner such as JUnit or Spek.
     *
     * Official website: [strikt.io](https://strikt.io)
     *
     * GitHub page: [robfletcher/strikt](https://github.com/robfletcher/strikt)
     */
    object Strikt {
        private const val artifactPrefix = "io.strikt:strikt"

        const val bom = "$artifactPrefix-bom:_"
        const val core = "$artifactPrefix-core:_"
        const val arrow = "$artifactPrefix-arrow:_"
        const val gradle = "$artifactPrefix-gradle:_"
        const val jackson = "$artifactPrefix-jackson:_"
        const val javaTime = "$artifactPrefix-java-time:_"
        const val mockk = "$artifactPrefix-mockk:_"
        const val protobuf = "$artifactPrefix-protobuf:_"
        const val spring = "$artifactPrefix-spring:_"
    }

    /**
     * Mocking library for Kotlin
     *
     * Official website: [mockk.io](http://mockk.io)
     *
     * GitHub page: [mockk/mockk](https://github.com/mockk/mockk).
     */
    object MockK : DependencyNotationAndGroup(group = "io.mockk", name = "mockk") {
        @JvmField val android = "$artifactPrefix-android:_"
        @JvmField val common = "$artifactPrefix-common:_"
    }

    /**
     * Tasty mocking framework for unit tests in Java
     *
     * Official website: [site.mockito.org](https://site.mockito.org)
     *
     * GitHub page: [mockito/mockito](https://github.com/mockito/mockito).
     */
    object Mockito {

        const val core = "org.mockito:mockito-core:_"
        const val android = "org.mockito:mockito-android:_"
        const val inline = "org.mockito:mockito-inline:_"
        const val errorProne = "org.mockito:mockito-errorprone:_"
        const val junitJupiter = "org.mockito:mockito-junit-jupiter:_"

        /**
         * Using Mockito with Kotlin
         * [More info here](https://github.com/nhaarman/mockito-kotlin)
         */
        const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:_"
    }
}
