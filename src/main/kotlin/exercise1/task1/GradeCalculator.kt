package org.jetbrains.exercise1.task1

import java.util.Scanner

/**
 * Task 1: Grade Calculator
 *
 * Write a Kotlin program that calculates the grade for a student based on their score according to the provided grading rules.
 * The grading rules are as follows:
 *
 * - Grade 10: 91-100 points
 * - Grade 9: 81-90 points
 * - Grade 8: 71-80 points
 * - Grade 7: 61-70 points
 * - Grade 6: 51-60 points
 *
 * The program should prompt the user to enter the student's score as an Integer.
 * Then, it should use `if` or `when` statements
 * to determine the corresponding grade based on the score and print the grade to the console.
 *
 * Additionally, the program should handle invalid inputs. If the user enters a score not in the range of 0 to 100,
 * the program should print an error message and prompt the user to enter a valid score.
 *
 * Example Output:
 *
 * ```
 * Enter student score: 93
 * Grade: 10
 * ```
 *
 * ```
 * Enter student score: 79
 * Grade: 8
 * ```
 *
 * ```
 * Enter student score: 105
 * Invalid score. Please enter a score between 0 and 100.
 * ```
 */

internal fun calculateGrade(score: Int): Int {
//    require(score in 0..100) {
//        "Score is out of range 0-100!"
//    }
    return when {
        score in 0..50 -> 5
        score in 51..60 -> 6
        score in 61..70 -> 7
        score in 71..80 -> 8
        score in 81..90 -> 9
        score in 91..100 -> 10
        else -> throw IllegalArgumentException("Score out of range 0-100")
    }
}

fun main() {
    print("Enter student score: ")
    val scanner = Scanner(System.`in`)
    val score = scanner.nextInt()
    val grade = calculateGrade(score)
    val msg = if(grade != 5) "Student's grade is: $grade" else "Student didn't pass the exam"
    println(msg)
}
