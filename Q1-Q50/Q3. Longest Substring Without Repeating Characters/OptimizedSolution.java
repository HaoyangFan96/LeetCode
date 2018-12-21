// Java solution to Q3 "Longest Substring Without Repeating Character"
// Reference: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
// Date: 10-19-2018

/**
 * Thoughts:
 * "Repeating characters" these words remind me to use data structures like
 * HashSet and HashMap to store some information of string that has been scanned
 * so far. To determine which part of string we should scan, we need to create
 * a sliding window by maintaining two pointers that move in the same direction.
 * Therefore, I can apply the template learned from 九章算法强化版 on this problem
 *
 * pseudo code in python syntax:
 * j = 0   <-- 右边的指针，代表当前subarray右边的第一个数，本身并不包含在subarray中
 * 左边的指针循环每个subarray的起始位置, 从0到n-1，
 * for i in range(n):
 *      while j < n and 当前 subarray 不满足条件
 *          j += 1
 *          扩宽当前 subarray
 *
 *      if 当前subarray 满足条件：
 *          打擂台，看看是不是最优的
 *
 *      将 nums[i] 移除当前subarray
 *
 * NOTE: one optimized idea is to instead of using HashSet to keep track of which
 * character we have seen before, we can used HashMap to also keep track of the
 * exact indices of characters that we have seen before. So whenever we met a
 * repeating character, we can immediately jump to the next character after it,
 * which greatly improve the efficiency
 *
 * NOTE: also remember that for this type of question, HashMap or HashSet can be
 * always replaced by array whose length is 128 (ASCII character set) or 256
 * (extended ASCII character set)
 */

/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */

/**
 * Review HashMap
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())   return 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 1;
        char[] chs = s.toCharArray();
        // initialize the right pointer, which will always monotonously increase
        int j = 0;
        // initialize the left pointer, which will iterate through the string in one pass
        for (int i = 0; i < chs.length; i++) {
            while (j < chs.length && !map.getOrDefault(s.charAt(j), -1) < i) {
                // add current character and its index in the string to the map
                // so that later we can easily track back to here
                map.put(chs[j], j);
                j++;
            }

            // 打擂台
            maxLen = Math.max(maxLen, j - i);
            // backtrack to the previous index of the repeating character
            if (j < chs.length) {
                i = map.get(chs[j]);
            }
        }
        return maxLen;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 2.0
 * @since 10-23-2018
 * HashMap Solution in Java
 */
 class Solution {
     public int lengthOfLongestSubstring(String s) {
         int n = s.length();
         // initialize a hash map to store the character scanned and their corresponding indices
         // in the string
         Map<Character, Integer> indices = new HashMap<>(n);
         // initialize the right pointer, which will always monotonously increase
         int right = 0;
         // initialize the variable that tracks global maixmum value of length
         int maxLen = 0;
         // initialize the left pointer
         for (int left = 0; left < n; left++) {
             // if there is no repeating charcter, keep moving the right pointer to the right
             while (j < chs.length && map.getOrDefault(s.charAt(j), -1) < i) {
                 // record the current character and its index
                 indices.put(s.charAt(right), right++);
             }
             // update the maximum length of nonrepeating character
             maxLen = Math.max(maxLen, right - left);
             // advance the left pointer to the position next to the repeating character
             if (right < n) {
                 left = indices.get(s.charAt(right));
             }
         }
         return maxLen;
     }
 }

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-19-2018
 * Initial solution in Java
 */

 class Solution {
     public int lengthOfLongestSubstring(String s) {
         if (s == null || s.isEmpty()) {
             return 0;
         }
         // variable to track the maximum length of string without repeating characters
         int currMax = 0;
         // initialize the right pointer, which always monotonously increase
         int j = 0;
         // initialize the length 128 array to keep track of which characters
         // that have been seen before and their indices in the array
         int[] map = new int[128];
         Arrays.fill(map, -1);
         int n = s.length();
         // initialize the right pointer, which iterates through the string from 0 to n-1
         for (int i = 0; i < n; i++) {
             // in case there is no repeating character, keep extending the subarray
             // until we finally got some repeating character
             while (j < n && map[s.charAt(j)] < i) {
                 map[s.charAt(j)] = j++;
             }
             // check to see if we find a longer subarray
             currMax = Math.max(currMax, j - i);
             if (j < n) {
                 // advance the left pointer to the index of last repeating character
                 i = Math.max(i, map[s.charAt(j)]);
             }
         }
         return currMax;
     }
 }
