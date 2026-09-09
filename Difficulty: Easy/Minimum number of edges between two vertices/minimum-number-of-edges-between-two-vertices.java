class Solution {
    public int minEdges(int V, int[][] edges, int U, int v) {

       
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // Convert edges into adjacency list

        for (int i = 0; i < V; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            list.get(a).add(b);
            list.get(b).add(a);
        }

        int[] distance = new int[V];// distance track
        Arrays.fill(distance, -1);

        
        Queue<Integer> qu = new LinkedList<>();//  BFS

        qu.add(U);
        distance[U] = 0;

        while (!qu.isEmpty()) {

            int current = qu.poll();

           
            if (current == v) {
                return distance[v]; //  reached
            }

            
            for (int node : list.get(current)) {// visit neighbors

                if (distance[node] == -1) {

                    distance[node] = distance[current] + 1;

                    qu.add(node);
                }
            }
        }

        
        return -1;
    }
}