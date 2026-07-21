/* Structure of a Linked List Node
class Node {
	int data;
	Node next;
	
	Node(int x) {
		data = x;
		next = null;
	}
}
*/

class Solution {
	Node reverseBetween(int a, int b, Node head) {
		// code here
		Node dummy = new Node(0);
		dummy.next = head;
		
		Node prevleft = dummy;
		
		for (int i = 1; i < a; i++)
			prevleft = prevleft.next;
		
		Node curr = prevleft.next;
		
		Node prev = null;
		
		
		for (int i = a; i<= b ; i++){
		    Node next = curr.next;
		    curr.next = prev;
            prev = curr;
            curr = next;
		}
		
		Node left = prevleft.next;
		prevleft.next = prev;
		left.next = curr;
		
		
		return dummy.next;
	}
		
}
