/* A binary tree Node
class Node {
	public int data;
	public Node left;
	public Node right;
	
	// Constructor
	public Node(int val) {
		data = val;
		left = null;
		right = null;
	}
};
*/

class Solution {
	public ArrayList<Integer> levelOrder(Node root) {
		// code here
		ArrayList<Integer> ans = new ArrayList<>();
		
		Queue<Node> q = new LinkedList<>();
		if (root == null)
			return ans;
		
		q.offer(root);
		
		while (!q.isEmpty()) {
			
			int size = q.size();
			
			for (int i = 0; i < size ; i++) {
				Node curr = q.poll();
				ans.add(curr.data);
				
				if (curr.left != null)
					q.offer(curr.left);
				
				if ((curr.right != null))
					q.offer(curr.right);
			}
			
		}
		return ans;
	}
}
