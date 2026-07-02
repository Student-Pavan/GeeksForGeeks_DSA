class Solution {
    public int longestKSubstr(String s, int k) {
        // code here(
        HashMap<Character, Integer> map = new HashMap<>();
        
        int left = 0;
        int ans = -1;
        for(int right = 0; right < s.length() ; right++){
            char curr = s.charAt(right);
            map.put(curr,map.getOrDefault(curr,0)+1);
            
            while(map.size() > k){
                char leftchar = s.charAt(left);
                map.put(leftchar, map.getOrDefault(leftchar,0)-1);
                
                if(map.get(leftchar) == 0)
                    map.remove(leftchar);
                
                left++;
            }
            if(map.size() == k)
                ans = Math.max(ans,right - left + 1);
        }
        
        return ans;
    }
}