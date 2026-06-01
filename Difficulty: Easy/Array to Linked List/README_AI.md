# Array to Linked List

---

# 📝 Problem Statement

Given an array, convert it into a linked list. The linked list should have the same elements as the array, in the same order. The first element of the array should be the head of the linked list, and the last element of the array should be the tail of the linked list.

**Example:**

```java
Input: [1, 2, 3, 4, 5]
Output: 1 -> 2 -> 3 -> 4 -> 5
```

**Constraints:**

- The array can contain up to 10^5 elements.
- The elements of the array are integers.

---

# 💡 Intuition

The problem requires converting an array into a linked list. The key insight here is that we need to create nodes for each element in the array and link them together in the same order as the array. The first element of the array will be the head of the linked list, and each subsequent element will be added to the end of the linked list.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through the array and creating a new node for each element. Each new node is then added to the end of the linked list. This approach is straightforward but may not be the most efficient for very large arrays.

---

## 🔹 Algorithm

1. Check if the array is empty. If it is, return null.
2. Create a new node with the first element of the array as the head of the linked list.
3. Iterate through the remaining elements of the array.
4. For each element, create a new node and add it to the end of the linked list.
5. Return the head of the linked list.

---

## 🔹 Code

```java
class Solution {
    public Node arrayToList(int arr[]) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            current.next = newNode;
            current = newNode;
        }

        return head;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input array `[1, 2, 3, 4, 5]`.

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 0 | 1 | head = 1, current = 1 | 1 |
| 1 | 2 | head = 1, current = 2 | 1 -> 2 |
| 2 | 3 | head = 1, current = 3 | 1 -> 2 -> 3 |
| 3 | 4 | head = 1, current = 4 | 1 -> 2 -> 3 -> 4 |
| 4 | 5 | head = 1, current = 5 | 1 -> 2 -> 3 -> 4 -> 5 |

After the loop completes, the linked list is `1 -> 2 -> 3 -> 4 -> 5`, and the head of the linked list is returned.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach. However, we can optimize the code by reducing the number of operations inside the loop. This approach still involves iterating through the array and creating nodes for each element, but it minimizes the number of operations performed within the loop.

---

## 🔹 Why This Works

This approach works because it efficiently creates a linked list by iterating through the array once and creating a node for each element. The time complexity is O(n) because we visit each element of the array exactly once. The space complexity is O(n) because we create a node for each element in the array.

---

## 🔹 Algorithm

1. Check if the array is empty. If it is, return null.
2. Create a new node with the first element of the array as the head of the linked list.
3. Iterate through the remaining elements of the array.
4. For each element, create a new node and add it to the end of the linked list.
5. Return the head of the linked list.

---

## 🔹 Code

```java
class Solution {
    public Node arrayToList(int arr[]) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        Node head = new Node(arr[0]);
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }

        return head;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input array `[1, 2, 3, 4, 5]`.

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 0 | 1 | head = 1, current = 1 | 1 |
| 1 | 2 | head = 1, current = 2 | 1 -> 2 |
| 2 | 3 | head = 1, current = 3 | 1 -> 2 -> 3 |
| 3 | 4 | head = 1, current = 4 | 1 -> 2 -> 3 -> 4 |
| 4 | 5 | head = 1, current = 5 | 1 -> 2 -> 3 -> 4 -> 5 |

After the loop completes, the linked list is `1 -> 2 -> 3 -> 4 -> 5`, and the head of the linked list is returned.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Array:** If the input array is empty, the function should return null.
- **Single Element Array:** If the input array contains only one element, the function should return a linked list with a single node.
- **Large Array:** The function should handle arrays with up to 10^5 elements efficiently.
- **Negative Numbers:** The function should handle arrays containing negative numbers correctly.

---

# 📚 Key Takeaways

- Converting an array to a linked list involves iterating through the array and creating nodes for each element.
- The time complexity of this operation is O(n), where n is the number of elements in the array.
- The space complexity is O(n) because we create a node for each element in the array.
- The optimal approach minimizes the number of operations inside the loop, making the code more efficient.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you convert the linked list back to an array?
  - How would you handle a very large array efficiently?
- **Common Pitfalls:**
  - Forgetting to handle the case where the array is empty.
  - Not setting the next pointer of the last node to null.
- **Alternative Approaches:**
  - Using recursion to create the linked list.
  - Using a stack to reverse the linked list after creation.

---

# ✅ Conclusion

The optimal approach to converting an array to a linked list is to iterate through the array and create a node for each element. This approach ensures that the linked list is created efficiently with a time complexity of O(n) and a space complexity of O(n). The key insight is to minimize the number of operations inside the loop to optimize the code.