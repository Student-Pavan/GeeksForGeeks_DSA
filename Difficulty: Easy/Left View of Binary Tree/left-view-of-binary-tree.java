/*
class Node {
	int data;
	Node left, right;
	
	Node(int val) {
		this.data = val;
		this.left = null;
		this.right = null;
	}
}
*/

class Solution {
	public ArrayList<Integer> leftView(Node root) {
		// code here
		
		ArrayList<Integer> ans = new ArrayList<>();
		leftViewTraversal(root, 0, ans);
		return ans;
		
	}
	
	private void leftViewTraversal(Node root, int level, ArrayList<Integer> list) {
		
		if (root == null)
			return;
		
		if (list.size() == level)
			list.add(root.data);
		
		leftViewTraversal(root.left, level + 1, list);
		leftViewTraversal(root.right, level + 1, list);
		
	}
}
