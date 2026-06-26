// User function Template for Java

import java.util.*;

class Solution {
    public int subCount(int[] arr, int k) {
       int n = arr.length, res = 0;
        Map<Integer, Integer> prefCnt = new HashMap<>();
        int sum = 0;

        for (int i = 0; i < n; i++) {

            sum = ((sum + arr[i]) % k + k) % k;

            if (sum == 0)
                res += 1;

            
            res += prefCnt.getOrDefault(sum, 0);

            prefCnt.put(sum, prefCnt.getOrDefault(sum, 0) + 1);
        }
        return res;
        
    }
        
}