class Solution {
	public ArrayList<Integer> topoSort(int V, int[][] edges) {
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
		Stack<Integer> stack = new Stack<>();
		
		boolean vis[] = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (!vis[i]) {
				dfs(i, vis, list, stack);
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		
		while(!stack.isEmpty()){
		    ans.add(stack.pop());
		}
		
		return ans;
		
		
	}
	
	private void dfs(int node, boolean[] vis, ArrayList<ArrayList<Integer>> list, Stack<Integer> stack){
	    vis[node] = true;
	    
	    for(int neigh : list.get(node)){
	        if(!vis[neigh]){
	            dfs(neigh,vis,list,stack);
	            
	        }
	    }
	    stack.push(node);
	}
}
