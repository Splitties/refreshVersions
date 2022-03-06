package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.KotlinScriptKind
import de.fayard.refreshVersions.core.internal.KotlinScriptKind.Delete
import de.fayard.refreshVersions.core.internal.KotlinScriptKind.Dependency
import de.fayard.refreshVersions.core.internal.KotlinScriptKind.Ignore
import de.fayard.refreshVersions.core.internal.KotlinScriptKind.Repository
import de.fayard.refreshVersions.core.internal.KotlinScriptLine
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe


class KotlinLineTest : FunSpec({

    test("Parsing dependencies") {
        val input = """
           @file:DependsOn("io.rsocket.kotlin:rsocket-core-jvm:0.13.0-SNAPSHOT")
           ////                                    # available:0.14.0")
           @file:Repository("https://jcenter.bintray.com")
           @file:DependsOn("io.ktor:ktor-client-okhttp:1.5.2")
           @file:DependsOn("com.squareup.wire:wire-moshi-adapter:3.4.0")
        """.trimIndent().lines()

        val expected = listOf(
            ModuleId.Maven("io.rsocket.kotlin", "rsocket-core-jvm"),
            null,
            null,
            ModuleId.Maven("io.ktor", "ktor-client-okhttp"),
            ModuleId.Maven("com.squareup.wire", "wire-moshi-adapter"),
        )

        (input.zip(expected)).forAll { (line, expected) ->
            KotlinScriptLine(line).moduleId() shouldBe expected
        }
    }

    test("Parsing repositories") {
        val input = """
           @file:DependsOn("io.rsocket.kotlin:rsocket-core-jvm:0.13.0-SNAPSHOT")
           @file:Repository("https://jcenter.bintray.com/")
           @file:Repository("https://google.com") // a nice comment
        """.trimIndent().lines()

        val expected = listOf(
            null,
            "https://jcenter.bintray.com/",
            "https://google.com/",
        )

        (input.zip(expected)).forAll { (line, expected) ->
            KotlinScriptLine(line).repository() shouldBe expected
        }
    }

    test("Parsing kind for libraries") {

        val lines = """
           #!/usr/bin/env kotlin
          
           @file:Repository("https://jcenter.bintray.com")
           @file:Repository("https://jitpack.io")
           @file:DependsOn("io.rsocket.kotlin:rsocket-core-jvm:0.13.0-SNAPSHOT")
           ////                                    # available:0.14.0")
           ////                                    # available:0.15.0")
           @file:DependsOn("io.ktor:ktor-client-okhttp:1.5.2")
           @file:DependsOn("com.squareup.wire:wire-moshi-adapter:3.4.0")
           @file:CompilerOptions("-jvm-target", "17")
          
           import com.baulsupp.cooee.p.CommandResponse
           
           fun main() { println("Hello World") }
        """.trimIndent()
            .lines()

        val expectedKinds: List<KotlinScriptKind> = listOf(
            Ignore, Ignore, Repository, Repository, Dependency, Delete, Delete,
            Dependency, Dependency, Ignore, Ignore, Ignore, Ignore, Ignore
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            KotlinScriptLine(line).kind shouldBe expectedKind
        }
    }
})

