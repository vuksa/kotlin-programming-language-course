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
 * s = abcc
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
    val FALSE = "NO"
    val TRUE = "YES"

    val charCounts = s.groupingBy { it }.eachCount()
    val values = charCounts.values.toSet()
    if (values.size > 2) return FALSE
    if (values.size <= 1) return TRUE

    val minFreq = values.minOrNull() ?: return FALSE
    val maxFreq = values.maxOrNull() ?: return FALSE

    val minFreqCount = charCounts.values.count { it == minFreq }
    val maxFreqCount = charCounts.values.count { it == maxFreq }

    if (minFreq == 1 && minFreqCount == 1) return TRUE
    if ((maxFreq - minFreq == 1 || maxFreq - minFreq == 0) && maxFreqCount == 1) return TRUE

    return FALSE
}

fun main() {
    val stringsToValidityCatalog = mapOf("abc" to "YES", "abcc" to "YES", "abccc" to "NO")

    stringsToValidityCatalog.forEach { (string, expectedIsValid) ->
        val actualIsValid = isSherlockValid(string)
        val errorMessageFactory = { answer: String -> if (answer == "YES") "is valid" else "is not valid" }

        require(expectedIsValid == actualIsValid) {
            "String \"$string\" is ${errorMessageFactory(expectedIsValid)}," +
                    " but actual value was ${errorMessageFactory(actualIsValid)}."
        }
    }
}
