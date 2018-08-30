// Solution to Q35 "Search Insert Position"
// Reference: https://leetcode.com/problems/search-insert-position/description/
// Date: 08-29-2018

/*

Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.

Example 1:
Input: [1,3,5,6], 5
Output: 2

Example 2:
Input: [1,3,5,6], 2
Output: 1

Example 3:
Input: [1,3,5,6], 7
Output: 4

Example 4:
Input: [1,3,5,6], 0
Output: 0
*/

/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var searchInsert = function(nums, target) {
    if (!nums || nums.length === 0) {
        return -1;
    }
    // left and right counter used in the binary search
    let low = 0, high = nums.length - 1;
    // NOTE: the way of writing binary search refer to  https://algs4.cs.princeton.edu/11model/BinarySearch.java.html
    while(low <= high) {
        let mid = low + (high - low) / 2;
        let curr = nums[mid];
        // case 1: found the target
        if (curr === target) {
            return mid;
        }
        // case 2: curr is less than target
        else if (curr < target) {
            low = mid + 1;
        }
        else {
            high = mid - 1;
        }
    }
    // why always low ? see the analyze: http://bangbingsyb.blogspot.com/2014/11/leetcode-search-insert-position.html
    return low;
};
