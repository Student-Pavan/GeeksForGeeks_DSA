/*
class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    public ArrayList<Integer> preOrder(Node root) {
        //  code here
        ArrayList<Integer> ans = new ArrayList<>();
        preordertraversal(root,ans);
        return ans;
    }
    public void preordertraversal(Node root ,ArrayList<Integer> ans){
        if(root == null)
            return;
        //root
        ans.add(root.data);
        //left
        preordertraversal(root.left,ans);
        //right
        preordertraversal(root.right,ans);
        
    }
    
    
}