// Java solution to Q128 "Longest Consecutive Sequence"
// Reference: https://leetcode.com/problems/longest-consecutive-sequence/description/
// Date 10-30-2018

/**
 * Thoughts:
 * Need a way to store the information of numbers in the array so that
 * when there we meet a new number v, we are able to know whether we have seen
 * v-1, v-2, v-3, ... and v+1, v+2, v+3, ...
 * Typically this is usually done by using a hashmap (hashset) or array.
 * In this problem, the array way is obviously not gonna to work, so need to
 * use a hashset for it
 *
 * algorithms:
 * 1. First add all elements in the array to the HashSet
 * 2. Now iterate through the array, for each element, remove consecutive numbers
 * to it that can be found in the HashSet. Keep track of the number of numbers
 * got removed so that we are able to know the length of consecutive sequence
 * 3. Use a variable to keep track of global maximum length and after the loop
 * ends, return that value
 */

 /*
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 Your algorithm should run in O(n) complexity.

 Example:

 Input: [100, 4, 200, 1, 3, 2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
  */

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 1;
        Set<Integer> set = new HashSet<>();
        // add all numbers into the set
        for (int num : nums) {
            set.add(num);
        }
        // re-iterate through the array
        for (int num : nums) {
            // in case all values have already been explored
            if (set.isEmpty()) {
                break;
            }
            // in case current value has already been explored
            if (!set.contains(num)) {
                continue;
            }
            set.remove(num);
            int length = 1;
            int left = num - 1, right = num + 1;
            // remove all the way
            while(set.remove(left--))   {
                length++;
            }
            while(set.remove(right++)) {
                length++;
            }
            max = Math.max(length, max);
        }
        return max;
    }
}
