/* Structure of linked list Node
class Node
{
    int data;
    Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }
}
*/
class Solution {
    public Node reverseKGroup(Node head, int k) {

        if (head == null || k <= 1) return head;

        ArrayList<Integer> list = new ArrayList<>();

        Node temp = head;
        while (temp != null) {
            list.add(temp.data);
            temp = temp.next;
        }

        // Reverse every group (including the last incomplete group)
        for (int i = 0; i < list.size(); i += k) {

            int left = i;
            int right = Math.min(i + k - 1, list.size() - 1);

            while (left < right) {
                int t = list.get(left);
                list.set(left, list.get(right));
                list.set(right, t);
                left++;
                right--;
            }
        }

        temp = head;
        int idx = 0;

        while (temp != null) {
            temp.data = list.get(idx++);
            temp = temp.next;
        }

        return head;
    }
}