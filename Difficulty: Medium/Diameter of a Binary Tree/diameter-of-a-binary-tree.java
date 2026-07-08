/*
Definition for Node
class Node {
	int data;
	Node left;
	Node right;
	Node(int data) {
		this.data = data;
		left = right = null;
	}
} */

class Solution {
	int diameter = 0;
	public int diameter(Node root) {
		// code here
		
		countDiameter(root);
		return diameter;
	}
	
	private int countDiameter(Node root) {
		
		if (root == null)
			return 0;
		
		int leftheight = countDiameter(root.left);
		int rightheight = countDiameter(root.right);
		diameter = Math.max(diameter, leftheight + rightheight);
		
		return Math.max(leftheight, rightheight) + 1;
		
	}
}
