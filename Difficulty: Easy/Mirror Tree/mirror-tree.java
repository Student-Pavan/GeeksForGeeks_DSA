/* Structure of Binary Tree Node
class Node
{
	int data;
	Node left, right;
	Node(int item)
	{
		data = item;
		left = right = null;
	}
} */

class Solution {
	void mirror(Node root) {
		// code here
		Queue<Node> q = new LinkedList<>();
		
		q.offer(root);
		
		while (!q.isEmpty()) {
			
			Node curr = q.poll();
			
			Node temp = curr.left;
			
			curr.left = curr.right;
			curr.right = temp;
			
			if (curr.left != null) {
				q.offer(curr.left);
			}
			if (curr.right != null) {
				q.offer(curr.right);
			}
			
		}
		
	}
}
