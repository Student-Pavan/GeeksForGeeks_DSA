class Solution {
	public int minCostPath(int[][] mat) {
		// code here
		int m = mat.length;
		int n = mat[0].length;
		
		int dist[][] = new int[m][n];
		
		for (int[] row : dist)
			Arrays.fill(row, Integer.MAX_VALUE);
		
		dist[0][0] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		
		pq.offer(new int[] {0, 0, 0});
		
		int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		
		while (!pq.isEmpty()) {
			
			int[] curr = pq.poll();
			int effort = curr[0];
			int row = curr[1];
			int col = curr[2];
			
			if (effort < dist[row][col])
				continue;
			
			if (row == m - 1 && col == n - 1)
				return effort;
			
			for (int[] dir : directions) {
				int newrow = row + dir[0];
				int newcol = col + dir[1];
				
				if (newrow < 0 || newrow >= m || newcol < 0 || newcol >= n) {
					continue;
					
				}
				int edgeEffort = Math.max(effort, Math.abs(mat[row][col] - mat[newrow][newcol]));
				
				if (edgeEffort < dist[newrow][newcol]) {
					dist[newrow][newcol] = edgeEffort;
					pq.offer(new int[] {edgeEffort, newrow, newcol});
				}
				
			}
		}
		return 0;
	}
}
