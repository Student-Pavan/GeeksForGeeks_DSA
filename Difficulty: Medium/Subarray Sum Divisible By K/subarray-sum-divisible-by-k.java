class Solution {
    public int subCount(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long prefixSum = 0;
        int count = 0;

        for (int num : arr) {
            prefixSum += num;

            int rem = (int)(prefixSum % k);
            if (rem < 0)
                rem += k;

            if (map.containsKey(rem))
                count += map.get(rem);

            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}