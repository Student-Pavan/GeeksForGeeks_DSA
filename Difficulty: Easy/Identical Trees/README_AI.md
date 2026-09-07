# 📌 Identical Trees

---

# 📝 Problem Statement

Given two binary trees, determine if they are identical. Two binary trees are considered identical if they have the same structure and the same node values.

**Objective:**
Write a function to check if two binary trees are identical.

**Input:**
- Two binary tree roots `r1` and `r2`

**Output:**
- `true` if the trees are identical, `false` otherwise

**Constraints:**
- The number of nodes in both trees will not exceed 1000
- Node values will be integers

---

# 💡 Intuition

The key insight is that two trees are identical if:
1. Both trees are empty (null)
2. Both trees have the same root value
3. The left subtrees are identical
4. The right subtrees are identical

This recursive approach naturally checks all these conditions simultaneously.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses a recursive method to compare each node of both trees:
1. If both nodes are null, return true
2. If one node is null and the other isn't, return false
3. If node values differ, return false
4. Recursively check left and right subtrees

## 🔹 Algorithm

1. Base case: If both nodes are null, return true
2. If either node is null, return false
3. If node values differ, return false
4. Recursively check left subtrees
5. Recursively check right subtrees
6. Return true only if all checks pass

## 🔹 Code

```java
class Node {
    int data;
    Node left, right;
    Node(int d) {
        data = d;
        left = right = null;
    }
}

class Solution {
    public boolean isIdentical(Node r1, Node r2) {
        // Base case: both nodes are null
        if (r1 == null && r2 == null) {
            return true;
        }

        // If one is null and the other isn't
        if (r1 == null || r2 == null) {
            return false;
        }

        // If values differ
        if (r1.data != r2.data) {
            return false;
        }

        // Recursively check left and right subtrees
        return isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);
    }
}
```

## 🔹 Dry Run

Let's dry run with two identical trees:

```
Tree 1:        1
             /   \
            2     3

Tree 2:        1
             /   \
            2     3
```

| Step | Node1 | Node2 | Action | Result |
|------|-------|-------|--------|--------|
| 1    | 1     | 1     | Compare values | Equal |
| 2    | 2     | 2     | Compare values | Equal |
| 3    | null  | null  | Both null | True |
| 4    | 3     | 3     | Compare values | Equal |
| 5    | null  | null  | Both null | True |

Final result: true

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the number of nodes in the tree |
| Space Complexity | O(h) where h is the height of the tree (recursion stack) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is identical to the brute force approach in this case because:
1. We must visit every node to verify identity
2. The recursive solution naturally handles the tree structure
3. There's no way to optimize further without additional information

## 🔹 Why This Works

This solution works because:
1. It systematically checks every corresponding node pair
2. The recursion naturally handles the tree's hierarchical structure
3. It catches all possible differences between trees

## 🔹 Algorithm

The algorithm remains the same as the brute force approach:
1. Base case: both nodes null → true
2. One node null → false
3. Values differ → false
4. Recursively check left and right

## 🔹 Code

```java
class Node {
    int data;
    Node left, right;
    Node(int d) {
        data = d;
        left = right = null;
    }
}

class Solution {
    public boolean isIdentical(Node r1, Node r2) {
        // Base case: both nodes are null
        if (r1 == null && r2 == null) {
            return true;
        }

        // If one is null and the other isn't
        if (r1 == null || r2 == null) {
            return false;
        }

        // If values differ
        if (r1.data != r2.data) {
            return false;
        }

        // Recursively check left and right subtrees
        return isIdentical(r1.left, r2.left) && isIdentical(r1.right, r2.right);
    }
}
```

## 🔹 Detailed Dry Run

Using the same example as before:

```
Tree 1:        1
             /   \
            2     3

Tree 2:        1
             /   \
            2     3
```

| Step | Node1 | Node2 | Action | Result |
|------|-------|-------|--------|--------|
| 1    | 1     | 1     | Compare values | Equal |
| 2    | 2     | 2     | Compare values | Equal |
| 3    | null  | null  | Both null | True |
| 4    | 3     | 3     | Compare values | Equal |
| 5    | null  | null  | Both null | True |

Final result: true

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the number of nodes in the tree |
| Space Complexity | O(h) where h is the height of the tree (recursion stack) |

---

# 🔍 Edge Cases

1. Both trees are empty
2. One tree is empty, the other isn't
3. Trees with different structures
4. Trees with same values but different structures
5. Trees with negative values
6. Trees with duplicate values
7. Very large trees (approaching constraint limits)

---

# 📚 Key Takeaways

1. Tree comparison requires checking both structure and values
2. Recursion is natural for tree traversal problems
3. The optimal solution matches the brute force approach in this case
4. Time complexity is linear with respect to the number of nodes
5. Space complexity depends on the tree's height

---

# 🚀 Interview Tips

1. Clarify if the trees are guaranteed to be balanced
2. Discuss alternative approaches like iterative solutions
3. Ask about handling null values explicitly
4. Consider edge cases during the interview
5. Be prepared to explain the recursion stack behavior

---

# ✅ Conclusion

The optimal solution is preferred because:
1. It's the most straightforward approach for tree comparison
2. It handles all edge cases properly
3. The time and space complexity are optimal for this problem
4. The recursive approach is clean and easy to understand

The key insight is that tree identity requires checking both structure and values simultaneously, which the recursive approach handles perfectly.