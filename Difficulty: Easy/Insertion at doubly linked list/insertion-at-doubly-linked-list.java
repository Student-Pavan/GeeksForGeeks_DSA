/*
class Node
{
    int data;
    Node next;
    Node prev;
    Node(int data)
    {
        this.data = data;
        next = prev = null;
    }
}
*/

class Solution {
    Node insertAtPos(Node head, int p, int x) {

        Node newNode = new Node(x);

        Node temp = head;
        int pos = 0;

        while (temp != null) {

            if (pos == p) {

                newNode.next = temp.next;
                newNode.prev = temp;

                if (temp.next != null) {
                    temp.next.prev = newNode;
                }

                temp.next = newNode;
                break;
            }

            temp = temp.next;
            pos++;
        }

        return head;
    }
}