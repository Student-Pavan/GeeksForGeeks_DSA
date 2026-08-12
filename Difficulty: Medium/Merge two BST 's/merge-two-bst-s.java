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
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        inorder(r1,list1);
        inorder(r2,list2);
        
        int i = 0, j=0;
        
        while(i < list1.size() && j < list2.size()){
            int a = list1.get(i);
            int b = list2.get(j);
            if( a < b){
                list.add(a);
                i++;
            }
            else{
                list.add(b);
                j++;
                
            }
                
            
        }
        
        while(i < list1.size()){
            list.add(list1.get(i));
            i++;
        }
        while(j < list2.size()){
            list.add(list2.get(j));
            j++;
        }
        
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