/* node class of the linked list

class Node
{
    int data;
    Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }
}

*/

class Solution {
    // Function to pairwise swap elements of a linked list.
    // It should returns head of the modified list
    public Node pairwiseSwap(Node head) {
        // code here
        if(head == null || head.next == null){
            return head;
        }
        
        Node first = head;
        Node sec = head.next;
        Node prev = null;
        
        while(first != null && sec != null){
            Node third = sec.next;
            
            sec.next = first;
            first.next = third;
            
            if(prev != null)
                prev.next = sec;
            else
                head = sec;
                
            
            prev = first;
            first = third;
            if(third != null)
                sec = third.next;
            
            else
                sec = null;
        }
        
        return head;
        
       
    }
}