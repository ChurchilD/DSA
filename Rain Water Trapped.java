Q: Rain Water Trapped
  Imagine a histogram where the bars' heights are given by the array A. Each bar is of uniform width, which is 1 unit. When it rains, water will accumulate in the valleys between the bars.
  Your task is to calculate the total amount of water that can be trapped in these valleys.

  Solution :
  public class Solution {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int trap(final int[] A) {
        int n = A.length;
        if (n == 0) return 0;
        
        int[] left = new int[n];
        int[] right = new int[n];
        int storedWater = 0;
        
        // Fill left array
        left[0] = A[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], A[i]);
        }
        
        // Fill right array
        right[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], A[i]);
        }
        
        // Calculate trapped water
        for (int i = 0; i < n; i++) {
            int minA = Math.min(left[i], right[i]);
            storedWater += minA - A[i];
        }
        
        return storedWater;
    }
}
