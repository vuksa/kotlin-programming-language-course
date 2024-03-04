package org.jetbrains.exercise1.task2

/**
 * Task 2: Pyramid Printing
 *
 * Write a Kotlin program that prints a pyramid structure to the console based on the specified level.
 *
 * The pyramid should be composed of asterisks (`*`) arranged in a pattern as shown below:
 * ```
 *       *
 *     * * *
 *   * * * * *
 * * * * * * * *
 * ```
 * The number of levels in the pyramid is determined by the `level` parameter passed to the function.
 * The `level` parameter must be an integer between 3 and 15 (inclusive).
 *
 * Your task is to implement a function called `printPyramid(level: Int)` that accepts an integer `level` as input
 * and prints the pyramid structure to the console. The function should ensure that the pyramid is symmetric
 * and properly aligned.
 *
 * Example:
 * For `level = 5`, the output should be:
 * ```
 *       *
 *     * * *
 *   * * * * *
 * * * * * * * *
 * ```
 * For `level = 7`, the output should be:
 * ```
 *         *
 *       * * *
 *     * * * * *
 *   * * * * * * *
 * * * * * * * * * *
 * ```
 *
 * Requirements:
 * - Implement the `printPyramid` function to generate the pyramid structure based on the given `level`.
 * - Ensure that the input `level` is an integer between 3 and 15 (inclusive). If the input is invalid,
 * throw an IllegalArgumentException exception.
 * - The pyramid should be symmetric and properly aligned.
 *
 */

internal fun printPyramid(level: Int) {
    TODO("Implement me!!!")
}

fun main() {
    printPyramid(5)
}