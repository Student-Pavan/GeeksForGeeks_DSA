// User function Template for Java

class Solution {
	public List<String> AllPossibleStrings(String s) {
		// Code here
		List<String> a = new ArrayList<>();
		
		subsequences(s, 0, "", a);
		
		a.remove("");
		
		Collections.sort(a);
		
		return a;
	}
	
	private void subsequences(String s, int idx, String newstr, List<String> a) {
		if (s.length() == idx) {
			a.add(newstr);
			return ;
		}
		
		char currchar = s.charAt(idx);
		subsequences(s, idx + 1, newstr + currchar , a);
		subsequences(s, idx + 1, newstr, a);
		
	}
}
