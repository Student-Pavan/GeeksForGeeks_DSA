class Solution {
	public int reverseExponentiation(int n) {
		// code here
		
		int num = n;
		int rev = 0;
		
		while (num > 0) {
			int rem = num % 10;
			rev = rev * 10 + rem;
			num /= 10;
		}
		
		int x = rev;
		if (x < 0) {
			num = 1/num;
			x = -x;
		}
		
		return (int)PowXN(n, x , 1);
		
	}
	
	private int PowXN(int n, long x , double ans) {
	    
	    if(x == 0){
	        return (int)ans;
	    }
	    
	    if(x % 2 != 0){
	        ans *= n;
	    }
	    
		return PowXN(n * n, x/2 ,ans);
	}
}
