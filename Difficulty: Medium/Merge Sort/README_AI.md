# 📌 Merge Sort

---

# 📝 Problem Statement

Merge Sort is a divide-and-conquer algorithm that sorts an array by recursively dividing it into smaller subarrays, sorting those subarrays, and then merging them back together.

**Objective**: Implement Merge Sort to sort an array of integers in ascending order.

**Input**:
- An unsorted array of integers `arr[]`

**Output**:
- The sorted array in ascending order

**Constraints**:
- The algorithm should sort the array in-place (if possible)
- The time complexity should be O(n log n)
- The space complexity should be O(n)

---

# 💡 Intuition

Merge Sort works by dividing the array into two halves, sorting each half recursively, and then merging the two sorted halves back together. The key insight is that merging two sorted arrays is straightforward and efficient, which allows the overall algorithm to achieve O(n log n) time complexity.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a simple sorting algorithm like Bubble Sort or Insertion Sort to sort the array. However, these algorithms have a time complexity of O(n²), which is inefficient for large arrays.

---

## 🔹 Algorithm

1. Iterate through the array from the first element to the last.
2. For each element, compare it with the next element.
3. If the current element is greater than the next element, swap them.
4. Repeat this process until the entire array is sorted.

---

## 🔹 Code

```java
class Solution {

    void bubbleSort(int arr[]) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
```

---

## 🔹 Dry Run

Let's dry run the Bubble Sort algorithm on the array `[5, 3, 8, 4, 2]`.

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | 5 | [5, 3, 8, 4, 2] | [3, 5, 8, 4, 2] |
| 2 | 5 | [3, 5, 8, 4, 2] | [3, 5, 4, 8, 2] |
| 3 | 5 | [3, 5, 4, 8, 2] | [3, 5, 4, 2, 8] |
| 4 | 3 | [3, 5, 4, 2, 8] | [3, 4, 5, 2, 8] |
| 5 | 4 | [3, 4, 5, 2, 8] | [3, 4, 2, 5, 8] |
| 6 | 4 | [3, 4, 2, 5, 8] | [3, 2, 4, 5, 8] |
| 7 | 3 | [3, 2, 4, 5, 8] | [2, 3, 4, 5, 8] |

After 7 iterations, the array is sorted as `[2, 3, 4, 5, 8]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is to use Merge Sort, which divides the array into two halves, sorts each half recursively, and then merges the two sorted halves back together. This approach has a time complexity of O(n log n), which is more efficient than the brute force approach.

---

## 🔹 Why This Works

Merge Sort works by dividing the array into smaller subarrays, sorting those subarrays, and then merging them back together. The merging process is efficient because it only requires comparing elements from the two sorted subarrays and placing them in the correct order. This ensures that the overall time complexity is O(n log n).

---

## 🔹 Algorithm

1. Divide the array into two halves.
2. Recursively sort the left half.
3. Recursively sort the right half.
4. Merge the two sorted halves back together.

---

## 🔹 Code

```java
class Solution {

    void mergeSort(int arr[], int l, int r) {

        if (l < r) {

            int mid = l + (r - l) / 2;

            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            merge(arr, l, mid, r);
        }
    }

    void merge(int[] arr, int left, int mid, int right) {

        int[] merged = new int[right - left + 1];

        int i = left;      // left half
        int j = mid + 1;   // right half
        int k = 0;

        // Merge both halves
        while (i <= mid && j <= right) {

            if (arr[i] <= arr[j]) {
                merged[k++] = arr[i++];
            } else {
                merged[k++] = arr[j++];
            }
        }

        // Remaining elements of left half
        while (i <= mid) {
            merged[k++] = arr[i++];
        }

        // Remaining elements of right half
        while (j <= right) {
            merged[k++] = arr[j++];
        }

        // Copy back to original array
        for (int x = 0; x < merged.length; x++) {
            arr[left + x] = merged[x];
        }
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the Merge Sort algorithm on the array `[5, 3, 8, 4, 2]`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 4 | Divide | [5, 3, 8, 4, 2] |
| 2 | 0 | 2 | Divide | [5, 3, 8, 4, 2] |
| 3 | 0 | 1 | Divide | [5, 3, 8, 4, 2] |
| 4 | 0 | 0 | Base case | [5, 3, 8, 4, 2] |
| 5 | 1 | 1 | Base case | [5, 3, 8, 4, 2] |
| 6 | 0 | 1 | Merge | [3, 5, 8, 4, 2] |
| 7 | 2 | 2 | Base case | [3, 5, 8, 4, 2] |
| 8 | 0 | 2 | Merge | [3, 5, 8, 4, 2] |
| 9 | 3 | 4 | Divide | [3, 5, 8, 4, 2] |
| 10 | 3 | 3 | Base case | [3, 5, 8, 4, 2] |
| 11 | 4 | 4 | Base case | [3, 5, 8, 4, 2] |
| 12 | 3 | 4 | Merge | [3, 5, 8, 2, 4] |
| 13 | 0 | 4 | Merge | [2, 3, 4, 5, 8] |

After 13 steps, the array is sorted as `[2, 3, 4, 5, 8]`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n log n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Array**: The algorithm should handle an empty array without any issues.
- **Single Element**: The algorithm should return the same array if it contains only one element.
- **Duplicate Elements**: The algorithm should handle arrays with duplicate elements correctly.
- **Negative Values**: The algorithm should sort arrays with negative values properly.
- **Large Constraints**: The algorithm should handle large arrays efficiently.
- **Sorted Input**: The algorithm should handle already sorted arrays efficiently.
- **Reverse Sorted Input**: The algorithm should handle reverse sorted arrays efficiently.

---

# 📚 Key Takeaways

- Merge Sort is a divide-and-conquer algorithm that sorts an array by recursively dividing it into smaller subarrays, sorting those subarrays, and then merging them back together.
- The key insight is that merging two sorted arrays is straightforward and efficient, which allows the overall algorithm to achieve O(n log n) time complexity.
- Merge Sort is more efficient than the brute force approach, which has a time complexity of O(n²).
- Merge Sort is a stable sorting algorithm, meaning that it maintains the relative order of equal elements.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you implement Merge Sort in-place?
  - What is the time complexity of Merge Sort?
  - How does Merge Sort compare to other sorting algorithms like Quick Sort and Heap Sort?
- **Common Pitfalls**:
  - Forgetting to merge the two sorted halves back together.
  - Not handling the base case correctly.
  - Incorrectly calculating the mid index.
- **Alternative Approaches**:
  - Quick Sort is another divide-and-conquer algorithm that sorts an array by selecting a pivot element and partitioning the array into two subarrays.
  - Heap Sort is another comparison-based sorting algorithm that sorts an array by building a heap and then repeatedly extracting the maximum element.

---

# ✅ Conclusion

Merge Sort is an efficient sorting algorithm that achieves O(n log n) time complexity by recursively dividing the array into smaller subarrays, sorting those subarrays, and then merging them back together. The key insight is that merging two sorted arrays is straightforward and efficient, which allows the overall algorithm to achieve O(n log n) time complexity. Merge Sort is a stable sorting algorithm, meaning that it maintains the relative order of equal elements.