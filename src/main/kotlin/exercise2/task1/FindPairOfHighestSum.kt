package exercise2.task1

import org.jetbrains.exercise2.common.isEqualsTo


/**
 * Task 1: Find a Pair that adds to the Highest Sum
 *
 * Write an extension function for a list of ints that returns a pair of numbers from the list
 * that make the highest sum. If there is more than one pair that makes the highest sum,
 * return the last pair.
 *
 * Constraints:
 * List has at least two integers.
 * Items in the list are in range -1000..1000
 *
 */

internal fun List<Int>.findHighestSumPair(): Pair<Int, Int> {
    require(this.size >= 2) {
        "The list must have at least two integers."
    }
    require(this.all { it in -1000..1000 }) {
        "All values in the list must be between -1000 and 1000."
    }

    var maxSum = Integer.MIN_VALUE
    var pair: Pair<Int, Int>? = null

    for ((index, item) in this.dropLast(1).withIndex()) {
        for (j in (index + 1) until this.size) {
            val sum = item + this[j]

            if (sum >= maxSum) {
                maxSum = sum
                pair = Pair(item, this[j])
            }
        }
    }
    return pair!!
}

fun main() {
    val nums = listOf(743, 284, 677, -753, 995, -934, 102, 903, -83, -760, 77, -420)
    val expectedPair = Pair(995, 903)
    val actualPair = nums.findHighestSumPair()

    println("Pair that has highest sum in list $nums is $actualPair.")

    require(expectedPair.isEqualsTo(actualPair)) {
        "Actual pair that has highest sum in list $nums was $actualPair, but expected was $expectedPair"
    }
}
