# Reverse a Doubly Linked List

---

# 📝 Problem Statement

Given the head of a doubly linked list, reverse the list and return the new head.

**Constraints:**
- The number of nodes in the list is in the range [0, 1000].
- -1000 ≤ Node.val ≤ 1000

---

# 💡 Intuition

The key insight is that reversing a doubly linked list requires updating both the `next` and `prev` pointers of each node. The optimal approach involves traversing the list while swapping these pointers, resulting in O(n) time complexity and O(1) space complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the list while storing all nodes in an array.
2. Reverse the array.
3. Reconstruct the doubly linked list from the reversed array.

---

## 🔹 Algorithm

1. Initialize an empty array to store nodes.
2. Traverse the list and store each node in the array.
3. Reverse the array.
4. Reconstruct the doubly linked list by setting `next` and `prev` pointers based on the reversed array.
5. Return the new head.

---

## 🔹 Code

```java
class Solution {
    public Node reverse(Node head) {
        if (head == null) return null;

        // Step 1: Store all nodes in an array
        List<Node> nodes = new ArrayList<>();
        Node current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        // Step 2: Reverse the array
        Collections.reverse(nodes);

        // Step 3: Reconstruct the doubly linked list
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (i > 0) {
                node.prev = nodes.get(i - 1);
            } else {
                node.prev = null;
            }
            if (i < nodes.size() - 1) {
                node.next = nodes.get(i + 1);
            } else {
                node.next = null;
            }
        }

        return nodes.get(0);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the following doubly linked list: `1 <-> 2 <-> 3 <-> 4`

| Step | Action | State |
|---|---|---|
| 1 | Store nodes in array | [1, 2, 3, 4] |
| 2 | Reverse array | [4, 3, 2, 1] |
| 3 | Reconstruct list | 4 <-> 3 <-> 2 <-> 1 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Initialize three pointers: `prev`, `curr`, and `next`.
2. Traverse the list while swapping `next` and `prev` pointers for each node.
3. Update `prev` and `curr` pointers to move forward in the list.
4. Finally, set the `prev` pointer of the new head to `null`.

---

## 🔹 Why This Works

This approach efficiently reverses the list in a single pass by maintaining and updating the `prev` and `next` pointers for each node. The algorithm ensures that all nodes are visited exactly once, resulting in optimal time complexity.

---

## 🔹 Algorithm

1. Initialize `prev` to `null` and `curr` to `head`.
2. While `curr` is not `null`:
   - Store `curr.next` in `next`.
   - Set `curr.next` to `prev`.
   - Set `curr.prev` to `next`.
   - Move `prev` to `curr`.
   - Move `curr` to `next`.
3. Set the `prev` pointer of the new head to `null`.
4. Return `prev` as the new head.

---

## 🔹 Code

```java
class Solution {
    public Node reverse(Node head) {
        Node prev = null;
        Node next = null;
        Node curr = head;

        while (curr != null) {
            next = curr.next;

            curr.next = prev;
            curr.prev = next;

            prev = curr;
            curr = next;
        }

        if (prev != null) {
            prev.prev = null;
        }

        return prev;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following doubly linked list: `1 <-> 2 <-> 3 <-> 4`

| Step | Prev | Curr | Next | Action | State |
|---|---|---|---|---|---|
| 1 | null | 1 | null | Initialize | 1 <-> 2 <-> 3 <-> 4 |
| 2 | null | 1 | 2 | Store next, swap pointers | 1 <-> null, 2 <-> 1 |
| 3 | 1 | 2 | 3 | Store next, swap pointers | 2 <-> 1, 3 <-> 2 |
| 4 | 2 | 3 | 4 | Store next, swap pointers | 3 <-> 2, 4 <-> 3 |
| 5 | 3 | 4 | null | Store next, swap pointers | 4 <-> 3 |
| 6 | 4 | null | null | Set prev.prev to null | 4 <-> 3 <-> 2 <-> 1 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty list
- Single node list
- List with duplicate values
- Large list (1000 nodes)

---

# 📚 Key Takeaways

- Reversing a doubly linked list requires updating both `next` and `prev` pointers.
- The optimal approach achieves O(n) time and O(1) space complexity.
- The brute force approach uses O(n) space to store nodes, which is less efficient.

---

# 🚀 Interview Tips

- Discuss the difference between singly and doubly linked list reversal.
- Mention that the optimal approach is preferred for large lists.
- Be ready to explain the importance of updating both pointers in a doubly linked list.

---

# ✅ Conclusion

The optimal approach is preferred for its O(n) time complexity and O(1) space complexity, making it efficient for large lists. The key insight is that reversing a doubly linked list requires careful pointer manipulation to ensure both `next` and `prev` pointers are correctly updated.