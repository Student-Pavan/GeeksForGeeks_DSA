
class Solution {
    public ArrayList<ArrayList<Integer>> findInOutDegree(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            ans.add(new ArrayList<>());
            ans.get(i).add(0); 
            ans.get(i).add(0); 
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

        
            ans.get(u).set(1, ans.get(u).get(1) + 1);

          
            ans.get(v).set(0, ans.get(v).get(0) + 1);
        }

        return ans;
    }
}