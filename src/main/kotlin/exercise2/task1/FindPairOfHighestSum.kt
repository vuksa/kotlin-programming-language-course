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


//    for ( i in 0  ..this.size - 1){
//        if(this[i] >= first){
//            first = this[i]
//        }
//    }
//
//    for ( i in 0  .. this.size - 1){
//        if(this[i] > second && this[i] < first){
//            second = this[i]
//        }
//    }

//    var first = this[0]
//    var second = this[0]
//    println("Velicina " + this.size)
//    for (i in 0 ..< this.size -1){
//        if (this[i+1] >= first ) first = this[i+1]
//        if ((this[i] > second) && (this[i] < first)) second = this[i]
//    }
//    if ((this[lastIndex] > second) && (this[lastIndex] < first)) second = this[lastIndex]
//    println("Prvi: " + first)
//    println("Drugi: " + second)


//    Prlazak kroz dve petlje
//    var first = this[1]
//    var second = this[0]
//    for ( i in indices){
//        if(this[i] >= first){
//            first = this[i]
//        }
//    }
//
//    for ( i in indices){
//        if((this[i] > second) && (this[i] < first)){
//            second = this[i]
//        }
//    }

//    Prolazak kroz jednu petlju
    var first = this[0]
    var second = this[0]
    for (i in 0 ..< this.size -1){
        if (this[i+1] >= first ){
            second = first
            first = this[i+1]
        }
        else if ((second < this[i+1]) && second < first){
            second = this[i+1]
        }
    }
    return Pair<Int, Int>(first, second)
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
