# Reverse a sublist of a linked list

---

# 📝 Problem Statement

Given a linked list, reverse a sublist from position `a` to position `b` (inclusive). The positions are 1-indexed.

**Example:**

Input:
```
1 -> 2 -> 3 -> 4 -> 5
a = 2, b = 4
```

Output:
```
1 -> 4 -> 3 -> 2 -> 5
```

**Constraints:**
- 1 ≤ n ≤ 10^5
- 1 ≤ a ≤ b ≤ n

---

# 💡 Intuition

The problem requires reversing a specific segment of a linked list. The key insight is to:
1. Locate the node just before the sublist (prevleft)
2. Reverse the sublist in place
3. Reconnect the reversed sublist back to the main list

This approach maintains O(1) space complexity while achieving O(n) time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the list to find the node at position `a-1` (prevleft)
2. Traverse the sublist from `a` to `b` and store the nodes in an array
3. Reverse the array
4. Traverse the reversed array and update the next pointers
5. Connect the reversed sublist back to the main list

## 🔹 Algorithm

1. Initialize a dummy node pointing to head
2. Traverse to find prevleft (node at position a-1)
3. Store nodes from a to b in an array
4. Reverse the array
5. Traverse the reversed array and update next pointers
6. Connect the reversed sublist back to the main list
7. Return dummy.next

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
    Node reverseBetween(int a, int b, Node head) {
        if (head == null || a == b) return head;

        Node dummy = new Node(0);
        dummy.next = head;

        Node prevleft = dummy;

        // Find the node before the sublist
        for (int i = 1; i < a; i++) {
            prevleft = prevleft.next;
        }

        // Extract the sublist
        Node[] sublist = new Node[b - a + 1];
        Node current = prevleft.next;
        for (int i = 0; i < sublist.length; i++) {
            sublist[i] = current;
            current = current.next;
        }

        // Reverse the sublist
        for (int i = 0; i < sublist.length / 2; i++) {
            Node temp = sublist[i];
            sublist[i] = sublist[sublist.length - 1 - i];
            sublist[sublist.length - 1 - i] = temp;
        }

        // Reconnect the reversed sublist
        prevleft.next = sublist[0];
        for (int i = 0; i < sublist.length - 1; i++) {
            sublist[i].next = sublist[i + 1];
        }
        sublist[sublist.length - 1].next = current;

        return dummy.next;
    }
}
```

## 🔹 Dry Run

Let's dry run with input: 1->2->3->4->5, a=2, b=4

| Step | prevleft | current | Action | State |
|------|----------|---------|--------|-------|
| 1    | dummy    | 1       | Move prevleft to position 1 | prevleft=1 |
| 2    | 1        | 2       | Store sublist [2,3,4] | sublist=[2,3,4] |
| 3    | 1        | 5       | Reverse sublist | sublist=[4,3,2] |
| 4    | 1        | 5       | Connect reversed sublist | 1->4->3->2->5 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(b-a+1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use a dummy node to handle edge cases
2. Locate the node before the sublist (prevleft)
3. Reverse the sublist in place using three pointers
4. Reconnect the reversed sublist back to the main list

## 🔹 Why This Works

This approach reverses the sublist in place without using extra space, making it more efficient than the brute force method. The three-pointer technique (prev, curr, next) allows us to reverse the sublist while maintaining the connections to the rest of the list.

## 🔹 Algorithm

1. Initialize a dummy node pointing to head
2. Traverse to find prevleft (node at position a-1)
3. Initialize three pointers: prev, curr, next
4. Reverse the sublist from a to b
5. Connect the reversed sublist back to the main list
6. Return dummy.next

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
    Node reverseBetween(int a, int b, Node head) {
        if (head == null || a == b) return head;

        Node dummy = new Node(0);
        dummy.next = head;

        Node prevleft = dummy;

        // Find the node before the sublist
        for (int i = 1; i < a; i++) {
            prevleft = prevleft.next;
        }

        // Initialize pointers for reversal
        Node prev = null;
        Node curr = prevleft.next;
        Node next = null;

        // Reverse the sublist
        for (int i = a; i <= b; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Connect the reversed sublist back to the main list
        prevleft.next.next = curr;
        prevleft.next = prev;

        return dummy.next;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run with input: 1->2->3->4->5, a=2, b=4

| Step | prev | curr | next | Action | State |
|------|------|------|------|--------|-------|
| 1    | null | 2    | 3    | Initialize pointers | prev=null, curr=2, next=3 |
| 2    | 2    | 3    | 4    | Reverse first node | 1->2<-3 4->5 |
| 3    | 3    | 4    | 5    | Reverse second node | 1->2<-3<-4 5 |
| 4    | 4    | 5    | null | Reverse third node | 1->4->3->2 5 |
| 5    | 4    | 5    | null | Connect reversed sublist | 1->4->3->2->5 |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

1. Empty list
2. Single element list
3. a = b (no reversal needed)
4. Reversing the entire list
5. a = 1 (reversing from head)
6. b = n (reversing to tail)

---

# 📚 Key Takeaways

1. Linked list reversal can be done in place with O(1) space
2. The three-pointer technique is efficient for in-place reversal
3. Using a dummy node simplifies edge case handling
4. Understanding the problem constraints is crucial for optimization

---

# 🚀 Interview Tips

1. Ask clarifying questions about the problem constraints
2. Consider edge cases during the interview
3. Explain your thought process clearly
4. Be prepared to discuss time and space complexity
5. Practice drawing linked list diagrams during interviews

---

# ✅ Conclusion

The optimal solution provides an efficient way to reverse a sublist in a linked list with O(n) time complexity and O(1) space complexity. The key insight is using the three-pointer technique for in-place reversal while maintaining the connections to the rest of the list. This approach is both time and space efficient, making it ideal for interview scenarios.