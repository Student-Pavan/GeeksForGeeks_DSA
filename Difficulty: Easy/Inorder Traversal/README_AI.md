# 📌 Inorder Traversal

---

# 📝 Problem Statement

Given the root of a binary tree, return the inorder traversal of its nodes' values.

**Constraints:**
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

---

# 💡 Intuition

Inorder traversal follows the Left-Root-Right pattern. The key insight is that we need to process the left subtree first, then the root node, and finally the right subtree. This recursive approach naturally captures this pattern.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to implement inorder traversal. We recursively traverse the left subtree, visit the root node, and then recursively traverse the right subtree.

---

## 🔹 Algorithm

1. Create an empty list to store the result.
2. Define a helper function that takes a node as input.
3. If the node is null, return.
4. Recursively call the helper function on the left child.
5. Add the value of the current node to the result list.
6. Recursively call the helper function on the right child.
7. Call the helper function starting from the root node.
8. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left, right;
    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        inorderTraversal(root, ans);
        return ans;
    }

    private void inorderTraversal(Node root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }

        // Left
        inorderTraversal(root.left, ans);

        // Root
        ans.add(root.data);

        // Right
        inorderTraversal(root.right, ans);
    }
}
```

---

## 🔹 Dry Run

Let's consider the following binary tree:

```
      1
       \
        2
       /
      3
```

| Step | Current Node | Action | Result List |
|---|---|---|---|
| 1 | 1 | Visit left subtree | [] |
| 2 | null | Return | [] |
| 3 | 1 | Add 1 to result | [1] |
| 4 | 1 | Visit right subtree | [1] |
| 5 | 2 | Visit left subtree | [1] |
| 6 | null | Return | [1] |
| 7 | 2 | Add 2 to result | [1, 2] |
| 8 | 2 | Visit right subtree | [1, 2] |
| 9 | null | Return | [1, 2] |
| 10 | 3 | Visit left subtree | [1, 2] |
| 11 | null | Return | [1, 2] |
| 12 | 3 | Add 3 to result | [1, 2, 3] |
| 13 | 3 | Visit right subtree | [1, 2, 3] |
| 14 | null | Return | [1, 2, 3] |

Final result: [1, 2, 3]

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach also uses recursion but is more concise. We directly add the node values to the result list in the order of left-root-right.

---

## 🔹 Why This Works

This approach works because it follows the inorder traversal pattern exactly. The recursion naturally handles the left and right subtrees, and the root node is processed in between.

---

## 🔹 Algorithm

1. Create an empty list to store the result.
2. Define a helper function that takes a node as input.
3. If the node is null, return.
4. Recursively call the helper function on the left child.
5. Add the value of the current node to the result list.
6. Recursively call the helper function on the right child.
7. Call the helper function starting from the root node.
8. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left, right;
    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        inorderTraversal(root, ans);
        return ans;
    }

    private void inorderTraversal(Node root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }

        // Left
        inorderTraversal(root.left, ans);

        // Root
        ans.add(root.data);

        // Right
        inorderTraversal(root.right, ans);
    }
}
```

---

## 🔹 Detailed Dry Run

Let's consider the following binary tree:

```
      1
     / \
    2   3
   /
  4
```

| Step | Current Node | Action | Result List |
|---|---|---|---|
| 1 | 1 | Visit left subtree | [] |
| 2 | 2 | Visit left subtree | [] |
| 3 | 4 | Visit left subtree | [] |
| 4 | null | Return | [] |
| 5 | 4 | Add 4 to result | [4] |
| 6 | 4 | Visit right subtree | [4] |
| 7 | null | Return | [4] |
| 8 | 2 | Add 2 to result | [4, 2] |
| 9 | 2 | Visit right subtree | [4, 2] |
| 10 | null | Return | [4, 2] |
| 11 | 1 | Add 1 to result | [4, 2, 1] |
| 12 | 1 | Visit right subtree | [4, 2, 1] |
| 13 | 3 | Visit left subtree | [4, 2, 1] |
| 14 | null | Return | [4, 2, 1] |
| 15 | 3 | Add 3 to result | [4, 2, 1, 3] |
| 16 | 3 | Visit right subtree | [4, 2, 1, 3] |
| 17 | null | Return | [4, 2, 1, 3] |

Final result: [4, 2, 1, 3]

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty tree
- Tree with only one node
- Left-skewed tree
- Right-skewed tree
- Balanced tree

---

# 📚 Key Takeaways

- Inorder traversal follows the Left-Root-Right pattern.
- Recursion is a natural way to implement tree traversals.
- The time complexity is O(n) as we visit each node exactly once.
- The space complexity is O(n) due to the recursion stack.

---

# 🚀 Interview Tips

- Be prepared to discuss iterative solutions using stacks.
- Consider edge cases like empty trees and skewed trees.
- Practice drawing the traversal path to understand the order of node visits.

---

# ✅ Conclusion

The optimal solution uses recursion to implement inorder traversal efficiently. The key insight is recognizing the Left-Root-Right pattern and applying it recursively. This approach ensures that we visit each node exactly once, resulting in an O(n) time complexity. The space complexity is also O(n) due to the recursion stack, making it suitable for trees of moderate size.