// Java solution to Q10 "Regular Expression Matching"
// Reference: https://leetcode.com/problems/regular-expression-matching/description/
// Date: 11-29-2018

/**
 * Thoughts:
 *
 * The idea is similar to Q44 Wildcard Matching
 *
 * DP + Memoization is another way of implementing Dynamic Programming
 *
 * The overall idea is basically the same as original DP solution, but instead
 * use of DFS (recursion) plus memoization (similiar to 1D array in DP) to solve
 * this problem.
 *
 * NOTE: Regular Expression Matching: 算法班第六节课下半部分29:50, 重点理解如何处理*这个特殊字符
 *
 * NOTE: array cannot be used as the key for HashMap since its hashCode is basically
 * depending on the reference not its elments' values
 * https://stackoverflow.com/questions/15576009/how-to-make-hashmap-work-with-arrays-as-key
 *
 * NOTE: refer to version 2.0 because it has very detailed explanation and follows
 * the standard way offered by jiuzhang
 *
 */

/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

/**
 * Review DFS + backtracking solution in Java.
 *
 * @author Haoyang Fan
 * @version 3.0
 * @since 05-14-2019
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        // initialize memo that will be used later to save unnecessary repeated
        // calculation overhead
        boolean[][] visited = new boolean[sLen][pLen];
        boolean[][] memo = new boolean[sLen][pLen];

        return dfs(s, 0, p, 0, visited, memo);
    }

    private boolean dfs(String source, int si, String pattern, int pi, boolean[][] visited, boolean[][] memo) {
        int sLen = source.length(), pLen = pattern.length();

        // case 1: we have matched all characters in the pattern string
        if (pi == pLen) return si == sLen;

        // case 2: we have matched all characters in the source string
        if (si == sLen) return checkRemaining(pattern, pi);

        // case 3: we have already calculated such result and cached it in the memo
        if (visited[si][pi]) return memo[si][pi];

        boolean canMatch = false;

        // case 4: the current character is followed by a asterisk
        if (pi + 1 < pLen && pattern.charAt(pi + 1) == '*') {
            // 1) this asterisk match 0 character of source
            canMatch |= dfs(source, si, pattern, pi + 2, visited, memo);

            // 2) this asterisk tries to match 1 character of source
            if (!canMatch)
                canMatch |= checkCanMatch(source, si, pattern, pi) && dfs(source, si + 1, pattern, pi, visited, memo);
        }

        // case 5: the current character is not followed by a asterisk
        else {
            canMatch |= checkCanMatch(source, si, pattern, pi) && dfs(source, si + 1, pattern, pi + 1, visited, memo);
        }

        // add result to memo for future's reference
        visited[si][pi] = true;
        memo[si][pi] = canMatch;
        return canMatch;
    }

    private boolean checkRemaining(String pattern, int pi) {
        int len = pattern.length();

        // in case the remaining string of pattern is odd-length
        if (((len - pi) & 1) == 1) return false;

        for (int i = pi; i < len; i += 2) {
            if (pattern.charAt(i + 1) != '*') return false;
        }

        return true;
    }

    private boolean checkCanMatch(String source, int si, String pattern, int pi) {
        char s = source.charAt(si), p = pattern.charAt(pi);
        return s == p || p == '.';
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Review DFS + memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 05-13-2019
 */
class Solution {
    public boolean isMatch(String s, String p) {
         int sLen = s.length(), pLen = p.length();

         // create boolean 2D arrays that will used for memoization
         // which save overhead for doing repeated calculation
         boolean[][] visited = new boolean[sLen][pLen];
         boolean[][] memo = new boolean[sLen][pLen];

         // call DFS helper function
         return dfs(s, 0, p, 0, visited, memo);
    }

    private boolean dfs(String source, int si, String pattern, int pi, boolean[][] visited, boolean[][] memo) {
        int sLen = source.length(), pLen = pattern.length();
        // case 1: we've verified all characters of pattern
        // in order for them to match, then we must have also verified all characters
        // of source string
        if (pi == pLen) return si == sLen;

        // case 2: we've verified all characters of source
        // in order for them to match, then there must be even length of characters
        // left unverified in pattern, and each regular character has to be followed
        // by a asterisk
        if (si == sLen) return matchRemainingPattern(pattern, pi);

        // case 3: the result has already been calculated and cached in memo
        if (visited[si][pi]) return memo[si][pi];

        boolean canMatch = false;

        // case 4: there is a '*' following current character in pattern
        if (pi < pLen - 1 && pattern.charAt(pi + 1) == '*') {
            // sub case 1: the following '*' will repeat current character 0 times
            // then we skip current character and the following asterisk => pi -> pi + 2
            canMatch |= dfs(source, si, pattern, pi + 2, visited, memo);

            // sub case 2: current character of pattern[pi] match with current character
            // of source source[si], so the following asterisk repeat for 1 time
            if (!canMatch) // we don't need to do it if the previous case already match
                canMatch |= checkMatch(source, si, pattern, pi) && dfs(source, si + 1, pattern, pi, visited, memo);
        }

        // case 5: there is no '*' following current character in pattern
        else {
            canMatch = checkMatch(source, si, pattern, pi) && dfs(source, si + 1, pattern, pi + 1, visited, memo);
        }

        // add this result to memo to save future's overhead
        visited[si][pi] = true;
        memo[si][pi] = canMatch;
        return canMatch;
    }

    private boolean matchRemainingPattern(String pattern, int pi) {
        int pLen = pattern.length();
        // return false if there is odd number of characters remaining in pattern
        if (((pLen - pi) & 1) == 1) return false;

        // check to ensure that each regular character must be followed by a asterisk
        for (int i = pi; i < pLen; i += 2) {
            if (pattern.charAt(i + 1) != '*') return false;
        }
        return true;
    }

    private boolean checkMatch(String source, int si, String pattern, int pi) {
        char s = source.charAt(si), p = pattern.charAt(pi);
        return s == p || p == '.';
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial DFS + Memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-29-2018
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        // memoization
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];

        // dfs
        return dfs(s, 0, p, 0, memo, visited);
    }

    private boolean dfs(String s, int si, String p, int pi, boolean[][] memo, boolean[][] visited) {
        // case 1: all characters of s have been matched
        if (si == s.length()) {
            // then two string s and p can match only if each non * character (i.e. a-z, .)
            // is followd by a *
            // e.g. a*b*.* => match
            //      *   => not match
            //      ** => not match
            return checkValid(p, pi);
        }

        // case 2: all characters of p have been matched
        if (pi == p.length()) {
            // s and p will match if all characters of s have also been matched
            return si == s.length();
        }

        // check if this combination of si and pi has been explored before
        if (visited[si][pi])    return memo[si][pi];

        char sc = s.charAt(si), pc = p.charAt(pi);
        boolean matched = false;
        // case 3: when current character of p is not *
        if (pc != '*')  {
            matched |= compareChars(sc, pc) && dfs(s, si + 1, p, pi + 1, memo, visited);
            matched |= pi + 1 < p.length() && p.charAt(pi + 1) == '*' && dfs(s, si, p, pi + 1, memo, visited);
        }
        else { // case 4: when current character of p is *
            // subcase 1: this * match 0 sequence of previous character
            matched |= dfs(s, si, p, pi + 1, memo, visited);
            // subcase 2: this * match 1 or more sequence of prevous character
            matched |= pi - 1 >= 0 && compareChars(sc, p.charAt(pi - 1)) && dfs(s, si + 1, p, pi, memo, visited);
        }

        // memoization: record the result for future's reference
        visited[si][pi] = true;
        memo[si][pi] = matched;
        return matched;
    }

    private boolean checkValid(String p, int pi) {
        if (pi == p.length() - 1 && p.charAt(pi) == '*')    return true;
        for (int i = pi; i < p.length(); i += 2) {
            if (i + 1 >= p.length() || p.charAt(i) == '*' || p.charAt(i + 1) != '*')  return false;
        }
        return true;
    }

    private boolean compareChars(char s, char p) {
        return s == p || p == '.';
    }
}
