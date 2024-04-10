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
    require(size >= 2) { "List must contain at least two elements" }

    var pair = Pair(Integer.MIN_VALUE, Integer.MIN_VALUE)
    val sum = pair.first + pair.second

    for (x in 0..(size - 2)) {
        for (y in (x+1) until size) {
            val currentSum = pair.first + pair.second
            if (currentSum >= sum) {
                pair = Pair(this[x], this[y])
            }
        }
    }
    return pair
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
