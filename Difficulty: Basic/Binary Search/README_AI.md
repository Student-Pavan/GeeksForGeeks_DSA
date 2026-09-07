# 📌 Binary Search

---

# 📝 Problem Statement

Given a sorted array of integers and a target value, determine if the target exists in the array. If it does, return `true`; otherwise, return `false`.

**Input:**
- A sorted array of integers `arr`
- An integer `k` representing the target value

**Output:**
- `true` if `k` is found in `arr`, otherwise `false`

**Constraints:**
- The array may contain up to 10^5 elements
- The array is sorted in ascending order
- The solution must be efficient for large inputs

---

# 💡 Intuition

Binary search is an efficient algorithm for finding an element in a sorted array. The key insight is that since the array is sorted, we can repeatedly divide the search interval in half, eliminating half of the remaining elements with each comparison. This approach significantly reduces the number of comparisons needed compared to a linear search.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking each element in the array sequentially until the target is found or the end of the array is reached.

---

## 🔹 Algorithm

1. Iterate through each element in the array from the first to the last element.
2. For each element, compare it with the target value.
3. If the element matches the target, return `true`.
4. If the loop completes without finding the target, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean bruteForceSearch(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) {
                return true;
            }
        }
        return false;
    }
}
```

---

## 🔹 Dry Run

Let's consider the array `[1, 3, 5, 7, 9]` and the target `5`.

| Iteration | Current Value | Action | Result |
|-----------|---------------|--------|--------|
| 1         | 1             | Compare 1 with 5 | Not found |
| 2         | 3             | Compare 3 with 5 | Not found |
| 3         | 5             | Compare 5 with 5 | Found |

The algorithm returns `true` after 3 iterations.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search, which leverages the sorted nature of the array to reduce the search space by half in each iteration.

---

## 🔹 Why This Works

Binary search works by repeatedly dividing the search interval in half. If the target value is less than the middle element of the interval, the search continues in the lower half. Otherwise, it continues in the upper half. This process continues until the target is found or the interval is empty.

---

## 🔹 Algorithm

1. Initialize two pointers, `low` and `high`, to the start and end of the array, respectively.
2. While `low` is less than or equal to `high`:
   a. Calculate the middle index `mid` as `low + (high - low) / 2`.
   b. If the element at `mid` is equal to the target, return `true`.
   c. If the element at `mid` is less than the target, set `low` to `mid + 1`.
   d. Otherwise, set `high` to `mid - 1`.
3. If the loop completes without finding the target, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean binarySearch(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == k) {
                return true;
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's consider the array `[1, 3, 5, 7, 9]` and the target `5`.

| Step | Left | Right | Mid | Action | State |
|------|------|-------|-----|--------|-------|
| 1    | 0    | 4     | 2   | Compare 5 with 5 | Found |

The algorithm returns `true` after 1 iteration.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array:** The array has no elements.
- **Single Element:** The array has only one element.
- **Target Not Present:** The target value is not in the array.
- **Target at First Position:** The target is the first element in the array.
- **Target at Last Position:** The target is the last element in the array.
- **Duplicate Elements:** The array contains duplicate values.

---

# 📚 Key Takeaways

- Binary search is an efficient algorithm for searching in sorted arrays.
- It reduces the search space by half in each iteration, leading to a logarithmic time complexity.
- The algorithm requires the array to be sorted, which is a crucial constraint.
- Understanding the optimal approach is essential for solving problems involving sorted data efficiently.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - How would you handle duplicate elements in the array?
  - What if the array is not sorted? Would you need to sort it first?
  - Can you implement binary search recursively?
- **Common Pitfalls:**
  - Forgetting to handle the case where the target is not in the array.
  - Incorrectly updating the `low` and `high` pointers.
  - Not considering edge cases such as empty arrays or single-element arrays.
- **Alternative Approaches:**
  - Linear search is straightforward but inefficient for large arrays.
  - Interpolation search can be used for uniformly distributed data but is less common in practice.

---

# ✅ Conclusion

Binary search is a fundamental algorithm that demonstrates the power of divide and conquer. By reducing the search space logarithmically, it efficiently locates elements in sorted arrays. Understanding and mastering this algorithm is crucial for solving a wide range of problems in computer science and software engineering.