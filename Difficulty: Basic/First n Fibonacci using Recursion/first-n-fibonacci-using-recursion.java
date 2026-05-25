// User function Template for Java

class Solution {
    // Function to return list containing first n fibonacci numbers.
    public static int[] fibonacciNumbers(int n) {
        // Your code here
        int ans[] = new int[n];
        
        for(int i = 0; i < n ; i++){
            ans[i] = fib(i);
        }
        
        return ans;
        
    }
    
    private static int fib(int n){
        if( n <= 1){
            return n;
        }
    
        return fib(n-1) + fib (n -2);
    }
    
}