package exercise2.task2

import org.jetbrains.exercise2.common.isEqualsTo
import org.jetbrains.exercise2.task3.findPairWithBiggestDifference


/**
 * Task 2: Find a Pair that adds to the Highest Sum using Functional approach
 *
 * Write an extension function for a list of ints that returns a pair of numbers from the list
 * that make the highest sum. If there is more than one pair that makes the highest sum,
 * return the last pair.
 *
 * Write the solution with the functional approach.
 *
 * Constraints
 * List has at least two integers.
 * Items in the list are in range -1000..1000
 *
 */

internal fun List<Int>.findHighestSumPairFunctional(): Pair<Int, Int> {
    return this.sortedDescending().let { it.first() to it[1] }
}

fun main() {
    val nums = listOf(743, 284, 677, -753, 995, -934, 102, 903, -83, -760, 77, -420)
    val expectedPair = Pair(995, 903)
    val actualPair = nums.findHighestSumPairFunctional()

    println("Pair that has highest sum in list $nums is $actualPair.")

    require(expectedPair.isEqualsTo(actualPair)) {
        "Actual pair that has highest sum in list $nums was $actualPair, but expected was $expectedPair"
    }
}
