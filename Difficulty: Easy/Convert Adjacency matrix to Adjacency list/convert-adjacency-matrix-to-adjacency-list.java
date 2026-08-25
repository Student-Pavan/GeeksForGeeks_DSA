class Solution {
    public ArrayList<ArrayList<Integer>> matToAdj(int[][] mat) {
        // code here
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        
        for(int i = 0 ; i < mat.length; i++){
            ArrayList<Integer> ll = new ArrayList<>();
            for(int j = 0 ; j < mat[0].length; j++){
                if(mat[i][j] == 1){
                    ll.add(j);
                }
            }
            list.add(ll);
        }
        
        return list;
    }
}