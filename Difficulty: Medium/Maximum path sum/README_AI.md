# Maximum Path Sum

## Problem Statement

Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree, but the path must follow the parent-child connections.

**Example:**

```
Input:
        1
       / \
      2   3

Output: 6
Explanation: The maximum path sum is 2 + 1 + 3 = 6.
```

**Constraints:**
- The number of nodes in the tree is in the range [1, 3 * 10^4].
- -1000 <= Node.val <= 1000

---

## Intuition

The key insight is that the maximum path through a node can either:
1. Be the node itself
2. Include the node plus the maximum path from one of its subtrees
3. Include the node plus both left and right subtrees (forming a "bridge" path)

We need to calculate these possibilities recursively while keeping track of the global maximum.

---

## Brute Force Approach

### Approach

The brute force approach would be to consider every possible path in the tree:
1. For each node, consider all possible paths starting and ending at that node
2. Calculate the sum for each path
3. Keep track of the maximum sum found

This would require O(n^2) time complexity in the worst case.

### Algorithm

1. For each node in the tree:
   a. Find all paths that start and end at this node
   b. Calculate the sum for each path
   c. Update the maximum sum found

### Code

```java
class Solution {
    int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        findMaxPath(root);
        return maxSum;
    }

    private int findMaxPath(TreeNode node) {
        if (node == null) return 0;

        // Calculate left and right subtree sums
        int leftSum = Math.max(0, findMaxPath(node.left));
        int rightSum = Math.max(0, findMaxPath(node.right));

        // Update the global maximum
        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);

        // Return the maximum path sum that can be extended upwards
        return Math.max(leftSum, rightSum) + node.val;
    }
}
```

### Dry Run

Let's dry run the algorithm with the example tree:

```
        1
       / \
      2   3
```

| Step | Node | Left Sum | Right Sum | Current Max | Global Max |
|------|------|----------|-----------|-------------|------------|
| 1    | 2    | 0        | 0         | 2           | 2          |
| 2    | 3    | 0        | 0         | 3           | 3          |
| 3    | 1    | 2        | 3         | 6           | 6          |

### Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) (where h is the height of the tree) |

---

## Optimal Approach

### Approach

The optimal approach uses a post-order traversal to calculate the maximum path sum:
1. For each node, calculate the maximum path sum that can be extended upwards
2. While doing this, keep track of the maximum path sum that includes the current node as the root of the path

This approach efficiently calculates the solution in a single traversal of the tree.

### Why This Works

By calculating the maximum path sums from the bottom up, we ensure that we consider all possible paths that include each node as the highest point in the path. The algorithm efficiently combines these values to find the global maximum.

### Algorithm

1. Perform a post-order traversal of the tree
2. For each node:
   a. Calculate the maximum path sum from the left and right subtrees (discarding negative sums)
   b. Update the global maximum with the sum of the current node's value and both subtree sums
   c. Return the maximum path sum that can be extended upwards (either left or right subtree plus current node)

### Code

```java
class Solution {
    int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        findMaxPath(root);
        return maxSum;
    }

    private int findMaxPath(TreeNode node) {
        if (node == null) return 0;

        // Calculate left and right subtree sums (discarding negative sums)
        int leftSum = Math.max(0, findMaxPath(node.left));
        int rightSum = Math.max(0, findMaxPath(node.right));

        // Update the global maximum
        maxSum = Math.max(maxSum, leftSum + rightSum + node.val);

        // Return the maximum path sum that can be extended upwards
        return Math.max(leftSum, rightSum) + node.val;
    }
}
```

### Detailed Dry Run

Let's dry run the optimal solution with the same example tree:

```
        1
       / \
      2   3
```

| Step | Node | Left Sum | Right Sum | Current Max | Global Max |
|------|------|----------|-----------|-------------|------------|
| 1    | 2    | 0        | 0         | 2           | 2          |
| 2    | 3    | 0        | 0         | 3           | 3          |
| 3    | 1    | 2        | 3         | 6           | 6          |

### Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) (where h is the height of the tree) |

---

## Edge Cases

1. Tree with only one node
2. Tree with all negative values
3. Skewed tree (unbalanced)
4. Tree with duplicate values
5. Large tree (testing efficiency)

---

## Key Takeaways

1. Tree traversal patterns (post-order in this case)
2. How to track global maximum during traversal
3. The importance of discarding negative sums
4. The concept of "bridge" paths in trees

---

## Interview Tips

1. Be prepared to explain the time and space complexity
2. Consider asking if negative values are allowed
3. Be ready to discuss alternative approaches (like BFS with memoization)
4. Practice explaining the algorithm with a whiteboard

---

## Conclusion

The optimal solution efficiently finds the maximum path sum in O(n) time with O(h) space complexity by leveraging post-order traversal and careful tracking of maximum values. The key insight is recognizing that the maximum path through any node can be determined by combining values from its subtrees.