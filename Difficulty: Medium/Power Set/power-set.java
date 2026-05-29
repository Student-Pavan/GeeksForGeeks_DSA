// User function Template for Java

class Solution {
	public List<String> AllPossibleStrings(String s) {
		// Code here
		List<String> result = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.append(s.charAt(j));
                }
            }
            if (subset.length() > 0) {
                result.add(subset.toString());
            }
        }
        Collections.sort(result);
        return result;
	}
	
}
