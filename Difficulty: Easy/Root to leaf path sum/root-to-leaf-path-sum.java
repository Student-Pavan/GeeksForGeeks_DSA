/*
// A Binary Tree node
class Node {
	int data;
	Node left;
	Node right;
	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}
*/

class Solution {
	/*you are required to complete this function */
	int sum = 0;
	boolean hasPathSum(Node root, int target) {
		
		// Your code here
		
		if (root == null)
			return false;
			
		sum += root.data;
		
		if (root.left == null && root.right == null) {
			if (sum == target)
				return true;
		}
		
		
		boolean ans =  hasPathSum(root.left, target) || hasPathSum(root.right, target);
		
		sum -= root.data;
		
		return ans;
		
	}
}
