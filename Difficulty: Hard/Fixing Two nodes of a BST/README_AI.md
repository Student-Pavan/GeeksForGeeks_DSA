# Fixing Two nodes of a BST

---

# 📝 Problem Statement

Given the root of a Binary Search Tree (BST) where exactly two nodes have been swapped, fix the BST by swapping them back to their correct positions.

**Objective**: Correct the BST by identifying and swapping the two incorrectly placed nodes.

**Input**:
- The root of a BST with exactly two nodes swapped.

**Output**:
- The root of the corrected BST.

**Constraints**:
- The number of nodes in the tree is in the range [2, 1000].
- -2^31 <= Node.val <= 2^31 - 1

---

# 💡 Intuition

The key insight is that in a BST, an in-order traversal yields a sorted sequence. When two nodes are swapped, this sorted order is violated. By performing an in-order traversal and tracking the first and second violations of the sorted order, we can identify the two nodes that need to be swapped back.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform an in-order traversal to collect all node values into an array.
2. Find the two elements in the array that are out of order.
3. Perform another in-order traversal to locate these two nodes in the tree.
4. Swap their values.

## 🔹 Algorithm

1. Perform in-order traversal to store node values in an array.
2. Identify the two indices where the order is violated.
3. Perform in-order traversal again to find the corresponding nodes.
4. Swap the values of these nodes.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> inorderList = new ArrayList<>();
    private Node first, second, prev;

    private void inorderTraversal(Node root) {
        if (root == null) return;
        inorderTraversal(root.left);
        inorderList.add(root.data);
        inorderTraversal(root.right);
    }

    private void findNodes(Node root) {
        if (root == null) return;
        findNodes(root.left);
        if (prev != null && root.data < prev.data) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        findNodes(root.right);
    }

    public Node correctBST(Node root) {
        inorderTraversal(root);
        int[] indices = new int[2];
        int count = 0;
        for (int i = 0; i < inorderList.size() - 1; i++) {
            if (inorderList.get(i) > inorderList.get(i + 1)) {
                indices[count++] = i;
                if (count == 2) break;
            }
        }
        int firstVal = inorderList.get(indices[0]);
        int secondVal = inorderList.get(indices[1] + 1);

        prev = null;
        first = null;
        second = null;
        findNodes(root);

        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }

        return root;
    }
}
```

## 🔹 Dry Run

Let's consider the following BST:

```
      10
     /  \
    5    8
   / \
  2   20
```

The in-order traversal of this BST is: [2, 5, 20, 8, 10]

The violations are between 20 and 8, and between 8 and 10. So, the indices are 2 and 3.

The nodes to swap are 20 and 8.

After swapping, the BST becomes:

```
      10
     /  \
    5    8
   / \
  2   20
```

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Perform an in-order traversal while keeping track of the previous node.
2. Identify the first and second nodes where the current node's value is less than the previous node's value.
3. Swap their values.

## 🔹 Why This Works

In a BST, an in-order traversal should yield a sorted sequence. When two nodes are swapped, this sorted order is violated. By tracking the previous node during the in-order traversal, we can identify the two nodes that are out of order and swap them back.

## 🔹 Algorithm

1. Perform in-order traversal.
2. Track the previous node.
3. Identify the first and second nodes where the current node's value is less than the previous node's value.
4. Swap their values.

## 🔹 Code

```java
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
    Node first = null;
    Node second = null;
    Node prev = null;

    private void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.data < prev.data) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    public Node correctBST(Node root) {
        inorder(root);
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
        return root;
    }
}
```

## 🔹 Detailed Dry Run

Let's consider the following BST:

```
      10
     /  \
    5    8
   / \
  2   20
```

The in-order traversal of this BST is: [2, 5, 20, 8, 10]

The violations are between 20 and 8, and between 8 and 10. So, the nodes to swap are 20 and 8.

After swapping, the BST becomes:

```
      10
     /  \
    5    8
   / \
  2   20
```

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- BST with only two nodes.
- BST where the two swapped nodes are adjacent.
- BST where the two swapped nodes are not adjacent.
- BST with a large number of nodes.

---

# 📚 Key Takeaways

- In-order traversal of a BST yields a sorted sequence.
- Two swapped nodes in a BST will cause violations in this sorted order.
- By tracking the previous node during in-order traversal, we can identify the two nodes that need to be swapped back.

---

# 🚀 Interview Tips

- Discuss the importance of in-order traversal in BSTs.
- Mention the time and space complexity of the optimal approach.
- Ask about the follow-up question: What if the BST has more than two swapped nodes?

---

# ✅ Conclusion

The optimal approach efficiently fixes the BST by leveraging in-order traversal to identify and swap the two incorrectly placed nodes. This approach ensures the BST is corrected with minimal time and space complexity.