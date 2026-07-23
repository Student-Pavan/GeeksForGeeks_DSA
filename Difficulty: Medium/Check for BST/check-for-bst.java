/*
class Node {
	int data;
	Node left;
	Node right;
	
	Node(int val) {
		data = val;
		left = right = null;
	}
}
*/

class Solution {
	public boolean isBST(Node root) {
		// code here
		return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}
	private boolean checkBST(Node root, long min, long max) {
		if (root == null)
			return true;
		if (root.data <= min || root.data >= max)
			return false;
		
		return checkBST(root.left, min, root.data) && checkBST(root.right, root.data, max);
	}
}
