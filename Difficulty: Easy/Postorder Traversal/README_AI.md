# 📌 Postorder Traversal

---

# 📝 Problem Statement

Given the root of a binary tree, return the postorder traversal of its nodes' values.

**Objective**: Perform postorder traversal of a binary tree.

**Input**:
- A binary tree represented by its root node.

**Output**:
- An array containing the values of the nodes in postorder traversal.

**Constraints**:
- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

---

# 💡 Intuition

Postorder traversal visits nodes in the order: left subtree, right subtree, root. This means we recursively traverse the left and right subtrees before processing the current node.

The key insight is that postorder traversal naturally follows a recursive approach where we process the left and right children before the parent node.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to perform postorder traversal. We recursively traverse the left subtree, then the right subtree, and finally add the current node's value to the result list.

---

## 🔹 Algorithm

1. Create an empty list to store the result.
2. Define a helper function that takes a node as input.
3. If the node is null, return.
4. Recursively call the helper function on the left child.
5. Recursively call the helper function on the right child.
6. Add the current node's value to the result list.
7. Call the helper function starting from the root node.
8. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left, right;
    Node(int val){
        data = val;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> postOrder(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        postordertraversal(root, list);
        return list;
    }

    public void postordertraversal(Node root, ArrayList<Integer> list) {
        if (root == null)
            return;
        postordertraversal(root.left, list);
        postordertraversal(root.right, list);
        list.add(root.data);
    }
}
```

---

## 🔹 Dry Run

Let's consider the following binary tree:

```
    1
     \
      2
     /
    3
```

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 1.left | 1.right | Traverse left subtree | Current node: 1 |
| 2 | null | null | Base case | Current node: null |
| 3 | null | null | Base case | Current node: null |
| 4 | 2.left | 2.right | Traverse left subtree | Current node: 2 |
| 5 | null | null | Base case | Current node: null |
| 6 | 3.left | 3.right | Traverse left subtree | Current node: 3 |
| 7 | null | null | Base case | Current node: null |
| 8 | null | null | Base case | Current node: null |
| 9 | null | null | Add to list | Current node: 3 |
| 10 | null | null | Add to list | Current node: 2 |
| 11 | null | null | Add to list | Current node: 1 |

Final result: [3, 2, 1]

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses an iterative method with a stack to avoid recursion. This approach is more efficient in terms of space complexity as it doesn't use the call stack.

---

## 🔹 Why This Works

The iterative approach mimics the recursive approach but uses a stack to keep track of the nodes to be processed. This avoids the overhead of recursive calls and can be more efficient in some cases.

---

## 🔹 Algorithm

1. Create an empty list to store the result.
2. Create an empty stack and push the root node onto the stack.
3. While the stack is not empty:
   a. Pop a node from the stack and add its value to the result list.
   b. Push the left child of the node onto the stack.
   c. Push the right child of the node onto the stack.
4. Reverse the result list.
5. Return the result list.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Stack;

class Node {
    int data;
    Node left, right;
    Node(int val){
        data = val;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Integer> postOrder(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            list.add(current.data);

            if (current.left != null)
                stack.push(current.left);
            if (current.right != null)
                stack.push(current.right);
        }

        // Reverse the list to get the correct postorder traversal
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            result.add(list.get(i));
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's consider the following binary tree:

```
    1
     \
      2
     /
    3
```

| Step | Stack | Action | Result |
|---|---|---|---|
| 1 | [1] | Push root | [] |
| 2 | [] | Pop 1 | [] |
| 3 | [2] | Push right child | [1] |
| 4 | [] | Pop 2 | [1] |
| 5 | [3] | Push left child | [1, 2] |
| 6 | [] | Pop 3 | [1, 2] |
| 7 | [] | Stack empty | [1, 2, 3] |
| 8 | [] | Reverse result | [3, 2, 1] |

Final result: [3, 2, 1]

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty tree: The algorithm should return an empty list.
- Single node: The algorithm should return a list containing the single node's value.
- Skewed tree: The algorithm should handle both left and right skewed trees correctly.
- Full binary tree: The algorithm should handle a complete binary tree with all levels filled.

---

# 📚 Key Takeaways

- Postorder traversal visits nodes in the order: left subtree, right subtree, root.
- Recursive approach is straightforward but uses O(n) space due to the call stack.
- Iterative approach using a stack avoids recursion and is more space-efficient.
- The iterative approach requires reversing the result list to get the correct postorder traversal.

---

# 🚀 Interview Tips

- Discuss the difference between preorder, inorder, and postorder traversals.
- Mention that postorder traversal is useful for deleting a tree or evaluating expressions.
- Be prepared to discuss the space complexity of recursive vs. iterative approaches.
- Practice drawing binary trees and performing traversals on paper.

---

# ✅ Conclusion

The optimal approach using an iterative method with a stack is preferred as it avoids the overhead of recursive calls and is more space-efficient. The key insight is that postorder traversal can be achieved by processing nodes in reverse order (root, right, left) and then reversing the result list.

---

# 🎨 Formatting Rules

- Use proper markdown headings.
- Use markdown separators.
- Use syntax-highlighted code blocks.
- Use markdown tables extensively.
- Ensure GitHub readability.
- Keep spacing visually clean.
- Make README visually premium.
- Keep explanations concise but valuable.