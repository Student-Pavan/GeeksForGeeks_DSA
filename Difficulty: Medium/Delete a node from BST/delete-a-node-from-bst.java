/* Structure of a Binary Search Tree node
class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int val) {
        data = val;
        left = right = null;
    }
}; */

class Solution {
    public Node delNode(Node root, int key) {
        // code here
        if(root == null)
            return root;
        
        if(root.data == key)
            return helper(root);
            
        Node dummy = root;
        
        while(root != null){
            
            if(root.data > key){
                if(root.left != null && root.left.data == key){
                    root.left = helper(root.left);
                    break;
                }
                else
                    root = root.left;
            }
            
            else{
                if(root.right != null && root.right.data == key){
                    root.right = helper(root.right);
                    break;
                }
                else
                    root = root.right;
            }
        }
        return dummy;
    }
    
    
    public Node helper(Node root){
        if(root.left == null)
            return root.right;
        else if(root.right == null)
            return root.left;
        else{
            Node rightchild = root.right;
            Node lastrightchild = findLastRightChild(root.left);
            lastrightchild.right = rightchild;
            return root.left;
        
         }  
       
        
    }
    
    public Node findLastRightChild(Node root){
        if(root.right == null)
            return root;
        return findLastRightChild(root.right);
    }
}