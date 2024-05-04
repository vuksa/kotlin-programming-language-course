package exercise6.taskProjections.task4

/**
 *  Projections #4
 *
 * Implement a simple function copy,
 * that accepts two arrays with a generic type of Any and copy elements from the first array to the second one.
**/

fun <T : Any> copy(arr1:Array<out T>, arr2:Array<T>){
    arr1.forEachIndexed { index, el -> arr2[arr2.size + index] = el }
}

fun main() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    ints.forEach{ print("$it ") }
    println()
    any.forEach{ print("$it ")}
    println()
    println("_______")
    println("_______")

    // TODO: uncomment this code
    copy(ints, any)

    ints.forEach{ print("$it ") }
    println()
    any.forEach{ print("$it ")}
}
