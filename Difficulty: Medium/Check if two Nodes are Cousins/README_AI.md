# Check if two Nodes are Cousins

---

# 📝 Problem Statement

Given a binary tree and two nodes `a` and `b`, determine if they are cousins. Two nodes are cousins if they are at the same level and have different parents.

**Constraints:**
- The number of nodes in the tree is in the range `[2, 100]`.
- `1 <= Node.data <= 100`
- All values in the tree are unique.

---

# 💡 Intuition

The key insight is that cousins must satisfy two conditions:
1. They must be at the same level in the tree
2. They must have different parents

A BFS approach works well here because:
- It naturally processes nodes level by level
- We can track parent-child relationships during traversal
- We can efficiently check for the two conditions

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform BFS to traverse the tree level by level
2. For each level, check if both nodes exist
3. If both nodes exist at the same level, check if they have different parents
4. If they have different parents, return true
5. If one node exists but not the other, return false
6. If neither node exists, continue to the next level

---

## 🔹 Algorithm

1. Initialize a queue for BFS and enqueue the root node
2. While the queue is not empty:
   - Get the current level size
   - Initialize flags for finding nodes a and b
   - For each node in the current level:
     - Check if the node's data matches a or b
     - Check if the node's children are a and b (to ensure different parents)
     - Enqueue the node's children
   - If both nodes are found in the current level and have different parents, return true
   - If only one node is found, return false
3. If the loop completes without finding both nodes, return false

---

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public boolean areCousins(Node root, int a, int b) {
        if (root == null || a == b) {
            return false;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean foundA = false;
            boolean foundB = false;

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (current.data == a) {
                    foundA = true;
                }
                if (current.data == b) {
                    foundB = true;
                }

                if (current.left != null && current.right != null) {
                    if ((current.left.data == a && current.right.data == b) ||
                        (current.left.data == b && current.right.data == a)) {
                        return false;
                    }
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            if (foundA && foundB) {
                return true;
            }
            if (foundA || foundB) {
                return false;
            }
        }

        return false;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with a sample tree:

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

Checking if nodes 4 and 6 are cousins:

| Step | Current Node | Found A | Found B | Action | Result |
|------|--------------|---------|---------|--------|--------|
| 1    | 1            | -       | -       | Enqueue 2, 3 | - |
| 2    | 2            | -       | -       | Enqueue 4, 5 | - |
| 3    | 3            | -       | -       | Enqueue 6 | - |
| 4    | 4            | true    | -       | - | - |
| 5    | 5            | true    | -       | - | - |
| 6    | 6            | true    | true    | Check parents | Different parents (2 and 3) |
| -    | -            | -       | -       | Return | true |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) - We visit each node exactly once |
| Space Complexity | O(n) - In the worst case, we store all nodes in the queue |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is essentially the same as the brute force approach since BFS is already optimal for this problem. We traverse the tree level by level, checking the two conditions for cousin nodes at each level.

---

## 🔹 Why This Works

This approach works because:
- BFS naturally processes nodes level by level
- We can efficiently track parent-child relationships during traversal
- We can immediately return when we find both nodes at the same level with different parents
- The solution handles all edge cases properly

---

## 🔹 Algorithm

The algorithm is identical to the brute force approach:
1. Perform BFS level by level
2. For each level, check if both nodes exist
3. If they exist at the same level with different parents, return true
4. Otherwise, continue to the next level

---

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public boolean areCousins(Node root, int a, int b) {
        if (root == null || a == b) {
            return false;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean foundA = false;
            boolean foundB = false;

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                if (current.data == a) {
                    foundA = true;
                }
                if (current.data == b) {
                    foundB = true;
                }

                if (current.left != null && current.right != null) {
                    if ((current.left.data == a && current.right.data == b) ||
                        (current.left.data == b && current.right.data == a)) {
                        return false;
                    }
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            if (foundA && foundB) {
                return true;
            }
            if (foundA || foundB) {
                return false;
            }
        }

        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

The dry run is identical to the brute force approach since the optimal solution uses the same algorithm.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) - We visit each node exactly once |
| Space Complexity | O(n) - In the worst case, we store all nodes in the queue |

---

# 🔍 Edge Cases

- Empty tree
- Tree with only one node
- Nodes are siblings (same parent)
- Nodes are at different levels
- One node is the root
- Nodes are in different subtrees
- Large tree with many levels
- Tree with duplicate values (though problem states all values are unique)

---

# 📚 Key Takeaways

- BFS is effective for level-order traversal problems
- Cousin nodes must satisfy two conditions: same level and different parents
- Early termination improves efficiency
- Parent-child relationships can be checked during traversal
- The solution handles all edge cases properly

---

# 🚀 Interview Tips

- Clarify if the tree can have duplicate values
- Discuss alternative approaches like DFS with parent tracking
- Consider if the problem can be solved with a single traversal
- Ask about the expected tree size and constraints
- Be prepared to explain time and space complexity

---

# ✅ Conclusion

The optimal BFS approach efficiently checks for cousin nodes by leveraging level-order traversal and parent-child relationship tracking. This solution is both time and space efficient, with O(n) complexity for both metrics, making it suitable for interview scenarios and production environments.