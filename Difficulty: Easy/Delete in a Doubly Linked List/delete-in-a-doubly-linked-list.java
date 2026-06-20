/*
Structure of a Doubly LinkList
class Node {
	int data;
	Node next;
	Node prev;
	
	Node(int val) {
		data = val;
		next = null;
		prev = null;
	}
}
*/
class Solution {
	public Node delPos(Node head, int x) {
		// code here
		Node temp = head;
		
		if (x == 1) {
			head = head.next;
			if (head != null) {
				head.prev = null;
			}
			return head;
			
		}
		
		int i = 0;
		while (temp != null) {
			i++;
			
			if (i == x - 1 && temp.next != null && temp.next.next != null) {
				temp.next = temp.next.next;
				temp.next.prev = temp;
				break;
				
			}
			
			if (i == x - 1 && temp.next != null && temp.next.next == null) {
				temp.next = null;
				break;
			}
			
			temp = temp.next;
		}
		
		return head;
	}
}
