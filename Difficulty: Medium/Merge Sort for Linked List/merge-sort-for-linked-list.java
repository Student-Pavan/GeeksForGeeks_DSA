/*
class Node {
	int data;
	Node next;
	
	Node(int key) {
		data = key;
		next = null;
	}
}
*/

class Solution {
	public Node mergeSort(Node head) {
		// code here
		return sortLL(head);
		
		
	}
	
	private Node sortLL(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node middle = findMiddle(head);
		Node left = head;
		Node right = middle.next;
		middle.next = null;
		
		left = sortLL(left);
		right = sortLL(right);
		
		return mergeList(left, right);
	}
	private Node findMiddle(Node head) {
		Node slow = head;
		Node fast = head.next;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	private Node mergeList(Node left, Node right){
	    if(left == null || right == null){
		    return left == null ? right : left;
		}
		
		if(left.data <= right.data){
		    left.next = mergeList(left.next, right);
		    return left;
		}
		else{
		    right.next = mergeList(left, right.next);
		    return right;
		}
	}
}
