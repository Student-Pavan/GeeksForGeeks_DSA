# Merge two sorted linked lists

---

# 📝 Problem Statement

You are given the heads of two sorted linked lists. Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

**Input:**
- Two sorted linked lists `head1` and `head2`

**Output:**
- A single sorted linked list formed by merging the two input lists

**Constraints:**
- The number of nodes in both lists is between 0 and 50
- -100 ≤ Node.val ≤ 100
- Both lists are sorted in non-decreasing order

---

# 💡 Intuition

The key insight here is that both lists are already sorted. We can leverage this by comparing the nodes from both lists and linking them in the correct order. The optimal approach uses recursion to handle the merging process elegantly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse both lists simultaneously
2. Compare the current nodes of both lists
3. Link the smaller node to the merged list
4. Move to the next node in the list from which we took the smaller node
5. Continue until one of the lists is exhausted
6. Link the remaining nodes of the non-exhausted list to the merged list

---

## 🔹 Algorithm

1. Initialize a dummy node to build the merged list
2. Use a current pointer to track the end of the merged list
3. While both lists have nodes:
   - Compare the current nodes of both lists
   - Link the smaller node to the merged list
   - Move the corresponding list pointer forward
4. Link the remaining nodes of the non-exhausted list to the merged list
5. Return the merged list (excluding the dummy node)

---

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
    Node sortedMerge(Node head1, Node head2) {
        Node dummy = new Node(-1);
        Node current = dummy;

        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                current.next = head1;
                head1 = head1.next;
            } else {
                current.next = head2;
                head2 = head2.next;
            }
            current = current.next;
        }

        if (head1 != null) {
            current.next = head1;
        } else {
            current.next = head2;
        }

        return dummy.next;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with two sample linked lists:

**List 1:** 1 → 3 → 5
**List 2:** 2 → 4 → 6

| Iteration | Current Node List 1 | Current Node List 2 | Action | Merged List |
|---|---|---|---|---|
| 1 | 1 | 2 | Compare, take 1 | 1 |
| 2 | 3 | 2 | Compare, take 2 | 1 → 2 |
| 3 | 3 | 4 | Compare, take 3 | 1 → 2 → 3 |
| 4 | 5 | 4 | Compare, take 4 | 1 → 2 → 3 → 4 |
| 5 | 5 | 6 | Compare, take 5 | 1 → 2 → 3 → 4 → 5 |
| 6 | null | 6 | List 1 exhausted | 1 → 2 → 3 → 4 → 5 → 6 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n + m) where n and m are the lengths of the two lists |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses recursion to merge the two lists. This approach is elegant and takes advantage of the sorted nature of the lists.

1. Base Case: If one of the lists is empty, return the other list
2. Recursive Case:
   - Compare the current nodes of both lists
   - Recursively merge the remaining lists
   - Link the smaller node to the result of the recursive merge

---

## 🔹 Why This Works

This approach works because it leverages the sorted order of the lists. By always comparing the current nodes and recursively merging the remaining lists, we ensure that the merged list is always sorted. The recursion naturally handles the merging process without needing additional data structures.

---

## 🔹 Algorithm

1. If either list is empty, return the other list
2. Compare the current nodes of both lists
3. If the current node of list 1 is smaller or equal:
   - Recursively merge the rest of list 1 with list 2
   - Link the current node of list 1 to the result of the recursive merge
4. Else:
   - Recursively merge list 1 with the rest of list 2
   - Link the current node of list 2 to the result of the recursive merge

---

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
    Node sortedMerge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }

        if (head1.data <= head2.data) {
            head1.next = sortedMerge(head1.next, head2);
            return head1;
        } else {
            head2.next = sortedMerge(head1, head2.next);
            return head2;
        }
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the recursive approach with the same sample lists:

**List 1:** 1 → 3 → 5
**List 2:** 2 → 4 → 6

| Recursive Call | Current Node List 1 | Current Node List 2 | Action | Result |
|---|---|---|---|---|
| 1 | 1 | 2 | Compare, take 1 | 1 → sortedMerge(3 → 5, 2 → 4 → 6) |
| 2 | 3 | 2 | Compare, take 2 | 1 → 2 → sortedMerge(3 → 5, 4 → 6) |
| 3 | 3 | 4 | Compare, take 3 | 1 → 2 → 3 → sortedMerge(5, 4 → 6) |
| 4 | 5 | 4 | Compare, take 4 | 1 → 2 → 3 → 4 → sortedMerge(5, 6) |
| 5 | 5 | 6 | Compare, take 5 | 1 → 2 → 3 → 4 → 5 → sortedMerge(null, 6) |
| 6 | null | 6 | List 1 exhausted | 1 → 2 → 3 → 4 → 5 → 6 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n + m) where n and m are the lengths of the two lists |
| Space Complexity | O(n + m) due to recursion stack |

---

# 🔍 Edge Cases

1. One of the lists is empty
2. Both lists are empty
3. One list has all smaller elements
4. One list has all larger elements
5. Lists of unequal length
6. Lists with duplicate values

---

# 📚 Key Takeaways

1. The brute force approach uses iteration and a dummy node to build the merged list.
2. The optimal approach uses recursion to merge the lists in a more elegant manner.
3. Both approaches have the same time complexity but different space complexities.
4. The recursive approach is more concise but uses more stack space.
5. Understanding the sorted nature of the input lists is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

1. Discuss the trade-offs between the iterative and recursive approaches.
2. Ask if the lists can be modified or if a new list must be created.
3. Consider the constraints and discuss the space complexity implications.
4. Be prepared to explain the base case and recursive case in the recursive approach.
5. Practice drawing the linked lists and the merging process on a whiteboard.

---

# ✅ Conclusion

The optimal recursive approach is preferred for its elegance and concise implementation. However, the iterative approach is more space-efficient and may be preferred in environments with limited stack space. Understanding both approaches provides a comprehensive solution to the problem and demonstrates a strong grasp of linked list manipulation techniques.