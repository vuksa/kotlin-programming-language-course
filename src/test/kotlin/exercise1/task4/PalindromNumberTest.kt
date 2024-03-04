package exercise1.task4

import common.TestCase
import org.jetbrains.exercise1.task4.isPalindrome
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PalindromeNumberTest : TestCase() {

    @ParameterizedTest
    @CsvSource(
        "121, true",
        "-121, false",
        "10, false",
        "12321, true",
        "-12321, false",
        "0, true"
    )
    fun `test - is Number a Palindrome`(input: Int, expected: Boolean) {
        assertEquals(expected, isPalindrome(input))
    }
}