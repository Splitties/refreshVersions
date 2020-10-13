/*
 * Copyright 2019 Louis Cognault Ayeva Derman. Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Testing {

    /**
     * JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
     *
     * Official website: [junit.org/junit4](https://junit.org/junit4/)
     */
    const val junit4 = "junit:junit:_"


    /**
     * Run unit tests in the JVM with the Android environment.
     *
     * GitHub page: [robolectric/robolectric](https://github.com/robolectric/robolectric)
     */
    const val roboElectric = "org.robolectric:robolectric:_"


    /**
     * JUnit 5: The new major version of the programmer-friendly testing framework for Java
     *
     * Official website: [junit.org/junit5](https://junit.org/junit5/)
     *
     * [User Guide](https://junit.org/junit5/docs/current/user-guide/)
     *
     * [Release Notes](https://junit.org/junit5/docs/current/release-notes/)
     *
     * GitHub page: [junit-team/junit5](https://github.com/junit-team/junit5)
     *
     * [API reference (JavaDoc)](https://junit.org/junit5/docs/current/api/)
     */
    val junit = JunitJupiter

    object JunitJupiter : DependencyNotationAndGroup(group = "org.junit.jupiter", name = "junit-jupiter") {
        @JvmField val api = "$artifactPrefix-api:_"
        @JvmField val engine = "$artifactPrefix-engine:_"
        @JvmField val params = "$artifactPrefix-params:_"
        @JvmField val migrationSupport = "$artifactPrefix-migrationsupport:_"
    }

    /**
     * Powerful, elegant and flexible test framework for Kotlin
     *
     * Official website: [kotest.io](https://kotest.io/)
     *
     * [Change log](https://kotest.io/changelog/)
     *
     * GitHub page: [kotest/kotest](https://github.com/kotest/kotest)
     */
    val kotest = Kotest

    object Kotest : IsNotADependency {
        private const val artifactBase = "io.kotest:kotest"


        const val core = "$artifactBase-core:_"
        const val property = "$artifactBase-property:_"
        const val propertyArrow = "$artifactBase-property-arrow:_"
        const val propertyKotlinxDateTime = "$artifactPrefix-property-datetime:_"

        val runner = Runner

        object Runner : IsNotADependency {
            private const val artifactPrefix = "$artifactBase-runner"

            const val junit4 = "$artifactPrefix-junit4:_"
            const val junit5 = "$artifactPrefix-junit5:_"
        }

        val plugins = Plugins

        object Plugins : IsNotADependency {
            private const val artifactPrefix = "$artifactBase-plugins"

            const val piTest = "$artifactPrefix-pitest:_"
        }

        val extensions = Extensions

        object Extensions : IsNotADependency {
            private const val artifactPrefix = "$artifactBase-extensions"

            const val spring = "$artifactPrefix-spring:_"
            const val koin = "$artifactPrefix-koin:_"
            const val allure = "$artifactPrefix-allure:_"
            const val testContainers = "$artifactPrefix-testcontainers:_"
            const val http = "$artifactPrefix-http:_"
            const val mockServer = "$artifactPrefix-mockserver:_"
        }

        val assertions = Assertions

        object Assertions : IsNotADependency {
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
            const val kotlinxDateTime = "$artifactPrefix-kotlinx-time:_"
        }
    }

    /**
     * A specification framework for Kotlin
     *
     * Official website: [spekframework.org](https://www.spekframework.org/)
     *
     * GitHub page: [spekframework/spek](https://github.com/spekframework/spek)
     *
     * [GitHub releases](https://github.com/spekframework/spek/releases)
     */
    val spek = Spek

    object Spek : IsNotADependency {
        private const val artifactBase = "org.spekframework.spek2:spek"

        val dsl = Dsl

        object Dsl : IsNotADependency {
            private const val artifactPrefix = "$artifactBase-dsl"

            const val jvm = "$artifactPrefix-jvm:_"
            const val js = "$artifactPrefix-js:_"
            const val metadata = "$artifactPrefix-metadata:_"

            val native = Native

            object Native : IsNotADependency {
                private const val prefix = "$artifactPrefix-native"
                const val linux = "$prefix-linux:_"
                const val macos = "$prefix-macos:_"
                const val windows = "$prefix-windows:_"
            }
        }

        val runner = Runner

        object Runner : IsNotADependency {
            private const val artifactPrefix = "$artifactBase-runner"

            const val junit5 = "$artifactPrefix-junit5:_"
        }

        val runtime = Runtime

        object Runtime : IsNotADependency {
            private const val artifactPrefix = "$artifactBase-runtime"

            const val jvm = "$artifactPrefix-jvm:_"
            const val metadata = "$artifactPrefix-metadata:_"
        }
    }

    /**
     * Strikt is an assertion library for Kotlin intended for use with a test runner such as JUnit or Spek.
     *
     * Official website: [strikt.io](https://strikt.io/)
     *
     * [Change log](https://strikt.io/changelog/)
     *
     * [GitHub releases](https://github.com/robfletcher/strikt/releases)
     *
     * GitHub page: [robfletcher/strikt](https://github.com/robfletcher/strikt)
     */
    val strikt = Strikt

    object Strikt : IsNotADependency {
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
     * Mocking library for Kotlin.
     *
     * Official Website: [mockk.io](https://mockk.io/)
     *
     * [GitHub releases](https://github.com/mockk/mockk/releases)
     *
     * GitHub page: [mockk/mockk](https://github.com/mockk/mockk)
     */
    val mockK = MockK

    object MockK : DependencyNotationAndGroup(group = "io.mockk", name = "mockk") {
        @JvmField val android = "$artifactPrefix-android:_"
        @JvmField val common = "$artifactPrefix-common:_"
    }

    /**
     * Most popular Mocking framework for unit tests written in Java
     *
     * Official website: [mockito.org](https://site.mockito.org/)
     *
     * [Changelog for versions 3.x](https://github.com/mockito/mockito/blob/release/3.x/doc/release-notes/official.md)
     *
     * [GitHub releases](https://github.com/mockito/mockito/releases)
     *
     * [Using Mockito with Kotlin](https://github.com/nhaarman/mockito-kotlin) (third party project)
     *
     * GitHub page: [mockito/mockito](https://github.com/mockito/mockito)
     */
    val mockito = Mockito

    object Mockito : IsNotADependency {
        private const val artifactPrefix = "org.mockito:mockito"

        const val core = "$artifactPrefix-core:_"
        const val android = "$artifactPrefix-android:_"
        const val inline = "$artifactPrefix-inline:_"
        const val errorProne = "$artifactPrefix-errorprone:_"
        const val junitJupiter = "$artifactPrefix-junit-jupiter:_"

        /**
         * Using Mockito with Kotlin
         * [More info here](https://github.com/nhaarman/mockito-kotlin)
         */
        const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:_"
    }
}
