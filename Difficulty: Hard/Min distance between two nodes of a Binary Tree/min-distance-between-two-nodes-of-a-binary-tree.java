/* A binary tree node
class Node {
	public int data;
	public Node left;
	public Node right;
	
	public Node(int val) {
		data = val;
		left = null;
		right = null;
	}
}
*/

class Solution {

    private Node LCA(Node root, int n1, int n2) {
        if (root == null)
            return null;

        if (root.data == n1 || root.data == n2)
            return root;

        Node leftLCA = LCA(root.left, n1, n2);
        Node rightLCA = LCA(root.right, n1, n2);

        if (leftLCA != null && rightLCA != null)
            return root;

        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    private int findDistance(Node root, int target, int dist) {
        if (root == null)
            return -1;

        if (root.data == target)
            return dist;

        int left = findDistance(root.left, target, dist + 1);

        if (left != -1)
            return left;

        return findDistance(root.right, target, dist + 1);
    }

    public int findDist(Node root, int a, int b) {
        Node lca = LCA(root, a, b);

        int d1 = findDistance(lca, a, 0);
        int d2 = findDistance(lca, b, 0);

        return d1 + d2;
    }
}