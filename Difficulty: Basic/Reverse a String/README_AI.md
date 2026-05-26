# Reverse a String

## Problem Statement

Given a string `s`, reverse the string and return the reversed string.

**Example 1:**
```java
Input: s = "hello"
Output: "olleh"
```

**Example 2:**
```java
Input: s = "world"
Output: "dlrow"
```

**Constraints:**
- `1 <= s.length <= 10^5`
- `s` consists of printable ASCII characters.

---

## 💡 Intuition

The problem requires reversing a string. The most straightforward approach is to swap characters from the start and end of the string, moving towards the center until the entire string is reversed. This approach efficiently handles the reversal in place, minimizing additional space usage.

---

## 🐌 Brute Force Approach

### 🔹 Approach

1. Convert the string into a character array.
2. Initialize two pointers, one at the start (`left`) and one at the end (`right`) of the array.
3. Swap the characters at these pointers.
4. Move the `left` pointer forward and the `right` pointer backward.
5. Repeat the process until the `left` pointer is no longer less than the `right` pointer.
6. Convert the character array back to a string and return it.

### 🔹 Algorithm

1. Convert the string `s` to a character array `arr`.
2. Initialize `left` to 0 and `right` to `s.length() - 1`.
3. While `left < right`:
   - Swap `arr[left]` and `arr[right]`.
   - Increment `left` and decrement `right`.
4. Convert `arr` back to a string and return it.

### 🔹 Code

```java
class Solution {
    public static String reverseString(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }
}
```

### 🔹 Dry Run

Let's dry run the algorithm with `s = "hello"`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 4 | Swap 'h' and 'o' | ['o', 'e', 'l', 'l', 'h'] |
| 2 | 1 | 3 | Swap 'e' and 'l' | ['o', 'l', 'l', 'e', 'h'] |
| 3 | 2 | 2 | Terminate loop | ['o', 'l', 'l', 'e', 'h'] |

The final reversed string is "olleh".

### 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

## ⚡ Optimal Approach

### 🔹 Approach

The optimal approach is similar to the brute force approach but avoids creating a new character array by using a `StringBuilder` to build the reversed string directly. This approach leverages the built-in methods of `StringBuilder` for efficient string manipulation.

### 🔹 Why This Works

Using `StringBuilder` allows us to append characters in reverse order without the overhead of converting the string to a character array and back. The `StringBuilder` class is optimized for such operations, making it more efficient in terms of both time and space.

### 🔹 Algorithm

1. Initialize a `StringBuilder` object.
2. Iterate over the string `s` from the end to the start.
3. Append each character to the `StringBuilder`.
4. Convert the `StringBuilder` to a string and return it.

### 🔹 Code

```java
class Solution {
    public static String reverseString(String s) {
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i));
        }
        return reversed.toString();
    }
}
```

### 🔹 Detailed Dry Run

Let's dry run the algorithm with `s = "hello"`.

| Step | Index | Character | Action | State |
|---|---|---|---|---|
| 1 | 4 | 'o' | Append 'o' | "o" |
| 2 | 3 | 'l' | Append 'l' | "ol" |
| 3 | 2 | 'l' | Append 'l' | "oll" |
| 4 | 1 | 'e' | Append 'e' | "olle" |
| 5 | 0 | 'h' | Append 'h' | "olleh" |

The final reversed string is "olleh".

### 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

## 🔍 Edge Cases

- **Empty String:** `s = ""` → Output: `""`
- **Single Character:** `s = "a"` → Output: `"a"`
- **Palindrome String:** `s = "madam"` → Output: `"madam"`
- **String with Spaces:** `s = "hello world"` → Output: `"dlrow olleh"`

---

## 📚 Key Takeaways

- **In-Place Reversal:** The brute force approach reverses the string in place, which is efficient in terms of space.
- **StringBuilder Usage:** The optimal approach uses `StringBuilder` to efficiently build the reversed string.
- **Two-Pointer Technique:** Both approaches use a two-pointer technique to swap characters from the start and end of the string.
- **Time Complexity:** Both approaches have a time complexity of O(n), where n is the length of the string.
- **Space Complexity:** Both approaches have a space complexity of O(n) due to the creation of a new string or character array.

---

## 🚀 Interview Tips

- **Follow-Up Questions:**
  - Can you reverse the string without using additional space?
  - How would you handle very large strings efficiently?
- **Common Pitfalls:**
  - Forgetting to handle edge cases like empty strings or single-character strings.
  - Off-by-one errors when initializing or updating pointers.
- **Alternative Approaches:**
  - Using recursion to reverse the string.
  - Using the `Collections.reverse` method on a list of characters.
- **Optimization Discussions:**
  - The optimal approach using `StringBuilder` is more efficient for very large strings.
  - The brute force approach is more suitable for scenarios where additional space is a concern.

---

## ✅ Conclusion

The optimal approach using `StringBuilder` is preferred for its efficiency and simplicity. However, the brute force approach using a character array is also efficient and demonstrates the two-pointer technique effectively. Both approaches are suitable for interview scenarios, with the choice depending on the specific constraints and requirements of the problem.

---

## 🎨 Formatting Rules

- Proper markdown headings and separators are used for clear structure.
- Syntax-highlighted code blocks are used for Java code.
- Markdown tables are used for dry runs and complexity analysis.
- Concise and informative explanations are provided for each section.