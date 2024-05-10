package exercise6.task4

/**
 * Task 4" Upper-Bound Constraints III
 *
 * In this task, we still want to add a constraint to the generic type parameters of function estimateIntersectionSize,
 * so that the function body became compilable. However, the difference is that now the function argument is a tree node
 * that contains a value of a generic type, not necessarily a SetIntersection instance.
 * Type parameter constraints should combine a few interfaces that the values in the tree nodes are supposed to implement.
 * We will use a where keyword to define the constraints.
 *
 * Once the type constraint is introduced, the code of estimateIntersectionSize
 * function will become compilable without additional changes.
 *
 * Also, you need to add a simple type constraint into printTree function so that it could traverse and
 * print trees composed of the values of different types, e.g., SetIntersection and Decision.
 */

/**
 * A binary tree node that holds some user-defined value.
 */
private data class TreeNode<T>(val value: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null
}

/**
 * This is an element of the cost function that shows the cost of intersecting a few sets.
 */
private interface CostElement {
    var cost: Double
}

/**
 * This is a range of comparable values. The object that implements this interface holds the values in
 * [lowerBound ... upperBound] range. Null value in the lower-bound stands for "minus infinity", and null
 * value in the upper-bound stands for "plus infinity".
 */
private interface Range<T: Comparable<T>> {
    val lowerBound: T?
    val upperBound: T?
}

/**
 * The object that implements this interface allows for measuring its size.
 */
private interface Measurable {
    val size: Int
}


/**
 * Person objects are supposed to serve as values in a genealogical binary tree.
 */
private data class Person(val name: String, val birthYear: Int, val nick: String? = null)

/**
 * Decision objects are supposed to serve as values in a decision tree. The cost is the cost of taking the decision.
 */
private data class Decision(val question: String, val answerLeft: String, val answerRight: String): CostElement {
    override var cost: Double = 0.0
}

/**
 * Set intersection objects are supposed to serve as values in a tree that represents the plan of calculation
 * the optimal way to find an intersection of N sets.
 *
 * This object is an intersection of two sets, if its left and right children in the tree are not null.
 * Otherwise, it is just an initial set of some values. The cost is an estimated cost of calculating the intersection
 * of two child sets, and the size is the estimated size of the intersection.
 * Upper and lower bounds are generic values that indicate the upper and lower bounds of the entire set.
 */
private data class SetIntersection<T: Comparable<T>>(
    override val size: Int,
    override val lowerBound: T?,
    override val upperBound: T?)
    : Measurable, CostElement, Range<T> {

    override var cost: Double = 0.0
}


/**
 * This function estimates the size of intersection of two sets.
 *
 * If the input tree node is a leaf node (any of its child nodes is null) then it returns the node value.
 *
 * Otherwise, it compares the upper and lower bounds of two child sets and returns 0 if it turns out that the sets have
 * no intersection at all. If the intersection is likely to be not empty, it estimates the intersection size as the mean
 * of the set sizes.
 */
private fun <T, S> estimateIntersectionSize(treeNode: TreeNode<T>) : Int where T: Range<S>, T: Measurable, S: Comparable<S> {
    val leftNode = treeNode.left ?: return treeNode.value.size
    val rightNode = treeNode.right ?: return treeNode.value.size

    if (leftNode.value.lowerBound == null || rightNode.value.lowerBound == null) {
        return 0
    }
    if (leftNode.value.upperBound == null || rightNode.value.upperBound == null) {
        return 0
    }

    if (leftNode.value.upperBound!! < rightNode.value.lowerBound!! || leftNode.value.lowerBound!! > rightNode.value.upperBound!!) {
        return 0
    }
    return (leftNode.value.size + rightNode.value.size)/2
}

private fun <T: CostElement> printTree(treeNode: TreeNode<T>) {
    println("${treeNode.value} cost:${treeNode.value.cost}")
    treeNode.left?.let { printTree(it) }
    treeNode.right?.let { printTree(it) }
}

/**
 * In this generic function we traverse the tree and calculate the total cost of traversing as the sum of costs at all
 * nodes. This code is not yet compilable, but it will become compilable as soon as you add an upper bound to the generic
 * type.
 */
private fun <T: CostElement> calculateTreeCost(root: TreeNode<T>): Double {
    // PLEASE DON'T CHANGE THE CODE OF THIS FUNCTION.
    return root.value.cost +
            (root.left?.let { calculateTreeCost(it) } ?: 0.0) +
            (root.right?.let { calculateTreeCost(it) } ?: 0.0)
}

/**
 * In this function we create the root nodes of two binary trees, one with Person value and another with
 * Decision value. This code is not yet compilable, but it will become compilable as soon as you make a generic
 * TreeNode class.
 */
private fun printNodes(): String {
    // PLEASE DON'T CHANGE THE CODE OF THIS FUNCTION.
    val richardI = Person("Richard I", 1157, "The Lionheart")
    val genealogyTree = TreeNode(richardI)

    val decisionI = Decision("Do you choose a red or a blue pill?", "Red", "Blue")
    val decisionTree = TreeNode(decisionI)
    return "${genealogyTree.value.name} ${decisionTree.value.answerLeft} cost: ${calculateTreeCost(decisionTree)}"
}

private fun run() {
    val leftNode = TreeNode(SetIntersection(10, 5, 15))
    val rightNode = TreeNode(SetIntersection(20, 10, 30))
    val rootNode = TreeNode(SetIntersection<Int>(-1, null, null)).also {
        it.left = leftNode
        it.right = rightNode
    }
    val intersectionSize = estimateIntersectionSize(rootNode)
    println(intersectionSize)

    printTree(rootNode)
    printNodes()
}

fun main() {
    run()
}