@file:JvmName("GuavaTest")

package de.fayard

import com.google.common.math.DoubleMath
import included
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GuavaTest {
    @Test
    fun test() {
        val mean = DoubleMath.mean(4, 2)
        mean shouldBe 3
        println("Guava says: mean(4, 2)=$mean")

        println(included())
//        println(includedSubproject2())
    }
}
