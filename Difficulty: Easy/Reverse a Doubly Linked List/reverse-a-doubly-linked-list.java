/* Structure of doubly linked list node
class Node {
	int data;
	Node next;
	Node prev;
	
	Node(int data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}
*/
class Solution {
    public Node reverse(Node head) {
        Node prev = null;
        Node next = null;
        Node curr = head;

        while (curr != null) {
            next = curr.next;

            curr.next = prev;
            curr.prev = next;

            prev = curr;
            curr = next;
        }

        if (prev != null) {
            prev.prev = null;
        }

        return prev;
    }
}
