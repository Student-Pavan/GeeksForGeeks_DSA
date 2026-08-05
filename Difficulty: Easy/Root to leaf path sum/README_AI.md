# Root to Leaf Path Sum

---

# 📝 Problem Statement

Given a binary tree and a sum, determine if there exists a root-to-leaf path such that adding up all the node values along the path equals the given sum.

**Objective**: Implement a function to check if such a path exists.

**Input**:
- A binary tree root node
- An integer target sum

**Output**:
- `true` if there exists a root-to-leaf path with sum equal to target
- `false` otherwise

**Constraints**:
- The number of nodes in the tree is in the range [0, 5000]
- -1000 ≤ Node.val ≤ 1000
- -1000 ≤ targetSum ≤ 1000

---

# 💡 Intuition

The problem requires checking if any path from root to leaf in a binary tree sums to a given target. The key insight is that we need to explore all possible root-to-leaf paths while keeping track of the current sum.

The optimal approach uses recursion to traverse the tree while maintaining a running sum. When we reach a leaf node, we check if the running sum equals the target. This approach efficiently explores all paths without unnecessary computations.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using recursion to explore all possible paths from the root to the leaves. For each node, we add its value to a running sum. When we reach a leaf node, we check if the running sum matches the target.

## 🔹 Algorithm

1. Start with the root node and an initial sum of 0.
2. Recursively traverse the left and right subtrees, adding each node's value to the running sum.
3. When a leaf node is reached, check if the running sum equals the target sum.
4. If any path matches the target, return true; otherwise, return false.

## 🔹 Code

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree and target sum of 22:

```
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \      \
  7    2      1
```

| Step | Current Node | Current Sum | Action | Result |
|------|--------------|--------------|--------|--------|
| 1    | 5            | 0            | Start  |        |
| 2    | 4            | 5            | Left   |        |
| 3    | 11           | 9            | Left   |        |
| 4    | 7            | 20           | Left   |        |
| 5    | 7            | 20           | Check  | Not leaf |
| 6    | 2            | 20           | Right  |        |
| 7    | 2            | 22           | Check  | Leaf, sum matches |
| 8    | 2            | 22           | Return true |        |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the number of nodes in the tree |
| Space Complexity | O(h) where h is the height of the tree (due to recursion stack) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses recursion to traverse the tree and checks the sum at each leaf node. This approach efficiently explores all possible paths while maintaining a running sum.

## 🔹 Why This Works

This approach works because it recursively explores all root-to-leaf paths. By subtracting the current node's value from the target sum at each step, we ensure that we only need to check the sum at the leaf nodes, making the solution efficient and straightforward.

## 🔹 Algorithm

1. Start with the root node and the target sum.
2. Recursively traverse the left and right subtrees, subtracting the current node's value from the target sum.
3. When a leaf node is reached, check if the remaining target sum equals the leaf node's value.
4. If any path matches the target, return true; otherwise, return false.

## 🔹 Code

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same binary tree and target sum of 22:

```
        5
       / \
      4   8
     /   / \
    11  13  4
   /  \      \
  7    2      1
```

| Step | Current Node | Current Sum | Action | Result |
|------|--------------|--------------|--------|--------|
| 1    | 5            | 22           | Start  |        |
| 2    | 4            | 17           | Left   |        |
| 3    | 11           | 13           | Left   |        |
| 4    | 7            | 6            | Left   |        |
| 5    | 7            | 6            | Check  | Not leaf |
| 6    | 2            | 6            | Right  |        |
| 7    | 2            | 4            | Check  | Leaf, sum matches |
| 8    | 2            | 4            | Return true |        |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the number of nodes in the tree |
| Space Complexity | O(h) where h is the height of the tree (due to recursion stack) |

---

# 🔍 Edge Cases

- **Empty Tree**: If the tree is empty, the function should return false.
- **Single Node Tree**: If the tree has only one node, the function should check if the node's value equals the target sum.
- **Negative Values**: The function should handle negative values in the tree and target sum correctly.
- **Large Tree**: The function should efficiently handle large trees within the given constraints.
- **Multiple Paths**: The function should correctly identify if any path sums to the target, even if there are multiple paths.

---

# 📚 Key Takeaways

- **Recursion**: Recursion is a powerful tool for tree traversal problems.
- **Running Sum**: Maintaining a running sum while traversing the tree is a common pattern in tree problems.
- **Base Case**: Always consider the base case when dealing with recursion, especially for tree problems.
- **Efficiency**: The optimal approach efficiently explores all possible paths without unnecessary computations.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to find all root-to-leaf paths that sum to a given target.
- **Common Pitfalls**: Ensure that the base case correctly handles leaf nodes and empty trees.
- **Alternative Approaches**: Discuss how to solve the problem using iterative approaches, such as using a stack to simulate recursion.
- **Optimization Discussions**: Explain why the optimal approach is efficient and how it avoids unnecessary computations.

---

# ✅ Conclusion

The optimal approach efficiently checks for the existence of a root-to-leaf path that sums to a given target. By using recursion and maintaining a running sum, the solution is both intuitive and efficient. This approach ensures that all possible paths are explored while minimizing unnecessary computations. The key insight is recognizing the pattern of tree traversal and using recursion to maintain and check the running sum.