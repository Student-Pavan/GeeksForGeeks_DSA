# 📌 Height of Binary Tree

---

# 📝 Problem Statement

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

**Constraints:**
- The number of nodes in the tree is in the range [0, 10^4].
- -100 <= Node.val <= 100

---

# 💡 Intuition

The key insight is that the height of a binary tree can be determined by recursively calculating the heights of its left and right subtrees. The height of the tree is then the maximum of these two subtree heights plus one (for the current node).

This approach naturally follows the recursive structure of tree traversal, making it an elegant solution to this problem.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves calculating the height of each subtree separately and then combining the results. This is essentially the same as the optimal approach, but we'll implement it separately to demonstrate the thought process.

1. If the root is null, return -1 (indicating an empty tree).
2. Recursively calculate the height of the left subtree.
3. Recursively calculate the height of the right subtree.
4. Return the maximum of the left and right subtree heights plus one.

## 🔹 Algorithm

1. Base Case: If the root is null, return -1.
2. Recursively calculate the height of the left subtree.
3. Recursively calculate the height of the right subtree.
4. Return the maximum of the left and right heights plus one.

## 🔹 Code

```java
class Solution {
    public int height(Node root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree:

```
        3
       / \
      9  20
         / \
        15  7
```

| Step | Left Height | Right Height | Action | State |
|------|-------------|--------------|--------|-------|
| 1    | -1          | -1           | Base case | Root: 3 |
| 2    | -1          | -1           | Base case | Root: 9 |
| 3    | -1          | -1           | Base case | Root: 20 |
| 4    | -1          | -1           | Base case | Root: 15 |
| 5    | -1          | -1           | Base case | Root: 7 |
| 6    | 0           | 1            | Return max(left, right) + 1 | Root: 15 |
| 7    | 1           | 2            | Return max(left, right) + 1 | Root: 20 |
| 8    | 1           | 2            | Return max(left, right) + 1 | Root: 3 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach because it already provides the most efficient solution to the problem. The algorithm visits each node exactly once, making it optimal in terms of time complexity. The space complexity is determined by the recursion stack, which in the worst case (a skewed tree) could be O(n), but for a balanced tree, it's O(log n).

## 🔹 Why This Works

This approach works because it leverages the recursive nature of tree traversal. By calculating the height of each subtree separately and combining the results, we ensure that we account for the longest path in the tree. The base case handles empty trees, and the recursive case builds up the solution by combining results from smaller subproblems.

## 🔹 Algorithm

1. Base Case: If the root is null, return -1.
2. Recursively calculate the height of the left subtree.
3. Recursively calculate the height of the right subtree.
4. Return the maximum of the left and right heights plus one.

## 🔹 Code

```java
class Solution {
    public int height(Node root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following binary tree:

```
        3
       / \
      9  20
         / \
        15  7
```

| Step | Left Height | Right Height | Action | State |
|------|-------------|--------------|--------|-------|
| 1    | -1          | -1           | Base case | Root: 3 |
| 2    | -1          | -1           | Base case | Root: 9 |
| 3    | -1          | -1           | Base case | Root: 20 |
| 4    | -1          | -1           | Base case | Root: 15 |
| 5    | -1          | -1           | Base case | Root: 7 |
| 6    | 0           | 1            | Return max(left, right) + 1 | Root: 15 |
| 7    | 1           | 2            | Return max(left, right) + 1 | Root: 20 |
| 8    | 1           | 2            | Return max(left, right) + 1 | Root: 3 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# 🔍 Edge Cases

- **Empty Tree:** The tree has no nodes.
- **Single Node Tree:** The tree has only one node.
- **Left-Skewed Tree:** All nodes have only left children.
- **Right-Skewed Tree:** All nodes have only right children.
- **Balanced Tree:** The tree is perfectly balanced.

---

# 📚 Key Takeaways

- The height of a binary tree can be efficiently calculated using a recursive approach.
- The time complexity is O(n) because each node is visited exactly once.
- The space complexity is O(h) due to the recursion stack, where h is the height of the tree.
- This problem is a classic example of a recursive tree traversal problem.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you solve this problem iteratively using a stack or queue?
  - Can you solve this problem in O(1) space complexity?
- **Common Pitfalls:**
  - Forgetting to handle the base case for an empty tree.
  - Incorrectly calculating the height by not adding 1 for the current node.
- **Alternative Approaches:**
  - Using an iterative approach with a stack to simulate the recursion.
  - Using level-order traversal to count the number of levels.

---

# ✅ Conclusion

The optimal solution for calculating the height of a binary tree is a recursive approach that efficiently traverses the tree and combines the results from subtrees. This solution is both time and space efficient, making it suitable for interview scenarios and real-world applications. The key insight is recognizing the recursive nature of the problem and leveraging it to build an elegant and efficient solution.