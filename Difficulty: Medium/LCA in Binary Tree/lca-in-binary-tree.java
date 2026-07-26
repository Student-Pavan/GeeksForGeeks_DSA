    /*
    Definition for Node
    class Node {
    	int data;
    	Node left;
    	Node right;
    	
    	Node(int val) {
    		this.data = val;
    		left = right = null;
    	}
    }
    } */
    
    class Solution {
    	Node lca(Node root, int n1, int n2) {
    		// code here
    		if (root == null)
    			return null;
    		
    		if (root.data == n1 || root.data == n2)
    			return root;
    		
    		Node leftLCA = lca(root.left, n1, n2);
    		Node rightLCA = lca(root.right, n1, n2);
    		
    		if (leftLCA != null &&  rightLCA != null)
    			return root;
    		else if (leftLCA != null)
    			return leftLCA;
    		else
    			return rightLCA;
    	}
    }
