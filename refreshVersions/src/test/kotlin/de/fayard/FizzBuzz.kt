package de.fayard

fun fizzbuz(i: Int) = when(i) {
    1 -> "Fizz"
    2 -> "Buzz"
    3 -> "FizzBuzz"
    else -> "Caca"
}

fun main() {
    println(fizzbuz(i = 1))
}
