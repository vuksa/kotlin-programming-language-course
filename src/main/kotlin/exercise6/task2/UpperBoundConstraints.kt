package exercise6.task2

/**
 * Task 2: The Upper-Bound Constraints
 *
 * In this task, you need to add a constraint to the generic type parameter of function calculateTreeCost,
 * so that the function body becomes compilable. The function calculates a sum of costs of all tree nodes,
 * and we want to be able to call it with trees holding values of a different type.
 *
 * Once the type constraint is introduced, the code of calculateTreeCost function will become compilable.
 */

/**
 * A binary tree node that holds some value.
 */
private data class TreeNode<T>(val value: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null
}

/**
 * Objects that implement this interface will have a cost property.
 */
private interface CostElement {
    var cost: Double
}

/**
 * The node is a set intersection if its left and right children are not null. Otherwise, it is
 * just a set of some values. The cost is an estimated cost of calculating the intersection.
 */
private data class SetIntersection(val size: Int): CostElement {
    override var cost: Double = size.toDouble()
}

/**
 * In this generic function we traverse the tree and calculate the total cost of traversing as the sum of costs at all
 * nodes. This code is not yet compilable, but it will become compilable as soon as you add an upper bound to the generic
 * type.
 */
private fun <T: CostElement> calculateTreeCost(root: TreeNode<T>): Double {
    return root.value.cost +
            (root.left?.let { calculateTreeCost(it) } ?: 0.0) +
            (root.right?.let { calculateTreeCost(it) } ?: 0.0)
}