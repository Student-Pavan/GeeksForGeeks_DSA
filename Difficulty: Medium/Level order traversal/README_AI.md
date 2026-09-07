# 📌 Level Order Traversal

---

# 📝 Problem Statement

Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

**Constraints:**
- The number of nodes in the tree is in the range [0, 2000].
- -1000 <= Node.val <= 1000

---

# 💡 Intuition

The key insight is that level order traversal requires visiting nodes level by level, from left to right. This is naturally achieved using a breadth-first search (BFS) approach with a queue. By processing nodes level by level, we can collect their values in the correct order.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to traverse the tree. We recursively visit the left and right subtrees and collect the node values level by level. However, this approach is less efficient because it doesn't leverage the natural ordering provided by BFS.

---

## 🔹 Algorithm

1. If the root is null, return an empty list.
2. Initialize a queue with the root node.
3. While the queue is not empty:
   - Get the current level size.
   - For each node in the current level:
     - Dequeue the node and add its value to the result list.
     - Enqueue its left and right children if they exist.
4. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}

class Solution {
    public ArrayList<Integer> levelOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                ans.add(currentNode.data);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return ans;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree:

```
      1
     / \
    2   3
   / \   \
  4   5   6
```

| Step | Queue | Current Node | Action | Result |
|------|-------|--------------|--------|--------|
| 1    | [1]   | 1            | Enqueue 2, 3 | [1] |
| 2    | [2, 3]| 2            | Enqueue 4, 5 | [1, 2] |
| 3    | [3, 4, 5]| 3         | Enqueue 6 | [1, 2, 3] |
| 4    | [4, 5, 6]| 4         | - | [1, 2, 3, 4] |
| 5    | [5, 6]| 5            | - | [1, 2, 3, 4, 5] |
| 6    | [6]   | 6            | - | [1, 2, 3, 4, 5, 6] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) where N is the number of nodes in the tree |
| Space Complexity | O(N) for the queue |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses BFS with a queue to traverse the tree level by level. This approach is efficient because it processes each node exactly once and uses the queue to keep track of nodes at the current level.

---

## 🔹 Why This Works

This approach works because BFS naturally processes nodes level by level, which is exactly what we need for level order traversal. The queue ensures that we visit nodes in the correct order and efficiently manage the traversal.

---

## 🔹 Algorithm

1. If the root is null, return an empty list.
2. Initialize a queue with the root node.
3. While the queue is not empty:
   - Get the current level size.
   - For each node in the current level:
     - Dequeue the node and add its value to the result list.
     - Enqueue its left and right children if they exist.
4. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int val) {
        data = val;
        left = null;
        right = null;
    }
}

class Solution {
    public ArrayList<Integer> levelOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                ans.add(currentNode.data);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return ans;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following binary tree:

```
      1
     / \
    2   3
   / \   \
  4   5   6
```

| Step | Queue | Current Node | Action | Result |
|------|-------|--------------|--------|--------|
| 1    | [1]   | 1            | Enqueue 2, 3 | [1] |
| 2    | [2, 3]| 2            | Enqueue 4, 5 | [1, 2] |
| 3    | [3, 4, 5]| 3         | Enqueue 6 | [1, 2, 3] |
| 4    | [4, 5, 6]| 4         | - | [1, 2, 3, 4] |
| 5    | [5, 6]| 5            | - | [1, 2, 3, 4, 5] |
| 6    | [6]   | 6            | - | [1, 2, 3, 4, 5, 6] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) where N is the number of nodes in the tree |
| Space Complexity | O(N) for the queue |

---

# 🔍 Edge Cases

- Empty tree: The algorithm should return an empty list.
- Single node tree: The algorithm should return a list containing the value of the single node.
- Skewed tree: The algorithm should handle trees that are skewed to the left or right.
- Large tree: The algorithm should efficiently handle trees with a large number of nodes.

---

# 📚 Key Takeaways

- Level order traversal can be efficiently achieved using BFS with a queue.
- The queue helps manage the nodes level by level, ensuring the correct order of traversal.
- Understanding BFS is crucial for solving tree traversal problems.

---

# 🚀 Interview Tips

- Discuss the trade-offs between BFS and DFS for level order traversal.
- Mention that BFS is more suitable for level order traversal due to its natural level-by-level processing.
- Be prepared to discuss the time and space complexity of the solution.

---

# ✅ Conclusion

The optimal approach using BFS with a queue is the most efficient way to perform level order traversal of a binary tree. It ensures that nodes are visited level by level, from left to right, and efficiently handles the traversal process.