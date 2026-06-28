# Majority Element

---

# 📝 Problem Statement

Given an array of integers, find the majority element which appears more than ⌊ n/2 ⌋ times. The array may contain duplicate elements and negative numbers.

**Input:** An array of integers `arr[]`.

**Output:** The majority element if it exists, otherwise `-1`.

**Constraints:**
- The array may contain up to 10^5 elements.
- The elements can be any 32-bit integer.

---

# 💡 Intuition

The problem requires finding an element that appears more than half the time in the array. The optimal solution uses the Boyer-Moore Voting Algorithm, which efficiently finds the majority element in linear time with constant space. The key insight is that the majority element will always survive the elimination process where we cancel out pairs of different elements.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every element in the array and counting its occurrences. For each element, we count how many times it appears in the array. If any element's count exceeds half the array length, we return it. If no such element is found after checking all elements, we return `-1`.

---

## 🔹 Algorithm

1. Initialize a variable to store the maximum count and a variable to store the majority element.
2. Iterate through each element in the array.
3. For each element, count its occurrences in the array.
4. If the count exceeds half the array length, update the majority element and break out of the loop.
5. If no majority element is found after the loop, return `-1`.

---

## 🔹 Code

```java
class Solution {
    int majorityElement(int arr[]) {
        int maxCount = 0;
        int majorityElement = -1;

        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                majorityElement = arr[i];
            }
        }

        return (maxCount > arr.length / 2) ? majorityElement : -1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the input array `[3, 3, 4, 2, 4, 4, 2, 4, 4]`.

| Iteration | Current Element | Count | Majority Element | Max Count |
|---|---|---|---|---|
| 1 | 3 | 2 | 3 | 2 |
| 2 | 4 | 5 | 4 | 5 |
| 3 | 2 | 2 | 4 | 5 |
| 4 | 4 | 5 | 4 | 5 |

The majority element is `4` with a count of 5, which is greater than half the array length (4.5). Therefore, the algorithm returns `4`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses the Boyer-Moore Voting Algorithm to find the majority element in linear time with constant space. The algorithm works by maintaining a candidate for the majority element and a count. For each element in the array, if the count is zero, we set the current element as the candidate. We then increment the count if the current element matches the candidate, otherwise decrement the count. After processing all elements, we verify if the candidate is indeed the majority element by counting its occurrences.

---

## 🔹 Why This Works

The algorithm works because the majority element will always survive the elimination process where we cancel out pairs of different elements. This is because the majority element appears more than half the time, so it will always be the last remaining element after all cancellations.

---

## 🔹 Algorithm

1. Initialize a candidate and a count to zero.
2. Iterate through each element in the array.
3. If the count is zero, set the current element as the candidate.
4. If the current element matches the candidate, increment the count; otherwise, decrement the count.
5. After the loop, verify the candidate by counting its occurrences in the array.
6. If the count exceeds half the array length, return the candidate; otherwise, return `-1`.

---

## 🔹 Code

```java
class Solution {
    int majorityElement(int arr[]) {
        int candidate = 0;
        int count = 0;

        // Find potential majority candidate
        for (int num : arr) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Verify the candidate
        count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }

        return (count > arr.length / 2) ? candidate : -1;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the input array `[3, 3, 4, 2, 4, 4, 2, 4, 4]`.

| Iteration | Current Element | Candidate | Count |
|---|---|---|---|
| 1 | 3 | 3 | 1 |
| 2 | 3 | 3 | 2 |
| 3 | 4 | 3 | 1 |
| 4 | 2 | 3 | 0 |
| 5 | 4 | 4 | 1 |
| 6 | 4 | 4 | 2 |
| 7 | 2 | 4 | 1 |
| 8 | 4 | 4 | 2 |
| 9 | 4 | 4 | 3 |

After the first loop, the candidate is `4` with a count of 3. In the verification step, we count the occurrences of `4` in the array, which is 5. Since 5 is greater than half the array length (4.5), the algorithm returns `4`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array:** The algorithm should return `-1`.
- **Single Element:** The algorithm should return the single element.
- **All Elements Same:** The algorithm should return the single element.
- **No Majority Element:** The algorithm should return `-1`.
- **Negative Numbers:** The algorithm should handle negative numbers correctly.
- **Large Input Size:** The algorithm should handle large input sizes efficiently.

---

# 📚 Key Takeaways

- The Boyer-Moore Voting Algorithm is efficient for finding the majority element.
- The algorithm works in linear time with constant space.
- The majority element will always survive the elimination process.
- The verification step is necessary to ensure the candidate is indeed the majority element.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the majority element does not exist?
  - How would you modify the algorithm to find the majority element in a 2D array?
- **Common Pitfalls:**
  - Forgetting to verify the candidate after the first loop.
  - Not handling the case where no majority element exists.
- **Alternative Approaches:**
  - Using a hash map to count occurrences of each element.
  - Sorting the array and checking the middle element.

---

# ✅ Conclusion

The optimal solution using the Boyer-Moore Voting Algorithm is efficient and works in linear time with constant space. The key insight is that the majority element will always survive the elimination process, making it the most efficient approach for this problem.