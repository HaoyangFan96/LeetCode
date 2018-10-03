// Java solution to Q16 "3Sum Closest"
// Reference: https://leetcode.com/problems/3sum-closest/description/
// Date: 09-29-2018

/**
 * Thoughts:
 *
 */

/*
Given an array nums of n integers and an integer target, find three integers in
nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

/**
 * @author Haoyang Fan
 * @version 2.0
 * @since 09-30-2018
 * Rewrite initial solution in java
 */

 class Solution {
     public int threeSumClosest(int[] nums, int target) {
         if (nums == null || nums.length == 0) {
             throw new IllegalArgumentException();
         }
         // sort the input array in-place
         Arrays.sort(nums);
         // record the difference between closest sum to the target
         int minDiff = Integer.MAX_VALUE;
         // typical way of iterating through array for 3 sum problem
         for (int i = 0; i < nums.length - 2; i++) {
             // only focus on the first element of duplicates, skip the rest
             if (i == 0 || nums[i] != nums[i-1]) {
                 // initialize two pointers
                 int left = i + 1, right = nums.length - 1;
                 while (left < right) {
                     int currSum = nums[i] + nums[left] + nums[right];
                     if (currSum == target) {
                         return target;
                     }
                     else if (currSum < target) {
                         if (target - currSum < Math.abs(minDiff)) {
                             minDiff = currSum - target;
                         }
                         // move the left pointer rightward, skip the duplicates
                         while(left < right && nums[left] == nums[++left]) {}
                     }
                     else {
                         if (currSum - target < Math.abs(minDiff)) {
                             minDiff = currSum - target;
                         }
                         // move the right pointer leftward, skip the duplicates
                         while(left < right && nums[right] == nums[--right]) {}
                     }
                 }
             }
         }
         return minDiff + target;
     }
 }

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-29-2018
 * Initial solution in java
 */

 class Solution {
     public int threeSumClosest(int[] nums, int target) {
         if (nums == null || nums.length == 0) {
             throw new IllegalArgumentException();
         }
         // sort the input array
         Arrays.sort(nums);
         boolean start = false;
         int currClosest = 0;
         for (int i = 0; i < nums.length - 2; i++) {
             if (i == 0 || nums[i] != nums[i-1]) {
                 int left = i+1, right = nums.length - 1;
                 int currSum = nums[i] + nums[left] + nums[right];
                 if (!start) {
                     start = true;
                     currClosest = currSum;
                 }
                 while (left < right) {
                     currSum = nums[i] + nums[left] + nums[right];
                     if (currSum == target) {
                         return currSum;
                     }
                     else if (currSum > target) {
                         if (Math.abs(currSum - target) < Math.abs(currClosest - target)) {
                             currClosest = currSum;
                         }
                         while(left < right && nums[right-1] == nums[right--]) {}
                     }
                     else {
                         if (Math.abs(currSum - target) < Math.abs(currClosest - target)) {
                             currClosest = currSum;
                         }
                         while(left < right && nums[left+1] == nums[left++]) {}
                     }
                 }
             }
         }
         return currClosest;
     }
 }
