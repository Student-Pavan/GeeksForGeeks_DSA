class Solution {
    public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

      
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[1] - b[1]);

        dist[src] = 0;
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int node = current[0];
            int distance = current[1];

            if (distance > dist[node]) {
                continue;
            }

            for (int[] neighbor : adj.get(node)) {

                int nextNode = neighbor[0];
                int weight = neighbor[1];

                int newDistance = distance + weight;

                if (newDistance < dist[nextNode]) { // relaxation
                    dist[nextNode] = newDistance;
                    pq.offer(new int[]{nextNode, newDistance});
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int d : dist) {
            result.add(d);
        }

        return result;
    }
}