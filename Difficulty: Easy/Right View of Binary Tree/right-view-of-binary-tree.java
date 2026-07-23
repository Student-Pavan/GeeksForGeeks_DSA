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
       Queue<Node> queue = new LinkedList<>();
        
        ArrayList<Integer> res = new ArrayList<>();
        
        if(root == null)
            return res;
        
        queue.add(root);
        
        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                Node curr = queue.poll();
                
                if(i == size - 1)
                    res.add(curr.data);
                    
                if(curr.left != null)
                    queue.add(curr.left);
                    
                if(curr.right != null)
                    queue.add(curr.right);
            }
        }
        return res;
        
    }
}