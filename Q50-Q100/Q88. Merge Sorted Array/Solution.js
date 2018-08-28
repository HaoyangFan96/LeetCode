// Solution to Q88 "Merge Sorted Array"
// Reference： https://leetcode.com/problems/merge-sorted-array/description/
// Date: 08-28-2018

/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
Note:
The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n)
to hold additional elements from nums2.

Example:
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/

/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function(nums1, m, nums2, n) {
    if (!nums1 || !nums2 || nums1.length === 0 || nums2.length === 0
        || nums1.length < m + n) {
        return;
    }
    // NOTE: 由于0集中于nums1的后端，从前端开始iterate的于，取两个list的较小值的话，并不好达成目的
    // 所以，换一种思路：从后端开始iterate，取两个list的较大值
    let p1 = m - 1, p2 = n - 1;
    // counter to keep track of the current index on the nums1 to hold the maximum of two array
    // let curr = nums1.length - 1;
    let curr = m + n - 1;
    for (;curr >= 0; curr--){
        if (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] <= nums2[p2]) {
                nums1[curr] = nums2[p2--];
            }
            else {
                nums1[curr] = nums1[p1--]
            }
        }
        // it is unnecessary, if the first array has left,
        // we just keep them as is
        // else if (p1 >= 0) {
        //     nums1[curr--] = nums1[p1--];
        // }
        else if (p2 >= 0) {
            nums1[curr] = nums2[p2--];
        }
    }
};
