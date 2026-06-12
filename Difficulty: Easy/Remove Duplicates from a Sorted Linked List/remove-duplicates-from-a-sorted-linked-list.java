/* Structure of linked list Node
class Node {
	int data;
	Node next;
	
	Node(int d) {
		data = d;
		next = null;
	}
}
*/
class Solution {
	Node removeDuplicates(Node head) {
		// code here
		if (head == null || head.next == null) {
			return head;
		}
		Node prev = head;
		Node Next = head.next;
		
		while (Next != null) {
			
			if (prev.data != Next.data) {
				prev.next = Next;
				prev = Next;
			}
			Next = Next.next;
			
			
		}
		prev.next = null;
		return head;
		
	}
}
