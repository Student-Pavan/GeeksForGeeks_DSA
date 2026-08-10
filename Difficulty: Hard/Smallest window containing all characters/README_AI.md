# Smallest Window Containing All Characters

---

# 📝 Problem Statement

Given two strings `s` and `p`, return the smallest substring in `s` which contains all characters of `p` (including duplicates). If no such substring exists, return an empty string.

**Objective**: Find the minimum length substring in `s` that contains all characters of `p`.

**Constraints**:
- `1 <= s.length, p.length <= 10^5`
- `s` and `p` consist of uppercase and lowercase English letters.

---

# 💡 Intuition

The problem requires finding the smallest window in `s` that contains all characters of `p`. The optimal approach involves using a sliding window technique combined with a hash map to track character frequencies. The key insight is to maintain a window that satisfies the condition (contains all characters of `p`) and then shrink it from the left to find the minimum window.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings of `s` to see if they contain all characters of `p`. For each possible starting index, we expand the window to the right until all characters of `p` are included. This approach is inefficient but helps in understanding the problem.

## 🔹 Algorithm

1. Initialize an empty result string.
2. Iterate over all possible starting indices of the window in `s`.
3. For each starting index, expand the window to the right until all characters of `p` are included.
4. If all characters are included, check if the current window is smaller than the smallest window found so far.
5. Update the result if a smaller valid window is found.
6. Return the smallest window found.

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public static String minWindow(String s, String p) {
        String result = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char ch : p.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }

            for (int j = i; j < s.length(); j++) {
                char curr = s.charAt(j);
                if (map.containsKey(curr)) {
                    map.put(curr, map.get(curr) - 1);
                    if (map.get(curr) == 0) {
                        map.remove(curr);
                    }
                }

                if (map.isEmpty()) {
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                    break;
                }
            }
        }

        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `s = "ADOBECODEBANC"` and `p = "ABC"`.

| Step | i | j | Current Window | Map State | Action |
|------|---|---|----------------|-----------|--------|
| 1    | 0 | 0 | "A"            | {A:0, B:1, C:1} | Decrement A, remove A |
| 2    | 0 | 1 | "AD"           | {B:1, C:1} | No action |
| 3    | 0 | 2 | "ADO"          | {B:1, C:1} | No action |
| 4    | 0 | 3 | "ADOB"         | {C:1} | Decrement B, remove B |
| 5    | 0 | 4 | "ADOBE"        | {C:1} | No action |
| 6    | 0 | 5 | "ADOBEC"       | {} | Map empty, update result to "ADOBEC" |
| 7    | 1 | 1 | "D"            | {A:1, B:1, C:1} | No action |
| 8    | 1 | 2 | "DO"           | {A:1, B:1, C:1} | No action |
| 9    | 1 | 3 | "DOB"          | {A:1, C:1} | Decrement B, remove B |
| 10   | 1 | 4 | "DOBE"         | {A:1, C:1} | No action |
| 11   | 1 | 5 | "DOBEC"        | {A:1} | Decrement C, remove C |
| 12   | 1 | 6 | "DOBECO"       | {A:1} | No action |
| 13   | 1 | 7 | "DOBECOD"      | {A:1} | No action |
| 14   | 1 | 8 | "DOBECODE"     | {A:1} | No action |
| 15   | 1 | 9 | "DOBECODEB"    | {A:1} | No action |
| 16   | 1 | 10 | "DOBECODEBA"   | {} | Decrement A, map empty, update result to "DOBECODEBA" |
| 17   | 2 | 2 | "O"            | {A:1, B:1, C:1} | No action |
| 18   | 2 | 3 | "OB"           | {A:1, C:1} | Decrement B, remove B |
| 19   | 2 | 4 | "OBE"          | {A:1, C:1} | No action |
| 20   | 2 | 5 | "OBEC"         | {A:1} | Decrement C, remove C |
| 21   | 2 | 6 | "OBECO"        | {A:1} | No action |
| 22   | 2 | 7 | "OBECODE"      | {A:1} | No action |
| 23   | 2 | 8 | "OBECODEB"      | {A:1} | No action |
| 24   | 2 | 9 | "OBECODEBA"     | {} | Decrement A, map empty, update result to "OBECODEBA" |
| 25   | 3 | 3 | "B"            | {A:1, B:0, C:1} | Decrement B, remove B |
| 26   | 3 | 4 | "BE"           | {A:1, C:1} | No action |
| 27   | 3 | 5 | "BEC"          | {A:1} | Decrement C, remove C |
| 28   | 3 | 6 | "BECO"         | {A:1} | No action |
| 29   | 3 | 7 | "BECOD"        | {A:1} | No action |
| 30   | 3 | 8 | "BECODEB"      | {A:1} | No action |
| 31   | 3 | 9 | "BECODEBA"     | {} | Decrement A, map empty, update result to "BECODEBA" |
| 32   | 4 | 4 | "E"            | {A:1, B:1, C:1} | No action |
| 33   | 4 | 5 | "EC"           | {A:1, B:1} | No action |
| 34   | 4 | 6 | "ECO"          | {A:1, B:1} | No action |
| 35   | 4 | 7 | "ECOD"         | {A:1, B:1} | No action |
| 36   | 4 | 8 | "ECODEB"       | {A:1} | Decrement B, remove B |
| 37   | 4 | 9 | "ECODEBA"      | {} | Decrement A, map empty, update result to "ECODEBA" |
| 38   | 5 | 5 | "C"            | {A:1, B:1, C:0} | Decrement C, remove C |
| 39   | 5 | 6 | "CO"           | {A:1, B:1} | No action |
| 40   | 5 | 7 | "COD"          | {A:1, B:1} | No action |
| 41   | 5 | 8 | "CODEB"        | {A:1} | Decrement B, remove B |
| 42   | 5 | 9 | "CODEBA"       | {} | Decrement A, map empty, update result to "CODEBA" |
| 43   | 6 | 6 | "O"            | {A:1, B:1, C:1} | No action |
| 44   | 6 | 7 | "OD"           | {A:1, B:1, C:1} | No action |
| 45   | 6 | 8 | "ODEB"         | {A:1, C:1} | Decrement B, remove B |
| 46   | 6 | 9 | "ODEBA"        | {C:1} | No action |
| 47   | 7 | 7 | "D"            | {A:1, B:1, C:1} | No action |
| 48   | 7 | 8 | "DEB"          | {A:1, C:1} | Decrement B, remove B |
| 49   | 7 | 9 | "DEBA"         | {C:1} | No action |
| 50   | 8 | 8 | "E"            | {A:1, B:1, C:1} | No action |
| 51   | 8 | 9 | "EB"           | {A:1, C:1} | Decrement B, remove B |
| 52   | 9 | 9 | "B"            | {A:1, B:0, C:1} | Decrement B, remove B |
| 53   | 10 | 10 | "A"            | {A:0, B:1, C:1} | Decrement A, remove A |

The brute force approach finds the smallest window as "CODEBA" with a length of 6.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique combined with a hash map to track the frequency of characters in `p`. The algorithm maintains a window in `s` that contains all characters of `p` and then shrinks the window from the left to find the smallest valid window.

## 🔹 Why This Works

The sliding window technique ensures that we only traverse the string `s` once, making the algorithm efficient. The hash map helps in tracking the characters of `p` and their frequencies, allowing us to determine when the current window contains all characters of `p`.

## 🔹 Algorithm

1. Initialize a hash map to store the frequency of characters in `p`.
2. Initialize two pointers, `left` and `right`, to represent the current window in `s`.
3. Initialize a variable `count` to keep track of the number of unique characters from `p` that are included in the current window.
4. Iterate over `s` with the `right` pointer, expanding the window to the right.
5. For each character at the `right` pointer, if it is in `p`, decrement its frequency in the hash map. If the frequency becomes zero, decrement `count`.
6. When `count` becomes zero, it means all characters of `p` are included in the current window. Shrink the window from the left to find the smallest valid window.
7. For each character at the `left` pointer, if it is in `p`, increment its frequency in the hash map. If the frequency becomes positive, increment `count`.
8. Update the result if the current window is smaller than the smallest window found so far.
9. Return the smallest window found.

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public static String minWindow(String s, String p) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : p.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int count = map.size();
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;

        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);

            if (map.containsKey(curr)) {
                map.put(curr, map.get(curr) - 1);

                if (map.get(curr) == 0) {
                    count--;
                }
            }

            while (count == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);

                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);

                    if (map.get(leftChar) > 0) {
                        count++;
                    }
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s = "ADOBECODEBANC"` and `p = "ABC"`.

| Step | Left | Right | Current Window | Map State | Count | Action |
|------|------|-------|----------------|-----------|-------|--------|
| 1    | 0    | 0     | "A"            | {A:0, B:1, C:1} | 2 | Decrement A, count becomes 1 |
| 2    | 0    | 1     | "AD"           | {A:0, B:1, C:1} | 2 | No action |
| 3    | 0    | 2     | "ADO"          | {A:0, B:1, C:1} | 2 | No action |
| 4    | 0    | 3     | "ADOB"         | {A:0, C:1} | 1 | Decrement B, count becomes 0 |
| 5    | 0    | 4     | "ADOBE"        | {A:0, C:1} | 1 | No action |
| 6    | 0    | 5     | "ADOBEC"       | {} | 0 | Decrement C, count becomes -1 |
| 7    | 1    | 5     | "DOBEC"        | {A:1, C:1} | 1 | Increment A, count becomes 1 |
| 8    | 2    | 5     | "OBEC"         | {A:1, C:1} | 1 | No action |
| 9    | 2    | 6     | "OBECO"        | {A:1, C:1} | 1 | No action |
| 10   | 2    | 7     | "OBECODE"      | {A:1, C:1} | 1 | No action |
| 11   | 2    | 8     | "OBECODEB"     | {A:1} | 0 | Decrement B, count becomes -1 |
| 12   | 3    | 8     | "BECODEB"      | {A:1} | 0 | Increment B, count becomes 0 |
| 13   | 3    | 9     | "BECODEBA"     | {} | -1 | Decrement A, count becomes -2 |
| 14   | 4    | 9     | "ECODEBA"      | {A:1} | 0 | Increment A, count becomes 0 |
| 15   | 5    | 9     | "CODEBA"       | {A:1} | 0 | Increment C, count becomes 0 |
| 16   | 6    | 9     | "ODEBA"        | {A:1} | 0 | Increment O, count becomes 0 |
| 17   | 7    | 9     | "DEBA"         | {A:1} | 0 | Increment D, count becomes 0 |
| 18   | 8    | 9     | "EBA"          | {A:1} | 0 | Increment E, count becomes 0 |
| 19   | 9    | 9     | "BA"           | {A:1} | 0 | Increment B, count becomes 0 |
| 20   | 10   | 9     | "A"            | {A:0} | -1 | Increment A, count becomes -1 |

The optimal approach finds the smallest window as "CODEBA" with a length of 6.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Input**: If `s` or `p` is empty, return an empty string.
- **No Valid Window**: If `s` does not contain all characters of `p`, return an empty string.
- **All Characters Same**: If `s` and `p` consist of the same character repeated, return the substring of `s` with length equal to `p.length()`.
- **Large Input**: Ensure the algorithm handles large input sizes efficiently.
- **Duplicate Characters**: Handle cases where `p` contains duplicate characters.

---

# 📚 Key Takeaways

- The sliding window technique is efficient for problems involving substrings.
- Using a hash map to track character frequencies helps in maintaining the window constraints.
- The optimal approach ensures linear time complexity, making it suitable for large input sizes.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - What if the characters in `p` are not unique?
  - How would you handle cases where `p` is very large compared to `s`?
- **Common Pitfalls**:
  - Forgetting to decrement the count when a character's frequency becomes zero.
  - Not updating the result when a smaller window is found.
- **Alternative Approaches**:
  - Using a fixed-size window and sliding it across the string.
  - Using two pointers to maintain the window.

---

# ✅ Conclusion

The optimal sliding window approach efficiently finds the smallest window in `s` that contains all characters of `p`. The key insight is to maintain a window that satisfies the condition and then shrink it to find the minimum window. This approach ensures optimal time complexity and is suitable for large input sizes.