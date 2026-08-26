class Solution {
    public int maxWater(int height[]) {
        // code here
        int water = 0;
        int left_max = height[0] ,right_max = height[height.length-1];
        int left = 0 ;
        int right = height.length-1; 
        while(left < right){
            if(left_max < right_max){
                left++;
                left_max = Math.max(left_max,height[left]);
                water += left_max - height[left];
            }
            else{
                right--;
                right_max = Math.max(right_max,height[right]);
                water += right_max - height[right];
            }
        }
        return water;
        
        
    }
}
