# Min Distance Between Two Nodes of a Binary Tree

---

# 📝 Problem Statement

Given a binary tree and two node values `a` and `b`, find the minimum distance between them. The distance between two nodes can be measured by the number of edges between them.

**Constraints:**
- The tree can have up to 1000 nodes
- Node values are unique integers

---

# 💡 Intuition

The key insight is that the minimum distance between two nodes in a binary tree is the sum of distances from the lowest common ancestor (LCA) to each node. This approach leverages the tree structure to efficiently find the path between the two nodes without traversing the entire tree.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Finding all paths from the root to each target node
2. Comparing the paths to find the last common node
3. Calculating the distances from this common node to both targets

## 🔹 Algorithm

1. Find path from root to node `a`
2. Find path from root to node `b`
3. Find the last common node in both paths
4. Calculate distance from LCA to `a`
5. Calculate distance from LCA to `b`
6. Return sum of these distances

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

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
    private boolean findPath(Node root, int target, List<Integer> path) {
        if (root == null) {
            return false;
        }

        path.add(root.data);

        if (root.data == target) {
            return true;
        }

        if ((root.left != null && findPath(root.left, target, path)) ||
            (root.right != null && findPath(root.right, target, path))) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    private int findLCA(List<Integer> path1, List<Integer> path2) {
        int i;
        for (i = 0; i < path1.size() && i < path2.size(); i++) {
            if (!path1.get(i).equals(path2.get(i))) {
                break;
            }
        }
        return path1.get(i - 1);
    }

    private int findDistance(Node root, int target, int dist) {
        if (root == null) {
            return -1;
        }

        if (root.data == target) {
            return dist;
        }

        int left = findDistance(root.left, target, dist + 1);
        if (left != -1) {
            return left;
        }

        return findDistance(root.right, target, dist + 1);
    }

    public int findDist(Node root, int a, int b) {
        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();

        if (!findPath(root, a, path1) || !findPath(root, b, path2)) {
            return -1;
        }

        int lca = findLCA(path1, path2);

        int d1 = findDistance(root, a, 0);
        int d2 = findDistance(root, b, 0);

        int lcaDist = findDistance(root, lca, 0);

        return (d1 + d2) - 2 * lcaDist;
    }
}
```

## 🔹 Dry Run

Let's dry run with this tree:
```
        1
       / \
      2   3
     / \
    4   5
```

**Finding path to 4:**
- Path: [1, 2, 4]

**Finding path to 5:**
- Path: [1, 2, 5]

**Finding LCA:**
- Common path: [1, 2]
- LCA: 2

**Calculating distances:**
- Distance from root to 4: 2
- Distance from root to 5: 2
- Distance from root to LCA (2): 1

**Final distance:**
- (2 + 2) - 2*1 = 2

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n²) in worst case |
| Space Complexity | O(n) for path storage |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the LCA to find the minimum distance:
1. Find the LCA of the two nodes
2. Calculate the distance from LCA to each node
3. Sum these distances

## 🔹 Why This Works

This approach is optimal because:
- It avoids storing multiple paths
- It leverages tree properties to find the LCA efficiently
- It calculates distances in a single traversal for each node

## 🔹 Algorithm

1. Find the LCA of nodes `a` and `b`
2. Calculate distance from LCA to `a`
3. Calculate distance from LCA to `b`
4. Return sum of these distances

## 🔹 Code

```java
class Solution {
    private Node LCA(Node root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        if (root.data == n1 || root.data == n2) {
            return root;
        }

        Node leftLCA = LCA(root.left, n1, n2);
        Node rightLCA = LCA(root.right, n1, n2);

        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    private int findDistance(Node root, int target, int dist) {
        if (root == null) {
            return -1;
        }

        if (root.data == target) {
            return dist;
        }

        int left = findDistance(root.left, target, dist + 1);
        if (left != -1) {
            return left;
        }

        return findDistance(root.right, target, dist + 1);
    }

    public int findDist(Node root, int a, int b) {
        Node lca = LCA(root, a, b);

        int d1 = findDistance(lca, a, 0);
        int d2 = findDistance(lca, b, 0);

        return d1 + d2;
    }
}
```

## 🔹 Detailed Dry Run

Using the same tree:
```
        1
       / \
      2   3
     / \
    4   5
```

**Finding LCA of 4 and 5:**
1. Recursively find LCA in left subtree (2)
2. Recursively find LCA in right subtree (null)
3. Since both left and right subtrees have LCAs, return current node (2)

**Calculating distances:**
1. Distance from 2 to 4: 1
2. Distance from 2 to 5: 1

**Final distance:**
1 + 1 = 2

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(h) where h is tree height |

---

# 🔍 Edge Cases

- Tree with only one node
- Nodes are siblings
- One node is ancestor of the other
- Nodes are in different subtrees
- Large tree (1000 nodes)
- Nodes at maximum depth

---

# 📚 Key Takeaways

1. The LCA approach efficiently finds the minimum distance between two nodes
2. Tree traversal is essential for finding paths and distances
3. Recursive solutions are often cleaner for tree problems
4. Understanding tree properties helps optimize solutions

---

# 🚀 Interview Tips

- Be prepared to explain the LCA concept
- Practice drawing trees to visualize paths
- Consider both recursive and iterative approaches
- Ask about expected tree size for optimization discussion

---

# ✅ Conclusion

The optimal solution using LCA provides an efficient O(n) time complexity approach to finding the minimum distance between two nodes in a binary tree. This method is both time and space efficient, making it ideal for interview scenarios where performance matters.