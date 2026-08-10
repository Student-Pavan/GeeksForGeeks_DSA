# 📌 Flatten Binary Tree to Linked List

---

# 📝 Problem Statement

Given the root of a binary tree, flatten the tree into a "linked list":

- The "linked list" should use the same `TreeNode` class where `right` child points to the next node in the list and `left` child is always `null`.
- The order of nodes in the list should be the same as a pre-order traversal of the binary tree.

**Constraints:**
- The number of nodes in the tree is in the range `[0, 2000]`.
- `-100 <= Node.val <= 100`

---

# 💡 Intuition

The key insight is that we need to perform a pre-order traversal (root, left, right) and restructure the tree as we traverse. The optimal approach uses a reverse post-order traversal to efficiently flatten the tree in O(n) time with O(h) space (where h is the height of the tree).

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform a pre-order traversal to collect all nodes in a list.
2. Iterate through the list and adjust the pointers to create the linked list.

## 🔹 Algorithm

1. Perform pre-order traversal and store nodes in a list.
2. Iterate through the list and set each node's left to null and right to the next node in the list.

## 🔹 Code

```java
class Solution {
    private List<TreeNode> list = new ArrayList<>();

    public void flatten(TreeNode root) {
        if (root == null) return;

        preOrder(root);

        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i + 1);
        }
    }

    private void preOrder(TreeNode node) {
        if (node == null) return;

        list.add(node);
        preOrder(node.left);
        preOrder(node.right);
    }
}
```

## 🔹 Dry Run

Let's dry run with the following tree:

```
    1
   / \
  2   5
 / \   \
3   4   6
```

| Step | Node | Action | List |
|------|------|--------|------|
| 1    | 1    | Add to list | [1] |
| 2    | 2    | Add to list | [1, 2] |
| 3    | 3    | Add to list | [1, 2, 3] |
| 4    | 4    | Add to list | [1, 2, 3, 4] |
| 5    | 5    | Add to list | [1, 2, 3, 4, 5] |
| 6    | 6    | Add to list | [1, 2, 3, 4, 5, 6] |

After pre-order traversal, we have the list `[1, 2, 3, 4, 5, 6]`.

Now, we iterate through the list and adjust pointers:

| Iteration | Current Node | Next Node | Action |
|-----------|--------------|-----------|--------|
| 1         | 1            | 2         | 1.right = 2, 1.left = null |
| 2         | 2            | 3         | 2.right = 3, 2.left = null |
| 3         | 3            | 4         | 3.right = 4, 3.left = null |
| 4         | 4            | 5         | 4.right = 5, 4.left = null |
| 5         | 5            | 6         | 5.right = 6, 5.left = null |

Final flattened tree:

```
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use a reverse post-order traversal (right, left, root).
2. Maintain a `prev` pointer to keep track of the last node processed.
3. As we traverse, we adjust the pointers to create the linked list.

## 🔹 Why This Works

This approach efficiently flattens the tree in a single pass without using extra space for a list. The reverse post-order ensures that we process the right subtree first, then the left subtree, and finally the root, allowing us to build the linked list in the correct order.

## 🔹 Algorithm

1. Initialize a `prev` pointer to null.
2. Perform a reverse post-order traversal:
   - Traverse the right subtree.
   - Traverse the left subtree.
   - Process the current node by setting its right to `prev` and left to null, then update `prev` to the current node.

## 🔹 Code

```java
class Solution {
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run with the same tree:

```
    1
   / \
  2   5
 / \   \
3   4   6
```

| Step | Node | Action | prev | Tree State |
|------|------|--------|------|-------------|
| 1    | 6    | Process 6 | 6 | 6 -> null |
| 2    | 5    | Process 5 | 5 | 5 -> 6 |
| 3    | 4    | Process 4 | 4 | 4 -> 5 |
| 4    | 3    | Process 3 | 3 | 3 -> 4 |
| 5    | 2    | Process 2 | 2 | 2 -> 3 |
| 6    | 1    | Process 1 | 1 | 1 -> 2 |

Final flattened tree:

```
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# 🔍 Edge Cases

- Empty tree: The function should handle it gracefully.
- Single node: The tree remains the same.
- Left-skewed tree: The function should flatten it correctly.
- Right-skewed tree: The function should flatten it correctly.
- Balanced tree: The function should handle it efficiently.

---

# 📚 Key Takeaways

- The optimal approach uses a reverse post-order traversal to flatten the tree in O(n) time with O(h) space.
- The brute force approach uses O(n) space to store the nodes and then reconstructs the tree.
- Understanding the traversal order is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask if the tree can be modified or if a new tree needs to be created.
- Consider the space complexity constraints and discuss the optimal approach's efficiency.

---

# ✅ Conclusion

The optimal approach using reverse post-order traversal is preferred as it efficiently flattens the tree in O(n) time with O(h) space. The key insight is leveraging the traversal order to build the linked list in a single pass.