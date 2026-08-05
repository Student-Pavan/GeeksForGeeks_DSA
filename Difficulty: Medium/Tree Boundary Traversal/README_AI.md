# 📌 Tree Boundary Traversal

---

# 📝 Problem Statement

Given a binary tree, the task is to print its boundary nodes in anti-clockwise direction starting from the root. Boundary nodes are defined as:
1. The root node
2. Left boundary (excluding leaves)
3. Leaf nodes (in order)
4. Right boundary (excluding leaves, in reverse)

The boundary should be printed in a single line.

**Example:**
```
Input:
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
      / \
     8   9

Output: 1 2 4 8 9 6 7 3
```

**Constraints:**
- 1 ≤ Number of nodes ≤ 10^5
- 1 ≤ Data of a node ≤ 10^5

---

# 💡 Intuition

The key insight is that the boundary traversal can be broken down into three distinct parts:
1. Left boundary (top-down, excluding leaves)
2. Leaf nodes (inorder traversal)
3. Right boundary (bottom-up, excluding leaves)

By combining these three parts, we can efficiently traverse the boundary of the tree in anti-clockwise order.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Traversing the left boundary from top to bottom
2. Collecting all leaf nodes using inorder traversal
3. Traversing the right boundary from bottom to top

## 🔹 Algorithm

1. If the tree is empty, return an empty list
2. If the tree has only one node, return that node's value
3. Add the root node's value to the result
4. Traverse the left boundary:
   - Start from the left child of the root
   - Move to the left child if it exists, otherwise move to the right child
   - Add non-leaf nodes to the result
5. Traverse the tree to collect all leaf nodes in order
6. Traverse the right boundary:
   - Start from the right child of the root
   - Move to the right child if it exists, otherwise move to the left child
   - Add non-leaf nodes to a stack
   - Pop from the stack to add nodes in reverse order

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        // Add root node
        res.add(root.data);

        // Add left boundary (excluding leaves)
        addLeftBoundary(root.left, res);

        // Add leaf nodes
        addLeaves(root.left, res);
        addLeaves(root.right, res);

        // Add right boundary (excluding leaves, in reverse)
        addRightBoundary(root.right, res);

        return res;
    }

    private void addLeftBoundary(Node node, ArrayList<Integer> res) {
        if (node == null) return;

        if (node.left != null) {
            res.add(node.data);
            addLeftBoundary(node.left, res);
        } else if (node.right != null) {
            res.add(node.data);
            addLeftBoundary(node.right, res);
        }
    }

    private void addLeaves(Node node, ArrayList<Integer> res) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            res.add(node.data);
            return;
        }

        addLeaves(node.left, res);
        addLeaves(node.right, res);
    }

    private void addRightBoundary(Node node, ArrayList<Integer> res) {
        if (node == null) return;

        if (node.right != null) {
            addRightBoundary(node.right, res);
            res.add(node.data);
        } else if (node.left != null) {
            addRightBoundary(node.left, res);
            res.add(node.data);
        }
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the example tree:

```
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
      / \
     8   9
```

1. Add root node: `res = [1]`
2. Add left boundary:
   - Start at node 2
   - Add 2 (not a leaf)
   - Move to left child (4)
   - Add 4 (not a leaf)
   - Move to left child (null)
   - Move to right child (null)
   - `res = [1, 2, 4]`
3. Add leaf nodes:
   - Left subtree of 2:
     - 4 is a leaf → add 4 (already added)
   - Right subtree of 2:
     - 5 is not a leaf
     - Left subtree of 5:
       - 8 is a leaf → add 8
     - Right subtree of 5:
       - 9 is a leaf → add 9
   - Left subtree of 3:
     - 6 is a leaf → add 6
   - Right subtree of 3:
     - 7 is a leaf → add 7
   - `res = [1, 2, 4, 8, 9, 6, 7]`
4. Add right boundary:
   - Start at node 3
   - Move to right child (7)
   - Add 7 (not a leaf)
   - Move to right child (null)
   - Move to left child (null)
   - `res = [1, 2, 4, 8, 9, 6, 7, 3]`

Final result: `[1, 2, 4, 8, 9, 6, 7, 3]`

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) where N is the number of nodes in the tree |
| Space Complexity | O(N) for the result storage |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but with a more efficient implementation. We can combine the left boundary, leaf nodes, and right boundary traversals into a single pass through the tree.

## 🔹 Why This Works

The optimal approach works because:
1. The left boundary is traversed top-down, adding non-leaf nodes
2. The leaf nodes are collected in order using inorder traversal
3. The right boundary is traversed bottom-up, adding non-leaf nodes in reverse order

This approach ensures that we visit each node exactly once, making it efficient.

## 🔹 Algorithm

1. If the tree is empty, return an empty list
2. If the tree has only one node, return that node's value
3. Add the root node's value to the result
4. Traverse the left boundary:
   - Start from the left child of the root
   - Move to the left child if it exists, otherwise move to the right child
   - Add non-leaf nodes to the result
5. Traverse the tree to collect all leaf nodes in order
6. Traverse the right boundary:
   - Start from the right child of the root
   - Move to the right child if it exists, otherwise move to the left child
   - Add non-leaf nodes to a stack
   - Pop from the stack to add nodes in reverse order

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;

        // Add root node
        res.add(root.data);

        // Add left boundary (excluding leaves)
        addLeftBoundary(root.left, res);

        // Add leaf nodes
        addLeaves(root.left, res);
        addLeaves(root.right, res);

        // Add right boundary (excluding leaves, in reverse)
        addRightBoundary(root.right, res);

        return res;
    }

    private void addLeftBoundary(Node node, ArrayList<Integer> res) {
        if (node == null) return;

        if (node.left != null || node.right != null) {
            res.add(node.data);
        }

        if (node.left != null) {
            addLeftBoundary(node.left, res);
        } else {
            addLeftBoundary(node.right, res);
        }
    }

    private void addLeaves(Node node, ArrayList<Integer> res) {
        if (node == null) return;

        if (node.left == null && node.right == null) {
            res.add(node.data);
            return;
        }

        addLeaves(node.left, res);
        addLeaves(node.right, res);
    }

    private void addRightBoundary(Node node, ArrayList<Integer> res) {
        if (node == null) return;

        if (node.right != null) {
            addRightBoundary(node.right, res);
        } else {
            addRightBoundary(node.left, res);
        }

        if (node.left != null || node.right != null) {
            res.add(node.data);
        }
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same example tree:

```
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
      / \
     8   9
```

1. Add root node: `res = [1]`
2. Add left boundary:
   - Start at node 2
   - Add 2 (not a leaf)
   - Move to left child (4)
   - Add 4 (not a leaf)
   - Move to left child (null)
   - Move to right child (null)
   - `res = [1, 2, 4]`
3. Add leaf nodes:
   - Left subtree of 2:
     - 4 is a leaf → add 4 (already added)
   - Right subtree of 2:
     - 5 is not a leaf
     - Left subtree of 5:
       - 8 is a leaf → add 8
     - Right subtree of 5:
       - 9 is a leaf → add 9
   - Left subtree of 3:
     - 6 is a leaf → add 6
   - Right subtree of 3:
     - 7 is a leaf → add 7
   - `res = [1, 2, 4, 8, 9, 6, 7]`
4. Add right boundary:
   - Start at node 3
   - Move to right child (7)
   - Add 7 (not a leaf)
   - Move to right child (null)
   - Move to left child (null)
   - `res = [1, 2, 4, 8, 9, 6, 7, 3]`

Final result: `[1, 2, 4, 8, 9, 6, 7, 3]`

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) where N is the number of nodes in the tree |
| Space Complexity | O(N) for the result storage |

---

# 🔍 Edge Cases

1. Empty tree
2. Tree with only one node
3. Tree with all nodes on the left boundary
4. Tree with all nodes on the right boundary
5. Tree with all nodes as leaves
6. Tree with a single path (degenerate tree)
7. Tree with duplicate values

---

# 📚 Key Takeaways

1. Tree boundary traversal can be broken down into three parts: left boundary, leaf nodes, and right boundary.
2. The left boundary is traversed top-down, while the right boundary is traversed bottom-up.
3. Leaf nodes are collected in order using inorder traversal.
4. The optimal approach ensures that each node is visited exactly once, making it efficient.

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - How would you handle a binary tree with duplicate values?
   - Can you modify the solution to work with a general tree (not just binary trees)?
   - What if the tree is very large and doesn't fit in memory?

2. **Common Pitfalls:**
   - Forgetting to exclude leaf nodes from the left and right boundaries.
   - Not handling the root node correctly.
   - Incorrectly traversing the right boundary in reverse order.

3. **Alternative Approaches:**
   - Using a single traversal with flags to track the current boundary.
   - Using a stack to reverse the right boundary traversal.

---

# ✅ Conclusion

The optimal approach efficiently traverses the boundary of a binary tree by breaking it down into three distinct parts: left boundary, leaf nodes, and right boundary. This approach ensures that each node is visited exactly once, making it both time and space efficient. Understanding this pattern is crucial for solving tree-related problems in interviews.