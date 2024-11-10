Q: Sum of All Subarrays
You are given an integer array A of length N.
You have to find the sum of all subarray sums of A.
More formally, a subarray is defined as a contiguous part of an array which we can obtain by deleting zero or more elements from either end of the array.
A subarray sum denotes the sum of all the elements of that subarray.
Note : Be careful of integer overflow issues while calculations. Use appropriate datatypes.

Solution Approach :
For each element, if we know how many subarrays do they come in, 
we can easily calculate their contribution to the sum as (Number of Subarrays) * (A[i]).

How do we calculate the number of subarrays for each element?
Let us focus on the definition of a subarray. It is obtained by deleting zero or more elements from either end.
So, for each element, let X be the number of elements to their left, and Y be the number of elements to their right.
Number of required subarrays = (X + 1) * (Y + 1)

We can easily know X and Y from the index of the element.
Let the array be 0 - indexed and N be the length of the array, 
X = i, Y = N - i - 1

Refer to the complete solution for implementation details.

Time Complexity: O(N)
Space Complexity: O(1), no extra space needed.

Solution :
  public class Solution {
    public long subarraySum(int[] A) {
        long result =0;
        for(int i=0;i<A.length;i++){
            result+=((long)A[i]*(i+1)*(A.length-i));
        }
        return result;
    }
}
