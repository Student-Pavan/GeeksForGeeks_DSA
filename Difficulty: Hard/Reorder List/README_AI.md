# 📌 Reorder List

---

# 📝 Problem Statement

You are given the head of a singly linked list. The task is to reorder the list such that the nodes alternate between the first half and the second half of the list.

For example:
- Input: `1 -> 2 -> 3 -> 4 -> 5`
- Output: `1 -> 5 -> 2 -> 4 -> 3`

The solution should modify the list in-place without creating a new list.

---

# 💡 Intuition

The key insight is to:
1. Find the middle of the linked list
2. Split the list into two halves
3. Reverse the second half
4. Merge the two halves alternately

This approach efficiently reorders the list in O(n) time with O(1) space complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

A brute force approach would involve:
1. Converting the linked list to an array
2. Using two pointers to reorder the array
3. Converting the array back to a linked list

This approach is simple but uses O(n) extra space.

---

## 🔹 Algorithm

1. Convert the linked list to an array
2. Initialize two pointers: `left` at start and `right` at end
3. While `left` < `right`:
   - Append `left` node to result
   - Append `right` node to result
   - Move `left` forward and `right` backward
4. Convert the array back to a linked list

---

## 🔹 Code

```java
class Solution {
    public void reorderList(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        // Convert linked list to array
        List<Node> nodes = new ArrayList<>();
        Node current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        // Reorder the array
        int left = 0, right = nodes.size() - 1;
        while (left < right) {
            nodes.get(left).next = nodes.get(right);
            left++;
            if (left < right) {
                nodes.get(right).next = nodes.get(left);
            }
            right--;
        }
        nodes.get(left).next = null;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with input: `1 -> 2 -> 3 -> 4 -> 5`

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 4 | nodes[0].next = nodes[4] | 1 -> 5 -> 2 -> 3 -> 4 |
| 2 | 1 | 3 | nodes[1].next = nodes[3] | 1 -> 5 -> 2 -> 4 -> 3 |
| 3 | 2 | 2 | left >= right | 1 -> 5 -> 2 -> 4 -> 3 |
| 4 | - | - | nodes[2].next = null | 1 -> 5 -> 2 -> 4 -> 3 |

The final reordered list is `1 -> 5 -> 2 -> 4 -> 3`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves:
1. Finding the middle of the linked list using slow and fast pointers
2. Splitting the list into two halves
3. Reversing the second half
4. Merging the two halves alternately

This approach modifies the list in-place with O(1) extra space.

---

## 🔹 Why This Works

The algorithm works by:
- Using slow and fast pointers to find the middle in O(n) time
- Splitting the list into two halves
- Reversing the second half to prepare for merging
- Merging the two halves by alternating nodes

This approach efficiently reorders the list in O(n) time with O(1) space.

---

## 🔹 Algorithm

1. Find the middle of the linked list using slow and fast pointers
2. Split the list into two halves
3. Reverse the second half
4. Merge the two halves alternately

---

## 🔹 Code

```java
class Solution {
    public void reorderList(Node head) {
        if (head == null || head.next == null) {
            return;
        }

        // Find the middle of the linked list
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list into two halves
        Node head1 = head;
        Node head2 = slow.next;
        slow.next = null;

        // Reverse the second half
        head2 = reverse(head2);

        // Merge the two halves alternately
        Node fronttrack = null;
        Node tailtrack = null;

        while (head1 != null && head2 != null) {
            fronttrack = head1.next;
            tailtrack = head2.next;

            head1.next = head2;
            head2.next = fronttrack;

            head1 = fronttrack;
            head2 = tailtrack;
        }
    }

    private Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
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

Let's dry run the algorithm with input: `1 -> 2 -> 3 -> 4 -> 5`

| Step | Slow | Fast | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | fast.next != null && fast.next.next != null | slow = 2, fast = 3 |
| 2 | 2 | 3 | fast.next != null && fast.next.next != null | slow = 3, fast = 5 |
| 3 | 3 | 5 | fast.next == null || fast.next.next == null | exit loop |

After finding the middle, the list is split into two halves:
- First half: `1 -> 2 -> 3`
- Second half: `4 -> 5`

The second half is reversed:
- Reversed second half: `5 -> 4`

The two halves are merged alternately:
- `1 -> 5 -> 2 -> 4 -> 3`

The final reordered list is `1 -> 5 -> 2 -> 4 -> 3`.

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
- Even number of nodes
- Odd number of nodes

---

# 📚 Key Takeaways

- The optimal approach uses O(1) space by modifying the list in-place
- The algorithm efficiently reorders the list in O(n) time
- Understanding the linked list traversal and reversal is crucial
- The two-pointer technique is useful for finding the middle of the list

---

# 🚀 Interview Tips

- Ask about the list size and constraints
- Discuss the trade-offs between time and space complexity
- Consider alternative approaches like using a stack
- Be prepared to explain the reversal and merging process

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently reorders the list in O(n) time with O(1) space complexity. The key insight is to split the list into two halves, reverse the second half, and merge them alternately. This approach is both time and space efficient, making it suitable for large input sizes.