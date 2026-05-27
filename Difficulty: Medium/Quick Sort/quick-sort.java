class Solution {
	public void quickSort(int[] arr, int low, int high) {
		// code here
		if (low < high) {
			int pivot = partition(arr, low, high);
			
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}
	
	private int partition(int[] arr, int low, int high) {
		
		// code here
		int idx = low - 1;
		int pivot = arr[high];
		
		for (int i = low ; i < high ; i++) {
			if (arr[i] <= pivot) {
				idx ++;
				int temp = arr[i];
				arr[i] = arr[idx];
				arr[idx] = temp;
			}
		}
		idx++;
		int temp = arr[idx];
		arr[idx] = arr[high];
		arr[high] = temp;
		
		return idx;
		
	}
}
