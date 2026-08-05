# Minimum Depth of a Binary Tree

---

# 📝 Problem Statement

Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

**Objective**: Find the minimum depth of a binary tree.

**Input**:
- A binary tree represented by its root node.

**Output**:
- An integer representing the minimum depth of the tree.

**Constraints**:
- The number of nodes in the tree is in the range [0, 10^5].
- -1000 <= Node.val <= 1000

---

# 💡 Intuition

The key insight is that the minimum depth of a binary tree is determined by the shortest path from the root to any leaf node. For a tree, this means we need to traverse the tree while keeping track of the depth at which we encounter the first leaf node.

The optimal approach involves using a breadth-first search (BFS) strategy, which naturally explores nodes level by level, allowing us to find the shortest path efficiently.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses a depth-first search (DFS) to explore all paths from the root to the leaves and keeps track of the minimum depth encountered.

1. Perform a DFS traversal of the tree.
2. For each node, recursively calculate the depth of the left and right subtrees.
3. If a node is a leaf (both left and right children are null), return 1.
4. Otherwise, return the minimum depth of the left and right subtrees plus 1.
5. Keep track of the minimum depth encountered during the traversal.

## 🔹 Algorithm

1. If the root is null, return 0.
2. If the root is a leaf node (both left and right children are null), return 1.
3. Recursively calculate the minimum depth of the left and right subtrees.
4. Return the minimum of the left and right subtree depths plus 1.

## 🔹 Code

```java
class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
    int minDepth(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
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

| Step | Node | Left | Right | Action | Depth |
|------|------|------|-------|--------|-------|
| 1    | 3    | 9    | 20    | Recurse left and right | - |
| 2    | 9    | null | null  | Leaf node | 1 |
| 3    | 20   | 15   | 7     | Recurse left and right | - |
| 4    | 15   | null | null  | Leaf node | 1 |
| 5    | 7    | null | null  | Leaf node | 1 |
| 6    | 20   | 15   | 7     | Return min(1, 1) + 1 | 2 |
| 7    | 3    | 9    | 20    | Return min(1, 2) + 1 | 2 |

The minimum depth of the tree is 2.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) where N is the number of nodes in the tree. |
| Space Complexity | O(H) where H is the height of the tree, due to the recursion stack. |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a breadth-first search (BFS) to traverse the tree level by level. The first time we encounter a leaf node, we can immediately return the current depth, as BFS guarantees that this is the shortest path.

1. Initialize a queue to perform BFS.
2. Start with the root node and a depth of 1.
3. While the queue is not empty, dequeue a node and check if it is a leaf node.
4. If it is a leaf node, return the current depth.
5. Otherwise, enqueue the left and right children of the node with an incremented depth.
6. Repeat until a leaf node is found.

## 🔹 Why This Works

BFS is suitable for finding the shortest path in an unweighted tree because it explores all nodes at the present depth level before moving on to nodes at the next depth level. This ensures that the first leaf node encountered is the one with the minimum depth.

## 🔹 Algorithm

1. If the root is null, return 0.
2. Initialize a queue with the root node and a depth of 1.
3. While the queue is not empty:
   a. Dequeue a node and its depth.
   b. If the node is a leaf node, return the depth.
   c. Enqueue the left child of the node with depth + 1.
   d. Enqueue the right child of the node with depth + 1.
4. If the queue is empty and no leaf node was found, return 0.

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;
    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
    int minDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                if (current.left == null && current.right == null) {
                    return depth;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            depth++;
        }
        return depth;
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

| Step | Queue | Current Node | Depth | Action |
|------|-------|--------------|-------|--------|
| 1    | [3]   | 3            | 1     | Enqueue left and right children |
| 2    | [9, 20]| 9           | 1     | Leaf node, return depth |
| 3    | [20]  | 9            | 1     | Leaf node found, return 1 |

The minimum depth of the tree is 2.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) where N is the number of nodes in the tree. |
| Space Complexity | O(W) where W is the maximum width of the tree, due to the queue storage. |

---

# 🔍 Edge Cases

- **Empty Tree**: The tree has no nodes. The minimum depth should be 0.
- **Single Node Tree**: The tree has only one node (the root). The minimum depth should be 1.
- **Left-Skewed Tree**: The tree is left-skewed, meaning each node has only a left child. The minimum depth should be equal to the number of nodes.
- **Right-Skewed Tree**: The tree is right-skewed, meaning each node has only a right child. The minimum depth should be equal to the number of nodes.
- **Balanced Tree**: The tree is balanced, meaning the left and right subtrees of every node have the same depth. The minimum depth should be equal to the depth of the tree.

---

# 📚 Key Takeaways

- **BFS for Shortest Path**: BFS is efficient for finding the shortest path in an unweighted tree or graph.
- **DFS for Depth Calculation**: DFS can also be used to calculate the minimum depth, but it may not be as efficient as BFS for this specific problem.
- **Edge Cases**: Always consider edge cases such as empty trees, single node trees, and skewed trees to ensure the solution is robust.

---

# 🚀 Interview Tips

- **Follow-Up Questions**:
  - Can you solve this problem using a depth-first search (DFS) approach?
  - How would you handle a binary tree with millions of nodes?
- **Common Pitfalls**:
  - Forgetting to handle the case where the tree is empty.
  - Not considering the case where the tree is skewed.
- **Alternative Approaches**:
  - Using a stack to simulate DFS.
  - Using a recursive approach to calculate the depth.

---

# ✅ Conclusion

The optimal approach using BFS is preferred for finding the minimum depth of a binary tree because it efficiently explores the tree level by level, ensuring the shortest path is found quickly. The key insight is recognizing that BFS is well-suited for shortest path problems in unweighted structures. The minimum depth of a binary tree is crucial in various applications, including game development, pathfinding algorithms, and network routing.

---

# 🎨 Formatting Rules

- Proper markdown headings and separators are used.
- Syntax-highlighted code blocks are included.
- Markdown tables are used extensively for dry runs and complexity analysis.
- The README is visually clean and GitHub-readable.