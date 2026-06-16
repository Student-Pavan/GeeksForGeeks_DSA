/*
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
    public Node intersectPoint(Node head1, Node head2) {
        // code here
        Node a = head1;
        Node b = head2;
        
        while(a != b){
            a = a == null ? head2 : a.next;
            b = b == null ? head1 : b.next;
        }
        return b;
    }
}