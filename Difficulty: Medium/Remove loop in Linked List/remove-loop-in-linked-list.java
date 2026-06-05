/* Structure of linked list Node
class Node {
    int data;
    Node next;
    Node(int val) {
        data = val;
        next = null;
    }
} */
class Solution {
    public static void removeLoop(Node head) {
        // code here
        
        Node slow = head;
        Node fast = head;
        boolean flag = false;
        while(fast != null && fast.next != null){
            
            slow = slow.next;
            fast = fast.next.next;
            
            if(fast == slow){
                flag = true;
                break;
            }
            
        }
        if(!flag){
            return;
        }
        
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        Node temp = slow;
        while(temp.next != slow){
            temp = temp.next;
        }
        temp.next = null;
        
    }
}