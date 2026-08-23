class Solution {
     public int maxWeightCell(int[] exits) {
         int n = exits.length;
         int[] weight = new int[n];

         // Calculate the weight of each cell
         for (int i = 0; i < n; i++) {
             if (exits[i] != -1) {
                 weight[exits[i]] += i;
             }
         }

         // Find the cell with maximum weight
         int maxWeight = -1;
         int maxCell = -1;

         for (int i = 0; i < n; i++) {
             if (weight[i] >= maxWeight) {
                 maxWeight = weight[i];
                 maxCell = i;
             }
         }

         return maxCell;
     }
 }