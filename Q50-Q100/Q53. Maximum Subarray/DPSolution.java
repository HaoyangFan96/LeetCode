// Java solution to Q53 "Maximum Subarray"
// Reference: https://leetcode.com/problems/maximum-subarray/description/
// Date: 09-27-2018

/**
 * Thoughts:
 * Idea from: https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
 * Optimization of the dp solution idea above:
 * 1) https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
 * 2) https://www.youtube.com/watch?v=86CQq3pKSUw
 *
 */

/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-27-2018
 * Initial dp solution in java
 */

 class Solution {
     public int maxSubArray(int[] nums) {
         if (nums ==  null || nums.length == 0) {
             throw new IllegalArgumentException();
         }
         // one-dimension dp
         int[] dp = new int[nums.length];
         dp[0] = nums[0];
         int res = nums[0];
         // begin to iterate through the array
         for(int i = 1; i < nums.length; i++) {
             dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
             // update the global max
             res = Math.max(dp[i], res);
         }
         return res;
     }
 }
