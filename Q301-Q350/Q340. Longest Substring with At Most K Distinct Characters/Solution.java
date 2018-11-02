// Java solution to Q340 "Longest Substring with At Most K Distinct Characters"
// Reference: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
// Date: 10-23-2018

/**
 * Thoughts:
 * 1. maintain a sliding window by creating two pointers and ensure that in the window
 * it contains at most k distinct characters
 * 2. the right pointer keep moving towards right to expand the window, when it
 * comes to the condition that more than k distinct characters exist in the window,
 * move left pointer towards right to shrink the window, in order to decrease the
 * number of k distinct characters
 * 3. use HashMap / array to store information of distinct characters that currently
 * in the window and their counts
 */

/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-23-2018
 * Initial array solution in java
 */

 class Solution {
     public int lengthOfLongestSubstringKDistinct(String s, int k) {
         int n = s.length();
         // initialize a 128-length array to support ASCII charset
         int[] counts = new int[128];
         // initialize the right pointer, which will always monotonously increase
         int right = 0;
         // variable to track the maixmum length of substring that meets the condition
         int maxLen = 0;
         // variable to track the current number of distinct characters in the substring
         int numOfDistinct = 0;
         // initialize the left pointer, which iterate the string from 0 to n-1
         for (int left = 0; left < n; left++) {
             /**
              * there might be two cases for window to not own more than k distinct characters
              * 1) numOfDistinct is less than k, then it doesn't matter if current
              * character has seen or not
              * 2) numOfDistinct is equal to k, then the current character must
              * have been seen before, it cannot be a brand new character
              */
             while (right < n && (numOfDistinct < k ? true : counts[s.charAt(right)] > 0)) {
                 char curr = s.charAt(right++);
                 // check to see if we already have this character in the substring
                 if (counts[curr]++ == 0) {
                     numOfDistinct++;
                 }
             }
             maxLen = Math.max(maxLen, right - left);
             // remove the leftmost character in the substring
             if (--counts[s.charAt(left)] == 0) {
                 numOfDistinct--;
             }
         }
         return maxLen;
     }
 }
