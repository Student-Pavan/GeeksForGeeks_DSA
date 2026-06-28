# Merge Sort for Linked List

---

# 📝 Problem Statement

Implement the merge sort algorithm for a linked list. Merge sort is a divide-and-conquer algorithm that recursively splits the list into halves, sorts each half, and then merges them back together.

**Objective**: Sort the given linked list in ascending order using the merge sort algorithm.

**Input**:
- A linked list with `head` node.

**Output**:
- The head of the sorted linked list.

**Constraints**:
- The number of nodes in the list is in the range `[0, 5 * 10^4]`.
- `-10^5 <= Node.val <= 10^5`

---

# 💡 Intuition

The key insight behind merge sort is that it efficiently sorts data by dividing it into smaller subproblems, solving them recursively, and then combining the solutions. For linked lists, merge sort is particularly effective because it operates in O(n log n) time complexity, which is optimal for comparison-based sorting algorithms, and it can be implemented with O(log n) space complexity due to the recursive nature of the algorithm.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using an array to store the values of the linked list nodes, sorting the array using a standard sorting algorithm (like quicksort or mergesort), and then reconstructing the linked list from the sorted array.

## 🔹 Algorithm

1. Traverse the linked list and store all node values in an array.
2. Sort the array using a standard sorting algorithm.
3. Traverse the array and update the values of the linked list nodes in sorted order.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Collections;

class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}

class Solution {
    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        ArrayList<Integer> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        Collections.sort(list);

        current = head;
        for (int num : list) {
            current.data = num;
            current = current.next;
        }

        return head;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with the linked list: `4 -> 2 -> 1 -> 3`.

1. **Traverse the linked list and store values in an array**:
   - Array: [4, 2, 1, 3]

2. **Sort the array**:
   - Sorted Array: [1, 2, 3, 4]

3. **Update the linked list with sorted values**:
   - Linked List: `1 -> 2 -> 3 -> 4`

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n log n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves implementing the merge sort algorithm directly on the linked list without using additional space for an array. This approach divides the linked list into two halves, recursively sorts each half, and then merges the sorted halves back together.

## 🔹 Why This Works

This approach efficiently sorts the linked list in O(n log n) time complexity and O(log n) space complexity due to the recursive nature of the algorithm. It avoids the need for additional space by directly manipulating the linked list nodes.

## 🔹 Algorithm

1. **Split the linked list into two halves**:
   - Find the middle of the linked list using the slow and fast pointer technique.
   - Split the linked list into two halves at the middle node.

2. **Recursively sort each half**:
   - Recursively apply the merge sort algorithm to the left and right halves of the linked list.

3. **Merge the sorted halves**:
   - Merge the two sorted halves back together by comparing the nodes and linking them in ascending order.

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int key) {
        data = key;
        next = null;
    }
}

class Solution {
    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = findMiddle(head);
        Node left = head;
        Node right = middle.next;
        middle.next = null;

        left = mergeSort(left);
        right = mergeSort(right);

        return mergeList(left, right);
    }

    private Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private Node mergeList(Node left, Node right) {
        if (left == null || right == null) {
            return left == null ? right : left;
        }

        if (left.data <= right.data) {
            left.next = mergeList(left.next, right);
            return left;
        } else {
            right.next = mergeList(left, right.next);
            return right;
        }
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the linked list: `4 -> 2 -> 1 -> 3`.

1. **Find the middle of the linked list**:
   - Slow pointer: `4 -> 2`
   - Fast pointer: `1 -> 3`
   - Middle node: `2`

2. **Split the linked list into two halves**:
   - Left half: `4 -> 2`
   - Right half: `1 -> 3`

3. **Recursively sort the left half**:
   - Find the middle of the left half: `4`
   - Split the left half into `4` and `2`
   - Sort the left half: `2 -> 4`

4. **Recursively sort the right half**:
   - Find the middle of the right half: `1`
   - Split the right half into `1` and `3`
   - Sort the right half: `1 -> 3`

5. **Merge the sorted halves**:
   - Merge `2 -> 4` and `1 -> 3`:
     - Compare `2` and `1`: `1` is smaller, so link `1` to the result of merging `2 -> 4` and `3`.
     - Compare `2` and `3`: `2` is smaller, so link `2` to the result of merging `4` and `3`.
     - Compare `4` and `3`: `3` is smaller, so link `3` to the result of merging `4` and `null`.
     - Link `4` to `null`.
   - Final merged list: `1 -> 2 -> 3 -> 4`

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n log n) |
| Space Complexity | O(log n) |

---

# 🔍 Edge Cases

- **Empty linked list**: The function should return `null`.
- **Single node linked list**: The function should return the node itself.
- **Already sorted linked list**: The function should return the linked list as is.
- **Reverse sorted linked list**: The function should sort the linked list in ascending order.
- **Linked list with duplicate values**: The function should handle duplicates correctly.

---

# 📚 Key Takeaways

- Merge sort is an efficient sorting algorithm for linked lists due to its O(n log n) time complexity.
- The optimal approach directly manipulates the linked list nodes, avoiding the need for additional space.
- Recursive divide-and-conquer strategies are powerful for sorting problems.

---

# 🚀 Interview Tips

- **Follow-up questions**: Discuss the time and space complexity of the algorithm and how it compares to other sorting algorithms.
- **Common pitfalls**: Ensure that the middle node is correctly identified and that the linked list is properly split and merged.
- **Alternative approaches**: Consider using an iterative approach to merge sort to reduce the space complexity to O(1).

---

# ✅ Conclusion

The optimal merge sort approach for linked lists is preferred due to its efficient time complexity and space complexity. The key insight is leveraging the divide-and-conquer strategy to recursively sort and merge the linked list, ensuring optimal performance.