package exercise6.taskProjections.task4

/**
 *  Projections #4
 *
 * Implement a simple function copy,
 * that accepts two arrays with a generic type of Any and copy elements from the first array to the second one.
**/

fun <Any> copy(arr1 : Array<Any>, arr2 : Array<in Any>) {
    require(arr2.size >= arr1.size) {
        throw Exception("Second array is shorter than first!")
    }

    for (i in arr1.indices)
            arr2[i] = arr1[i]
}

fun main() {
    val ints = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    ints.forEach{ print("$it ") }
    println()
    any.forEach{ print("$it ")}
    println()
    println("_______")
    println("_______")

    copy(ints, any)

    ints.forEach{ print("$it ") }
    println()
    any.forEach{ print("$it ")}
}
