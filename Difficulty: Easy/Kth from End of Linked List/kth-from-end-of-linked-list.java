/* Structure of linked list Node
class Node
{
	int data;
	Node next;
	
	Node(int d) {
		data = d;
		next = null;
	}
}
*/
class Solution {
	int getKthFromLast(Node head, int k) {
		// code here
		if (head == null) {
			return - 1;
		}
		if (head.next == null && k == 1) {
			return head.data;
		}
		
		int n = 0;
		Node len = head;
		Node curr = head;
		
		while (len != null) {
			n++;
			len = len.next;
		}
		if (k > n) {
			return - 1;
		}
		int track = 1;
		while (curr != null && track < (n - k + 1)) {
			curr = curr.next;
			track++;
		}
		return curr.data;
		
	}
}
