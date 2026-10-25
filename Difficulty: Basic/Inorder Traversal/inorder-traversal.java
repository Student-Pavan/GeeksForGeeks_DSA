/*
class Node {
    int data;
    Node left, right;
    Node(int item){
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    public ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        inorderTraversal(root, ans);
        return ans;
    }
    
    private void inorderTraversal(Node root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }
        
        // Left
        inorderTraversal(root.left, ans);
        
        // Root
        ans.add(root.data);
        
        // Right
        inorderTraversal(root.right, ans);
    }
}