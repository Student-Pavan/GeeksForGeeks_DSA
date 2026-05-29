# Power Set

---

# 📝 Problem Statement

Given a string, find all possible subsequences of the string in sorted order.

**Example:**

**Input:** `"abc"`

**Output:** `["a", "ab", "abc", "ac", "b", "bc", "c"]`

**Explanation:** The power set of the string "abc" contains all possible subsequences of the string in sorted order.

---

# 💡 Intuition

The problem requires generating all possible subsequences of a given string. A subsequence is a sequence that can be derived from another sequence by deleting zero or more elements without changing the order of the remaining elements. The key insight here is to use recursion to explore all possible combinations of characters in the string.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves generating all possible subsequences of the string by considering each character and deciding whether to include it or not in the subsequence. This can be done using a recursive approach where we make a choice at each step to include or exclude the current character.

---

## 🔹 Algorithm

1. **Base Case:** If the current index is equal to the length of the string, add the current subsequence to the result list and return.
2. **Recursive Case:**
   - Include the current character in the subsequence and recursively call the function for the next index.
   - Exclude the current character from the subsequence and recursively call the function for the next index.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<String> AllPossibleStrings(String s) {
        List<String> result = new ArrayList<>();
        generateSubsequences(s, 0, "", result);
        Collections.sort(result);
        return result;
    }

    private void generateSubsequences(String s, int index, String current, List<String> result) {
        if (index == s.length()) {
            if (!current.isEmpty()) {
                result.add(current);
            }
            return;
        }
        generateSubsequences(s, index + 1, current + s.charAt(index), result);
        generateSubsequences(s, index + 1, current, result);
    }
}
```

---

## 🔹 Dry Run

Let's dry run the code with the input string "abc".

| Step | Index | Current Subsequence | Action |
|------|-------|----------------------|--------|
| 1    | 0     | ""                   | Include 'a' and recurse |
| 2    | 1     | "a"                  | Include 'b' and recurse |
| 3    | 2     | "ab"                 | Include 'c' and recurse |
| 4    | 3     | "abc"                | Add to result and return |
| 5    | 2     | "ab"                 | Exclude 'c' and recurse |
| 6    | 3     | "ab"                 | Add to result and return |
| 7    | 1     | "a"                  | Exclude 'b' and recurse |
| 8    | 2     | "a"                  | Include 'c' and recurse |
| 9    | 3     | "ac"                 | Add to result and return |
| 10   | 2     | "a"                  | Exclude 'c' and recurse |
| 11   | 3     | "a"                  | Add to result and return |
| 12   | 0     | ""                   | Exclude 'a' and recurse |
| 13   | 1     | ""                   | Include 'b' and recurse |
| 14   | 2     | "b"                  | Include 'c' and recurse |
| 15   | 3     | "bc"                 | Add to result and return |
| 16   | 2     | "b"                  | Exclude 'c' and recurse |
| 17   | 3     | "b"                  | Add to result and return |
| 18   | 1     | ""                   | Exclude 'b' and recurse |
| 19   | 2     | ""                   | Include 'c' and recurse |
| 20   | 3     | "c"                  | Add to result and return |
| 21   | 2     | ""                   | Exclude 'c' and recurse |
| 22   | 3     | ""                   | Add to result and return |

After sorting, the result list will be `["a", "ab", "abc", "ac", "b", "bc", "c"]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(2^n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but uses bit manipulation to generate all possible subsets of the string. Each bit in an integer represents whether a character is included in the subset or not. This approach is more efficient and concise.

---

## 🔹 Why This Works

Bit manipulation allows us to represent all possible subsets of the string using integers. For a string of length `n`, there are `2^n` possible subsets. Each bit in an integer from `0` to `2^n - 1` represents whether a character is included in the subset or not.

---

## 🔹 Algorithm

1. **Initialize** an empty list to store the result.
2. **Iterate** from `0` to `2^n - 1`:
   - For each number, iterate through each bit.
   - If the bit is set, include the corresponding character in the subset.
3. **Add** the generated subset to the result list.
4. **Sort** the result list and return it.

---

## 🔹 Code

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<String> AllPossibleStrings(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.append(s.charAt(j));
                }
            }
            if (subset.length() > 0) {
                result.add(subset.toString());
            }
        }
        Collections.sort(result);
        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the code with the input string "abc".

| Iteration | i | Binary Representation | Subset |
|-----------|---|-----------------------|--------|
| 1         | 0 | 000                   | ""     |
| 2         | 1 | 001                   | "c"    |
| 3         | 2 | 010                   | "b"    |
| 4         | 3 | 011                   | "bc"   |
| 5         | 4 | 100                   | "a"    |
| 6         | 5 | 101                   | "ac"   |
| 7         | 6 | 110                   | "ab"   |
| 8         | 7 | 111                   | "abc"  |

After sorting, the result list will be `["a", "ab", "abc", "ac", "b", "bc", "c"]`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n * 2^n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty String:** If the input string is empty, the output should be an empty list.
- **Single Character:** If the input string has only one character, the output should be a list containing that character.
- **Duplicate Characters:** If the input string has duplicate characters, the output should still be a list of unique subsequences.
- **Large Input:** The algorithm should handle large input strings efficiently.

---

# 📚 Key Takeaways

- **Recursion:** Recursion is a powerful tool for generating all possible combinations of elements.
- **Bit Manipulation:** Bit manipulation can be used to generate all possible subsets of a set efficiently.
- **Sorting:** Sorting the result list ensures that the output is in the required order.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you generate the power set without using recursion?
  - How would you handle large input strings efficiently?
- **Common Pitfalls:**
  - Forgetting to handle the empty string case.
  - Not sorting the result list.
  - Including duplicate subsequences in the result.
- **Alternative Approaches:**
  - Using an iterative approach to generate all possible subsets.
  - Using a stack to keep track of the current subset being built.

---

# ✅ Conclusion

The optimal approach using bit manipulation is more efficient and concise compared to the brute force recursive approach. It leverages the properties of bitwise operations to generate all possible subsets of the string, making it a preferred solution for this problem.

---

# 🎨 Formatting Rules

- Use proper markdown headings.
- Use markdown separators.
- Use syntax-highlighted code blocks.
- Use markdown tables extensively.
- Ensure GitHub readability.
- Keep spacing visually clean.
- Make README visually premium.
- Keep explanations concise but valuable.