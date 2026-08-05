# Bottom View of Binary Tree

---

# 📝 Problem Statement

Given a binary tree, print the bottom view from left to right. A node `x` is there in output if x is the bottommost node at its horizontal distance. The horizontal distance of the left child of a node `x` is equal to the horizontal distance of `x` minus 1, and that of the right child is the horizontal distance of `x` plus 1.

**Input:**
A binary tree represented by its root node.

**Output:**
An array containing the bottom view of the binary tree from left to right.

**Example:**

```
Input:
       20
      /  \
     8    22
    / \    \
   5   3    25
      / \
     10  14

Output: [5, 10, 3, 14, 25]
```

**Constraints:**
- The number of nodes in the tree is in the range [0, 10^4].
- -1000 <= Node.val <= 1000

---

# 💡 Intuition

The bottom view of a binary tree consists of the nodes visible when the tree is viewed from the bottom. To find this, we need to determine the bottommost node at each horizontal distance (hd) from the root. The key insight is that nodes with the same horizontal distance will overwrite each other in a map, with the last node at each hd being the bottommost one.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Horizontal Distance Tracking**: For each node, track its horizontal distance from the root.
2. **Level Order Traversal**: Perform a level order traversal (BFS) of the tree.
3. **Map Update**: For each node, update a map with its horizontal distance as the key and its value as the value. This ensures that the last node at each horizontal distance is stored in the map.
4. **Result Construction**: After traversal, extract the values from the map in order of increasing horizontal distance to form the bottom view.

## 🔹 Algorithm

1. Initialize a queue for BFS and a map to store horizontal distances.
2. Start with the root node at horizontal distance 0.
3. For each node dequeued:
   - Update the map with the current node's value at its horizontal distance.
   - Enqueue the left child with horizontal distance `hd - 1`.
   - Enqueue the right child with horizontal distance `hd + 1`.
4. After traversal, extract values from the map in order of increasing horizontal distance to form the result.

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, Integer> hdMap = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> hdQueue = new LinkedList<>();

        queue.add(root);
        hdQueue.add(0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int hd = hdQueue.poll();

            hdMap.put(hd, current.data);

            if (current.left != null) {
                queue.add(current.left);
                hdQueue.add(hd - 1);
            }

            if (current.right != null) {
                queue.add(current.right);
                hdQueue.add(hd + 1);
            }
        }

        for (int val : hdMap.values()) {
            result.add(val);
        }

        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the given example:

```
Input:
       20
      /  \
     8    22
    / \    \
   5   3    25
      / \
     10  14
```

| Step | Node | HD | Action | Map State |
|------|------|-----|--------|------------|
| 1    | 20   | 0   | Enqueue left (8, -1), right (22, 1) | {0: 20} |
| 2    | 8    | -1  | Enqueue left (5, -2), right (3, 0) | {0: 20, -1: 8} |
| 3    | 22   | 1   | Enqueue right (25, 2) | {0: 20, -1: 8, 1: 22} |
| 4    | 5    | -2  | No children | {0: 20, -1: 8, 1: 22, -2: 5} |
| 5    | 3    | 0   | Enqueue left (10, -1), right (14, 1) | {0: 3, -1: 8, 1: 22, -2: 5} |
| 6    | 25   | 2   | No children | {0: 3, -1: 8, 1: 22, -2: 5, 2: 25} |
| 7    | 10   | -1  | No children | {0: 3, -1: 10, 1: 22, -2: 5, 2: 25} |
| 8    | 14   | 1   | No children | {0: 3, -1: 10, 1: 14, -2: 5, 2: 25} |

Final Map: {-2: 5, -1: 10, 0: 3, 1: 14, 2: 25}

Result: [5, 10, 3, 14, 25]

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) where N is the number of nodes in the tree. Each node is processed exactly once. |
| Space Complexity | O(N) for the queue and map storage. |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach since we need to visit every node to determine the bottom view. The use of a TreeMap ensures that the nodes are stored in order of their horizontal distances, allowing us to directly extract the bottom view in the required order.

## 🔹 Why This Works

The TreeMap automatically sorts the keys (horizontal distances) in ascending order, which allows us to directly extract the bottom view by iterating over the values in the map. This approach efficiently captures the bottommost nodes at each horizontal distance by overwriting previous entries in the map.

## 🔹 Algorithm

1. Initialize a TreeMap to store horizontal distances and node values.
2. Perform a level order traversal (BFS) of the tree.
3. For each node, update the TreeMap with its horizontal distance as the key and its value as the value.
4. After traversal, extract the values from the TreeMap in order of increasing horizontal distance to form the result.

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, Integer> hdMap = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> hdQueue = new LinkedList<>();

        queue.add(root);
        hdQueue.add(0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int hd = hdQueue.poll();

            hdMap.put(hd, current.data);

            if (current.left != null) {
                queue.add(current.left);
                hdQueue.add(hd - 1);
            }

            if (current.right != null) {
                queue.add(current.right);
                hdQueue.add(hd + 1);
            }
        }

        for (int val : hdMap.values()) {
            result.add(val);
        }

        return result;
    }
}
```

## 🔹 Detailed Dry Run

The dry run for the optimal approach is identical to the brute force approach since the algorithm is the same. The use of a TreeMap ensures that the nodes are stored in order of their horizontal distances, allowing us to directly extract the bottom view in the required order.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) where N is the number of nodes in the tree. Each node is processed exactly once. |
| Space Complexity | O(N) for the queue and map storage. |

---

# 🔍 Edge Cases

- **Empty Tree**: The function should return an empty list.
- **Single Node**: The function should return a list containing the value of the single node.
- **Skewed Tree**: The function should correctly handle left-skewed and right-skewed trees.
- **Duplicate Values**: The function should correctly handle nodes with duplicate values at different horizontal distances.
- **Large Tree**: The function should efficiently handle large trees within the given constraints.

---

# 📚 Key Takeaways

- **BFS Traversal**: Level order traversal is essential for capturing the bottom view of the tree.
- **Horizontal Distance Tracking**: Tracking the horizontal distance of each node is crucial for determining the bottom view.
- **TreeMap Usage**: Using a TreeMap ensures that the nodes are stored in order of their horizontal distances, simplifying the extraction of the bottom view.
- **Efficiency**: The optimal approach efficiently captures the bottom view in O(N) time and space complexity.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle trees with duplicate values or how to modify the solution for a top view.
- **Common Pitfalls**: Ensure that the horizontal distance is correctly calculated for each node to avoid incorrect results.
- **Alternative Approaches**: Consider using a hash map and sorting the keys to achieve the same result without the overhead of a TreeMap.

---

# ✅ Conclusion

The bottom view of a binary tree can be efficiently captured using a level order traversal and a TreeMap to store the horizontal distances and node values. This approach ensures that the bottommost nodes at each horizontal distance are correctly identified and returned in the required order. The optimal solution leverages the properties of a TreeMap to simplify the extraction of the result, making it both efficient and easy to understand.