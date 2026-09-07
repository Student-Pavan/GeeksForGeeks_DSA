# Container With Most Water

---

# 📝 Problem Statement

You are given an array of non-negative integers representing the heights of vertical lines drawn on a graph. The width of each line is 1 unit. Find two lines that, together with the x-axis, form a container that holds the most water. The amount of water a container can hold is equal to the distance between the two lines multiplied by the height of the shorter line.

**Objective**: Find the maximum amount of water that can be contained.

**Input**:
- An array of integers `height[]` where each integer represents the height of a vertical line.

**Output**:
- An integer representing the maximum amount of water that can be contained.

**Constraints**:
- `2 <= height.length <= 10^5`
- `0 <= height[i] <= 10^4`

---

# 💡 Intuition

The key insight is that the amount of water trapped between two lines is determined by the shorter of the two lines and the distance between them. To maximize the water, we need to find the pair of lines that gives the maximum value of `min(height[left], height[right]) * (right - left)`.

The brute force approach checks all possible pairs, but this is inefficient. The optimal approach uses a two-pointer technique to find the maximum area in linear time by always moving the pointer pointing to the shorter line.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach checks all possible pairs of lines and calculates the water they can hold. It then keeps track of the maximum water found.

## 🔹 Algorithm

1. Initialize `maxWater` to 0.
2. Use nested loops to iterate through all pairs of lines.
3. For each pair, calculate the water they can hold using `min(height[i], height[j]) * (j - i)`.
4. Update `maxWater` if the current water is greater.
5. Return `maxWater`.

## 🔹 Code

```java
class Solution {
    public int maxWater(int[] height) {
        int maxWater = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int currentWater = Math.min(height[i], height[j]) * (j - i);
                if (currentWater > maxWater) {
                    maxWater = currentWater;
                }
            }
        }
        return maxWater;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with the input `[1, 8, 6, 2, 5, 4, 8, 3, 7]`.

| Iteration | i | j | height[i] | height[j] | min(height[i], height[j]) | (j - i) | currentWater | maxWater |
|-----------|---|---|-----------|-----------|--------------------------|---------|--------------|----------|
| 1         | 0 | 1 | 1         | 8         | 1                        | 1       | 1            | 1        |
| 2         | 0 | 2 | 1         | 6         | 1                        | 2       | 2            | 2        |
| 3         | 0 | 3 | 1         | 2         | 1                        | 3       | 3            | 3        |
| 4         | 0 | 4 | 1         | 5         | 1                        | 4       | 4            | 4        |
| 5         | 0 | 5 | 1         | 4         | 1                        | 5       | 5            | 5        |
| 6         | 0 | 6 | 1         | 8         | 1                        | 6       | 6            | 6        |
| 7         | 0 | 7 | 1         | 3         | 1                        | 7       | 7            | 7        |
| 8         | 0 | 8 | 1         | 7         | 1                        | 8       | 8            | 8        |
| 9         | 1 | 2 | 8         | 6         | 6                        | 1       | 6            | 8        |
| 10        | 1 | 3 | 8         | 2         | 2                        | 2       | 4            | 8        |
| 11        | 1 | 4 | 8         | 5         | 5                        | 3       | 15           | 15       |
| 12        | 1 | 5 | 8         | 4         | 4                        | 4       | 16           | 16       |
| 13        | 1 | 6 | 8         | 8         | 8                        | 5       | 40           | 40       |
| 14        | 1 | 7 | 8         | 3         | 3                        | 6       | 18           | 40       |
| 15        | 1 | 8 | 8         | 7         | 7                        | 7       | 49           | 49       |
| 16        | 2 | 3 | 6         | 2         | 2                        | 1       | 2            | 49       |
| 17        | 2 | 4 | 6         | 5         | 5                        | 2       | 10           | 49       |
| 18        | 2 | 5 | 6         | 4         | 4                        | 3       | 12           | 49       |
| 19        | 2 | 6 | 6         | 8         | 6                        | 4       | 24           | 49       |
| 20        | 2 | 7 | 6         | 3         | 3                        | 5       | 15           | 49       |
| 21        | 2 | 8 | 6         | 7         | 6                        | 6       | 36           | 49       |
| 22        | 3 | 4 | 2         | 5         | 2                        | 1       | 2            | 49       |
| 23        | 3 | 5 | 2         | 4         | 2                        | 2       | 4            | 49       |
| 24        | 3 | 6 | 2         | 8         | 2                        | 3       | 6            | 49       |
| 25        | 3 | 7 | 2         | 3         | 2                        | 4       | 8            | 49       |
| 26        | 3 | 8 | 2         | 7         | 2                        | 5       | 10           | 49       |
| 27        | 4 | 5 | 5         | 4         | 4                        | 1       | 4            | 49       |
| 28        | 4 | 6 | 5         | 8         | 5                        | 2       | 10           | 49       |
| 29        | 4 | 7 | 5         | 3         | 3                        | 3       | 9            | 49       |
| 30        | 4 | 8 | 5         | 7         | 5                        | 4       | 20           | 49       |
| 31        | 5 | 6 | 4         | 8         | 4                        | 1       | 4            | 49       |
| 32        | 5 | 7 | 4         | 3         | 3                        | 2       | 6            | 49       |
| 33        | 5 | 8 | 4         | 7         | 4                        | 3       | 12           | 49       |
| 34        | 6 | 7 | 8         | 3         | 3                        | 1       | 3            | 49       |
| 35        | 6 | 8 | 8         | 7         | 7                        | 2       | 14           | 49       |
| 36        | 7 | 8 | 3         | 7         | 3                        | 1       | 3            | 49       |

The maximum water that can be contained is **49**.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n^2) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a two-pointer technique to find the maximum area in linear time. The idea is to start with the widest container and then move the pointers inward, always moving the pointer pointing to the shorter line. This is because moving the pointer pointing to the taller line will not increase the area, as the area is limited by the shorter line.

## 🔹 Why This Works

The two-pointer approach works because it efficiently narrows down the search space by always moving the pointer pointing to the shorter line. This ensures that we explore the most promising candidates for the maximum area without unnecessary computations.

## 🔹 Algorithm

1. Initialize two pointers, `left` at the start and `right` at the end of the array.
2. Initialize `maxWater` to 0.
3. While `left` is less than `right`:
   a. Calculate the current water using `min(height[left], height[right]) * (right - left)`.
   b. Update `maxWater` if the current water is greater.
   c. Move the pointer pointing to the shorter line inward.
4. Return `maxWater`.

## 🔹 Code

```java
class Solution {
    public int maxWater(int[] height) {
        int maxWater = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int currentWater = Math.min(height[left], height[right]) * (right - left);
            if (currentWater > maxWater) {
                maxWater = currentWater;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with the input `[1, 8, 6, 2, 5, 4, 8, 3, 7]`.

| Iteration | Left | Right | height[left] | height[right] | min(height[left], height[right]) | (right - left) | currentWater | maxWater | Action |
|-----------|------|-------|--------------|---------------|------------------------------------|----------------|--------------|----------|--------|
| 1         | 0    | 8     | 1            | 7             | 1                                  | 8              | 8            | 8        | left++ |
| 2         | 1    | 8     | 8            | 7             | 7                                  | 7              | 49           | 49       | right-- |
| 3         | 1    | 7     | 8            | 3             | 3                                  | 6              | 18           | 49       | right-- |
| 4         | 1    | 6     | 8            | 8             | 8                                  | 5              | 40           | 49       | right-- |
| 5         | 1    | 5     | 8            | 4             | 4                                  | 4              | 16           | 49       | right-- |
| 6         | 1    | 4     | 8            | 5             | 5                                  | 3              | 15           | 49       | right-- |
| 7         | 1    | 3     | 8            | 2             | 2                                  | 2              | 4            | 49       | left++ |
| 8         | 2    | 3     | 6            | 2             | 2                                  | 1              | 2            | 49       | left++ |
| 9         | 3    | 3     | 2            | 2             | 2                                  | 0              | 0            | 49       | Terminate |

The maximum water that can be contained is **49**.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty Input**: The function should handle an empty array gracefully, possibly by returning 0.
- **Single Element**: If the array has only one element, the function should return 0 since no container can be formed.
- **Duplicate Heights**: The function should correctly handle arrays with duplicate heights.
- **Large Input**: The function should efficiently handle large input sizes within the given constraints.
- **Sorted Input**: The function should work correctly for both increasing and decreasing sorted arrays.
- **Negative Values**: Although the problem states that heights are non-negative, the function should handle negative values gracefully, possibly by taking their absolute values.

---

# 📚 Key Takeaways

- The brute force approach checks all possible pairs, resulting in a time complexity of O(n^2).
- The optimal approach uses a two-pointer technique to find the maximum area in linear time, resulting in a time complexity of O(n).
- The key insight is to always move the pointer pointing to the shorter line to maximize the area.
- The two-pointer technique is efficient and widely used in problems involving arrays and pointers.

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - Can you solve this problem in O(n) time and O(1) space?
  - What if the heights are not integers but floating-point numbers?
- **Common Pitfalls**:
  - Forgetting to move the pointer pointing to the shorter line.
  - Not initializing `maxWater` to 0.
  - Not handling edge cases properly.
- **Alternative Approaches**:
  - Using a stack to keep track of potential containers.
  - Using dynamic programming to store intermediate results.
- **Optimization Discussions**:
  - The two-pointer approach is optimal for this problem.
  - The brute force approach is not suitable for large input sizes.

---

# ✅ Conclusion

The optimal approach using the two-pointer technique is the most efficient solution for the Container With Most Water problem. It efficiently narrows down the search space by always moving the pointer pointing to the shorter line, ensuring that we find the maximum area in linear time. This approach is both time and space efficient, making it suitable for large input sizes.