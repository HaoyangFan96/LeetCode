// Solution to Q33 "Search in Rotated Sorted Array"
// Reference: https://leetcode.com/problems/search-in-rotated-sorted-array/description/
// Date: 08-28-2018

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        else if (nums.length == 1) {
            return (nums[0] == target) ? 0 : -1;
        }
        boolean rotated = (nums[0] < nums[nums.length - 1]) ? false : true;
        int left = 0, mid = 0, right = nums.length - 1;
        while(left < right) {
            mid = (right - left) / 2 + left;
            int curr = nums[mid];
            // check if we have found the index
            if (curr == target) {
                return mid;
            }
            else if (curr < target) {
                // case 1, the value at the end is less than the target
                if (nums[right] < target) {

                }
            }
        }
        return -1;
    }
}
