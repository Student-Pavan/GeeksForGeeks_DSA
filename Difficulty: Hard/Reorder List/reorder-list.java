/* Following is the Linked list node structure */

/*class Node
{
	int data;
	Node next;
	Node(int d) {
		data = d;
		next = null;
	}
} */

class Solution {
	void reorderlist(Node head) {
	    
	    
		// Your code here
		   if (head == null || head.next == null)
            return;

		Node head1 = head;
		Node middle = getMiddle(head);
		Node head2 = reverse(middle.next);
		middle.next = null;
		
		Node fronttrack = null;
		Node tailtrack = null;
		
		while (head1 != null && head2 != null){
		    fronttrack = head1.next;
		    tailtrack = head2.next;
		    
		    head1.next = head2;
		    head2.next = fronttrack;
		    
		    head1 = fronttrack;
		    head2 = tailtrack;
		    
		}
		
	}
	
	private Node getMiddle(Node head) {
		
		Node slow = head;
		Node fast = head;
		
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow;
	}
	private Node reverse(Node middle) {
		Node prev = null;
		Node curr = middle;
		Node next = null;
		
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			
			prev = curr;
			curr = next;
			
		}
		return prev;
	}
}
