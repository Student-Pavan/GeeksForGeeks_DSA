# Longest Substring with K Uniques

---

# 📝 Problem Statement

Given a string `s` and an integer `k`, find the length of the longest substring that contains at most `k` distinct characters.

**Objective**: Find the maximum length of a substring with exactly `k` unique characters.

**Input**:
- `s`: A string consisting of lowercase English letters
- `k`: An integer representing the maximum number of unique characters allowed

**Output**:
- The length of the longest substring with at most `k` distinct characters
- Return `-1` if no such substring exists

**Constraints**:
- `1 <= s.length <= 10^5`
- `0 <= k <= 26`

---

# 💡 Intuition

The problem requires finding the longest substring with exactly `k` unique characters. The key insight is to use a sliding window approach to efficiently track the number of unique characters in the current window. By maintaining a window that contains at most `k` unique characters, we can find the maximum length of such a substring.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings of the given string and counting the number of unique characters in each substring. The maximum length of a substring with exactly `k` unique characters is then determined.

## 🔹 Algorithm

1. Initialize `max_len` to `-1` to handle cases where no valid substring is found.
2. Iterate over all possible starting indices `i` of the substring.
3. For each starting index `i`, iterate over all possible ending indices `j` of the substring.
4. For each substring `s[i..j]`, count the number of unique characters using a hash set.
5. If the count of unique characters is exactly `k`, update `max_len` with the maximum of its current value and the length of the substring.
6. Return `max_len` after checking all possible substrings.

## 🔹 Code

```java
import java.util.HashSet;

class Solution {
    public int longestKSubstr(String s, int k) {
        int max_len = -1;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            HashSet<Character> uniqueChars = new HashSet<>();
            for (int j = i; j < n; j++) {
                uniqueChars.add(s.charAt(j));
                if (uniqueChars.size() == k) {
                    max_len = Math.max(max_len, j - i + 1);
                }
            }
        }

        return max_len;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `s = "aabbcc"` and `k = 2`.

| Iteration | Substring | Unique Chars | Length | max_len |
|-----------|-----------|---------------|--------|---------|
| i=0, j=0  | "a"       | {a}           | 1      | -1      |
| i=0, j=1  | "aa"      | {a}           | 2      | -1      |
| i=0, j=2  | "aab"     | {a, b}        | 3      | 3       |
| i=0, j=3  | "aabb"    | {a, b}        | 4      | 4       |
| i=0, j=4  | "aabbc"   | {a, b, c}     | 5      | 4       |
| i=0, j=5  | "aabbcc"  | {a, b, c}     | 6      | 4       |
| i=1, j=1  | "a"       | {a}           | 1      | 4       |
| i=1, j=2  | "ab"      | {a, b}        | 2      | 4       |
| i=1, j=3  | "abb"     | {a, b}        | 3      | 4       |
| i=1, j=4  | "abbc"    | {a, b, c}     | 4      | 4       |
| i=1, j=5  | "abbcc"   | {a, b, c}     | 5      | 4       |
| ...       | ...       | ...           | ...    | ...     |

The maximum length of a substring with exactly 2 unique characters is 4 ("aabb" or "bbcc").

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(k) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique to efficiently find the longest substring with exactly `k` unique characters. The sliding window is defined by two pointers, `left` and `right`, which represent the current window's start and end indices. The algorithm maintains a hash map to keep track of the count of each character in the current window. If the number of unique characters exceeds `k`, the window is adjusted by moving the `left` pointer to the right until the number of unique characters is back to `k`.

## 🔹 Why This Works

The sliding window approach ensures that we only traverse the string once, making the algorithm efficient. By maintaining a count of characters in the current window, we can efficiently adjust the window size to ensure it contains at most `k` unique characters. This approach guarantees that we find the longest valid substring in linear time.

## 🔹 Algorithm

1. Initialize `max_len` to `-1` to handle cases where no valid substring is found.
2. Initialize a hash map `charCount` to keep track of the count of each character in the current window.
3. Initialize `left` pointer to 0.
4. Iterate over the string with the `right` pointer from 0 to `n-1`.
5. For each character at `right`, increment its count in `charCount`.
6. If the number of unique characters in `charCount` exceeds `k`, move the `left` pointer to the right, decrementing the count of the character at `left` in `charCount`, and remove it from the map if its count becomes zero.
7. If the number of unique characters in `charCount` is exactly `k`, update `max_len` with the maximum of its current value and the length of the current window (`right - left + 1`).
8. Return `max_len` after traversing the entire string.

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public int longestKSubstr(String s, int k) {
        if (k == 0) return -1;

        HashMap<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int max_len = -1;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }

            if (charCount.size() == k) {
                max_len = Math.max(max_len, right - left + 1);
            }
        }

        return max_len;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s = "aabbcc"` and `k = 2`.

| Iteration | Left | Right | Current Char | Char Count | Action | max_len |
|-----------|------|-------|--------------|------------|--------|---------|
| 1         | 0    | 0     | 'a'          | {a:1}      | -      | -1      |
| 2         | 0    | 1     | 'a'          | {a:2}      | -      | -1      |
| 3         | 0    | 2     | 'b'          | {a:2, b:1} | -      | 3       |
| 4         | 0    | 3     | 'b'          | {a:2, b:2} | -      | 4       |
| 5         | 0    | 4     | 'c'          | {a:2, b:2, c:1} | Move left to 1 | 4       |
| 6         | 1    | 4     | 'c'          | {a:1, b:2, c:1} | -      | 4       |
| 7         | 1    | 5     | 'c'          | {a:1, b:2, c:2} | Move left to 2 | 4       |
| 8         | 2    | 5     | 'c'          | {b:1, c:2} | -      | 4       |

The maximum length of a substring with exactly 2 unique characters is 4 ("aabb" or "bbcc").

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(k) |

---

# 🔍 Edge Cases

- **Empty String**: If the input string is empty, the function should return `-1`.
- **Single Character String**: If the string has only one character, the function should return `1` if `k` is `1`, otherwise `-1`.
- **All Unique Characters**: If the string has all unique characters, the function should return the length of the string if `k` is equal to the number of unique characters, otherwise `-1`.
- **k is 0**: If `k` is `0`, the function should return `-1` since no substring can have zero unique characters.
- **k is Greater than the Number of Unique Characters**: If `k` is greater than the number of unique characters in the string, the function should return the length of the string.

---

# 📚 Key Takeaways

- The sliding window technique is efficient for problems involving substrings or subarrays.
- Maintaining a count of characters in the current window allows for efficient adjustments to the window size.
- The optimal approach ensures linear time complexity, making it suitable for large input sizes.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - What if the string contains uppercase and lowercase letters?
  - How would you modify the solution to find the number of substrings with exactly `k` unique characters?
- **Common Pitfalls**:
  - Forgetting to handle the case where `k` is `0`.
  - Not efficiently adjusting the window size when the number of unique characters exceeds `k`.
- **Alternative Approaches**:
  - Using a fixed-size window and sliding it across the string.
  - Using a priority queue to keep track of the most frequent characters in the current window.

---

# ✅ Conclusion

The optimal sliding window approach efficiently finds the longest substring with exactly `k` unique characters. By maintaining a count of characters in the current window and adjusting the window size as needed, the algorithm ensures linear time complexity. This approach is both time and space efficient, making it suitable for large input sizes.

---