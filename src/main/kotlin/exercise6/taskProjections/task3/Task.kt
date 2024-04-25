package exercise6.taskProjections.task3

/**
 * Task: Projections #3
 *
 * You have a hierarchy of classes here.
 * You need to initialize parsersHashMap in the right way to make the code compilable.
**/

abstract class ProgrammingLanguage

class JvmLanguage : ProgrammingLanguage()
class BlockBasedLanguage : ProgrammingLanguage()

interface LanguageParser<T: ProgrammingLanguage> {
    @Suppress("UNCHECKED_CAST")
    fun parse(language: ProgrammingLanguage) {
        val tmpLang = language as? T
            ?: error("Invalid type ${language.javaClass.name} passed to this parser")
        internalParse(tmpLang)
    }

    fun internalParse(item: T)
}

class JvmLanguageParser : LanguageParser<JvmLanguage> {
    override fun internalParse(item: JvmLanguage) {
        println("Parsing JvmLanguage")
    }
}

class BlockBasedLanguageParser : LanguageParser<BlockBasedLanguage> {
    override fun internalParse(item: BlockBasedLanguage) {
        println("Parsing BlockBasedLanguage")
    }
}

fun main() {
    val parsersHashMap = HashMap<Class<*>, LanguageParser<*>>()
    parsersHashMap[JvmLanguage::class.java] = JvmLanguageParser()
    parsersHashMap[BlockBasedLanguage::class.java] = BlockBasedLanguageParser()

    val scratch = BlockBasedLanguage()
    val foundParser = parsersHashMap[scratch.javaClass]
    foundParser?.parse(scratch)
}
