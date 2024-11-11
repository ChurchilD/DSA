Q: Flip.java
You are given a binary string A(i.e., with characters 0 and 1) consisting of characters A1, A2, ..., AN. In a single operation, you can choose two indices, L and R, 
 such that 1 ≤ L ≤ R ≤ N and flip the characters AL, AL+1, ..., AR. By flipping, we mean changing character 0 to 1 and vice-versa.
Your aim is to perform ATMOST one operation such that in the final string number of 1s is maximized.
If you don't want to perform the operation, return an empty array. Else, return an array consisting of two elements denoting L and R. If there are multiple solutions, 
return the lexicographically smallest pair of L and R.

NOTE: Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == c and b < d.
Input = '010'  Output : [1,1]

Approach :
Note the net change in the number of 1s in string S when we flip bits of string S.
Say it has A 0s and B 1s. Eventually, there are B 0s and A 1s.
So, the number of 1s increased by A - B. We want to choose a subarray that maximizes this. Note that if we change 1s to -1, the sum of values will give us A - B. 
Then, we have to find a subarray with the maximum sum, which can be done via Kadane’s Algorithm.

public class Solution {
    public ArrayList<Integer> flip(String A) {
        int l=0;
        int r = 0;
        int cSum =0; int mSum =0;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ArrayList<Integer> dummy = new ArrayList<Integer>();
        ans.add(l);
        ans.add(r);
        int n = A.length();
        for (int i =0;i<n;i++){
            char ch=A.charAt(i);
            if (ch =='1'){cSum-=1;}
            else {cSum+=1;}
            if (cSum > mSum){
                mSum=cSum;
                ans.set(0,l+1);
                ans.set(1,r+1);
            }
            if(cSum<0){
                cSum =0;
                l=i+1;
                r=i+1;
            }
            else{
                r+=1;
            }
        }
        if(mSum==0) {return dummy; }
        else {return ans;}
    }
}
