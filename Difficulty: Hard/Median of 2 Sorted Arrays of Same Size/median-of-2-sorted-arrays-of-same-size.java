// User function Template for Java

class Solution {
	public double medianOf2(int a[], int b[]) {
		// Your Code Here
		int i = 0, j = 0;
		int m1 = -1;
		int m2 = -1;
		
		int n = a.length;
		
		for (int count = 0; count <= n ; count++) {
			
			m2 = m1;
			
			if (i < n && j < n) {
				if (a[i] < b[j]) {
					m1 = a[i++];
				}
				else {
					m1 = b[j++];
				}
			}
			
			else if (i < n) {
				m1 = a[i++];
			}
			else {
				m1 = b[j++];
			}
			
		}
		
		return (m1 + m2)/2.0;
		
	}
}
