class Solution {
    public boolean isPalindrome(int n) {
        // code here
        n = Math.abs(n);
        String value = String.valueOf(n);
        
        return checkPalin(value, 0, value.length() - 1);
        
    }
    
    private boolean checkPalin(String value, int left, int right){
        if(left >= right){
            return true;
        }
        if(value.charAt(left) != value.charAt(right)){
            return false;
        }
        return checkPalin(value , left+1, right-1);
    }
}