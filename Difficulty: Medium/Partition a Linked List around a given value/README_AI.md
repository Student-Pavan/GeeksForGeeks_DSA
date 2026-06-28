# 📌 Partition a Linked List around a given value

---

# 📝 Problem Statement

Given a linked list and a value `x`, partition it such that all nodes less than `x` come before nodes greater than or equal to `x`. Preserve the original relative order of the nodes in each of the two partitions.

**Constraints:**
- The solution should be in-place (no additional memory for another linked list).
- Maintain the original order of elements in each partition.

---

# 💡 Intuition

The key insight is that we can create three separate linked lists:
1. One for elements less than `x`
2. One for elements equal to `x`
3. One for elements greater than `x`

Then we can combine these lists in order: left → mid → right.

This approach maintains the relative order of elements while partitioning the list in a single pass.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list and collect all elements less than `x` in an array.
2. Collect all elements equal to `x` in another array.
3. Collect all elements greater than `x` in a third array.
4. Reconstruct the linked list by combining these arrays in order.

---

## 🔹 Algorithm

1. Initialize three empty arrays: `left`, `mid`, `right`.
2. Traverse the linked list:
   - For each node, append its value to the appropriate array based on comparison with `x`.
3. Reconstruct the linked list by:
   - First appending all values from `left`
   - Then all values from `mid`
   - Finally all values from `right`

---

## 🔹 Code

```java
class Solution {
    public Node partition(Node head, int x) {
        if (head == null) return null;

        // Arrays to store nodes
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> mid = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        Node current = head;

        // Separate nodes into three lists
        while (current != null) {
            if (current.data < x) {
                left.add(current.data);
            } else if (current.data == x) {
                mid.add(current.data);
            } else {
                right.add(current.data);
            }
            current = current.next;
        }

        // Rebuild the linked list
        current = head;
        for (int num : left) {
            current.data = num;
            current = current.next;
        }
        for (int num : mid) {
            current.data = num;
            current = current.next;
        }
        for (int num : right) {
            current.data = num;
            current = current.next;
        }

        return head;
    }
}
```

---

## 🔹 Dry Run

Let's dry run with input: `1 -> 4 -> 3 -> 2 -> 5 -> 2` and `x = 3`

| Step | Current Node | Left | Mid | Right | Action |
|------|--------------|------|-----|-------|--------|
| 1    | 1            | [1]  | []  | []    | 1 < 3 → left |
| 2    | 4            | [1]  | []  | [4]   | 4 >= 3 → right |
| 3    | 3            | [1]  | [3] | [4]   | 3 == 3 → mid |
| 4    | 2            | [1,2]| [3] | [4]   | 2 < 3 → left |
| 5    | 5            | [1,2]| [3] | [4,5] | 5 >= 3 → right |
| 6    | 2            | [1,2,2]| [3] | [4,5] | 2 < 3 → left |

After rebuilding:
- Left: 1 → 2 → 2
- Mid: 3
- Right: 4 → 5

Final list: `1 -> 2 -> 2 -> 3 -> 4 -> 5`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) (three passes through the list) |
| Space Complexity | O(n) (for storing node values) |

---

# ⚡ Optimal Approach

## 🔹 Approach

We can partition the linked list in a single pass by maintaining three separate linked lists:
1. `left` for nodes with values less than `x`
2. `mid` for nodes with values equal to `x`
3. `right` for nodes with values greater than `x`

Then we connect these lists in order: `left` → `mid` → `right`.

---

## 🔹 Why This Works

This approach maintains the relative order of elements while partitioning the list in O(n) time with O(1) extra space (excluding the dummy nodes).

---

## 🔹 Algorithm

1. Create three dummy nodes: `left`, `mid`, and `right`.
2. Initialize pointers to track the tails of these lists.
3. Traverse the original list:
   - For each node, append it to the appropriate list based on its value.
4. Connect the lists in order: `left` → `mid` → `right`.
5. Return the head of the `left` list (or `mid` if `left` is empty).

---

## 🔹 Code

```java
class Solution {
    public Node partition(Node head, int x) {
        // Create dummy nodes for three partitions
        Node left = new Node(0);
        Node mid = new Node(0);
        Node right = new Node(0);

        // Pointers to track the tails of each partition
        Node leftTail = left;
        Node midTail = mid;
        Node rightTail = right;

        Node curr = head;

        // Partition the list
        while (curr != null) {
            Node next = curr.next;

            if (curr.data < x) {
                leftTail.next = curr;
                leftTail = curr;
            } else if (curr.data == x) {
                midTail.next = curr;
                midTail = curr;
            } else {
                rightTail.next = curr;
                rightTail = curr;
            }

            curr = next;
        }

        // Connect the partitions
        rightTail.next = null;      // End of list
        midTail.next = right.next;  // mid -> right
        leftTail.next = mid.next;   // left -> mid

        // Determine the new head
        if (left.next != null) {
            return left.next;
        }
        if (mid.next != null) {
            return mid.next;
        }
        return right.next;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run with input: `1 -> 4 -> 3 -> 2 -> 5 -> 2` and `x = 3`

| Step | Current Node | Left | Mid | Right | Action |
|------|--------------|------|-----|-------|--------|
| 1    | 1            | 1    |     |       | 1 < 3 → left |
| 2    | 4            | 1    |     | 4     | 4 >= 3 → right |
| 3    | 3            | 1    | 3   | 4     | 3 == 3 → mid |
| 4    | 2            | 1→2  | 3   | 4     | 2 < 3 → left |
| 5    | 5            | 1→2  | 3   | 4→5   | 5 >= 3 → right |
| 6    | 2            | 1→2→2| 3   | 4→5   | 2 < 3 → left |

After connecting:
- Left: 1 → 2 → 2
- Mid: 3
- Right: 4 → 5

Final list: `1 -> 2 -> 2 -> 3 -> 4 -> 5`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) (single pass through the list) |
| Space Complexity | O(1) (only dummy nodes used) |

---

# 🔍 Edge Cases

- Empty list
- Single element list
- All elements less than `x`
- All elements greater than `x`
- All elements equal to `x`
- Large list (to test efficiency)
- Negative numbers
- Duplicate values

---

# 📚 Key Takeaways

- The optimal approach maintains relative order while partitioning in O(n) time.
- Using dummy nodes simplifies list manipulation.
- The brute force approach is easier to understand but less efficient.
- This problem demonstrates the power of partitioning techniques in linked lists.

---

# 🚀 Interview Tips

- Ask clarifying questions about maintaining order and edge cases.
- Consider follow-up questions about in-place modification and stability.
- Be prepared to discuss alternative approaches like using additional data structures.
- Practice drawing the list transformations to visualize the solution.

---

# ✅ Conclusion

The optimal approach is preferred because it partitions the list in a single pass while maintaining relative order. The key insight is using dummy nodes to simplify list manipulation and connecting the partitions in order. This solution demonstrates efficient linked list partitioning techniques that are valuable for interview preparation.