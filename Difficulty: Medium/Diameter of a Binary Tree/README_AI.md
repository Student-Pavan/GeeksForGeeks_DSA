# Diameter of a Binary Tree

---

# 📝 Problem Statement

Given a binary tree, find the diameter of the tree. The diameter of a tree is the number of nodes on the longest path between any two leaves in the tree.

**Constraints:**
- The number of nodes in the tree will be in the range [1, 10^4].
- The height of the tree will be in the range [1, 10^4].

---

# 💡 Intuition

The diameter of a binary tree is the longest path between any two nodes in the tree. This path may or may not pass through the root. The key insight is that the diameter can be calculated by finding the maximum of the following for each node:
- The diameter of the left subtree
- The diameter of the right subtree
- The longest path that goes through the current node (which is the sum of the heights of the left and right subtrees)

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves calculating the height of each node and then checking all possible paths that pass through that node. The diameter is the maximum of all these paths.

## 🔹 Algorithm

1. For each node in the tree:
   1. Calculate the height of the left subtree.
   2. Calculate the height of the right subtree.
   3. The diameter passing through the current node is the sum of the heights of the left and right subtrees.
2. The diameter of the tree is the maximum of all these diameters.

## 🔹 Code

```java
class Solution {
    public int diameterOfBinaryTree(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        return Math.max(leftHeight + rightHeight, Math.max(leftDiameter, rightDiameter));
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }
}
```

## 🔹 Dry Run

Let's consider the following binary tree:

```
        1
       / \
      2   3
     / \
    4   5
```

| Step | Node | Left Height | Right Height | Diameter | Action |
|------|------|-------------|--------------|----------|--------|
| 1    | 1    | 2           | 1            | 3        | Calculate height of left and right subtrees, then diameter |
| 2    | 2    | 1            | 1            | 2        | Calculate height of left and right subtrees, then diameter |
| 3    | 4    | 0            | 0            | 0        | Calculate height of left and right subtrees, then diameter |
| 4    | 5    | 0            | 0            | 0        | Calculate height of left and right subtrees, then diameter |
| 5    | 3    | 0            | 0            | 0        | Calculate height of left and right subtrees, then diameter |

The diameter of the tree is 3, which is the maximum of all the diameters calculated.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves calculating the diameter of the tree in a single traversal of the tree. This is done by keeping track of the height of each node and updating the diameter whenever a longer path is found.

## 🔹 Why This Works

By calculating the height of each node and updating the diameter whenever a longer path is found, we ensure that we only traverse the tree once. This reduces the time complexity from O(n^2) to O(n).

## 🔹 Algorithm

1. Initialize a variable `diameter` to store the diameter of the tree.
2. Perform a post-order traversal of the tree:
   1. For each node, calculate the height of the left and right subtrees.
   2. Update the diameter if the sum of the heights of the left and right subtrees is greater than the current diameter.
   3. Return the height of the current node, which is the maximum of the heights of the left and right subtrees plus one.
3. Return the `diameter` variable.

## 🔹 Code

```java
class Solution {
    int diameter = 0;

    public int diameterOfBinaryTree(Node root) {
        height(root);
        return diameter;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        diameter = Math.max(diameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
```

## 🔹 Detailed Dry Run

Let's consider the following binary tree:

```
        1
       / \
      2   3
     / \
    4   5
```

| Step | Node | Left Height | Right Height | Diameter | Action |
|------|------|-------------|--------------|----------|--------|
| 1    | 4    | 0            | 0            | 0        | Calculate height of left and right subtrees, then diameter |
| 2    | 5    | 0            | 0            | 0        | Calculate height of left and right subtrees, then diameter |
| 3    | 2    | 1            | 1            | 2        | Calculate height of left and right subtrees, then diameter |
| 4    | 3    | 0            | 0            | 0        | Calculate height of left and right subtrees, then diameter |
| 5    | 1    | 2            | 1            | 3        | Calculate height of left and right subtrees, then diameter |

The diameter of the tree is 3, which is the maximum of all the diameters calculated.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Tree:** The diameter of an empty tree is 0.
- **Single Node Tree:** The diameter of a single node tree is 0.
- **Left-Skewed Tree:** The diameter of a left-skewed tree is the number of nodes minus one.
- **Right-Skewed Tree:** The diameter of a right-skewed tree is the number of nodes minus one.
- **Balanced Tree:** The diameter of a balanced tree is the height of the tree.

---

# 📚 Key Takeaways

- The diameter of a binary tree can be calculated using a post-order traversal.
- The optimal approach reduces the time complexity from O(n^2) to O(n).
- The diameter of a tree is the longest path between any two nodes in the tree.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you solve this problem without using recursion?
  - Can you solve this problem using an iterative approach?
- **Common Pitfalls:**
  - Forgetting to update the diameter when calculating the height of the left and right subtrees.
  - Not considering the case where the longest path does not pass through the root.
- **Alternative Approaches:**
  - Using a queue to perform a level-order traversal and calculate the diameter.
- **Optimization Discussions:**
  - The optimal approach reduces the time complexity from O(n^2) to O(n).

---

# ✅ Conclusion

The optimal approach to calculating the diameter of a binary tree is to perform a post-order traversal and keep track of the height of each node. This approach ensures that we only traverse the tree once, reducing the time complexity from O(n^2) to O(n). The diameter of the tree is the maximum of the diameters of the left and right subtrees and the sum of the heights of the left and right subtrees.