# Alternates in an Array

---

# 📝 Problem Statement

Given an array of integers, the task is to return an array containing every alternate element of the given array starting from the first element. The solution should efficiently collect these elements without modifying the original array.

**Objective:**
Create a function that extracts every alternate element from the input array.

**Input:**
- An array of integers `arr[]`.

**Output:**
- An array containing every alternate element starting from the first element.

**Constraints:**
- The array can contain up to 10^5 elements.
- The elements in the array can be any integer within the range of -10^9 to 10^9.

---

# 💡 Intuition

The problem requires collecting every alternate element starting from the first element. The key insight is that we can iterate through the array with a step of 2, starting from index 0. This approach ensures that we only pick the required elements efficiently.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves iterating through the array and collecting every alternate element starting from the first element. This can be achieved by using a simple loop that increments the index by 2 in each iteration.

## 🔹 Algorithm

1. Initialize an empty list to store the result.
2. Iterate through the array starting from index 0, incrementing the index by 2 in each iteration.
3. In each iteration, add the current element to the result list.
4. Return the result list after the loop completes.

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getAlternates(int arr[]) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 2) {
            result.add(arr[i]);
        }
        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the code with the input array `[1, 2, 3, 4, 5, 6]`.

| Iteration | Index | Current Element | Result |
|-----------|-------|-----------------|--------|
| 1         | 0     | 1               | [1]    |
| 2         | 2     | 3               | [1, 3] |
| 3         | 4     | 5               | [1, 3, 5] |

After the loop completes, the result list is `[1, 3, 5]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but is more concise and efficient. It involves using a loop to iterate through the array with a step of 2, starting from index 0, and collecting the elements into a result list.

## 🔹 Why This Works

This approach works because it directly accesses the required elements by skipping every alternate element. This ensures that we only traverse the necessary elements and collect them efficiently.

## 🔹 Algorithm

1. Initialize an empty list to store the result.
2. Iterate through the array starting from index 0, incrementing the index by 2 in each iteration.
3. In each iteration, add the current element to the result list.
4. Return the result list after the loop completes.

## 🔹 Code

```java
import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getAlternates(int arr[]) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 2) {
            result.add(arr[i]);
        }
        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code with the input array `[1, 2, 3, 4, 5, 6]`.

| Iteration | Index | Current Element | Result |
|-----------|-------|-----------------|--------|
| 1         | 0     | 1               | [1]    |
| 2         | 2     | 3               | [1, 3] |
| 3         | 4     | 5               | [1, 3, 5] |

After the loop completes, the result list is `[1, 3, 5]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Array:** The function should return an empty list.
- **Single Element Array:** The function should return a list containing the single element.
- **Even Length Array:** The function should return a list containing every alternate element starting from the first element.
- **Odd Length Array:** The function should return a list containing every alternate element starting from the first element, excluding the last element if the length is odd.

---

# 📚 Key Takeaways

- The problem can be efficiently solved by iterating through the array with a step of 2.
- The optimal approach ensures that we only traverse the necessary elements and collect them efficiently.
- Understanding the problem requirements and constraints is crucial for choosing the right approach.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can the solution be modified to start from the second element instead of the first?
  - How would you handle very large arrays efficiently?
- **Common Pitfalls:**
  - Forgetting to increment the index by 2 in each iteration.
  - Not initializing the result list properly.
- **Alternative Approaches:**
  - Using a while loop instead of a for loop.
  - Using Java streams to filter elements.

---

# ✅ Conclusion

The optimal solution efficiently collects every alternate element starting from the first element by iterating through the array with a step of 2. This approach ensures that we only traverse the necessary elements and collect them efficiently, making it suitable for large arrays as well. The key insight is to leverage the step increment in the loop to directly access the required elements.