// Solution to Q75 "Sort Colors"
// Reference: https://leetcode.com/problems/sort-colors/description/
// Date: 08-24-2018

class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;    // return nums as is
        }
        // NOTE: here we are using two pointers again
        // use left pointer to maintain the index to be exchanged with 0
        // in order to push 0s to the left
        // use right pointer to maintain the index to be exchanged with 2
        // in order to push 2s to the right
        int left = 0, right = nums.length - 1;
        for(int counter = 0; counter <= right;) {
            // in case of meeting a 0
            if (nums[counter] == 0) {
                // we should increment counter in this case
                swap(nums, left++, counter++);
            }
            // in case of meeting a 1
            else if (nums[counter] == 1) {
                counter++;
            }
            // in case of meeting a 2
            else {
                // NOTE: we shouldn't increment counter in this case
                // the current position needs to be rechecked
                // why? see README for this question
                swap(nums, right--, counter);
            }
        }

    }
    private void swap(int[] nums, int index1, int index2) {
        if (nums != null && nums.length > 0) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
}
