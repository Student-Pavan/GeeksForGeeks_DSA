# First n Fibonacci using Recursion

---

# 📝 Problem Statement

Given an integer `n`, generate the first `n` Fibonacci numbers using recursion. The Fibonacci sequence is defined as follows:

- F(0) = 0
- F(1) = 1
- F(n) = F(n-1) + F(n-2) for n > 1

**Constraints:**
- 1 ≤ n ≤ 30

---

# 💡 Intuition

The Fibonacci sequence is a classic example of a problem that can be solved using recursion. The naive recursive approach directly implements the mathematical definition of the Fibonacci sequence. However, this approach has exponential time complexity due to repeated calculations of the same subproblems.

The optimal approach uses memoization to store previously computed Fibonacci numbers, avoiding redundant calculations and significantly improving the time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses a recursive function to calculate each Fibonacci number directly from the definition. For each number `i` from 0 to `n-1`, it calculates `fib(i)` recursively.

## 🔹 Algorithm

1. Initialize an array `ans` of size `n` to store the Fibonacci numbers.
2. For each index `i` from 0 to `n-1`:
   - Call the recursive function `fib(i)` to compute the i-th Fibonacci number.
   - Store the result in `ans[i]`.
3. Return the array `ans`.

## 🔹 Code

```java
class Solution {
    // Function to return list containing first n fibonacci numbers.
    public static int[] fibonacciNumbers(int n) {
        int ans[] = new int[n];

        for(int i = 0; i < n ; i++){
            ans[i] = fib(i);
        }

        return ans;
    }

    private static int fib(int n){
        if( n <= 1){
            return n;
        }

        return fib(n-1) + fib (n -2);
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm for `n = 5`:

| Iteration | i | fib(i) Calculation | ans[i] |
|-----------|---|--------------------|--------|
| 1         | 0 | fib(0) = 0          | 0      |
| 2         | 1 | fib(1) = 1          | 1      |
| 3         | 2 | fib(2) = fib(1) + fib(0) = 1 + 0 = 1 | 1 |
| 4         | 3 | fib(3) = fib(2) + fib(1) = 1 + 1 = 2 | 2 |
| 5         | 4 | fib(4) = fib(3) + fib(2) = 2 + 1 = 3 | 3 |

The final array `ans` is `[0, 1, 1, 2, 3]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(2^n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses memoization to store previously computed Fibonacci numbers. This avoids redundant calculations and significantly improves the time complexity.

## 🔹 Why This Works

Memoization stores the results of expensive function calls and returns the cached result when the same inputs occur again. This reduces the time complexity from exponential to linear.

## 🔹 Algorithm

1. Initialize an array `ans` of size `n` to store the Fibonacci numbers.
2. Initialize a memoization array `memo` of size `n` to store computed Fibonacci numbers.
3. For each index `i` from 0 to `n-1`:
   - Call the recursive function `fib(i)` with memoization to compute the i-th Fibonacci number.
   - Store the result in `ans[i]`.
4. Return the array `ans`.

## 🔹 Code

```java
class Solution {
    // Function to return list containing first n fibonacci numbers.
    public static int[] fibonacciNumbers(int n) {
        int ans[] = new int[n];
        int[] memo = new int[n];

        for(int i = 0; i < n ; i++){
            ans[i] = fib(i, memo);
        }

        return ans;
    }

    private static int fib(int n, int[] memo){
        if( n <= 1){
            return n;
        }

        if(memo[n] != 0){
            return memo[n];
        }

        memo[n] = fib(n-1, memo) + fib(n-2, memo);
        return memo[n];
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm for `n = 5`:

| Iteration | i | fib(i) Calculation | ans[i] | memo |
|-----------|---|--------------------|--------|------|
| 1         | 0 | fib(0) = 0          | 0      | [0, 0, 0, 0, 0] |
| 2         | 1 | fib(1) = 1          | 1      | [0, 1, 0, 0, 0] |
| 3         | 2 | fib(2) = fib(1) + fib(0) = 1 + 0 = 1 | 1 | [0, 1, 1, 0, 0] |
| 4         | 3 | fib(3) = fib(2) + fib(1) = 1 + 1 = 2 | 2 | [0, 1, 1, 2, 0] |
| 5         | 4 | fib(4) = fib(3) + fib(2) = 2 + 1 = 3 | 3 | [0, 1, 1, 2, 3] |

The final array `ans` is `[0, 1, 1, 2, 3]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **n = 1**: The output should be `[0]`.
- **n = 2**: The output should be `[0, 1]`.
- **n = 30**: The output should be `[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229]`.

---

# 📚 Key Takeaways

- The naive recursive approach has exponential time complexity due to repeated calculations.
- Memoization can significantly improve the time complexity by storing previously computed results.
- Understanding the Fibonacci sequence and its recursive definition is crucial for solving this problem.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem iteratively?
  - Can you solve this problem using dynamic programming?
- **Common Pitfalls**:
  - Forgetting to handle the base cases correctly.
  - Not considering the time complexity of the recursive approach.
- **Alternative Approaches**:
  - Using an iterative approach to avoid the overhead of recursion.
  - Using dynamic programming to store intermediate results.

---

# ✅ Conclusion

The optimal approach using memoization is preferred because it reduces the time complexity from exponential to linear. Understanding the Fibonacci sequence and its recursive definition is crucial for solving this problem efficiently.