class Solution {
    int floorSqrt(int n) {
        // code here
        if(n < 2){
            return n;
        }
        long left = 0, right = n/2;
        
        while(left <= right){
            long mid = left+(right-left) / 2;
            long square = mid * mid;
            
            if(square == n){
                return (int) mid;
            }
            else if(square < n){
                left =  mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        return (int)right;
    }
}