# 📌 Palindrome Linked List

---

# 📝 Problem Statement

Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

**Example 1:**

```
Input: head = [1,2,2,1]
Output: true
```

**Example 2:**

```
Input: head = [1,2]
Output: false
```

**Constraints:**

- The number of nodes in the list is in the range [1, 10^5].
- 0 <= Node.val <= 9

---

# 💡 Intuition

The key insight is that a linked list is a palindrome if the first half of the list mirrors the second half. The optimal approach involves finding the middle of the list, reversing the second half, and then comparing the two halves.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list and store all values in an array.
2. Use two pointers to check if the array is a palindrome by comparing elements from the start and end moving towards the center.

## 🔹 Algorithm

1. Initialize an empty array to store node values.
2. Traverse the linked list and store each node's value in the array.
3. Initialize two pointers, one at the start (left) and one at the end (right) of the array.
4. While left is less than right:
   - Compare the values at left and right pointers.
   - If they are not equal, return false.
   - Move left pointer forward and right pointer backward.
5. If the loop completes without mismatches, return true.

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(Node head) {
        // Store all node values in an array
        List<Integer> values = new ArrayList<>();
        Node current = head;
        while (current != null) {
            values.add(current.data);
            current = current.next;
        }

        // Check if the array is a palindrome
        int left = 0;
        int right = values.size() - 1;
        while (left < right) {
            if (!values.get(left).equals(values.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the input `[1, 2, 2, 1]`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 3 | Compare values[0] (1) and values[3] (1) | Equal |
| 2 | 1 | 2 | Compare values[1] (2) and values[2] (2) | Equal |
| 3 | 2 | 1 | Left > Right | Exit loop |

Since all comparisons were equal, the function returns `true`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Find the middle of the linked list.
2. Reverse the second half of the linked list.
3. Compare the first half with the reversed second half.
4. Restore the linked list by reversing the second half again (optional for interview purposes).

## 🔹 Why This Works

By reversing the second half of the linked list, we can compare it directly with the first half. This approach avoids using extra space for storing values, making it more space-efficient.

## 🔹 Algorithm

1. Initialize two pointers, slow and fast, both starting at the head of the list.
2. Move slow one step at a time and fast two steps at a time until fast reaches the end of the list.
3. The slow pointer will be at the middle of the list. Reverse the second half starting from slow.next.
4. Compare the first half (from head to slow) with the reversed second half (from slow.next to end).
5. Restore the linked list by reversing the second half again (optional).

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle of the linked list
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        Node secondHalf = reverseList(slow.next);

        // Compare the first half and the reversed second half
        Node firstHalf = head;
        Node secondHalfCopy = secondHalf;
        boolean result = true;
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                result = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // Restore the linked list (optional)
        slow.next = reverseList(secondHalfCopy);

        return result;
    }

    private Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `[1, 2, 2, 1]`.

| Step | Slow | Fast | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Move slow to 2, fast to 2 | Slow: 2, Fast: 2 |
| 2 | 2 | 1 | Move slow to 2, fast to null | Slow: 2, Fast: null |
| 3 | 2 | null | Exit loop | Slow: 2 |

Now, reverse the second half starting from `slow.next` (which is `2`).

| Step | Prev | Curr | NextTemp | Action | State |
|---|---|---|---|---|---|
| 1 | null | 2 | 1 | curr.next = prev (null), prev = curr (2), curr = nextTemp (1) | Prev: 2, Curr: 1 |
| 2 | 2 | 1 | null | curr.next = prev (2), prev = curr (1), curr = nextTemp (null) | Prev: 1, Curr: null |

The reversed second half is `1 -> 2`.

Now, compare the first half (`1 -> 2`) with the reversed second half (`1 -> 2`).

| Step | FirstHalf | SecondHalf | Action | State |
|---|---|---|---|---|
| 1 | 1 | 1 | Compare 1 and 1 | Equal |
| 2 | 2 | 2 | Compare 2 and 2 | Equal |
| 3 | null | null | Exit loop | All equal |

Since all comparisons were equal, the function returns `true`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Empty list: Should return true.
- Single node: Should return true.
- Even number of nodes: Should correctly compare halves.
- Odd number of nodes: Should correctly compare halves, ignoring the middle node.
- All nodes have the same value: Should return true.
- List is not a palindrome: Should return false.

---

# 📚 Key Takeaways

- The brute force approach is straightforward but uses extra space.
- The optimal approach is more space-efficient and demonstrates linked list manipulation skills.
- Understanding how to find the middle of a linked list and reverse a portion of it is crucial.
- Comparing two halves of a linked list is a common pattern in linked list problems.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask if the linked list can be modified or if it needs to be restored.
- Consider the time and space complexity requirements.
- Practice reversing a linked list and finding the middle of a linked list.

---

# ✅ Conclusion

The optimal approach is preferred for its space efficiency and demonstrates advanced linked list manipulation skills. The key insight is efficiently comparing the two halves of the linked list without using extra space. This problem is a great example of how understanding fundamental operations on linked lists can lead to efficient solutions.