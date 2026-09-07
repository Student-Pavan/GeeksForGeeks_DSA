# Binary Tree to DLL

Convert a given Binary Tree to a Doubly Linked List (DLL) in-place. The order of nodes in DLL must be the same as Inorder traversal of the given Binary Tree.

---

# 📝 Problem Statement

Given a Binary Tree, convert it to a Doubly Linked List (DLL) in-place. The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL. The order of nodes in DLL must be the same as Inorder traversal of the Binary Tree.

**Example:**

```
Input:
          10
         /  \
        12   15
       / \   /
      20 25 30

Output:
20 12 30 10 25 15
Explanation: The inorder traversal of the given tree is 20 12 30 10 25 15.

Input:
          10
         /  \
        20   30

Output:
20 10 30
```

**Constraints:**
- 1 ≤ Number of nodes ≤ 10^5
- 1 ≤ Data of a node ≤ 10^5

---

# 💡 Intuition

The key insight is that an inorder traversal of a binary tree visits nodes in the order we want for our DLL. We can leverage this by performing an inorder traversal while simultaneously building the DLL by linking nodes as we visit them.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Perform an inorder traversal of the binary tree and store the node values in an array.
2. Create a new DLL by iterating through the array and linking nodes sequentially.
3. Return the head of the newly created DLL.

## 🔹 Algorithm

1. Initialize an empty array to store node values.
2. Perform inorder traversal recursively:
   - Traverse left subtree
   - Visit current node (add to array)
   - Traverse right subtree
3. Create a dummy node to serve as the starting point of the DLL.
4. Iterate through the array:
   - Create a new node for each value
   - Link it to the previous node
   - Update the previous pointer
5. Return the head of the DLL (dummy.right)

## 🔹 Code

```java
/* Structure for tree and linked list
class Node {
  public int data;
  public Node left, right;

  public Node(int x) {
      data = x;
      left = right = null;
  }
};*/
class Solution {
    public Node treeToDLL(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);

        Node dummy = new Node(0);
        Node prev = dummy;

        for (int i = 0; i < list.size(); i++) {
            Node curr = new Node(list.get(i));

            prev.right = curr;
            curr.left = prev;

            prev = curr;
        }

        Node head = dummy.right;
        if (head != null) {
            head.left = null;
        }

        return head;
    }

    private void inorder(Node root, ArrayList<Integer> list) {
        if (root == null)
            return;

        inorder(root.left, list);
        list.add(root.data);
        inorder(root.right, list);
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following binary tree:

```
      10
     /  \
    12   15
   / \   /
  20 25 30
```

| Step | Current Node | Action | List State |
|------|--------------|--------|------------|
| 1    | 20           | Add 20 | [20] |
| 2    | 12           | Add 12 | [20, 12] |
| 3    | 30           | Add 30 | [20, 12, 30] |
| 4    | 10           | Add 10 | [20, 12, 30, 10] |
| 5    | 25           | Add 25 | [20, 12, 30, 10, 25] |
| 6    | 15           | Add 15 | [20, 12, 30, 10, 25, 15] |

After inorder traversal, we have the list [20, 12, 30, 10, 25, 15].

Now we create the DLL:

| Iteration | Current Value | Current State | Result |
|-----------|----------------|----------------|--------|
| 1         | 20             | dummy → 20     | 20 |
| 2         | 12             | 20 ↔ 12        | 20 ↔ 12 |
| 3         | 30             | 12 ↔ 30        | 20 ↔ 12 ↔ 30 |
| 4         | 10             | 30 ↔ 10        | 20 ↔ 12 ↔ 30 ↔ 10 |
| 5         | 25             | 10 ↔ 25        | 20 ↔ 12 ↔ 30 ↔ 10 ↔ 25 |
| 6         | 15             | 25 ↔ 15        | 20 ↔ 12 ↔ 30 ↔ 10 ↔ 25 ↔ 15 |

Final DLL: 20 ↔ 12 ↔ 30 ↔ 10 ↔ 25 ↔ 15

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Instead of using extra space to store node values, we can modify the tree in-place during the inorder traversal to build the DLL directly. This approach avoids the need for an additional array and reduces space complexity.

## 🔹 Why This Works

By maintaining a previous pointer during the inorder traversal, we can link nodes as we visit them, effectively building the DLL in a single pass. This approach leverages the fact that inorder traversal visits nodes in the exact order we need for our DLL.

## 🔹 Algorithm

1. Initialize a previous pointer to null.
2. Perform inorder traversal recursively:
   - Traverse left subtree
   - Process current node:
     - Set current node's left to previous
     - Set previous node's right to current (if previous exists)
     - Update previous to current
   - Traverse right subtree
3. After traversal, find the leftmost node (head) of the DLL.

## 🔹 Code

```java
/* Structure for tree and linked list
class Node {
  public int data;
  public Node left, right;

  public Node(int x) {
      data = x;
      left = right = null;
  }
};*/
class Solution {
    Node prev = null;
    Node head = null;

    public Node treeToDLL(Node root) {
        if (root == null)
            return null;

        // Convert left subtree
        treeToDLL(root.left);

        // Process current node
        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;

        // Convert right subtree
        treeToDLL(root.right);

        return head;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same binary tree:

```
      10
     /  \
    12   15
   / \   /
  20 25 30
```

| Step | Current Node | Previous Node | Action | State |
|------|--------------|----------------|--------|-------|
| 1    | 20           | null           | Set head to 20 | head = 20, prev = 20 |
| 2    | 12           | 20             | Link 20 ↔ 12 | 20 ↔ 12, prev = 12 |
| 3    | 30           | 12             | Link 12 ↔ 30 | 20 ↔ 12 ↔ 30, prev = 30 |
| 4    | 10           | 30             | Link 30 ↔ 10 | 20 ↔ 12 ↔ 30 ↔ 10, prev = 10 |
| 5    | 25           | 10             | Link 10 ↔ 25 | 20 ↔ 12 ↔ 30 ↔ 10 ↔ 25, prev = 25 |
| 6    | 15           | 25             | Link 25 ↔ 15 | 20 ↔ 12 ↔ 30 ↔ 10 ↔ 25 ↔ 15, prev = 15 |

Final DLL: 20 ↔ 12 ↔ 30 ↔ 10 ↔ 25 ↔ 15

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(h) (where h is the height of the tree) |

---

# 🔍 Edge Cases

- Empty tree: Return null.
- Single node tree: Return the node itself.
- Left-skewed tree: All nodes should be linked in a straight line.
- Right-skewed tree: All nodes should be linked in a straight line.
- Complete binary tree: Nodes should be linked in inorder sequence.
- Large tree: Should handle large input sizes efficiently.

---

# 📚 Key Takeaways

- The optimal approach leverages inorder traversal to build the DLL in-place, reducing space complexity.
- The brute force approach is simpler but uses extra space for storing node values.
- Both approaches have O(n) time complexity but differ in space efficiency.
- Understanding inorder traversal is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

- Ask clarifying questions about the expected order of nodes in the DLL.
- Consider whether the solution should handle duplicate values.
- Discuss the trade-offs between the brute force and optimal approaches.
- Be prepared to explain the in-place modification process in detail.

---

# ✅ Conclusion

The optimal approach is preferred for its space efficiency, converting the binary tree to a DLL in-place with O(n) time complexity and O(h) space complexity. The key insight is leveraging inorder traversal to build the DLL directly, avoiding the need for additional storage.