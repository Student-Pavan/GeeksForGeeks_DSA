# Count Graphs with n Vertices

---

# 📝 Problem Statement

Given a number `n`, count the number of undirected graphs with `n` vertices. An undirected graph consists of a set of vertices and a set of edges connecting pairs of vertices. The order of vertices does not matter, and the order of edges does not matter.

**Constraints:**
- `1 <= n <= 20`

---

# 💡 Intuition

The problem requires counting the number of possible undirected graphs with `n` vertices. The key insight is recognizing that each edge in the graph can either exist or not exist, and the order of edges does not matter. This leads us to consider all possible combinations of edges, which is a classic combinatorial problem.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves generating all possible combinations of edges for the graph. For `n` vertices, there are `n*(n-1)/2` possible edges. Each edge can either be present or not, leading to `2^(n*(n-1)/2)` possible graphs.

## 🔹 Algorithm

1. Calculate the number of possible edges: `edges = n * (n - 1) / 2`.
2. Initialize the count of graphs to 1.
3. For each edge, multiply the count by 2 (since each edge can be either present or not).
4. Return the final count.

## 🔹 Code

```java
class Solution {
    static long count(int n) {
        long graphCount = 1;
        int edges = n * (n - 1) / 2;
        for (int i = 0; i < edges; i++) {
            graphCount *= 2;
        }
        return graphCount;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm for `n = 3`:

1. Calculate edges: `3 * 2 / 2 = 3`.
2. Initialize `graphCount = 1`.
3. Iterate over each edge:
   - `i = 0`: `graphCount = 1 * 2 = 2`.
   - `i = 1`: `graphCount = 2 * 2 = 4`.
   - `i = 2`: `graphCount = 4 * 2 = 8`.
4. Return `graphCount = 8`.

| Step | Edges | Graph Count |
|------|-------|-------------|
| 1    | 3     | 1           |
| 2    | -     | 2           |
| 3    | -     | 4           |
| 4    | -     | 8           |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach leverages the mathematical insight that the number of undirected graphs with `n` vertices is `2^(n*(n-1)/2)`. This is derived from the fact that each of the possible edges can independently be present or absent, leading to a total of `2^edges` possible graphs.

## 🔹 Why This Works

The optimal approach works because it directly applies the combinatorial principle of counting all possible subsets of edges. Since each edge is independent of the others, the total number of graphs is the product of the choices for each edge.

## 🔹 Algorithm

1. Calculate the number of possible edges: `edges = n * (n - 1) / 2`.
2. Compute the result as `2^edges`.

## 🔹 Code

```java
class Solution {
    static long count(int n) {
        int edges = n * (n - 1) / 2;
        return (long) Math.pow(2, edges);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm for `n = 3`:

1. Calculate edges: `3 * 2 / 2 = 3`.
2. Compute `2^3 = 8`.
3. Return `8`.

| Step | Edges | Result |
|------|-------|--------|
| 1    | 3     | 8      |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(1) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **n = 1**: The only possible graph is a single vertex with no edges.
- **n = 2**: There are two possible graphs - one with no edges and one with a single edge.
- **n = 20**: The maximum constraint, requiring efficient computation.

---

# 📚 Key Takeaways

- The problem can be solved using combinatorial principles.
- The optimal solution leverages mathematical insight to avoid brute force computation.
- Understanding the independence of edges is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

- Discuss the combinatorial nature of the problem.
- Consider the trade-offs between brute force and optimal approaches.
- Be prepared to explain the mathematical reasoning behind the optimal solution.

---

# ✅ Conclusion

The optimal solution is preferred due to its constant time complexity, making it highly efficient even for the upper constraint limits. The key insight is recognizing the independence of edges and applying combinatorial principles to count all possible graphs.