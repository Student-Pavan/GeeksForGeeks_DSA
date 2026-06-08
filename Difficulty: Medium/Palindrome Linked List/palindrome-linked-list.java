/*
class Node {
	int data;
	Node next;
	
	Node(int d) {
		data = d;
		next = null;
	}
} */

class Solution {
	public boolean isPalindrome(Node head) {
		// code here
		int len = 0;
		Node length = head;
		
		while (length != null) {
			len++;
			length = length.next;
		}
		
		Node getmid = head;
		int mid = 0;
		for (int i = 0; i < len/2; i++) {
			getmid = getmid.next;
		}
		if (len % 2 == 1)
			getmid = getmid.next;
		
		Node prev = null;
		Node curr = getmid;
		Node next = null;
		
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			
			prev = curr;
			curr = next;
		}
		Node first = head;
		Node second = prev;
		
		while (second != null) {
			if (second.data != first.data) {
				return false;
			}
			first = first.next;
			second = second.next;
		}
		return true;
		
	}
}
