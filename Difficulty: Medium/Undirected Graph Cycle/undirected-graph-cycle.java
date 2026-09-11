class Solution {
	public boolean isCycle(int V, int[][] edges) {
		// Code here
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			
			list.get(u).add(v);
			list.get(v).add(u);
			
		}
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			
			if (!visited[i]) {
				if (dfs(i, -1, list, visited)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
		visited[node] = true;
		
		for (int neighbour : adj.get(node)) {
			if (!visited[neighbour]) {
				if (dfs(neighbour,node, adj, visited)){
				  return true;  
				}
			}
			
			else if (neighbour != parent){
			    return true;
			}
		}
		return false;
	}
}
