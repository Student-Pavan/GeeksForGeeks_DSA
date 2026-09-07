# Max Consecutive Bit

---

# 📝 Problem Statement

Given a binary array `arr[]` of size `N`. The task is to find the maximum number of consecutive 1s or 0s present in the array.

**Objective**: Find the maximum consecutive sequence of either 1s or 0s in the array.

**Input**:
- An integer array `arr[]` of size `N` containing only 0s and 1s.

**Output**:
- An integer representing the maximum length of consecutive 1s or 0s.

**Constraints**:
- 1 ≤ N ≤ 10^5
- arr[i] is either 0 or 1

---

# 💡 Intuition

The problem requires finding the longest sequence of consecutive identical elements (either all 1s or all 0s) in a binary array. The key insight is to traverse the array while keeping track of the current and maximum consecutive counts for both 1s and 0s. By maintaining these counts during a single pass through the array, we can efficiently determine the solution in linear time.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible consecutive sequences in the array. For each element, we examine all possible sequences starting at that element and count the number of consecutive identical elements. We keep track of the maximum length found during this process.

## 🔹 Algorithm

1. Initialize `max_length` to 0.
2. For each element in the array:
   - Initialize `current_length` to 1.
   - For each subsequent element that is the same as the current element:
     - Increment `current_length`.
   - Update `max_length` if `current_length` is greater than `max_length`.
3. Return `max_length`.

## 🔹 Code

```java
class Solution {
    public int maxConsecBits(int[] arr) {
        int max_length = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int current_length = 1;
            for (int j = i + 1; j < n && arr[j] == arr[i]; j++) {
                current_length++;
            }
            max_length = Math.max(max_length, current_length);
        }

        return max_length;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the input `[1, 1, 0, 0, 1, 1, 1, 0]`.

| Iteration | Current Element | Current Length | Max Length |
|-----------|------------------|----------------|------------|
| 1         | 1                | 2              | 2          |
| 2         | 0                | 2              | 2          |
| 3         | 1                | 3              | 3          |
| 4         | 0                | 1              | 3          |

The algorithm checks all possible consecutive sequences and updates the maximum length found, which is 3 in this case.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves traversing the array once while keeping track of the current and maximum consecutive counts for both 1s and 0s. This allows us to determine the solution in a single pass through the array, resulting in linear time complexity.

## 🔹 Why This Works

By maintaining separate counters for the current and maximum consecutive counts of 1s and 0s, we can efficiently track the longest sequence without needing nested loops. This approach ensures that we only traverse the array once, making it significantly faster than the brute force method.

## 🔹 Algorithm

1. Initialize `max1s` and `max0s` to 0.
2. Initialize `curr1` and `curr0` to 0.
3. For each element in the array:
   - If the element is 1:
     - Increment `curr1`.
     - Reset `curr0` to 0.
   - Else:
     - Increment `curr0`.
     - Reset `curr1` to 0.
   - Update `max1s` and `max0s` with the maximum values of `curr1` and `curr0`, respectively.
4. Return the maximum of `max1s` and `max0s`.

## 🔹 Code

```java
class Solution {
    public int maxConsecBits(int[] arr) {
        int max1s = 0;
        int max0s = 0;
        int curr1 = 0;
        int curr0 = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                curr1++;
                curr0 = 0;
            } else {
                curr0++;
                curr1 = 0;
            }
            max1s = Math.max(max1s, curr1);
            max0s = Math.max(max0s, curr0);
        }

        return Math.max(max1s, max0s);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `[1, 1, 0, 0, 1, 1, 1, 0]`.

| Iteration | Current Element | Current 1s | Current 0s | Max 1s | Max 0s |
|-----------|------------------|-------------|-------------|--------|--------|
| 1         | 1                | 1           | 0           | 1      | 0      |
| 2         | 1                | 2           | 0           | 2      | 0      |
| 3         | 0                | 0           | 1           | 2      | 1      |
| 4         | 0                | 0           | 2           | 2      | 2      |
| 5         | 1                | 1           | 0           | 2      | 2      |
| 6         | 1                | 2           | 0           | 2      | 2      |
| 7         | 1                | 3           | 0           | 3      | 2      |
| 8         | 0                | 0           | 1           | 3      | 2      |

The algorithm traverses the array once, updating the current and maximum consecutive counts for 1s and 0s. The maximum consecutive count found is 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array**: If the input array is empty, the function should return 0.
- **Single Element**: If the array contains only one element, the function should return 1.
- **All 1s or All 0s**: If the array contains all 1s or all 0s, the function should return the length of the array.
- **Alternating 1s and 0s**: If the array alternates between 1s and 0s, the function should return 1.
- **Large Input**: The function should handle large input sizes efficiently, as specified in the constraints.

---

# 📚 Key Takeaways

- **Single Pass Efficiency**: The optimal approach efficiently solves the problem in a single pass through the array, making it suitable for large input sizes.
- **Tracking Consecutive Counts**: Maintaining separate counters for the current and maximum consecutive counts of 1s and 0s allows for an optimal solution.
- **Linear Time Complexity**: The optimal solution achieves linear time complexity, which is crucial for handling large input sizes efficiently.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how the solution can be extended to handle multi-dimensional arrays or other variations.
- **Common Pitfalls**: Be cautious of off-by-one errors when updating the current and maximum counts.
- **Alternative Approaches**: Consider using sliding window techniques or dynamic programming for similar problems.
- **Optimization Discussions**: Highlight the importance of optimizing the solution to linear time complexity for large input sizes.

---

# ✅ Conclusion

The optimal solution efficiently solves the problem by traversing the array once while maintaining separate counters for the current and maximum consecutive counts of 1s and 0s. This approach ensures linear time complexity and optimal performance, making it suitable for large input sizes. The key insight is to leverage single-pass traversal and careful counter management to achieve an efficient solution.