
class Solution {
    public boolean checkPath(int V, int[][] edges, int src, int dest) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        // Build graph
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            list.get(u).add(v);
            list.get(v).add(u);
        }

        boolean[] vis = new boolean[V];

        return dfs(src, dest, vis, list);
    }

    public boolean dfs(int src, int dest, boolean[] vis,
                       ArrayList<ArrayList<Integer>> list) {

        if (src == dest)
            return true;

        vis[src] = true;

        for (int next : list.get(src)) {
            if (!vis[next] && dfs(next, dest, vis, list))
                return true;
        }

        return false;
    }
}