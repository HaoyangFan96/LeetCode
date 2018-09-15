// DP solution to Q44 "Wildcard Matching"
// Reference: https://leetcode.com/problems/wildcard-matching/description/
// Date: 09-11-2018
// Idea1： https://www.tianmaying.com/tutorial/LC44
// Idea2: https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java

/**
 * Thoughts:
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
 * @since 09-11-2018
 * My initial DP solution, 未经滚动数组优化space
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int slen = s.length() + 1, plen = p.length() + 1;
        // double sequence for dp
        // NOTE: dp[i][j] record whether substring of s up to character i match with
        // substring of p up to charater j
        boolean[][] dp = new boolean[slen][plen];
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < plen; j++) {
                if (i == 0 && j == 0) {
                    // set the base case
                    dp[i][j] = true;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }
                // case where dp[i][j] <= dp[i-1][j-1] && ((s.charAt(i) == p.charAt(j))
                // || p.charAt(j) == '?')
                if (p.charAt(j - 1) != '*') {
                    if (i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
                // case where p.charAt(j) == '*'
                else {
                    // "*" match for 0 character
                    dp[i][j] |= dp[i][j-1];
                    // "*" match for 1 or n characters
                    if (i >= 1) {
                        dp[i][j] |= dp[i-1][j];
                    }
                }
            }
        }
        return dp[slen - 1][plen - 1];
    }
}
