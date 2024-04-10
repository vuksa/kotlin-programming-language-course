package exercise2.task2

import kotlin.system.measureTimeMillis
import org.jetbrains.exercise2.common.isEqualsTo

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

// O(n log n)
internal fun List<Int>.findHighestSumPairFunctional1(): Pair<Int, Int> {
    return this.sortedDescending().let { sortedList -> sortedList.first() to sortedList[1] }
}

// O(n)
internal fun List<Int>.findHighestSumPairFunctional(): Pair<Int, Int> {
    val (max1, max2) = this.fold(Pair(Int.MIN_VALUE, Int.MIN_VALUE)) { (max1, max2), num ->
        when {
            num > max1 -> Pair(num, max1)
            num > max2 -> Pair(max1, num)
            else -> Pair(max1, max2)
        }
    }
    return max1 to max2
}

fun main() {
    val nums = listOf(743, 284, 677, -753, 995, -934, 102, 903, -83, -760, 77, -420)
    val expectedPair = Pair(995, 903)

    val sortingTime = measureTimeMillis {
        val actualPairSorting = nums.findHighestSumPairFunctional1()
        println("Pair that has highest sum using sorting approach: $actualPairSorting.")
        require(expectedPair.isEqualsTo(actualPairSorting)) {
            "Actual pair using sorting approach was $actualPairSorting, but expected was $expectedPair"
        }
    }
    println("Sorting approach took $sortingTime ms")

    val functionalTime = measureTimeMillis {
        val actualPairFunctional = nums.findHighestSumPairFunctional()
        println("Pair that has highest sum using functional approach: $actualPairFunctional.")
        require(expectedPair.isEqualsTo(actualPairFunctional)) {
            "Actual pair using functional approach was $actualPairFunctional, but expected was $expectedPair"
        }
    }
    println("Functional approach took $functionalTime ms")
}
