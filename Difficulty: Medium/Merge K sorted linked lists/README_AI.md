# Merge K sorted linked lists

---

# 📝 Problem Statement

Given K sorted linked lists, merge them into a single sorted linked list.

**Objective**: Merge K sorted linked lists into one sorted linked list.

**Input**:
- An array of K sorted linked lists

**Output**:
- A single sorted linked list containing all elements from all input lists

**Constraints**:
- K can be up to 1000
- Each list can contain up to 1000 elements
- Values can be negative or positive

---

# 💡 Intuition

The key insight is that we need to efficiently merge multiple sorted lists. The brute force approach collects all elements and sorts them, which is simple but inefficient. The optimal approach uses a min-heap to always get the smallest element from all lists, which is more efficient.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Collect all elements from all linked lists into an array
2. Sort the array
3. Create a new linked list from the sorted array

---

## 🔹 Algorithm

1. Initialize an empty list to store all elements
2. Traverse each linked list and add all elements to the list
3. Sort the list
4. Create a new linked list from the sorted list
5. Return the head of the new linked list

---

## 🔹 Code

```java
class Solution {
    Node mergeKLists(Node[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        // Store all elements
        for (Node head : arr) {
            Node temp = head;
            while (temp != null) {
                list.add(temp.data);
                temp = temp.next;
            }
        }

        Collections.sort(list);

        // Create new linked list
        Node dummy = new Node(-1);
        Node curr = dummy;

        for (int val : list) {
            curr.next = new Node(val);
            curr = curr.next;
        }

        return dummy.next;
    }
}
```

---

## 🔹 Dry Run

Let's dry run with 3 lists:

List 1: 1 → 4 → 5
List 2: 1 → 3 → 4
List 3: 2 → 6

| Step | Action | List Contents | Sorted List |
|------|--------|---------------|-------------|
| 1    | Add elements from List 1 | [1, 4, 5] | [1, 4, 5] |
| 2    | Add elements from List 2 | [1, 4, 5, 1, 3, 4] | [1, 4, 5, 1, 3, 4] |
| 3    | Add elements from List 3 | [1, 4, 5, 1, 3, 4, 2, 6] | [1, 4, 5, 1, 3, 4, 2, 6] |
| 4    | Sort the list | - | [1, 1, 2, 3, 4, 4, 5, 6] |
| 5    | Create new linked list | - | 1 → 1 → 2 → 3 → 4 → 4 → 5 → 6 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N log N) where N is total number of elements |
| Space Complexity | O(N) for storing all elements |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use a min-heap to efficiently get the smallest element from all lists at any time.

1. Insert the first node of each list into a min-heap
2. While the heap is not empty:
   - Extract the smallest node
   - Add it to the result list
   - If the extracted node has a next node, insert it into the heap
3. Return the merged list

---

## 🔹 Why This Works

This approach efficiently merges the lists by always processing the smallest available element, similar to the merge step in merge sort. The min-heap ensures we always have access to the smallest element in O(log K) time, where K is the number of lists.

---

## 🔹 Algorithm

1. Create a min-heap based on node values
2. Insert the first node of each list into the heap
3. Initialize a dummy node and a current pointer
4. While the heap is not empty:
   - Extract the smallest node from the heap
   - Append it to the result list
   - If the extracted node has a next node, insert it into the heap
5. Return the merged list

---

## 🔹 Code

```java
import java.util.PriorityQueue;

class Solution {
    Node mergeKLists(Node[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);

        // Add first node of each list to the heap
        for (Node head : arr) {
            if (head != null) {
                minHeap.add(head);
            }
        }

        Node dummy = new Node(-1);
        Node current = dummy;

        while (!minHeap.isEmpty()) {
            Node smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                minHeap.add(smallest.next);
            }
        }

        return dummy.next;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run with 3 lists:

List 1: 1 → 4 → 5
List 2: 1 → 3 → 4
List 3: 2 → 6

| Step | Heap Contents | Extracted Node | Result List |
|------|----------------|-----------------|--------------|
| 1    | [1, 1, 2]      | 1 (from List 1) | 1            |
| 2    | [1, 2, 4]      | 1 (from List 2) | 1 → 1        |
| 3    | [2, 4, 4]      | 2 (from List 3) | 1 → 1 → 2    |
| 4    | [3, 4, 4]      | 3 (from List 2) | 1 → 1 → 2 → 3|
| 5    | [4, 4, 4]      | 4 (from List 1) | 1 → 1 → 2 → 3 → 4|
| 6    | [4, 4, 5]      | 4 (from List 2) | 1 → 1 → 2 → 3 → 4 → 4|
| 7    | [4, 5, 6]      | 4 (from List 3) | 1 → 1 → 2 → 3 → 4 → 4 → 4|
| 8    | [5, 6]         | 5 (from List 1) | 1 → 1 → 2 → 3 → 4 → 4 → 4 → 5|
| 9    | [6]            | 6 (from List 3) | 1 → 1 → 2 → 3 → 4 → 4 → 4 → 5 → 6|

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N log K) where N is total number of elements and K is number of lists |
| Space Complexity | O(K) for the heap |

---

# 🔍 Edge Cases

- Empty input array
- Single list in the array
- Lists of different lengths
- Lists with duplicate values
- Lists with negative numbers
- Large input size (close to constraints)

---

# 📚 Key Takeaways

1. The brute force approach is simple but inefficient for large inputs
2. The optimal approach uses a min-heap to efficiently merge sorted lists
3. The heap approach reduces time complexity from O(N log N) to O(N log K)
4. This problem demonstrates the importance of choosing the right data structure for efficient operations

---

# 🚀 Interview Tips

- Discuss the trade-offs between the two approaches
- Be ready to explain why the heap approach is more efficient
- Consider asking if the lists are guaranteed to be sorted
- Be prepared to discuss follow-up questions about handling duplicates or negative numbers

---

# ✅ Conclusion

The optimal approach using a min-heap is preferred for large inputs as it provides better time complexity. The key insight is leveraging the heap to always access the smallest element efficiently, demonstrating the importance of selecting appropriate data structures for optimization.