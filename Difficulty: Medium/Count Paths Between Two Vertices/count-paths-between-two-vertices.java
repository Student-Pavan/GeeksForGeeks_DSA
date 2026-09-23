class Solution {
    public int countPaths(int V, int[][] edges, int src, int dest) {

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        int[] dp = new int[V];

        Arrays.fill(dp, -1);

        return dfs(src, dest, graph, dp);
    }

    private int dfs(int node, int dest,
                    ArrayList<ArrayList<Integer>> graph,
                    int[] dp) {

    
        if (node == dest) {
            return 1;
        }

       
        if (dp[node] != -1) {
            return dp[node];
        }

        int count = 0;

        for (int neigh : graph.get(node)) {
            count += dfs(neigh, dest, graph, dp);
        }

        dp[node] = count;

        return count;
    }
}