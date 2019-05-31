// Java solution to Q140 "Word Break II"
// Reference: https://leetcode.com/problems/word-break-ii/description/
// Date: 05-31-2019

/**
 * Thoughts:
 *
 * Originall I'm only using DFS + Backtracking to approach this problem (just use jiuzhang template on combination)
 * Although it can pass first 31 tests, it fails on later tests with extremely long input due to TLE.
 * Therefore, I add DP to this solution: for my dp array dp[i], it can tell me that whether
 * substring starts at i can form a valid string (can be broken into words in dictionary)
 *
 *
 * Lesson learned:
 * 1. Use DFS + Bactracking template to generate all possible solutions
 * 2. Use Dynamic Programming (Bottom-up) to pre-process the input string to mark
 * which substring is valid and which substring is invalid so that we can avoid
 * repeatedly trying those super long invalid substrings which will cost losts of time!
 *
 * Time: O(2^n) see: https://leetcode.com/problems/word-break-ii/solution/ 评论区的讨论
 *
 * Space: O(2^n) see: https://leetcode.com/problems/word-break-ii/solution/ 评论区的讨论
 *
 */

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

/**
 * Review DFS + Backtracking + DP solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 05-31-2019
 */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        Set<String> dict = new HashSet<>(wordDict);

        // dp[i]: indicate whether substring s[i .. len - 1] can be broken into words from wordDict
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;

        for (int i = len - 1; i >= 0; --i) {
            for (int j = i + 1; j <= len; ++j) {
                if (dp[j] && dict.contains(s.substring(i, j))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        List<String> rst = new ArrayList<>();
        dfs(s, 0, new StringBuilder(), rst, dict, dp);
        return rst;
    }

    private void dfs(String s, int startIndex, StringBuilder sb, List<String> rst, Set<String> dict, boolean[] dp) {
        int len = s.length();
        if (startIndex == len) {
            rst.add(sb.toString());
            return;
        }

        for (int i = startIndex; i < len; ++i) {
            String subStr = s.substring(startIndex, i + 1);
            // if current substring is not a valid word
            if (!dict.contains(subStr)) continue;
            // if the rest part of string cannot be broken into words from wordDict
            if (!dp[i + 1]) continue;

            int l = sb.length();
            if (startIndex != 0) sb.append(" ");
            sb.append(subStr);

            dfs(s, i + 1, sb, rst, dict, dp);

            // backtrack
            sb.setLength(l);
        }
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial DFS + Backtracking + DP solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 05-12-2019
 */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> rst = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordDict);

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;

        for (int i = len - 1; i >= 0; --i) {
            for (int j = i; j < len; ++j) {
                if (dict.contains(s.substring(i, j + 1)) && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        dfs(s, 0, new StringBuilder(), dict, dp, rst);
        return rst;
    }

    private void dfs(String s, int startIndex, StringBuilder sb, Set<String> dict, boolean[] dp, List<String> rst) {
        int len = s.length();
        if (startIndex == len) {
            rst.add(sb.toString());
            return;
        }

        for (int i = startIndex; i < len; ++i) {
            String sub = s.substring(startIndex, i + 1);
            if (!dict.contains(sub)) continue;

            if (!dp[i + 1]) continue;

            int l = sb.length();
            if (startIndex != 0) sb.append(" ");
            sb.append(sub);

            dfs(s, i + 1, sb, dict, dp, rst);

            sb.setLength(l); // backtrack
        }
    }
}
