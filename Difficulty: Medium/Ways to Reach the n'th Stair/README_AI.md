# Ways to Reach the n'th Stair

---

# 📝 Problem Statement

You are climbing a staircase. It takes `n` steps to reach the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

**Constraints:**
- `1 <= n <= 45`

---

# 💡 Intuition

This problem is a classic dynamic programming question that resembles the Fibonacci sequence. The key observation is that the number of ways to reach the nth stair is the sum of the ways to reach the (n-1)th stair (from which you take a single step) and the (n-2)th stair (from which you take a double step). This recurrence relation forms the basis for both the brute force and optimal solutions.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to explore all possible ways to climb the stairs. For each step, we recursively calculate the number of ways to reach the remaining steps by taking either 1 or 2 steps.

---

## 🔹 Algorithm

1. Base Cases:
   - If `n == 1`, return 1 (only one way to climb 1 step).
   - If `n == 2`, return 2 (two ways: 1+1 or 2).
2. Recursive Case:
   - For `n > 2`, the number of ways is the sum of the ways to reach `n-1` and `n-2`.

---

## 🔹 Code

```java
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the recursive approach for `n = 4`:

1. `climbStairs(4)` calls `climbStairs(3)` and `climbStairs(2)`.
2. `climbStairs(3)` calls `climbStairs(2)` and `climbStairs(1)`.
3. `climbStairs(2)` returns 2.
4. `climbStairs(1)` returns 1.
5. `climbStairs(3)` returns `2 + 1 = 3`.
6. `climbStairs(2)` returns 2.
7. `climbStairs(4)` returns `3 + 2 = 5`.

The total number of ways to reach the 4th stair is 5.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(2^n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses dynamic programming to avoid the exponential time complexity of the recursive solution. We use an iterative approach with constant space to store the results of subproblems.

---

## 🔹 Why This Works

By recognizing the overlapping subproblems and optimal substructure properties, we can compute the solution iteratively. This approach reduces the time complexity to linear and the space complexity to constant.

---

## 🔹 Algorithm

1. Initialize variables to store the number of ways to reach the previous two stairs.
2. Iterate from 3 to `n`, updating the number of ways to reach the current stair as the sum of the ways to reach the previous two stairs.
3. Return the result after the loop completes.

---

## 🔹 Code

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;

        int prev2 = 1;
        int prev1 = 2;

        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the iterative approach for `n = 4`:

| Iteration | prev2 | prev1 | curr | Action |
|---|---|---|---|---|
| Initialization | 1 | 2 | - | - |
| i = 3 | 1 | 2 | 3 | prev2 = prev1, prev1 = curr |
| i = 4 | 2 | 3 | 5 | prev2 = prev1, prev1 = curr |

The final result is `prev1 = 5`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- `n = 1`: Only one way to climb.
- `n = 2`: Two ways to climb (1+1 or 2).
- `n = 45`: The maximum constraint, which should be handled efficiently.

---

# 📚 Key Takeaways

- Recognize problems with overlapping subproblems and optimal substructure.
- Use dynamic programming to optimize recursive solutions.
- Iterative approaches with constant space are often more efficient.

---

# 🚀 Interview Tips

- Ask about the constraints to determine if a recursive solution is feasible.
- Discuss the trade-offs between time and space complexity.
- Be prepared to explain the dynamic programming approach and its advantages.

---

# ✅ Conclusion

The optimal solution using dynamic programming is preferred for its linear time complexity and constant space complexity. The key insight is recognizing the Fibonacci-like recurrence relation and applying dynamic programming to optimize the solution.