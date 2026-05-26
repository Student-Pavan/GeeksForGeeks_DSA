# Factorial

---

# 📝 Problem Statement

The factorial of a non-negative integer `n` is the product of all positive integers less than or equal to `n`. The factorial of `0` is defined as `1`.

Given an integer `n`, calculate its factorial.

**Constraints:**
- `0 <= n <= 20`

---

# 💡 Intuition

The factorial problem can be solved using recursion, where each call calculates the product of the current number with the factorial of the previous number. This approach leverages the mathematical definition of factorial directly.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to calculate the factorial. For each number `n`, the function calls itself with `n-1` until it reaches the base case (n = 0 or 1), at which point it returns 1. The results are then multiplied back up the call stack.

---

## 🔹 Algorithm

1. **Base Case:** If `n` is 0 or 1, return 1.
2. **Recursive Case:** Return `n * factorial(n - 1)`.

---

## 🔹 Code

```java
class Solution {
    // Function to calculate factorial of a number.
    int factorial(int n) {
        // code here
        return fact(n);
    }
    private int fact(int n){
        if(n == 1 || n == 0){
            return 1;
        }
        return n * fact(n - 1);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the factorial calculation for `n = 5`.

| Step | n | Action | Return Value |
|---|---|---|---|
| 1 | 5 | 5 * fact(4) | 5 * 24 = 120 |
| 2 | 4 | 4 * fact(3) | 4 * 6 = 24 |
| 3 | 3 | 3 * fact(2) | 3 * 2 = 6 |
| 4 | 2 | 2 * fact(1) | 2 * 1 = 2 |
| 5 | 1 | Base case | 1 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach also uses recursion, but it avoids the overhead of multiple function calls by using an iterative method. This approach iterates from 1 to `n`, multiplying each number to a running product.

---

## 🔹 Why This Works

The iterative approach is more efficient in terms of space complexity because it does not use the call stack to store intermediate results. It directly computes the factorial in a single loop.

---

## 🔹 Algorithm

1. Initialize a variable `result` to 1.
2. Iterate from 1 to `n`, multiplying each number with `result`.
3. Return `result`.

---

## 🔹 Code

```java
class Solution {
    // Function to calculate factorial of a number.
    int factorial(int n) {
        // code here
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the factorial calculation for `n = 5`.

| Iteration | i | result | Action |
|---|---|---|---|
| 1 | 1 | 1 | result = 1 * 1 = 1 |
| 2 | 2 | 1 | result = 1 * 2 = 2 |
| 3 | 3 | 2 | result = 2 * 3 = 6 |
| 4 | 4 | 6 | result = 6 * 4 = 24 |
| 5 | 5 | 24 | result = 24 * 5 = 120 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **n = 0:** The factorial of 0 is 1.
- **n = 1:** The factorial of 1 is 1.
- **n = 20:** The largest value within the constraint, ensuring the result fits in an integer.

---

# 📚 Key Takeaways

- **Recursion vs Iteration:** Recursion is elegant but can be less efficient due to stack overhead. Iteration is more efficient for this problem.
- **Base Case:** Always handle the base case (n = 0 or 1) to prevent infinite recursion.
- **Constraints:** Ensure the solution handles the upper constraint (n = 20) correctly.

---

# 🚀 Interview Tips

- **Follow-up Questions:** Discuss the constraints and how the solution would change if `n` were larger.
- **Common Pitfalls:** Forgetting the base case or not handling edge cases properly.
- **Alternative Approaches:** Consider using memoization or dynamic programming for larger values of `n`.

---

# ✅ Conclusion

The optimal solution using iteration is preferred for its efficiency and simplicity. It directly computes the factorial in linear time with constant space complexity, making it both time and space efficient. Understanding the base case and the iterative approach is crucial for solving this problem effectively.