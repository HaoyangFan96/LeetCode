// Solution to Q31 "Next Permutation"
// Reference: https://leetcode.com/problems/next-permutation/description/
// Idea: 1. http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
// 2. https://leetcode.com/problems/next-permutation/solution/
// Date: 08-28-2018

// 基本思路，从右向左遍历，找第一个nums[k-1] < nums[k]
// 接下来，再次从右向左遍历，将nums[k-1]与nums[k]到结尾中比nums[k-1]大，但最为接近nums[k-1]的值互换
// 最终将nums[k]到结尾的值reverse

/*
Pick One

Implement next permutation, which rearranges numbers into the lexicographically
next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible
order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

/**
 * Review solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 05-01-2019
 */
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        // k will be used to record the first position (wrt right) that is less
        // than (strictly) than its right element => nums[k] < nums[k + 1]
        // aka. all elements on it right form a decreasing sequence
        // nums[k + 1] >= nums[k + 2] .... >= nums[nums.length - 1]
        int k;
        for (k = nums.length - 2; k >= 0; k--) {
            if (nums[k] < nums[k + 1]) break;
        }

        // NOTE: do it only if k >= 0 (aka such element does exist in the array)
        // iterate from right to left again, find the smallest element that is
        // larger (strictly) than nums[k], exchange their position
        if (k >= 0) {
            int i;
            for (i = nums.length - 1; i > k; i--) {
                if (nums[i] > nums[k]) {
                    break;
                }
            }

            // exchange nums[j] and nums[k]
            swap(nums, i, k);
        }

        // reverse the part of array in the range [k + 1 ... end]
        int i = k + 1, j = nums.length - 1;
        while (j > i) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 08-28-2018
 */
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int counter = nums.length - 1;
        // First step: iterate nums from the end, find first index such that nums[i-1] < nums[i]
        while (counter > 0 && nums[counter-1] >= nums[counter]) {
            counter --;
        }
        // NOTE: if counter is 0, then there is no such match, directly reverse the array
        if (counter == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        // Second step:
        // find the index between [counter, nums.length - 1] that is larger than
        // nums[counter - 1] but is the most one closest to it
        // 在这里我们可以利用这样一个性质： 在[counter, nums.length-1]这个range中，
        // 从后往前的方向来看，values为单调递增
        int i = nums.length - 1;
        while(i >= counter && nums[i] <= nums[counter-1]){
            i--;
        }
        // Third step: do the swap
        swap(nums, counter - 1, i);
        // Final step: reverse the nums array of range [counter, nums.length - 1]
        reverse(nums, counter, nums.length - 1);
    }

    // helper function to reverse the nums array of range [start, end]
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // helper function to swap a pair of values in nums array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // this is my original improved version, which is too messy
    // public void nextPermutation(int[] nums) {
    //     if (nums == null || nums.length <= 1) {
    //         return;
    //     }
    //     int tail = nums.length - 1;
    //     // starting from the end, looking for the first value such that
    //     // nums[tail-1] < nums[tail]
    //     while(tail > 0) {
    //         if (nums[tail - 1] < nums[tail]) {
    //             break;
    //         }
    //         tail--;
    //     }
    //     // if tail == 0, then there is no such found, and we can simply reverse
    //     // the array
    //     if (tail == 0) {
    //         for (int i = 0; i < nums.length / 2; i++) {
    //             int temp = nums[i];
    //             nums[i] = nums[nums.length - i - 1];
    //             nums[nums.length - i - 1] = temp;
    //         }
    //         return;
    //     }
    //     // find the index of value in the range of nums[tail] to the end,
    //     // which is larger than nums[tail-1] but is the most closes value to it
    //     int v = Integer.MAX_VALUE, idx = 0;
    //     for(int i = tail; i < nums.length; i++) {
    //         if (nums[i] > nums[tail - 1] && nums[i] < v) {
    //             v = nums[i];
    //             idx = i;
    //         }
    //     }
    //     // swap the value
    //     int temp = nums[tail - 1];
    //     nums[tail - 1] = v;
    //     nums[idx] = temp;
    //     // reverse the array from tail to the end
    //     for(int i = tail; i < tail + (nums.length - tail) / 2; i++){
    //         temp = nums[i];
    //         nums[i] = nums[nums.length - (i - tail) - 1];
    //         nums[nums.length - (i - tail) - 1] = temp;
    //     }
    // }
}
