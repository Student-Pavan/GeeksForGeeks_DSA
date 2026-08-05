# Connect Nodes of Levels

---

# 📝 Problem Statement

Given a binary tree, connect each node with its right neighbor at the same level. If no right neighbor exists, set the next pointer to `null`.

**Objective:**
Modify the tree so that each node's `nextRight` points to its right neighbor at the same level.

**Input:**
- A binary tree with `nextRight` pointers initialized to `null`.

**Output:**
- The same tree with `nextRight` pointers properly connected.

**Constraints:**
- The number of nodes in the tree is in the range `[0, 2^12 - 1]`.
- `-1000 <= Node.val <= 1000`

---

# 💡 Intuition

The key insight is that we need to traverse the tree level by level and connect nodes at the same level. The brute force approach uses a queue to process nodes level by level, while the optimal approach leverages the existing `nextRight` pointers to traverse the tree without using extra space.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Use a queue to perform a level-order traversal.
2. For each level, connect nodes from left to right.
3. Set the `nextRight` pointer of the last node in the level to `null`.

## 🔹 Algorithm

1. If the root is `null`, return `null`.
2. Initialize a queue and enqueue the root node.
3. While the queue is not empty:
   - Get the size of the current level.
   - Initialize a `prev` pointer to `null`.
   - For each node in the current level:
     - Dequeue the node.
     - If `prev` is not `null`, set `prev.nextRight` to the current node.
     - Set `prev` to the current node.
     - Enqueue the left and right children of the current node if they exist.
   - Set `prev.nextRight` to `null` after processing all nodes in the level.

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;
    Node nextRight;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
        nextRight = null;
    }
}

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node prev = null;

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (prev != null) {
                    prev.nextRight = current;
                }

                prev = current;

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            prev.nextRight = null;
        }

        return root;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree:

```
        1
       / \
      2   3
     / \   \
    4   5   7
```

| Step | Queue | Current Node | Prev Node | Action | State |
|---|---|---|---|---|---|
| 1 | [1] | 1 | null | Enqueue 1 | Queue: [1] |
| 2 | [1] | 1 | null | Dequeue 1, set prev to 1 | Queue: [], Prev: 1 |
| 3 | [2, 3] | 2 | 1 | Enqueue 2, set 1.nextRight to 2, set prev to 2 | Queue: [2, 3], Prev: 2 |
| 4 | [2, 3] | 3 | 2 | Enqueue 3, set 2.nextRight to 3, set prev to 3 | Queue: [3], Prev: 3 |
| 5 | [3] | 3 | null | Dequeue 3, set prev to 3 | Queue: [], Prev: 3 |
| 6 | [4, 5, 7] | 4 | 3 | Enqueue 4, set 3.nextRight to 4, set prev to 4 | Queue: [4, 5, 7], Prev: 4 |
| 7 | [4, 5, 7] | 5 | 4 | Enqueue 5, set 4.nextRight to 5, set prev to 5 | Queue: [5, 7], Prev: 5 |
| 8 | [5, 7] | 5 | 4 | Dequeue 5, set prev to 5 | Queue: [7], Prev: 5 |
| 9 | [5, 7] | 7 | 5 | Enqueue 7, set 5.nextRight to 7, set prev to 7 | Queue: [7], Prev: 7 |
| 10 | [7] | 7 | null | Dequeue 7, set prev to 7 | Queue: [], Prev: 7 |
| 11 | [] | - | 7 | Set 7.nextRight to null | Final Tree |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use the `nextRight` pointers to traverse the tree level by level without using extra space.
2. For each level, connect nodes from left to right using the existing `nextRight` pointers.

## 🔹 Why This Works

The optimal approach leverages the existing `nextRight` pointers to traverse the tree level by level, which eliminates the need for a queue and reduces the space complexity to O(1).

## 🔹 Algorithm

1. If the root is `null`, return `null`.
2. Initialize a `levelStart` pointer to the root node.
3. While `levelStart` is not `null`:
   - Initialize a `current` pointer to `levelStart`.
   - While `current` is not `null`:
     - If `current.left` is not `null`, set `current.left.nextRight` to `current.right`.
     - If `current.right` is not `null` and `current.nextRight` is not `null`, set `current.right.nextRight` to `current.nextRight.left`.
     - Move `current` to `current.nextRight`.
   - Move `levelStart` to `levelStart.left`.

## 🔹 Code

```java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node levelStart = root;

        while (levelStart != null) {
            Node current = levelStart;

            while (current != null) {
                if (current.left != null) {
                    current.left.nextRight = current.right;
                }

                if (current.right != null && current.nextRight != null) {
                    current.right.nextRight = current.nextRight.left;
                }

                current = current.nextRight;
            }

            levelStart = levelStart.left;
        }

        return root;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal algorithm with the same binary tree:

```
        1
       / \
      2   3
     / \   \
    4   5   7
```

| Step | Level Start | Current Node | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Set levelStart to 1 | Level Start: 1 |
| 2 | 1 | 1 | Set current to 1 | Current: 1 |
| 3 | 1 | 1 | Set 1.left.nextRight to 1.right | 2.nextRight: 3 |
| 4 | 1 | 1 | Set 1.right.nextRight to null (since 1.nextRight is null) | 3.nextRight: null |
| 5 | 1 | 1 | Move current to 1.nextRight (null) | Current: null |
| 6 | 1 | - | Move levelStart to 1.left (2) | Level Start: 2 |
| 7 | 2 | 2 | Set current to 2 | Current: 2 |
| 8 | 2 | 2 | Set 2.left.nextRight to 2.right | 4.nextRight: 5 |
| 9 | 2 | 2 | Set 2.right.nextRight to null (since 2.nextRight is null) | 5.nextRight: null |
| 10 | 2 | 2 | Move current to 2.nextRight (null) | Current: null |
| 11 | 2 | - | Move levelStart to 2.left (4) | Level Start: 4 |
| 12 | 4 | 4 | Set current to 4 | Current: 4 |
| 13 | 4 | 4 | Set 4.left.nextRight to 4.right (null) | 4.left.nextRight: null |
| 14 | 4 | 4 | Move current to 4.nextRight (5) | Current: 5 |
| 15 | 4 | 5 | Set 5.left.nextRight to 5.right (null) | 5.left.nextRight: null |
| 16 | 4 | 5 | Move current to 5.nextRight (null) | Current: null |
| 17 | 4 | - | Move levelStart to 4.left (null) | Level Start: null |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty tree
- Tree with only one node
- Tree with nodes only on the left
- Tree with nodes only on the right
- Tree with nodes at all levels

---

# 📚 Key Takeaways

- Level-order traversal can be used to connect nodes at the same level.
- The optimal approach leverages existing pointers to reduce space complexity.
- Understanding the structure of the tree is crucial for connecting nodes efficiently.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask about the constraints and expected time and space complexities.
- Be prepared to explain the intuition behind the optimal approach.

---

# ✅ Conclusion

The optimal approach is preferred because it reduces the space complexity from O(N) to O(1) by leveraging the existing `nextRight` pointers. The key insight is to traverse the tree level by level using the `nextRight` pointers to connect nodes efficiently.