package exercise6.taskProjections.task6

/**
 * Task: Projections #6
 *
 * Put correct types inside the "funny" function
**/

open class A
class B : A()
class C : A() { fun consume(other: A): C = this }

// TODO: uncomment and fix me
//fun <T, S : R, R> funny(
//    source: Iterator<??>,
//    target: MutableCollection<??>,
//    base: ??,
//    how: ??
//) {
//    var result: R = base
//    for (value in source) {
//        result = how(result, value)
//        target.add(result)
//    }
//}

fun main() {
    // TODO: uncomment me, it should not produce any compilation errors
//    val wtf = mutableListOf<A>()
//    val src = mapOf(3.14 to B(), 2 to B(), "Hello" to B())
//    val c = C()
//    funny(src.values.iterator(), wtf, c) { r, t -> r.consume(t) }
}
