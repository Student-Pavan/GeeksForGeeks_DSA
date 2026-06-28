# Reverse a linked list

---

# 📝 Problem Statement

Given the head of a singly linked list, reverse the list, and return the reversed list.

**Objective**: Reverse the given linked list.

**Input**:
- `head`: The head node of the linked list.

**Output**:
- The head node of the reversed linked list.

**Constraints**:
- The number of nodes in the list is in the range [0, 5000].
- -5000 ≤ Node.val ≤ 5000

---

# 💡 Intuition

The problem requires reversing a linked list. The key insight is that we need to reverse the direction of the links between nodes. Instead of each node pointing to the next node, it should point to the previous node.

The optimal approach involves iterating through the list while reversing the links between nodes. We maintain three pointers: `prev`, `curr`, and `next`. The `prev` pointer keeps track of the previous node, `curr` is the current node being processed, and `next` is used to temporarily store the next node before reversing the link.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a stack to reverse the linked list. We traverse the linked list and push each node onto a stack. Then, we pop each node from the stack and rebuild the linked list in reverse order.

---

## 🔹 Algorithm

1. Initialize an empty stack.
2. Traverse the linked list and push each node onto the stack.
3. Initialize a new linked list with a dummy node.
4. Pop each node from the stack and append it to the new linked list.
5. Return the head of the new linked list.

---

## 🔹 Code

```java
import java.util.Stack;

class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Solution {
    Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Stack<Node> stack = new Stack<>();
        Node current = head;

        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        Node dummy = new Node(-1);
        Node newHead = dummy;

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            newHead.next = node;
            newHead = newHead.next;
        }

        newHead.next = null;

        return dummy.next;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the following linked list: `1 -> 2 -> 3 -> 4 -> 5`.

| Step | Action | Stack | New List |
|---|---|---|---|
| 1 | Push 1 | [1] | - |
| 2 | Push 2 | [1, 2] | - |
| 3 | Push 3 | [1, 2, 3] | - |
| 4 | Push 4 | [1, 2, 3, 4] | - |
| 5 | Push 5 | [1, 2, 3, 4, 5] | - |
| 6 | Pop 5 | [1, 2, 3, 4] | 5 |
| 7 | Pop 4 | [1, 2, 3] | 5 -> 4 |
| 8 | Pop 3 | [1, 2] | 5 -> 4 -> 3 |
| 9 | Pop 2 | [1] | 5 -> 4 -> 3 -> 2 |
| 10 | Pop 1 | [] | 5 -> 4 -> 3 -> 2 -> 1 |

The reversed linked list is `5 -> 4 -> 3 -> 2 -> 1`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves reversing the links between nodes in a single pass through the linked list. We use three pointers: `prev`, `curr`, and `next`. The `prev` pointer starts as `null` and will eventually become the new head of the reversed list. The `curr` pointer starts at the head of the list and moves through each node. The `next` pointer temporarily stores the next node before reversing the link.

---

## 🔹 Why This Works

This approach works because it reverses the links between nodes in place, without using additional data structures like a stack. By iterating through the list and reversing the links, we effectively reverse the order of the nodes. The `prev` pointer keeps track of the new head of the reversed list, while the `curr` pointer moves through the list, and the `next` pointer ensures we don't lose the reference to the next node.

---

## 🔹 Algorithm

1. Initialize three pointers: `prev` as `null`, `curr` as `head`, and `next` as `null`.
2. Traverse the linked list:
   - Store the next node in `next`.
   - Reverse the link of the current node to point to `prev`.
   - Move `prev` to `curr`.
   - Move `curr` to `next`.
3. After the loop, `prev` will be the new head of the reversed list.
4. Return `prev`.

---

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
    Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the following linked list: `1 -> 2 -> 3 -> 4 -> 5`.

| Step | Prev | Curr | Next | Action | State |
|---|---|---|---|---|---|
| 1 | null | 1 | null | next = curr.next (2) | prev = null, curr = 1, next = 2 |
| 2 | null | 1 | 2 | curr.next = prev (null) | prev = null, curr = 1, next = 2 |
| 3 | null | 1 | 2 | prev = curr (1) | prev = 1, curr = 1, next = 2 |
| 4 | null | 1 | 2 | curr = next (2) | prev = 1, curr = 2, next = 2 |
| 5 | 1 | 2 | 2 | next = curr.next (3) | prev = 1, curr = 2, next = 3 |
| 6 | 1 | 2 | 3 | curr.next = prev (1) | prev = 1, curr = 2, next = 3 |
| 7 | 1 | 2 | 3 | prev = curr (2) | prev = 2, curr = 2, next = 3 |
| 8 | 1 | 2 | 3 | curr = next (3) | prev = 2, curr = 3, next = 3 |
| 9 | 2 | 3 | 3 | next = curr.next (4) | prev = 2, curr = 3, next = 4 |
| 10 | 2 | 3 | 4 | curr.next = prev (2) | prev = 2, curr = 3, next = 4 |
| 11 | 2 | 3 | 4 | prev = curr (3) | prev = 3, curr = 3, next = 4 |
| 12 | 2 | 3 | 4 | curr = next (4) | prev = 3, curr = 4, next = 4 |
| 13 | 3 | 4 | 4 | next = curr.next (5) | prev = 3, curr = 4, next = 5 |
| 14 | 3 | 4 | 5 | curr.next = prev (3) | prev = 3, curr = 4, next = 5 |
| 15 | 3 | 4 | 5 | prev = curr (4) | prev = 4, curr = 4, next = 5 |
| 16 | 3 | 4 | 5 | curr = next (5) | prev = 4, curr = 5, next = 5 |
| 17 | 4 | 5 | 5 | next = curr.next (null) | prev = 4, curr = 5, next = null |
| 18 | 4 | 5 | null | curr.next = prev (4) | prev = 4, curr = 5, next = null |
| 19 | 4 | 5 | null | prev = curr (5) | prev = 5, curr = 5, next = null |
| 20 | 4 | 5 | null | curr = next (null) | prev = 5, curr = null, next = null |

The reversed linked list is `5 -> 4 -> 3 -> 2 -> 1`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty list**: The list is empty, so the function should return `null`.
- **Single node**: The list contains only one node, so the function should return the same node.
- **Large list**: The list contains a large number of nodes, so the function should handle it efficiently.

---

# 📚 Key Takeaways

- The brute force approach uses a stack to reverse the linked list, which requires additional space.
- The optimal approach reverses the links between nodes in place, using constant space.
- The optimal approach is more efficient in terms of space complexity and is preferred for large lists.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask about the constraints and expected input sizes to determine the best approach.
- Be prepared to explain the time and space complexity of each approach.

---

# ✅ Conclusion

The optimal approach is preferred for reversing a linked list because it reverses the links between nodes in place, using constant space. The brute force approach, while simple, uses additional space and is less efficient for large lists. Understanding the optimal approach is crucial for solving linked list problems efficiently.