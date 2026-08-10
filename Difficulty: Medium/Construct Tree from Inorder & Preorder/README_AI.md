# 📌 Construct Tree from Inorder & Preorder

---

# 📝 Problem Statement

Given two integer arrays `inorder` and `preorder` where:
- `inorder` is the inorder traversal of a binary tree
- `preorder` is the preorder traversal of the same binary tree

Construct and return the binary tree.

**Constraints:**
- `1 <= inorder.length <= 3000`
- `inorder.length == preorder.length`
- All values of inorder and preorder are unique
- Each value of inorder also appears in preorder
- `inorder` is guaranteed to be the inorder traversal of the tree
- `preorder` is guaranteed to be the preorder traversal of the same tree

---

# 💡 Intuition

The key insight is that in preorder traversal, the first element is always the root of the tree. In inorder traversal, all elements to the left of the root belong to the left subtree, and all elements to the right belong to the right subtree. This allows us to recursively construct the tree by identifying the root and partitioning the remaining elements.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Find the root in the inorder array
2. Recursively build the left subtree using elements to the left of the root in inorder
3. Recursively build the right subtree using elements to the right of the root in inorder

## 🔹 Algorithm

1. Start with the first element of preorder as root
2. Find this element in inorder to determine left and right subtrees
3. Recursively build left subtree using left portion of inorder
4. Recursively build right subtree using right portion of inorder

## 🔹 Code

```java
class Solution {

    static int search(int[] inorder, int left, int right, int val) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    static Node helper(int[] preorder, int[] inorder, int[] preIdx,
                       int left, int right) {

        if (left > right) {
            return null;
        }

        // Create root
        Node root = new Node(preorder[preIdx[0]]);
        preIdx[0]++;

        // If only one node
        if (left == right) {
            return root;
        }

        // Find root in inorder
        int inIdx = search(inorder, left, right, root.data);

        // Build left subtree
        root.left = helper(preorder, inorder, preIdx, left, inIdx - 1);

        // Build right subtree
        root.right = helper(preorder, inorder, preIdx, inIdx + 1, right);

        return root;
    }

    public static Node buildTree(int[] inorder, int[] preorder) {

        int[] preIdx = {0};   // works like int& preIdx in C++

        return helper(preorder, inorder, preIdx, 0, inorder.length - 1);
    }
}
```

## 🔹 Dry Run

Let's dry run with:
- inorder = [9, 3, 15, 20, 7]
- preorder = [3, 9, 20, 15, 7]

| Step | Root | Left | Right | Action |
|---|---|---|---|---|
| 1 | 3 | 0 | 4 | Create root 3 |
| 2 | 9 | 0 | 0 | Find root 9 in left subtree |
| 3 | 9 | 0 | 0 | Create leaf node 9 |
| 4 | 20 | 2 | 4 | Find root 20 in right subtree |
| 5 | 15 | 2 | 3 | Find root 15 in left subtree |
| 6 | 15 | 2 | 2 | Create leaf node 15 |
| 7 | 7 | 4 | 4 | Find root 7 in right subtree |
| 8 | 7 | 4 | 4 | Create leaf node 7 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n²) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use a hashmap to store indices of inorder elements for O(1) lookups, reducing the time complexity.

## 🔹 Why This Works

The hashmap allows us to eliminate the O(n) search time in each recursive call, making the overall time complexity O(n).

## 🔹 Algorithm

1. Create a hashmap to store value-to-index mappings of inorder array
2. Use a preorder index to track current root
3. Recursively build left and right subtrees using the hashmap

## 🔹 Code

```java
import java.util.HashMap;

class Solution {

    static int preIdx = 0;

    static Node helper(int[] preorder, int[] inorder, HashMap<Integer, Integer> inMap,
                       int left, int right) {

        if (left > right) {
            return null;
        }

        // Create root
        Node root = new Node(preorder[preIdx++]);

        // If only one node
        if (left == right) {
            return root;
        }

        // Find root in inorder using hashmap
        int inIdx = inMap.get(root.data);

        // Build left subtree
        root.left = helper(preorder, inorder, inMap, left, inIdx - 1);

        // Build right subtree
        root.right = helper(preorder, inorder, inMap, inIdx + 1, right);

        return root;
    }

    public static Node buildTree(int[] inorder, int[] preorder) {

        preIdx = 0;

        // Create hashmap for inorder indices
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return helper(preorder, inorder, inMap, 0, inorder.length - 1);
    }
}
```

## 🔹 Detailed Dry Run

Using same input:
- inorder = [9, 3, 15, 20, 7]
- preorder = [3, 9, 20, 15, 7]

| Step | Root | Left | Right | Action |
|---|---|---|---|---|
| 1 | 3 | 0 | 4 | Create root 3 |
| 2 | 9 | 0 | 0 | Find root 9 in left subtree |
| 3 | 9 | 0 | 0 | Create leaf node 9 |
| 4 | 20 | 2 | 4 | Find root 20 in right subtree |
| 5 | 15 | 2 | 3 | Find root 15 in left subtree |
| 6 | 15 | 2 | 2 | Create leaf node 15 |
| 7 | 7 | 4 | 4 | Find root 7 in right subtree |
| 8 | 7 | 4 | 4 | Create leaf node 7 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Single node tree
- Left-skewed tree
- Right-skewed tree
- Complete binary tree
- Full binary tree
- Large input size (approaching constraint limits)

---

# 📚 Key Takeaways

1. Tree construction from traversals is a common DSA pattern
2. Preorder traversal gives root information first
3. Inorder traversal provides left-right subtree partitioning
4. Hashmaps can optimize search operations in recursive algorithms
5. Recursive tree construction follows the divide-and-conquer paradigm

---

# 🚀 Interview Tips

- Be prepared to discuss time/space tradeoffs between approaches
- Consider asking if the input arrays contain duplicates
- Be ready to explain why the optimal solution is more efficient
- Practice visualizing tree construction from traversals

---

# ✅ Conclusion

The optimal solution using a hashmap provides the most efficient construction with O(n) time complexity, making it the preferred approach for interview scenarios. The key insight is leveraging the properties of preorder and inorder traversals to recursively partition the tree.