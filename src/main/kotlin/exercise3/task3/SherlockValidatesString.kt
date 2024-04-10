package exercise3.task3

import kotlin.math.abs

/**
 * Task 3: Sherlock Validates the Words
 *
 * Sherlock considers a string to be valid if all characters of the string appear the same number of times.
 * It is also valid if he can remove just a single character at index in the string,
 * and the remaining characters will occur the same number of times.
 *
 * Given a string, determine if it is valid. If so, return `YES`, otherwise return `NO`.
 *
 * Example:
 *```
 * s = abc
 * This is a valid string because frequencies are {`a`: 1, `b`: 1, `c`: 1} .
 *
 * s = abcc
 * This is a valid string because we can remove one `c` and have `1` of each character in the remaining string.
 *
 * s = abccc
 * This string is not valid as we can only remove `1` occurrence of `c`. That leaves character frequencies of
 * {`a`: 1, `b`: 1, `c`: 2}.
 * ```
 *
 * Constraints:
 * - `s` length is in range 1..10^5
 * - Each character of `s` is in a..z range
 *
 */

internal fun isSherlockValid(s: String): String {
    val chMap = HashMap<Char, Int>()
    val freqMap = HashMap<Int, Int>()
    s.forEach {
        chMap[it] = chMap.getOrDefault(it, 0) + 1
    }
    chMap.values.forEach {frequency ->
        freqMap[frequency] = freqMap.getOrDefault(frequency, 0) + 1
    }
    // If all characters have the same frequency, then it's already valid.
    if (freqMap.size == 1) return "YES"

    // If there are more than 2 different frequencies, it's not valid.
    if (freqMap.size > 2) return "NO"

    val (first, second) = freqMap.keys.toList()
    println("$first $second")
    // If there's only one character with a frequency different from others by 1, then it's valid.
    if (freqMap[first] == 1 && first == 1 || freqMap[second] == 1 && abs(first - second) == 1) return "YES"

    return "NO"
}

fun main() {
    val stringsToValidityCatalog = mapOf("abc" to "YES", "abcc" to "YES", "abccc" to "NO")

    stringsToValidityCatalog.forEach { (string, expectedIsValid) ->
        val actualIsValid = isSherlockValid(string)
        println(actualIsValid)
        val errorMessageFactory = { answer: String -> if (answer == "YES") "is valid" else "is not valid" }

        require(expectedIsValid == actualIsValid) {
            "String \"$string\" is ${errorMessageFactory(expectedIsValid)}," +
                    " but actual value was ${errorMessageFactory(actualIsValid)}."
        }
    }
}
