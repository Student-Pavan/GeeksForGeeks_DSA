/*
Definition for Node
class Node {
	int data;
	Node left, right;
	Node(int d)
	{
		data = d;
		left = right = null;
	}
}
*/

class Solution {
	ArrayList<Integer> zigZagTraversal(Node root) {
		
		ArrayList<Integer> lst = new ArrayList<>();
		
		if (root == null)
			return lst;
		
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		
		boolean leftToRight = true;
		
		while (!q.isEmpty()) {
			
			LinkedList<Integer> ll = new LinkedList<>();
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				
				Node curr = q.poll();
				
				if (leftToRight)
					ll.addLast(curr.data);
				else
					ll.addFirst(curr.data);
				
				if (curr.left != null)
					q.offer(curr.left);
				
				if (curr.right != null)
					q.offer(curr.right);
			}
			
			lst.addAll(ll);
			leftToRight = !leftToRight;
		}
		
		return lst;
	}
}
