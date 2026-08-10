# Max Product Pair

---

# 📝 Problem Statement

Given an array of integers, find the maximum product of any two distinct elements in the array.

**Objective:** Find the pair of numbers in the array whose product is the largest.

**Input:** An array of integers.

**Output:** The maximum product of any two distinct elements.

**Constraints:**
- The array will have at least two elements.
- The array may contain negative numbers.
- The array may contain duplicate numbers.

---

# 💡 Intuition

The key insight is that the maximum product of two numbers can be either:
1. The product of the two largest numbers, or
2. The product of the two smallest numbers (if both are negative, their product could be positive and larger than the product of the two largest positive numbers).

This means we need to track both the two largest and two smallest numbers in the array.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every possible pair of numbers in the array and calculating their product. We then keep track of the maximum product found.

## 🔹 Algorithm

1. Initialize a variable to store the maximum product.
2. Use nested loops to iterate through all possible pairs of numbers in the array.
3. Calculate the product of each pair.
4. Update the maximum product if the current product is greater.
5. Return the maximum product after all pairs have been checked.

## 🔹 Code

```java
class Solution {
    public static int maxProduct(int[] arr) {
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int product = arr[i] * arr[j];
                if (product > maxProduct) {
                    maxProduct = product;
                }
            }
        }
        return maxProduct;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with the array `[3, 5, 1, 2]`.

| Iteration | i | j | arr[i] | arr[j] | Product | maxProduct |
|-----------|---|---|--------|--------|---------|------------|
| 1         | 0 | 1 | 3      | 5      | 15      | 15         |
| 2         | 0 | 2 | 3      | 1      | 3       | 15         |
| 3         | 0 | 3 | 3      | 2      | 6       | 15         |
| 4         | 1 | 2 | 5      | 1      | 5       | 15         |
| 5         | 1 | 3 | 5      | 2      | 10      | 15         |
| 6         | 2 | 3 | 1      | 2      | 2       | 15         |

The maximum product is 15.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves a single pass through the array to find the two largest and two smallest numbers. This approach is more efficient and has a time complexity of O(n).

## 🔹 Why This Works

By tracking the two largest and two smallest numbers, we can efficiently determine the maximum product without checking every possible pair. This is because the maximum product will always be either the product of the two largest numbers or the product of the two smallest numbers.

## 🔹 Algorithm

1. Initialize variables to store the two largest numbers (`max1` and `max2`) and the two smallest numbers (`min1` and `min2`).
2. Iterate through the array.
3. For each number, update the two largest and two smallest numbers accordingly.
4. After the iteration, calculate the maximum product by comparing the product of the two largest numbers and the product of the two smallest numbers.
5. Return the maximum product.

## 🔹 Code

```java
class Solution {
    public static int maxProduct(int[] arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }

            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        return Math.max(max1 * max2, min1 * min2);
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with the array `[3, 5, 1, 2]`.

| Iteration | num | max1 | max2 | min1 | min2 |
|-----------|-----|------|------|------|------|
| 1         | 3   | 3    | -∞   | 3    | ∞    |
| 2         | 5   | 5    | 3    | 3    | ∞    |
| 3         | 1   | 5    | 3    | 1    | 3    |
| 4         | 2   | 5    | 3    | 1    | 2    |

After the iteration:
- max1 = 5, max2 = 3
- min1 = 1, min2 = 2

The maximum product is the maximum of (5 * 3 = 15) and (1 * 2 = 2), which is 15.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Array:** The problem states the array will have at least two elements, so this case is not applicable.
- **Single Element Array:** The problem states the array will have at least two elements, so this case is not applicable.
- **Array with All Negative Numbers:** The algorithm correctly handles this by considering the product of the two largest (most negative) numbers.
- **Array with Duplicate Numbers:** The algorithm correctly handles duplicates by updating the largest and smallest numbers appropriately.
- **Array with Zero:** The algorithm correctly handles zero by updating the smallest numbers appropriately.

---

# 📚 Key Takeaways

- The optimal approach is more efficient with a time complexity of O(n) compared to the brute force approach's O(n²).
- Tracking the two largest and two smallest numbers is a common pattern in problems involving finding maximum or minimum products.
- This problem highlights the importance of considering edge cases, especially when dealing with negative numbers.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the array can have more than two elements, and we need to find the maximum product of any k distinct elements?
  - How would you handle very large arrays efficiently?
- **Common Pitfalls:**
  - Forgetting to consider negative numbers and their products.
  - Not initializing variables correctly, especially when dealing with negative numbers.
- **Alternative Approaches:**
  - Using sorting to find the two largest and two smallest numbers, which has a time complexity of O(n log n).
- **Optimization Discussions:**
  - The optimal approach is more efficient and should be preferred for large arrays.

---

# ✅ Conclusion

The optimal approach is preferred because it efficiently finds the maximum product with a single pass through the array, making it more suitable for large arrays. The key insight is tracking the two largest and two smallest numbers to determine the maximum product. This approach ensures optimal performance and correctness.