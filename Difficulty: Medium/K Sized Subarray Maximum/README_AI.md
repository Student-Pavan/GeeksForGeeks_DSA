# 📌 K Sized Subarray Maximum

---

# 📝 Problem Statement

Given an array of integers and a number k, find the maximum element in every subarray of size k.

**Objective**: Return an array containing the maximum element of each subarray of size k.

**Input**:
- An array of integers `arr`
- An integer `k` representing the size of the subarray

**Output**:
- An array of integers where each element is the maximum of a subarray of size k

**Constraints**:
- 1 ≤ k ≤ arr.length ≤ 1,000,000
- -10,000 ≤ arr[i] ≤ 10,000

**Example**:
```
Input: arr = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
Output: [3, 3, 5, 5, 6, 7]
```

---

# 💡 Intuition

The brute force approach would involve sliding a window of size k over the array and finding the maximum element in each window, which would result in a time complexity of O(n*k). However, this is inefficient for large arrays.

The optimal approach uses a deque (double-ended queue) to maintain the indices of elements in the current window in decreasing order. This allows us to efficiently access the maximum element in each window in O(1) time, resulting in an overall time complexity of O(n).

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Iterate through the array from the start to the end.
2. For each position, consider the next k elements as a window.
3. Find the maximum element in this window.
4. Add the maximum element to the result list.
5. Move the window one step to the right and repeat until the end of the array is reached.

## 🔹 Algorithm

1. Initialize an empty list to store the result.
2. For each index `i` from 0 to `n - k`:
   a. Initialize `max_val` to the smallest possible integer.
   b. For each index `j` from `i` to `i + k - 1`:
      i. Update `max_val` to be the maximum of `max_val` and `arr[j]`.
   c. Add `max_val` to the result list.
3. Return the result list.

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i <= n - k; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, arr[j]);
            }
            result.add(max);
        }

        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with the input array `[1, 3, -1, -3, 5, 3, 6, 7]` and `k = 3`.

| Iteration | Window | Max |
|-----------|--------|-----|
| 1         | [1, 3, -1] | 3 |
| 2         | [3, -1, -3] | 3 |
| 3         | [-1, -3, 5] | 5 |
| 4         | [-3, 5, 3] | 5 |
| 5         | [5, 3, 6] | 6 |
| 6         | [3, 6, 7] | 7 |

The result list after the dry run is `[3, 3, 5, 5, 6, 7]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n*k) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use a deque to maintain the indices of elements in the current window in decreasing order.
2. Iterate through the array from the start to the end.
3. For each element, remove indices from the front of the deque that are outside the current window.
4. Remove indices from the back of the deque where the corresponding elements are smaller than the current element.
5. Add the current index to the back of the deque.
6. If the current index is greater than or equal to `k - 1`, add the element at the front of the deque to the result list.

## 🔹 Why This Works

The deque maintains the indices of elements in the current window in decreasing order. This ensures that the front of the deque always contains the index of the maximum element in the current window. By removing indices from the front that are outside the current window and indices from the back where the corresponding elements are smaller than the current element, we ensure that the deque always contains the indices of elements in the current window in decreasing order.

## 🔹 Algorithm

1. Initialize an empty list to store the result and a deque to store indices.
2. For each index `right` from 0 to `n - 1`:
   a. Remove indices from the front of the deque that are outside the current window.
   b. Remove indices from the back of the deque where the corresponding elements are smaller than the current element.
   c. Add the current index to the back of the deque.
   d. If the current index is greater than or equal to `k - 1`, add the element at the front of the deque to the result list.
3. Return the result list.

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();

        for (int right = 0; right < arr.length; right++) {

            // Remove indices out of window
            while (!dq.isEmpty() && dq.peekFirst() <= right - k) {
                dq.pollFirst();
            }

            // Maintain decreasing order in deque
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[right]) {
                dq.pollLast();
            }

            dq.addLast(right);

            // Add max of window
            if (right >= k - 1) {
                list.add(arr[dq.peekFirst()]);
            }
        }

        return list;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input array `[1, 3, -1, -3, 5, 3, 6, 7]` and `k = 3`.

| Iteration | Right | Deque | Action | Result |
|-----------|-------|-------|--------|--------|
| 1         | 0     | [0]   | Add 0  | []     |
| 2         | 1     | [1]   | Remove 0, Add 1 | [] |
| 3         | 2     | [1, 2] | Add 2 | [] |
| 4         | 3     | [1, 2, 3] | Remove 1, Add 3 | [3] |
| 5         | 4     | [3, 4] | Remove 2, Add 4 | [3, 3] |
| 6         | 5     | [4, 5] | Add 5 | [3, 3, 5] |
| 7         | 6     | [4, 5, 6] | Remove 4, Add 6 | [3, 3, 5, 5] |
| 8         | 7     | [6, 7] | Remove 5, Add 7 | [3, 3, 5, 5, 6, 7] |

The result list after the dry run is `[3, 3, 5, 5, 6, 7]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(k) |

---

# 🔍 Edge Cases

- **Empty Input**: If the input array is empty, the result should be an empty list.
- **Single Element**: If the input array has only one element, the result should be a list containing that element.
- **All Elements Same**: If all elements in the array are the same, the result should be a list containing that element repeated `n - k + 1` times.
- **Negative Values**: The algorithm should handle negative values correctly.
- **Large k**: If `k` is equal to the length of the array, the result should be a list containing the maximum element of the entire array.
- **Large Array**: The algorithm should handle large arrays efficiently.

---

# 📚 Key Takeaways

- The brute force approach has a time complexity of O(n*k), which is inefficient for large arrays.
- The optimal approach uses a deque to maintain the indices of elements in the current window in decreasing order, resulting in a time complexity of O(n).
- The optimal approach is suitable for large arrays and is preferred in practice.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem in O(n) time?
  - Can you solve this problem in O(1) space?
- **Common Pitfalls**:
  - Forgetting to remove indices from the front of the deque that are outside the current window.
  - Not maintaining the deque in decreasing order.
- **Alternative Approaches**:
  - Using a priority queue to maintain the elements in the current window in decreasing order.
- **Optimization Discussions**:
  - The optimal approach is more efficient than the brute force approach for large arrays.
  - The optimal approach is suitable for online processing, where elements are added to the array one by one.

---

# ✅ Conclusion

The optimal approach using a deque is more efficient than the brute force approach for large arrays. It maintains the indices of elements in the current window in decreasing order, allowing us to efficiently access the maximum element in each window in O(1) time. The time complexity of the optimal approach is O(n), which is suitable for large arrays.