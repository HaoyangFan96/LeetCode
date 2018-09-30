// Java solution to Q15 "3Sum"
// Reference: https://leetcode.com/problems/3sum/description/
// Date: 09-14-2018

/**
 * Thoughts:
 * 参见第一题 “2 Sum”的思路，我们可以将3 Sum转化为 1 value => find its negation of 2 sums
 * 基本思路为：
 * 1) sort the array in O(logn) time
 * 2) iterate through the array, for each of first value v of possible duplicates
 *    i.e. if there are multiple 1s and 2s, we only care about the left most 1s and 2s
 *    we use two pointers to find out if the substring right to the current value
 *    contain possible two values that sum to -v
 * 3) the way for us to figure out the sum is to narrow down the range of two pointers
 *    1. if the sum of three values is equal to 0, we have found a combination
 *    add it to the result list.
 *    NOTE: it is not done. There are still other possibilites. So we need to
 *    continue search between the interval of two pointers by shrinking it
 *    move both left pointer rightward and right pointer leftward, but remember
 *    to ignore the duplicates and stop at the first non-duplicate
 *    2. if the sum of three values is less than 0, we need to move the left
 *    pointer rightward to increase the sum. Remember to ignore the duplicates
 *    and stop at the first non-duplicate
 *    3. if the sum of three values is larger than 0, we need to move the right
 *    pointer leftward to decrease the sum. Remember to ignore the duplicates
 *    and stop at the first non-duplicate
 */

/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
 [-1, 0, 1],
 [-1, -1, 2]
]
*/

/**
 * @author Haoyang Fan
 * @version 2.0
 * @since 09-29-2018
 * Review solution in java
 */
 class Solution {
     public List<List<Integer>> threeSum(int[] nums) {
         List<List<Integer>> res = new ArrayList<>();
         if (nums == null || nums.length == 0) {
             return res;
         }
         // sort the array
         Arrays.sort(nums);
         // iterate through the array, each turn:
         // initialize the left pointer on the element next to the current element
         // initialize the right pointer on the element at the end
         for (int i = 0; i < nums.length -2; i++) {
             // ignore repeating elements, only focus on the first element of
             // those duplicates
             if (i == 0 || nums[i] != nums[i-1]) {
                 int left = i+1, right = nums.length - 1;
                 while (left < right) {
                     int lv = nums[left], rv = nums[right];
                     int sum = nums[i] + lv + rv;
                     if (sum == 0) {
                         res.add(Arrays.asList(nums[i], lv, rv));
                         while(left < right && nums[left] == lv)    left++;
                         while(left < right && nums[right] == rv)   right--;
                     }
                     else if (sum > 0) {
                         // try to move the right pointer a little bit left, ignoring the duplicate
                         while(left < right && nums[right] == rv)   right--;
                     }
                     else {
                         // try to move the left pointer a little bit right, ignoring the duplicate
                         while(left < right && nums[left] == lv)    left++;
                     }
                 }
             }
         }
         return res;
     }
 }

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-14-2018
 * My initial solution to Q15
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // sort the array
        Arrays.sort(nums);
        // iterate through the array
        for (int i = 0; i < nums.length - 2; i++) {
            // only focus on the first of duplicate values, ignore the rest
            if (i > 0 && nums[i-1] == nums[i]) {
                continue;
            }
            int curr = nums[i];
            // calculate the negation
            int goal = 0 - curr;
            // NOTE: initialize two pointers for the substring right to the current value
            int left = i + 1, right = nums.length - 1;
            // gradually narrow down the range
            while (left < right) {
                // case 1: nums[left] + nums[right] == goal
                if (nums[left] + nums[right] == goal) {
                    List<Integer> l = new ArrayList<>(3);
                    l.add(curr);
                    l.add(nums[left]);
                    l.add(nums[right]);
                    res.add(l);
                    // narrow down to the right
                    while (left < right && nums[left+1] == nums[left++]) {}
                    while (left < right && nums[right-1] == nums[right--]) {}
                }
                else if (nums[left] + nums[right] < goal) {
                    while (left < right && nums[left+1] == nums[left++]) {}
                }
                else {
                    while (left < right && nums[right-1] == nums[right--]) {}
                }
            }
        }
        return res;
    }
}
