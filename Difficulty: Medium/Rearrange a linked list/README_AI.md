# 📌 Rearrange a linked list

---

# 📝 Problem Statement

Given a singly linked list, the task is to rearrange it such that all even positioned nodes are placed before all odd positioned nodes. The positions are determined by their order in the original list, with the first node being position 1, the second node being position 2, and so on.

**Objective**: Rearrange the linked list so that all even-positioned nodes come before all odd-positioned nodes.

**Input**:
- A singly linked list with `head` node.

**Output**:
- The modified linked list with even-positioned nodes followed by odd-positioned nodes.

**Constraints**:
- The number of nodes in the linked list is between 1 and 10^4.
- The value of each node is between 1 and 10^5.

---

# 💡 Intuition

The key insight here is to separate the linked list into two parts: one for even-positioned nodes and one for odd-positioned nodes. We can then link the end of the even-positioned nodes to the beginning of the odd-positioned nodes. This approach efficiently rearranges the list in a single pass through the original list.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list to collect all even-positioned nodes and odd-positioned nodes separately.
2. Create two separate linked lists: one for even-positioned nodes and one for odd-positioned nodes.
3. Link the end of the even-positioned list to the beginning of the odd-positioned list.

## 🔹 Algorithm

1. Initialize two dummy nodes: `evenHead` and `oddHead`.
2. Initialize two pointers: `even` and `odd` to track the end of the even and odd lists respectively.
3. Traverse the original linked list:
   - For each even-positioned node, append it to the even list and move the `even` pointer.
   - For each odd-positioned node, append it to the odd list and move the `odd` pointer.
4. Link the end of the even list to the beginning of the odd list.
5. Return the head of the even list.

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class Solution {
    public void rearrangeEvenOdd(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node evenHead = new Node(-1);
        Node oddHead = new Node(-1);
        Node even = evenHead;
        Node odd = oddHead;
        Node current = head;
        int position = 1;

        while (current != null) {
            if (position % 2 == 0) {
                even.next = current;
                even = even.next;
            } else {
                odd.next = current;
                odd = odd.next;
            }
            current = current.next;
            position++;
        }

        even.next = oddHead.next;
        odd.next = null;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 2 -> 3 -> 4 -> 5`.

| Step | Current Node | Position | Even List | Odd List | Action |
|------|--------------|----------|-----------|----------|--------|
| 1    | 1            | 1        | -         | 1        | Append 1 to odd list |
| 2    | 2            | 2        | 2         | 1        | Append 2 to even list |
| 3    | 3            | 3        | 2         | 1 -> 3   | Append 3 to odd list |
| 4    | 4            | 4        | 2 -> 4    | 1 -> 3   | Append 4 to even list |
| 5    | 5            | 5        | 2 -> 4    | 1 -> 3 -> 5 | Append 5 to odd list |

After traversal:
- Even list: `2 -> 4`
- Odd list: `1 -> 3 -> 5`

Link the end of the even list to the beginning of the odd list:
- `2 -> 4 -> 1 -> 3 -> 5`

Final linked list: `2 -> 4 -> 1 -> 3 -> 5`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Traverse the linked list and separate the nodes into even and odd positions.
2. Link the last node of the even-positioned list to the first node of the odd-positioned list.
3. Ensure the last node of the odd-positioned list points to `null`.

## 🔹 Why This Works

This approach efficiently rearranges the linked list in a single pass, maintaining the relative order of even and odd-positioned nodes. It uses constant extra space, making it optimal in terms of space complexity.

## 🔹 Algorithm

1. Initialize two pointers: `odd` and `even` to track the end of the odd and even lists respectively.
2. Traverse the linked list:
   - For each even-positioned node, append it to the even list and move the `even` pointer.
   - For each odd-positioned node, append it to the odd list and move the `odd` pointer.
3. Link the end of the even list to the beginning of the odd list.
4. Ensure the last node of the odd list points to `null`.

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class Solution {
    public void rearrangeEvenOdd(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node odd = head;
        Node even = head.next;
        Node evenStart = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenStart;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 2 -> 3 -> 4 -> 5`.

| Step | Odd Pointer | Even Pointer | Action | State |
|------|-------------|--------------|--------|-------|
| 1    | 1           | 2            | Link odd.next to even.next (3) | 1 -> 3, 2 -> 4 |
| 2    | 3           | 4            | Link even.next to odd.next (5) | 3 -> 5, 4 -> null |
| 3    | 5           | null         | Terminate loop | 1 -> 3 -> 5, 2 -> 4 |

Link the end of the even list to the beginning of the odd list:
- `2 -> 4 -> 1 -> 3 -> 5`

Final linked list: `2 -> 4 -> 1 -> 3 -> 5`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty List**: The list is empty, so no rearrangement is needed.
- **Single Node**: The list contains only one node, so no rearrangement is needed.
- **Two Nodes**: The list contains two nodes, so the even-positioned node comes first.
- **All Even or Odd Positions**: The list contains nodes with all even or all odd positions, so the list remains unchanged.
- **Large List**: The list contains a large number of nodes, ensuring the algorithm handles it efficiently.

---

# 📚 Key Takeaways

- **Separation of Nodes**: The key insight is to separate the nodes into even and odd positions and then link them appropriately.
- **Efficiency**: The optimal approach efficiently rearranges the list in a single pass, making it optimal in terms of time and space complexity.
- **Pointer Manipulation**: Proper manipulation of pointers is crucial to ensure the correct rearrangement of the linked list.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem without using extra space?
  - How would you handle a doubly linked list?
- **Common Pitfalls**:
  - Forgetting to handle the edge case of an empty list or a single node.
  - Incorrectly linking the end of the even list to the beginning of the odd list.
- **Alternative Approaches**:
  - Using a stack to reverse the order of nodes, but this would increase the space complexity.
  - Using recursion, but this would also increase the space complexity due to the call stack.

---

# ✅ Conclusion

The optimal approach efficiently rearranges the linked list by separating the nodes into even and odd positions and then linking them appropriately. This approach ensures that the list is rearranged in a single pass, making it optimal in terms of time and space complexity. The key insight is to properly manipulate the pointers to ensure the correct rearrangement of the linked list.

---

# 🎨 Formatting Rules

- Use proper markdown headings and separators.
- Use syntax-highlighted code blocks for Java code.
- Use markdown tables for dry runs and complexity analysis.
- Ensure GitHub readability with clean spacing and formatting.