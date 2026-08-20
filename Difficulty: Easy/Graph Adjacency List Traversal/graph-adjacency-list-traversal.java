class Solution {
	public List<List<Integer>> printGraph(int V, int edges[][]) {
		// code here
		List<List<Integer>> list = new ArrayList<>();
		
		for (int i = 0 ; i < V ; i++) {
			list.add( new ArrayList<Integer>());
		}
		
		int E = edges.length;
		
		for(int i =0; i < E ;i++){
		    int u = edges[i][0];
		    int v = edges[i][1];
		    
		    list.get(u).add(v);
		    list.get(v).add(u);
		}
		return list;
	}
}
