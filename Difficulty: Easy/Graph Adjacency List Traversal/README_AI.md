# 📌 Graph Adjacency List Traversal

---

# 📝 Problem Statement

Given a graph with `V` vertices and `E` edges, implement a function to represent this graph using an adjacency list. The adjacency list should be an array of lists where each index represents a vertex and contains a list of vertices it is connected to.

**Objective**: Convert the given edge list representation of a graph into its adjacency list representation.

**Input**:
- `V`: Number of vertices in the graph.
- `edges`: A list of edges where each edge is represented as a pair `[u, v]` indicating a connection between vertex `u` and vertex `v`.

**Output**: An adjacency list representation of the graph.

**Constraints**:
- 1 ≤ V ≤ 10^5
- 1 ≤ E ≤ 10^5
- 0 ≤ u, v < V
- The graph is undirected (edges are bidirectional)

---

# 💡 Intuition

The adjacency list is a fundamental way to represent graphs, especially for sparse graphs. The key insight is that we can efficiently store connections for each vertex by using a list of lists. For an undirected graph, each edge `[u, v]` needs to be added to both `u`'s and `v`'s adjacency lists.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Initialize an adjacency list with `V` empty lists.
2. Iterate through each edge in the input.
3. For each edge `[u, v]`, add `v` to `u`'s adjacency list and `u` to `v`'s adjacency list.

## 🔹 Algorithm

1. Create a list of lists `adjList` with size `V`.
2. For each edge `[u, v]` in `edges`:
   - Append `v` to `adjList[u]`
   - Append `u` to `adjList[v]`
3. Return `adjList`

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> printGraph(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list with empty lists for each vertex
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with `V = 4` and `edges = [[0, 1], [1, 2], [2, 3], [3, 0]]`.

| Step | Action | Adjacency List State |
|------|--------|-----------------------|
| 1    | Initialize adjacency list with 4 empty lists | `[[], [], [], []]` |
| 2    | Process edge [0, 1] | `[[1], [0], [], []]` |
| 3    | Process edge [1, 2] | `[[1], [0, 2], [1], []]` |
| 4    | Process edge [2, 3] | `[[1], [0, 2], [1, 3], [2]]` |
| 5    | Process edge [3, 0] | `[[1, 3], [0, 2], [1, 3], [2, 0]]` |

Final adjacency list: `[[1, 3], [0, 2], [1, 3], [2, 0]]`

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V + E) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The brute force approach is already optimal for this problem. We initialize the adjacency list in O(V) time and process each edge exactly once in O(E) time, resulting in an overall O(V + E) time complexity. The space complexity is O(V + E) to store the adjacency list.

## 🔹 Why This Works

This approach efficiently represents the graph using an adjacency list, which is optimal for most graph traversal algorithms. The adjacency list allows quick access to all neighbors of any given vertex, making it suitable for algorithms like BFS and DFS.

## 🔹 Algorithm

The algorithm is identical to the brute force approach:

1. Initialize an adjacency list with `V` empty lists.
2. For each edge `[u, v]` in `edges`:
   - Append `v` to `adjList[u]`
   - Append `u` to `adjList[v]`
3. Return `adjList`

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> printGraph(int V, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list with empty lists for each vertex
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate the adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return adjList;
    }
}
```

## 🔹 Detailed Dry Run

The dry run for the optimal approach is identical to the brute force approach, as shown above.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V + E) |

---

# 🔍 Edge Cases

1. **Empty Graph**: `V = 0`, `edges = []` → Returns an empty list.
2. **Single Vertex**: `V = 1`, `edges = []` → Returns `[[], [], [], []]`.
3. **Disconnected Graph**: Graph with no edges → Returns a list of empty lists.
4. **Fully Connected Graph**: Every vertex connected to every other vertex → Returns a list where each sublist contains all other vertices.
5. **Large Input**: `V = 10^5`, `E = 10^5` → Efficiently handles large inputs.

---

# 📚 Key Takeaways

1. **Adjacency List Representation**: Essential for graph algorithms, especially for sparse graphs.
2. **Efficiency**: The optimal approach runs in linear time relative to the number of vertices and edges.
3. **Undirected Graph Handling**: Each edge is added to both vertices' adjacency lists.
4. **Initialization**: Proper initialization of the adjacency list is crucial to avoid index errors.

---

# 🚀 Interview Tips

1. **Clarify Graph Type**: Ask if the graph is directed or undirected. The solution assumes an undirected graph.
2. **Edge Cases**: Consider edge cases like empty graphs or disconnected components.
3. **Follow-up Questions**:
   - How would you modify this for a directed graph?
   - How would you handle weighted edges?
4. **Alternative Approaches**: Discuss the trade-offs between adjacency list and adjacency matrix representations.

---

# ✅ Conclusion

The optimal approach efficiently converts an edge list to an adjacency list representation, which is crucial for many graph algorithms. The solution handles undirected graphs and runs in linear time, making it suitable for large graphs. Understanding this representation is fundamental for solving graph-related problems in interviews.