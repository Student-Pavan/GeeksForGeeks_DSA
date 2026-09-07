# Nodes at given distance in binary tree

---

# 📝 Problem Statement

Given a binary tree and a target node, find all nodes that are at a given distance `k` from the target node.

**Objective**: Return a list of node values that are exactly `k` distance away from the target node.

**Input**:
- Binary tree root
- Target node value
- Distance `k`

**Output**:
- List of node values at distance `k` from target

**Constraints**:
- Tree can be very large (up to 10^5 nodes)
- `k` can be up to 1000
- Node values are unique

---

# 💡 Intuition

The key insight is that nodes at distance `k` from the target can be found by:
1. Moving upwards from the target (towards root)
2. Moving downwards from the target (towards leaves)

We need to track parent pointers to enable upward movement, while standard tree traversal handles downward movement.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Find the target node in the tree
2. Perform BFS from the target node to find nodes at distance `k`
3. Since we can't move upwards in standard BFS, we need to modify the tree to include parent pointers

## 🔹 Algorithm

1. First, modify the tree to include parent pointers using BFS
2. Then perform standard BFS from the target node, tracking visited nodes
3. When distance equals `k`, collect all nodes in the queue

## 🔹 Code

```java
class Solution {
    public ArrayList<Integer> kDistanceNodes(Node root, int target, int k) {
        // First, find the target node
        Node targetNode = findTarget(root, target);
        if (targetNode == null) return new ArrayList<>();

        // Then perform BFS with parent pointers
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.offer(targetNode);
        visited.add(targetNode);

        int currentLevel = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currentLevel == k) break;

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();

                // Add left child if exists and not visited
                if (current.left != null && !visited.contains(current.left)) {
                    visited.add(current.left);
                    queue.offer(current.left);
                }

                // Add right child if exists and not visited
                if (current.right != null && !visited.contains(current.right)) {
                    visited.add(current.right);
                    queue.offer(current.right);
                }

                // Add parent if exists and not visited
                if (current.parent != null && !visited.contains(current.parent)) {
                    visited.add(current.parent);
                    queue.offer(current.parent);
                }
            }
            currentLevel++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().data);
        }
        Collections.sort(result);
        return result;
    }

    private Node findTarget(Node root, int target) {
        if (root == null) return null;
        if (root.data == target) return root;

        Node left = findTarget(root.left, target);
        if (left != null) return left;

        return findTarget(root.right, target);
    }
}
```

## 🔹 Dry Run

Let's dry run with this tree:

```
        1
       / \
      2   3
     / \
    4   5
```

Target = 2, k = 2

| Step | Current Node | Queue | Visited | Action |
|------|---------------|-------|---------|--------|
| 1    | 2             | [2]   | {2}     | Start BFS |
| 2    | 2             | [4,5,1]| {2,4,5,1}| Level 1 |
| 3    | 4             | [5,1] | {2,4,5,1}| Process 4 |
| 4    | 5             | [1]   | {2,4,5,1}| Process 5 |
| 5    | 1             | []    | {2,4,5,1}| Process 1 |

Result: [1] (only node at distance 2 from 2)

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) - We visit each node at most once |
| Space Complexity | O(N) - For the queue and visited set |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. First create a parent map using BFS
2. Then perform BFS from target node, using the parent map to move upwards
3. Track visited nodes to avoid cycles

## 🔹 Why This Works

This approach efficiently handles both upward and downward movement by:
- Using a parent map to enable upward traversal
- Performing BFS to find nodes at exact distance
- Tracking visited nodes to prevent revisiting

## 🔹 Algorithm

1. Create parent map using BFS
2. Find target node
3. Perform BFS from target node:
   - Move to left child if exists and not visited
   - Move to right child if exists and not visited
   - Move to parent if exists and not visited
4. When distance equals k, collect all nodes in queue

## 🔹 Code

```java
class Solution {
    public ArrayList<Integer> kDistanceNodes(Node root, int target, int k) {
        // Create parent map
        Map<Node, Node> parentMap = new HashMap<>();
        createParentMap(root, parentMap);

        // Find target node
        Node targetNode = findTarget(root, target);
        if (targetNode == null) return new ArrayList<>();

        // Perform BFS with parent map
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.offer(targetNode);
        visited.add(targetNode);

        int currentLevel = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currentLevel == k) break;

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();

                // Add left child if exists and not visited
                if (current.left != null && !visited.contains(current.left)) {
                    visited.add(current.left);
                    queue.offer(current.left);
                }

                // Add right child if exists and not visited
                if (current.right != null && !visited.contains(current.right)) {
                    visited.add(current.right);
                    queue.offer(current.right);
                }

                // Add parent if exists and not visited
                Node parent = parentMap.get(current);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }
            currentLevel++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().data);
        }
        Collections.sort(result);
        return result;
    }

    private void createParentMap(Node root, Map<Node, Node> parentMap) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.left != null) {
                parentMap.put(current.left, current);
                queue.offer(current.left);
            }

            if (current.right != null) {
                parentMap.put(current.right, current);
                queue.offer(current.right);
            }
        }
    }

    private Node findTarget(Node root, int target) {
        if (root == null) return null;
        if (root.data == target) return root;

        Node left = findTarget(root.left, target);
        if (left != null) return left;

        return findTarget(root.right, target);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run with the same tree:

```
        1
       / \
      2   3
     / \
    4   5
```

Target = 2, k = 2

| Step | Current Node | Queue | Visited | Action |
|------|---------------|-------|---------|--------|
| 1    | 2             | [2]   | {2}     | Start BFS |
| 2    | 2             | [4,5,1]| {2,4,5,1}| Level 1 |
| 3    | 4             | [5,1] | {2,4,5,1}| Process 4 |
| 4    | 5             | [1]   | {2,4,5,1}| Process 5 |
| 5    | 1             | []    | {2,4,5,1}| Process 1 |

Result: [1] (only node at distance 2 from 2)

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) - We visit each node at most twice |
| Space Complexity | O(N) - For the parent map, queue, and visited set |

---

# 🔍 Edge Cases

1. Empty tree
2. Target node is root
3. k = 0 (should return only target node)
4. Tree with only one node
5. Tree with duplicate values (though problem states values are unique)
6. Very large tree (performance test)
7. Target node is a leaf node
8. k is larger than tree diameter

---

# 📚 Key Takeaways

1. **Parent Pointers**: Essential for upward traversal in trees
2. **BFS for Distance**: Perfect for finding nodes at exact distance
3. **Visited Tracking**: Prevents cycles and redundant processing
4. **Tree Modification**: Sometimes necessary to enable efficient traversal
5. **Time Complexity**: O(N) is optimal for this problem

---

# 🚀 Interview Tips

1. **Clarify**: Ask if parent pointers are allowed in the tree structure
2. **Follow-up**: Discuss how to solve without modifying the tree
3. **Alternative**: Consider DFS with recursion for upward movement
4. **Optimization**: Discuss how to handle very large trees efficiently
5. **Edge Cases**: Practice with various tree structures and distances

---

# ✅ Conclusion

The optimal solution efficiently finds nodes at distance `k` by combining:
1. Parent pointer tracking
2. BFS traversal
3. Visited node management

This approach provides an O(N) time and space solution, which is optimal for this problem. The key insight is recognizing that nodes at distance `k` can be found by moving both upwards and downwards from the target node.