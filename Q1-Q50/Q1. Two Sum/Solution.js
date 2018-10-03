// JS solution to Q1 "Two Sum"
// Reference: https://leetcode.com/problems/two-sum/description/
// Date: 09-08-2018

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    if (nums == null) {
        throw Error('No valid solution');
    }
    // Key idea: use a map (in js, it's object or Map) to store the previous number
    // that has been read during the iteration
    let m = {};
    // iterate through the nums
    for(let i = 0; i < nums.length; i++) {
        let need = target - nums[i];
        // check the map to see if there is such value in the array
        if (m.hasOwnProperty(need)) {
            return [m[need], i];
        }
        // store the information
        m[nums[i]] = i;
    }
    throw Error('No valid solution');
};
