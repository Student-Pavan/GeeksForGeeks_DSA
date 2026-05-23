class Solution {
    static void printTillN(int N) {
        // code here
        print(N);
    }
    
    private static  void print(int n){
        if(n == 0){
            return;
        }
        print(n-1);
        System.out.print(n + " ");
        
    }
}