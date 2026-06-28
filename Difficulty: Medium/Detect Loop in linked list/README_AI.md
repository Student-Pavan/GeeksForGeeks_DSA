# 📌 Detect Loop in linked list

---

# 📝 Problem Statement

Given a linked list, determine if it contains a loop. A loop exists if any node in the linked list can be reached again by continuously following the `next` pointer.

**Objective**: Implement a function to detect whether a given linked list contains a loop.

**Input**:
- A linked list represented by a `Node` class with `data` and `next` attributes.

**Output**:
- Return `true` if a loop is detected, otherwise return `false`.

**Constraints**:
- The number of nodes in the linked list can be up to 10,000.
- The values of the nodes can be any integer.

---

# 💡 Intuition

The problem requires detecting a loop in a linked list. The key insight is that if there's a loop, traversing the list will eventually lead to revisiting a node. The optimal approach uses Floyd's Tortoise and Hare algorithm, which uses two pointers moving at different speeds to detect a loop efficiently.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a hash set to keep track of visited nodes. As we traverse the linked list, we check if the current node has been visited before. If it has, a loop exists. If we reach the end of the list without finding any duplicates, there is no loop.

---

## 🔹 Algorithm

1. Initialize an empty hash set to store visited nodes.
2. Traverse the linked list starting from the head node.
3. For each node, check if it is already in the hash set.
   - If it is, return `true` (loop detected).
   - Otherwise, add the node to the hash set.
4. If the traversal completes without finding any duplicates, return `false` (no loop).

---

## 🔹 Code

```java
import java.util.HashSet;

class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Solution {
    public boolean detectLoop(Node head) {
        HashSet<Node> visited = new HashSet<>();
        Node current = head;

        while (current != null) {
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
            current = current.next;
        }

        return false;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the following linked list: `1 -> 2 -> 3 -> 4 -> 2` (where the loop starts at node 2).

| Step | Current Node | Visited Nodes | Action | Result |
|------|--------------|----------------|--------|--------|
| 1    | 1            | {}             | Add 1  | false  |
| 2    | 2            | {1}            | Add 2  | false  |
| 3    | 3            | {1, 2}         | Add 3  | false  |
| 4    | 4            | {1, 2, 3}      | Add 4  | false  |
| 5    | 2            | {1, 2, 3, 4}   | Found  | true   |

In this example, the algorithm detects the loop when it encounters node 2 again.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses Floyd's Tortoise and Hare algorithm, which involves two pointers moving at different speeds. The slow pointer moves one step at a time, while the fast pointer moves two steps at a time. If there is a loop, the fast pointer will eventually meet the slow pointer, indicating the presence of a loop. If the fast pointer reaches the end of the list, there is no loop.

---

## 🔹 Why This Works

Floyd's algorithm works because the fast pointer moves twice as fast as the slow pointer. If there is a loop, the fast pointer will eventually lap the slow pointer, and they will meet. If there is no loop, the fast pointer will reach the end of the list.

---

## 🔹 Algorithm

1. Initialize two pointers, `slow` and `fast`, both pointing to the head of the linked list.
2. Traverse the linked list with the `slow` pointer moving one step at a time and the `fast` pointer moving two steps at a time.
3. If at any point the `slow` and `fast` pointers meet, return `true` (loop detected).
4. If the `fast` pointer reaches the end of the list, return `false` (no loop).

---

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}

class Solution {
    public boolean detectLoop(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the same linked list: `1 -> 2 -> 3 -> 4 -> 2` (where the loop starts at node 2).

| Step | Slow Pointer | Fast Pointer | Action | Result |
|------|--------------|--------------|--------|--------|
| 1    | 1            | 1            | Move   | -      |
| 2    | 2            | 3            | Move   | -      |
| 3    | 3            | 2            | Move   | -      |
| 4    | 4            | 4            | Move   | -      |
| 5    | 2            | 2            | Meet   | true   |

In this example, the algorithm detects the loop when the slow and fast pointers meet at node 2.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty list**: The list has no nodes.
- **Single node**: The list has only one node with no loop.
- **Single node with loop**: The list has only one node that points to itself.
- **Large list**: The list has a large number of nodes.
- **No loop**: The list is linear with no loops.
- **Loop at the beginning**: The loop starts at the first node.

---

# 📚 Key Takeaways

- The brute force approach uses a hash set to detect loops, which is straightforward but uses extra space.
- The optimal approach uses Floyd's algorithm, which is more efficient in terms of space.
- Understanding the intuition behind Floyd's algorithm is crucial for solving similar problems efficiently.

---

# 🚀 Interview Tips

- **Follow-up questions**: Discuss the time and space complexity trade-offs between the brute force and optimal approaches.
- **Common pitfalls**: Ensure that edge cases, such as an empty list or a single node, are handled correctly.
- **Alternative approaches**: Consider using recursion or other pointer-based techniques, but be aware of their limitations.

---

# ✅ Conclusion

The optimal approach using Floyd's Tortoise and Hare algorithm is preferred for detecting loops in a linked list due to its efficiency in both time and space complexity. Understanding the intuition behind the algorithm is key to solving similar problems effectively.