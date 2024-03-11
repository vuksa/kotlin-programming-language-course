package org.jetbrains.exercise2.common

import kotlin.math.max
import kotlin.math.min


/**
 * The `isEqualsTo` function compares the current pair object with another pair object to check if they are equal.
 * It compares the elements of the pairs in different orders because the ordering of the elements does not matter.
 *
 * @param actualPair The pair to compare with the current pair object.
 * @return true if the pairs have the same elements, regardless of their order. Otherwise, it returns false.
 */
fun Pair<Int, Int>.isEqualsTo(actualPair: Pair<Int, Int>): Boolean {
    val expectedFirst = min(this.first, this.second)
    val expectedSecond = max(this.first, this.second)
    val actualFirst = min(actualPair.first, actualPair.second)
    val actualSecond = max(actualPair.first, actualPair.second)

    return expectedFirst == actualFirst && expectedSecond == actualSecond
}
