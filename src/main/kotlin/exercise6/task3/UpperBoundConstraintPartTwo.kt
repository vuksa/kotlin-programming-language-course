package exercise6.task3

/**
 * Task 3: Upper-Bound Constraints II
 *
 * In this task, you need to add a constraint to the generic type parameter of function estimateIntersectionSize,
 * so that the function body becomes compilable. The function estimates the size of the intersection of
 * two sets of values by comparing the upper and lower bounds of values in both sets.
 * The function will work only if the value type supports total ordering, and the task is to specify this constraint.
 * A tricky part is the expected upper bound itself is a generic interface, and its type parameter will be exactly
 * the same as the function type parameter, that is, the type parameter becomes recursive.
 *
 * Once the type constraint is introduced, the code of estimateIntersectionSize function will become compilable.
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
interface CostElement {
    var cost: Double
}

/**
 * Set intersection objects are supposed to serve as values in a tree that represents the plan of calculation
 * the optimal way to find an intersection of N sets.
 *
 * This object is an intersection of two sets if its left and right children in the tree are not null.
 * Otherwise, it is just an initial set of some values. The cost is an estimated cost of calculating the intersection
 * of two child sets.
 */
private data class SetIntersection<S>(val size: Int, val lowerBound: S?, val upperBound: S?): CostElement {
    override var cost: Double = size.toDouble()
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
private fun <T: Comparable<T>> estimateIntersectionSize(treeNode: TreeNode<SetIntersection<T>>): Int {
    val leftNode = treeNode.left ?: return treeNode.value.size
    val rightNode = treeNode.right ?: return treeNode.value.size

    if (leftNode.value.lowerBound == null || rightNode.value.lowerBound == null) {
        return 0
    }
    if (leftNode.value.upperBound == null || rightNode.value.upperBound == null) {
        return 0
    }

    if (leftNode.value.upperBound < rightNode.value.lowerBound || leftNode.value.lowerBound > rightNode.value.upperBound) {
        return 0
    }
    return (leftNode.value.size + rightNode.value.size)/2
}