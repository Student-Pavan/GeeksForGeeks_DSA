# Maximum Weight Node

---

# 📝 Problem Statement

Given an array of integers `exits` where `exits[i]` represents the exit point from cell `i`, find the cell with the maximum weight. The weight of a cell is the sum of all indices that point to it.

**Objective**: Return the index of the cell with the maximum weight. If multiple cells have the same maximum weight, return the smallest index.

**Constraints**:
- `1 <= exits.length <= 10^5`
- `-1 <= exits[i] < exits.length`
- `exits[i] = -1` means no exit from cell `i`

---

# 💡 Intuition

The key insight is that the weight of each cell is determined by the sum of all indices that point to it. We can calculate the weight of each cell by iterating through the array and accumulating the indices that point to each cell. The cell with the maximum weight can then be found by iterating through the weight array.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Initialize a weight array of the same length as `exits` with all values set to 0.
2. Iterate through each element in the `exits` array.
3. For each element, if it is not -1, add the current index to the weight of the cell it points to.
4. After processing all elements, iterate through the weight array to find the cell with the maximum weight.
5. Return the index of the cell with the maximum weight.

## 🔹 Algorithm

1. Initialize `weight` array with size `n` and all values set to 0.
2. For each index `i` from 0 to `n-1`:
   - If `exits[i] != -1`, then `weight[exits[i]] += i`.
3. Initialize `maxWeight` to -1 and `maxCell` to -1.
4. For each index `i` from 0 to `n-1`:
   - If `weight[i] > maxWeight`, then set `maxWeight` to `weight[i]` and `maxCell` to `i`.
5. Return `maxCell`.

## 🔹 Code

```java
class Solution {
    public int maxWeightCell(int[] exits) {
        int n = exits.length;
        int[] weight = new int[n];

        // Calculate the weight of each cell
        for (int i = 0; i < n; i++) {
            if (exits[i] != -1) {
                weight[exits[i]] += i;
            }
        }

        // Find the cell with maximum weight
        int maxWeight = -1;
        int maxCell = -1;

        for (int i = 0; i < n; i++) {
            if (weight[i] > maxWeight) {
                maxWeight = weight[i];
                maxCell = i;
            }
        }

        return maxCell;
    }
}
```

## 🔹 Dry Run

Let's dry run the code with the following input: `exits = [1, 4, -1, 2, 3]`.

| Iteration | Current Index (i) | exits[i] | Action | weight Array |
|-----------|---------------------|----------|--------|--------------|
| 1         | 0                   | 1        | weight[1] += 0 | [0, 0, 0, 0, 0] → [0, 0, 0, 0, 0] |
| 2         | 1                   | 4        | weight[4] += 1 | [0, 0, 0, 0, 0] → [0, 0, 0, 0, 1] |
| 3         | 2                   | -1       | Skip    | [0, 0, 0, 0, 1] |
| 4         | 3                   | 2        | weight[2] += 3 | [0, 0, 0, 0, 1] → [0, 0, 3, 0, 1] |
| 5         | 4                   | 3        | weight[3] += 4 | [0, 0, 3, 0, 1] → [0, 0, 3, 4, 1] |

Now, we find the cell with the maximum weight:

| Iteration | Current Index (i) | weight[i] | maxWeight | maxCell |
|-----------|---------------------|-----------|-----------|---------|
| 1         | 0                   | 0         | -1        | -1      |
| 2         | 1                   | 0         | -1        | -1      |
| 3         | 2                   | 3         | 3         | 2       |
| 4         | 3                   | 4         | 4         | 3       |
| 5         | 4                   | 1         | 4         | 3       |

The cell with the maximum weight is 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach is similar to the brute force approach but with a slight optimization in the final step. Instead of iterating through the entire weight array to find the maximum weight, we can keep track of the maximum weight and the corresponding cell during the first iteration.

## 🔹 Why This Works

This approach works because we can calculate the weight of each cell in a single pass through the array. By keeping track of the maximum weight and the corresponding cell during the first iteration, we can avoid an additional pass through the weight array, thus optimizing the solution.

## 🔹 Algorithm

1. Initialize `weight` array with size `n` and all values set to 0.
2. Initialize `maxWeight` to -1 and `maxCell` to -1.
3. For each index `i` from 0 to `n-1`:
   - If `exits[i] != -1`, then `weight[exits[i]] += i`.
   - If `weight[exits[i]] > maxWeight`, then set `maxWeight` to `weight[exits[i]]` and `maxCell` to `exits[i]`.
4. Return `maxCell`.

## 🔹 Code

```java
class Solution {
    public int maxWeightCell(int[] exits) {
        int n = exits.length;
        int[] weight = new int[n];
        int maxWeight = -1;
        int maxCell = -1;

        // Calculate the weight of each cell and track the maximum weight
        for (int i = 0; i < n; i++) {
            if (exits[i] != -1) {
                weight[exits[i]] += i;
                if (weight[exits[i]] > maxWeight) {
                    maxWeight = weight[exits[i]];
                    maxCell = exits[i];
                }
            }
        }

        return maxCell;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the code with the following input: `exits = [1, 4, -1, 2, 3]`.

| Iteration | Current Index (i) | exits[i] | Action | weight Array | maxWeight | maxCell |
|-----------|---------------------|----------|--------|--------------|-----------|---------|
| 1         | 0                   | 1        | weight[1] += 0 | [0, 0, 0, 0, 0] → [0, 0, 0, 0, 0] | 0         | 1       |
| 2         | 1                   | 4        | weight[4] += 1 | [0, 0, 0, 0, 0] → [0, 0, 0, 0, 1] | 1         | 4       |
| 3         | 2                   | -1       | Skip    | [0, 0, 0, 0, 1] | 1         | 4       |
| 4         | 3                   | 2        | weight[2] += 3 | [0, 0, 0, 0, 1] → [0, 0, 3, 0, 1] | 3         | 2       |
| 5         | 4                   | 3        | weight[3] += 4 | [0, 0, 3, 0, 1] → [0, 0, 3, 4, 1] | 4         | 3       |

The cell with the maximum weight is 3.

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- **Empty Input**: If `exits` is empty, the function should return -1.
- **Single Element**: If `exits` has only one element, the function should return the index of that element if it is not -1.
- **All Elements Point to Same Cell**: If all elements point to the same cell, the function should return the index of that cell.
- **No Exits**: If all elements are -1, the function should return -1.
- **Large Input**: The function should handle large input sizes efficiently.

---

# 📚 Key Takeaways

- The weight of a cell is the sum of all indices that point to it.
- The brute force approach involves calculating the weight of each cell and then finding the cell with the maximum weight.
- The optimal approach involves calculating the weight of each cell and keeping track of the maximum weight and the corresponding cell in a single pass.
- The time complexity of both approaches is O(n), and the space complexity is O(n).

---

# 🚀 Interview Tips

- **Follow-up Questions**:
  - What if the input array is very large, and we need to find the maximum weight cell in O(1) space?
  - How can we optimize the solution further if the input array is sorted?
- **Common Pitfalls**:
  - Forgetting to handle the case where all elements are -1.
  - Not initializing `maxWeight` and `maxCell` correctly.
  - Not updating `maxWeight` and `maxCell` when the weight of a cell is equal to `maxWeight`.
- **Alternative Approaches**:
  - Using a hash map to store the weight of each cell and then finding the cell with the maximum weight.
  - Using a priority queue to keep track of the cells with the maximum weight.

---

# ✅ Conclusion

The optimal approach is preferred because it reduces the number of passes through the array and keeps track of the maximum weight and the corresponding cell in a single pass. This approach is efficient and handles large input sizes effectively.

---