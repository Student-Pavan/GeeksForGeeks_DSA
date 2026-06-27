class Solution {
    public int cntSubarrays(int[] arr, int k) {
        // code here
        HashMap<Integer,Integer> map = new HashMap<>();
        
        map.put(0,1);
        int prefixsum = 0;
        int count = 0;
        
        for(int ele : arr){
            prefixsum += ele;
            
            
            if(map.containsKey(prefixsum-k))
                count += map.get(prefixsum -k);
                
            map.put(prefixsum,map.getOrDefault(prefixsum,0) + 1);
            
        }
        return count;
        
    }
}