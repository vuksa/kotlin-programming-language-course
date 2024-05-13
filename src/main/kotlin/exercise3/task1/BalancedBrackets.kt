package exercise3.task1

/**
 * Task1: Balanced Brackets (Parentheses) Problem
 *
 * Implement a method [isExpressionBalanced] to check whether the given expression containing only brackets (parentheses), {}, [], ()
 * is balanced or not.
 *
 * In this problem, you are given a string expression containing only brackets of three types: {}, [], ().
 * Your task is to determine whether the brackets in the expression are balanced or not.
 *
 * Brackets are considered balanced if and only if the following expression complies with the following two rules:
 * 1. For every opening bracket, there exists a corresponding closing bracket of the same type
 * 2. The brackets are properly nested, meaning a closing bracket cannot occur before its corresponding opening bracket.
 *
 * For example,
 * - ```"{[()]}"``` is balanced because each opening bracket has a corresponding closing bracket and they are properly nested.
 * - ```"{[(])}"``` is not balanced because the closing bracket ']' appears before the `)` closing bracket.
 * - ```"{{}}"``` is balanced because all opening brackets have corresponding closing brackets, and they are properly nested.
 * - ```"()"``` is balanced because all opening brackets have corresponding closing brackets, and they are properly nested.
 * - ```"[[["``` is not balanced because there are unmatched opening brackets.
 * - ```"[{}()]"``` is balanced because there are matched opening and closing brackets.
 * - ```"[{(}]"```  is not balanced because there are unmatched opening brackets, in this case `)` is missing.
 *
 */


internal fun isExpressionBalanced(expression: String): Boolean {
    val openBrackets = listOf('(', '[', '{')
    val closedBrackets = listOf(')', ']', '}')
    var brackets = mutableListOf<Char>()


    for(char in expression) {
        when(char) {
            in openBrackets -> brackets.add(char)
            ')' -> if(brackets.isEmpty() || brackets.removeAt(brackets.size - 1) != '(') return false
            ']' -> if(brackets.isEmpty() || brackets.removeAt(brackets.size - 1) != '[') return false
            '}' -> if(brackets.isEmpty() || brackets.removeAt(brackets.size - 1) != '{') return false
        }
    }

    return brackets.isEmpty()
}

fun main() {
    val expressions = listOf(
        "{[()]}" to true,
        "{[(])}" to false,
        "{{}}" to true,
        "()" to true,
        "[[[" to false,
        "[{}()]" to true,
        "[{(}]" to false
    )

    expressions.forEach { (expression, expectedIsBalanced) ->
        val actualIsExpressionBalanced = isExpressionBalanced(expression)

        require(expectedIsBalanced == actualIsExpressionBalanced) {
            val errorMessageFactory = { isBalanced: Boolean -> if (isBalanced) "is balanced" else "is unbalanced" }
            buildString {
                append("Expression \"$expression\" ${errorMessageFactory(expectedIsBalanced)},")
                append(" but function result shows it ${errorMessageFactory(actualIsExpressionBalanced)}")
            }
        }
    }
}
