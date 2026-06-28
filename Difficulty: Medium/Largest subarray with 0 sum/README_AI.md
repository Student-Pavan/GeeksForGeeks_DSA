# Largest subarray with 0 sum

---

# 📝 Problem Statement

Given an array of integers, find the length of the largest subarray with a sum equal to 0.

**Objective**: Determine the maximum length of a contiguous subarray whose elements sum to zero.

**Input**:
- An array of integers `arr[]`

**Output**:
- Integer representing the length of the largest subarray with sum 0

**Constraints**:
- 1 ≤ arr.length ≤ 10^5
- -10^5 ≤ arr[i] ≤ 10^5

---

# 💡 Intuition

The key insight is recognizing that if the same prefix sum occurs at two different indices, the subarray between these indices must sum to zero. This allows us to use a hash map to store prefix sums and their first occurrence indices, enabling efficient lookup.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and calculating their sums to find the longest one that sums to zero.

---

## 🔹 Algorithm

1. Initialize `maxlen` to 0
2. Iterate through all possible starting indices `i` from 0 to n-1
3. For each starting index `i`, initialize `current_sum` to 0
4. Iterate through all ending indices `j` from `i` to n-1
5. Add `arr[j]` to `current_sum`
6. If `current_sum` equals 0, update `maxlen` with the maximum of its current value and `j-i+1`
7. Return `maxlen`

---

## 🔹 Code

```java
class Solution {
    int maxLength(int arr[]) {
        int maxlen = 0;
        for (int i = 0; i < arr.length; i++) {
            int current_sum = 0;
            for (int j = i; j < arr.length; j++) {
                current_sum += arr[j];
                if (current_sum == 0) {
                    maxlen = Math.max(maxlen, j - i + 1);
                }
            }
        }
        return maxlen;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input array `[15, -2, 2, -8, 1, 7, 10, 23]`:

| Iteration | i | j | current_sum | maxlen |
|-----------|---|---|-------------|--------|
| 1         | 0 | 0 | 15          | 0      |
| 2         | 0 | 1 | 13          | 0      |
| 3         | 0 | 2 | 15          | 0      |
| 4         | 0 | 3 | 7           | 0      |
| 5         | 0 | 4 | 8           | 0      |
| 6         | 0 | 5 | 15          | 0      |
| 7         | 0 | 6 | 25          | 0      |
| 8         | 0 | 7 | 48          | 0      |
| 9         | 1 | 1 | -2          | 0      |
| 10        | 1 | 2 | 0           | 3      |
| 11        | 1 | 3 | -8          | 3      |
| 12        | 1 | 4 | -7          | 3      |
| 13        | 1 | 5 | 5           | 3      |
| 14        | 1 | 6 | 15          | 3      |
| 15        | 1 | 7 | 38          | 3      |
| 16        | 2 | 2 | 2           | 3      |
| 17        | 2 | 3 | -6          | 3      |
| 18        | 2 | 4 | -5          | 3      |
| 19        | 2 | 5 | 7           | 3      |
| 20        | 2 | 6 | 17          | 3      |
| 21        | 2 | 7 | 40          | 3      |
| 22        | 3 | 3 | -8          | 3      |
| 23        | 3 | 4 | -7          | 3      |
| 24        | 3 | 5 | 5           | 3      |
| 25        | 3 | 6 | 15          | 3      |
| 26        | 3 | 7 | 38          | 3      |
| 27        | 4 | 4 | 1           | 3      |
| 28        | 4 | 5 | 8           | 3      |
| 29        | 4 | 6 | 18          | 3      |
| 30        | 4 | 7 | 41          | 3      |
| 31        | 5 | 5 | 7           | 3      |
| 32        | 5 | 6 | 17          | 3      |
| 33        | 5 | 7 | 40          | 3      |
| 34        | 6 | 6 | 10          | 3      |
| 35        | 6 | 7 | 33          | 3      |
| 36        | 7 | 7 | 23          | 3      |

The algorithm returns `3` as the maximum length of a subarray with sum 0.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a hash map to store prefix sums and their first occurrence indices. This allows us to find the longest subarray with sum 0 in O(n) time.

---

## 🔹 Why This Works

The optimal approach works because if the same prefix sum occurs at two different indices, the subarray between these indices must sum to zero. By storing the first occurrence of each prefix sum, we can efficiently calculate the length of the longest subarray with sum 0.

---

## 🔹 Algorithm

1. Initialize a hash map `map` to store prefix sums and their first occurrence indices
2. Initialize `prefixsum` to 0 and `maxlen` to 0
3. Insert the pair `(0, -1)` into the map to handle the case where the subarray starts from index 0
4. Iterate through the array:
   - Add the current element to `prefixsum`
   - If `prefixsum` equals 0, update `maxlen` with the maximum of its current value and `i+1`
   - If `prefixsum` is found in the map, update `maxlen` with the maximum of its current value and `i - map.get(prefixsum)`
   - Otherwise, insert the pair `(prefixsum, i)` into the map
5. Return `maxlen`

---

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    int maxLength(int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefixsum = 0;
        int maxlen = 0;

        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            prefixsum += arr[i];

            if (prefixsum == 0) {
                maxlen = Math.max(maxlen, i + 1);
            } else {
                if (map.containsKey(prefixsum)) {
                    maxlen = Math.max(maxlen, i - map.get(prefixsum));
                } else {
                    map.put(prefixsum, i);
                }
            }
        }
        return maxlen;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal algorithm with the input array `[15, -2, 2, -8, 1, 7, 10, 23]`:

| Iteration | i | arr[i] | prefixsum | maxlen | map |
|-----------|---|--------|-----------|--------|-----|
| 0         | 0 | 15     | 15        | 0      | {0=-1, 15=0} |
| 1         | 1 | -2     | 13        | 0      | {0=-1, 15=0, 13=1} |
| 2         | 2 | 2      | 15        | 0      | {0=-1, 15=0, 13=1} |
| 3         | 3 | -8     | 7         | 0      | {0=-1, 15=0, 13=1, 7=3} |
| 4         | 4 | 1      | 8         | 0      | {0=-1, 15=0, 13=1, 7=3, 8=4} |
| 5         | 5 | 7      | 15        | 3      | {0=-1, 15=0, 13=1, 7=3, 8=4} |
| 6         | 6 | 10     | 25        | 3      | {0=-1, 15=0, 13=1, 7=3, 8=4, 25=6} |
| 7         | 7 | 23     | 48        | 3      | {0=-1, 15=0, 13=1, 7=3, 8=4, 25=6, 48=7} |

The algorithm returns `3` as the maximum length of a subarray with sum 0.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty array: The function should return 0.
- Single element array: If the element is 0, return 1; otherwise, return 0.
- All elements are zero: The function should return the length of the array.
- No subarray with sum zero: The function should return 0.
- Large input size: The optimal solution should handle large inputs efficiently.

---

# 📚 Key Takeaways

1. **Prefix Sum Technique**: The prefix sum technique is useful for solving subarray sum problems efficiently.
2. **Hash Map for Lookup**: Using a hash map to store prefix sums and their first occurrence indices allows for efficient lookup and reduces the time complexity from O(n²) to O(n).
3. **Edge Cases**: Always consider edge cases such as empty arrays, single element arrays, and large input sizes.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem in O(n) time and O(n) space?
  - What if the array contains negative numbers?
- **Common Pitfalls**:
  - Forgetting to handle the case where the subarray starts from index 0.
  - Not considering the case where the subarray ends at the last index.
- **Alternative Approaches**:
  - Using a brute force approach with O(n²) time complexity.
  - Using a sliding window approach if the array contains only positive numbers.

---

# ✅ Conclusion

The optimal solution using the prefix sum technique and hash map is preferred because it efficiently solves the problem in O(n) time and O(n) space. The key insight is recognizing that if the same prefix sum occurs at two different indices, the subarray between these indices must sum to zero. This allows us to use a hash map to store prefix sums and their first occurrence indices, enabling efficient lookup and reducing the time complexity significantly.