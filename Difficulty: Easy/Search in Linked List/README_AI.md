# Search in Linked List

---

# 📝 Problem Statement

Given a linked list and a key, the task is to check if the key is present in the linked list. Return `true` if the key is found, otherwise return `false`.

**Input:**
- A linked list `head` of type `Node`
- An integer `key` to search for

**Output:**
- `true` if the key is found in the linked list
- `false` otherwise

**Constraints:**
- The linked list can be empty
- The key can be any integer value
- The linked list can contain duplicate values

---

# 💡 Intuition

The problem requires searching for a specific value in a linked list. The most straightforward approach is to traverse the linked list from the head node to the end, checking each node's data against the key. If a match is found, we return `true`; otherwise, after traversing the entire list, we return `false`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through each node of the linked list sequentially. For each node, we compare its data with the key. If a match is found, we immediately return `true`. If the end of the list is reached without finding the key, we return `false`.

---

## 🔹 Algorithm

1. Initialize a temporary pointer `temp` to the head of the linked list.
2. Traverse the linked list using a while loop until `temp` becomes `null`.
3. In each iteration, compare the data of the current node (`temp.data`) with the key.
4. If a match is found, return `true`.
5. If the loop completes without finding the key, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean searchKey(Node head, int key) {
        Node temp = head;

        while (temp != null) {
            if (temp.data == key) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 3 -> 5 -> 7 -> 9` and the key `5`.

| Step | Current Node | Current Node Data | Key | Action |
|------|---------------|--------------------|-----|--------|
| 1    | Node 1        | 1                  | 5   | Move to next node |
| 2    | Node 3        | 3                  | 5   | Move to next node |
| 3    | Node 5        | 5                  | 5   | Key found, return true |

The algorithm returns `true` as the key `5` is found in the linked list.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach because it is already the most efficient way to search for a key in an unsorted linked list. Traversing the list once is the only way to ensure that we do not miss any nodes, and the time complexity is linear, which is optimal for this problem.

---

## 🔹 Why This Works

The optimal approach works because it checks every node exactly once, ensuring that no node is missed. The time complexity is O(n), where n is the number of nodes in the linked list, which is optimal for an unsorted linked list. The space complexity is O(1) as no additional space is used apart from a few variables.

---

## 🔹 Algorithm

1. Initialize a temporary pointer `temp` to the head of the linked list.
2. Traverse the linked list using a while loop until `temp` becomes `null`.
3. In each iteration, compare the data of the current node (`temp.data`) with the key.
4. If a match is found, return `true`.
5. If the loop completes without finding the key, return `false`.

---

## 🔹 Code

```java
class Solution {
    public boolean searchKey(Node head, int key) {
        Node temp = head;

        while (temp != null) {
            if (temp.data == key) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the following linked list: `1 -> 3 -> 5 -> 7 -> 9` and the key `7`.

| Step | Current Node | Current Node Data | Key | Action |
|------|---------------|--------------------|-----|--------|
| 1    | Node 1        | 1                  | 7   | Move to next node |
| 2    | Node 3        | 3                  | 7   | Move to next node |
| 3    | Node 5        | 5                  | 7   | Move to next node |
| 4    | Node 7        | 7                  | 7   | Key found, return true |

The algorithm returns `true` as the key `7` is found in the linked list.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Linked List:** The linked list has no nodes. The algorithm should return `false` as there are no nodes to check.
- **Single Node Linked List:** The linked list has only one node. The algorithm should check this node and return `true` if the key matches, otherwise `false`.
- **Key Not Present:** The key is not present in the linked list. The algorithm should traverse the entire list and return `false`.
- **Duplicate Keys:** The linked list contains duplicate values of the key. The algorithm should return `true` as soon as the first occurrence of the key is found.

---

# 📚 Key Takeaways

- **Linear Search:** The problem requires a linear search through the linked list, which is the most efficient way for an unsorted list.
- **Early Termination:** The algorithm can terminate early if the key is found, which optimizes the best-case scenario.
- **Optimal Complexity:** The time complexity is O(n), which is optimal for an unsorted linked list, and the space complexity is O(1), as no additional space is used.

---

# 🚀 Interview Tips

- **Follow-Up Questions:** Discuss how the solution would change if the linked list were sorted. A binary search approach could be used, but it would require converting the linked list to an array or using a different data structure.
- **Common Pitfalls:** Ensure that the algorithm correctly handles edge cases such as an empty linked list or a single-node linked list.
- **Alternative Approaches:** Discuss the use of recursion to traverse the linked list, though it would have the same time and space complexity.

---

# ✅ Conclusion

The optimal solution for searching in a linked list is to traverse the list sequentially, checking each node's data against the key. This approach ensures that the key is found if it exists in the list, and it handles all edge cases correctly. The time complexity is O(n), which is optimal for an unsorted linked list, and the space complexity is O(1), as no additional space is used.