package org.jetbrains.exercise1.task5

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
    if(x !in -1_000_000..1_000_000) throw IllegalArgumentException("x in the invalid range!")
    var res = 1
    var number = x

    if(x < 0) {
        res *= -1
        number *= -1
    }
    res *= number.toString().reversed().toInt()
    return res
}

fun main() {
    val integer = -321
    println("Reverse integer of number $integer is ${reverseInteger(integer)}")
}
