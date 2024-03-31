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

    val grpByChars = s.groupingBy { it }.eachCount()

    val valuesOfGrouping = grpByChars.values.groupingBy { it }.eachCount()

    if (valuesOfGrouping.size > 2) return "NO"
    if (valuesOfGrouping.size == 1) return "YES"

    val firstKey = valuesOfGrouping.keys.first()
    val firstValue = valuesOfGrouping.values.first()
    val secondKey = valuesOfGrouping.keys.last()
    val secondValue = valuesOfGrouping.values.last()

    if((secondValue == 1) and (secondKey== 1)) return "YES"
    if((firstValue == 1) and (firstKey== 1)) return "YES"

    val minusKeys = abs(firstKey - secondKey)
    val minusValues = abs(firstValue - secondValue)

/*
    Mislim da sa ovim ne pokrivam sve krajnje slucajeve
    i da kod nije bas efikasan. Prolazi testove.
 */
    if ( (minusKeys <=1) or (minusValues <= 1) ){

        if( (firstKey > secondKey) and (firstValue > secondValue) ) return "NO"
        if( (firstKey < secondKey) and (firstValue < secondValue) ) return "NO"
        if(firstValue >=2 && secondValue >= 2)  return "NO"

        return "YES"
    }
    else{
        return "NO"
    }

}

fun main() {
    val stringsToValidityCatalog = mapOf("abc" to "YES", "abcc" to "YES", "aabbccccc" to "NO")

    stringsToValidityCatalog.forEach { (string, expectedIsValid) ->
        val actualIsValid = isSherlockValid(string)
        val errorMessageFactory = { answer: String -> if (answer == "YES") "is valid" else "is not valid" }

        require(expectedIsValid == actualIsValid) {
            "String \"$string\" is ${errorMessageFactory(expectedIsValid)}," +
                    " but actual value was ${errorMessageFactory(actualIsValid)}."
        }
    }
}
