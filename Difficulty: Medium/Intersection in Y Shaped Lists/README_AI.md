# Intersection in Y Shaped Lists

---

# 📝 Problem Statement

Given two singly linked lists that intersect at a common node, find the node at which the two lists intersect. If the lists do not intersect, return `null`.

**Constraints:**
- The lists must retain their original structure after function returns.
- Do not modify the lists.
- The intersection is defined by reference, not value.
- The two lists may or may not be of the same length.

---

# 💡 Intuition

The key insight is that by traversing both lists simultaneously, we can account for the difference in their lengths. When one pointer reaches the end of its list, it continues from the head of the other list. This ensures that both pointers traverse the same number of nodes before reaching the intersection point.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse the first list and store all nodes in a hash set.
2. Traverse the second list and check for the first node that exists in the hash set.
3. Return the first common node found.

## 🔹 Algorithm

1. Initialize an empty hash set.
2. Traverse the first linked list:
   - Add each node to the hash set.
3. Traverse the second linked list:
   - For each node, check if it exists in the hash set.
   - If found, return the node.
4. If no intersection is found, return `null`.

## 🔹 Code

```java
import java.util.HashSet;

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class Solution {
    public Node intersectPoint(Node head1, Node head2) {
        HashSet<Node> set = new HashSet<>();

        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }

        while (head2 != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }

        return null;
    }
}
```

## 🔹 Dry Run

Let's consider two linked lists that intersect at node with value 3:

List 1: 1 → 2 → 3 → 4 → 5
List 2: 6 → 7 → 3 → 4 → 5

| Step | Action | Set Contents | Current Node (List 1) | Current Node (List 2) | Intersection Found |
|------|--------|--------------|----------------------|----------------------|--------------------|
| 1    | Add 1  | {1}          | 2                    | null                 | No                 |
| 2    | Add 2  | {1, 2}       | 3                    | null                 | No                 |
| 3    | Add 3  | {1, 2, 3}    | 4                    | null                 | No                 |
| 4    | Add 4  | {1, 2, 3, 4} | 5                    | null                 | No                 |
| 5    | Add 5  | {1, 2, 3, 4, 5} | null              | null                 | No                 |
| 6    | Check 6 | -            | null                 | 7                    | No                 |
| 7    | Check 7 | -            | null                 | 3                    | Yes (3 in set)     |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(m + n) |
| Space Complexity | O(m) or O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Initialize two pointers, one for each list.
2. Traverse both lists simultaneously:
   - When a pointer reaches the end of its list, redirect it to the head of the other list.
3. The pointers will meet at the intersection node after traversing the combined length of both lists.

## 🔹 Why This Works

This approach works because the two pointers will eventually meet after traversing the combined length of both lists. The difference in lengths is accounted for by the pointers switching lists, ensuring they traverse the same number of nodes before meeting at the intersection point.

## 🔹 Algorithm

1. Initialize two pointers, `a` and `b`, to the heads of the two lists.
2. While `a` and `b` are not equal:
   - If `a` reaches the end of its list, set `a` to the head of the second list.
   - If `b` reaches the end of its list, set `b` to the head of the first list.
   - Move both pointers forward.
3. Return `a` (or `b`, as they are equal).

## 🔹 Code

```java
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class Solution {
    public Node intersectPoint(Node head1, Node head2) {
        Node a = head1;
        Node b = head2;

        while (a != b) {
            a = a == null ? head2 : a.next;
            b = b == null ? head1 : b.next;
        }

        return a;
    }
}
```

## 🔹 Detailed Dry Run

Let's consider the same linked lists as before:

List 1: 1 → 2 → 3 → 4 → 5
List 2: 6 → 7 → 3 → 4 → 5

| Step | Pointer a | Pointer b | Action | Intersection Found |
|------|-----------|-----------|--------|--------------------|
| 1    | 1         | 6         | Move both | No                 |
| 2    | 2         | 7         | Move both | No                 |
| 3    | 3         | 3         | Move both | Yes                |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(m + n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **No intersection**: Both lists do not intersect.
- **Same lists**: Both lists are identical.
- **Different lengths**: Lists have different lengths but intersect.
- **Single node intersection**: Lists intersect at the first node.
- **Large lists**: Lists are very long with intersection at the end.

---

# 📚 Key Takeaways

- **Hash Set Approach**: Simple to implement but uses extra space.
- **Two Pointers Approach**: More efficient in terms of space but requires careful traversal.
- **Pattern Recognition**: Identifying the need to account for length differences is crucial.
- **Pointer Manipulation**: Effective use of pointer redirection to handle different lengths.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - What if the lists are very large and we need to minimize space usage?
  - How would you handle circular linked lists?
- **Common Pitfalls**:
  - Forgetting to reset pointers when reaching the end of a list.
  - Incorrectly handling the case where lists do not intersect.
- **Alternative Approaches**:
  - Using a stack to store nodes and then comparing from the end.
- **Optimization Discussions**:
  - The two-pointer approach is optimal for space but requires understanding of pointer manipulation.

---

# ✅ Conclusion

The optimal approach using two pointers is preferred for its O(1) space complexity. The key insight is to account for the difference in list lengths by redirecting pointers when they reach the end of their respective lists. This ensures that both pointers traverse the combined length of the lists, meeting at the intersection point if it exists.