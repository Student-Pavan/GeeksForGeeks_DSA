class Solution {
    public int maxWater(int arr[]) {
        int mostwater = 0 ;
        int left = 0 ; 
        int right = arr.length-1;
        while(left < right){
            int watertraped =(Math.min(arr[left],arr[right])*Math.abs(left-right));
            if(watertraped > mostwater){
                mostwater = watertraped;
            }
            else if(arr[left] > arr[right]){
                right--;
            }
            else{
                left++;
            }
        }
        return mostwater;
    }
}