# 📌 Top View of Binary Tree

---

# 📝 Problem Statement

Given a binary tree, print the top view of the binary tree. The top view of a binary tree is the set of nodes visible when the tree is viewed from the top. The top view of a binary tree is the set of nodes visible when the tree is viewed from the top. The top view will be printed from leftmost node to rightmost node.

**Example:**

```
Input:
       1
    /     \
   2       3
  / \     / \
 4   5   6   7

Output:
4 2 1 3 7
```

**Constraints:**
- The number of nodes in the tree is in the range [0, 10^4].
- -1000 <= Node.val <= 1000

---

# 💡 Intuition

The key insight is that the top view of a binary tree consists of the nodes that are visible from the top when looking at the tree. These nodes are the first nodes encountered at each horizontal distance from the root. We can use a level-order traversal (BFS) to visit nodes level by level and keep track of the horizontal distance (hd) of each node from the root. The first node encountered at each hd is the one that will be visible from the top.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a level-order traversal (BFS) to visit each node and record the horizontal distance (hd) of each node. We then use a TreeMap to store the first node encountered at each hd. Finally, we iterate through the TreeMap to collect the top view nodes.

---

## 🔹 Algorithm

1. If the root is null, return an empty list.
2. Initialize a queue for BFS and a TreeMap to store the first node at each hd.
3. Enqueue the root node with hd 0.
4. While the queue is not empty:
   - Dequeue the front node and its hd.
   - If the hd is not present in the TreeMap, add the node's value to the TreeMap.
   - Enqueue the left child with hd - 1.
   - Enqueue the right child with hd + 1.
5. Iterate through the TreeMap to collect the top view nodes.
6. Return the list of top view nodes.

---

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

    class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public ArrayList<Integer> topView(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<Pair> q = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        q.offer(new Pair(root, 0));

        // BFS (Level Order Traversal)
        while (!q.isEmpty()) {

            Pair curr = q.poll();

            // First node at this horizontal distance
            if (!map.containsKey(curr.hd))
                map.put(curr.hd, curr.node.data);

            if (curr.node.left != null)
                q.offer(new Pair(curr.node.left, curr.hd - 1));

            if (curr.node.right != null)
                q.offer(new Pair(curr.node.right, curr.hd + 1));
        }

        for (int val : map.values())
            ans.add(val);

        return ans;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the given example:

```
Input:
       1
    /     \
   2       3
  / \     / \
 4   5   6   7
```

| Step | Node | HD | Action | TreeMap |
|---|---|---|---|---|
| 1 | 1 | 0 | Enqueue (1,0) | {0:1} |
| 2 | 1 | 0 | Dequeue (1,0) | {0:1} |
| 3 | 2 | -1 | Enqueue (2,-1) | {0:1, -1:2} |
| 4 | 3 | 1 | Enqueue (3,1) | {0:1, -1:2, 1:3} |
| 5 | 2 | -1 | Dequeue (2,-1) | {0:1, -1:2, 1:3} |
| 6 | 4 | -2 | Enqueue (4,-2) | {0:1, -1:2, 1:3, -2:4} |
| 7 | 5 | 0 | Enqueue (5,0) | {0:1, -1:2, 1:3, -2:4} |
| 8 | 3 | 1 | Dequeue (3,1) | {0:1, -1:2, 1:3, -2:4} |
| 9 | 6 | 0 | Enqueue (6,0) | {0:1, -1:2, 1:3, -2:4} |
| 10 | 7 | 2 | Enqueue (7,2) | {0:1, -1:2, 1:3, -2:4, 2:7} |
| 11 | 4 | -2 | Dequeue (4,-2) | {0:1, -1:2, 1:3, -2:4, 2:7} |
| 12 | 5 | 0 | Dequeue (5,0) | {0:1, -1:2, 1:3, -2:4, 2:7} |
| 13 | 6 | 0 | Dequeue (6,0) | {0:1, -1:2, 1:3, -2:4, 2:7} |
| 14 | 7 | 2 | Dequeue (7,2) | {0:1, -1:2, 1:3, -2:4, 2:7} |

After processing all nodes, the TreeMap contains the first node at each hd: { -2:4, -1:2, 0:1, 1:3, 2:7 }. The top view is the values in the order of their hd: [4, 2, 1, 3, 7].

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) where N is the number of nodes in the tree. |
| Space Complexity | O(N) for the queue and TreeMap. |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach since it already efficiently computes the top view using BFS and a TreeMap. The time and space complexities are optimal for this problem.

---

## 🔹 Why This Works

The approach works because BFS ensures that nodes are processed level by level, and the TreeMap stores the first node encountered at each horizontal distance. This guarantees that the top view is correctly captured.

---

## 🔹 Algorithm

1. If the root is null, return an empty list.
2. Initialize a queue for BFS and a TreeMap to store the first node at each hd.
3. Enqueue the root node with hd 0.
4. While the queue is not empty:
   - Dequeue the front node and its hd.
   - If the hd is not present in the TreeMap, add the node's value to the TreeMap.
   - Enqueue the left child with hd - 1.
   - Enqueue the right child with hd + 1.
5. Iterate through the TreeMap to collect the top view nodes.
6. Return the list of top view nodes.

---

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

    class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public ArrayList<Integer> topView(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<Pair> q = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        q.offer(new Pair(root, 0));

        // BFS (Level Order Traversal)
        while (!q.isEmpty()) {

            Pair curr = q.poll();

            // First node at this horizontal distance
            if (!map.containsKey(curr.hd))
                map.put(curr.hd, curr.node.data);

            if (curr.node.left != null)
                q.offer(new Pair(curr.node.left, curr.hd - 1));

            if (curr.node.right != null)
                q.offer(new Pair(curr.node.right, curr.hd + 1));
        }

        for (int val : map.values())
            ans.add(val);

        return ans;
    }
}
```

---

## 🔹 Detailed Dry Run

The dry run for the optimal approach is the same as the brute force approach, as the algorithm is identical.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) where N is the number of nodes in the tree. |
| Space Complexity | O(N) for the queue and TreeMap. |

---

# 🔍 Edge Cases

- Empty tree: The function should return an empty list.
- Single node tree: The function should return a list containing the single node's value.
- Skewed tree: The function should correctly handle left or right skewed trees.
- Large tree: The function should handle large trees efficiently.
- Duplicate values: The function should correctly handle nodes with duplicate values.

---

# 📚 Key Takeaways

- The top view of a binary tree can be efficiently computed using BFS and a TreeMap.
- The key insight is to track the horizontal distance of each node and store the first node encountered at each distance.
- The optimal approach is the same as the brute force approach, as it efficiently computes the top view with optimal time and space complexity.

---

# 🚀 Interview Tips

- Discuss the trade-offs between BFS and DFS for computing the top view.
- Consider alternative data structures like HashMap if the order of nodes is not required.
- Ask about follow-up questions such as computing the bottom view or side views of the tree.

---

# ✅ Conclusion

The optimal approach using BFS and a TreeMap efficiently computes the top view of a binary tree. The key insight is to track the horizontal distance of each node and store the first node encountered at each distance. This approach ensures that the top view is correctly captured with optimal time and space complexity.