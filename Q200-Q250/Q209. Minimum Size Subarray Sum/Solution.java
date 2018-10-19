// Java solution to Q209 "Minimum Size Subarray Sum"
// Reference: https://leetcode.com/problems/minimum-size-subarray-sum/description/
// Date: 10-19-2018

/**
 * Thoughts:
 * Maintain a sliding window that covers the current subarray by using two pointers
 * that move in the same direction
 * The idea is actually from 九章算法强化班的第一节课给的同向双指针的模版
 * pseudo code in python syntax:
 * j = 0   <-- 右边的指针，代表当前subarray右边的第一个数，本身并不包含在subarray中
 * 左边的指针循环每个subarray的起始位置, 从0到n-1，
 * for i in range(n):
 *      while j < n and 当前 subarray 不满足条件
 *          j += 1
 *          扩宽当前 subarray
 *
 *      if 当前subarray 满足条件：
 *          打擂台，看看是不是最优的
 *
 *      将 nums[i] 移除当前subarray
 */

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-19-2018
 * Initial java solution using "same direction two pointer" template
 */

 class Solution {
     public int minSubArrayLen(int s, int[] nums) {
         if (nums == null || nums.length == 0) {
             return 0;
         }
         int currMin = Integer.MAX_VALUE;
         // initialize the right pointer to be 0, monotonously increasing
         int j = 0;
         // initialize the variable to keep track of sum of current subarray
         int currSubarraySum = 0;
         // initialize the left pointer to be 0, iterate through the array from 0 to n-1
         for (int i = 0; i < nums.length; i++) {
             // in case the current subarray still doesn't satisfy the condition
             // advance the position of j
             while (j < nums.length && currSubarraySum < s) {
                 currSubarraySum += nums[j++];
             }
             // check to see if the condition has been met
             if (currSubarraySum >= s) {
                 // update the minimum length
                 currMin = Math.min((j - i), currMin);
             }
             // early termination
             // it turns out to be that there should be an early termination condition
             // if (j == nums.length) {
             //     break;
             // }
             // remove current element nums[i] from subarray, advance i
             currSubarraySum -= nums[i];
         }
         return (currMin == Integer.MAX_VALUE) ? 0 : currMin;
     }
 }
