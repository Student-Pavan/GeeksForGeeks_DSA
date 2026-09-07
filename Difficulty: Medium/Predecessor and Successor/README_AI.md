# 📌 Predecessor and Successor

---

# 📝 Problem Statement

Given a BST and a key, find the predecessor and successor of the key in the BST.

**Predecessor**: The largest key in the BST that is smaller than the given key.

**Successor**: The smallest key in the BST that is larger than the given key.

**Constraints**:
- The BST contains unique values.
- The key may or may not exist in the BST.

---

# 💡 Intuition

The optimal approach leverages the properties of BSTs:
1. In-order traversal yields sorted order.
2. For any node, left subtree contains smaller values, right subtree contains larger values.
3. We can traverse the tree while keeping track of potential predecessor and successor.

The key insight is that during traversal, we can update predecessor and successor based on the current node's value relative to the key.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform in-order traversal to get all elements in sorted order.
2. Iterate through the sorted list to find predecessor and successor.

## 🔹 Algorithm

1. Perform in-order traversal and store all nodes in a list.
2. Iterate through the list to find:
   - The largest value smaller than the key (predecessor)
   - The smallest value larger than the key (successor)

## 🔹 Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> list = new ArrayList<>();
        inOrderTraversal(root, list);

        Node pred = null;
        Node succ = null;

        for (Node node : list) {
            if (node.data < key) {
                pred = node;
            } else if (node.data > key) {
                succ = node;
                break;
            }
        }

        ArrayList<Node> result = new ArrayList<>();
        result.add(pred);
        result.add(succ);
        return result;
    }

    private void inOrderTraversal(Node node, ArrayList<Node> list) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, list);
        list.add(node);
        inOrderTraversal(node.right, list);
    }
}
```

## 🔹 Dry Run

Let's consider the following BST:

```
        50
       /  \
      30   70
     / \   / \
    20 40 60 80
```

**Key = 65**

| Step | Current Node | Action | Predecessor | Successor |
|------|--------------|--------|-------------|-----------|
| 1    | 50           | 65 > 50 | 50          | null      |
| 2    | 70           | 65 < 70 | 50          | 70        |
| 3    | 60           | 65 > 60 | 60          | 70        |

Final result: [60, 70]

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) (in-order traversal) + O(N) (searching) = O(N) |
| Space Complexity | O(N) (for storing the list) + O(N) (recursion stack) = O(N) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Traverse the BST while keeping track of potential predecessor and successor.
2. Update predecessor and successor based on the current node's value relative to the key.

## 🔹 Why This Works

This approach efficiently finds predecessor and successor in a single traversal without storing all nodes. It leverages BST properties to make intelligent decisions during traversal.

## 🔹 Algorithm

1. Initialize predecessor and successor as null.
2. Start from the root node.
3. While current node is not null:
   - If key is less than current node's value:
     - Update successor to current node
     - Move to left child
   - Else if key is greater than current node's value:
     - Update predecessor to current node
     - Move to right child
   - Else (key found):
     - Find predecessor in left subtree (rightmost node)
     - Find successor in right subtree (leftmost node)
     - Break the loop

## 🔹 Code

```java
import java.util.ArrayList;

class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
}

class Solution {
    public ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> list = new ArrayList<>();

        Node pred = null;
        Node succ = null;
        Node curr = root;

        while (curr != null) {
            if (key < curr.data) {
                succ = curr;
                curr = curr.left;
            } else if (key > curr.data) {
                pred = curr;
                curr = curr.right;
            } else {
                // Find predecessor
                if (curr.left != null) {
                    Node temp = curr.left;
                    while (temp.right != null) {
                        temp = temp.right;
                    }
                    pred = temp;
                }

                // Find successor
                if (curr.right != null) {
                    Node temp = curr.right;
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    succ = temp;
                }
                break;
            }
        }

        list.add(pred);
        list.add(succ);
        return list;
    }
}
```

## 🔹 Detailed Dry Run

Using the same BST as before:

```
        50
       /  \
      30   70
     / \   / \
    20 40 60 80
```

**Key = 65**

| Step | Current Node | Key Comparison | Action | Predecessor | Successor |
|------|--------------|-----------------|--------|-------------|-----------|
| 1    | 50           | 65 > 50         | Move right | 50          | null      |
| 2    | 70           | 65 < 70         | Update successor, move left | 50          | 70        |
| 3    | 60           | 65 > 60         | Move right | 60          | 70        |
| 4    | 80           | 65 < 80         | Update successor, move left | 60          | 80        |
| 5    | null         | -               | Break   | 60          | 80        |

Final result: [60, 80]

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(H) where H is the height of the tree (O(log N) for balanced BST) |
| Space Complexity | O(1) (constant extra space) |

---

# 🔍 Edge Cases

1. **Key exists in the BST**:
   - Predecessor is the rightmost node in the left subtree.
   - Successor is the leftmost node in the right subtree.

2. **Key doesn't exist in the BST**:
   - Predecessor is the last node where we took a right turn.
   - Successor is the last node where we took a left turn.

3. **Key is the smallest element**:
   - Predecessor is null.
   - Successor is the leftmost node.

4. **Key is the largest element**:
   - Predecessor is the rightmost node.
   - Successor is null.

5. **Empty BST**:
   - Both predecessor and successor are null.

---

# 📚 Key Takeaways

1. BST properties enable efficient predecessor and successor search.
2. Single traversal approach is more efficient than storing all nodes.
3. Understanding BST traversal patterns is crucial for solving BST problems.
4. The optimal solution works in O(H) time, which is better than O(N) for skewed trees.

---

# 🚀 Interview Tips

1. **Follow-up questions**:
   - What if the BST is very large and doesn't fit in memory?
   - How would you find kth predecessor/successor?
   - Can you solve this iteratively without recursion?

2. **Common pitfalls**:
   - Forgetting to handle cases where key doesn't exist.
   - Not properly updating predecessor and successor during traversal.
   - Missing edge cases like empty tree or single node.

3. **Alternative approaches**:
   - Using Morris Traversal to achieve O(1) space complexity.
   - Using a stack to simulate recursion.

4. **Optimization discussions**:
   - Why the optimal solution is better than brute force.
   - The trade-off between time and space complexity.

---

# ✅ Conclusion

The optimal solution is preferred because:
1. It operates in O(H) time complexity.
2. It uses constant extra space.
3. It leverages BST properties efficiently.
4. It handles all edge cases properly.

The most important insight is understanding how to traverse the BST while maintaining predecessor and successor information, which is a common pattern in BST problems.