package exercise6.taskProjections.task2

/**
 * Task: Projections #2
 *
 * Create the class "Programmer" with a type parameter T that is a ProgrammingLanguage.
 * In this class, you need to add restrictions and allow creating only methods, which accept T.
 * The methods, which return T, should be restricted.
 * Then create one method learn, that accepts a language and prints "I learned ${language.name}!".
 * If you did everything correct, you could not create a method create which accepts nothing and returns T
**/

class Programmer <in T: ProgrammingLanguage> {
    fun learn(language: T) = println("I learned ${language.name}!")
}
interface ProgrammingLanguage {
    val name: String
}
class JvmLanguage(override val name: String) : ProgrammingLanguage

class BlockBasedLanguage(override val name: String) : ProgrammingLanguage

fun main() {
    val programmer = Programmer<ProgrammingLanguage>()

    val jvmLanguages = listOf(JvmLanguage("Java"), JvmLanguage("Kotlin"))
    jvmLanguages.forEach{ programmer.learn(it) } // OK

    val blockBasedLanguages = listOf(BlockBasedLanguage("Scratch"), JvmLanguage("Snap"))
    blockBasedLanguages.forEach{ programmer.learn(it) } // OK

    (jvmLanguages + blockBasedLanguages).forEach{ programmer.learn(it) } // OK

//    val newLanguage: ProgrammingLanguage = programmer.create() // ERROR
}
