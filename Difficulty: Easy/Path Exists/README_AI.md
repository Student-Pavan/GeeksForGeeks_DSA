# 📌 Path Exists

---

# 📝 Problem Statement

Given a directed graph, determine if there exists a path from a source node to a destination node.

**Constraints:**
- The graph may be disconnected.
- The graph may contain cycles.
- The number of nodes `V` can be up to 10^5.
- The number of edges `E` can be up to 10^5.

---

# 💡 Intuition

The problem requires checking if there's a path between two nodes in a graph. The key insight is that we can use either Depth-First Search (DFS) or Breadth-First Search (BFS) to explore the graph and determine if the destination node is reachable from the source node. DFS is chosen here as it's straightforward to implement recursively.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using DFS to explore all possible paths from the source node to the destination node. We recursively visit all adjacent nodes and check if any path leads to the destination.

---

## 🔹 Algorithm

1. **Build the adjacency list** for the graph.
2. **Initialize a visited array** to keep track of visited nodes.
3. **Perform DFS** starting from the source node:
   - If the current node is the destination, return `true`.
   - Mark the current node as visited.
   - Recursively visit all adjacent nodes that haven't been visited yet.
   - If any recursive call returns `true`, return `true`.
4. If no path is found after exploring all nodes, return `false`.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public boolean checkPath(int V, int[][] edges, int src, int dest) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        return dfs(src, dest, visited, adj);
    }

    private boolean dfs(int current, int dest, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        if (current == dest) {
            return true;
        }
        visited[current] = true;
        for (int neighbor : adj.get(current)) {
            if (!visited[neighbor] && dfs(neighbor, dest, visited, adj)) {
                return true;
            }
        }
        return false;
    }
}
```

---

## 🔹 Dry Run

Let's consider a graph with 4 nodes and the following edges: `[[0,1], [1,2], [2,3], [3,0]]`. We want to check if there's a path from node `0` to node `3`.

| Step | Current Node | Visited Nodes | Action | Result |
|------|---------------|----------------|--------|--------|
| 1    | 0             | [0]            | Visit 0 | -      |
| 2    | 1             | [0, 1]         | Visit 1 | -      |
| 3    | 2             | [0, 1, 2]      | Visit 2 | -      |
| 4    | 3             | [0, 1, 2, 3]   | Visit 3 | true   |

In this dry run, the DFS starts at node `0`, visits node `1`, then node `2`, and finally node `3`, which is the destination. Hence, the function returns `true`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is to use BFS to explore the graph level by level. BFS is suitable here because it explores all nodes at the present depth before moving on to nodes at the next depth level, which can be more efficient in some cases, especially for unweighted graphs.

---

## 🔹 Why This Works

BFS ensures that we explore the graph in a level-order manner, which can be more efficient for finding the shortest path in unweighted graphs. However, for the purpose of checking path existence, both DFS and BFS have the same time complexity, but BFS might be more intuitive for some interviewers.

---

## 🔹 Algorithm

1. **Build the adjacency list** for the graph.
2. **Initialize a visited array** to keep track of visited nodes.
3. **Perform BFS** starting from the source node:
   - If the current node is the destination, return `true`.
   - Mark the current node as visited.
   - Enqueue all adjacent nodes that haven't been visited yet.
   - Dequeue the next node and repeat the process.
4. If the queue is exhausted without finding the destination, return `false`.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean checkPath(int V, int[][] edges, int src, int dest) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == dest) {
                return true;
            }
            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's consider the same graph with 4 nodes and the following edges: `[[0,1], [1,2], [2,3], [3,0]]`. We want to check if there's a path from node `0` to node `3`.

| Step | Queue | Visited Nodes | Action | Result |
|------|-------|----------------|--------|--------|
| 1    | [0]   | [0]            | Dequeue 0 | -      |
| 2    | [1, 3]| [0, 1, 3]      | Dequeue 1 | -      |
| 3    | [2, 3]| [0, 1, 2, 3]   | Dequeue 2 | -      |
| 4    | [3]   | [0, 1, 2, 3]   | Dequeue 3 | true   |

In this dry run, the BFS starts at node `0`, enqueues its neighbors `1` and `3`, then processes node `1`, enqueues its neighbor `2`, processes node `2`, and finally processes node `3`, which is the destination. Hence, the function returns `true`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

---

# 🔍 Edge Cases

- **Empty Graph**: No edges, only one node. The function should return `false` if the source and destination are different.
- **Single Node**: Only one node, source and destination are the same. The function should return `true`.
- **Disconnected Graph**: No path exists between the source and destination nodes. The function should return `false`.
- **Large Graph**: The graph has a large number of nodes and edges. The function should handle it efficiently.
- **Cycle**: The graph contains a cycle. The function should not get stuck in an infinite loop.

---

# 📚 Key Takeaways

- **Graph Traversal**: Understanding DFS and BFS is crucial for solving graph-related problems.
- **Adjacency List**: Building an adjacency list is a common technique for representing graphs.
- **Visited Array**: Keeping track of visited nodes is essential to avoid cycles and redundant processing.
- **Path Existence**: Both DFS and BFS can be used to check for path existence, but BFS is often preferred for level-order traversal.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you modify the solution to find the shortest path?
  - How would you handle a weighted graph?
  - What if the graph is directed?
- **Common Pitfalls**:
  - Forgetting to mark nodes as visited, leading to infinite loops.
  - Not handling disconnected graphs properly.
- **Alternative Approaches**:
  - Using Union-Find (Disjoint Set Union) for path existence in undirected graphs.
  - Using Topological Sorting for directed acyclic graphs (DAGs).

---

# ✅ Conclusion

Both the brute force and optimal approaches have the same time and space complexity, but BFS might be preferred for its level-order traversal property. The key takeaway is understanding how to traverse a graph and check for path existence efficiently.