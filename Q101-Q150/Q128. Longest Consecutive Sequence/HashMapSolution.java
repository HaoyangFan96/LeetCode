// Java solution to Q128 "Longest Consecutive Sequence"
// Reference: https://leetcode.com/problems/longest-consecutive-sequence/description/
// Date 10-30-2018

/**
 * Thoughts:
 * 遍历数组，利用HashMap存储每一个数和当前与其相邻的数能够组成的sequence的长度
 * 这样的话，每当接下来遇到一个新的与其相邻的数，更新sequence的长度
 */

 /*
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 Your algorithm should run in O(n) complexity.

 Example:

 Input: [100, 4, 200, 1, 3, 2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
  */

/**
 * HashMap solution in Java
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-30-2018
 */

class Info {
    protected int leftmost;
    protected int rightmost;
    protected int len;

    public Info (int l, int r, int s) {
        leftmost = l;
        rightmost = r;
        len = s;
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 1;
        Map<Integer, Info> map = new HashMap<>(nums.length);
        // iterate through the array
        for (int num : nums) {
            // check to see if we have encountered this value before
            if (map.containsKey(num)) {
                continue;
            }
            // find the last and next value of current value
            int last = num - 1, next = num + 1;
            // in case both are present
            if (map.containsKey(last) && map.containsKey(next)) {
                Info l = map.get(last);
                Info r = map.get(next);
                Info i = new Info (l.leftmost, r.rightmost, 1 + l.len + r.len);
                map.put(l.leftmost, i);
                map.put(r.rightmost, i);
                map.put(num, i);
                max = Math.max(max, 1 + l.len + r.len);
                continue;
            }
            // in case only the left one is present
            if (map.containsKey(last)) {
                Info l = map.get(last);
                Info i = new Info(l.leftmost, num, l.len + 1);
                map.put(l.leftmost, i);
                map.put(num, i);
                max = Math.max(max, l.len + 1);
                continue;
            }
            // in case only the right one is present
            if (map.containsKey(next)) {
                Info r = map.get(next);
                Info i = new Info(num, r.rightmost, r.len + 1);
                map.put(r.rightmost, i);
                map.put(num, i);
                max = Math.max(max, r.len + 1);
                continue;
            }
            // in case none of them is present
            map.put(num, new Info(num, num, 1));
        }
        return max;
    }
}
