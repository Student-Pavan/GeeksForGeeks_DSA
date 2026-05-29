class Solution {
	public double medianOf2(int a[], int b[]) {
		// Code Here
		int n = a.length, m = b.length;
		
		int i = 0, j = 0;
		int m1 = -1;
		int m2 = -1;
		
		for (int k = 0; k <= (m + n)/2 ; k++) {
			
			m2 = m1;
			
			if (i <n && j <m) {
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
		
		if ((n + m) % 2 == 0) {
			return (m1 + m2) / 2.0;
		}
		else {
			return (double)m1;
		}
	}
}
