# Linked List Group Reverse

---

# 📝 Problem Statement

Given a linked list and a number `k`, reverse every `k` nodes in the list. If the remaining nodes are less than `k`, leave them as they are.

**Example:**
```
Input: 1->2->3->4->5->NULL, k=2
Output: 2->1->4->3->5->NULL
```

**Constraints:**
- The number of nodes in the list is in the range [0, 5000].
- -5000 ≤ Node.val ≤ 5000
- 1 ≤ k ≤ 5000

---

# 💡 Intuition

The problem requires reversing nodes in groups of size `k`. The key insight is to process the linked list in segments, reversing each segment individually while maintaining the connections between segments. This approach avoids the need for additional space by performing in-place reversals.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list and store all node values in an array.
2. Reverse the elements in the array in groups of size `k`.
3. Traverse the linked list again and update the node values from the reversed array.

## 🔹 Algorithm

1. Initialize an empty array to store node values.
2. Traverse the linked list and store each node's value in the array.
3. For each group of size `k` in the array, reverse the elements.
4. Traverse the linked list again and update each node's value from the reversed array.

## 🔹 Code

```java
class Solution {
    public Node reverseKGroup(Node head, int k) {
        if (head == null || k <= 1) return head;

        ArrayList<Integer> list = new ArrayList<>();

        Node temp = head;
        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }

        // Reverse every group (including the last incomplete group)
        for (int i = 0; i < list.size(); i += k) {
            int left = i;
            int right = Math.min(i + k - 1, list.size() - 1);

            while (left < right) {
                int t = list.get(left);
                list.set(left, list.get(right));
                list.set(right, t);
                left++;
                right--;
            }
        }

        temp = head;
        int idx = 0;

        while (temp != null) {
            temp.data = list.get(idx++);
            temp = temp.next;
        }

        return head;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the example input: `1->2->3->4->5->NULL`, `k=2`.

| Step | Action | State |
|---|---|---|
| 1 | Initialize array list | `list = []` |
| 2 | Traverse and store values | `list = [1, 2, 3, 4, 5]` |
| 3 | Reverse first group (i=0) | `list = [2, 1, 3, 4, 5]` |
| 4 | Reverse second group (i=2) | `list = [2, 1, 4, 3, 5]` |
| 5 | Update linked list | `1->2->3->4->5` becomes `2->1->4->3->5` |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Reverse the linked list in groups of size `k` directly without using additional space.
2. Use a dummy node to simplify the handling of the head of the list.
3. For each group, reverse the nodes and reconnect the previous and next groups.

## 🔹 Why This Works

This approach reverses the nodes in-place, which is more memory efficient than the brute force approach. By reversing each group directly, we avoid the need for additional storage, thus optimizing space complexity.

## 🔹 Algorithm

1. Initialize a dummy node to simplify the reversal process.
2. Use a pointer to keep track of the previous group's tail.
3. For each group of size `k`:
   - Reverse the nodes in the group.
   - Reconnect the previous group's tail to the new head of the reversed group.
   - Update the previous group's tail to the new tail of the reversed group.
4. Return the head of the modified list.

## 🔹 Code

```java
class Solution {
    public Node reverseKGroup(Node head, int k) {
        if (head == null || k <= 1) return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;

        while (prev.next != null) {
            Node last = prev;
            for (int i = 0; i < k && last.next != null; i++) {
                last = last.next;
            }

            if (last == prev) break;

            Node curr = prev.next;
            Node next = null;
            Node newHead = last.next;

            while (curr != last) {
                next = curr.next;
                curr.next = newHead;
                newHead = curr;
                curr = next;
            }

            prev.next = newHead;
            prev = last;
        }

        return dummy.next;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the example input: `1->2->3->4->5->NULL`, `k=2`.

| Step | Action | State |
|---|---|---|
| 1 | Initialize dummy node | `dummy -> 1->2->3->4->5->NULL` |
| 2 | Set prev to dummy | `prev = dummy` |
| 3 | Find last node of first group | `last = 2` |
| 4 | Reverse first group | `1->2` becomes `2->1` |
| 5 | Reconnect groups | `dummy -> 2->1->3->4->5->NULL` |
| 6 | Update prev | `prev = 1` |
| 7 | Find last node of second group | `last = 4` |
| 8 | Reverse second group | `3->4` becomes `4->3` |
| 9 | Reconnect groups | `dummy -> 2->1->4->3->5->NULL` |
| 10 | Update prev | `prev = 3` |
| 11 | No more groups to reverse | Exit loop |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty list: `head = null`
- Single node list: `head = 1->NULL`
- List length equals `k`: `head = 1->2->3->NULL`, `k=3`
- List length is a multiple of `k`: `head = 1->2->3->4->5->6->NULL`, `k=2`
- List length is not a multiple of `k`: `head = 1->2->3->4->5->NULL`, `k=2`
- `k=1`: No reversal needed

---

# 📚 Key Takeaways

- **In-place reversal** is more memory efficient than using additional storage.
- **Dummy node** simplifies the handling of the head of the list.
- **Group reversal** can be done by reversing each group individually and reconnecting the groups.

---

# 🚀 Interview Tips

- Ask clarifying questions about the constraints and edge cases.
- Discuss the trade-offs between the brute force and optimal approaches.
- Consider the time and space complexity implications of each approach.
- Practice reversing linked lists in-place to build intuition.

---

# ✅ Conclusion

The optimal approach is preferred because it reverses the linked list in-place, reducing the space complexity from O(n) to O(1). The key insight is to reverse each group of nodes directly and reconnect the groups to maintain the overall list structure. This approach is both time and space efficient, making it suitable for large input sizes.