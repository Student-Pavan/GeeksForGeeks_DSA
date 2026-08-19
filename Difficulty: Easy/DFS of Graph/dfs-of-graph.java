class Solution {
	public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
		// code here
		
		ArrayList<Integer> ans = new ArrayList<>();
		int n = adj.size();
		boolean vis[] = new boolean[n];
		
		
		dfsTraversal(0,adj,vis,ans);
		return ans;
		
	}
	
	public void dfsTraversal(int node, ArrayList<ArrayList<Integer>> adj, boolean vis[], ArrayList<Integer> ans) {
		
		vis[node] = true;
		ans.add(node);
		
		for (int neighbour : adj.get(node)) {
			if (!vis[neighbour]) {
				dfsTraversal(neighbour, adj, vis, ans);
			}
		}
		
	}
}
