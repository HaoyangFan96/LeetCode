// JS solution to Q44 "Wildcard Matching"
// Reference: https://leetcode.com/problems/wildcard-matching/description/
// Date: 09-15-2019

/**
 * Thoughts:
 * The idea is similar to "DPSolution.java" for this problem
 * This code is bascially a re-implementation in JS
 *
 * 基本思路：对于wildcard问题，判断一个string s 与 given pattern p 是否match时，可以
 * 用到 dynamic programming的思路来解决这个问题：
 * 当前的substring是否match完全取决于 1)当前的character是否match 2）之前一位character
 * 所对应的substring是否match
 * base case:
 * 1) 当两个substring同时为empty, it's a match, dp[0][0] = true
 * 2) 当s不为空，而p为空时，it's a not match, dp[i][0] = false for i > 0
 * 3) 当p不为空，而s为空时，we cannot determine it's a match or not
 * for example: if s == "" and p = "*", it's still a match
 * Time: O(mn), m = len(s), p = len(p)
 * Space: O(mn)
 */

/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-15-2018
 * Reimplementation of "DPSolution.java" in javascript
 */

 /**
  * @param {string} s
  * @param {string} p
  * @return {boolean}
  */
 var isMatch = function(s, p) {
     if (s == null || p == null) {
         return false;
     }
     let lens = s.length + 1, lenp = p.length + 1;
     // use Array(n) to create array with size n
     // use Array.fill(v) to fill it with values and make it iterable
     // NOTE: if the value passed to Array.fill is a object, it won't get copied
     // but only its reference get copied
     let dp = Array(lens).fill(null).map(c => Array(lenp).fill(false));
     // start the nested for-loop
     for (let i = 0; i < lens; i++) {
         for (let j = 0; j < lenp; j++) {
             if (i === 0 && j === 0) {
                 dp[i][j] = true;
                 continue;
             }
             if (j === 0) {
                 dp[i][j] = false;
                 continue;
             }
             // case: if current char of s and p are the same or if current char
             // of p is the '?'
             if (p[j-1] !== '*') {
                 if (i > 0 && (s[i-1] === p[j-1] || p[j-1] === '?')) {
                     dp[i][j] = dp[i-1][j-1];
                 }
             }
             // case when current char of p is '*'
             else {
                 // for case '*' match 0 character of s
                 dp[i][j] |= dp[i][j-1];
                 // for case '*' match 1 or n characters
                 if (i > 0) {
                     dp[i][j] |= dp[i-1][j];
                 }
                 // convert back to boolean
                 dp[i][j] = !!dp[i][j];
             }
         }
     }
     return dp[lens-1][lenp-1];
 };
