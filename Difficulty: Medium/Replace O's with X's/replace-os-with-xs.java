class Solution {
    public void fill(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        // First and last rows
        for (int i = 0; i < m; i++) {
            if (grid[0][i] == 'O')
                bfs(grid, 0, i);

            if (grid[n - 1][i] == 'O')
                bfs(grid, n - 1, i);
        }

        // First and last columns
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 'O')
                bfs(grid, i, 0);

            if (grid[i][m - 1] == 'O')
                bfs(grid, i, m - 1);
        }

        // Convert remaining O -> X
        // Convert protected cells X -> O
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                } else if (grid[i][j] == '#') {
                    grid[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(char[][] grid, int row, int col) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{row, col});

        // Mark boundary-connected O as protected
        grid[row][col] = '#';

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {

                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < m &&
                    grid[nr][nc] == 'O') {

                    grid[nr][nc] = '#';

                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }
}