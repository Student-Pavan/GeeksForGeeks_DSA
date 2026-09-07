# 📌 BFS of graph

---

# 📝 Problem Statement

Given an undirected graph, perform a Breadth-First Search (BFS) starting from vertex 0. Return the order in which vertices are visited.

**Input:**
- An adjacency list representation of the graph.

**Output:**
- An array containing the vertices in the order they were visited during BFS.

**Constraints:**
- The graph may be disconnected.
- The graph may contain cycles.
- The number of vertices can be up to 10^5.

---

# 💡 Intuition

BFS explores all nodes at the present depth before moving on to nodes at the next depth level. This is achieved using a queue to keep track of nodes to visit next. The algorithm ensures that nodes are visited in the order of their distance from the starting node.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses a queue to implement BFS. It starts from the root node, visits all adjacent nodes, and continues this process level by level.

---

## 🔹 Algorithm

1. Initialize a queue with the starting node (0).
2. Mark the starting node as visited.
3. While the queue is not empty:
   - Dequeue a node from the queue.
   - Add the node to the result list.
   - Enqueue all unvisited adjacent nodes and mark them as visited.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = adj.size();
        boolean vis[] = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        vis[0] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : adj.get(node)) {
                if (!vis[neighbor]) {
                    vis[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the BFS algorithm on the following graph:

```
0 --- 1
|     |
2 --- 3
```

**Adjacency List:**
- 0: [1, 2]
- 1: [0, 3]
- 2: [0, 3]
- 3: [1, 2]

| Step | Queue | Visited | Result |
|------|-------|---------|--------|
| 1    | [0]   | [0]     | []     |
| 2    | [1, 2]| [0, 1, 2]| [0]   |
| 3    | [2, 3]| [0, 1, 2, 3]| [0, 1]|
| 4    | [3]   | [0, 1, 2, 3]| [0, 1, 2]|
| 5    | []    | [0, 1, 2, 3]| [0, 1, 2, 3]|

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach because BFS inherently visits each node and edge exactly once. The queue ensures that nodes are processed in the correct order, and the visited array prevents revisiting nodes.

---

## 🔹 Why This Works

BFS is optimal for unweighted graphs because it guarantees the shortest path from the starting node to any other node. The queue ensures that nodes are processed in the order they are discovered, which is the essence of BFS.

---

## 🔹 Algorithm

1. Initialize a queue with the starting node (0).
2. Mark the starting node as visited.
3. While the queue is not empty:
   - Dequeue a node from the queue.
   - Add the node to the result list.
   - Enqueue all unvisited adjacent nodes and mark them as visited.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = adj.size();
        boolean vis[] = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        vis[0] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : adj.get(node)) {
                if (!vis[neighbor]) {
                    vis[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal BFS algorithm on the same graph:

```
0 --- 1
|     |
2 --- 3
```

**Adjacency List:**
- 0: [1, 2]
- 1: [0, 3]
- 2: [0, 3]
- 3: [1, 2]

| Step | Queue | Visited | Result |
|------|-------|---------|--------|
| 1    | [0]   | [0]     | []     |
| 2    | [1, 2]| [0, 1, 2]| [0]   |
| 3    | [2, 3]| [0, 1, 2, 3]| [0, 1]|
| 4    | [3]   | [0, 1, 2, 3]| [0, 1, 2]|
| 5    | []    | [0, 1, 2, 3]| [0, 1, 2, 3]|

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

---

# 🔍 Edge Cases

- **Empty Graph:** The graph has no nodes.
- **Single Node:** The graph has only one node.
- **Disconnected Graph:** The graph has multiple connected components.
- **Cycle:** The graph contains a cycle.
- **Large Graph:** The graph has a large number of nodes and edges.

---

# 📚 Key Takeaways

- BFS is used to traverse or search tree or graph data structures.
- BFS explores all the neighbor nodes at the present depth prior to moving on to nodes at the next depth level.
- BFS is optimal for unweighted graphs as it guarantees the shortest path from the starting node to any other node.
- The time complexity of BFS is O(V + E), where V is the number of vertices and E is the number of edges.
- The space complexity of BFS is O(V) due to the queue and visited array.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you modify the BFS to find the shortest path in an unweighted graph?
  - How would you handle a directed graph?
  - What if the graph is weighted?
- **Common Pitfalls:**
  - Forgetting to mark nodes as visited.
  - Not handling disconnected graphs.
  - Not considering cycles in the graph.
- **Alternative Approaches:**
  - DFS (Depth-First Search) for traversal.
  - Dijkstra's algorithm for shortest path in weighted graphs.
- **Optimization Discussions:**
  - Using a queue to ensure nodes are processed in the correct order.
  - Using a visited array to prevent revisiting nodes.

---

# ✅ Conclusion

BFS is a fundamental algorithm for traversing or searching tree or graph data structures. It explores all the neighbor nodes at the present depth prior to moving on to nodes at the next depth level. The optimal BFS algorithm uses a queue to ensure nodes are processed in the correct order and a visited array to prevent revisiting nodes. The time complexity of BFS is O(V + E), and the space complexity is O(V). BFS is optimal for unweighted graphs as it guarantees the shortest path from the starting node to any other node.