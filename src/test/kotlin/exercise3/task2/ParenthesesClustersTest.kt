package exercise3.task2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ParenthesesClustersTest {
    @Test
    fun `test - split expression to clusters returns expected result`() {
        assertClustersValidity("()()()", listOf("()", "()", "()"))
        assertClustersValidity("((()))", listOf("((()))"))
        assertClustersValidity("((()))(())()()(()())", listOf("((()))", "(())", "()", "()", "(()())"))
        assertClustersValidity("((())())(()(()()))", listOf("((())())", "(()(()()))"))
        assertClustersValidity("()(", emptyList())
        assertClustersValidity("())(", emptyList())
        assertClustersValidity("))((", emptyList())
        assertClustersValidity("(())))(", emptyList())
        assertClustersValidity("((())", emptyList())
        assertClustersValidity("()", listOf("()"))
        assertClustersValidity("", emptyList())
        assertClustersValidity("(()", emptyList())
        assertClustersValidity("())", emptyList())
        assertClustersValidity("()()()(()())(())", listOf("()", "()", "()", "(()())", "(())"))
        assertClustersValidity("()(())((()))", listOf("()", "(())", "((()))"))
        assertClustersValidity("()()()((()))", listOf("()", "()", "()", "((()))"))
        assertClustersValidity("(()())", listOf("(()())"))
    }

    private fun assertClustersValidity(input: String, expectedCluster: List<String>) {
        val actualCluster: List<String> = input.splitToBracketsClusters()
        assertEquals(
            expectedCluster,
            actualCluster,
            "Expected cluster value for input \"$input\" is $expectedCluster, " +
                    "but actual value was $actualCluster"
        )
    }
}
