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

private sealed class ClusterProcessing {
    abstract val clusters: List<String>

    abstract fun process(bracketCharacter: Char): ClusterProcessing

    data object InvalidCluster : ClusterProcessing() {
        override val clusters: List<String> = emptyList()
        override fun process(bracketCharacter: Char): ClusterProcessing = InvalidCluster
    }

    data class ValidCluster(
        private val accumulatorValue: String = "",
        private val openBracketsCounter: OpenBracketsCounter = OpenBracketsCounter(),
        private val totalCharacters: Int = 0
    ) : ClusterProcessing() {
        private val clusterEndChar = "|"
        private val closedToOpenBrackets = mapOf(']' to '[', '}' to '{', ')' to '(')
        private val closeBrackets = closedToOpenBrackets.keys
        private val openBrackets = closedToOpenBrackets.values

        override val clusters: List<String>
            // If there is any open brackets expression is invalid, hence no clusters should be returned.
            get() = if (openBracketsCounter.anyOpen) {
                emptyList()
            } else {
                accumulatorValue.split(clusterEndChar).filter(String::isNotBlank)
            }

        override fun process(bracketCharacter: Char): ClusterProcessing {
            return when {
                // Case when we open the bracket
                bracketCharacter in openBrackets -> {
                    ValidCluster(accumulatorValue + bracketCharacter, openBracketsCounter.increment(bracketCharacter))
                }
                // We add close bracket when there previously was no open bracket
                bracketCharacter in closeBrackets
                        && openBracketsCounter[closedToOpenBrackets.getValue(bracketCharacter)] == 0 -> InvalidCluster
                // Case when we close the bracket
                else -> {
                    val openBracket = closedToOpenBrackets.getValue(bracketCharacter)
                    val currentOpenBracketCount = openBracketsCounter[openBracket]
                    // Case when we close the bracket but there is more open brackets pending
                    if (accumulatorValue.lastOrNull() == openBracket || currentOpenBracketCount > 0) {
                        val newOpenBracketsCounter = openBracketsCounter.decrement(bracketCharacter)
                        val accumulatorValue = buildString {
                            append(accumulatorValue)
                            append(bracketCharacter)
                            if (newOpenBracketsCounter[openBracket] == 0) append(clusterEndChar)
                        }

                        ValidCluster(accumulatorValue, newOpenBracketsCounter)
                    } else {
                        // Case when we add close the bracket but there is no more open brackets to close
                        InvalidCluster
                    }
                }
            }
        }

        /**
         * Classed used to track opened brackets
         */
        data class OpenBracketsCounter(
            // Corresponds to `(`
            private val roundBracketCounter: Int = 0,
            // Corresponds to `{`
            private val curlyBracketCounter: Int = 0,
            // Corresponds to `[`
            private val squareBracketCounter: Int = 0
        ) {
            val anyOpen: Boolean get() = roundBracketCounter != 0 || curlyBracketCounter != 0 || squareBracketCounter != 0

            fun increment(bracket: Char): OpenBracketsCounter {
                return when (bracket) {
                    '(' -> this.copy(roundBracketCounter = roundBracketCounter + 1)
                    '{' -> this.copy(curlyBracketCounter = curlyBracketCounter + 1)
                    '[' -> this.copy(squareBracketCounter = squareBracketCounter + 1)
                    else -> error("Unknown bracket character: $bracket")
                }
            }

            fun decrement(bracket: Char): OpenBracketsCounter {
                return when (bracket) {
                    ')' -> this.copy(roundBracketCounter = (roundBracketCounter - 1).coerceAtLeast(0))
                    '}' -> this.copy(curlyBracketCounter = (curlyBracketCounter - 1).coerceAtLeast(0))
                    ']' -> this.copy(squareBracketCounter = (squareBracketCounter - 1).coerceAtLeast(0))
                    else -> error("Unknown bracket character: $bracket")
                }
            }

            operator fun get(bracketChar: Char): Int {
                return when (bracketChar) {
                    '(' -> roundBracketCounter
                    '{' -> curlyBracketCounter
                    '[' -> squareBracketCounter
                    else -> error("Unknown bracket character: $bracketChar")
                }
            }
        }
    }
}

internal fun String.splitToBracketsClusters(): List<String> {
    val initial = ClusterProcessing.ValidCluster()

    return this.fold<ClusterProcessing>(initial) { clusterResult, bracketCharacter ->
        clusterResult.process(bracketCharacter)
    }.clusters
}

fun main() {
    val expressionsToClustersCatalog = mapOf(
        "()()()" to listOf("()", "()", "()"),
        "((()))" to listOf("((()))"),
        "())" to emptyList(),
        "()(" to emptyList(),
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
