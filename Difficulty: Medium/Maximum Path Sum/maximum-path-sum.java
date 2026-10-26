/*
Definition for Node
class Node {
	int data;
	Node left, right;
	Node(int val) {
		data = val;
		left = right = null;
	}
}
*/

class Solution {
	int maxvalue = Integer.MIN_VALUE;
	int findMaxSum(Node root) {
		// code here
		
		maxsum(root);
		return maxvalue;
		
	}
	
	int maxsum(Node root) {
		if (root == null)
			return 0;
		
		int leftsum = Math.max(0, maxsum(root.left));
        int rightsum = Math.max(0, maxsum(root.right));
		
		maxvalue = Math.max(maxvalue, leftsum + rightsum + root.data);
		
		return Math.max(leftsum,rightsum) + root.data;
	}
}
