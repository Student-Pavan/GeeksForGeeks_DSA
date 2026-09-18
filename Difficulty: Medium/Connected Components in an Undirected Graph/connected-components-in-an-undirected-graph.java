class Solution {
	public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
		// code here
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		
		for (int i = 0; i < V; i++) {
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			
			list.get(u).add(v);
			list.get(v).add(u);
		}
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		boolean[] vis = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> compo = new ArrayList<>();
			if (!vis[i]) {
				dfs(i, list, compo, vis);
				ans.add(compo);
			}
		}
		return ans;
	}
	
	private void dfs(int node, ArrayList<ArrayList<Integer>> list, ArrayList<Integer> compo, boolean []vis) {
		
		vis[node] = true;
		compo.add(node);
		
		for(int neigh : list.get(node)){
		    if(!vis[neigh]){
		        dfs(neigh,list,compo,vis);
		    }
		}
	}
}
