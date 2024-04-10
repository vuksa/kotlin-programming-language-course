package exercise2.task3

import org.jetbrains.exercise2.common.isEqualsTo
import kotlin.math.abs

/**
 * Task 3: Function [findPairWithBiggestDifference] finds the pair with the biggest difference in a list of integers.
 *
 * The function is written with the imperative approach, and the task of this assignment is to refactor it to the
 * functional approach.
 *
 * Constraints:
 * List has at least two integers.
 *
 * @receiver The input list of integers from which to find the pair with the biggest difference.
 * @return The pair with the biggest difference between its elements.
 */

internal fun List<Int>.findPairWithBiggestDifference(): Pair<Int, Int> {
    var resultPair: Pair<Int, Int>? = null
    var biggestDifference = Int.MIN_VALUE

    for (i in this.indices) {
        for (j in (i + 1) until this.size) {
            val first = this[i]
            val second = this[j]
            val absDifference = abs(first - second)

            if (absDifference >= biggestDifference) {
                biggestDifference = absDifference
                resultPair = Pair(first, second)
            }
        }
    }

    return resultPair!!
}

// O(n log n)
internal fun List<Int>.findPairWithBiggestDifferenceRefactored() : Pair<Int, Int> {
    return this.sorted().let { sortedList -> sortedList.last() to sortedList.first() }
}

// O(n)
internal fun List<Int>.findPairWithBiggestDifferenceOptimal(): Pair<Int, Int> {
    require(size >= 2) { "List must contain at least two elements" }

    val (min, max) = this.minOrNull()!! to this.maxOrNull()!!
    return if (max - min >= 0) Pair(max, min) else Pair(min, max)
}

fun main() {
    val nums = listOf(743, 284, 677, -753, 995, -934, 102, 903, -83, -760, 77, -420)
    val expectedPair = Pair(995, -934)
    val actualPair = nums.findPairWithBiggestDifferenceRefactored()

    println("Pair that has highest difference in list $nums is $actualPair")

    require(expectedPair.isEqualsTo(actualPair)) {
        "Actual pair that has highest difference in list $nums was $actualPair, but expected was $expectedPair."
    }
}
