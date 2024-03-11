package exercise2.task3

import org.jetbrains.exercise2.task3.findPairWithBiggestDifference
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min

class FindPairWithHighestDifferenceTest {
    @Test
    fun `test - find pairs with the highest difference`() {
        val testData = listOf(
            TestEntry(listOf(1, 1), Pair(1, 1)),
            TestEntry(listOf(743, 284, 677, -753, 995, -934, 102, 903, -83, -760, 77, -420), Pair(-934, 995)),
            TestEntry(listOf(-270, 271, -80, 926, -332, -93, 209, 668), Pair(-332, 926)),
            TestEntry(listOf(-252, -537, -659, 235, -505, -619, -687), Pair(-687, 235)),
            TestEntry(listOf(810, -341, 742, 498, 560, -940, 79, -762, 835, 969), Pair(-940, 969)),
            TestEntry(listOf(-317, 67, -614, -234, -490, 814, 424, -465, 67, 759, -741, -731), Pair(-741, 814)),
            TestEntry(listOf(336, 443, -314, -614, 66, -676, -871, -255), Pair(-871, 443)),
            TestEntry(listOf(34, 376, 577, -900, -61, 362, 82, -973), Pair(-973, 577)),
            TestEntry(listOf(227, 891, -41, 666, 906, 201, -121, -100, 268, -215, -746, -712), Pair(-746, 906)),
            TestEntry(listOf(-46, 943, 572, 15, 996, 632, 997), Pair(-46, 997)),
            TestEntry(listOf(-453, -214, -593, 315, 497, -90, -631, 706, -950, -666, -1000, -379), Pair(-1000, 706))
        )

        testData.forEach { (input: List<Int>, expected: Pair<Int, Int>) ->
            val actual = input.findPairWithBiggestDifference()

            requireNotNull(actual)

            assertEquals(
                listOf(min(expected.first, expected.second), max(expected.first, expected.second)),
                listOf(min(actual.first, actual.second), max(actual.first, actual.second)),
                "Pair that adds to highest sum in list: $input is $expected, but actual value was $actual"
            )
        }
    }

    private data class TestEntry(val input: List<Int>, val expected: Pair<Int, Int>)
}
