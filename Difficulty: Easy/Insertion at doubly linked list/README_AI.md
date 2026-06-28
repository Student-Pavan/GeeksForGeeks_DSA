# Insertion at doubly linked list

---

# 📝 Problem Statement

Given a doubly linked list and a position `p`, insert a new node with value `x` at position `p`. The position is 0-indexed.

**Input:**
- `head`: The head node of the doubly linked list.
- `p`: The position where the new node should be inserted.
- `x`: The value of the new node.

**Output:**
- The head of the modified doubly linked list.

**Constraints:**
- The position `p` is guaranteed to be valid.
- The doubly linked list may be empty.

---

# 💡 Intuition

The key insight is that we need to carefully handle the pointers of the nodes involved in the insertion. For a doubly linked list, we need to update both the `next` and `prev` pointers of the surrounding nodes to maintain the list's integrity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the list to find the node at position `p`.
2. Insert the new node after the node at position `p`.
3. Update the pointers of the new node, the node at position `p`, and the node after position `p` to maintain the doubly linked list structure.

## 🔹 Algorithm

1. Create a new node with value `x`.
2. Initialize a temporary node `temp` to the head of the list and a counter `pos` to 0.
3. Traverse the list until `temp` reaches the node at position `p`.
4. Set the `next` pointer of the new node to the `next` pointer of `temp`.
5. Set the `prev` pointer of the new node to `temp`.
6. If `temp.next` is not null, set the `prev` pointer of `temp.next` to the new node.
7. Set the `next` pointer of `temp` to the new node.
8. Return the head of the list.

## 🔹 Code

```java
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        next = prev = null;
    }
}

class Solution {
    Node insertAtPos(Node head, int p, int x) {
        Node newNode = new Node(x);

        if (head == null) {
            return newNode;
        }

        Node temp = head;
        int pos = 0;

        while (temp != null) {
            if (pos == p) {
                newNode.next = temp.next;
                newNode.prev = temp;

                if (temp.next != null) {
                    temp.next.prev = newNode;
                }

                temp.next = newNode;
                break;
            }

            temp = temp.next;
            pos++;
        }

        return head;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the following input:
- `head`: 1 <-> 2 <-> 3 <-> 4
- `p`: 2
- `x`: 5

| Step | Temp | Pos | Action | List State |
|------|------|-----|--------|-------------|
| 1    | 1    | 0   | Move to next node | 1 <-> 2 <-> 3 <-> 4 |
| 2    | 2    | 1   | Move to next node | 1 <-> 2 <-> 3 <-> 4 |
| 3    | 3    | 2   | Insert new node | 1 <-> 2 <-> 5 <-> 3 <-> 4 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is the same as the brute force approach since we need to traverse the list to find the position where the new node should be inserted. The optimal approach is efficient because it only requires a single pass through the list.

## 🔹 Why This Works

This approach works because it correctly updates the pointers of the nodes involved in the insertion, ensuring that the doubly linked list remains intact. The algorithm handles edge cases such as inserting at the beginning or end of the list by checking the `prev` and `next` pointers of the surrounding nodes.

## 🔹 Algorithm

1. Create a new node with value `x`.
2. Initialize a temporary node `temp` to the head of the list and a counter `pos` to 0.
3. Traverse the list until `temp` reaches the node at position `p`.
4. Set the `next` pointer of the new node to the `next` pointer of `temp`.
5. Set the `prev` pointer of the new node to `temp`.
6. If `temp.next` is not null, set the `prev` pointer of `temp.next` to the new node.
7. Set the `next` pointer of `temp` to the new node.
8. Return the head of the list.

## 🔹 Code

```java
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        next = prev = null;
    }
}

class Solution {
    Node insertAtPos(Node head, int p, int x) {
        Node newNode = new Node(x);

        if (head == null) {
            return newNode;
        }

        Node temp = head;
        int pos = 0;

        while (temp != null) {
            if (pos == p) {
                newNode.next = temp.next;
                newNode.prev = temp;

                if (temp.next != null) {
                    temp.next.prev = newNode;
                }

                temp.next = newNode;
                break;
            }

            temp = temp.next;
            pos++;
        }

        return head;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal algorithm with the following input:
- `head`: 1 <-> 2 <-> 3 <-> 4
- `p`: 1
- `x`: 5

| Step | Temp | Pos | Action | List State |
|------|------|-----|--------|-------------|
| 1    | 1    | 0   | Move to next node | 1 <-> 2 <-> 3 <-> 4 |
| 2    | 2    | 1   | Insert new node | 1 <-> 5 <-> 2 <-> 3 <-> 4 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- Inserting at the beginning of the list.
- Inserting at the end of the list.
- Inserting into an empty list.
- Inserting into a list with a single node.

---

# 📚 Key Takeaways

- Understanding how to handle pointers in a doubly linked list is crucial.
- Carefully updating the `next` and `prev` pointers ensures the list remains intact.
- The optimal approach is efficient and requires only a single pass through the list.

---

# 🚀 Interview Tips

- Discuss the importance of updating both `next` and `prev` pointers.
- Ask about the time and space complexity of the solution.
- Consider follow-up questions about inserting at the beginning or end of the list.

---

# ✅ Conclusion

The optimal solution efficiently inserts a new node into a doubly linked list by carefully handling the pointers of the surrounding nodes. The key insight is to ensure that both the `next` and `prev` pointers are updated correctly to maintain the list's integrity.