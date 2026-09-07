# 📌 Array to BST

---

# 📝 Problem Statement

Given a sorted array, convert it into a **Balanced Binary Search Tree (BST)**. A balanced BST is defined as a tree where the depth of the two subtrees of every node never differs by more than one.

## 🔹 Input
- A sorted array of integers.

## 🔹 Output
- The root node of the constructed balanced BST.

## 🔹 Constraints
- The array can have up to 10,000 elements.
- All elements in the array are unique.
- The array is sorted in non-decreasing order.

---

# 💡 Intuition

The key insight here is recognizing that the middle element of a sorted array can serve as the root of a balanced BST. This approach ensures that the left and right subtrees are also balanced, as the middle element divides the array into two roughly equal parts.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves recursively selecting the middle element of the current subarray to be the root of the subtree. This ensures that the BST remains balanced.

### 🔹 Algorithm

1. **Base Case**: If the start index exceeds the end index, return `null`.
2. **Find Middle Element**: Calculate the middle index of the current subarray.
3. **Create Node**: Create a new node with the middle element as the data.
4. **Recursive Construction**: Recursively construct the left subtree using the left half of the array and the right subtree using the right half of the array.
5. **Return Root**: Return the root node of the constructed subtree.

### 🔹 Code

```java
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public Node sortedArrayToBST(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return helper(arr, 0, arr.length - 1);
    }

    private Node helper(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        Node root = new Node(arr[mid]);

        root.left = helper(arr, start, mid - 1);
        root.right = helper(arr, mid + 1, end);

        return root;
    }
}
```

### 🔹 Dry Run

Let's dry run the algorithm with the input array `[1, 2, 3, 4, 5, 6, 7]`.

| Step | Start | End | Mid | Root | Left Subtree | Right Subtree |
|------|-------|-----|-----|------|--------------|----------------|
| 1    | 0     | 6   | 3   | 4    | [1, 2, 3]    | [5, 6, 7]      |
| 2    | 0     | 2   | 1   | 2    | [1]           | [3]            |
| 3    | 0     | 0   | 0   | 1    | null          | null           |
| 4    | 2     | 2   | 2   | 3    | null          | null           |
| 5    | 4     | 6   | 5   | 6    | [5]           | [7]            |
| 6    | 4     | 4   | 4   | 5    | null          | null           |
| 7    | 6     | 6   | 6   | 7    | null          | null           |

### 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(log n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but is more concise and efficient. It leverages the sorted nature of the array to construct the BST in a balanced manner.

### 🔹 Why This Works

The optimal approach works because the middle element of the sorted array is always the root of the BST, ensuring that the tree is balanced. This approach efficiently constructs the BST by recursively dividing the array into left and right halves.

### 🔹 Algorithm

1. **Base Case**: If the start index exceeds the end index, return `null`.
2. **Find Middle Element**: Calculate the middle index of the current subarray.
3. **Create Node**: Create a new node with the middle element as the data.
4. **Recursive Construction**: Recursively construct the left subtree using the left half of the array and the right subtree using the right half of the array.
5. **Return Root**: Return the root node of the constructed subtree.

### 🔹 Code

```java
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

class Solution {
    public Node sortedArrayToBST(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return helper(arr, 0, arr.length - 1);
    }

    private Node helper(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        Node root = new Node(arr[mid]);

        root.left = helper(arr, start, mid - 1);
        root.right = helper(arr, mid + 1, end);

        return root;
    }
}
```

### 🔹 Detailed Dry Run

Let's dry run the algorithm with the input array `[1, 2, 3, 4, 5, 6, 7]`.

| Step | Start | End | Mid | Root | Left Subtree | Right Subtree |
|------|-------|-----|-----|------|--------------|----------------|
| 1    | 0     | 6   | 3   | 4    | [1, 2, 3]    | [5, 6, 7]      |
| 2    | 0     | 2   | 1   | 2    | [1]           | [3]            |
| 3    | 0     | 0   | 0   | 1    | null          | null           |
| 4    | 2     | 2   | 2   | 3    | null          | null           |
| 5    | 4     | 6   | 5   | 6    | [5]           | [7]            |
| 6    | 4     | 4   | 4   | 5    | null          | null           |
| 7    | 6     | 6   | 6   | 7    | null          | null           |

### 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(log n) |

---

# 🔍 Edge Cases

- **Empty Array**: The function should return `null` for an empty array.
- **Single Element Array**: The function should return a single node with the element as the data.
- **Even Number of Elements**: The function should handle arrays with an even number of elements by selecting the middle element appropriately.
- **Negative Numbers**: The function should handle arrays with negative numbers correctly.
- **Large Array**: The function should handle large arrays efficiently.

---

# 📚 Key Takeaways

- **Balanced BST Construction**: The middle element of a sorted array can be used to construct a balanced BST.
- **Recursive Approach**: Recursive construction is efficient and ensures the tree remains balanced.
- **Time Complexity**: The time complexity is O(n) as each element is processed exactly once.
- **Space Complexity**: The space complexity is O(log n) due to the recursion stack.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle duplicates in the array.
- **Alternative Approaches**: Mention iterative approaches using a stack or queue.
- **Optimization Discussions**: Discuss the trade-offs between recursive and iterative approaches.
- **Common Pitfalls**: Avoid off-by-one errors when calculating the middle index.

---

# ✅ Conclusion

The optimal approach efficiently constructs a balanced BST from a sorted array by recursively selecting the middle element as the root. This ensures the tree remains balanced, and the time complexity is O(n). The space complexity is O(log n) due to the recursion stack. This approach is both efficient and easy to understand, making it suitable for interview scenarios.