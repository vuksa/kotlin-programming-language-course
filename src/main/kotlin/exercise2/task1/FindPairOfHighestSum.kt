package exercise2.task1

import org.jetbrains.exercise2.common.isEqualsTo
import org.jetbrains.exercise2.task3.findPairWithBiggestDifference


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
    val prvi = this.sorted().get(this.lastIndex)
    val drugi = this.sorted().get(this.lastIndex-1)

    return Pair(prvi, drugi)
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
