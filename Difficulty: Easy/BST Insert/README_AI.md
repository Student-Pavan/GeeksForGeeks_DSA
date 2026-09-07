# BST Insert

---

# 📝 Problem Statement

Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST while maintaining its properties. The BST property states that for any given node, all values in the left subtree are less than the node's value, and all values in the right subtree are greater than the node's value.

**Input:**
- `root`: The root node of the BST.
- `key`: The value to be inserted into the BST.

**Output:**
- The root node of the modified BST after insertion.

**Constraints:**
- The number of nodes in the tree will be in the range `[0, 10^4]`.
- Each node will have a unique value.
- `-10^8 <= key <= 10^8`
- It's guaranteed that `key` does not already exist in the BST.

---

# 💡 Intuition

The key insight here is understanding how to maintain the BST property during insertion. Since BSTs are inherently ordered, we can recursively traverse the tree to find the appropriate position for the new node. The insertion follows the BST property where:
- If the key is less than the current node's value, we move to the left subtree.
- If the key is greater than the current node's value, we move to the right subtree.
- We continue this process until we reach a null node, where we insert the new node.

This approach ensures that the BST property is maintained after insertion.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves recursively traversing the BST to find the appropriate position for the new node. If the tree is empty, we create a new node with the given key. Otherwise, we compare the key with the current node's value and recursively insert it into the left or right subtree based on the comparison.

## 🔹 Algorithm

1. If the root is null, create a new node with the given key and return it.
2. If the key is less than the current node's value, recursively insert the key into the left subtree.
3. If the key is greater than the current node's value, recursively insert the key into the right subtree.
4. Return the root of the modified tree.

## 🔹 Code

```java
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        }

        return root;
    }
}
```

## 🔹 Dry Run

Let's dry run the insertion of the key `5` into the following BST:

```
      4
     / \
    2   6
   / \
  1   3
```

| Step | Current Node | Key | Action | State |
|------|--------------|-----|--------|-------|
| 1    | 4            | 5   | Compare 5 with 4 | Move to right subtree |
| 2    | 6            | 5   | Compare 5 with 6 | Move to left subtree |
| 3    | null         | 5   | Insert new node with value 5 | New node created |

The modified BST after insertion:

```
      4
     / \
    2   6
   / \ /
  1 3 5
```

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h) where h is the height of the tree. In the worst case, this is O(n) for a skewed tree. |
| Space Complexity | O(h) due to the recursion stack, where h is the height of the tree. In the worst case, this is O(n) for a skewed tree. |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach because the problem inherently requires traversing the tree to find the insertion point. The BST property ensures that we only need to traverse the relevant subtree, making the approach efficient.

## 🔹 Why This Works

This approach works because it leverages the BST property to efficiently navigate to the correct insertion point. By comparing the key with the current node's value, we can determine whether to move left or right, ensuring that the insertion maintains the BST property.

## 🔹 Algorithm

1. If the root is null, create a new node with the given key and return it.
2. If the key is less than the current node's value, recursively insert the key into the left subtree.
3. If the key is greater than the current node's value, recursively insert the key into the right subtree.
4. Return the root of the modified tree.

## 🔹 Code

```java
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}

class Solution {
    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        }

        return root;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the insertion of the key `5` into the following BST:

```
      4
     / \
    2   6
   / \
  1   3
```

| Step | Current Node | Key | Action | State |
|------|--------------|-----|--------|-------|
| 1    | 4            | 5   | Compare 5 with 4 | Move to right subtree |
| 2    | 6            | 5   | Compare 5 with 6 | Move to left subtree |
| 3    | null         | 5   | Insert new node with value 5 | New node created |

The modified BST after insertion:

```
      4
     / \
    2   6
   / \ /
  1 3 5
```

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h) where h is the height of the tree. In the worst case, this is O(n) for a skewed tree. |
| Space Complexity | O(h) due to the recursion stack, where h is the height of the tree. In the worst case, this is O(n) for a skewed tree. |

---

# 🔍 Edge Cases

- **Empty Tree:** The tree is empty, and we need to insert the first node.
- **Insertion at Root:** The key is less than the root's value, and we need to insert it as the new root.
- **Insertion in Left Subtree:** The key is less than the current node's value, and we need to insert it into the left subtree.
- **Insertion in Right Subtree:** The key is greater than the current node's value, and we need to insert it into the right subtree.
- **Duplicate Key:** The key already exists in the tree (though the problem states it doesn't).

---

# 📚 Key Takeaways

- BST insertion maintains the BST property by ensuring that the left subtree contains values less than the node's value and the right subtree contains values greater than the node's value.
- The recursive approach efficiently navigates to the correct insertion point.
- The time and space complexity depend on the height of the tree, which can be O(n) in the worst case for a skewed tree.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you handle duplicate keys?
  - Can you implement an iterative solution instead of a recursive one?
- **Common Pitfalls:**
  - Forgetting to return the root node after insertion.
  - Not handling the case where the tree is empty.
- **Alternative Approaches:**
  - Iterative insertion using a while loop to traverse the tree.
- **Optimization Discussions:**
  - The optimal approach is already efficient, but an iterative solution can avoid the recursion stack overhead.

---

# ✅ Conclusion

The optimal approach for BST insertion is the recursive method, which efficiently maintains the BST property by leveraging the inherent ordering of the tree. The time and space complexity are O(h), where h is the height of the tree, making it efficient for balanced trees. The key takeaway is understanding how to navigate the tree to find the correct insertion point while maintaining the BST property.