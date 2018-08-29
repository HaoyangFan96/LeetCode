// Solution to Q31 "Next Permutation"
// Reference: https://leetcode.com/problems/next-permutation/description/
// Idea: 1. http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
// 2. https://leetcode.com/problems/next-permutation/solution/
// Date: 08-28-2018

// 基本思路，从右向左遍历
// 1. 找第一个nums[k-1] < nums[k]
// 2. 将nums[k-1]与nums[k]到结尾中比nums[k-1]大，但最为接近nums[k-1]的值互换
// 3. 将nums[k-1]到结尾的值reverse

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

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int tail = nums.length - 1;
        // starting from the end, looking for the first value such that
        // nums[tail-1] < nums[tail]
        while(tail > 0) {
            if (nums[tail - 1] < nums[tail]) {
                break;
            }
            tail--;
        }
        // if tail == 0, then there is no such found, and we can simply reverse
        // the array
        if (tail == 0) {
            for (int i = 0; i < nums.length / 2; i++) {
                int temp = nums[i];
                nums[i] = nums[nums.length - i - 1];
                nums[nums.length - i - 1] = temp;
            }
            return;
        }
        // find the index of value in the range of nums[tail] to the end,
        // which is larger than nums[tail-1] but is the most closes value to it
        int v = Integer.MAX_VALUE, idx = 0;
        for(int i = tail; i < nums.length; i++) {
            if (nums[i] > nums[tail - 1] && nums[i] < v) {
                v = nums[i];
                idx = i;
            }
        }
        // swap the value
        int temp = nums[tail - 1];
        nums[tail - 1] = v;
        nums[idx] = temp;
        // reverse the array from tail to the end
        for(int i = tail; i < tail + (nums.length - tail) / 2; i++){
            temp = nums[i];
            nums[i] = nums[nums.length - (i - tail) - 1];
            nums[nums.length - (i - tail) - 1] = temp;
        }
    }
}
