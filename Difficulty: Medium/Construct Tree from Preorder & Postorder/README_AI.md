# Construct Tree from Preorder & Postorder

---

# 📝 Problem Statement

Given two integer arrays `preorder` and `postorder` where `preorder` is the preorder traversal of a binary tree and `postorder` is the postorder traversal of the same tree, construct and return the binary tree.

**Constraints:**
- 1 <= preorder.length <= 30
- 1 <= preorder[i] <= preorder.length
- All values of preorder are unique.
- postorder.length == preorder.length
- All values of postorder are unique.

---

# 💡 Intuition

The key insight is recognizing that the first element in `preorder` is always the root of the tree. The last element in `postorder` is also the root of the tree. By combining these two observations, we can recursively construct the left and right subtrees.

The approach involves:
1. Using the root from `preorder` to divide the tree into left and right subtrees
2. Using the root's position in `postorder` to determine the size of the left subtree
3. Recursively building the left and right subtrees

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Finding the root in `postorder` array
2. Determining the size of the left subtree
3. Recursively building the left and right subtrees

## 🔹 Algorithm

1. If the current segment is empty, return null
2. Create a node with the first element of `preorder`
3. If the segment length is 1, return the node
4. Find the position of the next `preorder` element in `postorder`
5. Recursively build the left subtree using the left segment
6. Recursively build the right subtree using the right segment

## 🔹 Code

```java
class Solution {
    public Node constructTree(int[] pre, int[] post) {
        return buildTree(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    private Node buildTree(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }

        Node root = new Node(pre[preStart]);

        if (preStart == preEnd) {
            return root;
        }

        int leftRootVal = pre[preStart + 1];
        int leftRootPos = findIndex(post, leftRootVal, postStart, postEnd);

        int leftSubtreeSize = leftRootPos - postStart + 1;

        root.left = buildTree(pre, post, preStart + 1, preStart + leftSubtreeSize, postStart, leftRootPos);
        root.right = buildTree(pre, post, preStart + leftSubtreeSize + 1, preEnd, leftRootPos + 1, postEnd - 1);

        return root;
    }

    private int findIndex(int[] arr, int val, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
```

## 🔹 Dry Run

Let's dry run with `preorder = [1,2,4,5,3,6,7]` and `postorder = [4,5,2,6,7,3,1]`:

| Step | preStart | preEnd | postStart | postEnd | Action | Root Value | Left Subtree Size |
|------|----------|--------|-----------|---------|--------|------------|-------------------|
| 1    | 0        | 6      | 0         | 6       | Create root | 1          | -                 |
| 2    | 1        | 6      | 0         | 5       | Find left root | 2          | 2                 |
| 3    | 1        | 2      | 0         | 1       | Build left subtree | 2          | 2                 |
| 4    | 2        | 2      | 0         | 0       | Create leaf | 4          | -                 |
| 5    | 3        | 3      | 1         | 1       | Create leaf | 5          | -                 |
| 6    | 4        | 6      | 2         | 5       | Build right subtree | 3          | 3                 |
| 7    | 4        | 4      | 2         | 2       | Create leaf | 6          | -                 |
| 8    | 5        | 5      | 3         | 3       | Create leaf | 7          | -                 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a hash map to store the indices of elements in the `postorder` array, allowing for O(1) lookups. This eliminates the need for linear searches in the brute force approach.

## 🔹 Why This Works

By pre-processing the `postorder` array into a hash map, we can quickly find the position of any element, significantly improving the time complexity from O(n²) to O(n).

## 🔹 Algorithm

1. Create a hash map to store the indices of elements in the `postorder` array
2. Initialize a pointer to track the current position in the `preorder` array
3. Recursively build the tree:
   - Create a node with the current `preorder` element
   - If the current segment is a leaf node, return it
   - Find the position of the next `preorder` element in the hash map
   - Recursively build the left and right subtrees

## 🔹 Code

```java
class Solution {
    int preIndex = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public Node constructTree(int[] pre, int[] post) {
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return build(pre, post, 0, post.length - 1);
    }

    private Node build(int[] pre, int[] post, int l, int r) {
        if (preIndex >= pre.length || l > r) {
            return null;
        }

        Node root = new Node(pre[preIndex++]);

        if (l == r || preIndex >= pre.length) {
            return root;
        }

        int idx = map.get(pre[preIndex]);

        root.left = build(pre, post, l, idx);
        root.right = build(pre, post, idx + 1, r - 1);

        return root;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run with the same input:

| Step | preIndex | Current Preorder | Action | Root Value | Left Index | Right Index |
|------|----------|-------------------|--------|------------|------------|-------------|
| 1    | 0        | 1                 | Create root | 1          | -          | -           |
| 2    | 1        | 2                 | Find left root | 2          | 0          | 1           |
| 3    | 1        | 2                 | Build left subtree | 2          | 0          | 1           |
| 4    | 2        | 4                 | Create leaf | 4          | -          | -           |
| 5    | 3        | 5                 | Create leaf | 5          | -          | -           |
| 6    | 4        | 3                 | Build right subtree | 3          | 2          | 5           |
| 7    | 4        | 3                 | Create root | 3          | 2          | 5           |
| 8    | 5        | 6                 | Create leaf | 6          | -          | -           |
| 9    | 6        | 7                 | Create leaf | 7          | -          | -           |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty input arrays
- Single element in both arrays
- Skewed trees (left or right)
- Large input sizes within constraints
- All values are the same (though constraints say all values are unique)

---

# 📚 Key Takeaways

- The optimal approach uses a hash map for O(1) lookups, significantly improving performance
- Recursive tree construction is a common pattern in tree problems
- Understanding the relationship between preorder and postorder traversals is crucial
- The first element in preorder is always the root, and the last element in postorder is also the root

---

# 🚀 Interview Tips

- Be prepared to explain the relationship between preorder and postorder traversals
- Practice drawing small trees to understand the construction process
- Consider discussing alternative approaches like using stacks for iterative solutions
- Be ready to discuss time and space complexity trade-offs

---

# ✅ Conclusion

The optimal solution using a hash map provides an efficient O(n) time complexity approach to constructing the binary tree from preorder and postorder traversals. Understanding the relationship between these traversal orders is key to solving this problem efficiently. The recursive approach naturally fits the tree construction problem, making it a strong choice for interview scenarios.