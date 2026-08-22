class Solution {
    static long count(int n) {
        long graphCount = 1;

        int edges = n * (n - 1) / 2;

        int i = 0;
        while (i < edges) {
            graphCount *= 2;
            i++;
        }

        return graphCount;
    }
}