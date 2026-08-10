# Right View of Binary Tree

---

# 📝 Problem Statement

Given a binary tree, return the right view of the binary tree. The right view of a binary tree is the set of nodes visible when the tree is viewed from the right side.

**Constraints:**
- The number of nodes in the binary tree is in the range [0, 100].
- Node values are unique.

---

# 💡 Intuition

The right view of a binary tree consists of the last node of each level when viewed from the right side. This can be efficiently captured using a level-order traversal (BFS) approach, where we only consider the last node of each level.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform a level-order traversal (BFS) of the binary tree.
2. For each level, traverse all nodes and keep track of the last node.
3. Add the last node of each level to the result list.

## 🔹 Algorithm

1. Initialize a queue for BFS and a result list.
2. If the root is null, return the empty result list.
3. Enqueue the root node.
4. While the queue is not empty:
   - Get the current level size.
   - Traverse all nodes in the current level.
   - For each node, dequeue it and enqueue its children.
   - After processing all nodes in the level, add the last node's value to the result list.
5. Return the result list.

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    public ArrayList<Integer> rightView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                if (i == levelSize - 1) {
                    result.add(currentNode.data);
                }
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree:

```
      1
    /   \
   2     3
    \     \
     5     4
```

| Step | Queue | Current Node | Action | Result |
|------|-------|--------------|--------|--------|
| 1    | [1]   | 1            | Enqueue children (2, 3) | [] |
| 2    | [2, 3]| 2            | Enqueue children (5) | [] |
| 3    | [3, 5]| 3            | Enqueue child (4) | [] |
| 4    | [5, 4]| 5            | No children | [] |
| 5    | [4]   | 4            | No children | [1, 3, 4] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses a more efficient way to track the last node of each level. We can use a recursive DFS approach and keep track of the current level and the maximum level encountered so far.

## 🔹 Why This Works

The DFS approach allows us to visit nodes in a specific order, and by keeping track of the current level and the maximum level encountered, we can ensure that we only add the last node of each level to the result list.

## 🔹 Algorithm

1. Initialize a result list and a variable to keep track of the maximum level encountered.
2. Perform a DFS traversal of the binary tree.
3. For each node, if the current level is greater than the maximum level encountered, add the node's value to the result list and update the maximum level.
4. Recursively traverse the right subtree first, then the left subtree.
5. Return the result list.

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    public ArrayList<Integer> rightView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        rightViewHelper(root, 0, result);
        return result;
    }

    private void rightViewHelper(Node node, int level, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        if (level == result.size()) {
            result.add(node.data);
        }
        rightViewHelper(node.right, level + 1, result);
        rightViewHelper(node.left, level + 1, result);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the same binary tree as before:

```
      1
    /   \
   2     3
    \     \
     5     4
```

| Step | Current Node | Level | Action | Result |
|------|--------------|-------|--------|--------|
| 1    | 1            | 0     | Add to result | [1] |
| 2    | 3            | 1     | Add to result | [1, 3] |
| 3    | 4            | 2     | Add to result | [1, 3, 4] |
| 4    | 2            | 1     | Skip (level <= max level) | [1, 3, 4] |
| 5    | 5            | 2     | Skip (level <= max level) | [1, 3, 4] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# 🔍 Edge Cases

- Empty tree: The result should be an empty list.
- Single node tree: The result should contain only the root node's value.
- Skewed tree: The result should contain all nodes in the order they appear from the right side.
- Full binary tree: The result should contain the last node of each level.

---

# 📚 Key Takeaways

- The right view of a binary tree can be efficiently captured using a level-order traversal (BFS) or a depth-first search (DFS) approach.
- The BFS approach is straightforward and easy to understand, while the DFS approach is more efficient in terms of space complexity.
- Both approaches have a time complexity of O(n), where n is the number of nodes in the binary tree.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the BFS and DFS approaches.
- Ask about the constraints and expected input size to choose the appropriate approach.
- Be prepared to explain the time and space complexity of both approaches.

---

# ✅ Conclusion

The optimal approach using DFS is preferred for its space efficiency, especially for large binary trees. The key insight is to use a recursive DFS approach and keep track of the current level and the maximum level encountered to ensure that only the last node of each level is added to the result list.