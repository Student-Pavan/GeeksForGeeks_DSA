# 📌 Find Kth Rotation

---

# 📝 Problem Statement

Given a sorted and rotated array `arr[]` of size `N`. Find the index of the minimum element in the array. This index is also the point of rotation.

**Constraints:**
- 1 ≤ N ≤ 10^5
- 1 ≤ arr[i] ≤ 10^9

---

# 💡 Intuition

The key observation is that in a sorted and rotated array, the minimum element will be the point where the order of elements changes from decreasing to increasing. This is because the array is sorted initially and then rotated, creating a pivot point where the minimum element resides.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves scanning through the entire array to find the minimum element. We initialize the first element as the minimum and then compare it with every subsequent element in the array. If we find an element smaller than the current minimum, we update the minimum and its index.

---

## 🔹 Algorithm

1. Initialize `min` with the first element of the array and `index` with 0.
2. Iterate through the array starting from the second element.
3. For each element, compare it with `min`.
4. If the current element is smaller than `min`, update `min` with the current element and update `index` with the current index.
5. After the loop ends, return `index`.

---

## 🔹 Code

```java
class Solution {
    public int findKRotation(int arr[]) {
        int min = arr[0];
        int index = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }
        return index;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with the array `[4, 5, 1, 2, 3]`.

| Iteration | Current Element | Current Min | Current Index | Action |
|-----------|------------------|--------------|----------------|--------|
| 1         | 4                | 4            | 0              | Initialize |
| 2         | 5                | 4            | 0              | No update |
| 3         | 1                | 1            | 2              | Update min and index |
| 4         | 2                | 1            | 2              | No update |
| 5         | 3                | 1            | 2              | No update |

The algorithm returns the index `2`, which is the correct index of the minimum element `1`.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(N) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using binary search to find the minimum element in O(log N) time. The idea is to leverage the properties of the sorted and rotated array to determine which half of the array to search next.

---

## 🔹 Why This Works

In a sorted and rotated array, the minimum element is the only element for which the next element is smaller. By comparing the middle element with the rightmost element, we can determine which half of the array contains the minimum element.

---

## 🔹 Algorithm

1. Initialize `low` to 0 and `high` to the last index of the array.
2. While `low` is less than `high`:
   - Calculate `mid` as `low + (high - low) / 2`.
   - If `arr[mid]` is greater than `arr[high]`, the minimum element must be in the right half. Update `low` to `mid + 1`.
   - Else, the minimum element is in the left half, including `mid`. Update `high` to `mid`.
3. When the loop ends, `low` will be pointing to the minimum element. Return `low`.

---

## 🔹 Code

```java
class Solution {
    public int findKRotation(int arr[]) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the array `[4, 5, 1, 2, 3]`.

| Iteration | Low | High | Mid | Action |
|-----------|-----|------|-----|--------|
| 1         | 0   | 4    | 2   | arr[2] > arr[4] → low = 3 |
| 2         | 3   | 4    | 3   | arr[3] > arr[4] → low = 4 |
| 3         | 4   | 4    | 4   | low == high → exit loop |

The algorithm returns the index `4`, which is the correct index of the minimum element `1`.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log N) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Single Element Array:** The array has only one element, which is the minimum.
- **Already Sorted Array:** The array is sorted in ascending order, so the minimum element is the first element.
- **Reverse Sorted Array:** The array is sorted in descending order, so the minimum element is the last element.
- **All Elements Same:** All elements in the array are the same, so any index can be returned as the minimum.
- **Large Input Size:** The array size is very large, so the optimal approach is necessary to avoid TLE.

---

# 📚 Key Takeaways

- The brute force approach is straightforward but inefficient for large arrays.
- The optimal approach uses binary search to achieve O(log N) time complexity, which is crucial for large input sizes.
- Understanding the properties of sorted and rotated arrays is key to solving such problems efficiently.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the array contains duplicates?
  - How would you modify the solution to find the index of the maximum element?
- **Common Pitfalls:**
  - Forgetting to handle the case where the array is already sorted.
  - Incorrectly updating the `low` and `high` pointers in the binary search.
- **Alternative Approaches:**
  - Using linear search, but it's not optimal for large arrays.
  - Using recursion instead of iteration in the binary search approach.

---

# ✅ Conclusion

The optimal approach using binary search is the preferred solution for finding the index of the minimum element in a sorted and rotated array. It efficiently handles large input sizes and demonstrates a deep understanding of binary search and array properties.