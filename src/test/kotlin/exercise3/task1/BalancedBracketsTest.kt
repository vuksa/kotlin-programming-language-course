package exercise3.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BalancedBracketTest {

    @Test
    fun `test - verify expression is balanced`() {
        assertTrue(isExpressionBalanced("{}"))
        assertTrue(isExpressionBalanced("()"))
        assertTrue(isExpressionBalanced("[]"))
        assertTrue(isExpressionBalanced("{[()]}"))
        assertTrue(isExpressionBalanced("{[()]()}"))
        assertTrue(isExpressionBalanced("[{}]"))
        assertTrue(isExpressionBalanced("((()))"))
        assertTrue(isExpressionBalanced("{[()]()[]}"))
        assertTrue(isExpressionBalanced("{{}}"))
        assertTrue(isExpressionBalanced("[[()]]"))
        assertTrue(isExpressionBalanced("{{([][])}()}"))
        assertTrue(isExpressionBalanced("((()))"))
        assertTrue(isExpressionBalanced("(){}[]"))
    }

    @Test
    fun `test - verify expression is unbalanced`() {
        assertFalse(isExpressionBalanced("{"))
        assertFalse(isExpressionBalanced("}"))
        assertFalse(isExpressionBalanced("[["))
        assertFalse(isExpressionBalanced("))"))
        assertFalse(isExpressionBalanced(")("))
        assertFalse(isExpressionBalanced("{[()]())"))
        assertFalse(isExpressionBalanced("{[(])}"))
        assertFalse(isExpressionBalanced("{[}]"))
        assertFalse(isExpressionBalanced("{[}"))
        assertFalse(isExpressionBalanced("}{"))
        assertFalse(isExpressionBalanced("{{[}}"))
        assertFalse(isExpressionBalanced("()("))
        assertFalse(isExpressionBalanced("[(])"))
    }
}
