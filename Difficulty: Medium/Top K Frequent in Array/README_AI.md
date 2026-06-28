# 📌 Top K Frequent in Array

---

# 📝 Problem Statement

Given an integer array `arr` and an integer `k`, return the `k` most frequent elements. If multiple elements have the same frequency, return the larger element first.

**Constraints:**
- `1 <= arr.length <= 10^5`
- `-10^4 <= arr[i] <= 10^4`
- `k` is in the range `[1, the number of unique elements in the array]`

---

# 💡 Intuition

The key insight is that we need to count frequencies of each element, then sort them based on frequency (and by value if frequencies are equal), and finally select the top `k` elements. This involves three main steps: frequency counting, sorting, and selection.

---

# 🐌 Brute Force Approach

## 🔹 Approach

1. Count frequencies of each element using a hash map.
2. Convert the map to a list of elements.
3. Sort the list based on frequency (descending) and value (descending for equal frequencies).
4. Select the first `k` elements from the sorted list.

---

## 🔹 Algorithm

1. Create a frequency map to count occurrences of each element.
2. Convert the map keys to a list.
3. Sort the list using a custom comparator that sorts by frequency (descending) and value (descending).
4. Extract the first `k` elements from the sorted list.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> topKFreq(int[] arr, int k) {
        // Step 1: Count frequencies
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Convert to list
        ArrayList<Integer> elements = new ArrayList<>(freqMap.keySet());

        // Step 3: Sort by frequency (descending) and value (descending)
        elements.sort((a, b) -> {
            int freqCompare = freqMap.get(b) - freqMap.get(a);
            if (freqCompare != 0) {
                return freqCompare;
            }
            return b - a;
        });

        // Step 4: Select top k elements
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(elements.get(i));
        }

        return result;
    }
}
```

---

## 🔹 Dry Run

Let's dry run the algorithm with `arr = [1,1,1,2,2,3]` and `k = 2`:

| Step | Action | State |
|------|--------|-------|
| 1    | Initialize frequency map | `{}` |
| 2    | Count frequencies | `{1:3, 2:2, 3:1}` |
| 3    | Convert to list | `[1, 2, 3]` |
| 4    | Sort list | `[1, 2, 3]` (sorted by frequency descending) |
| 5    | Select top 2 | `[1, 2]` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n log n) (due to sorting) |
| Space Complexity | O(n) (for frequency map and list) |

---

# ⚡ Optimal Approach

## 🔹 Approach

1. Count frequencies of each element using a hash map.
2. Use a min-heap to keep track of the top `k` elements.
3. Iterate through the frequency map and maintain the heap, ensuring it only contains the `k` most frequent elements.
4. Extract elements from the heap to get the result.

---

## 🔹 Why This Works

This approach is more efficient because it avoids the O(n log n) sorting step. Instead, it uses a min-heap to maintain the top `k` elements in O(n log k) time, which is better when `k` is much smaller than `n`.

---

## 🔹 Algorithm

1. Create a frequency map to count occurrences of each element.
2. Use a min-heap to keep track of the top `k` elements based on frequency.
3. Iterate through the frequency map and for each element:
   - If the heap has fewer than `k` elements, add the current element.
   - Otherwise, compare the current element's frequency with the smallest frequency in the heap. If it's larger, remove the smallest and add the current element.
4. Extract elements from the heap to get the result.

---

## 🔹 Code

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> topKFreq(int[] arr, int k) {
        // Step 1: Count frequencies
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a min-heap to keep track of top k elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> {
                int freqCompare = freqMap.get(a) - freqMap.get(b);
                if (freqCompare != 0) {
                    return freqCompare;
                }
                return a - b;
            }
        );

        for (int num : freqMap.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 3: Extract elements from the heap
        ArrayList<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        // Step 4: Reverse to get descending order
        Collections.reverse(result);

        return result;
    }
}
```

---

## 🔹 Detailed Dry Run

Let's dry run the algorithm with `arr = [1,1,1,2,2,3]` and `k = 2`:

| Step | Action | Heap State |
|------|--------|------------|
| 1    | Initialize frequency map | `{}` |
| 2    | Count frequencies | `{1:3, 2:2, 3:1}` |
| 3    | Initialize min-heap | `[]` |
| 4    | Add 1 to heap | `[1]` |
| 5    | Add 2 to heap | `[1, 2]` |
| 6    | Add 3 to heap | `[2, 3]` (since 3 has lower frequency than 2) |
| 7    | Extract elements | `[2, 3]` |
| 8    | Reverse to get descending order | `[3, 2]` |

---

## 🔹 Complexity Analysis

| Complexity | Value |
|------------|-------|
| Time Complexity | O(n log k) |
| Space Complexity | O(n) |

---

# 🔍 Edge Cases

- Empty input array.
- Single element array.
- All elements are the same.
- `k` is equal to the number of unique elements.
- Negative numbers in the array.
- Large input size.

---

# 📚 Key Takeaways

1. **Frequency Counting:** Always start by counting frequencies of elements.
2. **Heap Usage:** Use a min-heap to efficiently track top `k` elements.
3. **Custom Comparator:** Sorting with a custom comparator is essential for handling ties.
4. **Time Complexity:** The optimal approach reduces time complexity from O(n log n) to O(n log k).

---

# 🚀 Interview Tips

1. **Follow-up Questions:**
   - What if `k` is very large compared to the array size?
   - Can we solve this in O(n) time?
   - How would you handle streaming data?

2. **Common Pitfalls:**
   - Forgetting to handle ties in frequency.
   - Not considering edge cases like empty input.
   - Inefficient sorting approach.

3. **Alternative Approaches:**
   - Using a bucket sort approach to achieve O(n) time complexity.

---

# ✅ Conclusion

The optimal approach using a min-heap is more efficient, especially for large `n` and small `k`. The key insight is to leverage a heap to maintain the top `k` elements, reducing the time complexity from O(n log n) to O(n log k). This approach is crucial for interview scenarios where performance matters.