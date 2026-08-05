# 📌 Preorder Traversal

---

# 📝 Problem Statement

Given the root of a binary tree, return the preorder traversal of its nodes' values.

**Preorder traversal** follows the order: root, left, right.

**Example 1:**

```
Input: root = [1,null,2,3]
Output: [1,2,3]
```

**Example 2:**

```
Input: root = []
Output: []
```

**Example 3:**

```
Input: root = [1]
Output: [1]
```

**Constraints:**

- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

---

# 💡 Intuition

The key insight is that preorder traversal visits nodes in the order of root, left, right. This is a fundamental tree traversal technique that can be implemented both recursively and iteratively. The recursive approach is straightforward and leverages the call stack to keep track of nodes, while the iterative approach uses an explicit stack to achieve the same result.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses a recursive method to traverse the tree. The algorithm visits the root node first, then recursively traverses the left subtree, followed by the right subtree. This approach is simple to implement but has a space complexity of O(h), where h is the height of the tree, due to the recursion stack.

---

## 🔹 Algorithm

1. Create an empty list to store the result.
2. Define a helper function that takes a node as input:
   - If the node is null, return.
   - Add the node's value to the result list.
   - Recursively call the helper function on the left child.
   - Recursively call the helper function on the right child.
3. Call the helper function starting with the root node.
4. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
    public ArrayList<Integer> preOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        preordertraversal(root, ans);
        return ans;
    }

    public void preordertraversal(Node root, ArrayList<Integer> ans) {
        if (root == null)
            return;
        // root
        ans.add(root.data);
        // left
        preordertraversal(root.left, ans);
        // right
        preordertraversal(root.right, ans);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input `root = [1,null,2,3]`.

| Step | Current Node | Action | Result List |
|------|--------------|--------|-------------|
| 1    | 1            | Add 1  | [1]         |
| 2    | null         | Return | [1]         |
| 3    | 2            | Add 2  | [1, 2]      |
| 4    | 3            | Add 3  | [1, 2, 3]   |
| 5    | null         | Return | [1, 2, 3]   |
| 6    | null         | Return | [1, 2, 3]   |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses an iterative method with a stack to traverse the tree. This approach avoids the overhead of recursive calls and uses an explicit stack to keep track of nodes, resulting in the same time complexity but with a space complexity of O(h), where h is the height of the tree.

---

## 🔹 Why This Works

The iterative approach mimics the recursive approach by using a stack to keep track of nodes to visit. By pushing nodes onto the stack in the order of right child first and then left child, we ensure that the left child is processed before the right child, which is consistent with the preorder traversal order.

---

## 🔹 Algorithm

1. Create an empty list to store the result.
2. Create an empty stack and push the root node onto it.
3. While the stack is not empty:
   - Pop a node from the stack.
   - Add the node's value to the result list.
   - Push the right child of the node onto the stack if it exists.
   - Push the left child of the node onto the stack if it exists.
4. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Stack;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {
    public ArrayList<Integer> preOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            ans.add(current.data);
            if (current.right != null)
                stack.push(current.right);
            if (current.left != null)
                stack.push(current.left);
        }
        return ans;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `root = [1,null,2,3]`.

| Step | Stack | Current Node | Action | Result List |
|------|-------|--------------|--------|-------------|
| 1    | [1]   | 1            | Add 1  | [1]         |
| 2    | [3, 2]| 1            | Push 2, 3 | [1]         |
| 3    | [3]   | 2            | Add 2  | [1, 2]      |
| 4    | []    | 2            | Push 3 | [1, 2]      |
| 5    | []    | 3            | Add 3  | [1, 2, 3]   |
| 6    | []    | 3            | Return | [1, 2, 3]   |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) |

---

# 🔍 Edge Cases

- **Empty Tree:** The tree has no nodes.
- **Single Node Tree:** The tree has only one node.
- **Left-Skewed Tree:** The tree has only left children.
- **Right-Skewed Tree:** The tree has only right children.

---

# 📚 Key Takeaways

- Preorder traversal visits nodes in the order of root, left, right.
- Recursive and iterative approaches are both valid and have the same time complexity.
- The iterative approach uses a stack to avoid recursion, which can be more efficient for very deep trees.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you implement the solution iteratively?
  - What is the time and space complexity of your solution?
- **Common Pitfalls:**
  - Forgetting to handle the null root case.
  - Pushing children onto the stack in the wrong order.
- **Alternative Approaches:**
  - Using Morris Traversal for O(1) space complexity.
- **Optimization Discussions:**
  - The iterative approach is more space-efficient for very deep trees.

---

# ✅ Conclusion

The optimal solution uses an iterative approach with a stack to traverse the tree, resulting in the same time complexity as the recursive approach but with a space complexity of O(h), where h is the height of the tree. This approach is more efficient for very deep trees and is a good example of how to optimize a recursive solution using an explicit stack.