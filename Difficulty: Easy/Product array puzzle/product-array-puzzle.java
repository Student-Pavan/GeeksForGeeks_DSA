class Solution {
    public static int[] productExceptSelf(int arr[]) {
        // code here
        int lp[] = new int[arr.length];
        int rp[] = new int[arr.length];
        int res[] = new int[arr.length];
        
        lp[0] = 1;
        for(int i = 1 ;i < arr.length ;i++){
            lp[i] = lp[i-1] * arr[i-1];
        }
        rp[arr.length - 1] = 1;
        for(int i = arr.length-2 ;i >= 0  ;i--){
            rp[i] = rp[i+1] * arr[i+1];
        }
        
        for(int i = 0 ;i < arr.length ;i++){
            res[i] = lp[i] * rp[i];
        }
        return res;
        
    }
}
