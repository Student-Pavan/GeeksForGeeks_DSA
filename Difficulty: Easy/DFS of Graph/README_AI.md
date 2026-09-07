# DFS of Graph

---

# 📝 Problem Statement

Given an undirected graph with `V` vertices and `E` edges, the task is to perform a Depth First Search (DFS) traversal of the graph starting from vertex `0`.

**Objective**: Return the DFS traversal sequence as an array.

**Constraints**:
- `1 ≤ V ≤ 10^5`
- `0 ≤ E ≤ (V*(V-1))/2`
- The graph may be disconnected

---

# 💡 Intuition

The key insight is that DFS explores as far as possible along each branch before backtracking. This is naturally implemented using recursion or a stack. The algorithm maintains a visited array to avoid cycles and ensure each node is processed only once.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach uses recursion to implement DFS. For each node, we visit it, mark it as visited, and recursively visit all its unvisited neighbors.

---

## 🔹 Algorithm

1. Initialize an empty result array and a visited array of size `V` with all values set to `false`.
2. Start DFS from node `0`.
3. For each node:
   - Mark it as visited
   - Add it to the result array
   - Recursively visit all unvisited neighbors

---

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = adj.size();
        boolean vis[] = new boolean[n];

        dfsTraversal(0, adj, vis, ans);
        return ans;
    }

    public void dfsTraversal(int node, ArrayList<ArrayList<Integer>> adj, boolean vis[], ArrayList<Integer> ans) {
        vis[node] = true;
        ans.add(node);

        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                dfsTraversal(neighbour, adj, vis, ans);
            }
        }
    }
}
```

---

## 🔹 Dry Run

Let's consider a simple graph with 5 nodes:

```
0 --- 1 --- 2
 \   /
   3
```

Adjacency list representation:
```
0: [1, 3]
1: [0, 2, 3]
2: [1]
3: [0, 1]
```

| Step | Current Node | Visited | Action | Result |
|------|--------------|---------|--------|--------|
| 1    | 0            | [F, F, F, F] | Visit 0 | [0] |
| 2    | 1            | [T, F, F, F] | Visit 1 | [0, 1] |
| 3    | 2            | [T, T, F, F] | Visit 2 | [0, 1, 2] |
| 4    | 3            | [T, T, T, F] | Visit 3 | [0, 1, 2, 3] |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses an iterative DFS with a stack to avoid recursion overhead. This is more efficient for large graphs and avoids potential stack overflow issues.

---

## 🔹 Why This Works

The iterative approach uses a stack to simulate the call stack of the recursive solution. This maintains the same DFS order while being more memory efficient for large graphs.

---

## 🔹 Algorithm

1. Initialize an empty result array and a visited array of size `V` with all values set to `false`.
2. Create a stack and push node `0` onto it.
3. While the stack is not empty:
   - Pop a node from the stack
   - If it hasn't been visited:
     - Mark it as visited
     - Add it to the result array
     - Push all its unvisited neighbors onto the stack in reverse order

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Stack;

class Solution {
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = adj.size();
        boolean vis[] = new boolean[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!vis[node]) {
                vis[node] = true;
                ans.add(node);

                // Push neighbors in reverse order to maintain DFS order
                for (int i = adj.get(node).size() - 1; i >= 0; i--) {
                    int neighbour = adj.get(node).get(i);
                    if (!vis[neighbour]) {
                        stack.push(neighbour);
                    }
                }
            }
        }

        return ans;
    }
}
```

---

## 🔹 Detailed Dry Run

Using the same graph as before:

| Step | Stack | Current Node | Visited | Action | Result |
|------|-------|--------------|---------|--------|--------|
| 1    | [0]   | 0            | [F, F, F, F] | Visit 0 | [0] |
| 2    | [3, 1]| 1            | [T, F, F, F] | Visit 1 | [0, 1] |
| 3    | [3, 2]| 2            | [T, T, F, F] | Visit 2 | [0, 1, 2] |
| 4    | [3]   | 3            | [T, T, T, F] | Visit 3 | [0, 1, 2, 3] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(V + E) |
| Space Complexity | O(V) |

---

# 🔍 Edge Cases

1. **Empty Graph**: Graph with no nodes
2. **Single Node**: Graph with only one node
3. **Disconnected Graph**: Graph with multiple components
4. **Large Graph**: Graph with maximum number of nodes and edges
5. **Cycle Detection**: Graph containing cycles

---

# 📚 Key Takeaways

1. DFS explores depth-first, using recursion or a stack
2. The iterative approach avoids recursion overhead
3. Visited array prevents cycles and redundant processing
4. Time complexity is linear with respect to nodes and edges
5. Space complexity is linear with respect to nodes

---

# 🚀 Interview Tips

1. **Follow-up Questions**:
   - How would you modify this for a directed graph?
   - How would you detect cycles using DFS?
   - How would you implement DFS without recursion?

2. **Common Pitfalls**:
   - Forgetting to mark nodes as visited
   - Not handling disconnected graphs
   - Incorrect neighbor processing order

3. **Alternative Approaches**:
   - BFS (Breadth First Search)
   - Topological Sorting

4. **Optimization Discussions**:
   - The iterative approach is generally preferred for large graphs
   - The recursive approach is simpler but may cause stack overflow

---

# ✅ Conclusion

The optimal DFS implementation using an iterative approach with a stack is preferred for large graphs due to its memory efficiency and avoidance of recursion overhead. The key insight is maintaining the DFS order while efficiently tracking visited nodes. The time and space complexity remain linear with respect to the number of nodes and edges, making this solution suitable for large-scale graph traversal problems.