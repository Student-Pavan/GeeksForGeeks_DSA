# Remove Loop in Linked List

---

# 📝 Problem Statement

Given a linked list, remove the loop if it exists. A loop is formed when a node's `next` pointer points to a previous node in the list.

**Input:**
- A linked list with a potential loop.

**Output:**
- The same linked list with the loop removed.

**Constraints:**
- The linked list may or may not have a loop.
- The solution should not modify the list if there is no loop.

---

# 💡 Intuition

The problem requires detecting and removing a loop in a linked list. The key insight is to use Floyd's Cycle-Finding Algorithm (tortoise and hare) to detect the loop. Once detected, we can find the start of the loop and then remove it by setting the `next` pointer of the last node in the loop to `null`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. **Detect the Loop:**
   - Use two pointers, `slow` and `fast`, where `slow` moves one step at a time and `fast` moves two steps at a time.
   - If they meet, a loop exists.

2. **Find the Start of the Loop:**
   - Reset `slow` to the head of the list.
   - Move both `slow` and `fast` one step at a time until they meet again. The meeting point is the start of the loop.

3. **Remove the Loop:**
   - Traverse the loop to find the last node.
   - Set the `next` pointer of the last node to `null`.

## 🔹 Algorithm

1. Initialize `slow` and `fast` pointers to the head of the list.
2. Move `slow` by one step and `fast` by two steps until they meet or `fast` reaches the end.
3. If they meet, reset `slow` to the head.
4. Move both `slow` and `fast` one step at a time until they meet again. The meeting point is the start of the loop.
5. Traverse the loop to find the last node.
6. Set the `next` pointer of the last node to `null`.

## 🔹 Code

```java
class Solution {
    public static void removeLoop(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node slow = head;
        Node fast = head;

        // Detect loop
        boolean loopExists = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                loopExists = true;
                break;
            }
        }

        if (!loopExists) {
            return;
        }

        // Find the start of the loop
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Find the last node of the loop
        Node lastNode = slow;
        while (lastNode.next != slow) {
            lastNode = lastNode.next;
        }

        // Remove the loop
        lastNode.next = null;
    }
}
```

## 🔹 Dry Run

Let's consider a linked list with a loop: `1 -> 2 -> 3 -> 4 -> 5 -> 3` (where `5` points back to `3`).

| Step | Slow | Fast | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Initialize pointers | slow = 1, fast = 1 |
| 2 | 2 | 3 | Move slow by 1, fast by 2 | slow = 2, fast = 3 |
| 3 | 3 | 5 | Move slow by 1, fast by 2 | slow = 3, fast = 5 |
| 4 | 4 | 3 | Move slow by 1, fast by 2 | slow = 4, fast = 3 |
| 5 | 5 | 5 | Move slow by 1, fast by 2 | slow = 5, fast = 5 (meet) |
| 6 | 1 | 5 | Reset slow to head, move both by 1 | slow = 1, fast = 5 |
| 7 | 2 | 3 | Move both by 1 | slow = 2, fast = 3 |
| 8 | 3 | 5 | Move both by 1 | slow = 3, fast = 5 |
| 9 | 4 | 3 | Move both by 1 | slow = 4, fast = 3 (meet) |
| 10 | 5 | 5 | Move both by 1 | slow = 5, fast = 5 |
| 11 | 3 | 3 | Move both by 1 | slow = 3, fast = 3 (start of loop) |
| 12 | 5 | 5 | Find last node of loop | lastNode = 5 |
| 13 | - | - | Remove loop | lastNode.next = null |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses Floyd's Cycle-Finding Algorithm to detect and remove the loop in a single traversal. This approach is more efficient as it avoids the need for a second traversal to find the start of the loop.

## 🔹 Why This Works

Floyd's algorithm efficiently detects the loop by using two pointers moving at different speeds. Once the loop is detected, the start of the loop can be found by resetting one pointer to the head and moving both pointers at the same speed until they meet. The last node of the loop is then found and the loop is removed by setting its `next` pointer to `null`.

## 🔹 Algorithm

1. Initialize `slow` and `fast` pointers to the head of the list.
2. Move `slow` by one step and `fast` by two steps until they meet or `fast` reaches the end.
3. If they meet, reset `slow` to the head.
4. Move both `slow` and `fast` one step at a time until they meet again. The meeting point is the start of the loop.
5. Traverse the loop to find the last node.
6. Set the `next` pointer of the last node to `null`.

## 🔹 Code

```java
class Solution {
    public static void removeLoop(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        Node slow = head;
        Node fast = head;

        // Detect loop
        boolean loopExists = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                loopExists = true;
                break;
            }
        }

        if (!loopExists) {
            return;
        }

        // Find the start of the loop
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Find the last node of the loop
        Node lastNode = slow;
        while (lastNode.next != slow) {
            lastNode = lastNode.next;
        }

        // Remove the loop
        lastNode.next = null;
    }
}
```

## 🔹 Detailed Dry Run

Let's consider the same linked list as before: `1 -> 2 -> 3 -> 4 -> 5 -> 3`.

| Step | Slow | Fast | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Initialize pointers | slow = 1, fast = 1 |
| 2 | 2 | 3 | Move slow by 1, fast by 2 | slow = 2, fast = 3 |
| 3 | 3 | 5 | Move slow by 1, fast by 2 | slow = 3, fast = 5 |
| 4 | 4 | 3 | Move slow by 1, fast by 2 | slow = 4, fast = 3 |
| 5 | 5 | 5 | Move slow by 1, fast by 2 | slow = 5, fast = 5 (meet) |
| 6 | 1 | 5 | Reset slow to head, move both by 1 | slow = 1, fast = 5 |
| 7 | 2 | 3 | Move both by 1 | slow = 2, fast = 3 |
| 8 | 3 | 5 | Move both by 1 | slow = 3, fast = 5 |
| 9 | 4 | 3 | Move both by 1 | slow = 4, fast = 3 (meet) |
| 10 | 5 | 5 | Move both by 1 | slow = 5, fast = 5 |
| 11 | 3 | 3 | Move both by 1 | slow = 3, fast = 3 (start of loop) |
| 12 | 5 | 5 | Find last node of loop | lastNode = 5 |
| 13 | - | - | Remove loop | lastNode.next = null |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

1. **Empty List:** The list has no nodes.
2. **Single Node:** The list has only one node.
3. **No Loop:** The list is linear with no loops.
4. **Loop at Head:** The loop starts at the head node.
5. **Large List:** The list is very large with a loop towards the end.

---

# 📚 Key Takeaways

1. **Floyd's Algorithm:** Efficiently detects loops in linked lists.
2. **Loop Removal:** Once the loop is detected, the start of the loop can be found and removed.
3. **Single Traversal:** The optimal approach minimizes the number of traversals.

---

# 🚀 Interview Tips

1. **Clarify Assumptions:** Ensure you understand if the list can be empty or if it always has a loop.
2. **Edge Cases:** Consider edge cases such as an empty list or a list with a single node.
3. **Efficiency:** Aim for an O(n) time complexity solution using Floyd's algorithm.

---

# ✅ Conclusion

The optimal approach efficiently detects and removes loops in a linked list using Floyd's Cycle-Finding Algorithm. This approach ensures minimal time complexity and optimal performance. Understanding the algorithm and its application is crucial for solving linked list problems in interviews.