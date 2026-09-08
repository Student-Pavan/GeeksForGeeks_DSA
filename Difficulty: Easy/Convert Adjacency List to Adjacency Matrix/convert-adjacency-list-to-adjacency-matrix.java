class Solution {
    public int[][] adjToMat(ArrayList<ArrayList<Integer>> adj) {
        // code here
        int mat[][] = new int[adj.size()][adj.size()];
        
        for(int i = 0; i < adj.size(); i++){
            for(int node : adj.get(i)){
                mat[i][node] = 1;
            }
        }
        
        return mat;
    }
}
