class Solution {
	public boolean isCyclic(int V, int[][] edges) {
		// code here
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			
			list.get(u).add(v);
		}
		
		boolean[] vis = new boolean[V];
		boolean[] pathVis = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			if (!vis[i]) {
				if (dfs(i, vis, list, pathVis))
					return true;
			}
		}
		
		return false;
	}
	
	private boolean dfs(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, boolean[] pathVis) {
		vis[node] = true;
		pathVis[node] = true;
		
		for (int nextnode : adj.get(node)) {
			
			if (!vis[nextnode]) {
				if (dfs(nextnode, vis, adj, pathVis))
					return true;
			}
			
			else if (pathVis[nextnode])
				return true;
		}
		
		pathVis[node] = false;
		
		return false;
	}
}
