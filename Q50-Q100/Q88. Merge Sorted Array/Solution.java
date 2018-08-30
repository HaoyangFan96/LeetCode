// Solution to Q88 "Merge Sorted Array"
// Reference： https://leetcode.com/problems/merge-sorted-array/description/
// Date: 08-29-2018

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

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1 == null || nums2 == null || nums1.length < m + n) {
            return;
        }
        int counter = m + n - 1,  i1 = m - 1, i2 = n - 1;
        while(i1 >= 0 && i2 >= 0) {
            // do the comparsion of two values from nums1 and nums2
            if (nums1[i1] > nums2[i2]) {
                nums1[counter--] = nums1[i1--];
            }
            else {
                nums1[counter--] = nums2[i2--];
            }
        }
        // in case there are still some remainings in nums2
        while(i2 >= 0) {
            nums1[counter--] = nums2[i2--];
        }
        // NOTE: if i1 has some remainings, leave them as is and we don't need to handle them
    }
}
