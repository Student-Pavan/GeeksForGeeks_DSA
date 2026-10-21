class Solution {
    public static int findCheapestCost(int n, int[][] edges, int src, int dst, int k) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            graph.get(u).add(new int[]{v, cost});
        }

        int[][] dist = new int[n][k + 2];

        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        pq.offer(new int[]{0, src, 0});
        dist[src][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int cost = curr[0];
            int node = curr[1];
            int stops = curr[2];

            if (node == dst)
                return cost;

            if (stops == k + 1)
                continue;

            if (cost > dist[node][stops])
                continue;

            for (int[] neigh : graph.get(node)) {
                int next = neigh[0];
                int price = neigh[1];
                int newcost = cost + price;
                int newstops = stops + 1;

                if (newcost < dist[next][newstops]) {
                    dist[next][newstops] = newcost;
                    pq.offer(new int[]{newcost, next, newstops});
                }
            }
        }

        return -1;
    }
}