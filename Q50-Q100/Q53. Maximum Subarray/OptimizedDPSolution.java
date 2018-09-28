// Java solution to Q53 "Maximum Subarray"
// Reference: https://leetcode.com/problems/maximum-subarray/description/
// Date: 09-27-2018

/**
 * Thoughts:
 * Idea from: https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
 * Optimization of the dp solution idea above:
 * 1) https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
 * 2) https://www.youtube.com/watch?v=86CQq3pKSUw
 * Time: O(n)
 * Space: O(1), 使用滚动数组来压缩DP的空间
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

class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        // variable which keeps track of the sum of current maximum subarray which
        // ends at index i, which mean that nums[i] must be included in that subarray
        // variable which keeps track of the global maximum subarray sum
        int res = nums[0];
        int currMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currMax = nums[i] + (currMax > 0 ? currMax : 0);
            res = Math.max(res, currMax);
        }
        return res;
    }
}
