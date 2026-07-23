class Solution {
	public boolean canRepresentBST(List<Integer> arr) {
		// code here
		Stack<Integer> stack = new Stack<>();
		
		int root = Integer.MIN_VALUE;
		
		for (int ele : arr) {
			
			if (ele < root)
				return false;
			
			while (!stack.isEmpty() && stack.peek() < ele) {
				root = stack.pop();
			}
			stack.push(ele);
		}
		return true;
	}
}
