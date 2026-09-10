class Solution {
	public boolean checkEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
		//   code here
		
		for (int node : adj.get(u)) {
			
			if (node == v) {
				return true;
			}
			
		}
		return false;
	}
	
}

