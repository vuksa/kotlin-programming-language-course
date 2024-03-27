package org.jetbrains.exercise1.task5

import kotlin.math.abs

/**
 * Task 5: Given an integer x, return x with its digits reversed.
 *
 * Constraints:
 *```
 * x in -1_000_000..1_000_000
 * ```
 *
 * Example 1:
 *```
 * Input: x = 123
 * Output: 321
 * ```
 * Example 2:
 *```
 * Input: x = -123
 * Output: -321
 * ```
 * Example 3:
 *```
 * Input: x = 120
 * Output: 21
 *```
 *
 */

internal fun reverseInteger(x: Int): Int {
    require(x in -1_000_000..1_000_000)
    var input = x
    var reversed = 0

    while (input != 0) {
        val digit = input % 10
        reversed = reversed * 10 + digit
        input /= 10
    }

    return reversed
}

private fun reverseIntegerSolutionTwo(x: Int): Int {
    require(x in -1_000_000..1_000_000)
    val coefficient = if (x < 0) -1 else 1
    return abs(x).toString().reversed().toInt() * coefficient
}

fun main() {
    val integer = -321
    println("Reverse integer of number $integer is ${reverseInteger(integer)}")
}
