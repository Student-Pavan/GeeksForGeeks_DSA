/* Structure of a Tree Node
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
} */

class Solution {

    static int search(int[] inorder, int left, int right, int val) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    static Node helper(int[] preorder, int[] inorder, int[] preIdx,
                       int left, int right) {

        if (left > right) {
            return null;
        }

        // Create root
        Node root = new Node(preorder[preIdx[0]]);
        preIdx[0]++;

        // If only one node
        if (left == right) {
            return root;
        }

        // Find root in inorder
        int inIdx = search(inorder, left, right, root.data);

        // Build left subtree
        root.left = helper(preorder, inorder, preIdx, left, inIdx - 1);

        // Build right subtree
        root.right = helper(preorder, inorder, preIdx, inIdx + 1, right);

        return root;
    }

    public static Node buildTree(int[] inorder, int[] preorder) {

        int[] preIdx = {0};   // works like int& preIdx in C++

        return helper(preorder, inorder, preIdx, 0, inorder.length - 1);
    }
}