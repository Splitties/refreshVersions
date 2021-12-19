package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.AbstractDependencyGroup
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class DependencyNotationsTest {

    @Test
    fun `test kotlinx coroutines with BoM`() = runDependencyGroupTest(KotlinX.coroutines) {
        KotlinX.coroutines.bom.toString()
        KotlinX.coroutines.core.toString() shouldBe "org.jetbrains.kotlinx:kotlinx-coroutines-core"
    }

    @Test
    fun `test kotlinx coroutines without BoM`() = runDependencyGroupTest(KotlinX.coroutines) {
        KotlinX.coroutines.core.toString() shouldBe "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
    }

    @Test
    fun `test JUnit with BoM`() = runDependencyGroupTest(Testing.junit, Testing.junit.jupiter) {
        Testing.junit.bom.toString()
        Testing.junit.jupiter.toString() shouldBe "org.junit.jupiter:junit-jupiter"
        Testing.junit.jupiter.api.toString() shouldBe "org.junit.jupiter:junit-jupiter-api"
    }

    @Test
    fun `test JUnit without BoM`() = runDependencyGroupTest(Testing.junit, Testing.junit.jupiter) {
        Testing.junit.jupiter.toString() shouldBe "org.junit.jupiter:junit-jupiter:_"
        Testing.junit.jupiter.api.toString() shouldBe "org.junit.jupiter:junit-jupiter-api:_"
    }

    @Test
    fun `unrelated groups should not affect each other`() {
        runDependencyGroupTest(Testing.junit, Testing.junit.jupiter, KotlinX.coroutines) {
            Testing.junit.bom.toString()
            Testing.junit.jupiter.toString() shouldBe "org.junit.jupiter:junit-jupiter"
            KotlinX.coroutines.core.toString() shouldBe "org.jetbrains.kotlinx:kotlinx-coroutines-core:_"
        }
        runDependencyGroupTest(Testing.junit, Testing.junit.jupiter, KotlinX.coroutines) {
            KotlinX.coroutines.bom.toString()
            KotlinX.coroutines.core.toString() shouldBe "org.jetbrains.kotlinx:kotlinx-coroutines-core"
            Testing.junit.jupiter.toString() shouldBe "org.junit.jupiter:junit-jupiter:_"
            Testing.junit.jupiter.api.toString() shouldBe "org.junit.jupiter:junit-jupiter-api:_"
        }
    }

    private inline fun runDependencyGroupTest(
        vararg groups: AbstractDependencyGroup,
        block: () -> Unit
    ): Unit = try {
        block()
    } finally {
        groups.forEach { it.reset() }
    }
}
