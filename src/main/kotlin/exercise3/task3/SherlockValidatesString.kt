package exercise3.task3

import kotlin.math.pow

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
    require(s.length in (1..(10.0).pow(5).toInt())) {
        "The length of the string must be in the given range: 1 to 10⁵."
    }
    require(s.all { it.isLowerCase() }) {
        "Uppercase and special characters are not allowed. Please make sure that the string contains only lowercase " +
        "characters."
    }

    val frequencyMap = s.groupingBy { it }.eachCount()
    val occurrences = frequencyMap.values.groupingBy { it }.eachCount()

    if (occurrences.size == 1)
        return "YES"
    if (occurrences.size == 2) {
        val (freq1, freq2) = occurrences.keys.toList()
        val (count1, count2) = occurrences.values.toList()

        if ((count1 == 1 && (freq1 == 1 || freq1 - freq2 == 1)) ||
            (count2 == 1 && (freq2 == 1 || freq2 - freq1 == 1)))
            return "YES"
    }
    return "NO"
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
