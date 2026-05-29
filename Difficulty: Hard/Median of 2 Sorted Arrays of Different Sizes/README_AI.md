# 📌 Median of Two Sorted Arrays of Different Sizes

---

# 📝 Problem Statement

Given two sorted arrays of different sizes, find the median of the combined sorted array. The median is the middle value if the combined array has an odd length, or the average of the two middle values if the combined array has an even length.

**Constraints:**
- The arrays can be of different sizes.
- The arrays are sorted in non-decreasing order.
- The solution should be efficient for large input sizes.

---

# 💡 Intuition

The key insight is to find the median without merging the arrays, which would take O(m+n) time. Instead, we can use a binary search approach to partition the arrays such that the left half of the combined array contains elements less than or equal to the right half. This allows us to find the median in O(log(min(m,n))) time.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Merge the two sorted arrays into a single sorted array.
2. Find the median of the merged array based on its length.

## 🔹 Algorithm

1. Initialize an empty array to store the merged result.
2. Use two pointers to traverse both arrays and merge them in sorted order.
3. If one array is exhausted, append the remaining elements of the other array.
4. Calculate the median based on the length of the merged array.

## 🔹 Code

```java
class Solution {
    public double medianOf2(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int[] merged = new int[n + m];
        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (a[i] < b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }

        while (i < n) {
            merged[k++] = a[i++];
        }

        while (j < m) {
            merged[k++] = b[j++];
        }

        if ((n + m) % 2 == 0) {
            return (merged[(n + m) / 2 - 1] + merged[(n + m) / 2]) / 2.0;
        } else {
            return merged[(n + m) / 2];
        }
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with the following arrays:
- `a = [1, 3, 5]`
- `b = [2, 4, 6, 7]`

| Step | i | j | k | merged | Action |
|------|---|---|---|--------|--------|
| 1    | 0 | 0 | 0 | [1]    | a[0] < b[0], merged[0] = 1 |
| 2    | 1 | 0 | 1 | [1, 2] | b[0] < a[1], merged[1] = 2 |
| 3    | 1 | 1 | 2 | [1, 2, 3] | a[1] < b[1], merged[2] = 3 |
| 4    | 2 | 1 | 3 | [1, 2, 3, 4] | b[1] < a[2], merged[3] = 4 |
| 5    | 2 | 2 | 4 | [1, 2, 3, 4, 5] | a[2] < b[2], merged[4] = 5 |
| 6    | 3 | 2 | 5 | [1, 2, 3, 4, 5, 6] | j < m, merged[5] = 6 |
| 7    | 3 | 3 | 6 | [1, 2, 3, 4, 5, 6, 7] | j < m, merged[6] = 7 |

The merged array is `[1, 2, 3, 4, 5, 6, 7]`. The median is `(4 + 5) / 2 = 4.5`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(m + n) |
| Space Complexity | O(m + n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Use binary search to partition the smaller array.
2. Find the correct partition in the larger array such that the combined left partitions of both arrays contain elements less than or equal to the combined right partitions.
3. Calculate the median based on the elements around the partition.

## 🔹 Why This Works

By partitioning the smaller array, we ensure that the binary search runs in O(log(min(m,n))) time. The correct partition in the larger array can be determined by comparing the elements around the partition, ensuring the left partitions contain the smaller elements.

## 🔹 Algorithm

1. Ensure the first array is the smaller one to minimize the binary search steps.
2. Perform binary search on the smaller array to find the correct partition.
3. Calculate the partition in the larger array based on the partition in the smaller array.
4. Determine the maximum elements on the left side and the minimum elements on the right side of the partition.
5. Calculate the median based on whether the combined length is even or odd.

## 🔹 Code

```java
class Solution {
    public double medianOf2(int a[], int b[]) {
        if (a.length > b.length) {
            return medianOf2(b, a);
        }

        int n = a.length;
        int m = b.length;
        int low = 0, high = n;

        while (low <= high) {
            int partitionA = (low + high) / 2;
            int partitionB = (n + m + 1) / 2 - partitionA;

            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : a[partitionA - 1];
            int minRightA = (partitionA == n) ? Integer.MAX_VALUE : a[partitionA];

            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : b[partitionB - 1];
            int minRightB = (partitionB == m) ? Integer.MAX_VALUE : b[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                high = partitionA - 1;
            } else {
                low = partitionA + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the following arrays:
- `a = [1, 3, 5]`
- `b = [2, 4, 6, 7]`

| Iteration | low | high | partitionA | partitionB | maxLeftA | minRightA | maxLeftB | minRightB | Condition |
|-----------|-----|------|------------|------------|----------|-----------|----------|-----------|-----------|
| 1         | 0   | 3    | 1          | 3          | 1        | 3         | 6        | 7         | maxLeftA > minRightB |
| 2         | 0   | 0    | 0          | 4          | -∞       | 1         | 7        | ∞         | maxLeftB > minRightA |
| 3         | 1   | 0    | -          | -          | -        | -         | -        | -         | low > high |

The algorithm determines that the correct partition is `partitionA = 1` and `partitionB = 3`. The median is calculated as `(Math.max(1, 6) + Math.min(3, 7)) / 2.0 = (6 + 3) / 2.0 = 4.5`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log(min(m, n))) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array:** One of the arrays is empty.
- **Single Element Arrays:** Both arrays have a single element.
- **Duplicate Elements:** Arrays contain duplicate elements.
- **Large Arrays:** Arrays are very large, ensuring the solution is efficient.
- **Equal Length Arrays:** Both arrays have the same length.
- **Different Length Arrays:** Arrays have significantly different lengths.

---

# 📚 Key Takeaways

- **Binary Search:** Efficiently partition the smaller array to find the median without merging.
- **Partitioning:** Correctly partition both arrays to ensure the left partitions contain the smaller elements.
- **Median Calculation:** Handle both even and odd combined lengths appropriately.
- **Efficiency:** Optimize the solution to run in O(log(min(m, n))) time.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the arrays are not sorted?
  - How would you handle very large arrays?
  - Can you solve it in O(1) space complexity?
- **Common Pitfalls:**
  - Forgetting to handle edge cases.
  - Incorrectly calculating the partition in the larger array.
  - Not considering the combined length for median calculation.
- **Alternative Approaches:**
  - Using two pointers to merge the arrays and find the median.
  - Using a max-heap and min-heap to find the median.

---

# ✅ Conclusion

The optimal approach using binary search is more efficient than the brute force method, especially for large arrays. By partitioning the smaller array and correctly determining the partition in the larger array, we can find the median in logarithmic time. This approach is both time and space efficient, making it suitable for large input sizes.