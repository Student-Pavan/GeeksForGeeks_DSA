# Delete Nodes with Greater on Right

---

# 📝 Problem Statement

Given a singly linked list, the task is to remove all the nodes which have a node with a greater value on the right side. The entire list must be traversed only once.

**Example:**

```
Input: 12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3
Output: 15 -> 11 -> 6 -> 3
```

**Constraints:**
- The number of nodes in the given list will not exceed 100.
- Each node's value will be in the range [-100, 100].

---

# 💡 Intuition

The key insight is that we need to traverse the list from right to left, keeping track of the maximum value encountered so far. If a node's value is less than the current maximum, it should be deleted. This approach ensures that we only traverse the list once, making it efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the linked list from left to right.
2. For each node, traverse the remaining list to find if there exists a node with a greater value.
3. If such a node is found, remove the current node; otherwise, keep it.

## 🔹 Algorithm

1. Initialize a pointer `current` to the head of the list.
2. While `current` is not null:
   - Initialize a pointer `runner` to `current.next`.
   - Initialize a flag `found` to false.
   - While `runner` is not null:
     - If `runner.data` > `current.data`, set `found` to true and break.
     - Move `runner` to the next node.
   - If `found` is true, remove `current` from the list.
   - Move `current` to the next node.

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
    Node compute(Node head) {
        Node current = head;

        while (current != null) {
            Node runner = current.next;
            boolean found = false;

            while (runner != null) {
                if (runner.data > current.data) {
                    found = true;
                    break;
                }
                runner = runner.next;
            }

            if (found) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the input list: 12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3.

| Step | Current Node | Runner Node | Found | Action | List State |
|------|--------------|-------------|-------|--------|------------|
| 1    | 12           | 15          | true  | Remove 12 | 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3 |
| 2    | 15           | 10          | false | Keep 15 | 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3 |
| 3    | 10           | 11          | true  | Remove 10 | 15 -> 11 -> 5 -> 6 -> 2 -> 3 |
| 4    | 11           | 5           | false | Keep 11 | 15 -> 11 -> 5 -> 6 -> 2 -> 3 |
| 5    | 5            | 6           | true  | Remove 5 | 15 -> 11 -> 6 -> 2 -> 3 |
| 6    | 6            | 2           | false | Keep 6 | 15 -> 11 -> 6 -> 2 -> 3 |
| 7    | 2            | 3           | true  | Remove 2 | 15 -> 11 -> 6 -> 3 |
| 8    | 3            | null        | false | Keep 3 | 15 -> 11 -> 6 -> 3 |

Final list: 15 -> 11 -> 6 -> 3

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Reverse the linked list.
2. Traverse the reversed list, keeping track of the maximum value encountered so far.
3. If the current node's value is less than the maximum value, remove the node.
4. Reverse the list back to its original order.

## 🔹 Why This Works

By reversing the list, we can traverse it from right to left, which allows us to keep track of the maximum value encountered so far. This approach ensures that we only traverse the list twice, making it more efficient than the brute force approach.

## 🔹 Algorithm

1. Reverse the linked list.
2. Initialize a pointer `current` to the head of the reversed list.
3. Initialize a variable `max` to the value of the first node.
4. While `current.next` is not null:
   - If `current.next.data` < `max`, remove `current.next`.
   - Else, move `current` to the next node and update `max` to `current.data`.
5. Reverse the list back to its original order.

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
    Node reverse(Node head) {
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

    Node compute(Node head) {
        head = reverse(head);

        Node curr = head;
        int max = curr.data;

        while (curr != null && curr.next != null) {
            if (curr.next.data < max) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
                max = curr.data;
            }
        }

        return reverse(head);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input list: 12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3.

### Step 1: Reverse the List

Original list: 12 -> 15 -> 10 -> 11 -> 5 -> 6 -> 2 -> 3

Reversed list: 3 -> 2 -> 6 -> 5 -> 11 -> 10 -> 15 -> 12

### Step 2: Traverse the Reversed List

| Step | Current Node | Max | Action | List State |
|------|--------------|-----|--------|------------|
| 1    | 3            | 3   | Keep 3 | 3 -> 2 -> 6 -> 5 -> 11 -> 10 -> 15 -> 12 |
| 2    | 2            | 3   | Remove 2 | 3 -> 6 -> 5 -> 11 -> 10 -> 15 -> 12 |
| 3    | 6            | 6   | Keep 6 | 3 -> 6 -> 5 -> 11 -> 10 -> 15 -> 12 |
| 4    | 5            | 6   | Remove 5 | 3 -> 6 -> 11 -> 10 -> 15 -> 12 |
| 5    | 11           | 11  | Keep 11 | 3 -> 6 -> 11 -> 10 -> 15 -> 12 |
| 6    | 10           | 11  | Remove 10 | 3 -> 6 -> 11 -> 15 -> 12 |
| 7    | 15           | 15  | Keep 15 | 3 -> 6 -> 11 -> 15 -> 12 |
| 8    | 12           | 15  | Remove 12 | 3 -> 6 -> 11 -> 15 |

### Step 3: Reverse the List Back

Reversed list: 15 -> 11 -> 6 -> 3

Final list: 15 -> 11 -> 6 -> 3

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty List:** The function should handle an empty list gracefully.
- **Single Node:** If the list contains only one node, it should remain unchanged.
- **All Nodes in Descending Order:** The list should remain unchanged.
- **All Nodes in Ascending Order:** Only the last node should remain.
- **Duplicate Values:** The function should handle duplicate values correctly.

---

# 📚 Key Takeaways

- **Pattern Recognition:** The problem can be solved efficiently by reversing the list and traversing it from right to left.
- **Optimization Strategy:** By reversing the list, we can keep track of the maximum value encountered so far, which allows us to remove nodes in a single pass.
- **Interview Insight:** Understanding how to traverse a linked list in reverse can be a valuable skill in solving linked list problems.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you solve the problem without reversing the list?
  - What if you cannot modify the list? (Use a stack to simulate the reverse traversal.)
- **Common Pitfalls:**
  - Forgetting to update the `max` value when moving to the next node.
  - Not handling edge cases such as an empty list or a single node.
- **Alternative Approaches:**
  - Using a stack to simulate the reverse traversal without actually reversing the list.

---

# ✅ Conclusion

The optimal approach of reversing the list and traversing it from right to left is more efficient than the brute force approach. It ensures that we only traverse the list twice, making it an O(n) time complexity solution. This approach is particularly useful for solving linked list problems where traversal order is crucial.

---

# 🎨 Formatting Rules

- **Headings:** Use markdown headings to organize the content.
- **Code Blocks:** Use syntax-highlighted code blocks for Java code.
- **Tables:** Use markdown tables for dry runs and complexity analysis.
- **Lists:** Use bullet points for key takeaways and interview tips.

---

# 🚨 Final Validation Rules

- **Markdown:** Ensure the markdown is not escaped and renders directly on GitHub.
- **Code:** Verify that the Java code is complete and executable.
- **Dry Runs:** Ensure the dry runs are logically correct and help a beginner understand the algorithm.
- **Complexity Analysis:** Provide accurate time and space complexity analysis.