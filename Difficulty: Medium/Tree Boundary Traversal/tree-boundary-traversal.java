/* Node Structure
class Node {
	int data;
	Node left, right;
	
	Node(int val) {
		data = val;
		left = right = null;
	}
} */

class Solution {
	public ArrayList<Integer> boundaryTraversal(Node root) {
		// code here
		ArrayList<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		if (isLeaf(root)) {
			res.add(root.data);
			return res;
		}
		res.add(root.data);
		
		leftBoundary(root, res); // 1
		
		inorderTravForLeafs(root, res); // 2
		
		rightBoundary(root, res); // 3
		
		return res;
		
	}
	
	// leaf chech
	private boolean isLeaf(Node root) {
		if (root.left == null && root.right == null)
			return true;
		return false;
	}
	
	// Add left boundary (excluding leaves)
	private void leftBoundary(Node root, ArrayList<Integer> left) {
		Node curr = root.left;
		
		while (curr != null) {
			if (!isLeaf(curr))
				left.add(curr.data);
			if (curr.left == null)
				curr = curr.right;
			else
				curr = curr.left;
		}
		
	}
	
	// Add leaf nodes (inorder traversal)
	
	private void inorderTravForLeafs(Node root, ArrayList<Integer> leafNodes) {
		
		if (root == null) {
			return;
		}
		if (isLeaf(root)) {
			leafNodes.add(root.data);
			return;
		}
		
		inorderTravForLeafs(root.left, leafNodes);
		inorderTravForLeafs(root.right, leafNodes);
	}
	// Add right boundary (excluding leaves, in reverse)
	private void rightBoundary(Node root, ArrayList<Integer> right) {
		
		Node curr = root.right;
		Stack<Integer> stack = new Stack<>();
		
		while (curr != null) {
			if (!isLeaf(curr))
				stack.push(curr.data);
			if (curr.right == null)
				curr = curr.left;
			else
				curr = curr.right;
		}
		
		while (!stack.isEmpty())
			right.add(stack.pop());
		
	}
}
