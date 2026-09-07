# 📌 Calculator

---

# 📝 Problem Statement

Implement a basic calculator that can perform addition, subtraction, and multiplication operations based on a given operator.

**Objective**:
- Create a function that takes two integers and an operator code.
- Perform the corresponding arithmetic operation.
- Print the result or an error message for invalid input.

**Input**:
- Two integers `a` and `b`
- An integer `oper` representing the operation (1 for addition, 2 for subtraction, 3 for multiplication)

**Output**:
- The result of the arithmetic operation if the operator is valid
- "Invalid Input" if the operator is not 1, 2, or 3

**Constraints**:
- The input values can be any integers
- The operator must be an integer between 1 and 3 inclusive

---

# 💡 Intuition

The problem requires implementing a simple calculator that performs basic arithmetic operations. The key insight is to use conditional statements to determine which operation to perform based on the operator value. This approach is straightforward and efficient for the given problem constraints.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a series of conditional statements to check the operator value and perform the corresponding arithmetic operation. This approach is simple and easy to understand, making it suitable for beginners.

---

## 🔹 Algorithm

1. Initialize a variable `value` to store the result.
2. Check the value of `oper`:
   - If `oper` is 1, set `value` to `a + b`.
   - If `oper` is 2, set `value` to `a - b`.
   - If `oper` is 3, set `value` to `a * b`.
   - If `oper` is none of the above, print "Invalid Input" and return.
3. Print the value of `value`.

---

## 🔹 Code

```java
class Solution {
    public void calculate(int a, int b, int oper) {
        int value = 0;

        if (oper == 1) {
            value = a + b;
        }
        else if (oper == 2) {
            value = a - b;
        }
        else if (oper == 3) {
            value = a * b;
        }
        else {
            System.out.print("Invalid Input");
            return;
        }

        System.out.print(value);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the code with `a = 5`, `b = 3`, and `oper = 2`.

| Step | Oper | Action | Value | Output |
|---|---|---|---|---|
| 1 | 2 | Check if oper is 1 | - | - |
| 2 | 2 | Check if oper is 2 | - | - |
| 3 | 2 | Set value to a - b (5 - 3) | 2 | - |
| 4 | 2 | Print value | - | 2 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(1) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses a switch-case statement instead of multiple if-else statements. This approach is more concise and efficient, especially when dealing with multiple conditions.

---

## 🔹 Why This Works

The switch-case statement is more efficient than multiple if-else statements because it directly jumps to the correct case based on the value of the operator, avoiding unnecessary checks. This approach is also more readable and maintainable.

---

## 🔹 Algorithm

1. Initialize a variable `value` to store the result.
2. Use a switch-case statement to check the value of `oper`:
   - Case 1: Set `value` to `a + b`.
   - Case 2: Set `value` to `a - b`.
   - Case 3: Set `value` to `a * b`.
   - Default: Print "Invalid Input" and return.
3. Print the value of `value`.

---

## 🔹 Code

```java
class Solution {
    public void calculate(int a, int b, int oper) {
        int value = 0;

        switch (oper) {
            case 1:
                value = a + b;
                break;
            case 2:
                value = a - b;
                break;
            case 3:
                value = a * b;
                break;
            default:
                System.out.print("Invalid Input");
                return;
        }

        System.out.print(value);
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the code with `a = 5`, `b = 3`, and `oper = 3`.

| Step | Oper | Action | Value | Output |
|---|---|---|---|---|
| 1 | 3 | Jump to case 3 | - | - |
| 2 | 3 | Set value to a * b (5 * 3) | 15 | - |
| 3 | 3 | Print value | - | 15 |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(1) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- `oper` is 1: Addition operation.
- `oper` is 2: Subtraction operation.
- `oper` is 3: Multiplication operation.
- `oper` is not 1, 2, or 3: Invalid input.
- `a` and `b` are very large integers.
- `a` and `b` are negative integers.

---

# 📚 Key Takeaways

- Use conditional statements or switch-case to handle multiple conditions efficiently.
- Always handle invalid input cases to ensure robustness.
- The time and space complexity are constant for this problem.

---

# 🚀 Interview Tips

- Discuss the difference between if-else and switch-case statements.
- Ask about handling additional operations in the future.
- Consider using enums for better readability and maintainability.

---

# ✅ Conclusion

The optimal approach using a switch-case statement is more efficient and concise than the brute force approach. It directly handles the operator value and performs the corresponding arithmetic operation, making it suitable for interview scenarios.