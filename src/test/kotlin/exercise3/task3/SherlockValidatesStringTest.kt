package exercise3.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SherlockValidatesStringTest {
    @Test
    fun `verify - is string sherlock valid`() {
        assertStringValidity("YES", "abc")
        assertStringValidity("YES", "abcc")
        assertStringValidity("YES", "aabbcc")
        assertStringValidity("YES", "aabbccc")
        assertStringValidity("YES", "aaaaabbbbbccccc")
        assertStringValidity("YES", "aabbbcc")
        assertStringValidity("YES", "abcdefghij")
        assertStringValidity("YES", "abcdefghia")
        assertStringValidity("YES", "aabb")
        assertStringValidity("YES", "aabbc")
        assertStringValidity("YES", "aaabbbcccdddeeefffggghhhiiijjj")

        assertStringValidity("YES","a")
        assertStringValidity("NO","acc")
        assertStringValidity("YES", "aaabbbcccdddeeefffggghhhiiijjjk")
        assertStringValidity("NO", "aaabbbcccdddeeefffggghhhiiijjjkk")
        assertStringValidity("NO", "aabbcddd")
        assertStringValidity("YES", "aabbccddd")
        assertStringValidity("NO", "aabbccdddde")
        assertStringValidity("NO", "aabbccddddef")
        assertStringValidity("NO", "aabbccddddefg")
        assertStringValidity("NO", "aabbbcccdddeeefffggghhhiiijjjkkkl")
        assertStringValidity("NO", "aabbccdddeef")
        assertStringValidity("YES", "aaabbcc")
        assertStringValidity("YES", "aabbccd")
        assertStringValidity("YES", "aabbccdd")
        assertStringValidity("YES", "abcde")
        assertStringValidity("YES", "abbcde")
        assertStringValidity("NO", "aabbcde")
        assertStringValidity("YES", "aabbccddee")
        assertStringValidity("YES", "aabbbccddeeff")
        assertStringValidity("NO", "abcdefghiaa")
        assertStringValidity("YES", "abcdefghijk")
    }

    private fun assertStringValidity(expectedValidity: String, stringToValidate: String) {
        val actualValidity = isSherlockValid(stringToValidate)
        assertEquals(
            expectedValidity,
            actualValidity,
            "String $stringToValidate has $expectedValidity as a expected result," +
                    " but actual value was $actualValidity"
        )
    }
}
