class Solution {

    void mergeSort(int arr[], int l, int r) {
        
        if (l < r) {

            int mid = l + (r - l) / 2;

            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            merge(arr, l, mid, r);
        }
    }

    void merge(int[] arr, int left, int mid, int right) {

        int[] merged = new int[right - left + 1];

        int i = left;      // left half
        int j = mid + 1;   // right half
        int k = 0;

        // Merge both halves
        while (i <= mid && j <= right) {

            if (arr[i] <= arr[j]) {
                merged[k++] = arr[i++];
            } else {
                merged[k++] = arr[j++];
            }
        }

        // Remaining elements of left half
        while (i <= mid) {
            merged[k++] = arr[i++];
        }

        // Remaining elements of right half
        while (j <= right) {
            merged[k++] = arr[j++];
        }

        // Copy back to original array
        for (int x = 0; x < merged.length; x++) {
            arr[left + x] = merged[x];
        }
    }
}