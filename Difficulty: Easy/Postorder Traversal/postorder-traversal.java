/*
class Node {
    int data;
    Node left, right;
    Node(int val){
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    public ArrayList<Integer> postOrder(Node root) {
        // code here
         ArrayList<Integer> list = new ArrayList<>();
         
         postordertraversal(root,list);
         
         return list;
         
    }
    public void postordertraversal(Node root, ArrayList<Integer> list){
        if(root == null)
            return;
        // left
        postordertraversal(root.left,list);
        // right
        postordertraversal(root.right,list);
        //root
        list.add(root.data);
    }
}