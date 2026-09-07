# Closest in BST

---

# 📝 Problem Statement

Given a Binary Search Tree (BST) and a number `k`, find the closest element in the BST to `k`.

**Objective**: Find the value in the BST that is closest to the given number `k`.

**Input**:
- A BST node `root`
- An integer `k`

**Output**:
- The closest value in the BST to `k`

**Constraints**:
- The BST may contain up to 10^5 nodes
- Node values are unique
- `k` can be any integer value

---

# 💡 Intuition

The key insight is that in a BST, the closest value to `k` will either be:
1. The node itself if `k` is found in the tree
2. The predecessor (largest value less than `k`)
3. The successor (smallest value greater than `k`)

This approach leverages the BST property where left children are smaller and right children are larger than the current node.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves traversing the entire BST and comparing each node's value with `k` to find the closest value.

---

## 🔹 Algorithm

1. Initialize a variable `closest` with the root's value
2. Traverse the BST using any traversal method (in-order, pre-order, post-order)
3. For each node, calculate the absolute difference between its value and `k`
4. Update `closest` if the current node's value is closer to `k` than the previous closest
5. Return `closest` after traversing all nodes

---

## 🔹 Code

```java
class Solution {
    private int closest = Integer.MAX_VALUE;

    public int minDiff(Node root, int k) {
        if (root == null) {
            return closest;
        }

        // Update closest if current node is closer
        if (Math.abs(root.data - k) < Math.abs(closest - k)) {
            closest = root.data;
        }

        // If current node is equal to k, we can't get closer
        if (root.data == k) {
            return closest;
        }

        // Recursively search left and right subtrees
        minDiff(root.left, k);
        minDiff(root.right, k);

        return closest;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the following BST and `k = 5`:

```
        4
       / \
      2   6
     / \ / \
    1  3 5 7
```

| Step | Current Node | Absolute Difference | Closest | Action |
|------|--------------|----------------------|---------|--------|
| 1    | 4            | \|4-5\| = 1           | 4       | Update closest |
| 2    | 2            | \|2-5\| = 3           | 4       | No update |
| 3    | 1            | \|1-5\| = 4           | 4       | No update |
| 4    | 3            | \|3-5\| = 2           | 4       | No update |
| 5    | 6            | \|6-5\| = 1           | 4       | No update |
| 6    | 5            | \|5-5\| = 0           | 5       | Update closest |
| 7    | 7            | \|7-5\| = 2           | 5       | No update |

Final closest value: 5

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) where n is the number of nodes in the BST |
| Space Complexity | O(h) where h is the height of the tree (due to recursion stack) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the BST property to find the closest value in O(h) time where h is the height of the tree. Instead of traversing the entire tree, we can navigate to the closest value by comparing `k` with each node's value and moving left or right accordingly.

---

## 🔹 Why This Works

This approach works because:
1. If `k` is found in the tree, it's the closest value
2. If `k` is not found, the closest value must be either:
   - The predecessor (largest value less than `k`)
   - The successor (smallest value greater than `k`)
3. By navigating the tree, we can find these values efficiently

---

## 🔹 Algorithm

1. Initialize `prev` and `next` to null
2. Start at the root node
3. While the current node is not null:
   - If `k` is greater than the current node's value:
     - Set `prev` to current node
     - Move to the right child
   - Else if `k` is less than the current node's value:
     - Set `next` to current node
     - Move to the left child
   - Else (found `k`):
     - Find predecessor (rightmost node in left subtree)
     - Find successor (leftmost node in right subtree)
     - Return 0 (since we found exact match)
4. Calculate the minimum difference between `k` and `prev`/`next` values
5. Return the minimum difference found

---

## 🔹 Code

```java
class Solution {
    public int minDiff(Node root, int k) {
        Node prev = null;
        Node next = null;

        Node temp = root;

        while (temp != null) {
            if (k > temp.data) {
                prev = temp;
                temp = temp.right;
            } else if (k < temp.data) {
                next = temp;
                temp = temp.left;
            } else {
                // Found exact match
                if (temp.left != null) {
                    Node curr = temp.left;
                    while (curr.right != null) {
                        curr = curr.right;
                    }
                    prev = curr;
                }

                if (temp.right != null) {
                    Node curr = temp.right;
                    while (curr.left != null) {
                        curr = curr.left;
                    }
                    next = curr;
                }

                return 0;
            }
        }

        int diff = Integer.MAX_VALUE;

        if (prev != null) {
            diff = Math.min(diff, Math.abs(k - prev.data));
        }

        if (next != null) {
            diff = Math.min(diff, Math.abs(next.data - k));
        }

        return diff;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same BST and `k = 5`:

```
        4
       / \
      2   6
     / \ / \
    1  3 5 7
```

| Step | Current Node | k | prev | next | Action |
|------|--------------|----|------|------|--------|
| 1    | 4            | 5  | null | null | k > current, move right |
| 2    | 6            | 5  | 4    | null | k < current, move left |
| 3    | 5            | 5  | 4    | 6    | Found exact match |
| 4    | 5            | 5  | 4    | 6    | Find predecessor |
| 5    | 3            | -  | 4    | 6    | Predecessor is 4 |
| 6    | 5            | 5  | 4    | 6    | Find successor |
| 7    | 7            | -  | 4    | 6    | Successor is 6 |

Since we found exact match, return 0.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h) where h is the height of the BST |
| Space Complexity | O(1) (constant space) |

---

# 🔍 Edge Cases

1. Empty BST
2. BST with only one node
3. `k` is smaller than all nodes
4. `k` is larger than all nodes
5. `k` is exactly equal to a node's value
6. BST with all nodes equal to `k`
7. BST with duplicate values (though problem states values are unique)

---

# 📚 Key Takeaways

1. BST properties can be leveraged to optimize search operations
2. The optimal approach efficiently finds the closest value without traversing the entire tree
3. Understanding predecessor and successor relationships is crucial
4. Time complexity improves significantly from O(n) to O(h) in optimal solution

---

# 🚀 Interview Tips

1. Ask if the BST is balanced or unbalanced
2. Consider if duplicates are allowed
3. Discuss follow-up questions about handling duplicates
4. Mention that this approach works for any binary search tree, not just balanced ones

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently finds the closest value in O(h) time, where h is the height of the BST. This is significantly better than the brute force approach which takes O(n) time. The key insight is leveraging the BST property to navigate directly to the closest value without examining every node.