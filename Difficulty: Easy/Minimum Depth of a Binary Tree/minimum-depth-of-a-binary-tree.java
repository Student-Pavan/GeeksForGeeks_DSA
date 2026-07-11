/*
class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}
*/

class Solution {
    int minDepth(Node root) {
        // code here
        if(root == null)
            return 0;
        if(root.left == null)
          return minDepth(root.right)+1;
        if(root.right == null)
            return minDepth(root.left) + 1;
        
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}