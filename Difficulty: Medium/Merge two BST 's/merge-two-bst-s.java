/* Structure of a Binary Search Tree node
class Node {
    int data;
    Node left, right;

    public Node(int val)
    {
        data = val;
        left = right = null;
    }
} */

class Solution {
    public ArrayList<Integer> merge(Node r1, Node r2) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        inorder(r1,list);
        inorder(r2,list);
        
        Collections.sort(list);
        return list;
    }
    
    private void inorder(Node root ,ArrayList<Integer> list){
        if(root == null)
            return;
            
        inorder(root.left,list);
        list.add(root.data);
        inorder(root.right,list);
    }
}