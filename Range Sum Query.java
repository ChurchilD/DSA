Q2 - Range Sum Query
You are given an integer array A of length N.
You are also given a 2D integer array B with dimensions M x 2, where each row denotes a [L, R] query.
For each query, you have to find the sum of all elements from L to R indices in A (0 - indexed).
More formally, find A[L] + A[L + 1] + A[L + 2] +... + A[R - 1] + A[R] for each query.

Approach  : This problem can be solved in O(N) TC with prefix sum approach

Solution : 
public class Solution {
    public long[] rangeSum(int[] A, int[][] B) {
        int n = A.length;
        int m = B.length;
        long[] pref = new long[n + 1];
        pref[0] = A[0];
        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] + A[i];   // Sum from the 0th to the i-th index 
        }
        long[] ans = new long[m];
        for (int i = 0; i < m; i++) {
            ans[i] = pref[B[i][1]] - (B[i][0] > 0 ? pref[B[i][0] - 1] : 0);
        }
        return ans;
    }
}
