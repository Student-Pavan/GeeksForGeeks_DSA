# 📌 Trapping Rain Water

---

# 📝 Problem Statement

Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

**Objective**: Calculate the total amount of rainwater that can be trapped between the bars.

**Input**:
- An integer array `height[]` representing the elevation of bars

**Output**:
- An integer representing the total units of water trapped

**Constraints**:
- `n == height.length`
- `1 <= n <= 2 * 10^4`
- `0 <= height[i] <= 10^5`

---

# 💡 Intuition

The key insight is that the amount of water trapped at any given position depends on the tallest bars to its left and right. The water trapped at position `i` is determined by the minimum of the maximum heights to the left and right of `i`, minus the height of the current bar.

The optimal approach uses a two-pointer technique to efficiently compute this without using extra space, moving from both ends towards the center while keeping track of the maximum heights encountered from both sides.

---

# 🐌 Brute Force Approach

## 🔹 Approach

For each element in the array, calculate the maximum height to the left and right of the current element. The water trapped at that position is the minimum of these two maximum heights minus the height of the current element.

## 🔹 Algorithm

1. Initialize `water` to `0`
2. For each element in the array:
   1. Find the maximum height to the left of the current element
   2. Find the maximum height to the right of the current element
   3. The water trapped at the current position is `min(left_max, right_max) - height[i]`
   4. Add this value to `water`
3. Return `water`

## 🔹 Code

```java
class Solution {
    public int maxWater(int height[]) {
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int left_max = 0;
            for (int j = i; j >= 0; j--) {
                left_max = Math.max(left_max, height[j]);
            }
            int right_max = 0;
            for (int j = i; j < height.length; j++) {
                right_max = Math.max(right_max, height[j]);
            }
            water += Math.min(left_max, right_max) - height[i];
        }
        return water;
    }
}
```

## 🔹 Dry Run

Let's dry run the algorithm with `height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]`.

| Iteration | Current Value | Left Max | Right Max | Water Trapped | Total Water |
|-----------|---------------|----------|-----------|---------------|--------------|
| 0         | 0             | 0        | 3         | min(0, 3) - 0 = 0 | 0            |
| 1         | 1             | 1        | 3         | min(1, 3) - 1 = 0 | 0            |
| 2         | 0             | 1        | 3         | min(1, 3) - 0 = 1 | 1            |
| 3         | 2             | 2        | 3         | min(2, 3) - 2 = 0 | 1            |
| 4         | 1             | 2        | 3         | min(2, 3) - 1 = 1 | 2            |
| 5         | 0             | 2        | 3         | min(2, 3) - 0 = 2 | 4            |
| 6         | 1             | 3        | 3         | min(3, 3) - 1 = 2 | 6            |
| 7         | 3             | 3        | 3         | min(3, 3) - 3 = 0 | 6            |
| 8         | 2             | 3        | 2         | min(3, 2) - 2 = 0 | 6            |
| 9         | 1             | 3        | 2         | min(3, 2) - 1 = 1 | 7            |
| 10        | 2             | 3        | 2         | min(3, 2) - 2 = 0 | 7            |
| 11        | 1             | 3        | 2         | min(3, 2) - 1 = 1 | 8            |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n²) |
| Space Complexity | O(1) |

---

# ⚡ Optimal Approach

## 🔹 Approach

Use two pointers, one starting at the beginning (`left`) and one at the end (`right`) of the array. Track the maximum heights encountered from both sides. Move the pointer pointing to the smaller maximum height inward, updating the maximum height and calculating the water trapped at each step.

## 🔹 Why This Works

This approach efficiently calculates the water trapped by leveraging the fact that the amount of water trapped at any position is limited by the smaller of the two maximum heights on either side. By moving the pointer with the smaller maximum height, we ensure that we are always considering the limiting factor for water trapping.

## 🔹 Algorithm

1. Initialize `water` to `0`
2. Initialize `left` to `0` and `right` to `height.length - 1`
3. Initialize `left_max` to `height[left]` and `right_max` to `height[right]`
4. While `left < right`:
   1. If `left_max < right_max`:
      1. Move `left` to the right
      2. Update `left_max` to `max(left_max, height[left])`
      3. Add `left_max - height[left]` to `water`
   2. Else:
      1. Move `right` to the left
      2. Update `right_max` to `max(right_max, height[right])`
      3. Add `right_max - height[right]` to `water`
5. Return `water`

## 🔹 Code

```java
class Solution {
    public int maxWater(int height[]) {
        int water = 0;
        int left_max = height[0], right_max = height[height.length - 1];
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (left_max < right_max) {
                left++;
                left_max = Math.max(left_max, height[left]);
                water += left_max - height[left];
            } else {
                right--;
                right_max = Math.max(right_max, height[right]);
                water += right_max - height[right];
            }
        }
        return water;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]`.

| Step | Left | Right | Left Max | Right Max | Action | Water Added | Total Water |
|------|------|-------|----------|-----------|--------|--------------|--------------|
| 1    | 0    | 11    | 0        | 1         | Move left (0 < 1) | 0 - 1 = -1 | 0            |
| 2    | 1    | 11    | 1        | 1         | Move left (1 == 1) | 1 - 0 = 1 | 1            |
| 3    | 2    | 11    | 1        | 1         | Move left (1 < 1) | 1 - 0 = 1 | 2            |
| 4    | 3    | 11    | 2        | 1         | Move right (2 > 1) | 2 - 1 = 1 | 3            |
| 5    | 3    | 10    | 2        | 2         | Move right (2 == 2) | 2 - 1 = 1 | 4            |
| 6    | 3    | 9     | 2        | 2         | Move right (2 == 2) | 2 - 0 = 2 | 6            |
| 7    | 3    | 8     | 2        | 3         | Move left (2 < 3) | 2 - 1 = 1 | 7            |
| 8    | 3    | 7     | 3        | 3         | Move right (3 == 3) | 3 - 3 = 0 | 7            |
| 9    | 3    | 6     | 3        | 3         | Move right (3 == 3) | 3 - 1 = 2 | 9            |
| 10   | 3    | 5     | 3        | 3         | Move right (3 == 3) | 3 - 0 = 3 | 12           |
| 11   | 3    | 4     | 3        | 3         | Move right (3 == 3) | 3 - 1 = 2 | 14           |
| 12   | 3    | 3     | 3        | 3         | Terminate (left >= right) | - | 14           |

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- **Empty input**: `height = []` → Output: `0`
- **Single element**: `height = [5]` → Output: `0`
- **All elements same height**: `height = [2, 2, 2, 2]` → Output: `0`
- **Increasing sequence**: `height = [1, 2, 3, 4]` → Output: `0`
- **Decreasing sequence**: `height = [4, 3, 2, 1]` → Output: `0`
- **Peak in middle**: `height = [1, 3, 2, 4, 1]` → Output: `2`
- **Multiple peaks**: `height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]` → Output: `6`

---

# 📚 Key Takeaways

- The brute force approach has a time complexity of O(n²) and is not efficient for large inputs.
- The optimal approach uses a two-pointer technique to achieve O(n) time complexity with O(1) space complexity.
- The key insight is that the water trapped at any position is determined by the minimum of the maximum heights to the left and right of that position.
- The optimal approach efficiently calculates the water trapped by moving the pointer with the smaller maximum height inward, ensuring that we always consider the limiting factor for water trapping.

---

# 🚀 Interview Tips

- **Follow-up questions**: Ask about handling very large inputs or if the solution can be optimized further.
- **Common pitfalls**: Forgetting to update the maximum heights or not considering edge cases.
- **Alternative approaches**: Discuss using prefix and suffix arrays to store maximum heights, which can also achieve O(n) time complexity but with O(n) space complexity.
- **Optimization discussions**: Emphasize the importance of the two-pointer technique and how it reduces the time complexity significantly.

---

# ✅ Conclusion

The optimal solution using the two-pointer technique is preferred for its O(n) time complexity and O(1) space complexity. The key insight is recognizing that the water trapped at any position is limited by the smaller of the two maximum heights on either side, and efficiently calculating this by moving the pointer with the smaller maximum height inward. This approach ensures that we efficiently compute the total water trapped without using extra space.