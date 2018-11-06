// Java solution to Q674 "Longest Continuous Increasing Subsequence"
// Reference: https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/
// Date: 11-5-2018

/**
 * Thoughts:
 * 感觉就是用同向双指针，左缩右补，套模板，打擂台。。。
 */

/*
Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
Note: Length of the array will not exceed 10,000.
 */

/**
 * More concise solution in Java
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 11-5-2018
 */
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 1, len = 1;
        // starting from the second index of array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                ans = Math.max(ans, ++len);
            } else {
                len = 1;
            }
        }
        return ans;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial two pointer solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-5-2018
 */
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int currMax = 0;
        // initialize the right poiner, which will always monotonously incrase
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // keep moving j to the right until we encounter a non-increasing value
            while (j < nums.length && (j == i || nums[j] > nums[j-1])) {
                j++;
            }
            currMax = Math.max(currMax, j - i);
            i = j - 1;
        }
        return currMax;
    }
}
