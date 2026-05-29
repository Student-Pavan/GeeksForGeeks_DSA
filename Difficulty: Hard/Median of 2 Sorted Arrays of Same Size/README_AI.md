# Median of 2 Sorted Arrays of Same Size

---

# 📝 Problem Statement

Given two sorted arrays of equal size, find the median of the combined sorted array.

**Objective**: Compute the median efficiently without merging the arrays.

**Input**:
- Two sorted integer arrays `a[]` and `b[]` of equal size `n`

**Output**:
- The median of the combined sorted array

**Constraints**:
- Arrays are sorted in non-decreasing order
- Arrays contain only integers
- Arrays are of equal size

---

# 💡 Intuition

The key insight is that the median of two sorted arrays of equal size can be found by comparing elements from both arrays until we reach the middle elements. For even-sized arrays, the median is the average of the two middle elements. This approach avoids the O(n) time complexity of merging the arrays by leveraging the sorted property of the inputs.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Merge the two sorted arrays into a single sorted array
2. Find the median of the merged array

## 🔹 Algorithm

1. Initialize an empty array `merged` of size `2n`
2. Use two pointers `i` and `j` to traverse `a[]` and `b[]`
3. Compare elements at `a[i]` and `b[j]`, add the smaller one to `merged`
4. When one array is exhausted, add remaining elements from the other array
5. Calculate median based on whether `2n` is even or odd

## 🔹 Code

```java
class Solution {
    public double medianOf2(int a[], int b[]) {
        int n = a.length;
        int[] merged = new int[2 * n];
        int i = 0, j = 0, k = 0;

        while (i < n && j < n) {
            if (a[i] < b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }

        while (i < n) {
            merged[k++] = a[i++];
        }

        while (j < n) {
            merged[k++] = b[j++];
        }

        if ((2 * n) % 2 == 0) {
            return (merged[n - 1] + merged[n]) / 2.0;
        } else {
            return merged[n];
        }
    }
}
```

## 🔹 Dry Run

Let's dry run with `a = [1, 3, 5]` and `b = [2, 4, 6]`:

| Step | i | j | k | merged | Action |
|------|---|---|---|--------|--------|
| 1    | 0 | 0 | 0 | [1]    | a[0] < b[0] → merged[0] = 1, i++, k++ |
| 2    | 1 | 0 | 1 | [1, 2] | a[1] > b[0] → merged[1] = 2, j++, k++ |
| 3    | 1 | 1 | 2 | [1, 2, 3] | a[1] < b[1] → merged[2] = 3, i++, k++ |
| 4    | 2 | 1 | 3 | [1, 2, 3, 4] | a[2] > b[1] → merged[3] = 4, j++, k++ |
| 5    | 2 | 2 | 4 | [1, 2, 3, 4, 5] | a[2] < b[2] → merged[4] = 5, i++, k++ |
| 6    | 3 | 2 | 5 | [1, 2, 3, 4, 5, 6] | i == n → add remaining b[2] = 6 |

Median = (merged[2] + merged[3]) / 2 = (3 + 4) / 2 = 3.5

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use two pointers to traverse both arrays simultaneously
2. Track the two middle elements as we go
3. When we reach the middle position, return the average of the two middle elements

## 🔹 Why This Works

This approach works because both arrays are sorted. By comparing elements from both arrays at each step, we can efficiently find the middle elements without merging the arrays. We only need to track the two middle elements, which reduces the space complexity to O(1).

## 🔹 Algorithm

1. Initialize two pointers `i` and `j` to 0
2. Initialize variables `m1` and `m2` to -1
3. Traverse both arrays until we reach the middle position
4. At each step, compare elements at `a[i]` and `b[j]`
5. Update `m2` to `m1` and set `m1` to the smaller element
6. Increment the pointer of the array from which we took the element
7. When one array is exhausted, take elements from the remaining array
8. Return the average of `m1` and `m2`

## 🔹 Code

```java
class Solution {
    public double medianOf2(int a[], int b[]) {
        int i = 0, j = 0;
        int m1 = -1, m2 = -1;
        int n = a.length;

        for (int count = 0; count <= n; count++) {
            m2 = m1;

            if (i < n && j < n) {
                if (a[i] < b[j]) {
                    m1 = a[i++];
                } else {
                    m1 = b[j++];
                }
            } else if (i < n) {
                m1 = a[i++];
            } else {
                m1 = b[j++];
            }
        }

        return (m1 + m2) / 2.0;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run with `a = [1, 3, 5]` and `b = [2, 4, 6]`:

| Step | i | j | m1 | m2 | Action |
|------|---|---|----|----|--------|
| 1    | 0 | 0 | 1  | -1 | a[0] < b[0] → m1 = 1, i++ |
| 2    | 1 | 0 | 2  | 1  | a[1] > b[0] → m1 = 2, j++ |
| 3    | 1 | 1 | 3  | 2  | a[1] < b[1] → m1 = 3, i++ |
| 4    | 2 | 1 | 4  | 3  | a[2] > b[1] → m1 = 4, j++ |
| 5    | 2 | 2 | 5  | 4  | a[2] < b[2] → m1 = 5, i++ |
| 6    | 3 | 2 | 6  | 5  | i == n → m1 = 6 |

Median = (m1 + m2) / 2 = (6 + 5) / 2 = 5.5

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

1. Arrays with duplicate elements
2. Arrays with negative numbers
3. Arrays with very large numbers
4. Arrays where one element is much larger than others
5. Arrays with all identical elements
6. Arrays with single element each

---

# 📚 Key Takeaways

1. The optimal approach leverages the sorted property of the input arrays to find the median in O(n) time with O(1) space.
2. The brute force approach is straightforward but uses O(n) space to merge the arrays.
3. The optimal approach is more space-efficient and demonstrates how to solve the problem without merging the arrays.
4. Understanding the sorted property of the input arrays is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

1. Ask if the arrays are guaranteed to be sorted to confirm the optimal approach is applicable.
2. Discuss the trade-offs between the brute force and optimal approaches.
3. Consider how the solution would change if the arrays were of different sizes.
4. Practice explaining the algorithm with a whiteboard to ensure clarity.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently finds the median without merging the arrays, reducing space complexity. The key insight is leveraging the sorted property of the input arrays to track the middle elements during traversal. This approach demonstrates both time and space optimization in solving the problem.