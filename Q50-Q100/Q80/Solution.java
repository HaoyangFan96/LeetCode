// Solution to Q80 "Remove Duplicates from Sorted Array II"
// Reference: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
// Date: 08-24-2018

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null)   return 0;
        if (nums.length <= 2)   return nums.length;
        int count = 1;
        for(int i = 2; i < nums.length; i++) {
            if (nums[count] != nums[i] || nums[count - 1] != nums[i]) {
                nums[++count] = nums[i];
            }
        }
        return count + 1;
    }
}
