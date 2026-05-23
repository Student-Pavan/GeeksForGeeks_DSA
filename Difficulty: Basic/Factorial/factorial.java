class Solution {
    // Function to calculate factorial of a number.
    int factorial(int n) {
        // code here
        return fact(n);
    }
    private int fact(int n){
        if(n == 1 || n == 0){
            return 1;
        }
        return n * fact(n - 1);
    }
}
