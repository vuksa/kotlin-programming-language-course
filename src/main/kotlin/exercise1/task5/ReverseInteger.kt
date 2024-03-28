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
    require(x in -1000000..1000000) { "Number must be integer in range -1000000..1000000" }
    return if (x < 0) ("-" + abs(x).toString().reversed()).toInt() else x.toString().reversed().toInt()
}
fun main() {
    val integer = -78952
    println("Reverse integer of number $integer is ${reverseInteger(integer)}")
}
