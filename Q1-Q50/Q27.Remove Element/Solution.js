// Solution to Q27 "Remove Element"
// Reference: https://leetcode.com/problems/remove-element/description/
// Date: 08-21-2018

// NOTE: the idea of solution of this question is very similar to the solution of Q26
// Make sure that you also study it while studying this one

/**
 * @param {number[]} nums
 * @param {number} val
 * @return {number}
 */
var removeElement = function(nums, val) {
    if (!nums || !nums.length) {
        return 0;
    }
    else if (val == null) {
        return nums.length;
    }
    // variable to keep track of the ending index of the portion of array
    // that has been overwritten
    let length = 0;
    for (let v of nums) {
        if (v !== val) {
            nums[length++] = v;
        }
    }
    return length;
};
