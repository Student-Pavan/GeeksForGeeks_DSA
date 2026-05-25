class Solution {
    public int countWays(int n) {
        
        if(n <= 1) return 1;
        if(n == 2) return 2;
        
        int prev2 = 1;
        int prev1 = 2;
        
        for(int i = 3; i <= n; i++){
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}