```markdown
# 1 to n Without Loops

## Problem Statement

Print numbers from 1 to n without using any loops.

## Intuition

The problem requires printing numbers from 1 to n without using any loops. A recursive approach is a natural fit for this problem because recursion inherently uses the call stack to handle the iteration, effectively replacing the loop construct.

## Brute Force Approach

### Approach

The brute force approach involves using a loop to print numbers from 1 to n. This is straightforward but doesn't meet the problem's requirement of not using loops.

### Algorithm

1. Initialize a counter variable `i` to 1.
2. Use a while loop to iterate from `i` to `n`.
3. Print the current value of `i`.
4. Increment `i` by 1.
5. Repeat steps 3-4 until `i` exceeds `n`.

### Code

```java
class Solution {
    static void printTillN(int N) {
        int i = 1;
        while (i <= N) {
            System.out.print(i + " ");
            i++;
        }
    }
}
```

### Dry Run

Let's dry run the code with `N = 5`:

1. Initialize `i` to 1.
2. Enter the loop since `1 <= 5`.
3. Print `1`, increment `i` to 2.
4. `2 <= 5`, print `2`, increment `i` to 3.
5. `3 <= 5`, print `3`, increment `i` to 4.
6. `4 <= 5`, print `4`, increment `i` to 5.
7. `5 <= 5`, print `5`, increment `i` to 6.
8. Exit the loop since `6 > 5`.

Output: `1 2 3 4 5`

### Complexity

- Time Complexity: O(n) - We iterate from 1 to n once.
- Space Complexity: O(1) - We use a constant amount of extra space.

## Optimal Approach

### Approach

The optimal approach uses recursion to print numbers from 1 to n without using any loops. The idea is to use the call stack to handle the iteration, effectively replacing the loop construct.

### Why This Works

Recursion works by breaking down the problem into smaller subproblems. In this case, the function calls itself with a decremented value of `n` until it reaches the base case (`n == 0`). This ensures that the numbers are printed in ascending order.

### Algorithm

1. Define a helper function `print` that takes an integer `n` as input.
2. If `n` is 0, return from the function (base case).
3. Recursively call `print` with `n-1`.
4. Print the value of `n`.
5. The main function `printTillN` calls the helper function `print` with `N`.

### Code

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

### Dry Run

Let's dry run the code with `N = 5`:

1. Call `print(5)`.
2. `5 != 0`, call `print(4)`.
3. `4 != 0`, call `print(3)`.
4. `3 != 0`, call `print(2)`.
5. `2 != 0`, call `print(1)`.
6. `1 != 0`, call `print(0)`.
7. `0 == 0`, return from `print(0)`.
8. Print `1`, return from `print(1)`.
9. Print `2`, return from `print(2)`.
10. Print `3`, return from `print(3)`.
11. Print `4`, return from `print(4)`.
12. Print `5`, return from `print(5)`.

Output: `1 2 3 4 5`

### Complexity

- Time Complexity: O(n) - We make `n` recursive calls.
- Space Complexity: O(n) - The maximum depth of the recursion stack is `n`.

## Edge Cases

1. `N = 0`: The function should not print anything.
2. `N = 1`: The function should print `1`.
3. `N = 1000`: The function should print numbers from 1 to 1000.

## Key Takeaways

- Recursion can be used to replace loops in certain scenarios.
- The call stack in recursion handles the iteration, which can be more efficient in some cases.
- Recursion can lead to stack overflow errors for large values of `n` due to the call stack depth.

## Interview Tips

- Be familiar with both iterative and recursive approaches to solve problems.
- Understand the trade-offs between time and space complexity in different approaches.
- Practice dry running recursive functions to understand the call stack behavior.

## Conclusion

In this problem, we explored two approaches to print numbers from 1 to n without using loops. The brute force approach uses a loop, while the optimal approach uses recursion. Understanding both approaches helps in choosing the right solution based on the problem constraints and requirements.
```