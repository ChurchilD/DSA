Q: Add One To Number
Given a non-negative number represented as an array of digits, add 1 to the number ( increment the number represented by the digits ).
The digits are stored such that the most significant digit is at the head of the list.
NOTE: Certain things are intentionally left unclear in this question which you should practice asking the interviewer. For example: for this problem, the following are some good questions to ask :
Q: Can the input have 0's before the most significant digit. Or, in other words, is 0 1 2 3 a valid input?
A: For the purpose of this question, YES
Q: Can the output have 0's before the most significant digit? Or, in other words, is 0 1 2 4 a valid output?
A: For the purpose of this question, NO. Even if the input has zeroes before the most significant digit.

Solution :
public class Solution {
    public int[] plusOne(int[] A) {
        int index= A.length - 1; 
        int result[] = new int[A.length];
        while (index >= 0 && A[index] == 9){ 
               A[index] = 0; 
                index -= 1; 
             } 
             if (index < 0){ 
                 A[0]=1;
                 result = A;
                 result[index]=0;
            } else {
                A[index]=A[index]+1;
            }
            if (index == 1 && A[0]==0){
                A[0]=A[0]+1;
                return A;
            }
            else if (index < 0){
            return A;
            } else {
                return result;
            }
            }
}
