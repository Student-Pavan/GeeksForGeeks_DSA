/* Structure of a Binary Search Tree node
class Node {
    int data;
    Node left, right;
    Node(int x) {
        data = x;
        left = right = null;
    }
} */

class Solution {
    public ArrayList<Node> findPreSuc(Node root, int key) {

        ArrayList<Node> list = new ArrayList<>();

        Node pred = null;
        Node succ = null;
        Node curr = root;

        while (curr != null) {

            if (key < curr.data) {
                succ = curr;
                curr = curr.left;
            }

            else if (key > curr.data) {
                pred = curr;
                curr = curr.right;
            }

            else {

                // Find predecessor
                if (curr.left != null) {
                    Node temp = curr.left;

                    while (temp.right != null) {
                        temp = temp.right;
                    }

                    pred = temp;
                }

                // Find successor
                if (curr.right != null) {
                    Node temp = curr.right;

                    while (temp.left != null) {
                        temp = temp.left;
                    }

                    succ = temp;
                }

                break;
            }
        }

        list.add(pred);
        list.add(succ);

        return list;
    }
}