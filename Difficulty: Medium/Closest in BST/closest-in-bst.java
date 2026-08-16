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
    public int minDiff(Node root, int k) {

        Node prev = null;
        Node next = null;

        Node temp = root;

        while (temp != null) {

            if (k > temp.data) {
                prev = temp;
                temp = temp.right;
            }

            else if (k < temp.data) {
                next = temp;
                temp = temp.left;
            }

            else {
               
                // Find predecessor
                if (temp.left != null) {
                    Node curr = temp.left;

                    while (curr.right != null) {
                        curr = curr.right;
                    }

                    prev = curr;
                }

                // Find successor
                if (temp.right != null) {
                    Node curr = temp.right;

                    while (curr.left != null) {
                        curr = curr.left;
                    }

                    next = curr;
                }

               
                return 0;
            }
        }

        int diff = Integer.MAX_VALUE;

        if (prev != null) {
            diff = Math.min(diff, Math.abs(k - prev.data));
        }

        if (next != null) {
            diff = Math.min(diff, Math.abs(next.data - k));
        }

        return diff;
    }
}