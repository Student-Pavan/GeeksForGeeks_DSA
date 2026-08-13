/*
Definition for Node
class Node {
	int data;
	Node left, right;
	
	public Node(int val)
	{
		data = val;
		left = right = null;
	}
}
*/

class Solution {
    int level = 0;
	public int kthSmallest(Node root, int k) {
		// code here
		if (root == null) {
			return - 1;
		}
		
		return inorder(root, k);
	}
	
	private int inorder(Node root, int k) {
		
		if (root == null)
			return - 1;
		
		int left = inorder(root.left, k);
		if (left != -1)
			return left;
		level++;
		
		if (level == k)
			return root.data;
		
		return inorder(root.right, k);
		
	}
}
