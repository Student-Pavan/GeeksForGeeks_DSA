/*
Definition for Node
class Node {
    int data;
    Node left, right;

    Node(int val)
    {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    public int height(Node root) {
        
        // code here
        if(root == null)
            return -1;
        int left = height(root.left);
        int right = height(root.right);
        
        return Math.max(left,right) + 1;
    }
}