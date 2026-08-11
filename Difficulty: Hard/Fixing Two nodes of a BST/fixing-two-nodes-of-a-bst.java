/* Structure of a Binary Search Tree node
class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        data = val;
        left = right = null;
    }
} */
class Solution {
    
    Node first = null;
    Node second = null;
    Node prev = null;
    private void inorder(Node root){
        if(root == null)
            return;
        
        inorder(root.left);
        if(prev != null && root.data < prev.data){
            if(first == null)
                first = prev;
                
            second = root;
        }
        prev = root;
        inorder(root.right);
        
    }
    public Node correctBST(Node root) {
        // code here
        inorder(root);
        
        int temp = first.data;
        first.data = second.data;
        second.data = temp;
        
        return root;
    }
};