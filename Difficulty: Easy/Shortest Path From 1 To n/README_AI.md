# Shortest Path From 1 To n

---

# 📝 Problem Statement

Given a number `n`, find the minimum number of steps required to reach `n` from `1` using only two operations:

1. Multiply by 2
2. Multiply by 3

**Objective**: Find the minimum number of steps required to reach `n` from `1`.

**Constraints**:
- `1 <= n <= 10^6`

---

# 💡 Intuition

The key insight is that to minimize the number of steps, we should always prefer the operation that reduces the number to `1` the fastest. This means we should prioritize division by 3 when possible, as it reduces the number more aggressively than division by 2.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves using a breadth-first search (BFS) to explore all possible paths from `1` to `n`. At each step, we consider both multiplying by 2 and multiplying by 3, and we keep track of the minimum number of steps required to reach `n`.

---

## 🔹 Algorithm

1. Initialize a queue to perform BFS, starting with the number `1` and `0` steps.
2. Use a visited set to keep track of numbers we have already processed to avoid cycles.
3. For each number dequeued, check if it equals `n`. If it does, return the current step count.
4. Otherwise, enqueue the number multiplied by 2 and multiplied by 3 if they haven't been visited yet.
5. Repeat the process until `n` is found.

---

## 🔹 Code

```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int minimumStep(int n) {
        if (n == 1) return 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        int steps = 0;
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current * 2 == n || current * 3 == n) {
                    return steps;
                }

                if (current * 2 <= n && !visited[current * 2]) {
                    queue.offer(current * 2);
                    visited[current * 2] = true;
                }

                if (current * 3 <= n && !visited[current * 3]) {
                    queue.offer(current * 3);
                    visited[current * 3] = true;
                }
            }
        }

        return -1;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm for `n = 6`:

| Step | Queue | Visited | Action |
|------|-------|----------|--------|
| 1    | [1]   | [true]   | Dequeue 1, enqueue 2 and 3 |
| 2    | [2, 3]| [true, true, true] | Dequeue 2, enqueue 4 and 6 |
| 3    | [3, 4, 6] | [true, true, true, true, true, true, true] | Dequeue 3, enqueue 6 and 9 |
| 4    | [4, 6, 6, 9] | [true, true, true, true, true, true, true, true, true, true] | Dequeue 4, enqueue 8 and 12 |
| 5    | [6, 6, 9, 8, 12] | [true, true, true, true, true, true, true, true, true, true, true, true, true] | Dequeue 6, return steps (2) |

The algorithm returns `2` steps, which is correct.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n) |
| Space Complexity | O(n) |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach involves working backwards from `n` to `1`, dividing by 3 or 2 whenever possible. This approach is more efficient because it avoids the overhead of BFS and directly computes the minimum steps.

---

## 🔹 Why This Works

By working backwards, we can always choose the operation that reduces the number to `1` the fastest. This ensures that we find the minimum number of steps required.

---

## 🔹 Algorithm

1. Initialize a counter to keep track of the number of steps.
2. While `n` is greater than `1`:
   - If `n` is divisible by 3, divide `n` by 3.
   - Else if `n` is divisible by 2, divide `n` by 2.
   - Else, subtract 1 from `n`.
   - Increment the step counter.
3. Return the step counter.

---

## 🔹 Code

```java
class Solution {
    public int minimumStep(int n) {
        int steps = 0;
        while (n > 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
            steps++;
        }
        return steps;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm for `n = 6`:

| Step | n | Operation | Steps |
|------|---|-----------|-------|
| 1    | 6 | Divide by 3 | 1 |
| 2    | 2 | Divide by 2 | 2 |

The algorithm returns `2` steps, which is correct.

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(log n) |
| Space Complexity | O(1) |

---

# 🔍 Edge Cases

- `n = 1`: The number of steps required is `0`.
- `n = 2`: The number of steps required is `1`.
- `n = 3`: The number of steps required is `1`.
- Large `n`: The algorithm should handle large numbers efficiently.

---

# 📚 Key Takeaways

- The optimal approach is more efficient than the brute force approach.
- Working backwards can simplify the problem and lead to a more efficient solution.
- The key insight is to always prefer the operation that reduces the number to `1` the fastest.

---

# 🚀 Interview Tips

- Discuss the trade-offs between BFS and the optimal approach.
- Ask about the constraints and whether the optimal approach is suitable.
- Consider follow-up questions about handling additional operations or constraints.

---

# ✅ Conclusion

The optimal approach is preferred because it is more efficient and easier to understand. The key insight is to always prefer the operation that reduces the number to `1` the fastest. The optimal solution has a time complexity of O(log n) and a space complexity of O(1).