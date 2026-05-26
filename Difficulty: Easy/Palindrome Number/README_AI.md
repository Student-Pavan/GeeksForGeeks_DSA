# Palindrome Number

---

# 📝 Problem Statement

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Objective**: Implement a function to check if a given integer is a palindrome.

**Input**:
- An integer `n`

**Output**:
- `true` if the integer is a palindrome, `false` otherwise

**Constraints**:
- The integer may be positive or negative
- Consider the absolute value for negative numbers
- The solution should be efficient

---

# 💡 Intuition

The key insight is that a palindrome reads the same forwards and backwards. For numbers, this means the first digit should match the last digit, the second digit should match the second last digit, and so on.

The optimal approach involves converting the number to a string and using a two-pointer technique to compare characters from both ends moving towards the center.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Convert the number to a string.
2. Compare characters from both ends moving towards the center.
3. If any pair of characters doesn't match, return false.
4. If all pairs match, return true.

---

## 🔹 Algorithm

1. Convert the absolute value of the integer to a string.
2. Initialize two pointers, `left` at the start and `right` at the end of the string.
3. While `left` is less than `right`:
   - If characters at `left` and `right` are not equal, return false.
   - Increment `left` and decrement `right`.
4. If the loop completes without mismatches, return true.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(int n) {
        // Convert the absolute value of the integer to a string
        String value = String.valueOf(Math.abs(n));

        // Initialize pointers
        int left = 0;
        int right = value.length() - 1;

        // Compare characters from both ends
        while (left < right) {
            if (value.charAt(left) != value.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input `n = 121`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 2 | Compare '1' and '1' | Match |
| 2 | 1 | 1 | Compare '2' and '2' | Match |
| 3 | 2 | 0 | Left > Right | Loop ends |

Since all characters matched, the function returns `true`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) where n is the number of digits in the integer |
| Space Complexity | O(n) for storing the string representation of the integer |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves converting the number to a string and using recursion to compare characters from both ends.

---

## 🔹 Why This Works

This approach leverages recursion to simplify the comparison logic. The base case checks if the pointers have crossed, indicating a palindrome. The recursive case checks if the current characters match and proceeds to the next pair.

---

## 🔹 Algorithm

1. Convert the absolute value of the integer to a string.
2. Define a recursive helper function that takes the string and two pointers.
3. Base case: If the left pointer is greater than or equal to the right pointer, return true.
4. Recursive case: If characters at the left and right pointers are not equal, return false. Otherwise, call the helper function with incremented left and decremented right pointers.

---

## 🔹 Code

```java
class Solution {
    public boolean isPalindrome(int n) {
        // Convert the absolute value of the integer to a string
        String value = String.valueOf(Math.abs(n));

        // Call the recursive helper function
        return checkPalin(value, 0, value.length() - 1);
    }

    private boolean checkPalin(String value, int left, int right) {
        // Base case: pointers have crossed
        if (left >= right) {
            return true;
        }

        // Check if current characters match
        if (value.charAt(left) != value.charAt(right)) {
            return false;
        }

        // Recursive case: proceed to next pair
        return checkPalin(value, left + 1, right - 1);
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input `n = 121`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 2 | Compare '1' and '1' | Match |
| 2 | 1 | 1 | Compare '2' and '2' | Match |
| 3 | 2 | 0 | Left > Right | Base case reached |

Since all characters matched, the function returns `true`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) where n is the number of digits in the integer |
| Space Complexity | O(n) for the recursion stack and string representation |

---

# 🔍 Edge Cases

- **Negative numbers**: The function should consider the absolute value.
- **Single-digit numbers**: All single-digit numbers are palindromes.
- **Numbers with leading zeros**: Not applicable since we consider absolute values.
- **Large numbers**: The solution should handle large integers efficiently.

---

# 📚 Key Takeaways

- **Two-pointer technique**: Efficiently checks for palindromes by comparing from both ends.
- **Recursion**: Simplifies the comparison logic by breaking down the problem into smaller subproblems.
- **String conversion**: Converting the number to a string simplifies character comparison.

---

# 🚀 Interview Tips

- **Follow-up questions**: Discuss handling edge cases and optimizing further.
- **Alternative approaches**: Consider converting the number to a string and using built-in functions for reversal.
- **Optimization discussions**: Explore mathematical approaches that avoid string conversion.

---

# ✅ Conclusion

The optimal approach using recursion provides a clean and efficient solution to check if a number is a palindrome. The key insight is leveraging the two-pointer technique to compare characters from both ends, ensuring optimal performance.