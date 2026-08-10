# 📌 Duplicates in a Limited Range Array

---

# 📝 Problem Statement

Given an array of integers where each element is in the range `[1, n]` and `n` is the size of the array, find all the duplicates in the array. The solution should return a list of all duplicate numbers.

**Constraints:**
- The array length `n` is between 1 and 10^5.
- The elements in the array are between 1 and `n`.
- The array may contain duplicates.

---

# 💡 Intuition

The key insight here is that since the elements are in the range `[1, n]`, we can use the array indices to mark the presence of elements. By using the indices to track which numbers have been seen, we can efficiently find duplicates without using extra space for a hash map.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking each element and comparing it with every other element to find duplicates. This approach is straightforward but inefficient.

---

## 🔹 Algorithm

1. Initialize an empty list to store duplicates.
2. Iterate through each element in the array.
3. For each element, iterate through the rest of the array to find duplicates.
4. If a duplicate is found, add it to the list.
5. Return the list of duplicates.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j] && !duplicates.contains(arr[i])) {
                    duplicates.add(arr[i]);
                }
            }
        }
        return duplicates;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the brute force approach with the input array `[4, 3, 2, 7, 8, 2, 3, 1]`.

| Iteration | i | j | arr[i] | arr[j] | Action | Duplicates |
|---|---|---|---|---|---|---|
| 1 | 0 | 1 | 4 | 3 | No match | [] |
| 2 | 0 | 2 | 4 | 2 | No match | [] |
| 3 | 0 | 3 | 4 | 7 | No match | [] |
| 4 | 0 | 4 | 4 | 8 | No match | [] |
| 5 | 0 | 5 | 4 | 2 | No match | [] |
| 6 | 0 | 6 | 4 | 3 | No match | [] |
| 7 | 0 | 7 | 4 | 1 | No match | [] |
| 8 | 1 | 2 | 3 | 2 | No match | [] |
| 9 | 1 | 3 | 3 | 7 | No match | [] |
| 10 | 1 | 4 | 3 | 8 | No match | [] |
| 11 | 1 | 5 | 3 | 2 | No match | [] |
| 12 | 1 | 6 | 3 | 3 | Match, add 3 | [3] |
| 13 | 1 | 7 | 3 | 1 | No match | [3] |
| 14 | 2 | 3 | 2 | 7 | No match | [3] |
| 15 | 2 | 4 | 2 | 8 | No match | [3] |
| 16 | 2 | 5 | 2 | 2 | Match, add 2 | [3, 2] |
| 17 | 2 | 6 | 2 | 3 | No match | [3, 2] |
| 18 | 2 | 7 | 2 | 1 | No match | [3, 2] |
| 19 | 3 | 4 | 7 | 8 | No match | [3, 2] |
| 20 | 3 | 5 | 7 | 2 | No match | [3, 2] |
| 21 | 3 | 6 | 7 | 3 | No match | [3, 2] |
| 22 | 3 | 7 | 7 | 1 | No match | [3, 2] |
| 23 | 4 | 5 | 8 | 2 | No match | [3, 2] |
| 24 | 4 | 6 | 8 | 3 | No match | [3, 2] |
| 25 | 4 | 7 | 8 | 1 | No match | [3, 2] |
| 26 | 5 | 6 | 2 | 3 | No match | [3, 2] |
| 27 | 5 | 7 | 2 | 1 | No match | [3, 2] |
| 28 | 6 | 7 | 3 | 1 | No match | [3, 2] |

The final list of duplicates is `[3, 2]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using the array indices to mark the presence of elements. By negating the value at the index corresponding to the current element, we can track which numbers have been seen. If we encounter a negative value at an index, it means the number corresponding to that index has been seen before, and we add it to the list of duplicates.

---

## 🔹 Why This Works

This approach works because the elements are in the range `[1, n]`, so we can use the array indices to store information about the presence of elements. By negating the value at the index corresponding to the current element, we can mark that the element has been seen. If we encounter a negative value at an index, it means the element corresponding to that index has been seen before, and we add it to the list of duplicates.

---

## 🔹 Algorithm

1. Initialize an empty list to store duplicates.
2. Iterate through each element in the array.
3. For each element, calculate the index as the absolute value of the current element minus one.
4. If the value at the calculated index is negative, add the absolute value of the current element to the list of duplicates.
5. Otherwise, negate the value at the calculated index.
6. Return the list of duplicates.

---

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> duplicates = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] < 0) {
                duplicates.add(Math.abs(arr[i]));
            } else {
                arr[index] = -arr[index];
            }
        }
        return duplicates;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input array `[4, 3, 2, 7, 8, 2, 3, 1]`.

| Iteration | i | arr[i] | index | arr[index] | Action | Duplicates |
|---|---|---|---|---|---|---|
| 1 | 0 | 4 | 3 | 7 | arr[3] = -7 | [] |
| 2 | 1 | 3 | 2 | 2 | arr[2] = -2 | [] |
| 3 | 2 | -2 | 1 | 3 | arr[1] = -3 | [] |
| 4 | 3 | 7 | 6 | 3 | arr[6] = -3 | [] |
| 5 | 4 | 8 | 7 | 1 | arr[7] = -1 | [] |
| 6 | 5 | 2 | 1 | -3 | arr[1] is negative, add 2 | [2] |
| 7 | 6 | -3 | 2 | -2 | arr[2] is negative, add 3 | [2, 3] |
| 8 | 7 | -1 | 0 | 4 | arr[0] = -4 | [2, 3] |

The final list of duplicates is `[2, 3]`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Input:** The array is empty. The solution should return an empty list.
- **Single Element:** The array contains only one element. The solution should return an empty list since there are no duplicates.
- **All Duplicates:** All elements in the array are the same. The solution should return a list containing that element.
- **No Duplicates:** The array contains all unique elements. The solution should return an empty list.
- **Large Constraints:** The array size is very large (e.g., 10^5). The solution should handle it efficiently.

---

# 📚 Key Takeaways

- The brute force approach is simple but inefficient, with a time complexity of O(n^2).
- The optimal approach uses the array indices to mark the presence of elements, achieving a time complexity of O(n) and a space complexity of O(1).
- The optimal approach is suitable for large input sizes due to its linear time complexity.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you solve the problem without modifying the input array?
  - Can you solve the problem using a hash map?
- **Common Pitfalls:**
  - Forgetting to handle negative values correctly.
  - Not considering the edge case where all elements are the same.
- **Alternative Approaches:**
  - Using a hash map to track the frequency of each element.
  - Sorting the array and then checking for adjacent duplicates.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently finds duplicates in linear time without using extra space. The key insight is using the array indices to mark the presence of elements, making it suitable for large input sizes.