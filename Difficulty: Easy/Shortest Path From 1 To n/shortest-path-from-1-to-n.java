class Solution {
	public int minimumStep(int n) {
		// code here
		int paths = 0;
		while (n > 1) {
			if (n % 3 == 0) {
				n /= 3;
				paths += 1;
			}
			else {
				n -= 1;
				paths += 1;
				
			}
		}
		return paths;
	}
}
