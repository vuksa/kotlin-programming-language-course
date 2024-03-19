package org.jetbrains.exercise1.task3

/**
 * Task 3:
 * In Scrabble, each letter has a corresponding point value. To calculate the score of a word,
 * you sum up the point values of each letter in the word according to the following table:
 *
 * Score Table:
 * ```
 * 1  Point:  A, E, I, O, U, L, N, R, S, T
 * 2  Points: D, G
 * 3  Points: B, C, M, P
 * 4  Points: F, H, V, W, Y
 * 5  Points: K
 * 8  Points: J, X
 * 10 Points: Q, Z
 *```
 * For example, the word 'faculty' has a Scrabble score of 15, as the following mappings apply:
 * ```
 *    'f' = 4
 *    'a' = 1
 *    'c' = 3
 *    'u' = 1
 *    'l' = 1
 *    't' = 1
 *    'y' = 4
 *  Total: 15
 *```
 * Assignment: Implement the [calculateWordScrabbleScore] function, so that it returns the Scrabble score for a given
 * [word]. It is guaranteed that the [word] contains only characters in the range from 'a' to 'z'.
 *
 */

private val characterScoreMap = mapOf(
    'A' to 1, 'E' to 1, 'I' to 1, 'O' to 1, 'U' to 1, 'L' to 1, 'N' to 1, 'R' to 1, 'S' to 1, 'T' to 1,
    'D' to 2, 'G' to 2,
    'B' to 3, 'C' to 3, 'M' to 3, 'P' to 3,
    'F' to 4, 'H' to 4, 'V' to 4, 'W' to 4, 'Y' to 4,
    'K' to 5,
    'J' to 8, 'X' to 8,
    'Q' to 10, 'Z' to 10
)
internal fun getCharacterScoreWhen(character : Char) : Int {
    return when(character.uppercase()) {
        "A", "E", "I", "O", "U", "L", "N", "R", "S", "T" -> 1
        "D", "G" -> 2
        "B", "C", "M", "P" -> 3
        "F", "H", "V", "W", "Y" -> 4
        "K" -> 5
        "J", "X" -> 8
        "Q", "Z" -> 10
        else -> 0
    }
}

internal fun calculateWordScrabbleScore(word: String): Int {
    /* var score = 0
     * for (c in word) score += getCharacterScoreWhen(c)
     * return score
     */
    return word.sumOf { characterScoreMap.getValue(it.uppercaseChar()) }
}

fun main() {
    val word = "faculty"
    val actualScore = calculateWordScrabbleScore(word)
    val expectedScore = 15
    if (actualScore == expectedScore) {
        println("Well done!")
    } else {
        println("Score for a given word $word is $actualScore, but it should be 15")
    }
}