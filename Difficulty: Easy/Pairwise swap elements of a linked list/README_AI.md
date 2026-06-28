# Pairwise swap elements of a linked list

---

# 📝 Problem Statement

Given a singly linked list, write a function to swap elements pairwise.

For example, if the linked list is `1->2->3->4->5->6->7`, then the function should change it to `2->1->4->3->6->5->7`.

**Constraints:**
- The number of nodes in the list is in the range `[0, 100]`.
- `0 <= Node.val <= 100`

---

# 💡 Intuition

The key insight is that we need to swap adjacent nodes in pairs. This requires careful pointer manipulation to ensure the links between nodes are properly updated after each swap. The optimal approach involves iterating through the list while maintaining references to the current pair of nodes and the previous node, which allows us to update the links correctly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list.
2. For each pair of nodes, swap their values.
3. Move to the next pair and repeat until the end of the list is reached.

---

## 🔹 Algorithm

1. Initialize a pointer `current` to the head of the list.
2. While `current` and `current.next` are not null:
   a. Swap the values of `current` and `current.next`.
   b. Move `current` to `current.next.next`.
3. Return the head of the modified list.

---

## 🔹 Code

```java
class Solution {
    public Node pairwiseSwap(Node head) {
        Node current = head;

        while (current != null && current.next != null) {
            int temp = current.data;
            current.data = current.next.data;
            current.next.data = temp;

            current = current.next.next;
        }

        return head;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input list `1->2->3->4->5->6->7`.

| Step | Current Node | Next Node | Action | State |
|---|---|---|---|---|
| 1 | 1 | 2 | Swap 1 and 2 | 2->1->3->4->5->6->7 |
| 2 | 3 | 4 | Swap 3 and 4 | 2->1->4->3->5->6->7 |
| 3 | 5 | 6 | Swap 5 and 6 | 2->1->4->3->6->5->7 |
| 4 | 7 | null | Terminate | 2->1->4->3->6->5->7 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Initialize pointers to keep track of the current pair of nodes and the previous node.
2. For each pair of nodes, update the links to swap their positions.
3. Move the pointers to the next pair and repeat until the end of the list is reached.

---

## 🔹 Why This Works

This approach efficiently swaps nodes by adjusting their links directly, rather than swapping values. This method ensures that the list remains connected correctly after each swap, and it only requires a single pass through the list.

---

## 🔹 Algorithm

1. Initialize `first` to the head of the list, `second` to `first.next`, and `prev` to null.
2. While `first` and `second` are not null:
   a. Update the links to swap `first` and `second`.
   b. Move `prev` to `first`, `first` to `third` (the node after `second`), and `second` to `first.next`.
3. Return the head of the modified list.

---

## 🔹 Code

```java
class Solution {
    public Node pairwiseSwap(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node first = head;
        Node second = head.next;
        Node prev = null;

        while (first != null && second != null) {
            Node third = second.next;

            second.next = first;
            first.next = third;

            if (prev != null) {
                prev.next = second;
            } else {
                head = second;
            }

            prev = first;
            first = third;
            if (third != null) {
                second = third.next;
            } else {
                second = null;
            }
        }

        return head;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input list `1->2->3->4->5->6->7`.

| Step | First | Second | Third | Prev | Action | State |
|---|---|---|---|---|---|---|
| 1 | 1 | 2 | 3 | null | Swap 1 and 2 | 2->1->3->4->5->6->7 |
| 2 | 3 | 4 | 5 | 1 | Swap 3 and 4 | 2->1->4->3->5->6->7 |
| 3 | 5 | 6 | 7 | 3 | Swap 5 and 6 | 2->1->4->3->6->5->7 |
| 4 | 7 | null | null | 5 | Terminate | 2->1->4->3->6->5->7 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

1. Empty list: The function should return null.
2. Single node: The function should return the node as is.
3. Even number of nodes: The function should swap all pairs.
4. Odd number of nodes: The last node should remain as is.

---

# 📚 Key Takeaways

- The brute force approach swaps values directly, which is simple but not optimal for large lists.
- The optimal approach swaps nodes by adjusting their links, which is more efficient and works in-place.
- Understanding pointer manipulation is crucial for solving linked list problems.

---

# 🚀 Interview Tips

- Ask clarifying questions about the input constraints and expected output.
- Consider edge cases and test the solution with various inputs.
- Discuss the time and space complexity of the solution.
- Be prepared to explain the thought process behind the optimal approach.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently swaps nodes by adjusting their links, resulting in a time complexity of O(n) and a space complexity of O(1). The key insight is to maintain pointers to the current pair of nodes and the previous node, ensuring the list remains connected correctly after each swap.