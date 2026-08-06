/*
class Node {
	int data;
	Node left, right;
	
	Node(int item) {
		data = item;
		left = right = null;
	}
}
*/

class Solution {
	public Node sortedArrayToBST(int[] arr) {
		// code here
		return helper(arr,0,arr.length - 1);
	}
	
	public Node helper(int[] arr, int st, int end) {
	    if(st > end){
	        return null;
	    }
		
		int mid = st + (end - st) / 2;
		
		Node root = new Node (arr[mid]);
		
		root.left = helper(arr, st , mid -1);
		root.right = helper(arr, mid + 1 , end);
		
		
		return root;
	}
}
