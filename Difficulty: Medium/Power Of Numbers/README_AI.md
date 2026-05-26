# 📌 Power Of Numbers

---

# 📝 Problem Statement

Given a number `n`, compute `n` raised to the power of the reverse of `n`.

**Objective**: Calculate `n^reverse(n)` efficiently.

**Constraints**:
- `n` can be positive or negative
- The result should be an integer
- Handle edge cases properly

---

# 💡 Intuition

The key insight is recognizing that exponentiation can be optimized using the "exponentiation by squaring" method, which reduces the time complexity from O(n) to O(log n). The reverse of the number can be computed efficiently using basic arithmetic operations.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Reverse the digits of the input number `n` to get `rev`.
2. Compute `n^rev` using a simple iterative multiplication approach.

## 🔹 Algorithm

1. Initialize `rev` to 0.
2. While `n` is greater than 0:
   - Extract the last digit of `n` and append it to `rev`.
   - Remove the last digit from `n`.
3. Initialize `result` to 1.
4. For each digit in `rev`:
   - Multiply `result` by `n`.

## 🔹 Code

```java
class Solution {
    public int reverseExponentiation(int n) {
        int rev = 0;
        int num = n;

        // Reverse the digits of n
        while (num != 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;
            num /= 10;
        }

        // Handle negative exponents
        if (rev < 0) {
            return 1 / power(n, -rev);
        }

        // Compute n^rev using brute force
        int result = 1;
        for (int i = 0; i < rev; i++) {
            result *= n;
        }

        return result;
    }

    private int power(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with `n = 12`.

| Step | `num` | `rev` | Action |
|------|-------|-------|--------|
| 1    | 12    | 0     | Initialize `rev` to 0 |
| 2    | 12    | 0     | Extract last digit 2, append to `rev` |
| 3    | 1     | 2     | Remove last digit, `num` becomes 1 |
| 4    | 1     | 2     | Extract last digit 1, append to `rev` |
| 5    | 0     | 21    | Remove last digit, `num` becomes 0 |
| 6    | 0     | 21    | Loop ends |

Now compute `12^21` using brute force.

| Iteration | `i` | `result` |
|-----------|-----|----------|
| 1         | 0   | 1        |
| 2         | 1   | 12       |
| ...       | ... | ...      |
| 21        | 20  | 12^21    |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(rev) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Reverse the digits of the input number `n` to get `rev`.
2. Compute `n^rev` using the "exponentiation by squaring" method, which reduces the number of multiplications.

## 🔹 Why This Works

The "exponentiation by squaring" method works by breaking down the exponent into powers of two, which allows us to compute the result in logarithmic time. This method is more efficient than the brute force approach, especially for large exponents.

## 🔹 Algorithm

1. Initialize `rev` to 0.
2. While `n` is greater than 0:
   - Extract the last digit of `n` and append it to `rev`.
   - Remove the last digit from `n`.
3. Initialize `result` to 1.
4. While `rev` is greater than 0:
   - If `rev` is odd, multiply `result` by `n`.
   - Square `n`.
   - Divide `rev` by 2.

## 🔹 Code

```java
class Solution {
    public int reverseExponentiation(int n) {
        int rev = 0;
        int num = n;

        // Reverse the digits of n
        while (num != 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;
            num /= 10;
        }

        // Handle negative exponents
        if (rev < 0) {
            return 1 / power(n, -rev);
        }

        // Compute n^rev using exponentiation by squaring
        int result = 1;
        while (rev > 0) {
            if (rev % 2 != 0) {
                result *= n;
            }
            n *= n;
            rev /= 2;
        }

        return result;
    }

    private int power(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            if (exponent % 2 != 0) {
                result *= base;
            }
            base *= base;
            exponent /= 2;
        }
        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `n = 12`.

| Step | `num` | `rev` | Action |
|------|-------|-------|--------|
| 1    | 12    | 0     | Initialize `rev` to 0 |
| 2    | 12    | 0     | Extract last digit 2, append to `rev` |
| 3    | 1     | 2     | Remove last digit, `num` becomes 1 |
| 4    | 1     | 2     | Extract last digit 1, append to `rev` |
| 5    | 0     | 21    | Remove last digit, `num` becomes 0 |
| 6    | 0     | 21    | Loop ends |

Now compute `12^21` using exponentiation by squaring.

| Iteration | `rev` | `n` | `result` | Action |
|-----------|-------|-----|----------|--------|
| 1         | 21    | 12  | 1        | Initialize `result` to 1 |
| 2         | 21    | 12  | 1        | `rev` is odd, multiply `result` by `n` |
| 3         | 10    | 144 | 12       | Square `n`, divide `rev` by 2 |
| 4         | 10    | 144 | 12       | `rev` is even, skip multiplication |
| 5         | 5     | 20736 | 12     | Square `n`, divide `rev` by 2 |
| 6         | 5     | 20736 | 12       | `rev` is odd, multiply `result` by `n` |
| 7         | 2     | 4299814400 | 2519424 | Square `n`, divide `rev` by 2 |
| 8         | 2     | 4299814400 | 2519424 | `rev` is even, skip multiplication |
| 9         | 1     | 1.846744e+19 | 2519424 | Square `n`, divide `rev` by 2 |
| 10        | 1     | 1.846744e+19 | 2519424 | `rev` is odd, multiply `result` by `n` |
| 11        | 0     | 3.410858e+38 | 6.2771016e+27 | Loop ends |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log rev) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Negative Numbers**: Handle negative exponents correctly.
- **Single Digit Numbers**: Ensure the algorithm works for numbers like 5.
- **Zero**: Handle cases where `n` is 0.
- **Large Numbers**: Ensure the algorithm can handle large numbers without overflow.

---

# 📚 Key Takeaways

- **Exponentiation by Squaring**: This method is more efficient than brute force for large exponents.
- **Handling Negative Exponents**: Special care is needed to handle negative exponents.
- **Edge Cases**: Always consider edge cases to ensure robustness.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle very large numbers and potential overflow issues.
- **Alternative Approaches**: Mention other methods like using logarithms for floating-point results.
- **Optimization Discussion**: Explain why exponentiation by squaring is preferred over brute force.

---

# ✅ Conclusion

The optimal approach using exponentiation by squaring is more efficient and scalable, especially for large exponents. Understanding the underlying algorithm and its optimizations is crucial for solving such problems efficiently in interviews.