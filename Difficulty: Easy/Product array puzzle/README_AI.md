# 📌 Product array puzzle

---

# 📝 Problem Statement

Given an array `arr[]` of `n` integers, construct a Product Array `prod[]` (of the same size) such that `prod[i]` is equal to the product of all the elements of `arr[]` except `arr[i]`. Solve it without using the division operator and in O(n) time complexity.

**Example:**
```java
Input: arr[] = {10, 3, 5, 6, 2}
Output: prod[] = {180, 600, 360, 300, 900}
```

**Constraints:**
- 1 ≤ n ≤ 10^5
- 1 ≤ arr[i] ≤ 10^4

---

# 💡 Intuition

The key insight is that the product of all elements except the current one can be obtained by multiplying the product of all elements to the left of the current element with the product of all elements to the right of the current element. This approach avoids using division and achieves O(n) time complexity by making two passes through the array.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves calculating the product of all elements for each position by iterating through the array for each element. This results in O(n²) time complexity, which is inefficient for large arrays.

## 🔹 Algorithm

1. Initialize an array `prod[]` of the same size as `arr[]`.
2. For each element `arr[i]`:
   - Initialize `product` to 1.
   - For each element `arr[j]` where `j ≠ i`:
     - Multiply `product` by `arr[j]`.
   - Set `prod[i]` to `product`.

## 🔹 Code

```java
class Solution {
    public static int[] productExceptSelf(int arr[]) {
        int n = arr.length;
        int[] prod = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= arr[j];
                }
            }
            prod[i] = product;
        }

        return prod;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `arr[] = {10, 3, 5, 6, 2}`.

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | arr[0] = 10 | product = 1 * 3 * 5 * 6 * 2 = 180 | prod[0] = 180 |
| 2 | arr[1] = 3 | product = 1 * 10 * 5 * 6 * 2 = 600 | prod[1] = 600 |
| 3 | arr[2] = 5 | product = 1 * 10 * 3 * 6 * 2 = 360 | prod[2] = 360 |
| 4 | arr[3] = 6 | product = 1 * 10 * 3 * 5 * 2 = 300 | prod[3] = 300 |
| 5 | arr[4] = 2 | product = 1 * 10 * 3 * 5 * 6 = 900 | prod[4] = 900 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n²) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves using two auxiliary arrays to store the product of all elements to the left and right of each element. The product array is then constructed by multiplying the corresponding elements from these two auxiliary arrays.

## 🔹 Why This Works

This approach efficiently calculates the product of all elements except the current one in O(n) time by leveraging the fact that the product of all elements to the left of an element can be computed in one pass, and the product of all elements to the right can be computed in another pass. Multiplying these two products gives the desired result without using division.

## 🔹 Algorithm

1. Initialize two arrays `left[]` and `right[]` of the same size as `arr[]`.
2. Initialize `left[0]` to 1 and compute the product of all elements to the left of each element in `arr[]` and store it in `left[]`.
3. Initialize `right[n-1]` to 1 and compute the product of all elements to the right of each element in `arr[]` and store it in `right[]`.
4. Construct the product array `prod[]` by multiplying the corresponding elements from `left[]` and `right[]`.

## 🔹 Code

```java
class Solution {
    public static int[] productExceptSelf(int arr[]) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] prod = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * arr[i - 1];
        }

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * arr[i + 1];
        }

        for (int i = 0; i < n; i++) {
            prod[i] = left[i] * right[i];
        }

        return prod;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `arr[] = {10, 3, 5, 6, 2}`.

| Iteration | Left | Right | Action | State |
|---|---|---|---|---|
| 1 | left[0] = 1 | right[4] = 1 | Initialize left[0] and right[4] | left[0] = 1, right[4] = 1 |
| 2 | left[1] = left[0] * arr[0] = 1 * 10 = 10 | right[3] = right[4] * arr[4] = 1 * 2 = 2 | Compute left[1] and right[3] | left[1] = 10, right[3] = 2 |
| 3 | left[2] = left[1] * arr[1] = 10 * 3 = 30 | right[2] = right[3] * arr[3] = 2 * 6 = 12 | Compute left[2] and right[2] | left[2] = 30, right[2] = 12 |
| 4 | left[3] = left[2] * arr[2] = 30 * 5 = 150 | right[1] = right[2] * arr[2] = 12 * 5 = 60 | Compute left[3] and right[1] | left[3] = 150, right[1] = 60 |
| 5 | left[4] = left[3] * arr[3] = 150 * 6 = 900 | right[0] = right[1] * arr[1] = 60 * 3 = 180 | Compute left[4] and right[0] | left[4] = 900, right[0] = 180 |

Now, construct the product array `prod[]`:

| Iteration | Current Value | Current State | Result |
|---|---|---|---|
| 1 | prod[0] = left[0] * right[0] = 1 * 180 = 180 | prod[0] = 180 | prod[0] = 180 |
| 2 | prod[1] = left[1] * right[1] = 10 * 60 = 600 | prod[1] = 600 | prod[1] = 600 |
| 3 | prod[2] = left[2] * right[2] = 30 * 12 = 360 | prod[2] = 360 | prod[2] = 360 |
| 4 | prod[3] = left[3] * right[3] = 150 * 2 = 300 | prod[3] = 300 | prod[3] = 300 |
| 5 | prod[4] = left[4] * right[4] = 900 * 1 = 900 | prod[4] = 900 | prod[4] = 900 |

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Input:** If the input array is empty, the output should be an empty array.
- **Single Element:** If the input array has only one element, the output should be an array with a single element 0.
- **Duplicate Elements:** The algorithm should handle duplicate elements correctly.
- **Negative Values:** The algorithm should handle negative values in the input array.
- **Large Constraints:** The algorithm should handle large input sizes efficiently.

---

# 📚 Key Takeaways

- The brute force approach has a time complexity of O(n²), which is inefficient for large arrays.
- The optimal approach leverages auxiliary arrays to compute the product of all elements except the current one in O(n) time.
- The optimal approach avoids using division and efficiently computes the result in linear time.
- Understanding the use of auxiliary arrays to store intermediate results is crucial for solving such problems efficiently.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - Can you solve the problem in O(n) time without using division?
  - Can you solve the problem in O(n) time and O(1) space?
- **Common Pitfalls:**
  - Forgetting to initialize the auxiliary arrays.
  - Incorrectly computing the product of elements to the left and right.
  - Not handling edge cases such as empty input or single element input.
- **Alternative Approaches:**
  - Using a single pass with division to compute the product of all elements and then dividing by each element to get the result.
  - Using a hash map to store the product of all elements except the current one, but this approach has higher space complexity.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently computes the product array in O(n) time without using division. The key insight is to use auxiliary arrays to store the product of all elements to the left and right of each element, which allows us to construct the product array in linear time. This approach is both time and space efficient and is suitable for large input sizes.