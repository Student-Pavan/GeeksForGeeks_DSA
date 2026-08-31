
class Solution {
    static int exactlyK(int arr[], int k) {
        // code here
        return getMost(arr,k) - getMost(arr,k-1);
       
    }
    static int getMost(int arr[], int k ){
        int count = 0;
        int left =0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int right = 0 ; right <arr.length ; right++){
            map.put(arr[right] , map.getOrDefault(arr[right],0)+1);
            
            
            while(map.size() > k){
                  map.put(arr[left] , map.get(arr[left])-1);
                  if(map.get(arr[left]) == 0){
                      map.remove(arr[left]);
                  }
                  left++;
            }
            count += right - left +1;
        }
        return count;
    }
}
