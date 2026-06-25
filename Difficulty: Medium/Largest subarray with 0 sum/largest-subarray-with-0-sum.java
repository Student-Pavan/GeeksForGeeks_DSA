class Solution {
    int maxLength(int arr[]) {
        // code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefixsum = 0;
        int maxlen = 0;
        
        map.put(0,-1);
        for(int i = 0 ; i < arr.length ; i++ ){
            prefixsum += arr[i];
            
            if(prefixsum == 0){
                maxlen = Math.max(maxlen,i+1);
            }
            else{
                if(map.containsKey(prefixsum)){
                    maxlen = Math.max(maxlen, i - map.get(prefixsum));
                }
                else{
                     map.put(prefixsum , i);
                }
                       
            }
            
        }
        return maxlen;
    }
}