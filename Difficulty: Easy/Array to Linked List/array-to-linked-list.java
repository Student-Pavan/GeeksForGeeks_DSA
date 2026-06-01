/*
// Representation of a node
class Node {
    int data;
    Node next;
    Node (int d) {
       data = d;
       next = null;
    }
};
*/
class Solution {
    public Node arrayToList(int arr[]) {
        
        // code here
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1; i < arr.length ; i++){
            Node newnode = new Node(arr[i]);
            temp.next = newnode;
            temp = newnode;
        }
        
        temp.next = null;
        
        return head;
    }
}
