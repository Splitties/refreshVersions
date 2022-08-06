package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.PrefixRule
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith

class PrefixRuleTest : FunSpec({

    context("Convert PrefixRule to rawText") {

        val rawTextRules = """
io.arrow-kt.analysis.kotlin:io.arrow-kt.analysis.kotlin(-*)
^^^^^^^^^^^^^^^^^^^^^^^^^^^

org.jetbrains.kotlinx:kotlinx-coroutines(-*)
              ^^^^^^^.        ^^^^^^^^^^

io.insert-koin:koin(-*)
          ^^^^

io.ktor:*
   ^^^^

io.arrow-kt:arrow(-*)
   ^^^^^

co.touchlab:kermit(-*)
            ^^^^^^

io.github.javaeden.orchid:*
                   ^^^^^^

org.http4k:http4k(-*)
    ^^^^^^
        """.trimIndent().split("\n\n")

        val prefixRules: List<PrefixRule> = listOf(
            PrefixRule("io.arrow-kt.analysis.kotlin", "io.arrow-kt.analysis.kotlin", "io.arrow-kt.analysis.kotlin"),
            PrefixRule("kotlinx.coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines"),
            PrefixRule("koin", "io.insert-koin", "koin"),
            PrefixRule("ktor", "io.ktor", null),
            PrefixRule("arrow", "io.arrow-kt", "arrow"),
            PrefixRule("kermit", "co.touchlab", "kermit"),
            PrefixRule("orchid", "io.github.javaeden.orchid", null),
            PrefixRule("http4k", "org.http4k", "http4k"),
        )

        test("check size") {
            prefixRules shouldHaveSize rawTextRules.size
        }

        fun Any.format() = "\n${toString()}\n"

        for (index in rawTextRules.indices) {
            test("rule #$index") {
                val rule = prefixRules[index].toRawRules()
                val expected = rawTextRules[index]
                rule.format() shouldBe expected.format()
            }
        }
    }

    context("invalid prefix rule") {
        val invalid = listOf(
            PrefixRule("kotlinx-serialization", "org.jetbrains.kotlinx"),
            PrefixRule("coroutines.kotlinx", "org.jetbrains.kotlinx", "kotlinx-coroutines"),
            PrefixRule("version.kotlinx.coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines"),
            PrefixRule(" kotlinx.coroutines ", "org.jetbrains.kotlinx", "kotlinx-coroutines"),
            PrefixRule("", "org.jetbrains.kotlinx", "kotlinx-coroutines"),
            PrefixRule("kotlinx.coroutines", "org.jetbrains.kotlinx:kotlinx", null),
            PrefixRule("kotlinx.coroutines", "io..group", "kotlinx-coroutines"),
        )

        val expectedErrors = """
PrefixRule.versionName contains words not found in the pattern: [serialization]
PrefixRule.versionName contains words not found in the pattern: [kotlinx]
PrefixRule.versionName should not start with version
PrefixRule.versionName contains spaces
PrefixRule.versionName must match regex
PrefixRule.mavenGroup is invalid
PrefixRule.mavenGroup is invalid
        """.trimIndent().lines()

        test("check size") {
            expectedErrors shouldHaveSize invalid.size
        }

        invalid.forEachIndexed { index, rule ->
            test("invalid rule #$index") {
                val message = try {
                    rule.toRawRules()
                } catch (e: Exception) {
                    e.message!!
                }
                message shouldStartWith expectedErrors[index]
            }
        }
    }

    context("regexes") {
        test("valid version name") {
            val invalid = listOf("ktor", "kotlinx", "kotlinx.coroutines", "kotlinx-serialization", "ktor2")
            invalid.forAll { shouldNotThrowAny { PrefixRule.checkValidVersion(it) } }
        }
        test("invalid version name") {
            val invalid = listOf(
                "",
                "ko",
                "version.kotlin",
                "-kotlinx",
                "kotlinx coroutines",
                "kotlinx.coroutines.",
                "**kotlin--"
            )
            invalid.forAll { shouldThrowAny { PrefixRule.checkValidVersion(it) } }
        }

    }

})

