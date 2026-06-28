# Delete in a Doubly Linked List

---

# 📝 Problem Statement

Given a doubly linked list and a position, the task is to delete a node from the given position in the doubly linked list.

**Objective**: Delete the node at the specified position in the doubly linked list.

**Input**:
- A doubly linked list
- An integer `x` representing the position to delete

**Output**:
- The modified doubly linked list with the node at position `x` removed

**Constraints**:
- The position `x` is valid (1 ≤ x ≤ length of list)
- The list may contain duplicate values
- The list may be empty

---

# 💡 Intuition

The key insight is that in a doubly linked list, each node has both a `next` and `prev` pointer, which allows us to traverse the list in both directions. To delete a node at a specific position, we need to:
1. Find the node at the given position
2. Update the `next` pointer of the previous node to skip the node to be deleted
3. Update the `prev` pointer of the next node to point to the previous node
4. Handle edge cases where the node to be deleted is the head or tail of the list

This approach efficiently handles the deletion by maintaining the doubly linked list structure while traversing only once through the list.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves traversing the list to the node at the given position and then performing the deletion operation. This approach is straightforward but may not be the most efficient for very large lists.

---

## 🔹 Algorithm

1. If the position is 1 (head node), update the head to the next node and set the `prev` pointer of the new head to null.
2. Otherwise, traverse the list to the node at position `x-1`.
3. Update the `next` pointer of the node at position `x-1` to skip the node at position `x`.
4. If the node at position `x` is not the tail, update the `prev` pointer of the node at position `x+1` to point to the node at position `x-1`.
5. Return the head of the modified list.

---

## 🔹 Code

```java
class Solution {
    public Node delPos(Node head, int x) {
        if (head == null) {
            return null;
        }

        Node temp = head;

        if (x == 1) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return head;
        }

        int i = 0;
        while (temp != null) {
            i++;
            if (i == x - 1) {
                if (temp.next != null) {
                    temp.next = temp.next.next;
                    if (temp.next != null) {
                        temp.next.prev = temp;
                    }
                }
                break;
            }
            temp = temp.next;
        }

        return head;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the following doubly linked list: `1 <-> 2 <-> 3 <-> 4` and position `x = 3`.

| Step | Current Node | Action | State |
|---|---|---|---|
| 1 | 1 | Start at head | temp = 1, i = 0 |
| 2 | 1 | i = 1, not x-1 | temp = 2, i = 1 |
| 3 | 2 | i = 2, not x-1 | temp = 3, i = 2 |
| 4 | 3 | i = 3, x-1 found | Update temp.next to temp.next.next (4.next = null) |
| 5 | 3 | Update temp.next.prev to temp (null.prev = 3) | List becomes 1 <-> 2 <-> 4 |
| 6 | 3 | Exit loop | Return head (1) |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but is more concise and handles edge cases more efficiently. It involves traversing the list to the node at the given position and then performing the deletion operation.

---

## 🔹 Why This Works

This approach works because it leverages the doubly linked list structure to efficiently update the pointers of the surrounding nodes. By traversing the list only once, it ensures optimal performance with a time complexity of O(n).

---

## 🔹 Algorithm

1. If the position is 1 (head node), update the head to the next node and set the `prev` pointer of the new head to null.
2. Otherwise, traverse the list to the node at position `x-1`.
3. Update the `next` pointer of the node at position `x-1` to skip the node at position `x`.
4. If the node at position `x` is not the tail, update the `prev` pointer of the node at position `x+1` to point to the node at position `x-1`.
5. Return the head of the modified list.

---

## 🔹 Code

```java
class Solution {
    public Node delPos(Node head, int x) {
        if (head == null) {
            return null;
        }

        Node temp = head;

        if (x == 1) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return head;
        }

        int i = 0;
        while (temp != null) {
            i++;
            if (i == x - 1) {
                if (temp.next != null) {
                    temp.next = temp.next.next;
                    if (temp.next != null) {
                        temp.next.prev = temp;
                    }
                }
                break;
            }
            temp = temp.next;
        }

        return head;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the following doubly linked list: `1 <-> 2 <-> 3 <-> 4` and position `x = 2`.

| Step | Current Node | Action | State |
|---|---|---|---|
| 1 | 1 | Start at head | temp = 1, i = 0 |
| 2 | 1 | i = 1, x-1 found | Update temp.next to temp.next.next (2.next = 3) |
| 3 | 1 | Update temp.next.prev to temp (3.prev = 1) | List becomes 1 <-> 3 <-> 4 |
| 4 | 1 | Exit loop | Return head (1) |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

1. **Empty List**: The list is empty, and the position is 1.
2. **Single Node List**: The list has only one node, and the position is 1.
3. **Delete Head Node**: The position is 1, and the list has more than one node.
4. **Delete Tail Node**: The position is the length of the list.
5. **Delete Middle Node**: The position is in the middle of the list.

---

# 📚 Key Takeaways

1. **Doubly Linked List Operations**: Understanding how to traverse and update pointers in a doubly linked list is crucial for efficient deletion operations.
2. **Edge Case Handling**: Always consider edge cases such as empty lists, single node lists, and deleting head or tail nodes.
3. **Pointer Manipulation**: Properly updating the `next` and `prev` pointers is essential to maintain the integrity of the doubly linked list structure.

---

# 🚀 Interview Tips

1. **Follow-Up Questions**: Discuss how to handle invalid positions or large lists efficiently.
2. **Common Pitfalls**: Be cautious about null pointer exceptions when updating pointers.
3. **Alternative Approaches**: Consider using recursion or additional data structures if the problem constraints change.

---

# ✅ Conclusion

The optimal approach efficiently handles the deletion of a node in a doubly linked list by leveraging the doubly linked list structure to update pointers in a single traversal. This approach ensures optimal performance with a time complexity of O(n) and a space complexity of O(1). Understanding how to manipulate pointers in a doubly linked list is essential for efficient data structure operations.