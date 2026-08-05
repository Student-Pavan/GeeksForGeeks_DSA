/*
class Node {
    int data;
    Node left, right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    int preIndex = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public Node constructTree(int[] pre, int[] post) {

        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }

        return build(pre, post, 0, post.length - 1);
    }

    private Node build(int[] pre, int[] post, int l, int r) {

        if (preIndex >= pre.length || l > r)
            return null;

        Node root = new Node(pre[preIndex++]);

        // Leaf node
        if (l == r || preIndex >= pre.length)
            return root;

        // Next preorder element is left child
        int idx = map.get(pre[preIndex]);

        root.left = build(pre, post, l, idx);
        root.right = build(pre, post, idx + 1, r - 1);

        return root;
    }
}