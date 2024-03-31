package exercise3.task2

/**
 * Task 2: Split Expression To Parentheses Clusters
 *
 * Write a function that groups an expression of parentheses into parentheses cluster. Each cluster should be balanced.
 * If the expression isn't balanced, return the empty array.
 *
 * The following parenthesis pairs can appear in an expression string input: {}, [], ()
 *
 * The Cluster is considered balanced when every opening parenthesis must exist with its matching closing parens
 * in the same cluster.
 *
 * Examples:
 * ```"()()()"``` is split to the list: ```["()", "()", "()"]```
 * ```"((()))"``` is split to the list: ```["((()))"]```
 * ```"((()))(())()()(()())"``` is split to the list: ```["((()))", "(())", "()", "()", "(()())"]```
 * ```"((())())(()(()()))"``` is split to the list: ```["((())())", "(()(()()))"]```
 *
 * Constraints:
 * - All input strings will only contain parentheses.
 * - Input string can be empty
 *
 */

internal fun String.splitToBracketsClusters(): List<String> {
    val open = listOf('(', '[', '{')
    val closed = listOf(')', ']', '}')
    val pairs = closed.zip(open).toMap()
    val stack: MutableList<Char> = mutableListOf()
    val clusters: MutableList<String> = mutableListOf()

    var clusterStart = 0
    for ((index, c) in this.withIndex()) {
        if (c in open) {
            if(stack.isEmpty() && index != 0) {
                clusters.add(this.substring(clusterStart, index))
                clusterStart = index
            }
            stack.add(c)
        } else if (c in closed) {
            if (stack.isEmpty() || stack.last() != pairs.get(c)) return emptyList()
            stack.removeLast()
        }
    }
    if (stack.isNotEmpty()) return emptyList()
    clusters.add(this.substring(clusterStart, this.length))
    return clusters
}
fun main() {
    val expressionsToClustersCatalog = mapOf(
        "()()()" to listOf("()", "()", "()"),
        "((()))" to listOf("((()))"),
        "((()))(())()()(()())" to listOf("((()))", "(())", "()", "()", "(()())"),
        "((())())(()(()()))" to listOf("((())())", "(()(()()))")
    )

    expressionsToClustersCatalog.forEach { (expression, expectedCluster) ->
        val actualClusters = expression.splitToBracketsClusters()
        require(expectedCluster == actualClusters) {
            "Expression $expression should be split to $expression clusters, but actual value was $actualClusters."
        }
    }
}
