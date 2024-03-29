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

    println("String s: " + s)

    val proba = s.groupingBy { it }.eachCount().all { ( maxOf(it.value) - minOf(it.value) ) <=1 }

    val proba4 = s.groupingBy { it }.eachCount()

    val proba5 = proba4.values.groupingBy { it }.eachCount()
    println("Proba 5: " + proba5)

    val proba6 = proba5
    println("Proba 6: " + proba6)

    if (proba5.size > 2) return "No"
    if (proba5.size == 1) return "YES"


    val firstKey = proba5.keys.first()
    val firstValue = proba5.values.first()
    val secondKey = proba5.keys.last()
    val secondValue = proba5.values.last()


    if((secondValue == 1) and (secondKey== 1)) return "YES"
    if((firstKey == 1) and (firstKey== 1)) return "YES"


    println("Abs: " + abs(firstKey - secondKey))
    if ( (abs(firstKey - secondKey) <=1)  ){
//        if((abs(firstValue - secondValue) <= 1 ) and (firstKey > 2 || secondKey > 2))
//            if((firstKey > 2 || secondKey > 2))
        if (abs(firstValue - secondValue ) <= 1){
            return "YES"
        }
        println("Vratilo YES")
        return "NO"
    }
    else{
        println("Vratilo NO")
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
