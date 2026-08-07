/*
Definition for Node
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
	public boolean search(Node root, int key) {
		// code here
		
		if(root == null)
		    return false;
		
		if (root.data == key)
			return true;
		
		if (root.data > key)
			return search(root.left, key);
		
		if (root.data<key)
		    return search(root.right,key);
		    
		 return false;
	}
}
