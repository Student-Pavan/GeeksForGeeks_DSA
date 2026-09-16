class Solution {
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		// code here
		int original = image[sr][sc];
		
		if (original == newColor)
			return image;
		
		dfs(image, sr, sc, original, newColor);
		
		return image;
	}
	
	private void dfs(int[][] image, int row, int col, int original, int newColor) {
		if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != original) {
			return ;
		}
		
		
		image[row][col] = newColor;
		
		dfs(image,row+1,col,original,newColor);
		dfs(image,row-1,col,original,newColor);
		dfs(image,row,col+1,original,newColor);
		dfs(image,row,col-1,original,newColor);
	}
}
