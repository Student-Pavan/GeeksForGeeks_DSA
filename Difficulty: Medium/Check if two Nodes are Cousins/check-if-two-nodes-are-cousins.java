/* Structure of binary tree Node
class Node {
	int data;
	Node left;
	Node right;
	
	Node(int val) {
		data = val;
		left = right = null;
	}
}; */
class Solution {
	public boolean areCousins(Node root, int a, int b) {
		// code here
		
		if (root == null || a == b)
			return false;
		
		Queue<Node> q = new LinkedList<>();
		
		q.offer(root);
		
		while (!q.isEmpty()) {
			int size = q.size();
			boolean foundA = false;
			boolean foundB = false;
			
			for (int i = 0; i < size ; i++) {
				Node curr = q.poll();
				
				if (curr.data == a)
					foundA = true;
				if (curr.data == b)
					foundB = true;
				if (curr.left != null && curr.right != null) {
					if ((curr.left.data == a && curr.right.data == b) ||
					(curr.left.data == b && curr.right.data == a)) {
						return false;
					}
				}
				
				if (curr.left != null)
					q.offer(curr.left);
				
				if (curr.right != null)
					q.offer(curr.right);
				
			}
			
			if (foundA && foundB)
				return true;
			if (foundA || foundB)
				return false;
			
		}
		
		return false;
		
	}
}
