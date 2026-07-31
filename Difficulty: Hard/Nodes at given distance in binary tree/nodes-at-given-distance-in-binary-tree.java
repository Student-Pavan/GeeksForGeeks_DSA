/* Structure of Binary Tree Node
class Node {
	public int data;
	public Node left;
	public Node right;
	
	public Node(int val) {
		data = val;
		left = null;
		right = null;
	}
};
*/
class Solution {
	
	// Storing parent element of every node
	private void parentTrack(Node root, Map<Node, Node> map) {
		Queue<Node> q = new LinkedList<>();
		
		q.offer(root);
		
		while (!q.isEmpty()) {
			Node curr = q.poll();
			
			if (curr.left != null) {
				map.put(curr.left, curr);
				q.offer(curr.left);
			}
			if (curr.right != null) {
				map.put(curr.right, curr);
				q.offer(curr.right);
			}
		}
	}
	
	// getting target Node
	private Node getTarget(Node root, int target) {
		if (root == null)
			return null;
		if (root.data == target)
			return root;
		
		Node left = getTarget(root.left, target);
		if (left != null)
			return left;
		
		return getTarget(root.right, target);
	}
	public ArrayList<Integer> kDistanceNodes(Node root, int target, int k) {
		// code here
		Map<Node, Node> map = new HashMap<>();
		parentTrack(root, map);
		
		Node targetNode = getTarget(root, target);
		
		Queue<Node> q = new LinkedList<>();
		Map<Node, Boolean> visited = new HashMap<>();
		
		q.offer(targetNode);
		visited.put(targetNode, true);
		
		int currLevel = 0;
		
		while (!q.isEmpty()) {
			
			int size = q.size();
			
			if (currLevel == k)
				break;
			
			currLevel++;
			
			for (int i = 0; i < size; i++) {
				
				Node curr = q.poll();
				
				if (curr.left != null && !visited.containsKey(curr.left)) {
					visited.put(curr.left, true);
					q.offer(curr.left);
				}
				if (curr.right != null && !visited.containsKey(curr.right)) {
					visited.put(curr.right, true);
					q.offer(curr.right);
				}
				if (map.get(curr) != null && !visited.containsKey(map.get(curr))) {
					visited.put(map.get(curr), true);
					q.offer(map.get(curr));
				}
			}
			
		}
		
		ArrayList<Integer> ans = new ArrayList<>();
		
		while(!q.isEmpty()){
		    ans.add(q.poll().data);
		}
		Collections.sort(ans);
		
		return ans;
		
	}
}
