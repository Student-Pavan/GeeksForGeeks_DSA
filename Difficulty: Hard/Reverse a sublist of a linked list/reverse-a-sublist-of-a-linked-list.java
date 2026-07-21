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

        if (head == null || a == b)
            return head;

        Node first = head;
        int len = 1;

        // Find first node
        while (len < a) {
            first = first.next;
            len++;
        }

        Node beforefirst = null;
        if (a != 1) {
            beforefirst = head;
            while (beforefirst.next != first) {
                beforefirst = beforefirst.next;
            }
        }

        Node second = head;
        int len1 = 1;

        // Find second node
        while (len1 < b) {
            second = second.next;
            len1++;
        }

        Node aftersecond = second.next;

        // Reverse from first to second
        Node prev = aftersecond;
        Node temp = first;
        Node next = null;

        while (temp != aftersecond) {
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }

        // Reconnect
        if (beforefirst != null)
            beforefirst.next = prev;
        else
            head = prev;

        return head;
    }
}