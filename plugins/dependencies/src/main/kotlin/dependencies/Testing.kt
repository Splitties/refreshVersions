/*
 * Copyright 2019 Louis Cognault Ayeva Derman. Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Testing : IsNotADependency {

    /**
     * JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
     *
     * Official website: [junit.org/junit4](https://junit.org/junit4/)
     */
    val junit4 = DependencyNotation("junit", "junit")


    /**
     * Run unit tests in the JVM with the Android environment.
     *
     * GitHub page: [robolectric/robolectric](https://github.com/robolectric/robolectric)
     */
    val robolectric = DependencyNotation("org.robolectric", "robolectric")

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
    val junit = Junit

    object Junit : DependencyGroup(group = "org.junit") {

        val bom = module("junit-bom", isBom = true)

        val jupiter = Jupiter

        object Jupiter : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Junit,
            group = "org.junit.jupiter",
            name = "junit-jupiter"
        ) {

            val api = module("junit-jupiter-api")

            val engine = module("junit-jupiter-engine")

            val params = module("junit-jupiter-params")

            val migrationSupport = module("junit-jupiter-migrationsupport")
        }
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

    object Kotest : DependencyGroup("io.kotest") {


        val core = module("kotest-core")
        val property = module("kotest-property")
        val propertyArrow = module("kotest-property-arrow")

        val runner = Runner

        object Runner : IsNotADependency {

            val junit4 = module("kotest-runner-junit4")
            val junit5 = module("kotest-runner-junit5")
        }

        val plugins = Plugins

        object Plugins : IsNotADependency {

            val piTest = module("kotest-plugins-pitest")
        }


        val framework = Framework

        object Framework : IsNotADependency {

            val api = module("kotest-framework-api")
            val datatest = module("kotest-framework-datatest")
        }

        val extensions = Extensions

        object Extensions : DependencyGroup("io.kotest.extensions") {

            val pitest = module("kotest-extensions-pitest")
            val embeddedKafka = module("kotest-extensions-embedded-kafka")
            val robolectric = module("kotest-extensions-robolectric")
            val wiremock = module("kotest-extensions-wiremock")
            val gherkin = module("kotest-extensions-gherkin")
            val allure = module("kotest-extensions-allure")
            val koin = module("kotest-extensions-koin")
            val mockServer = module("kotest-extensions-mockserver")
            val spring = module("kotest-extensions-spring")
            val testContainers = module("kotest-extensions-testcontainers")

            val property = Property

            object Property : DependencyGroup(group = group) {
                val datetime = module("kotest-property-datetime")
                val arbs = module("kotest-property-arbs")
            }
        }

        val assertions = Assertions

        object Assertions : IsNotADependency {

            val arrow = module("kotest-assertions-arrow")
            val compiler = module("kotest-assertions-compiler")
            val core = module("kotest-assertions-core")
            val json = module("kotest-assertions-json")
            val jsoup = module("kotest-assertions-jsoup")
            val klock = module("kotest-assertions-klock")
            val konform = module("kotest-assertions-konform")
            val kotlinxDateTime = module("kotest-assertions-kotlinx-time")
            val ktor = module("kotest-assertions-ktor")
            val sql = module("kotest-assertions-sql")
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

    object Spek : DependencyGroup("org.spekframework.spek2") {

        val dsl = Dsl

        object Dsl : IsNotADependency {

            val jvm = module("spek-dsl-jvm")
            val js = module("spek-dsl-js")
            val metadata = module("spek-dsl-metadata")

            val native = Native

            object Native : IsNotADependency {
                val linux = module("spek-dsl-native-linux")
                val macos = module("spek-dsl-native-macos")
                val windows = module("spek-dsl-native-windows")
            }
        }

        val runner = Runner

        object Runner : IsNotADependency {

            val junit5 = module("spek-runner-junit5")
        }

        val runtime = Runtime

        object Runtime : IsNotADependency {

            val jvm = module("spek-runtime-jvm")
            val metadata = module("spek-runtime-metadata")
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

    object Strikt : DependencyGroup("io.strikt") {

        val bom = module("strikt-bom")
        val core = module("strikt-core")
        val arrow = module("strikt-arrow")
        val gradle = module("strikt-gradle")
        val jackson = module("strikt-jackson")
        val javaTime = module("strikt-java-time")
        val mockk = module("strikt-mockk")
        val protobuf = module("strikt-protobuf")
        val spring = module("strikt-spring")
    }

    /**
     * Mocking library for Kotlin.
     *
     * Official website: [mockk.io](https://mockk.io/)
     *
     * [GitHub releases](https://github.com/mockk/mockk/releases)
     *
     * GitHub page: [mockk/mockk](https://github.com/mockk/mockk)
     */
    val mockK = MockK

    object MockK : DependencyNotationAndGroup(group = "io.mockk", name = "mockk") {
        val android = module("mockk-android")
        val common = module("mockk-common")
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

    object Mockito : DependencyGroup("org.mockito") {

        val core = module("mockito-core")
        val android = module("mockito-android")
        val inline = module("mockito-inline")
        val errorProne = module("mockito-errorprone")
        val junitJupiter = module("mockito-junit-jupiter")

        /**
         * Using Mockito with Kotlin
         * [More info here](https://github.com/nhaarman/mockito-kotlin)
         */
        val kotlin = DependencyNotation("com.nhaarman.mockitokotlin2", "mockito-kotlin")
    }

    /**
     * AssertJ provides a rich and intuitive set of strongly-typed assertions to use for unit testing
     *
     * Official website: [assertj.github.io](https://assertj.github.io/doc/)
     *
     * GitHub page: [assertj/assertj-core](https://github.com/assertj/assertj-core)
     */
    val assertj = AssertJ

    object AssertJ : DependencyGroup(
        group = "org.assertj",
        rawRules = """
            org.assertj:assertj-*
                        ^^^^^^^.^
        """.trimIndent()
    ) {

        val core = module("assertj-core")
        val guava = module("assertj-guava")
        val jodaTime = module("assertj-joda-time")
        val db = module("assertj-db")
        val swing = module("assertj-swing")
    }

    /**
     * Matchers that can be combined to create flexible expressions of intent in tests
     *
     * Official website: [hamcrest.org](http://hamcrest.org/JavaHamcrest/)
     *
     * [GitHub releases](https://github.com/hamcrest/JavaHamcrest/releases)
     *
     * GitHub page: [hamcrest/JavaHamcrest](https://github.com/hamcrest/JavaHamcrest)
     *
     * [API reference (JavaDoc)](http://hamcrest.org/JavaHamcrest/javadoc/)
     */
    val hamcrest = Hamcrest

    object Hamcrest : DependencyNotationAndGroup(
        group = "org.hamcrest",
        name = "hamcrest",
        rawRules = """
            org.hamcrest:hamcrest(-*)
                ^^^^^^^^
        """.trimIndent()
    ) {

        val core = module("hamcrest-core")
        val library = module("hamcrest-library")
    }
}
