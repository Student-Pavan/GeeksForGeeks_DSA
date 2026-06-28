# Remove Duplicates from a Sorted Linked List

---

# 📝 Problem Statement

Given a sorted linked list, remove duplicates from it.

**Objective**: Modify the linked list in-place to remove all duplicate nodes, keeping only distinct values.

**Input**:
- A sorted linked list with `Node` objects containing `data` and `next` pointer.

**Output**:
- The modified linked list with duplicates removed.

**Constraints**:
- The linked list is sorted in non-decreasing order.
- You must modify the linked list in-place without using extra space for another linked list.

---

# 💡 Intuition

The problem requires removing duplicates from a sorted linked list. Since the list is sorted, all duplicate values will be adjacent. We can leverage this property to efficiently remove duplicates in a single pass through the list.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through the linked list and comparing each node with its next node. If a duplicate is found, we remove the duplicate node by adjusting the pointers.

---

## 🔹 Algorithm

1. Initialize two pointers, `prev` and `Next`, both pointing to the head of the linked list.
2. Iterate through the linked list using the `Next` pointer.
3. If the data of `prev` and `Next` are the same, skip the `Next` node by setting `prev.next` to `Next.next`.
4. If the data of `prev` and `Next` are different, move the `prev` pointer to the `Next` node.
5. Move the `Next` pointer to the next node.
6. Repeat steps 3-5 until the end of the linked list is reached.
7. Return the head of the modified linked list.

---

## 🔹 Code

```java
class Solution {
    Node removeDuplicates(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node prev = head;
        Node Next = head.next;

        while (Next != null) {
            if (prev.data != Next.data) {
                prev.next = Next;
                prev = Next;
            }
            Next = Next.next;
        }
        prev.next = null;
        return head;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 1 -> 2 -> 3 -> 3`.

| Step | Prev | Next | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Skip duplicate | `1 -> 2 -> 3 -> 3` |
| 2 | 1 | 2 | Move prev | `1 -> 2 -> 3 -> 3` |
| 3 | 2 | 3 | Move prev | `1 -> 2 -> 3 -> 3` |
| 4 | 2 | 3 | Skip duplicate | `1 -> 2 -> 3` |
| 5 | 2 | null | Terminate | `1 -> 2 -> 3` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but is more concise and efficient. We iterate through the linked list and remove duplicates in a single pass.

---

## 🔹 Why This Works

The optimal approach works because the linked list is sorted. This allows us to check for duplicates by simply comparing adjacent nodes. By adjusting the pointers in-place, we ensure that the list remains sorted and duplicates are removed efficiently.

---

## 🔹 Algorithm

1. Initialize a pointer `current` to the head of the linked list.
2. Iterate through the linked list using the `current` pointer.
3. If the data of `current` and `current.next` are the same, skip the `current.next` node by setting `current.next` to `current.next.next`.
4. If the data of `current` and `current.next` are different, move the `current` pointer to the `current.next` node.
5. Repeat steps 3-4 until the end of the linked list is reached.
6. Return the head of the modified linked list.

---

## 🔹 Code

```java
class Solution {
    Node removeDuplicates(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node current = head;

        while (current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 1 -> 2 -> 3 -> 3`.

| Step | Current | Current.next | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Skip duplicate | `1 -> 2 -> 3 -> 3` |
| 2 | 1 | 2 | Move current | `1 -> 2 -> 3 -> 3` |
| 3 | 2 | 3 | Move current | `1 -> 2 -> 3 -> 3` |
| 4 | 2 | 3 | Skip duplicate | `1 -> 2 -> 3` |
| 5 | 2 | null | Terminate | `1 -> 2 -> 3` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty List**: The list is empty, so no duplicates to remove.
- **Single Element**: The list contains only one element, so no duplicates to remove.
- **All Duplicates**: The list contains all duplicate elements, so the result should be a single node.
- **No Duplicates**: The list contains no duplicate elements, so the result should be the same as the input.

---

# 📚 Key Takeaways

- The optimal approach is efficient and leverages the sorted property of the linked list.
- In-place modification ensures minimal space complexity.
- Understanding the sorted property is crucial for optimizing the solution.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - How would you handle an unsorted linked list?
  - Can you solve this problem using recursion?
- **Common Pitfalls**:
  - Forgetting to handle edge cases.
  - Not adjusting pointers correctly when removing duplicates.
- **Alternative Approaches**:
  - Using a hash set to track seen values, but this requires extra space.
- **Optimization Discussions**:
  - The optimal approach is already optimal for this problem.

---

# ✅ Conclusion

The optimal approach efficiently removes duplicates from a sorted linked list by leveraging the sorted property and in-place modification. This solution is both time and space efficient, making it suitable for interview scenarios.