class Solution {
    
    static Node nextRight;
    public static void flatten(Node root) {
        // code here
        nextRight = null;
        helper(root);
        
    }
    
    static void helper(Node root){
        if(root == null)
            return;
        
        helper(root.right);
        helper(root.left);
        
        root.left = null;
        root.right = nextRight;
        nextRight = root;
    }
}