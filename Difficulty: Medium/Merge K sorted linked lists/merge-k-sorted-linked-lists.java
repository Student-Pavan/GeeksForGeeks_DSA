/*
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
    Node mergeKLists(Node[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        // Store all elements
        for (Node head : arr) {
            Node temp = head;
            while (temp != null) {
                list.add(temp.data);
                temp = temp.next;
            }
        }

        Collections.sort(list);

        // Create new linked list
        Node dummy = new Node(-1);
        Node curr = dummy;

        for (int val : list) {
            curr.next = new Node(val);
            curr = curr.next;
        }

        return dummy.next;
    }
}