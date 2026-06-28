# Kth from End of Linked List

---

# 📝 Problem Statement

Given a linked list and a positive integer `k`, find the kth node from the end of the linked list. If the list has fewer than `k` nodes, return `-1`.

**Objective**: Find the kth node from the end of a singly linked list.

**Input**:
- A singly linked list
- An integer `k`

**Output**:
- The value of the kth node from the end
- `-1` if the list has fewer than `k` nodes

**Constraints**:
- The number of nodes in the linked list is in the range `[1, 1000]`.
- `1 <= k <= 1000`
- Each node's value is a unique integer in the range `[1, 1000]`.

---

# 💡 Intuition

The problem requires finding the kth node from the end of a linked list. The key insight is that we can traverse the list to determine its length and then calculate the position of the kth node from the beginning. This approach leverages the fact that the distance from the start of the list to the kth node from the end is equal to the total length of the list minus `k` plus one.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Calculate the length of the linked list**: Traverse the entire list to count the number of nodes.
2. **Check for validity**: If the length of the list is less than `k`, return `-1`.
3. **Find the kth node from the end**: Traverse the list again from the head to the node at position `(length - k + 1)`.

## 🔹 Algorithm

1. Initialize a counter `n` to 0.
2. Traverse the linked list to count the number of nodes and store the length in `n`.
3. If `k` is greater than `n`, return `-1`.
4. Traverse the list again from the head to the node at position `(n - k + 1)`.
5. Return the value of the node at this position.

## 🔹 Code

```java
class Solution {
    int getKthFromLast(Node head, int k) {
        if (head == null) {
            return -1;
        }

        int n = 0;
        Node len = head;
        while (len != null) {
            n++;
            len = len.next;
        }

        if (k > n) {
            return -1;
        }

        Node curr = head;
        int track = 1;
        while (curr != null && track < (n - k + 1)) {
            curr = curr.next;
            track++;
        }

        return curr.data;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 2 -> 3 -> 4 -> 5` and `k = 2`.

| Step | Current Node | Action | State |
|---|---|---|---|
| 1 | 1 | Initialize `n = 0`, `len = head` | `n = 0`, `len = 1` |
| 2 | 1 | Increment `n` to 1, move `len` to next node | `n = 1`, `len = 2` |
| 3 | 2 | Increment `n` to 2, move `len` to next node | `n = 2`, `len = 3` |
| 4 | 3 | Increment `n` to 3, move `len` to next node | `n = 3`, `len = 4` |
| 5 | 4 | Increment `n` to 4, move `len` to next node | `n = 4`, `len = 5` |
| 6 | 5 | Increment `n` to 5, move `len` to next node | `n = 5`, `len = null` |
| 7 | null | Exit loop | `n = 5` |
| 8 | - | Check if `k > n` | `2 <= 5` |
| 9 | 1 | Initialize `curr = head`, `track = 1` | `curr = 1`, `track = 1` |
| 10 | 1 | Move `curr` to next node, increment `track` to 2 | `curr = 2`, `track = 2` |
| 11 | 2 | Move `curr` to next node, increment `track` to 3 | `curr = 3`, `track = 3` |
| 12 | 3 | Exit loop | `curr = 3`, `track = 3` |
| 13 | 3 | Return `curr.data` | Return `3` |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses two pointers, `fast` and `slow`. The `fast` pointer is moved `k` steps ahead of the `slow` pointer. Then, both pointers are moved one step at a time until the `fast` pointer reaches the end of the list. At this point, the `slow` pointer will be at the kth node from the end.

## 🔹 Why This Works

This approach works because the distance between the `fast` and `slow` pointers remains constant as they traverse the list. By moving the `fast` pointer `k` steps ahead initially, we ensure that when the `fast` pointer reaches the end, the `slow` pointer will be at the kth node from the end.

## 🔹 Algorithm

1. Initialize two pointers, `fast` and `slow`, both pointing to the head of the list.
2. Move the `fast` pointer `k` steps ahead.
3. If the `fast` pointer becomes `null` during this process, return `-1`.
4. Move both pointers one step at a time until the `fast` pointer reaches the end of the list.
5. Return the value of the node pointed to by the `slow` pointer.

## 🔹 Code

```java
class Solution {
    int getKthFromLast(Node head, int k) {
        if (head == null) {
            return -1;
        }

        Node fast = head;
        Node slow = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return -1;
            }
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow.data;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 2 -> 3 -> 4 -> 5` and `k = 2`.

| Step | Fast | Slow | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Initialize `fast` and `slow` to head | `fast = 1`, `slow = 1` |
| 2 | 1 | 1 | Move `fast` 1 step ahead | `fast = 2`, `slow = 1` |
| 3 | 2 | 1 | Move `fast` 1 step ahead | `fast = 3`, `slow = 1` |
| 4 | 3 | 1 | Exit loop | `fast = 3`, `slow = 1` |
| 5 | 3 | 1 | Move both pointers one step | `fast = 4`, `slow = 2` |
| 6 | 4 | 2 | Move both pointers one step | `fast = 5`, `slow = 3` |
| 7 | 5 | 3 | Move both pointers one step | `fast = null`, `slow = 4` |
| 8 | null | 4 | Exit loop | `fast = null`, `slow = 4` |
| 9 | - | - | Return `slow.data` | Return `4` |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty list**: The function should return `-1`.
- **Single node list**: If `k` is 1, return the node's value; otherwise, return `-1`.
- **k equals list length**: Return the first node's value.
- **k greater than list length**: Return `-1`.

---

# 📚 Key Takeaways

- **Two-pointer technique**: This technique is efficient for problems involving linked lists where we need to find a node at a specific position relative to the end.
- **Single traversal**: The optimal approach reduces the time complexity to O(n) with a single traversal, making it more efficient than the brute force approach.
- **Edge cases**: Always consider edge cases such as empty lists, single node lists, and invalid `k` values.

---

# 🚀 Interview Tips

- **Clarify requirements**: Ensure you understand the problem requirements, especially edge cases.
- **Optimization**: The two-pointer technique is a common optimization for linked list problems.
- **Follow-up questions**: Consider asking if the list is guaranteed to have at least `k` nodes or if the list can be empty.

---

# ✅ Conclusion

The optimal approach using two pointers is more efficient and elegant than the brute force method. It leverages the two-pointer technique to find the kth node from the end in a single traversal, reducing the time complexity to O(n). This approach is particularly useful in interview settings where efficiency and clarity are crucial.