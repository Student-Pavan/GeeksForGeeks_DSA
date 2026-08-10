# Check Preorder of BST

---

# 📝 Problem Statement

Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree (BST).

**Objective**: Determine if the given sequence can be a valid preorder traversal of a BST.

**Input**:
- A list of integers representing the preorder traversal sequence.

**Output**:
- `true` if the sequence is a valid preorder traversal of a BST, `false` otherwise.

**Constraints**:
- The sequence can contain duplicate values.
- The sequence may be empty.
- The sequence may contain negative numbers.

---

# 💡 Intuition

The key insight is that in a BST, the preorder traversal sequence must satisfy the BST property where for any node, all left descendants are less than the node, and all right descendants are greater than the node. We can use a stack to keep track of the nodes and validate this property as we process the sequence.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Construct the BST**: Reconstruct the BST from the preorder traversal sequence.
2. **Validate the BST**: Perform an inorder traversal of the constructed BST and check if it is sorted.

## 🔹 Algorithm

1. **Construct BST**:
   - Initialize an empty stack and set the first element as the root.
   - For each subsequent element, if it is less than the top of the stack, it becomes the left child of the top node.
   - If it is greater, pop nodes from the stack until you find a node that is greater than the current element, making the current element the right child of that node.

2. **Validate BST**:
   - Perform an inorder traversal of the constructed BST.
   - Check if the inorder traversal is sorted.

## 🔹 Code

```java
import java.util.*;

class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean canRepresentBST(List<Integer> arr) {
        if (arr.isEmpty()) return true;
        TreeNode root = constructBST(arr);
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i) < inorder.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private TreeNode constructBST(List<Integer> arr) {
        if (arr.isEmpty()) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(arr.get(0));
        stack.push(root);
        for (int i = 1; i < arr.size(); i++) {
            TreeNode node = new TreeNode(arr.get(i));
            if (arr.get(i) < stack.peek().val) {
                stack.peek().left = node;
            } else {
                TreeNode parent = null;
                while (!stack.isEmpty() && arr.get(i) > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }

    private void inorderTraversal(TreeNode root, List<Integer> inorder) {
        if (root == null) return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the input `[40, 30, 35, 80, 100]`.

**Step 1: Construct BST**

| Iteration | Current Value | Stack | Action | State |
|---|---|---|---|---|
| 1 | 40 | [40] | Push 40 | Root: 40 |
| 2 | 30 | [40, 30] | Push 30 | 40.left: 30 |
| 3 | 35 | [40, 30, 35] | Push 35 | 30.right: 35 |
| 4 | 80 | [40, 80] | Pop 35, Pop 30, Push 80 | 40.right: 80 |
| 5 | 100 | [40, 80, 100] | Push 100 | 80.right: 100 |

**Step 2: Inorder Traversal**

| Iteration | Current Node | Inorder List | Action |
|---|---|---|---|
| 1 | 30 | [30] | Visit 30 |
| 2 | 35 | [30, 35] | Visit 35 |
| 3 | 40 | [30, 35, 40] | Visit 40 |
| 4 | 80 | [30, 35, 40, 80] | Visit 80 |
| 5 | 100 | [30, 35, 40, 80, 100] | Visit 100 |

**Check Sorted**: [30, 35, 40, 80, 100] is sorted → `true`

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) in the worst case (skewed tree) |
| Space Complexity | O(n) for the stack and recursion |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use a stack to simulate the preorder traversal and validate the BST property on the fly. This avoids constructing the BST explicitly.

## 🔹 Why This Works

By using a stack, we can keep track of the nodes and validate the BST property as we process each element in the sequence. This approach is more efficient as it avoids the overhead of constructing the BST and performing an inorder traversal.

## 🔹 Algorithm

1. Initialize a stack and set `root` to the minimum possible integer value.
2. For each element in the sequence:
   - If the element is less than `root`, return `false` because it violates the BST property.
   - While the stack is not empty and the top of the stack is less than the current element, update `root` to the top of the stack and pop the stack.
   - Push the current element onto the stack.
3. If all elements are processed without violating the BST property, return `true`.

## 🔹 Code

```java
import java.util.*;

class Solution {
    public boolean canRepresentBST(List<Integer> arr) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MIN_VALUE;
        for (int ele : arr) {
            if (ele < root) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() < ele) {
                root = stack.pop();
            }
            stack.push(ele);
        }
        return true;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `[40, 30, 35, 80, 100]`.

| Iteration | Current Value | Stack | Root | Action | Result |
|---|---|---|---|---|---|
| 1 | 40 | [40] | -∞ | Push 40 | Valid |
| 2 | 30 | [40, 30] | -∞ | Push 30 | Valid |
| 3 | 35 | [40, 35] | 30 | Pop 30, Push 35 | Valid |
| 4 | 80 | [80] | 35 | Pop 35, Pop 40, Push 80 | Valid |
| 5 | 100 | [80, 100] | 40 | Push 100 | Valid |

All elements are processed without violating the BST property → `true`

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Input**: `[]` → `true`
- **Single Element**: `[42]` → `true`
- **Duplicate Values**: `[40, 30, 30, 80, 100]` → `true`
- **Negative Values**: `[-10, -20, -30, -5, 0]` → `true`
- **Large Constraints**: A very large sequence of numbers → Efficiently handled by the optimal approach.
- **Sorted Input**: `[1, 2, 3, 4, 5]` → `true`
- **Reverse Sorted Input**: `[5, 4, 3, 2, 1]` → `false`

---

# 📚 Key Takeaways

- **BST Property**: In a BST, for any node, all left descendants are less than the node, and all right descendants are greater than the node.
- **Stack Utilization**: The stack helps in simulating the preorder traversal and validating the BST property on the fly.
- **Efficiency**: The optimal approach avoids constructing the BST and performing an inorder traversal, making it more efficient.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem without using extra space?
  - How would you handle duplicate values in the sequence?
- **Common Pitfalls**:
  - Forgetting to update the `root` value when popping from the stack.
  - Not handling the case where the sequence is empty.
- **Alternative Approaches**:
  - Using recursion to construct the BST and validate the inorder traversal.
- **Optimization Discussions**:
  - The optimal approach uses a stack to simulate the preorder traversal and validate the BST property on the fly, making it more efficient.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently validates the BST property without constructing the BST explicitly, making it more time and space efficient. The key insight is using a stack to simulate the preorder traversal and validating the BST property on the fly. This approach ensures that we efficiently determine if the given sequence can be a valid preorder traversal of a BST.