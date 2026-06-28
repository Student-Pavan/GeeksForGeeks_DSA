# Rotate a Linked List

---

# 📝 Problem Statement

Given a singly linked list, rotate the list to the right by `k` places, where `k` is non-negative.

**Constraints:**
- The length of the linked list is between 0 and 500.
- `k` can be greater than the length of the linked list.

**Example:**
```
Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
```

---

# 💡 Intuition

The key insight is that rotating a linked list by `k` places is equivalent to moving the last `k` nodes to the front. However, if `k` is larger than the length of the list, we can use modulo operation to simplify the problem.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Calculate the length of the linked list.
2. If `k` is 0 or the list is empty, return the head as it is.
3. If `k` is greater than the length of the list, use modulo operation to find the effective rotation count.
4. Traverse the list to find the new tail (the node before the new head).
5. Break the list into two parts: the new head and the remaining list.
6. Connect the tail of the original list to the original head.
7. Return the new head.

## 🔹 Algorithm

1. Initialize `len` to 1 and `tail` to `head`.
2. Traverse the list to find the length and the tail.
3. Calculate `k = k % len`.
4. If `k` is 0, return `head`.
5. Traverse the list again to find the new tail (the node at position `len - k - 1`).
6. Set `newHead` to `newTail.next`.
7. Set `newTail.next` to `null`.
8. Set `tail.next` to `head`.
9. Return `newHead`.

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
    public Node rotate(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int len = 1;
        Node tail = head;

        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        k = k % len;

        if (k == 0) {
            return head;
        }

        Node newTail = head;
        for (int i = 1; i < len - k; i++) {
            newTail = newTail.next;
        }

        Node newHead = newTail.next;
        newTail.next = null;
        tail.next = head;

        return newHead;
    }
}
```

## 🔹 Dry Run

Let's dry run the code with the example input: `1->2->3->4->5->NULL` and `k = 2`.

| Step | Current Node | Action | State |
|---|---|---|---|
| 1 | head (1) | Initialize `len` to 1, `tail` to head | len = 1, tail = 1 |
| 2 | 1 | Move `tail` to next node | tail = 2, len = 2 |
| 3 | 2 | Move `tail` to next node | tail = 3, len = 3 |
| 4 | 3 | Move `tail` to next node | tail = 4, len = 4 |
| 5 | 4 | Move `tail` to next node | tail = 5, len = 5 |
| 6 | 5 | `tail.next` is null, exit loop | len = 5, tail = 5 |
| 7 | - | Calculate `k = k % len` | k = 2 % 5 = 2 |
| 8 | head (1) | Initialize `newTail` to head | newTail = 1 |
| 9 | 1 | Move `newTail` to next node | newTail = 2 |
| 10 | 2 | Move `newTail` to next node | newTail = 3 |
| 11 | 3 | Exit loop (i = 3, len - k = 3) | newTail = 3 |
| 12 | 3 | Set `newHead` to `newTail.next` | newHead = 4 |
| 13 | 3 | Set `newTail.next` to null | 3->null |
| 14 | 5 | Set `tail.next` to head | 5->1->2->3->null |
| 15 | - | Return `newHead` | 4->5->1->2->3->null |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but avoids traversing the list twice. Instead, it uses a single traversal to find the length and the new tail.

## 🔹 Why This Works

By traversing the list once to find the length and the tail, we can then calculate the effective rotation count `k` using modulo operation. This allows us to find the new tail in a single traversal, making the algorithm more efficient.

## 🔹 Algorithm

1. Initialize `len` to 1 and `tail` to `head`.
2. Traverse the list to find the length and the tail.
3. Calculate `k = k % len`.
4. If `k` is 0, return `head`.
5. Traverse the list again to find the new tail (the node at position `len - k - 1`).
6. Set `newHead` to `newTail.next`.
7. Set `newTail.next` to `null`.
8. Set `tail.next` to `head`.
9. Return `newHead`.

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
    public Node rotate(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int len = 1;
        Node tail = head;

        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        k = k % len;

        if (k == 0) {
            return head;
        }

        Node newTail = head;
        for (int i = 1; i < len - k; i++) {
            newTail = newTail.next;
        }

        Node newHead = newTail.next;
        newTail.next = null;
        tail.next = head;

        return newHead;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code with the example input: `1->2->3->4->5->NULL` and `k = 2`.

| Step | Current Node | Action | State |
|---|---|---|---|
| 1 | head (1) | Initialize `len` to 1, `tail` to head | len = 1, tail = 1 |
| 2 | 1 | Move `tail` to next node | tail = 2, len = 2 |
| 3 | 2 | Move `tail` to next node | tail = 3, len = 3 |
| 4 | 3 | Move `tail` to next node | tail = 4, len = 4 |
| 5 | 4 | Move `tail` to next node | tail = 5, len = 5 |
| 6 | 5 | `tail.next` is null, exit loop | len = 5, tail = 5 |
| 7 | - | Calculate `k = k % len` | k = 2 % 5 = 2 |
| 8 | head (1) | Initialize `newTail` to head | newTail = 1 |
| 9 | 1 | Move `newTail` to next node | newTail = 2 |
| 10 | 2 | Move `newTail` to next node | newTail = 3 |
| 11 | 3 | Exit loop (i = 3, len - k = 3) | newTail = 3 |
| 12 | 3 | Set `newHead` to `newTail.next` | newHead = 4 |
| 13 | 3 | Set `newTail.next` to null | 3->null |
| 14 | 5 | Set `tail.next` to head | 5->1->2->3->null |
| 15 | - | Return `newHead` | 4->5->1->2->3->null |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty List:** If the list is empty, return `null`.
- **Single Node:** If the list has only one node, return the head as it is.
- **k is 0:** If `k` is 0, return the head as it is.
- **k is Greater than Length:** If `k` is greater than the length of the list, use modulo operation to find the effective rotation count.
- **k is Equal to Length:** If `k` is equal to the length of the list, the list remains unchanged.

---

# 📚 Key Takeaways

- Rotating a linked list by `k` places involves moving the last `k` nodes to the front.
- Using modulo operation simplifies the problem when `k` is larger than the length of the list.
- The optimal approach involves traversing the list once to find the length and the tail, and then traversing the list again to find the new tail.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you handle a doubly linked list?
  - Can you solve this problem in a single traversal?
- **Common Pitfalls:**
  - Forgetting to handle the case when `k` is greater than the length of the list.
  - Not updating the `next` pointer of the new tail to `null`.
- **Alternative Approaches:**
  - Using a queue to store the nodes and then rotating the queue.
- **Optimization Discussions:**
  - The optimal approach is efficient as it involves traversing the list only twice.

---

# ✅ Conclusion

The optimal approach to rotating a linked list by `k` places involves traversing the list once to find the length and the tail, and then traversing the list again to find the new tail. This approach ensures that the list is rotated efficiently with a time complexity of O(n) and a space complexity of O(1).