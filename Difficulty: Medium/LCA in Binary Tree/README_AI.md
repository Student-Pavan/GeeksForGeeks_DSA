# LCA in Binary Tree

---

# 📝 Problem Statement

Find the lowest common ancestor (LCA) of two nodes in a binary tree. The LCA is the deepest node that has both nodes as descendants.

**Constraints:**
- The tree can be large (up to 10^4 nodes)
- Node values are unique
- Both nodes are guaranteed to exist in the tree

---

# 💡 Intuition

The key insight is that the LCA is the first node where the two nodes appear in different subtrees. We can solve this recursively by checking if both nodes exist in the left and right subtrees of each node.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform a depth-first search (DFS) to find the paths from the root to each of the two nodes.
2. Compare the paths to find the last common node.

## 🔹 Algorithm

1. Perform DFS to find path from root to n1.
2. Perform DFS to find path from root to n2.
3. Compare the two paths to find the last common node.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        this.data = val;
        left = right = null;
    }
}

class Solution {
    boolean findPath(Node root, int n, List<Integer> path) {
        if (root == null) {
            return false;
        }

        path.add(root.data);

        if (root.data == n) {
            return true;
        }

        if ((root.left != null && findPath(root.left, n, path)) ||
            (root.right != null && findPath(root.right, n, path))) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    Node lca(Node root, int n1, int n2) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
            return null;
        }

        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i))) {
                break;
            }
        }

        return new Node(path1.get(i - 1));
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following tree:

```
        1
       / \
      2   3
     / \ / \
    4  5 6 7
```

Find LCA of 4 and 5:

| Step | Path1 | Path2 | Action | LCA |
|---|---|---|---|---|
| 1 | [1] | [] | Start DFS for n1=4 | - |
| 2 | [1,2] | [] | Move to left child | - |
| 3 | [1,2,4] | [] | Found n1=4 | - |
| 4 | [1] | [] | Backtrack | - |
| 5 | [1] | [1] | Start DFS for n2=5 | - |
| 6 | [1,2] | [1,2] | Move to left child | - |
| 7 | [1,2,5] | [1,2,5] | Found n2=5 | - |
| 8 | [1,2] | [1,2] | Backtrack | - |
| 9 | [1] | [1] | Compare paths | LCA is 2 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Perform a single traversal of the tree.
2. If either of the nodes is found, return that node.
3. If both nodes are found in different subtrees, the current node is the LCA.

## 🔹 Why This Works

This approach works because we're essentially checking at each node if it's the point where the two nodes diverge in the tree. The first such node we find is the LCA.

## 🔹 Algorithm

1. If root is null, return null.
2. If root is either n1 or n2, return root.
3. Recursively search left and right subtrees.
4. If both subtrees return non-null, current node is LCA.
5. If only one subtree returns non-null, return that result.

## 🔹 Code

```java
class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        this.data = val;
        left = right = null;
    }
}

class Solution {
    Node lca(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        if (root.data == n1 || root.data == n2) {
            return root;
        }

        Node leftLCA = lca(root.left, n1, n2);
        Node rightLCA = lca(root.right, n1, n2);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        return (leftLCA != null) ? leftLCA : rightLCA;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal solution with the same tree:

```
        1
       / \
      2   3
     / \ / \
    4  5 6 7
```

Find LCA of 4 and 5:

| Step | Current Node | Left LCA | Right LCA | Action | LCA |
|---|---|---|---|---|---|
| 1 | 1 | - | - | Start at root | - |
| 2 | 2 | - | - | Move to left child | - |
| 3 | 4 | - | - | Move to left child | - |
| 4 | null | - | - | Reach leaf | Return null |
| 5 | 4 | null | - | Backtrack | - |
| 6 | 2 | null | - | Move to right child | - |
| 7 | 5 | - | - | Found n2=5 | Return 5 |
| 8 | 2 | null | 5 | Both subtrees not null | Return 2 |
| 9 | 1 | 2 | - | One subtree null | Return 2 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(H) (where H is tree height) |

---

# 🔍 Edge Cases

- Tree with only one node
- Nodes are the same
- One node is the ancestor of the other
- Large tree with many nodes
- Tree with skewed structure

---

# 📚 Key Takeaways

- The optimal solution avoids storing paths, making it more space efficient
- Recursive tree traversal is a common pattern for tree problems
- Understanding the problem constraints helps choose the right approach

---

# 🚀 Interview Tips

- Ask if the tree is a BST (Binary Search Tree) to potentially use BST properties
- Discuss time/space tradeoffs between the two approaches
- Be prepared to explain the recursive approach clearly

---

# ✅ Conclusion

The optimal solution is preferred because it's more space efficient while maintaining the same time complexity. The key insight is recognizing that the LCA is the first node where the two nodes appear in different subtrees.