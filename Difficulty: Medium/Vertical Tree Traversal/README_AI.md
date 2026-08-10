# Vertical Tree Traversal

---

# 📝 Problem Statement

Given a binary tree, find the vertical order traversal of the binary tree. Vertical order traversal means that if we look at the tree from the top, nodes at the same vertical distance from the root should be considered at the same vertical line.

**Constraints:**
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

---

# 💡 Intuition

The key insight is to assign a horizontal distance (hd) to each node, where the root has hd 0, left child has hd - 1, and right child has hd + 1. We can then use a TreeMap to store nodes based on their hd values, which automatically sorts them in ascending order. This allows us to traverse the tree level by level and collect nodes with the same hd together.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform a level-order traversal (BFS) of the tree.
2. For each node, track its horizontal distance (hd) from the root.
3. Store nodes with the same hd in a list.
4. After traversal, collect the lists in order of increasing hd.

---

## 🔹 Algorithm

1. Initialize a queue for BFS and a queue for tracking hd values.
2. Start with the root node and hd 0.
3. While the queue is not empty:
   - Dequeue a node and its hd.
   - Add the node's value to the list corresponding to its hd in a map.
   - Enqueue the left child with hd - 1.
   - Enqueue the right child with hd + 1.
4. Collect the lists from the map in order of increasing hd.

---

## 🔹 Code

```java
import java.util.*;

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
    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> hdQueue = new LinkedList<>();

        queue.add(root);
        hdQueue.add(0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int hd = hdQueue.poll();

            if (!map.containsKey(hd)) {
                map.put(hd, new ArrayList<>());
            }
            map.get(hd).add(current.data);

            if (current.left != null) {
                queue.add(current.left);
                hdQueue.add(hd - 1);
            }

            if (current.right != null) {
                queue.add(current.right);
                hdQueue.add(hd + 1);
            }
        }

        for (ArrayList<Integer> list : map.values()) {
            result.add(list);
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the following tree:

```
        1
       / \
      2   3
     / \ / \
    4  5 6 7
```

| Step | Current Node | HD | Action | Map State |
|------|--------------|----|--------|-----------|
| 1    | 1            | 0  | Add 1 to map[0] | {0: [1]} |
| 2    | 2            | -1 | Add 2 to map[-1] | {-1: [2], 0: [1]} |
| 3    | 3            | 1  | Add 3 to map[1] | {-1: [2], 0: [1], 1: [3]} |
| 4    | 4            | -2 | Add 4 to map[-2] | {-2: [4], -1: [2], 0: [1], 1: [3]} |
| 5    | 5            | 0  | Add 5 to map[0] | {-2: [4], -1: [2], 0: [1, 5], 1: [3]} |
| 6    | 6            | 0  | Add 6 to map[0] | {-2: [4], -1: [2], 0: [1, 5, 6], 1: [3]} |
| 7    | 7            | 2  | Add 7 to map[2] | {-2: [4], -1: [2], 0: [1, 5, 6], 1: [3], 2: [7]} |

Final result: [[4], [2], [1, 5, 6], [3], [7]]

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N log N) |
| Space Complexity | O(N) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses a TreeMap to automatically sort nodes by their horizontal distance. This ensures that the nodes are collected in the correct vertical order without additional sorting steps.

---

## 🔹 Why This Works

Using a TreeMap allows us to maintain the nodes in sorted order based on their horizontal distance. This eliminates the need for an additional sorting step, making the solution more efficient.

---

## 🔹 Algorithm

1. Initialize a TreeMap to store nodes by their horizontal distance.
2. Perform a level-order traversal (BFS) of the tree.
3. For each node, track its horizontal distance (hd).
4. Add the node's value to the list corresponding to its hd in the TreeMap.
5. After traversal, collect the lists from the TreeMap in order of increasing hd.

---

## 🔹 Code

```java
import java.util.*;

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
    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> hdQueue = new LinkedList<>();

        queue.add(root);
        hdQueue.add(0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int hd = hdQueue.poll();

            if (!map.containsKey(hd)) {
                map.put(hd, new ArrayList<>());
            }
            map.get(hd).add(current.data);

            if (current.left != null) {
                queue.add(current.left);
                hdQueue.add(hd - 1);
            }

            if (current.right != null) {
                queue.add(current.right);
                hdQueue.add(hd + 1);
            }
        }

        for (ArrayList<Integer> list : map.values()) {
            result.add(list);
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following tree:

```
        1
       / \
      2   3
     / \ / \
    4  5 6 7
```

| Step | Current Node | HD | Action | Map State |
|------|--------------|----|--------|-----------|
| 1    | 1            | 0  | Add 1 to map[0] | {0: [1]} |
| 2    | 2            | -1 | Add 2 to map[-1] | {-1: [2], 0: [1]} |
| 3    | 3            | 1  | Add 3 to map[1] | {-1: [2], 0: [1], 1: [3]} |
| 4    | 4            | -2 | Add 4 to map[-2] | {-2: [4], -1: [2], 0: [1], 1: [3]} |
| 5    | 5            | 0  | Add 5 to map[0] | {-2: [4], -1: [2], 0: [1, 5], 1: [3]} |
| 6    | 6            | 0  | Add 6 to map[0] | {-2: [4], -1: [2], 0: [1, 5, 6], 1: [3]} |
| 7    | 7            | 2  | Add 7 to map[2] | {-2: [4], -1: [2], 0: [1, 5, 6], 1: [3], 2: [7]} |

Final result: [[4], [2], [1, 5, 6], [3], [7]]

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N log N) |
| Space Complexity | O(N) |

---

# 🔍 Edge Cases

- **Empty Tree**: The function should return an empty list.
- **Single Node**: The function should return a list containing a single list with the node's value.
- **Skewed Tree**: The function should handle left or right skewed trees correctly.
- **Large Tree**: The function should handle trees with up to 100 nodes efficiently.
- **Negative Values**: The function should handle negative node values correctly.

---

# 📚 Key Takeaways

- **TreeMap Usage**: Using a TreeMap to store nodes by their horizontal distance ensures that the nodes are collected in the correct vertical order.
- **BFS Traversal**: Performing a level-order traversal (BFS) allows us to process nodes level by level, ensuring that nodes at the same level are processed together.
- **Horizontal Distance Tracking**: Tracking the horizontal distance (hd) of each node from the root helps in determining the vertical order of the nodes.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle nodes with the same horizontal distance but different levels.
- **Alternative Approaches**: Consider using a hash map and sorting the keys to achieve the same result.
- **Optimization**: The optimal approach uses a TreeMap to automatically sort nodes by their horizontal distance, making it more efficient than using a hash map and sorting the keys.

---

# ✅ Conclusion

The optimal approach uses a TreeMap to store nodes by their horizontal distance, ensuring that the nodes are collected in the correct vertical order. This approach is efficient and handles all edge cases correctly. The key insight is to use a TreeMap to automatically sort nodes by their horizontal distance, making the solution both efficient and easy to understand.