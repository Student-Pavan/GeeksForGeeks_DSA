class Solution {
    public int uniquePaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int dp[][] = new int[n][m]; 

        // If start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) {
            return 0;
        }
        
        for(int[] rows : dp){
            Arrays.fill(rows,-1);
            
        }
        return solve(0, 0, grid,dp ,n, m);
    }

    public int solve(int i, int j, int[][] grid, int[][] dp,int n, int m) {

        // Out of bounds or blocked cell
        if (i >= n || j >= m || grid[i][j] == 1) {
            return 0;
        }

        // Reached destination
        if (i == n - 1 && j == m - 1) {
            return 1;
        }
        
        if(dp[i][j] != -1){
            return dp[i][j];
        }

        // Move right + move down
        return dp[i][j] = solve(i + 1, j, grid,dp, n, m) +
               solve(i, j + 1, grid,dp, n, m);
    }
}