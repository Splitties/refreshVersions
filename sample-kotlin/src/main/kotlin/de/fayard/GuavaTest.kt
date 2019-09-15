@file:JvmName("GuavaTest")

package de.fayard

import com.google.common.math.DoubleMath

fun main() {
    val mean = DoubleMath.mean(4, 2)
    println("Guava says: mean(4, 2)=$mean")
}
