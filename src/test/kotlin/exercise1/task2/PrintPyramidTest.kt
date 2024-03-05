package exercise1.task2

import common.TestCase
import org.jetbrains.exercise1.task2.printPyramid
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.io.path.readLines

class PrintPyramidTest : TestCase() {

    @ParameterizedTest
    @ValueSource(ints = [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])
    fun `test -  print Pyramid with different levels`(level: Int) {
        val levelToPyramidStructureCatalog = loadTestData()

        val pyramidStructure = captureSystemOut { printPyramid(level) }

        val expectedPyramid = levelToPyramidStructureCatalog.getValue(level)
        assertEquals(
            expectedPyramid.trim(),
            pyramidStructure.trim(),
            "Expected pyramid structure for level $level was $pyramidStructure, but expected was:\n$expectedPyramid"
        )
    }

    @Test
    fun `test - invalid input`() {
        assertThrows<Exception> { printPyramid(-1) }
        assertThrows<Exception> { printPyramid(0) }
        assertThrows<Exception> { printPyramid(16) }
    }

    private fun captureSystemOut(block: () -> Unit): String {
        val outputStream = System.out
        try {
            val outputStreamCaptor = ByteArrayOutputStream()
            System.setOut(PrintStream(outputStreamCaptor))
            block()
            return outputStreamCaptor.toString()
        } finally {
            System.setOut(outputStream)
        }
    }

    private fun loadTestData(): Map<Int, String> {
        val lines = getTestData("pyramid_schema.txt")
            .readLines()

        val levelToStructureCatalog = HashMap<Int, String>(12)

        for ((index, line) in lines.withIndex()) {
            if (line.contains("Level: ")) {
                val level = line.removePrefix("Level: ").trim().toInt()

                val pyramidStructure = lines.subList(index + 1, index + level + 1)
                    .joinToString("\n") { lineNum -> lineNum }

                levelToStructureCatalog[level] = pyramidStructure
            }
        }

        return levelToStructureCatalog
    }
}
