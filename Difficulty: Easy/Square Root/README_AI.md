# Square Root

---

# 📝 Problem Statement

Given an integer `n`, find the floor value of the square root of `n`. The floor value is the greatest integer less than or equal to the square root of `n`.

**Objective:** Implement a function to compute the floor of the square root of `n`.

**Input:** An integer `n` (where `n` ≥ 0).

**Output:** The floor value of the square root of `n`.

**Constraints:**
- `0 ≤ n ≤ 10^18`
- The solution should be efficient and handle large values of `n`.

---

# 💡 Intuition

The problem requires finding the floor of the square root of a given integer `n`. The brute force approach involves checking each integer starting from 1 until the square of the integer exceeds `n`. The optimal approach uses binary search to efficiently narrow down the search space, reducing the time complexity significantly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through each integer starting from 1 and checking if the square of the integer is less than or equal to `n`. The loop continues until the square of the integer exceeds `n`, at which point the previous integer is returned as the floor of the square root.

---

## 🔹 Algorithm

1. Handle the edge case where `n` is 0 or 1 by returning `n` directly.
2. Initialize a variable `i` to 1.
3. Iterate through each integer `i` starting from 1:
   - If the square of `i` is greater than `n`, return `i - 1`.
   - Otherwise, increment `i` and continue the loop.

---

## 🔹 Code

```java
class Solution {
    int floorSqrt(int n) {
        if (n < 2) {
            return n;
        }
        int i = 1;
        while (i * i <= n) {
            i++;
        }
        return i - 1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with `n = 10`.

| Iteration | i | i * i | Condition (i * i <= n) | Action |
|-----------|---|-------|-------------------------|--------|
| 1         | 1 | 1     | True                    | i++    |
| 2         | 2 | 4     | True                    | i++    |
| 3         | 3 | 9     | True                    | i++    |
| 4         | 4 | 16    | False                   | Return i - 1 (3) |

The algorithm returns 3, which is the floor of the square root of 10.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(√n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses binary search to efficiently find the floor of the square root of `n`. Binary search is suitable here because the square root function is monotonically increasing, allowing us to narrow down the search space by comparing the square of the middle element with `n`.

---

## 🔹 Why This Works

Binary search is efficient because it reduces the search space by half in each iteration. By comparing the square of the middle element with `n`, we can determine whether to search the left or right half of the current search space. This approach ensures that we find the floor of the square root in logarithmic time.

---

## 🔹 Algorithm

1. Handle the edge case where `n` is 0 or 1 by returning `n` directly.
2. Initialize `left` to 0 and `right` to `n / 2`.
3. Perform binary search:
   - Calculate the middle element `mid`.
   - If the square of `mid` is equal to `n`, return `mid`.
   - If the square of `mid` is less than `n`, update `left` to `mid + 1`.
   - If the square of `mid` is greater than `n`, update `right` to `mid - 1`.
4. Return `right` as the floor of the square root of `n`.

---

## 🔹 Code

```java
class Solution {
    int floorSqrt(int n) {
        if (n < 2) {
            return n;
        }
        long left = 0, right = n / 2;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            if (square == n) {
                return (int) mid;
            } else if (square < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `n = 10`.

| Iteration | Left | Right | Mid | Square | Action |
|-----------|------|-------|-----|--------|--------|
| 1         | 0    | 5     | 2   | 4      | left = mid + 1 (3) |
| 2         | 3    | 5     | 4   | 16     | right = mid - 1 (3) |
| 3         | 3    | 3     | 3   | 9      | left = mid + 1 (4) |
| 4         | 4    | 3     | -    | -      | Loop ends |

The algorithm returns 3, which is the floor of the square root of 10.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- `n = 0`: The floor of the square root of 0 is 0.
- `n = 1`: The floor of the square root of 1 is 1.
- `n = 16`: The floor of the square root of 16 is 4.
- `n = 10^18`: The floor of the square root of 10^18 is 10^9.

---

# 📚 Key Takeaways

- The brute force approach is simple but inefficient for large values of `n`.
- The optimal approach uses binary search to achieve logarithmic time complexity.
- Binary search is efficient for problems involving monotonically increasing or decreasing functions.
- Understanding the properties of the square root function is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Explain why binary search is suitable for this problem.
- Consider edge cases such as large values of `n` and the behavior of the square root function.
- Be prepared to discuss the time and space complexity of both approaches.

---

# ✅ Conclusion

The optimal approach using binary search is preferred for its efficiency, especially when dealing with large values of `n`. Understanding the properties of the square root function and the application of binary search are key insights for solving this problem effectively.