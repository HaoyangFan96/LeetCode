// Java solution to Q1 "Two Sum"
// Reference: https://leetcode.com/problems/two-sum/description/
// Date: 10-02-2018

/**
 * Thoughts:
 * Simple problem, iterate through the array
 * Use a hashMap to stats the values and their indices that have been scanned
 */

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-02-2018
 * HashMap solution in Java
 */

 class Solution {
     public int[] twoSum(int[] nums, int target) {
         int[] res = new int[2];
         Map<Integer, Integer> m = new HashMap<>();
         // iterate through the array
         for (int i = 0; i < nums.length; i++) {
             int complement = target - nums[i];
             if (m.containsKey(complement)) {
                 res[0] = m.get(complement);
                 res[1] = i;
                 break;
             }
             m.put(nums[i], i);
         }
         return res;
     }
 }
