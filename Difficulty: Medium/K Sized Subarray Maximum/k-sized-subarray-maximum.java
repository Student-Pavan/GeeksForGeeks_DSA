class Solution {
    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();

        for (int right = 0; right < arr.length; right++) {

            // Remove indices out of window
            while (!dq.isEmpty() && dq.peekFirst() <= right - k) {
                dq.pollFirst();
            }

            // Maintain decreasing order in deque
            while (!dq.isEmpty() && arr[dq.peekLast()] < arr[right]) {
                dq.pollLast();
            }

            dq.addLast(right);

            // Add max of window
            if (right >= k - 1) {
                list.add(arr[dq.peekFirst()]);
            }
        }

        return list;
    }
}