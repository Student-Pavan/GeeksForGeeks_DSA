# Check for BST

---

# 📝 Problem Statement

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

**Constraints:**
- The number of nodes in the tree is in the range [1, 10^4].
- Each node's value is in the range [-2^31, 2^31 - 1].

---

# 💡 Intuition

The key insight is that for a BST, every node must satisfy the BST property relative to its ancestors. A brute force approach would involve checking every node against all other nodes, but this would be inefficient. The optimal approach uses recursion to enforce the BST property as we traverse the tree, passing along the valid range for each node's value.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach checks for each node whether all nodes in its left subtree are smaller and all nodes in its right subtree are larger. This involves nested traversals for each node, leading to O(n^2) time complexity.

---

## 🔹 Algorithm

1. For each node, traverse the entire left subtree to ensure all values are smaller than the current node.
2. Traverse the entire right subtree to ensure all values are larger than the current node.
3. Repeat this process recursively for all nodes.

---

## 🔹 Code

```java
class Solution {
    public boolean isBST(Node root) {
        if (root == null) return true;

        // Check left subtree values
        if (!checkLeftSubtree(root.left, root.data)) return false;

        // Check right subtree values
        if (!checkRightSubtree(root.right, root.data)) return false;

        // Recursively check subtrees
        return isBST(root.left) && isBST(root.right);
    }

    private boolean checkLeftSubtree(Node node, int val) {
        if (node == null) return true;
        if (node.data >= val) return false;
        return checkLeftSubtree(node.left, val) && checkLeftSubtree(node.right, val);
    }

    private boolean checkRightSubtree(Node node, int val) {
        if (node == null) return true;
        if (node.data <= val) return false;
        return checkRightSubtree(node.left, val) && checkRightSubtree(node.right, val);
    }
}
```

---

## 🔹 Dry Run

Let's consider the following BST:

```
      4
     / \
    2   5
   / \
  1   3
```

| Step | Node | Left Check | Right Check | Result |
|------|------|------------|-------------|--------|
| 1    | 4    | All left values < 4 | All right values > 4 | True |
| 2    | 2    | All left values < 2 | All right values > 2 | True |
| 3    | 1    | No left subtree | No right subtree | True |
| 4    | 3    | No left subtree | No right subtree | True |
| 5    | 5    | No left subtree | No right subtree | True |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses recursion with a range check. For each node, we ensure its value lies within a valid range (min, max). The left child's value must be less than the parent's value, and the right child's value must be greater. This approach ensures each node is visited only once, leading to O(n) time complexity.

---

## 🔹 Why This Works

This approach efficiently enforces the BST property by maintaining valid ranges for each node. By passing the valid range down the tree, we ensure that each node's value adheres to the BST property relative to its ancestors.

---

## 🔹 Algorithm

1. Start with the root node and set the initial range to (Long.MIN_VALUE, Long.MAX_VALUE).
2. For each node, check if its value lies within the current range.
3. Recursively check the left subtree with the range (min, node.data).
4. Recursively check the right subtree with the range (node.data, max).

---

## 🔹 Code

```java
class Solution {
    public boolean isBST(Node root) {
        return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean checkBST(Node root, long min, long max) {
        if (root == null) return true;
        if (root.data <= min || root.data >= max) return false;
        return checkBST(root.left, min, root.data) && checkBST(root.right, root.data, max);
    }
}
```

---

## 🔹 Detailed Dry Run

Using the same BST as before:

```
      4
     / \
    2   5
   / \
  1   3
```

| Step | Node | Min | Max | Valid? | Action |
|------|------|-----|-----|--------|--------|
| 1    | 4    | -∞  | +∞  | True   | Check left and right |
| 2    | 2    | -∞  | 4   | True   | Check left and right |
| 3    | 1    | -∞  | 2   | True   | No children |
| 4    | 3    | 2   | 4   | True   | No children |
| 5    | 5    | 4   | +∞  | True   | No children |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty tree (should return true)
- Single node tree (should return true)
- Left-skewed tree
- Right-skewed tree
- Tree with duplicate values (should return false)
- Large tree with all values equal (should return false)
- Tree with minimum and maximum integer values

---

# 📚 Key Takeaways

1. The optimal approach leverages recursion to enforce the BST property efficiently.
2. The range-checking technique ensures each node is validated in constant time.
3. Understanding the BST property is crucial for solving this problem.
4. Recursive traversal is a common pattern in tree problems.

---

# 🚀 Interview Tips

1. Clarify if duplicate values are allowed (typically not in BST).
2. Discuss the trade-offs between recursive and iterative approaches.
3. Mention that in-order traversal can also be used to check BST properties.
4. Be prepared to explain the time and space complexity trade-offs.

---

# ✅ Conclusion

The optimal solution using range checking is preferred because it efficiently validates the BST property in O(n) time with O(n) space complexity. The key insight is maintaining valid ranges for each node during traversal, ensuring the BST property is enforced at every level of the tree.