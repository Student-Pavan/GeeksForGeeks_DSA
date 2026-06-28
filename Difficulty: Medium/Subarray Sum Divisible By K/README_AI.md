# Subarray Sum Divisible By K

---

# 📝 Problem Statement

Given an integer array `arr` and an integer `k`, find the number of subarrays where the sum of the subarray is divisible by `k`.

**Constraints:**
- `1 <= arr.length <= 3 * 10^4`
- `-10^4 <= arr[i] <= 10^4`
- `2 <= k <= 10^4`

---

# 💡 Intuition

The key insight here is recognizing that if the difference between two prefix sums is divisible by `k`, then the subarray between those indices has a sum divisible by `k`. This allows us to use prefix sums and modular arithmetic to efficiently count these subarrays without checking every possible subarray explicitly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible subarrays and calculating their sums to see if they're divisible by `k`. This involves nested loops where we consider every possible starting and ending index for subarrays.

---

## 🔹 Algorithm

1. Initialize a counter to zero.
2. Use nested loops to consider all possible subarrays:
   - Outer loop for starting index `i`
   - Inner loop for ending index `j` (from `i` to end of array)
3. For each subarray from `i` to `j`, calculate the sum.
4. If the sum is divisible by `k`, increment the counter.
5. Return the counter.

---

## 🔹 Code

```java
class Solution {
    public int subCount(int[] arr, int k) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum % k == 0) {
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

Let's dry run the algorithm with `arr = [4, 5, 0, -2, -3, 1]` and `k = 5`.

| Iteration | Subarray | Sum | Divisible by 5? | Count |
|-----------|----------|-----|------------------|-------|
| 1         | [4]      | 4   | No               | 0     |
| 2         | [5]      | 5   | Yes              | 1     |
| 3         | [0]      | 0   | Yes              | 2     |
| 4         | [-2]     | -2  | No               | 2     |
| 5         | [-3]     | -3  | No               | 2     |
| 6         | [1]      | 1   | No               | 2     |
| 7         | [4,5]    | 9   | No               | 2     |
| 8         | [5,0]    | 5   | Yes              | 3     |
| 9         | [0,-2]   | -2  | No               | 3     |
| 10        | [-2,-3]  | -5  | No               | 3     |
| 11        | [-3,1]   | -2  | No               | 3     |
| 12        | [4,5,0]  | 9   | No               | 3     |
| 13        | [5,0,-2] | 3   | No               | 3     |
| 14        | [0,-2,-3]| -5  | No               | 3     |
| 15        | [-2,-3,1]| -4  | No               | 3     |
| 16        | [4,5,0,-2]| 7 | No               | 3     |
| 17        | [5,0,-2,-3]| 0| Yes              | 4     |
| 18        | [0,-2,-3,1]| -4| No               | 4     |
| 19        | [4,5,0,-2,-3]| 4| No               | 4     |
| 20        | [5,0,-2,-3,1]| 1| No               | 4     |
| 21        | [4,5,0,-2,-3,1]| 5| Yes              | 5     |

Final count: 5

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses prefix sums and modular arithmetic to count subarrays with sums divisible by `k` in O(n) time. We maintain a running prefix sum and use a hash map to store the frequency of remainders when the prefix sum is divided by `k`. This allows us to efficiently count valid subarrays without checking every possible subarray.

---

## 🔹 Why This Works

The approach works because if two prefix sums have the same remainder when divided by `k`, the subarray between them has a sum divisible by `k`. By tracking remainders and their frequencies, we can count all such valid subarrays in linear time.

---

## 🔹 Algorithm

1. Initialize a hash map to store remainder frequencies, with `remainder 0` initialized to 1.
2. Initialize `prefixSum` and `count` to 0.
3. Iterate through the array:
   - Update `prefixSum` with the current element.
   - Calculate the remainder of `prefixSum` divided by `k`.
   - If the remainder is negative, adjust it to be positive.
   - If the remainder exists in the map, add its frequency to `count`.
   - Update the map with the current remainder.
4. Return `count`.

---

## 🔹 Code

```java
class Solution {
    public int subCount(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long prefixSum = 0;
        int count = 0;

        for (int num : arr) {
            prefixSum += num;

            int rem = (int)(prefixSum % k);
            if (rem < 0)
                rem += k;

            if (map.containsKey(rem))
                count += map.get(rem);

            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `arr = [4, 5, 0, -2, -3, 1]` and `k = 5`.

| Iteration | Current Value | Prefix Sum | Remainder | Map Updates | Count |
|-----------|---------------|------------|-----------|-------------|-------|
| 1         | 4             | 4          | 4         | {0:1, 4:1}  | 0     |
| 2         | 5             | 9          | 4         | {0:1, 4:2}  | 0     |
| 3         | 0             | 9          | 4         | {0:1, 4:3}  | 0     |
| 4         | -2            | 7          | 2         | {0:1, 4:3, 2:1} | 0 |
| 5         | -3            | 4          | 4         | {0:1, 4:4, 2:1} | 1 (4 appears 3 times) |
| 6         | 1             | 5          | 0         | {0:2, 4:4, 2:1} | 3 (0 appears 1 time) |

Final count: 3

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty array: Should return 0
- Single element divisible by `k`: Should return 1
- All elements zero: Should return n*(n+1)/2
- Large array with small `k`: Should handle efficiently
- Negative numbers: Should handle correctly with modulo adjustment

---

# 📚 Key Takeaways

- Prefix sums are powerful for subarray problems
- Modular arithmetic helps identify patterns in sums
- Hash maps can efficiently track frequencies
- Optimal solution reduces time complexity significantly

---

# 🚀 Interview Tips

- Discuss follow-up questions like handling negative numbers
- Mention alternative approaches like using sliding window
- Explain why the modulo adjustment is necessary
- Highlight the importance of prefix sums in subarray problems

---

# ✅ Conclusion

The optimal solution using prefix sums and modular arithmetic is significantly more efficient than the brute force approach. It reduces the time complexity from O(n²) to O(n) and is essential for handling large input sizes. Understanding this pattern is crucial for solving subarray problems efficiently in interviews.