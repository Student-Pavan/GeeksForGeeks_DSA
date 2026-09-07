# 📌 Smallest Window with All

---

# 📝 Problem Statement

Given two strings `s` and `p`, find the smallest substring in `s` that contains all characters of `p` (including duplicates).

**Objective**: Return the smallest substring of `s` that contains all characters of `p`. If no such substring exists, return an empty string.

**Constraints**:
- `1 <= s.length, p.length <= 10^5`
- `s` and `p` consist of uppercase and lowercase English letters.

---

# 💡 Intuition

The problem requires finding the smallest window in `s` that contains all characters of `p`. The optimal approach involves using a sliding window technique combined with a hash map to track character frequencies. The key insight is to maintain a window that contains all characters of `p` with their required frequencies, then shrink the window from the left to find the smallest valid window.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking all possible substrings of `s` to find the smallest one that contains all characters of `p`. For each starting index, we expand the window to the right until all characters of `p` are included, then we check if this window is the smallest found so far.

---

## 🔹 Algorithm

1. Initialize `minLen` to a large value and `result` to an empty string.
2. Iterate over each character in `s` as the starting index of the window.
3. For each starting index, expand the window to the right until all characters of `p` are included.
4. If a valid window is found, update `minLen` and `result` if the current window is smaller.
5. Return the smallest window found.

---

## 🔹 Code

```java
import java.util.HashMap;

class Solution {
    public static String minWindow(String s, String p) {
        int minLen = Integer.MAX_VALUE;
        String result = "";

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

---

## 🔹 Dry Run

Let's dry run the brute force approach with `s = "ADOBECODEBANC"` and `p = "ABC"`.

| Iteration | Start Index | End Index | Current Window | Valid? | Action |
|---|---|---|---|---|---|
| 1 | 0 | 0 | A | No | Expand |
| 1 | 0 | 1 | AD | No | Expand |
| 1 | 0 | 2 | ADO | No | Expand |
| 1 | 0 | 3 | ADOB | No | Expand |
| 1 | 0 | 4 | ADOBE | No | Expand |
| 1 | 0 | 5 | ADOBEC | No | Expand |
| 1 | 0 | 6 | ADOBECO | No | Expand |
| 1 | 0 | 7 | ADOBECOB | No | Expand |
| 1 | 0 | 8 | ADOBECOBA | No | Expand |
| 1 | 0 | 9 | ADOBECOBAN | No | Expand |
| 1 | 0 | 10 | ADOBECOBANC | Yes | Update result to "ADOBEC" |
| 2 | 1 | 1 | D | No | Expand |
| 2 | 1 | 2 | DO | No | Expand |
| 2 | 1 | 3 | DOB | No | Expand |
| 2 | 1 | 4 | DOBE | No | Expand |
| 2 | 1 | 5 | DOBEC | No | Expand |
| 2 | 1 | 6 | DOBECO | No | Expand |
| 2 | 1 | 7 | DOBECOB | No | Expand |
| 2 | 1 | 8 | DOBECOBA | No | Expand |
| 2 | 1 | 9 | DOBECOBAN | No | Expand |
| 2 | 1 | 10 | DOBECOBANC | Yes | Update result to "DOBECOBA" |
| ... | ... | ... | ... | ... | ... |
| 10 | 10 | 10 | C | No | Expand |

The brute force approach checks all possible substrings, resulting in a time complexity of O(n^2), where n is the length of `s`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(m) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique combined with a hash map to track character frequencies. We maintain a window that contains all characters of `p` with their required frequencies, then we shrink the window from the left to find the smallest valid window.

---

## 🔹 Why This Works

The sliding window technique allows us to efficiently track the characters in the current window and adjust the window size to find the smallest valid window. The hash map helps us keep track of the required characters and their frequencies, ensuring that we only consider valid windows.

---

## 🔹 Algorithm

1. Initialize a hash map to store the frequency of each character in `p`.
2. Initialize `left` pointer, `count` to track the number of unique characters to be matched, and `minLen` to a large value.
3. Iterate over `s` with the `right` pointer, expanding the window to the right.
4. For each character, if it is in the hash map, decrement its count. If the count reaches zero, decrement `count`.
5. When `count` is zero, it means all characters of `p` are included in the current window. Shrink the window from the left to find the smallest valid window.
6. Update `minLen` and the result if a smaller valid window is found.
7. Return the smallest window found.

---

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

                if (map.get(curr) == 0)
                    count--;
            }

            while (count == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);

                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);

                    if (map.get(leftChar) > 0)
                        count++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s = "ADOBECODEBANC"` and `p = "ABC"`.

| Step | Left | Right | Current Window | Count | Action | Result |
|---|---|---|---|---|---|---|
| 1 | 0 | 0 | A | 2 | Decrement A count to 0, count becomes 2 | - |
| 2 | 0 | 1 | AD | 2 | - | - |
| 3 | 0 | 2 | ADO | 2 | - | - |
| 4 | 0 | 3 | ADOB | 2 | - | - |
| 5 | 0 | 4 | ADOBE | 2 | - | - |
| 6 | 0 | 5 | ADOBEC | 2 | - | - |
| 7 | 0 | 6 | ADOBECO | 2 | - | - |
| 8 | 0 | 7 | ADOBECOB | 2 | Decrement B count to 0, count becomes 1 | - |
| 9 | 0 | 8 | ADOBECOBA | 1 | Decrement A count to -1, count remains 1 | - |
| 10 | 0 | 9 | ADOBECOBAN | 1 | Decrement C count to 0, count becomes 0 | Update minLen to 10, startIndex to 0 |
| 11 | 1 | 9 | DOBECOBAN | 1 | Increment A count to 0, count becomes 1 | - |
| 12 | 2 | 9 | OBECOBAN | 1 | - | - |
| 13 | 3 | 9 | BECOBAN | 1 | - | - |
| 14 | 4 | 9 | ECOBAN | 1 | - | - |
| 15 | 5 | 9 | COBAN | 1 | - | - |
| 16 | 6 | 9 | OBAN | 1 | - | - |
| 17 | 7 | 9 | BAN | 1 | Decrement B count to -1, count remains 1 | - |
| 18 | 8 | 9 | AN | 1 | Decrement C count to 1, count becomes 1 | - |
| 19 | 9 | 9 | N | 1 | - | - |
| 20 | 10 | 10 | C | 1 | Decrement C count to 0, count becomes 0 | Update minLen to 4, startIndex to 7 |

The optimal approach efficiently finds the smallest window in O(n) time, where n is the length of `s`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(m) |

---

# 🔍 Edge Cases

- **Empty Input**: If `s` or `p` is empty, return an empty string.
- **No Valid Window**: If no window in `s` contains all characters of `p`, return an empty string.
- **All Characters Same**: If all characters in `s` are the same and `p` consists of the same character, return the smallest window that contains all characters of `p`.
- **Large Input**: Ensure the solution handles large input sizes efficiently.

---

# 📚 Key Takeaways

- The sliding window technique is efficient for problems involving substrings or subarrays.
- Hash maps are useful for tracking character frequencies and ensuring all required characters are included in the window.
- The optimal approach reduces the time complexity from O(n^2) to O(n) by efficiently tracking the window and required characters.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss how to handle cases where `p` has duplicate characters.
- **Common Pitfalls**: Ensure the hash map is updated correctly when shrinking the window from the left.
- **Alternative Approaches**: Consider using arrays instead of hash maps for better performance in some cases.

---

# ✅ Conclusion

The optimal sliding window approach efficiently finds the smallest window in `s` that contains all characters of `p`. This approach is preferred due to its linear time complexity and efficient use of space. Understanding the sliding window technique and hash maps is crucial for solving such problems effectively.