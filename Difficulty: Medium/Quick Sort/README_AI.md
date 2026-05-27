# Quick Sort

---

# 📝 Problem Statement

Quick Sort is a divide-and-conquer algorithm that sorts an array by selecting a 'pivot' element and partitioning the array around the pivot. The elements less than the pivot are placed before it, and elements greater than the pivot are placed after it. This process is repeated recursively for the sub-arrays.

**Objective**: Implement an efficient Quick Sort algorithm to sort an array of integers in ascending order.

**Input**:
- An array of integers `arr`.

**Output**:
- The sorted array in ascending order.

**Constraints**:
- The solution should be efficient and handle large input sizes.
- The algorithm should be in-place to minimize space complexity.

---

# 💡 Intuition

The key insight behind Quick Sort is the partition process, which rearranges the array such that all elements less than the pivot come before it, and all elements greater than the pivot come after it. This process is repeated recursively for the sub-arrays, leading to a sorted array. The choice of pivot can significantly impact the performance of the algorithm.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves selecting the last element as the pivot and partitioning the array around the pivot. The partition process involves iterating through the array and swapping elements to ensure all elements less than the pivot are on the left and all elements greater than the pivot are on the right. This process is then repeated recursively for the left and right sub-arrays.

---

## 🔹 Algorithm

1. **Choose Pivot**: Select the last element of the array as the pivot.
2. **Partition**: Iterate through the array and swap elements to ensure all elements less than the pivot are on the left and all elements greater than the pivot are on the right.
3. **Recursive Sort**: Recursively apply the Quick Sort algorithm to the left and right sub-arrays.

---

## 🔹 Code

```java
class Solution {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);

            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int idx = low - 1;
        int pivot = arr[high];

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                idx++;
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
            }
        }
        idx++;
        int temp = arr[idx];
        arr[idx] = arr[high];
        arr[high] = temp;

        return idx;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the Quick Sort algorithm with the array `[10, 7, 8, 9, 1, 5]`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 5 | Partition | [10, 7, 8, 9, 1, 5] |
| 2 | 0 | 4 | Partition | [1, 7, 8, 9, 10, 5] |
| 3 | 0 | 0 | Base Case | [1, 7, 8, 9, 10, 5] |
| 4 | 2 | 4 | Partition | [1, 7, 8, 9, 10, 5] |
| 5 | 2 | 3 | Partition | [1, 5, 8, 9, 10, 7] |
| 6 | 2 | 2 | Base Case | [1, 5, 8, 9, 10, 7] |
| 7 | 4 | 4 | Base Case | [1, 5, 7, 9, 10, 8] |
| 8 | 5 | 5 | Base Case | [1, 5, 7, 8, 10, 9] |
| 9 | 5 | 5 | Base Case | [1, 5, 7, 8, 9, 10] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n^2) in the worst case, O(n log n) in the average case |
| Space Complexity | O(log n) due to the recursive call stack |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves selecting a pivot element and partitioning the array around the pivot. The partition process ensures that all elements less than the pivot are on the left and all elements greater than the pivot are on the right. This process is repeated recursively for the left and right sub-arrays. The optimal approach also includes a randomized pivot selection to avoid the worst-case scenario.

---

## 🔹 Why This Works

The optimal approach ensures that the pivot selection is randomized, which helps in achieving an average time complexity of O(n log n). This randomization reduces the probability of encountering the worst-case scenario, where the pivot is the smallest or largest element in the array.

---

## 🔹 Algorithm

1. **Randomized Pivot Selection**: Select a random element as the pivot.
2. **Partition**: Iterate through the array and swap elements to ensure all elements less than the pivot are on the left and all elements greater than the pivot are on the right.
3. **Recursive Sort**: Recursively apply the Quick Sort algorithm to the left and right sub-arrays.

---

## 🔹 Code

```java
import java.util.Random;

class Solution {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = randomizedPartition(arr, low, high);

            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1);

        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;

        return partition(arr, low, high);
    }

    private int partition(int[] arr, int low, int high) {
        int idx = low - 1;
        int pivot = arr[high];

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                idx++;
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
            }
        }
        idx++;
        int temp = arr[idx];
        arr[idx] = arr[high];
        arr[high] = temp;

        return idx;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the Quick Sort algorithm with the array `[10, 7, 8, 9, 1, 5]`.

| Step | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | 0 | 5 | Randomized Partition | [10, 7, 8, 9, 1, 5] |
| 2 | 0 | 4 | Partition | [1, 7, 8, 9, 10, 5] |
| 3 | 0 | 0 | Base Case | [1, 7, 8, 9, 10, 5] |
| 4 | 2 | 4 | Partition | [1, 7, 8, 9, 10, 5] |
| 5 | 2 | 3 | Partition | [1, 5, 8, 9, 10, 7] |
| 6 | 2 | 2 | Base Case | [1, 5, 8, 9, 10, 7] |
| 7 | 4 | 4 | Base Case | [1, 5, 7, 9, 10, 8] |
| 8 | 5 | 5 | Base Case | [1, 5, 7, 8, 10, 9] |
| 9 | 5 | 5 | Base Case | [1, 5, 7, 8, 9, 10] |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n log n) in the average case |
| Space Complexity | O(log n) due to the recursive call stack |

---

# 🔍 Edge Cases

- **Empty Array**: The algorithm should handle an empty array gracefully.
- **Single Element**: The algorithm should handle an array with a single element.
- **Duplicate Elements**: The algorithm should handle arrays with duplicate elements.
- **Negative Values**: The algorithm should handle arrays with negative values.
- **Large Input Size**: The algorithm should handle large input sizes efficiently.
- **Sorted Array**: The algorithm should handle a sorted array.
- **Reverse Sorted Array**: The algorithm should handle a reverse sorted array.

---

# 📚 Key Takeaways

- Quick Sort is an efficient sorting algorithm with an average time complexity of O(n log n).
- The choice of pivot can significantly impact the performance of the algorithm.
- Randomized pivot selection helps in achieving an average time complexity of O(n log n).
- Quick Sort is an in-place sorting algorithm, which minimizes space complexity.

---

# 🚀 Interview Tips

- **Follow-up Questions**: Discuss the worst-case scenario and how to optimize the algorithm.
- **Common Pitfalls**: Avoid selecting the first or last element as the pivot in a sorted array.
- **Alternative Approaches**: Discuss other sorting algorithms like Merge Sort and Heap Sort.
- **Optimization Discussions**: Discuss the impact of pivot selection on the algorithm's performance.

---

# ✅ Conclusion

Quick Sort is an efficient sorting algorithm with an average time complexity of O(n log n). The optimal approach involves selecting a randomized pivot to avoid the worst-case scenario. Quick Sort is an in-place sorting algorithm, which minimizes space complexity. Understanding the partition process and the impact of pivot selection is crucial for mastering Quick Sort.