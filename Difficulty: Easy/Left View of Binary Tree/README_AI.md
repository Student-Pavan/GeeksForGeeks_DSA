# Left View of Binary Tree

---

# 📝 Problem Statement

Given a Binary Tree, print the left view of it. The left view of a Binary Tree is the set of nodes visible when the tree is visited from the left side.

**Input:**
A binary tree represented by its root node.

**Output:**
An array containing the left view of the binary tree.

**Constraints:**
- The number of nodes in the tree is in the range [0, 100].
- -1000 <= Node.val <= 1000

---

# 💡 Intuition

The left view of a binary tree consists of the first node encountered at each level when traversing the tree from left to right. This can be efficiently captured using a level-order traversal (BFS) approach, where we only consider the first node of each level.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a recursive method to traverse the tree and keep track of the current level. For each level, we record the first node encountered.

## 🔹 Algorithm

1. Initialize a list to store the left view nodes.
2. Perform a recursive traversal of the tree.
3. For each node, check if it is the first node of its level.
4. If it is, add it to the left view list.
5. Recursively traverse the left and right subtrees, incrementing the level.

## 🔹 Code

```java
import java.util.ArrayList;

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
    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        leftViewUtil(root, 1, result);
        return result;
    }

    void leftViewUtil(Node node, int level, ArrayList<Integer> result) {
        if (node == null) return;

        if (level > result.size()) {
            result.add(node.data);
        }

        leftViewUtil(node.left, level + 1, result);
        leftViewUtil(node.right, level + 1, result);
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree:

```
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```

| Level | Node | Action | Result |
|-------|------|--------|--------|
| 1     | 1    | Add to result | [1] |
| 2     | 2    | Add to result | [1, 2] |
| 3     | 4    | Add to result | [1, 2, 4] |
| 4     | null | Skip | [1, 2, 4] |
| 3     | 5    | Skip | [1, 2, 4] |
| 2     | 3    | Skip | [1, 2, 4] |
| 3     | 6    | Skip | [1, 2, 4] |
| 4     | null | Skip | [1, 2, 4] |
| 3     | 7    | Skip | [1, 2, 4] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(H) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using a level-order traversal (BFS) to traverse the tree. For each level, we only consider the first node encountered.

## 🔹 Why This Works

This approach efficiently captures the left view by leveraging the BFS traversal, which naturally processes nodes level by level. By only recording the first node of each level, we ensure that we get the left view of the tree.

## 🔹 Algorithm

1. Initialize a queue to perform BFS traversal.
2. Enqueue the root node.
3. While the queue is not empty:
   - Determine the number of nodes at the current level.
   - For each node in the current level:
     - If it is the first node of the level, add it to the result list.
     - Enqueue its left and right children if they exist.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
    ArrayList<Integer> leftView(Node root) {
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        if (root == null) return result;

        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();

                if (i == 0) {
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

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following binary tree:

```
        1
       / \
      2   3
     / \ / \
    4  5 6  7
```

| Queue State | Current Node | Action | Result |
|-------------|--------------|--------|--------|
| [1] | 1 | Add to result | [1] |
| [2, 3] | 2 | Add to result | [1, 2] |
| [3, 4, 5] | 3 | Skip | [1, 2] |
| [4, 5, 6, 7] | 4 | Add to result | [1, 2, 4] |
| [5, 6, 7] | 5 | Skip | [1, 2, 4] |
| [6, 7] | 6 | Skip | [1, 2, 4] |
| [7] | 7 | Skip | [1, 2, 4] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

---

# 🔍 Edge Cases

- **Empty Tree:** The tree has no nodes.
- **Single Node:** The tree has only one node.
- **Left-Skewed Tree:** All nodes have only left children.
- **Right-Skewed Tree:** All nodes have only right children.
- **Full Binary Tree:** All levels are completely filled.

---

# 📚 Key Takeaways

- The left view of a binary tree can be efficiently captured using a level-order traversal.
- The brute force approach uses recursion to track the current level and record the first node of each level.
- The optimal approach leverages BFS to traverse the tree level by level, ensuring the first node of each level is recorded.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you modify the solution to get the right view of the tree?
  - Can you solve this problem using DFS instead of BFS?
- **Common Pitfalls:**
  - Forgetting to handle the case where the tree is empty.
  - Incorrectly tracking the level during traversal.
- **Alternative Approaches:**
  - Using DFS with a level tracker.
  - Using a stack to simulate BFS.

---

# ✅ Conclusion

The optimal approach using BFS is preferred for its efficiency and clarity. It ensures that we capture the left view of the binary tree in O(N) time and O(N) space complexity. The key insight is leveraging level-order traversal to record the first node of each level, providing the left view of the tree.