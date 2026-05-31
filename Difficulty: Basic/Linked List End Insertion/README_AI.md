# Linked List End Insertion

---

# 📝 Problem Statement

Given a linked list and a value `x`, insert a new node with value `x` at the end of the linked list. Return the head of the modified linked list.

**Input:**
- `head`: The head node of the linked list
- `x`: The value to be inserted at the end

**Output:**
- The head of the modified linked list

**Constraints:**
- The number of nodes in the linked list can be up to 10^5
- -10^9 <= Node.val <= 10^9
- -10^9 <= x <= 10^9

---

# 💡 Intuition

The problem requires inserting a node at the end of a linked list. The key insight is that we need to traverse the linked list until we reach the last node, then attach the new node to it. This involves simple traversal and pointer manipulation.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Create a new node with the given value `x`.
2. If the linked list is empty (head is null), return the new node as the head.
3. Traverse the linked list starting from the head until the last node is reached.
4. Attach the new node to the last node's `next` pointer.
5. Return the head of the modified linked list.

## 🔹 Algorithm

1. Create a new node with value `x`.
2. If `head` is null:
   - Return the new node.
3. Initialize a temporary pointer `temp` to `head`.
4. While `temp.next` is not null:
   - Move `temp` to `temp.next`.
5. Set `temp.next` to the new node.
6. Return `head`.

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Solution {
    public Node insertAtEnd(Node head, int x) {
        Node newNode = new Node(x);

        if (head == null) {
            return newNode;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        return head;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 2 -> 3` and `x = 4`.

| Step | Temp | Temp.next | Action | State |
|---|---|---|---|---|
| 1 | head (1) | 2 | Check if temp.next is null | temp.next is not null |
| 2 | 2 | 3 | Move temp to temp.next | temp is now 2 |
| 3 | 3 | null | Check if temp.next is null | temp.next is null |
| 4 | 3 | 4 | Set temp.next to new node | Linked list becomes 1 -> 2 -> 3 -> 4 |
| 5 | - | - | Return head | Head is still 1 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach since inserting at the end of a linked list inherently requires traversing the entire list. There is no more efficient way to do this without additional information about the linked list structure.

## 🔹 Why This Works

This approach works because it correctly handles both the empty list case and the non-empty list case. By traversing to the end of the list and then attaching the new node, we ensure the node is inserted at the correct position.

## 🔹 Algorithm

1. Create a new node with value `x`.
2. If `head` is null:
   - Return the new node.
3. Initialize a temporary pointer `temp` to `head`.
4. While `temp.next` is not null:
   - Move `temp` to `temp.next`.
5. Set `temp.next` to the new node.
6. Return `head`.

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Solution {
    public Node insertAtEnd(Node head, int x) {
        Node newNode = new Node(x);

        if (head == null) {
            return newNode;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        return head;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same linked list: `1 -> 2 -> 3` and `x = 4`.

| Step | Temp | Temp.next | Action | State |
|---|---|---|---|---|
| 1 | head (1) | 2 | Check if temp.next is null | temp.next is not null |
| 2 | 2 | 3 | Move temp to temp.next | temp is now 2 |
| 3 | 3 | null | Check if temp.next is null | temp.next is null |
| 4 | 3 | 4 | Set temp.next to new node | Linked list becomes 1 -> 2 -> 3 -> 4 |
| 5 | - | - | Return head | Head is still 1 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Linked List:** The linked list is empty, so the new node becomes the head.
- **Single Node Linked List:** The linked list has only one node, so the new node is inserted after it.
- **Large Linked List:** The linked list has a large number of nodes, ensuring the algorithm handles it efficiently.
- **Negative Values:** The values in the linked list or the value to be inserted can be negative.

---

# 📚 Key Takeaways

- Inserting at the end of a linked list requires traversing the entire list.
- The optimal approach is the same as the brute force approach due to the nature of linked lists.
- Understanding pointer manipulation is crucial for linked list operations.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you insert at the beginning of the linked list?
  - How would you insert at a specific position?
- **Common Pitfalls:**
  - Forgetting to handle the empty list case.
  - Not updating the `next` pointer of the last node correctly.
- **Alternative Approaches:**
  - Using recursion to traverse the list.
- **Optimization Discussions:**
  - The optimal approach is already optimal for this problem.

---

# ✅ Conclusion

The optimal solution for inserting a node at the end of a linked list involves traversing the list to the end and then attaching the new node. This approach ensures the node is inserted correctly and efficiently. The key takeaway is understanding the importance of pointer manipulation in linked list operations.