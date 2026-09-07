# k-th Smallest in BST

---

# 📝 Problem Statement

Given a Binary Search Tree (BST), find the k-th smallest element in the tree.

**Objective**: Find the k-th smallest element in a BST.

**Input**:
- A BST root node
- An integer k

**Output**:
- The k-th smallest element in the BST

**Constraints**:
- The BST contains at most 10^5 nodes
- 1 ≤ k ≤ total nodes in BST

---

# 💡 Intuition

The key insight is that an in-order traversal of a BST yields elements in ascending order. By performing an in-order traversal and keeping track of the count of visited nodes, we can efficiently find the k-th smallest element.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform an in-order traversal of the BST
2. Store all elements in an array
3. Return the k-th element from the array

## 🔹 Algorithm

1. Initialize an empty array to store elements
2. Perform in-order traversal:
   - Recursively traverse left subtree
   - Add current node's value to array
   - Recursively traverse right subtree
3. Return the (k-1)-th index element from the array (since arrays are 0-indexed)

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Node {
    int data;
    Node left, right;

    public Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public int kthSmallest(Node root, int k) {
        List<Integer> elements = new ArrayList<>();
        inOrderTraversal(root, elements);
        return elements.get(k - 1);
    }

    private void inOrderTraversal(Node node, List<Integer> elements) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, elements);
        elements.add(node.data);
        inOrderTraversal(node.right, elements);
    }
}
```

## 🔹 Dry Run

Let's consider a BST:

```
      5
     / \
    3   7
   / \   \
  2   4   8
```

For k = 3:

| Step | Node | Action | Elements List |
|------|------|--------|----------------|
| 1    | 2    | Visit  | [2]            |
| 2    | 3    | Visit  | [2, 3]         |
| 3    | 4    | Visit  | [2, 3, 4]      |
| 4    | 5    | Visit  | [2, 3, 4, 5]   |
| 5    | 7    | Visit  | [2, 3, 4, 5, 7]|
| 6    | 8    | Visit  | [2, 3, 4, 5, 7, 8]|

The 3rd smallest element is 4.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) (we visit each node once) |
| Space Complexity | O(n) (for storing elements) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Perform an in-order traversal iteratively using a stack, counting nodes until we reach the k-th smallest element.

## 🔹 Why This Works

This approach avoids storing all elements in memory by using an iterative in-order traversal with a stack, which allows us to find the k-th smallest element in a single pass.

## 🔹 Algorithm

1. Initialize an empty stack and set current node to root
2. Initialize a counter to 0
3. While current node is not null or stack is not empty:
   - Traverse to the leftmost node, pushing each node onto the stack
   - Pop a node from the stack, increment counter
   - If counter equals k, return the node's value
   - Move to the right subtree of the popped node
4. If loop completes without finding k-th element, return -1 (error case)

## 🔹 Code

```java
import java.util.Stack;

class Solution {
    public int kthSmallest(Node root, int k) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        int count = 0;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            count++;

            if (count == k) {
                return current.data;
            }

            current = current.right;
        }

        return -1; // k is larger than the number of nodes
    }
}
```

## 🔹 Detailed Dry Run

Using the same BST as before:

```
      5
     / \
    3   7
   / \   \
  2   4   8
```

For k = 3:

| Step | Current Node | Stack | Action | Count |
|------|--------------|-------|--------|-------|
| 1    | 5            | [5]   | Push 5| 0     |
| 2    | 3            | [5,3] | Push 3| 0     |
| 3    | 2            | [5,3,2]| Push 2| 0     |
| 4    | null         | [5,3] | Pop 2 | 1     |
| 5    | 3            | [5]   |       | 1     |
| 6    | null         | [5]   | Pop 3 | 2     |
| 7    | 5            | []    | Pop 5 | 3     |
| 8    | 4            | [4]   | Push 4| 3     |
| 9    | null         | []    | Pop 4 | 4     |

The algorithm returns 4 when count equals 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h + k) where h is the height of the tree (worst case O(n)) |
| Space Complexity | O(h) where h is the height of the tree (worst case O(n)) |

---

# 🔍 Edge Cases

- Empty tree (return -1 or handle appropriately)
- k = 1 (smallest element)
- k = total nodes (largest element)
- BST with all left children (degenerate tree)
- BST with all right children (degenerate tree)
- k larger than total nodes (return -1 or handle appropriately)

---

# 📚 Key Takeaways

- In-order traversal of a BST yields elements in sorted order
- Iterative in-order traversal with a stack is more memory efficient than storing all elements
- The optimal approach stops early when the k-th element is found
- Understanding BST properties is crucial for solving this problem efficiently

---

# 🚀 Interview Tips

- Clarify if k is guaranteed to be valid (1 ≤ k ≤ total nodes)
- Discuss follow-up questions like:
  - What if the BST is modified frequently?
  - How would you optimize for repeated queries?
- Consider alternative approaches like:
  - Augmenting the tree with subtree sizes
  - Using Morris traversal for O(1) space

---

# ✅ Conclusion

The optimal approach using iterative in-order traversal with a stack provides an efficient solution with O(h + k) time complexity and O(h) space complexity, where h is the height of the tree. This approach is particularly suitable for large BSTs where memory efficiency is important.