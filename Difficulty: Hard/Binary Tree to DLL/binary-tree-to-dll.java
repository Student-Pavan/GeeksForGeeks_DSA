/* Structure for tree and linked list
class Node {
  public int data;
  public Node left, right;

  public Node(int x) {
      data = x;
      left = right = null;
  }
};*/
class Solution {
    public Node treeToDLL(Node root) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root,list);
        
        Node dummy = new Node(0);
        Node prev = dummy;

        for (int i = 0; i < list.size(); i++) {
            Node curr = new Node(list.get(i));

            prev.right = curr;
            curr.left = prev;

            prev = curr;
        }

        Node head = dummy.right;
        if (head != null) {
            head.left = null;
        }

        return head;
        
    }
    
    private void inorder(Node root, ArrayList<Integer> list){
        if(root == null)
            return ;
            
        inorder(root.left,list);
        list.add(root.data);
        inorder(root.right,list);
    }
}