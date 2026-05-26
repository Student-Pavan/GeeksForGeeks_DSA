# Unique Paths in a Grid

---

# 📝 Problem Statement

Given a grid of size `n x m`, where some cells are blocked (represented by `1`), find the number of unique paths from the top-left corner to the bottom-right corner. You can only move right or down.

**Constraints:**
- `1 <= n, m <= 100`
- `grid[i][j]` is either `0` (unblocked) or `1` (blocked)

---

# 💡 Intuition

The problem requires counting all possible paths from start to end in a grid with obstacles. The key insight is that we can use dynamic programming to avoid recalculating the same subproblems repeatedly. The optimal approach uses memoization to store intermediate results, significantly improving efficiency.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to explore all possible paths by moving right or down at each step. If we encounter a blocked cell or go out of bounds, we return 0. If we reach the destination, we return 1. Otherwise, we recursively explore both directions and sum the results.

---

## 🔹 Algorithm

1. If the current cell is out of bounds or blocked, return 0.
2. If we've reached the destination, return 1.
3. Recursively calculate paths by moving right and down, then sum the results.

---

## 🔹 Code

```java
class Solution {
    public int uniquePaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // If start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) {
            return 0;
        }

        return solve(0, 0, grid, n, m);
    }

    public int solve(int i, int j, int[][] grid, int n, int m) {
        // Out of bounds or blocked cell
        if (i >= n || j >= m || grid[i][j] == 1) {
            return 0;
        }

        // Reached destination
        if (i == n - 1 && j == m - 1) {
            return 1;
        }

        // Move right + move down
        return solve(i + 1, j, grid, n, m) + solve(i, j + 1, grid, n, m);
    }
}
```

---

## 🔹 Dry Run

Let's consider a sample grid:

```
[
  [0, 0, 0],
  [0, 1, 0],
  [0, 0, 0]
]
```

| Step | i | j | Action | Result |
|------|---|---|--------|--------|
| 1    | 0 | 0 | Start at (0,0) | - |
| 2    | 1 | 0 | Move down | - |
| 3    | 2 | 0 | Move down | - |
| 4    | 2 | 1 | Move right | - |
| 5    | 2 | 2 | Reached destination | 1 |
| 6    | 2 | 1 | Backtrack | - |
| 7    | 1 | 1 | Blocked cell | 0 |
| 8    | 1 | 0 | Backtrack | - |
| 9    | 0 | 1 | Move right | - |
| 10   | 1 | 1 | Blocked cell | 0 |
| 11   | 0 | 1 | Backtrack | - |
| 12   | 0 | 2 | Move right | - |
| 13   | 1 | 2 | Move down | - |
| 14   | 2 | 2 | Reached destination | 1 |
| 15   | 1 | 2 | Backtrack | - |
| 16   | 0 | 2 | Backtrack | - |

Total paths: 2

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(2^(n+m)) |
| Space Complexity | O(n+m) |

The time complexity is exponential because each step has two choices, and the recursion depth is `n + m`. The space complexity is linear due to the recursion stack.

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses memoization to store the number of paths from each cell to the destination. This avoids recalculating the same subproblems repeatedly, significantly improving efficiency.

---

## 🔹 Why This Works

Memoization stores intermediate results, so each cell's value is computed only once. This reduces the time complexity from exponential to polynomial.

---

## 🔹 Algorithm

1. Initialize a memoization table with `-1` values.
2. If the current cell is out of bounds or blocked, return 0.
3. If we've reached the destination, return 1.
4. If the result for the current cell is already computed, return it from the memoization table.
5. Otherwise, compute the result by recursively exploring both directions, store it in the memoization table, and return the result.

---

## 🔹 Code

```java
class Solution {
    public int uniquePaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int dp[][] = new int[n][m];

        // If start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) {
            return 0;
        }

        for(int[] rows : dp){
            Arrays.fill(rows,-1);

        }
        return solve(0, 0, grid,dp ,n, m);
    }

    public int solve(int i, int j, int[][] grid, int[][] dp,int n, int m) {

        // Out of bounds or blocked cell
        if (i >= n || j >= m || grid[i][j] == 1) {
            return 0;
        }

        // Reached destination
        if (i == n - 1 && j == m - 1) {
            return 1;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        // Move right + move down
        return dp[i][j] = solve(i + 1, j, grid,dp, n, m) +
               solve(i, j + 1, grid,dp, n, m);
    }
}
```

---

## 🔹 Detailed Dry Run

Using the same grid:

```
[
  [0, 0, 0],
  [0, 1, 0],
  [0, 0, 0]
]
```

| Step | i | j | Action | dp[i][j] | Result |
|------|---|---|--------|----------|--------|
| 1    | 0 | 0 | Start at (0,0) | - | - |
| 2    | 1 | 0 | Move down | - | - |
| 3    | 2 | 0 | Move down | - | - |
| 4    | 2 | 1 | Move right | - | - |
| 5    | 2 | 2 | Reached destination | dp[2][2] = 1 | 1 |
| 6    | 2 | 1 | Backtrack | dp[2][1] = 1 | - |
| 7    | 1 | 1 | Blocked cell | dp[1][1] = 0 | 0 |
| 8    | 1 | 0 | Backtrack | dp[1][0] = 1 | - |
| 9    | 0 | 1 | Move right | - | - |
| 10   | 1 | 1 | Blocked cell | dp[1][1] = 0 | 0 |
| 11   | 0 | 1 | Backtrack | dp[0][1] = 0 | - |
| 12   | 0 | 2 | Move right | - | - |
| 13   | 1 | 2 | Move down | - | - |
| 14   | 2 | 2 | Reached destination | dp[2][2] = 1 | 1 |
| 15   | 1 | 2 | Backtrack | dp[1][2] = 1 | - |
| 16   | 0 | 2 | Backtrack | dp[0][2] = 1 | - |

Total paths: 2

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n*m) |
| Space Complexity | O(n*m) |

The time complexity is polynomial because each cell is visited only once. The space complexity is quadratic due to the memoization table.

---

# 🔍 Edge Cases

- **Empty grid**: Handle gracefully.
- **Single cell grid**: Return 1 if unblocked.
- **Fully blocked grid**: Return 0.
- **Start or end blocked**: Return 0 immediately.
- **Large grid**: Ensure efficient computation.

---

# 📚 Key Takeaways

- **Dynamic Programming**: Memoization is crucial for optimizing recursive solutions.
- **Grid Traversal**: Understanding how to navigate grids efficiently is essential.
- **Base Cases**: Properly handling base cases prevents infinite recursion.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle larger grids or additional constraints.
- **Alternative Approaches**: Consider iterative DP solutions or BFS.
- **Optimization**: Emphasize the importance of memoization.

---

# ✅ Conclusion

The optimal approach using memoization significantly improves the brute force solution by avoiding redundant calculations. Understanding the problem's structure and applying dynamic programming principles is key to solving such grid traversal problems efficiently.