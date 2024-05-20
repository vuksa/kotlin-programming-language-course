package org.jetbrains.exercise1.task4

/**
 * Task 4: Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * Example 1:
 *```
 * Input: x = 121
 * Output: true
 * ```
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 *```
 * Input: x = -121
 * Output: false
 * ```
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore, it is not a palindrome.
 * Example 3:
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore, it is not a palindrome.
 *
 * Constraints:
 * ```
 * x in -1_000_000..1_000_000
 * ```
 */

internal fun isPalindrome(x: Int): Boolean {
    val numAsString = x.toString()
    for (index in 0..numAsString.lastIndex / 2) {
        if (numAsString[index] != numAsString[numAsString.lastIndex - index]) {
            return false
        }
    }
    return true
}

private fun isPalindromeSolutionTwo(x: Int): Boolean = x.toString() == x.toString().reversed()

fun main() {
    val number = 121
    if (isPalindrome(number)) {
        println("Number $number is a palindrome.")
    } else {
        println("Number $number is not a palindrome.")
    }
}