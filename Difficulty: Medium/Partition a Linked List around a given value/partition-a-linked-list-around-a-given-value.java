/* Structure of linked list Node
class Node {
  public:
    int data;
    Node* next;

    Node(int x) {
        data = x;
        next = null;
    }
};
*/
class Solution {
    public Node partition(Node head, int x) {

        Node left = new Node(0);   // < x
        Node mid = new Node(0);    // == x
        Node right = new Node(0);  // > x

        Node leftTail = left;
        Node midTail = mid;
        Node rightTail = right;

        Node curr = head;

        while (curr != null) {
            Node next = curr.next;

            if (curr.data < x) {
                leftTail.next = curr;
                leftTail = curr;
            } 
            else if (curr.data == x) {
                midTail.next = curr;
                midTail = curr;
            } 
            else {
                rightTail.next = curr;
                rightTail = curr;
            }

            curr = next;
        }

        rightTail.next = null;      // end of list
        midTail.next = right.next;  // mid -> right
        leftTail.next = mid.next;   // left -> mid

        if (left.next != null)
            return left.next;
        if (mid.next != null)
            return mid.next;
        return right.next;
    }
}