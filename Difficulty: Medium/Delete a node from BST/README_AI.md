# Delete a Node from BST

---

# 📝 Problem Statement

Given the root of a Binary Search Tree (BST) and a key, delete the node with the given key from the BST. If the key is not found, return the original BST.

**Constraints:**
- The number of nodes in the tree is in the range [0, 10^4].
- Each node has a unique value.
- The key to be deleted is always present in the BST.

---

# 💡 Intuition

The key insight is that in a BST, the left subtree contains only nodes with values less than the parent node, and the right subtree contains only nodes with values greater than the parent node. When deleting a node, we need to maintain this property.

For deletion, there are three cases:
1. Node to be deleted is a leaf node (no children)
2. Node to be deleted has only one child
3. Node to be deleted has two children

The optimal approach involves recursively finding the node to delete and then handling each case appropriately to maintain the BST properties.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves:
1. Finding the node to delete
2. Handling each case separately:
   - If the node is a leaf, simply remove it
   - If the node has one child, replace it with its child
   - If the node has two children, find the inorder successor (smallest node in the right subtree), replace the node's value with the successor's value, and then recursively delete the successor

## 🔹 Algorithm

1. If the root is null, return null
2. If the key is less than the root's value, recursively delete from the left subtree
3. If the key is greater than the root's value, recursively delete from the right subtree
4. If the key is found:
   - If the node is a leaf, return null
   - If the node has only one child, return that child
   - If the node has two children:
     - Find the inorder successor (minimum value in the right subtree)
     - Replace the node's value with the successor's value
     - Recursively delete the successor from the right subtree

## 🔹 Code

```java
class Solution {
    public Node delNode(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.data) {
            root.left = delNode(root.left, key);
        } else if (key > root.data) {
            root.right = delNode(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = delNode(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with the following BST and key = 50:

```
        50
       /  \
      30   70
     / \   / \
    20 40 60 80
```

| Step | Current Node | Key | Action | State |
|------|--------------|-----|--------|-------|
| 1    | 50           | 50  | Found  | Replace 50 with its inorder successor |
| 2    | 70           | 50  | Find min | 60 is the min in right subtree |
| 3    | 70           | 60  | Found  | Replace 70 with its right child |
| 4    | 60           | 60  | Found  | 60 is a leaf, remove it |

Final BST:

```
        60
       /  \
      30   70
     / \    \
    20 40    80
```

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h) where h is the height of the tree |
| Space Complexity | O(h) due to recursion stack |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses an iterative method to find the node to delete, which can be more efficient in some cases, especially for very deep trees where recursion might cause stack overflow.

## 🔹 Why This Works

The iterative approach avoids recursion and uses a while loop to traverse the tree, which can be more memory efficient for very deep trees. The logic for handling the deletion cases remains the same.

## 🔹 Algorithm

1. Initialize a dummy node to handle edge cases
2. Use a while loop to find the node to delete and its parent
3. If the node is found:
   - If the node is a leaf, simply remove it
   - If the node has one child, replace it with its child
   - If the node has two children:
     - Find the inorder successor (smallest node in the right subtree)
     - Replace the node's value with the successor's value
     - Recursively delete the successor from the right subtree

## 🔹 Code

```java
class Solution {
    public Node delNode(Node root, int key) {
        if (root == null) {
            return root;
        }

        Node dummy = new Node(Integer.MIN_VALUE);
        dummy.right = root;
        Node parent = dummy;
        Node current = root;

        // Find the node to delete and its parent
        while (current != null && current.data != key) {
            parent = current;
            if (key < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return root; // Key not found
        }

        // Case 1: Node to be deleted has no children or only one child
        if (current.left == null || current.right == null) {
            Node newChild = (current.left != null) ? current.left : current.right;

            if (parent.left == current) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        } else {
            // Case 2: Node to be deleted has two children
            Node successorParent = current;
            Node successor = current.right;

            // Find the inorder successor (smallest in the right subtree)
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Replace the node's value with the successor's value
            current.data = successor.data;

            // Delete the successor
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }

        return dummy.right;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same BST and key = 50:

```
        50
       /  \
      30   70
     / \   / \
    20 40 60 80
```

| Step | Current Node | Parent | Key | Action | State |
|------|--------------|--------|-----|--------|-------|
| 1    | 50           | dummy  | 50  | Found  | Replace 50 with its inorder successor |
| 2    | 70           | 50     | 50  | Find min | 60 is the min in right subtree |
| 3    | 60           | 70     | 60  | Found  | Replace 60 with its right child |
| 4    | 60           | 70     | 60  | Found  | 60 is a leaf, remove it |

Final BST:

```
        60
       /  \
      30   70
     / \    \
    20 40    80
```

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(h) where h is the height of the tree |
| Space Complexity | O(1) for iterative approach |

---

# 🔍 Edge Cases

- Empty tree
- Node to be deleted is the root
- Node to be deleted is a leaf
- Node to be deleted has only one child
- Node to be deleted has two children
- Key not found in the tree

---

# 📚 Key Takeaways

- Understanding BST properties is crucial for solving deletion problems
- The inorder successor is essential when deleting a node with two children
- Both recursive and iterative approaches have their advantages
- Time complexity is O(h) where h is the height of the tree
- Space complexity is O(h) for recursion and O(1) for iteration

---

# 🚀 Interview Tips

- Be prepared to explain both recursive and iterative approaches
- Practice drawing BSTs and performing deletions on paper
- Understand the importance of maintaining BST properties after deletion
- Be ready to discuss time and space complexity trade-offs

---

# ✅ Conclusion

The optimal approach using an iterative method is preferred for its better space complexity, especially for very deep trees. Understanding the BST properties and the inorder successor concept is key to solving this problem effectively. The time complexity remains O(h) in both approaches, but the iterative method avoids recursion stack overhead.