class Solution {
	public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
		// code here
		ArrayList<Integer> result = new ArrayList<>();
		int n = adj.size();
		boolean vis[] = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(0);
		vis[0] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			result.add(node);
			
			for (int neighbor : adj.get(node)) {
				if (!vis[neighbor]) {
					vis[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
		
		return result;
		
	}
}
