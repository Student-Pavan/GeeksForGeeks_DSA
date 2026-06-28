# 📌 Subarrays with Sum K

---

# 📝 Problem Statement

Given an array of integers `arr[]` and an integer `k`, find the number of subarrays whose sum is equal to `k`.

**Constraints:**
- `1 <= arr.length <= 2 * 10^4`
- `-1000 <= arr[i] <= 1000`
- `-10^7 <= k <= 10^7`

---

# 💡 Intuition

The optimal solution uses the prefix sum technique combined with a hash map to store frequency counts. The key insight is that if the difference between two prefix sums equals `k`, then the subarray between those indices sums to `k`. This allows us to solve the problem in O(n) time with O(n) space complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach checks all possible subarrays and counts those that sum to `k`. This involves nested loops where the outer loop selects the starting index and the inner loop extends the subarray to calculate the sum.

---

## 🔹 Algorithm

1. Initialize a counter to zero.
2. Use two nested loops:
   - Outer loop runs from index 0 to n-1.
   - Inner loop runs from the current outer index to n-1.
3. For each subarray, calculate the sum.
4. If the sum equals `k`, increment the counter.
5. Return the counter.

---

## 🔹 Code

```java
class Solution {
    public int cntSubarrays(int[] arr, int k) {
        int count = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `arr = [1, 2, 3]` and `k = 3`.

| Iteration | Subarray | Sum | Count |
|---|---|---|---|
| i=0, j=0 | [1] | 1 | 0 |
| i=0, j=1 | [1, 2] | 3 | 1 |
| i=0, j=2 | [1, 2, 3] | 6 | 1 |
| i=1, j=1 | [2] | 2 | 1 |
| i=1, j=2 | [2, 3] | 5 | 1 |
| i=2, j=2 | [3] | 3 | 2 |

The final count is 2.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses prefix sums and a hash map to store the frequency of prefix sums encountered. This allows us to count subarrays that sum to `k` in O(n) time.

---

## 🔹 Why This Works

By storing the frequency of each prefix sum, we can determine if a subarray with sum `k` exists by checking if `prefixSum - k` has been seen before. This avoids the need for nested loops and reduces the time complexity to O(n).

---

## 🔹 Algorithm

1. Initialize a hash map to store prefix sums and their frequencies.
2. Initialize `prefixSum` to 0 and `count` to 0.
3. Insert `(0, 1)` into the hash map to handle subarrays starting from index 0.
4. Iterate through the array:
   - Update `prefixSum` with the current element.
   - If `prefixSum - k` exists in the hash map, increment `count` by the frequency of `prefixSum - k`.
   - Update the hash map with the current `prefixSum`.
5. Return `count`.

---

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public int cntSubarrays(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int count = 0;

        for (int ele : arr) {
            prefixSum += ele;
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `arr = [1, 2, 3]` and `k = 3`.

| Iteration | Element | Prefix Sum | Map Updates | Count |
|---|---|---|---|---|
| 0 | 1 | 1 | {0:1, 1:1} | 0 |
| 1 | 2 | 3 | {0:1, 1:1, 3:1} | 1 (3-3=0, map contains 0) |
| 2 | 3 | 6 | {0:1, 1:1, 3:1, 6:1} | 1 |

The final count is 1.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty array.
- Single element array.
- All elements are the same.
- Negative values in the array.
- Large constraints.
- Sorted input.
- Reverse sorted input.

---

# 📚 Key Takeaways

- Prefix sums can be used to solve subarray sum problems efficiently.
- Hash maps are useful for storing and retrieving frequency counts.
- The optimal approach reduces the time complexity from O(n²) to O(n).

---

# 🚀 Interview Tips

- Discuss the brute force approach first to show understanding.
- Explain the intuition behind the optimal approach.
- Mention that the optimal approach can be extended to other subarray problems.
- Be prepared to discuss the trade-offs between time and space complexity.

---

# ✅ Conclusion

The optimal approach using prefix sums and a hash map is preferred for its efficiency. The key insight is recognizing that the difference between two prefix sums can determine the sum of a subarray. This approach is both time and space efficient, making it suitable for large input sizes.