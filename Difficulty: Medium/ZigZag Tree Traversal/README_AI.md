# ZigZag Tree Traversal

---

# 📝 Problem Statement

Given a binary tree, write a function to traverse the tree in a zig-zag order. In zig-zag order, nodes at level 1 are traversed from left to right, nodes at level 2 are traversed from right to left, and so on.

**Input:**
- A binary tree root node.

**Output:**
- An array containing the nodes in zig-zag order.

**Constraints:**
- The number of nodes in the tree is in the range [0, 1000].
- -1000 ≤ Node.val ≤ 1000

---

# 💡 Intuition

The key insight is to use a level-order traversal (BFS) but alternate the direction of traversal for each level. We can achieve this by using a queue to process nodes level by level and a boolean flag to track the current direction.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform a standard level-order traversal using a queue.
2. For each level, collect nodes in a temporary list.
3. Alternate between adding nodes to the beginning or end of the temporary list based on the current direction.
4. Add the temporary list to the result list after processing each level.

## 🔹 Algorithm

1. Initialize an empty result list and a queue.
2. If the root is not null, add it to the queue.
3. Initialize a boolean flag `leftToRight` to true.
4. While the queue is not empty:
   - Get the current level size.
   - Initialize a temporary list.
   - For each node in the current level:
     - Dequeue the node.
     - If `leftToRight` is true, add the node's value to the end of the temporary list.
     - Otherwise, add the node's value to the beginning of the temporary list.
     - Enqueue the left and right children if they exist.
   - Add the temporary list to the result list.
   - Toggle the `leftToRight` flag.
5. Return the result list.

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> tempList = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();

                if (leftToRight) {
                    tempList.addLast(currentNode.data);
                } else {
                    tempList.addFirst(currentNode.data);
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.addAll(tempList);
            leftToRight = !leftToRight;
        }

        return result;
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

| Step | Queue | Temp List | Result | LeftToRight |
|------|-------|-----------|--------|-------------|
| 1    | [1]   | []        | []     | true        |
| 2    | [2,3] | [1]       | [1]    | false       |
| 3    | [4,5,6,7] | [3,2] | [1,3,2] | true |
| 4    | []    | [4,5,6,7] | [1,3,2,4,5,6,7] | false |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses a more efficient way to handle the direction of traversal. Instead of using a `LinkedList` to alternate between adding to the beginning and end, we can use a `LinkedList` and a `Deque` to manage the direction of traversal more efficiently.

## 🔹 Why This Works

This approach leverages the properties of a `Deque` to efficiently alternate the direction of traversal. By using a `Deque`, we can add elements to the front or back based on the current direction, which simplifies the code and improves efficiency.

## 🔹 Algorithm

1. Initialize an empty result list and a deque.
2. If the root is not null, add it to the deque.
3. Initialize a boolean flag `leftToRight` to true.
4. While the deque is not empty:
   - Get the current level size.
   - Initialize a temporary list.
   - For each node in the current level:
     - Dequeue the node from the front.
     - If `leftToRight` is true, add the node's value to the end of the temporary list.
     - Otherwise, add the node's value to the beginning of the temporary list.
     - Enqueue the left and right children if they exist.
   - Add the temporary list to the result list.
   - Toggle the `leftToRight` flag.
5. Return the result list.

## 🔹 Code

```java
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<Node> deque = new LinkedList<>();
        deque.offer(root);
        boolean leftToRight = true;

        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            LinkedList<Integer> tempList = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = deque.poll();

                if (leftToRight) {
                    tempList.addLast(currentNode.data);
                } else {
                    tempList.addFirst(currentNode.data);
                }

                if (currentNode.left != null) {
                    deque.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    deque.offer(currentNode.right);
                }
            }

            result.addAll(tempList);
            leftToRight = !leftToRight;
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

| Step | Deque | Temp List | Result | LeftToRight |
|------|-------|-----------|--------|-------------|
| 1    | [1]   | []        | []     | true        |
| 2    | [2,3] | [1]       | [1]    | false       |
| 3    | [4,5,6,7] | [3,2] | [1,3,2] | true |
| 4    | []    | [4,5,6,7] | [1,3,2,4,5,6,7] | false |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty tree: The function should return an empty list.
- Single node: The function should return a list containing the single node's value.
- Left-skewed tree: The function should handle traversal correctly for a tree with only left children.
- Right-skewed tree: The function should handle traversal correctly for a tree with only right children.
- Large tree: The function should handle a tree with a large number of nodes efficiently.

---

# 📚 Key Takeaways

- Level-order traversal (BFS) is essential for zig-zag traversal.
- Using a boolean flag to alternate the direction of traversal simplifies the logic.
- Efficiently managing the direction of traversal using a `Deque` improves code clarity and performance.

---

# 🚀 Interview Tips

- Discuss the trade-offs between using a `Queue` and a `Deque`.
- Mention that this problem is a variation of level-order traversal.
- Be prepared to discuss the time and space complexity of the solution.
- Consider asking if the tree is balanced or if there are any constraints on the tree structure.

---

# ✅ Conclusion

The optimal approach using a `Deque` provides an efficient and clear solution to the zig-zag tree traversal problem. By alternating the direction of traversal for each level, we ensure that the nodes are visited in the correct order. The use of a `Deque` simplifies the logic and improves the overall performance of the solution.