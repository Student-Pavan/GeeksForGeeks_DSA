class Solution {
	public int findDelayTime(int V, int[][] edges, int src) {
		// code here
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		
		for (int i = 0; i<V; i++)
			graph.add(new ArrayList<>());
		
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			int wt = edge[2];
			
			graph.get(u).add(new int[] {v, wt});
			
		}
		
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]-b[0]);
		
		dist[src] = 0;
		pq.offer(new int[] {src, 0});
		
		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			
			int node = current[0];
			int distance = current[1];
			
			if (distance < dist[node])
				continue;
			
			for (int[] neighbor : graph.get(node)) {
				
				int nextNode = neighbor[0];
				int weight = neighbor[1];
				
				int newDistance = distance + weight;
				
				if (newDistance < dist[nextNode]) { // relaxation
					dist[nextNode] = newDistance;
					pq.offer(new int[] {nextNode, newDistance});
				}
			}
		}
		
		int maxTime = 0;
		
		for(int i = 0; i < V ; i++){
		    if(dist[i] == Integer.MAX_VALUE)
		        return -1;
		    maxTime = Math.max(maxTime,dist[i]);
		}
		return maxTime;
		
	}
}
