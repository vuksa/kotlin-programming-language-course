package exercise1.task5

import common.TestCase
import org.jetbrains.exercise1.task5.reverseInteger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ReverseIntegerTest : TestCase() {

    @ParameterizedTest
    @CsvSource(
        "123, 321",
        "-123, -321",
        "0, 0",
        "1, 1",
        "-1, -1",
        "10, 1",
        "-10, -1",
        "1000000, 1",
        "-1000000, -1",
        "999999, 999999",
        "-999999, -999999",
        "0, 0",
        "123456, 654321",
        "-654321, -123456"
    )
    fun `test - number is reversed correctly`(input: Int, expected: Int) {
        assertEquals(expected, reverseInteger(input))
    }

    @Test
    fun `test - error is thrown for argument out of range -1_000_000 to 1_000_000`() {
        assertThrows<IllegalArgumentException> { reverseInteger(1_000_001) }
        assertThrows<IllegalArgumentException> { reverseInteger(-1_000_001) }
    }
}