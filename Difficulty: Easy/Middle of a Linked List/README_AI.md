# 📌 Middle of a Linked List

---

# 📝 Problem Statement

Given the head of a singly linked list, return the middle node of the linked list. If the number of nodes is even, return the second middle node.

**Constraints:**
- The number of nodes in the list is in the range `[1, 100]`.
- `1 <= Node.val <= 100`

---

# 💡 Intuition

The key insight here is to find the middle node without knowing the length of the linked list beforehand. The optimal approach uses two pointers: a slow pointer that moves one node at a time and a fast pointer that moves two nodes at a time. When the fast pointer reaches the end of the list, the slow pointer will be at the middle.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list to count the number of nodes.
2. Calculate the middle index.
3. Traverse the list again to reach the middle node.

## 🔹 Algorithm

1. Initialize a counter to 0.
2. Traverse the linked list while incrementing the counter.
3. Calculate the middle index as `counter / 2`.
4. Reset the traversal and move to the middle index.
5. Return the node at the middle index.

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
    int getMiddle(Node head) {
        if (head == null) {
            return -1;
        }

        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        int middleIndex = count / 2;
        current = head;

        for (int i = 0; i < middleIndex; i++) {
            current = current.next;
        }

        return current.data;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with the following linked list: `1 -> 2 -> 3 -> 4 -> 5`.

| Step | Current Node | Count | Action |
|------|---------------|-------|--------|
| 1    | 1             | 1     | Increment count, move to next node |
| 2    | 2             | 2     | Increment count, move to next node |
| 3    | 3             | 3     | Increment count, move to next node |
| 4    | 4             | 4     | Increment count, move to next node |
| 5    | 5             | 5     | Increment count, move to next node |
| 6    | null          | 5     | Exit loop |

Calculate middle index: `5 / 2 = 2`.

| Step | Current Node | Index | Action |
|------|---------------|-------|--------|
| 1    | 1             | 0     | Move to next node |
| 2    | 2             | 1     | Move to next node |
| 3    | 3             | 2     | Exit loop |

Return `3`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use two pointers: a slow pointer that moves one node at a time and a fast pointer that moves two nodes at a time. When the fast pointer reaches the end of the list, the slow pointer will be at the middle.

## 🔹 Why This Works

The fast pointer moves twice as fast as the slow pointer. When the fast pointer reaches the end, the slow pointer will have traversed half the distance, landing at the middle node. This approach efficiently finds the middle node in a single traversal.

## 🔹 Algorithm

1. Initialize two pointers, `slow` and `fast`, both pointing to the head of the list.
2. Move the `slow` pointer one node at a time and the `fast` pointer two nodes at a time.
3. When the `fast` pointer reaches the end of the list, the `slow` pointer will be at the middle node.
4. Return the value of the `slow` pointer.

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
    int getMiddle(Node head) {
        if (head == null) {
            return -1;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.data;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the following linked list: `1 -> 2 -> 3 -> 4 -> 5`.

| Step | Slow Pointer | Fast Pointer | Action |
|------|---------------|--------------|--------|
| 1    | 1             | 1            | Move slow to next node, fast to next next node |
| 2    | 2             | 3            | Move slow to next node, fast to next next node |
| 3    | 3             | 5            | Move slow to next node, fast to next next node |
| 4    | 4             | null         | Exit loop |

Return `4`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single node list:** The middle node is the only node.
- **Even number of nodes:** The second middle node is returned.
- **Empty list:** Return `-1` or handle appropriately.

---

# 📚 Key Takeaways

- The optimal approach uses two pointers to efficiently find the middle node in a single traversal.
- The brute force approach involves two traversals, making it less efficient.
- Understanding the two-pointer technique is crucial for solving linked list problems efficiently.

---

# 🚀 Interview Tips

- **Follow-up questions:** What if you need to find the middle node in a circular linked list?
- **Common pitfalls:** Forgetting to handle the case where the fast pointer's next node is null.
- **Alternative approaches:** Using a hash map to store nodes and then accessing the middle index, but this is less efficient.

---

# ✅ Conclusion

The optimal approach using two pointers is the most efficient way to find the middle node of a linked list. It ensures a single traversal with constant space complexity, making it ideal for interview scenarios.