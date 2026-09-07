# Search a Node in BST

---

# 📝 Problem Statement

Given a Binary Search Tree (BST), the task is to search a given key in the BST. If the key is found, return true; otherwise, return false.

**Input:**
- A BST represented by its root node
- An integer key to search for

**Output:**
- `true` if the key is found in the BST
- `false` otherwise

**Constraints:**
- The number of nodes in the BST is in the range [0, 10^4]
- Each node's value is unique
- The BST is properly structured (left subtree < root < right subtree)

---

# 💡 Intuition

The key insight is leveraging the BST property where for any given node:
- All values in the left subtree are less than the node's value
- All values in the right subtree are greater than the node's value

This property allows us to eliminate half of the tree with each comparison, leading to an efficient search operation.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves searching the entire tree, regardless of the BST property. This would be similar to a general binary tree search.

## 🔹 Algorithm

1. If the root is null, return false
2. If the root's value equals the key, return true
3. Recursively search the left subtree
4. Recursively search the right subtree
5. If neither subtree contains the key, return false

## 🔹 Code

```java
class Solution {
    public boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        return search(root.left, key) || search(root.right, key);
    }
}
```

## 🔹 Dry Run

Let's consider a BST with the following structure:
```
        50
       /  \
     30    70
    / \   / \
  20  40 60 80
```

Searching for key = 60:

| Step | Current Node | Action | Result |
|------|--------------|--------|--------|
| 1    | 50           | Compare 60 > 50 | Search right subtree |
| 2    | 70           | Compare 60 < 70 | Search left subtree |
| 3    | 60           | Found key | Return true |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) in worst case (when tree is skewed) |
| Space Complexity | O(h) where h is the height of the tree (recursion stack) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the BST property to eliminate half of the tree with each comparison, reducing the search space exponentially.

## 🔹 Why This Works

By comparing the key with the current node's value, we can determine whether to search the left or right subtree, effectively halving the search space at each step. This leads to a much more efficient search operation.

## 🔹 Algorithm

1. If the root is null, return false
2. If the root's value equals the key, return true
3. If the key is less than the root's value, recursively search the left subtree
4. If the key is greater than the root's value, recursively search the right subtree

## 🔹 Code

```java
class Solution {
    public boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (key < root.data) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }
}
```

## 🔹 Detailed Dry Run

Using the same BST as before (50, 30, 70, 20, 40, 60, 80) and searching for key = 40:

| Step | Current Node | Key Comparison | Action |
|------|--------------|----------------|--------|
| 1    | 50           | 40 < 50        | Search left subtree |
| 2    | 30           | 40 > 30        | Search right subtree |
| 3    | 40           | 40 == 40       | Found key |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h) where h is the height of the tree |
| Space Complexity | O(h) where h is the height of the tree (recursion stack) |

---

# 🔍 Edge Cases

1. Empty tree (root is null)
2. Key is the root node's value
3. Key is in the leftmost node
4. Key is in the rightmost node
5. Key is not present in the tree
6. Tree with only one node
7. Large BST with many nodes

---

# 📚 Key Takeaways

1. BST property enables efficient searching by eliminating half of the tree with each comparison
2. The optimal approach has better time complexity than brute force
3. Recursion is a natural way to implement BST operations
4. Understanding tree traversal is crucial for BST operations

---

# 🚀 Interview Tips

1. Ask about the expected size of the BST to understand time complexity expectations
2. Consider iterative solutions for large trees to avoid recursion stack limits
3. Be prepared to discuss alternative approaches like iterative traversal
4. Practice visualizing BST structures to better understand the problem

---

# ✅ Conclusion

The optimal solution leverages the BST property to perform efficient searching with O(h) time complexity, where h is the height of the tree. This is significantly better than the brute force approach which has O(n) time complexity. The key insight is recognizing that we can eliminate half of the tree with each comparison, making the search operation much more efficient.

The optimal solution demonstrates how understanding the properties of a data structure can lead to significant performance improvements in algorithm design.