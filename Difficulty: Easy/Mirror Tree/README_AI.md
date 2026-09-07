# 📌 Mirror Tree

---

# 📝 Problem Statement

Given the root of a binary tree, invert the tree and return its root.

**Example 1:**

```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]
```

**Example 2:**

```
Input: root = [2,1,3]
Output: [2,3,1]
```

**Example 3:**

```
Input: root = []
Output: []
```

**Constraints:**

- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

---

# 💡 Intuition

The key insight here is that mirroring a binary tree involves swapping the left and right children of every node recursively. This can be done using either a recursive or iterative approach. The recursive approach is more intuitive, while the iterative approach using a queue is more efficient for large trees.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses a recursive method to traverse the tree and swap the left and right children of each node.

1. **Base Case:** If the root is `null`, return.
2. **Recursive Case:** Swap the left and right children of the current node.
3. **Recursive Calls:** Recursively call the function on the left and right children of the current node.

## 🔹 Algorithm

1. If the root is `null`, return.
2. Swap the left and right children of the root.
3. Recursively call the function on the left child.
4. Recursively call the function on the right child.

## 🔹 Code

```java
class Solution {
    public Node mirror(Node root) {
        if (root == null) {
            return null;
        }
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
        return root;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the input `[4,2,7,1,3,6,9]`.

| Step | Current Node | Left Child | Right Child | Action | State |
|---|---|---|---|---|---|
| 1 | 4 | 2 | 7 | Swap left and right children | [4,7,2,1,3,6,9] |
| 2 | 7 | 6 | 9 | Swap left and right children | [4,7,2,9,6,3,1] |
| 3 | 2 | 1 | 3 | Swap left and right children | [4,7,2,3,1,9,6] |
| 4 | 1 | null | null | No children to swap | [4,7,2,3,1,9,6] |
| 5 | 3 | null | null | No children to swap | [4,7,2,3,1,9,6] |
| 6 | 9 | null | null | No children to swap | [4,7,2,3,1,9,6] |
| 7 | 6 | null | null | No children to swap | [4,7,2,3,1,9,6] |

The final state of the tree is `[4,7,2,3,1,9,6]`, which is the mirrored version of the input tree.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses an iterative method with a queue to traverse the tree and swap the left and right children of each node. This approach is more efficient for large trees as it avoids the overhead of recursive calls.

1. **Initialization:** Create a queue and enqueue the root node.
2. **Traversal:** While the queue is not empty, dequeue a node, swap its left and right children, and enqueue its children.
3. **Termination:** Return the root node.

## 🔹 Why This Works

This approach ensures that each node is visited exactly once, and the left and right children are swapped for each node. The queue ensures that the nodes are processed in the correct order, and the time complexity remains linear.

## 🔹 Algorithm

1. If the root is `null`, return `null`.
2. Create a queue and enqueue the root node.
3. While the queue is not empty:
   - Dequeue a node.
   - Swap the left and right children of the node.
   - Enqueue the left child if it is not `null`.
   - Enqueue the right child if it is not `null`.
4. Return the root node.

## 🔹 Code

```java
class Solution {
    public Node mirror(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Node temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return root;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `[4,2,7,1,3,6,9]`.

| Step | Current Node | Left Child | Right Child | Action | State |
|---|---|---|---|---|---|
| 1 | 4 | 2 | 7 | Swap left and right children | [4,7,2,1,3,6,9] |
| 2 | 7 | 6 | 9 | Swap left and right children | [4,7,2,9,6,3,1] |
| 3 | 2 | 1 | 3 | Swap left and right children | [4,7,2,3,1,9,6] |
| 4 | 1 | null | null | No children to swap | [4,7,2,3,1,9,6] |
| 5 | 3 | null | null | No children to swap | [4,7,2,3,1,9,6] |
| 6 | 9 | null | null | No children to swap | [4,7,2,3,1,9,6] |
| 7 | 6 | null | null | No children to swap | [4,7,2,3,1,9,6] |

The final state of the tree is `[4,7,2,3,1,9,6]`, which is the mirrored version of the input tree.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Tree:** The function should return `null`.
- **Single Node Tree:** The function should return the same node.
- **Skewed Tree:** The function should invert the tree correctly.
- **Balanced Tree:** The function should invert the tree correctly.

---

# 📚 Key Takeaways

- Mirroring a binary tree can be done using either a recursive or iterative approach.
- The recursive approach is more intuitive but has higher space complexity due to the call stack.
- The iterative approach using a queue is more efficient for large trees and has linear space complexity.
- The time complexity for both approaches is linear, O(n), where n is the number of nodes in the tree.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you solve this problem using an iterative approach?
  - What is the time and space complexity of your solution?
- **Common Pitfalls:**
  - Forgetting to handle the base case where the root is `null`.
  - Not swapping the children of all nodes.
- **Alternative Approaches:**
  - Using a stack instead of a queue for the iterative approach.
  - Using a depth-first search (DFS) approach.

---

# ✅ Conclusion

The optimal approach using an iterative method with a queue is preferred for mirroring a binary tree. It ensures that each node is visited exactly once, and the left and right children are swapped for each node. The time complexity remains linear, and the space complexity is optimized to O(n), making it efficient for large trees.