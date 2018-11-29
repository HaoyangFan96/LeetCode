// Java solution to Q44 "Wildcard Matching"
// Reference: https://leetcode.com/problems/wildcard-matching/description/
// Date: 11-29-2018

/**
 * Thoughts:
 *
 * 九章算法班思路：
 *
 * DP + Memoization is another way of implementing Dynamic Programming
 *
 * The overall idea is basically the same as original DP solution, but instead
 * use of DFS (recursion) plus memoization (similiar to 1D array in DP) to solve
 * this problem.
 *
 * NOTE: array cannot be used as the key for HashMap since its hashCode is basically
 * depending on the reference not its elments' values
 * https://stackoverflow.com/questions/15576009/how-to-make-hashmap-work-with-arrays-as-key
 *
 * 详见 九章算法班第六节课笔记下半部分
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
 * Initial DP + memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-29-2018
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        // Memoization (cache), the data stored into it is array of size 2
        // where the first element is the index we have been explored at s
        // and the second element is the index we have been explored at p
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        // DFS
        return dfs(s, 0, p, 0, memo, visited);
    }

    // dfs with memoization
    private boolean dfs(String s, int si, String p, int pi, boolean[][] memo, boolean[][] visited) {
        // case 1: all characters of s have been matched
        if (si == s.length())   {
            // in case p only have * left, then the two string can match
            for (int i = pi; i < p.length(); i++) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }

        // case 2: all characters of p have been matched
        // if all characters of s have been matched, then the two string can match
        if (pi == p.length())   return si == s.length();

        // check to see if this combination of si and pi has been explored before
        if (visited[si][pi])    return memo[si][pi];

        char sc = s.charAt(si), pc = p.charAt(pi);
        boolean matched = false;
        // case 3: current character of p is '?' or one of a-z
        if (pc != '*')  {
            if (checkMatchChars(sc, pc))    matched = dfs(s, si + 1, p, pi + 1, memo, visited);
            else    matched = false;
        }
        // case 4: current character of p is '*'
        else {
            // subcase 1: this * match 0 character
            matched |= dfs(s, si, p, pi + 1, memo, visited);
            // subcase 2: this * match 1 or more character. In that case, just
            // remove the current character of s and continue
            matched |= dfs(s, si + 1, p, pi, memo, visited);
        }

        // memoization: add the result for future's reference
        visited[si][pi] = true;
        memo[si][pi] = matched;
        return matched;
    }

    // helper function to check if two chars are the same or not
    private boolean checkMatchChars(char a, char b) {
        return a == b || b == '?';
    }
}
