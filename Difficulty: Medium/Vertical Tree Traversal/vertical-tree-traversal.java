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
    public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        // code here
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        
        if(root == null)
            return list;
        
        TreeMap<Integer, ArrayList<Integer>> treemap = new TreeMap<>();
        
        
        Queue<Node> queue = new LinkedList<>();
        
        Queue<Integer> hdq = new LinkedList<>();
        
        
        queue.add(root);
        hdq.add(0);
        
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            
            int hd = hdq.poll();
            
            treemap.putIfAbsent(hd,new ArrayList<>());
            treemap.get(hd).add(curr.data);
            
            if(curr.left != null){
                queue.offer(curr.left);
                hdq.offer(hd - 1);
            }
            if(curr.right != null){
                queue.offer(curr.right);
                hdq.offer(hd + 1);
            }
        }
        
        for(ArrayList<Integer>  l : treemap.values()){
            list.add(l);
        }
        return list;
    }
}