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

    class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public ArrayList<Integer> topView(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();

        if (root == null)
            return ans;

        Queue<Pair> q = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        q.offer(new Pair(root, 0));

        // BFS (Level Order Traversal)
        while (!q.isEmpty()) {

            Pair curr = q.poll();

            // First node at this horizontal distance
            if (!map.containsKey(curr.hd))
                map.put(curr.hd, curr.node.data);

            if (curr.node.left != null)
                q.offer(new Pair(curr.node.left, curr.hd - 1));

            if (curr.node.right != null)
                q.offer(new Pair(curr.node.right, curr.hd + 1));
        }

        for (int val : map.values())
            ans.add(val);

        return ans;
    }
}