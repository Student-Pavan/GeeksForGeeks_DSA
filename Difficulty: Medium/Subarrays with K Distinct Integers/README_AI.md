# 📌 Subarrays with K Distinct Integers

---

# 📝 Problem Statement

Given an integer array `arr` and an integer `k`, return the number of good subarrays of `arr`.

A good array is an array where the number of different integers in that array is exactly `k`.

For example, `[1,2,1,2,3]` has `2` distinct integers: `1, 2, 3`.

Subarrays are contiguous parts of an array.

---

# 💡 Intuition

The key insight is recognizing that the problem can be solved using a sliding window approach combined with a mathematical relationship between subarrays with at most `k` distinct integers and subarrays with at most `k-1` distinct integers.

The formula `exactlyK = atMostK - atMostK-1` efficiently calculates the count of subarrays with exactly `k` distinct integers.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and counting those with exactly `k` distinct integers. This involves nested loops to generate all subarrays and a hash set to count distinct integers in each subarray.

---

## 🔹 Algorithm

1. Initialize `count` to 0.
2. Use nested loops to generate all possible subarrays.
3. For each subarray, use a hash set to count distinct integers.
4. If the count of distinct integers equals `k`, increment `count`.
5. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int subarraysWithKDistinct(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < arr.length; j++) {
                set.add(arr[j]);
                if (set.size() == k) {
                    count++;
                } else if (set.size() > k) {
                    break;
                }
            }
        }
        return count;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `arr = [1,2,1,2,3]` and `k = 2`.

| Iteration | Subarray | Distinct Elements | Count |
|-----------|----------|--------------------|-------|
| i=0       | [1]      | {1}                | 0     |
|           | [1,2]    | {1,2}              | 1     |
|           | [1,2,1]  | {1,2}              | 2     |
|           | [1,2,1,2]| {1,2}              | 3     |
|           | [1,2,1,2,3]| {1,2,3}          | 3     |
| i=1       | [2]      | {2}                | 3     |
|           | [2,1]    | {2,1}              | 4     |
|           | [2,1,2]  | {2,1}              | 5     |
|           | [2,1,2,3]| {2,1,3}           | 5     |
| i=2       | [1]      | {1}                | 5     |
|           | [1,2]    | {1,2}              | 6     |
|           | [1,2,3]  | {1,2,3}            | 6     |
| i=3       | [2]      | {2}                | 6     |
|           | [2,3]    | {2,3}              | 7     |
| i=4       | [3]      | {3}                | 7     |

The final count is `7`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the sliding window technique to count subarrays with at most `k` distinct integers and at most `k-1` distinct integers. The difference between these counts gives the number of subarrays with exactly `k` distinct integers.

---

## 🔹 Why This Works

The sliding window technique efficiently counts subarrays with at most `k` distinct integers by maintaining a window that contains at most `k` distinct integers. The difference between the counts of subarrays with at most `k` and at most `k-1` distinct integers gives the count of subarrays with exactly `k` distinct integers.

---

## 🔹 Algorithm

1. Define a helper function `atMostK` that counts subarrays with at most `k` distinct integers.
2. Use the sliding window technique to count subarrays with at most `k` distinct integers.
3. Use the formula `exactlyK = atMostK(arr, k) - atMostK(arr, k-1)` to get the count of subarrays with exactly `k` distinct integers.

---

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public int subarraysWithKDistinct(int[] arr, int k) {
        return atMostK(arr, k) - atMostK(arr, k - 1);
    }

    private int atMostK(int[] arr, int k) {
        int count = 0;
        int left = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < arr.length; right++) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
            while (map.size() > k) {
                map.put(arr[left], map.get(arr[left]) - 1);
                if (map.get(arr[left]) == 0) {
                    map.remove(arr[left]);
                }
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `arr = [1,2,1,2,3]` and `k = 2`.

### atMostK(arr, 2)

| Step | Left | Right | Action | State | Count |
|------|------|-------|--------|-------|-------|
| 1    | 0    | 0     | Add 1  | {1:1} | 1     |
| 2    | 0    | 1     | Add 2  | {1:1, 2:1} | 3     |
| 3    | 0    | 2     | Add 1  | {1:2, 2:1} | 6     |
| 4    | 0    | 3     | Add 2  | {1:2, 2:2} | 10    |
| 5    | 0    | 4     | Add 3  | {1:2, 2:2, 3:1} | 15    |
| 6    | 1    | 4     | Remove 1 | {1:1, 2:2, 3:1} | 15    |
| 7    | 2    | 4     | Remove 2 | {1:1, 2:1, 3:1} | 15    |

### atMostK(arr, 1)

| Step | Left | Right | Action | State | Count |
|------|------|-------|--------|-------|-------|
| 1    | 0    | 0     | Add 1  | {1:1} | 1     |
| 2    | 1    | 1     | Remove 1, Add 2 | {2:1} | 2     |
| 3    | 2    | 2     | Remove 2, Add 1 | {1:1} | 3     |
| 4    | 3    | 3     | Remove 1, Add 2 | {2:1} | 4     |
| 5    | 4    | 4     | Remove 2, Add 3 | {3:1} | 5     |

### Final Calculation

`exactlyK = atMostK(arr, 2) - atMostK(arr, 1) = 15 - 5 = 10`

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Input**: `arr = []`, `k = 1` → `0`
- **Single Element**: `arr = [1]`, `k = 1` → `1`
- **All Elements Same**: `arr = [1,1,1]`, `k = 1` → `3`
- **Large k**: `arr = [1,2,3]`, `k = 3` → `1`
- **k > Array Length**: `arr = [1,2,3]`, `k = 4` → `0`

---

# 📚 Key Takeaways

- The sliding window technique efficiently counts subarrays with at most `k` distinct integers.
- The difference between counts of subarrays with at most `k` and at most `k-1` distinct integers gives the count of subarrays with exactly `k` distinct integers.
- The optimal approach reduces the time complexity from O(n²) to O(n).

---

# 🚀 Interview Tips

- **Follow-up Questions**: What if the array is very large? Can we optimize further?
- **Common Pitfalls**: Forgetting to handle edge cases, such as empty input or single element.
- **Alternative Approaches**: Using a hash map to count distinct integers and nested loops to generate subarrays.

---

# ✅ Conclusion

The optimal approach efficiently counts subarrays with exactly `k` distinct integers using the sliding window technique and a mathematical relationship between counts of subarrays with at most `k` and at most `k-1` distinct integers. This approach reduces the time complexity from O(n²) to O(n), making it suitable for large input sizes.