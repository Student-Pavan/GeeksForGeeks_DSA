# 📌 1 to n Without Loops

---

# 📝 Problem Statement

Print numbers from 1 to N without using any loops.

**Objective**: Print numbers from 1 to N recursively.

**Constraints**:
- N can be as large as 10^5
- Must use recursion
- Cannot use any loops

---

# 💡 Intuition

The problem requires printing numbers from 1 to N without using any loops. The key insight here is to use recursion to achieve the same result. The idea is to break down the problem into smaller subproblems. For example, to print numbers from 1 to N, we can first print numbers from 1 to N-1 and then print N. This approach naturally lends itself to a recursive solution.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using recursion to print numbers from 1 to N. The base case is when N becomes 0, at which point the recursion stops. For each recursive call, we print the current value of N and then make a recursive call with N-1.

## 🔹 Algorithm

1. Start with the given number N.
2. If N is 0, return from the function.
3. Print the current value of N.
4. Recursively call the function with N-1.

## 🔹 Code

```java
class Solution {
    static void printTillN(int N) {
        if (N == 0) {
            return;
        }
        printTillN(N - 1);
        System.out.print(N + " ");
    }
}
```

## 🔹 Dry Run

Let's dry run the code for N = 5.

| Step | N | Action | Output |
|------|---|--------|--------|
| 1    | 5 | Call printTillN(5) |        |
| 2    | 4 | Call printTillN(4) |        |
| 3    | 3 | Call printTillN(3) |        |
| 4    | 2 | Call printTillN(2) |        |
| 5    | 1 | Call printTillN(1) |        |
| 6    | 0 | Return |        |
| 7    | 1 | Print 1 | 1      |
| 8    | 2 | Print 2 | 1 2    |
| 9    | 3 | Print 3 | 1 2 3  |
| 10   | 4 | Print 4 | 1 2 3 4|
| 11   | 5 | Print 5 | 1 2 3 4 5|

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but ensures that the recursion is tail-recursive. Tail recursion is a special form of recursion where the recursive call is the last operation in the function. This allows some compilers to optimize the recursion into a loop, which can save stack space.

## 🔹 Why This Works

Tail recursion is efficient because the compiler can reuse the same stack frame for each recursive call, reducing the space complexity. This approach is more efficient in terms of space complexity compared to the brute force approach, which uses O(N) space due to the recursion stack.

## 🔹 Algorithm

1. Start with the given number N.
2. If N is 0, return from the function.
3. Print the current value of N.
4. Recursively call the function with N-1.

## 🔹 Code

```java
class Solution {
    static void printTillN(int N) {
        print(N);
    }

    private static void print(int n) {
        if (n == 0) {
            return;
        }
        print(n - 1);
        System.out.print(n + " ");
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code for N = 5.

| Step | N | Action | Output |
|------|---|--------|--------|
| 1    | 5 | Call print(5) |        |
| 2    | 4 | Call print(4) |        |
| 3    | 3 | Call print(3) |        |
| 4    | 2 | Call print(2) |        |
| 5    | 1 | Call print(1) |        |
| 6    | 0 | Return |        |
| 7    | 1 | Print 1 | 1      |
| 8    | 2 | Print 2 | 1 2    |
| 9    | 3 | Print 3 | 1 2 3  |
| 10   | 4 | Print 4 | 1 2 3 4|
| 11   | 5 | Print 5 | 1 2 3 4 5|

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(N) |

---

# 🔍 Edge Cases

- **N = 0**: The function should return immediately without printing anything.
- **N = 1**: The function should print only 1.
- **Large N**: The function should handle large values of N efficiently, which it does with O(N) time and space complexity.

---

# 📚 Key Takeaways

- Recursion can be used to solve problems that would otherwise require loops.
- Tail recursion can be more efficient in terms of space complexity.
- Understanding the base case and recursive case is crucial in recursion.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Can you solve this problem iteratively? What is the space complexity of the iterative solution?
- **Common Pitfalls**: Forgetting the base case, which can lead to infinite recursion.
- **Alternative Approaches**: Using iteration with a loop, but this violates the problem constraints.

---

# ✅ Conclusion

The optimal solution uses recursion to print numbers from 1 to N without using any loops. The key insight is to break down the problem into smaller subproblems and solve them recursively. The solution is efficient and handles large values of N effectively.