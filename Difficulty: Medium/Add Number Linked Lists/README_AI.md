# Add Number Linked Lists

---

# 📝 Problem Statement

You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order, and each node contains a single digit. Add the two numbers and return the sum as a linked list.

**Objective**: Implement a function to add two numbers represented by linked lists and return the sum as a linked list.

**Input**:
- Two linked lists `head1` and `head2`, where each list represents a non-negative number in reverse order.

**Output**:
- A linked list representing the sum of the two numbers in reverse order.

**Constraints**:
- The number of nodes in each linked list is in the range `[1, 100]`.
- `0 <= Node.val <= 9`
- It is guaranteed that the list represents a number that does not have leading zeros.

---

# 💡 Intuition

The key insight here is that the digits are stored in reverse order, which simplifies the addition process. We can add the digits of the two numbers from the least significant digit to the most significant digit, just like we do on paper. We need to handle the carry from each addition appropriately.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Traverse both linked lists to extract the numbers they represent.
2. Convert these numbers to integers.
3. Add the two integers.
4. Convert the sum back to a linked list in reverse order.

## 🔹 Algorithm

1. Initialize two empty strings `num1` and `num2`.
2. Traverse `head1` and append each digit to `num1`.
3. Traverse `head2` and append each digit to `num2`.
4. Reverse both strings to get the correct order of digits.
5. Convert `num1` and `num2` to integers.
6. Add the two integers to get the sum.
7. Convert the sum back to a string.
8. Create a new linked list from the string in reverse order.

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
    public Node addTwoLists(Node head1, Node head2) {
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        while (head1 != null) {
            num1.append(head1.data);
            head1 = head1.next;
        }

        while (head2 != null) {
            num2.append(head2.data);
            head2 = head2.next;
        }

        int sum = Integer.parseInt(num1.reverse().toString()) + Integer.parseInt(num2.reverse().toString());

        Node dummy = new Node(0);
        Node curr = dummy;

        if (sum == 0) {
            return new Node(0);
        }

        while (sum != 0) {
            curr.next = new Node(sum % 10);
            curr = curr.next;
            sum /= 10;
        }

        return dummy.next;
    }
}
```

## 🔹 Dry Run

Let's dry run the code with the following linked lists:

- `head1`: 3 -> 4 -> 2 (represents 243)
- `head2`: 4 -> 6 -> 5 (represents 564)

| Step | Action | num1 | num2 | sum | curr.next |
|------|--------|------|------|-----|-----------|
| 1    | Traverse head1 | "3" | "" | - | - |
| 2    | Traverse head1 | "34" | "" | - | - |
| 3    | Traverse head1 | "342" | "" | - | - |
| 4    | Reverse num1 | "243" | "" | - | - |
| 5    | Traverse head2 | "243" | "4" | - | - |
| 6    | Traverse head2 | "243" | "46" | - | - |
| 7    | Traverse head2 | "243" | "465" | - | - |
| 8    | Reverse num2 | "243" | "564" | - | - |
| 9    | Convert to integers | - | - | 243 + 564 = 807 | - |
| 10   | Create linked list | - | - | - | 7 |
| 11   | Create linked list | - | - | - | 7 -> 0 |
| 12   | Create linked list | - | - | - | 7 -> 0 -> 8 |

Final result: 7 -> 0 -> 8 (represents 807)

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n + m) where n and m are the lengths of the two linked lists |
| Space Complexity | O(n + m) for storing the numbers and the result |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Reverse both linked lists to make the addition process easier.
2. Traverse both linked lists simultaneously, adding corresponding digits along with any carry from the previous addition.
3. Create a new linked list to store the result.
4. Reverse the result linked list to get the correct order.

## 🔹 Why This Works

Reversing the linked lists allows us to add the digits from the least significant digit to the most significant digit, which simplifies the addition process. We can handle the carry from each addition step and build the result linked list accordingly.

## 🔹 Algorithm

1. Reverse both linked lists `head1` and `head2`.
2. Initialize a dummy node and a current pointer to build the result linked list.
3. Initialize a carry variable to 0.
4. While there are nodes left in either `head1` or `head2`, or there is a carry:
   - Calculate the sum of the current digits from `head1` and `head2` along with the carry.
   - Update the carry for the next iteration.
   - Create a new node with the digit value (sum % 10) and append it to the result linked list.
   - Move the current pointer to the next node.
5. Reverse the result linked list to get the correct order.
6. Remove any leading zeros from the result linked list.

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

    private Node reverse(Node head) {
        Node prev = null;

        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    public Node addTwoLists(Node head1, Node head2) {

        head1 = reverse(head1);
        head2 = reverse(head2);

        Node dummy = new Node(0);
        Node curr = dummy;

        int carry = 0;

        while (head1 != null || head2 != null || carry != 0) {

            int sum = carry;

            if (head1 != null) {
                sum += head1.data;
                head1 = head1.next;
            }

            if (head2 != null) {
                sum += head2.data;
                head2 = head2.next;
            }

            carry = sum / 10;

            curr.next = new Node(sum % 10);
            curr = curr.next;
        }

        Node result = reverse(dummy.next);

        // Remove leading zeros if required by GFG
        while (result != null && result.data == 0 && result.next != null) {
            result = result.next;
        }

        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code with the following linked lists:

- `head1`: 3 -> 4 -> 2 (represents 243)
- `head2`: 4 -> 6 -> 5 (represents 564)

| Step | Action | head1 | head2 | sum | carry | curr.next |
|------|--------|-------|-------|-----|-------|-----------|
| 1    | Reverse head1 | 2 -> 4 -> 3 | 4 -> 6 -> 5 | - | - | - |
| 2    | Reverse head2 | 2 -> 4 -> 3 | 5 -> 6 -> 4 | - | - | - |
| 3    | Initialize dummy and curr | 2 -> 4 -> 3 | 5 -> 6 -> 4 | - | 0 | - |
| 4    | Add digits | 4 -> 3 | 6 -> 4 | 2 + 5 = 7 | 0 | 7 |
| 5    | Add digits | 3 | 4 | 4 + 6 = 10 | 1 | 7 -> 0 |
| 6    | Add digits | - | - | 3 + 4 = 7 + 1 (carry) = 8 | 0 | 7 -> 0 -> 8 |
| 7    | Reverse result | - | - | - | - | 8 -> 0 -> 7 |
| 8    | Remove leading zeros | - | - | - | - | 8 -> 0 -> 7 |

Final result: 8 -> 0 -> 7 (represents 708)

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n + m) where n and m are the lengths of the two linked lists |
| Space Complexity | O(n + m) for storing the result |

---

# 🔍 Edge Cases

- One of the linked lists is empty.
- Both linked lists have only one node.
- The sum results in a number with leading zeros.
- The sum results in a number with more digits than the input numbers.

---

# 📚 Key Takeaways

- Reversing the linked lists simplifies the addition process.
- Handling the carry properly is crucial for correct addition.
- Converting the linked lists to integers and back can be a simple but less efficient approach.

---

# 🚀 Interview Tips

- Discuss the trade-offs between the brute force and optimal approaches.
- Ask if the linked lists are guaranteed to be in reverse order.
- Consider edge cases such as leading zeros and empty lists.

---

# ✅ Conclusion

The optimal approach efficiently adds the two numbers represented by linked lists by reversing them, adding corresponding digits, and handling the carry. This approach ensures that we handle the addition process correctly and efficiently.