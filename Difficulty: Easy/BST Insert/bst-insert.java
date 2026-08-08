/* Structure of a Binary Search Tree node
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
} */

class Solution {
    public Node insert(Node root, int key) {
        // code here
        if(root == null){
            return new Node(key);
        }
        
        if(key < root.data){
            root.left =  insert(root.left, key);
        }
        
        else if(key > root.data){
            root.right =  insert(root.right, key);
        }
        
        return root;
        
    }
}
