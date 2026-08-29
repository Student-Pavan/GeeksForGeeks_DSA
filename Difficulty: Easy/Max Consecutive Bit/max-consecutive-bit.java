class Solution {
    public int maxConsecBits(int[] arr) {
        // code here
        int max1s = 0;
        int max0s = 0;
        int curr1 = 0;
        int curr0  = 0;
        for(int i =0 ; i < arr.length; i++){
            if(arr[i] == 1){
                curr1++;
                curr0 = 0;
            }
            else{
                curr0++;
                curr1 = 0;
            }
            max1s = Math.max(max1s,curr1);
            max0s = Math.max(max0s,curr0);
            
        } 
        return Math.max(max1s,max0s);
       
    }
}
