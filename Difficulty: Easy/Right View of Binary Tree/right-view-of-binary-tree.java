/*
Definition for Node
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}
*/

class Solution {
    public ArrayList<Integer> rightView(Node root) {
        // code here
        ArrayList<Integer> list = new  ArrayList<>();
        
        rightTravaersal(root,list,0);
        
        return list;
         
    }
    
    private void rightTravaersal(Node root, ArrayList<Integer> list, int level){
        
        
        if(root == null)
            return;
            
        if(level == list.size())
            list.add(root.data);
        
        rightTravaersal(root.right, list, level + 1);
        rightTravaersal(root.left, list, level + 1);
    }
}