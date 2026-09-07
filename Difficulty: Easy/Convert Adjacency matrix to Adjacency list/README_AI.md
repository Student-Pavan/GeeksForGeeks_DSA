# Convert Adjacency Matrix to Adjacency List

---

# 📝 Problem Statement

Given an adjacency matrix representation of a graph, convert it into an adjacency list representation.

**Objective:**
Convert the given adjacency matrix into an adjacency list.

**Input:**
- A 2D array `mat[][]` representing the adjacency matrix of a graph.

**Output:**
- An adjacency list representation of the graph.

**Constraints:**
- The graph can be directed or undirected.
- The adjacency matrix is square (N x N).
- The adjacency list should be represented as an array of lists.

---

# 💡 Intuition

The adjacency matrix representation of a graph uses a 2D array where each cell `mat[i][j]` indicates whether there is an edge from node `i` to node `j`. The adjacency list representation uses an array of lists where each index `i` contains a list of nodes that are adjacent to node `i`.

The key insight is that for each node `i`, we can iterate through the row `i` of the adjacency matrix and collect all nodes `j` where `mat[i][j]` is 1 (indicating an edge from `i` to `j`).

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through each cell of the adjacency matrix and checking if there is an edge between nodes `i` and `j`. If there is an edge, we add `j` to the adjacency list of `i`.

---

## 🔹 Algorithm

1. Initialize an empty adjacency list.
2. For each node `i` from `0` to `n-1`:
   - Initialize an empty list for node `i`.
   - For each node `j` from `0` to `n-1`:
     - If `mat[i][j]` is 1, add `j` to the list of node `i`.
   - Add the list of node `i` to the adjacency list.
3. Return the adjacency list.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> matToAdj(int[][] mat) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        int n = mat.length;

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    list.add(j);
                }
            }
            adjList.add(list);
        }

        return adjList;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the following adjacency matrix:

```
[
  [0, 1, 0, 0],
  [0, 0, 1, 0],
  [0, 0, 0, 1],
  [0, 0, 0, 0]
]
```

| Iteration | i | j | mat[i][j] | Action | Adjacency List |
|-----------|---|---|-----------|--------|----------------|
| 1         | 0 | 0 | 0         | Skip   | []             |
| 2         | 0 | 1 | 1         | Add 1  | [1]            |
| 3         | 0 | 2 | 0         | Skip   | [1]            |
| 4         | 0 | 3 | 0         | Skip   | [1]            |
| 5         | 1 | 0 | 0         | Skip   | [1]            |
| 6         | 1 | 1 | 0         | Skip   | [1]            |
| 7         | 1 | 2 | 1         | Add 2  | [1, 2]         |
| 8         | 1 | 3 | 0         | Skip   | [1, 2]         |
| 9         | 2 | 0 | 0         | Skip   | [1, 2]         |
| 10        | 2 | 1 | 0         | Skip   | [1, 2]         |
| 11        | 2 | 2 | 0         | Skip   | [1, 2]         |
| 12        | 2 | 3 | 1         | Add 3  | [1, 2, 3]      |
| 13        | 3 | 0 | 0         | Skip   | [1, 2, 3]      |
| 14        | 3 | 1 | 0         | Skip   | [1, 2, 3]      |
| 15        | 3 | 2 | 0         | Skip   | [1, 2, 3]      |
| 16        | 3 | 3 | 0         | Skip   | [1, 2, 3]      |

Final adjacency list:
```
[
  [1],
  [2],
  [3],
  []
]
```

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N^2) where N is the number of nodes |
| Space Complexity | O(N^2) for the adjacency list |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach because we need to check every cell in the adjacency matrix to build the adjacency list. There is no more efficient way to convert an adjacency matrix to an adjacency list without missing any edges.

---

## 🔹 Why This Works

The optimal approach works because it systematically checks every possible edge in the adjacency matrix. By iterating through each cell, we ensure that we do not miss any edges and correctly build the adjacency list.

---

## 🔹 Algorithm

1. Initialize an empty adjacency list.
2. For each node `i` from `0` to `n-1`:
   - Initialize an empty list for node `i`.
   - For each node `j` from `0` to `n-1`:
     - If `mat[i][j]` is 1, add `j` to the list of node `i`.
   - Add the list of node `i` to the adjacency list.
3. Return the adjacency list.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> matToAdj(int[][] mat) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        int n = mat.length;

        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    list.add(j);
                }
            }
            adjList.add(list);
        }

        return adjList;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same adjacency matrix:

```
[
  [0, 1, 0, 0],
  [0, 0, 1, 0],
  [0, 0, 0, 1],
  [0, 0, 0, 0]
]
```

| Iteration | i | j | mat[i][j] | Action | Adjacency List |
|-----------|---|---|-----------|--------|----------------|
| 1         | 0 | 0 | 0         | Skip   | []             |
| 2         | 0 | 1 | 1         | Add 1  | [1]            |
| 3         | 0 | 2 | 0         | Skip   | [1]            |
| 4         | 0 | 3 | 0         | Skip   | [1]            |
| 5         | 1 | 0 | 0         | Skip   | [1]            |
| 6         | 1 | 1 | 0         | Skip   | [1]            |
| 7         | 1 | 2 | 1         | Add 2  | [1, 2]         |
| 8         | 1 | 3 | 0         | Skip   | [1, 2]         |
| 9         | 2 | 0 | 0         | Skip   | [1, 2]         |
| 10        | 2 | 1 | 0         | Skip   | [1, 2]         |
| 11        | 2 | 2 | 0         | Skip   | [1, 2]         |
| 12        | 2 | 3 | 1         | Add 3  | [1, 2, 3]      |
| 13        | 3 | 0 | 0         | Skip   | [1, 2, 3]      |
| 14        | 3 | 1 | 0         | Skip   | [1, 2, 3]      |
| 15        | 3 | 2 | 0         | Skip   | [1, 2, 3]      |
| 16        | 3 | 3 | 0         | Skip   | [1, 2, 3]      |

Final adjacency list:
```
[
  [1],
  [2],
  [3],
  []
]
```

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N^2) where N is the number of nodes |
| Space Complexity | O(N^2) for the adjacency list |

---

# 🔍 Edge Cases

1. **Empty Graph:** The adjacency matrix is empty.
2. **Single Node Graph:** The adjacency matrix has only one node.
3. **Dense Graph:** The adjacency matrix is almost entirely filled with 1s.
4. **Sparse Graph:** The adjacency matrix is mostly filled with 0s.
5. **Directed Graph:** The adjacency matrix represents a directed graph.
6. **Undirected Graph:** The adjacency matrix represents an undirected graph.

---

# 📚 Key Takeaways

1. The adjacency matrix and adjacency list are two common ways to represent graphs.
2. Converting an adjacency matrix to an adjacency list involves iterating through each cell of the matrix and building the adjacency list accordingly.
3. The time and space complexity of the conversion is O(N^2) where N is the number of nodes.
4. Understanding the conversion between different graph representations is crucial for solving graph-related problems.

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - Can the solution be optimized further?
   - How would you handle weighted graphs?
   - What if the graph is very large and does not fit in memory?

2. **Common Pitfalls:**
   - Forgetting to handle the case where the adjacency matrix is empty.
   - Incorrectly handling directed vs. undirected graphs.
   - Not considering the space complexity of the adjacency list.

3. **Alternative Approaches:**
   - Using a hash map to store the adjacency list.
   - Using a list of sets to store the adjacency list.

4. **Optimization Discussions:**
   - Discussing the trade-offs between the adjacency matrix and adjacency list representations.
   - Discussing the time and space complexity of different graph algorithms based on the graph representation.

---

# ✅ Conclusion

The optimal solution for converting an adjacency matrix to an adjacency list involves iterating through each cell of the matrix and building the adjacency list accordingly. The time and space complexity of the solution is O(N^2) where N is the number of nodes. Understanding the conversion between different graph representations is crucial for solving graph-related problems.