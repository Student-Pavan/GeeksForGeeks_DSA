/*
class Node {
    int data;
    Node next;

    Node(int d){
        data=d;
        next=null;
   }
}
*/

class Solution {
    public Node rotate(Node head, int k) {

        if(head == null || head.next == null || k == 0)
            return head;

        int len = 1;
        Node tail = head;

        while(tail.next != null){
            tail = tail.next;
            len++;
        }

        k = k % len;

        if(k == 0)
            return head;

        Node curr = head;

        for(int i = 1; i < k; i++){
            curr = curr.next;
        }

        Node newHead = curr.next;
        curr.next = null;

        tail.next = head;

        return newHead;
    }
}