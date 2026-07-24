/*
Definition for Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        data = val;
        left = right = null;

    }
}
*/

class Solution {
    
    class Pair{
        
        Node node;
        int hd;
        
        Pair(Node node, int hd){
            this.node = node;
            this.hd = hd;
        }
    }
    public ArrayList<Integer> bottomView(Node root) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        
        Queue<Node> q = new LinkedList<>();
        Queue<Integer> hdq = new LinkedList<>();
        
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        
        q.offer(root);
        hdq.add(0);
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            
            int hd = hdq.poll();
            
            treemap.put(hd ,curr.data);
            
            if(curr.left != null){
                q.offer(curr.left);
                hdq.offer(hd-1);
            }
            
            if(curr.right != null){
                q.offer(curr.right);
                hdq.offer(hd+1);
            }
        }
        
        for(int val : treemap.values())
            res.add(val);
            
        return res;
        
    }
}