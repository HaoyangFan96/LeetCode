// Java solution to Q139 "Word Break"
// Reference: https://leetcode.com/problems/word-break/description/
// Date: 02-01-2019

/**
 * Thoughts:
 *
 * Observing this problem: first of all, this problem is asking for judging feasibility
 * to reach the goal, which falls into one of the three categories of problems that
 * can be solved by using dynamic programming.
 *
 * To check whether we can indeed apply dynamic programming on this problem, we
 * first think of whether this problem has "optimal substructure", which means that
 * we can use optimal solutions of subproblems to construct the optimal solution of
 * initial problem:
 * Yes, it has, suppose the length of original string input is n
 * canBreak(s, 0, n) = (isWord(s, i) && canBreak (s, i, n)) || (isWord (s, j) &&
 * canBreak(s, j, n)) || ....
 * which means that to check if original string can be broken down into different
 * words, we can simply break down first word from it, and recursively call the
 * same routine to check if the rest of string can be broken down into different
 * words
 *
 * In addition, as this way of solving the problem involves a lot of repeat computation
 * on the same substrings, which we call "overlapping subproblem", we then can
 * assure that it is good idea for us to apply dynamic programming because it can
 * save a lot of unnecessary repeat computations on the same subproblems
 *
 * v1.0: top-down approach, recursion + memoization
 * v2.0: bottom-up approach, tabulation
 *
 * TODO: 学习另一种BFS的解法 和 DFS + Memoization的写法
 *
 * Time: O(n^2)
 *
 * Space: O(n) due to the use of memoization as well as the recursion depth
 *
 */

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

/**
 * Review bottom up dynamic programming solution in Java.
 *
 * @author Haoyang Fan
 * @version 5.0
 * @since 05-31-2019
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // convert the list of words into a set for faster look-up
        Set<String> dict = new HashSet<>(wordDict);

        int len = s.length();

        // initialize array that will be used for dynamic programming
        // dp[i]: indicate whether the substring s[0 .. i-1] can be broken into words from wordDict
        boolean[] dp = new boolean[len + 1];
        dp[0] = true; // in order to avoid index out of bound exception

        // bottom-up
        for (int i = 1; i <= len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Review bottom-up + tabulation solution in Java.
 *
 * @author Haoyang Fan
 * @version 4.0
 * @since 02-12-2019
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        // set the base case which is the empty string
        dp[n] = true;

        // convert wordDict into a hash set for faster lookup
        Set<String> words = new HashSet<>(wordDict);

        // tabulation
        for (int i = n - 1; i >= 0; i--) {
            boolean res = false;
            for (int j = i + 1; j <= n; j++) {
                // skip those invalid word
                if (!words.contains(s.substring(i, j))) continue;
                if (dp[j]) {
                    res = true;
                    break;
                }
            }
            dp[i] = res;
        }

        return dp[0];
    }
}

/******************************************************************************/

/**
 * Review top-down + memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 3.0
 * @since 02-12-2019
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length() + 1];
        // add the base case when we have explored the whole string
        memo[s.length()] = true;

        // dfs: top-down + memoization
        return dfs(s, 0, new HashSet<String>(wordDict), memo);
    }

    private boolean dfs(String s, int startIdx, Set<String> words, Boolean[] memo) {
        // check if we have explored current substring before
        if (memo[startIdx] != null) return memo[startIdx];

        for (int i = startIdx + 1; i <= s.length(); i++) {
            // get the current word
            String currWord = s.substring(startIdx, i);
            // in case this is not a valid word, skip it
            if (!words.contains(currWord)) continue;
            // check if the substring is also valid
            if (dfs(s, i, words, memo)) {
                memo[startIdx] = true;
                return true;
            }
        }

        memo[startIdx] = false;
        return false;
    }
}

/******************************************************************************/

/**
 * Initial bottom-up approach solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 02-01-2019
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // create the dp array to hold the result of whether current substring can be broken
        // into different words that belong to the wordDict
        boolean[] dp = new boolean[s.length() + 1];

        // define the base case where current substring is empty
        dp[0] = true;

        // convert the list of string to the set of string for faster lookup speed
        Set<String> wordSet = new HashSet<>(wordDict);

        // start tabulation
        for (int i = 1; i < dp.length; i++) {
            // iterate through all previous indices before i
            for (int j = 0; j < i, j++) {
                // check if previous string can be broken into words and the
                // substring between previous substring and current index is also valid
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }
}

/******************************************************************************/

/**
 * Initial recursion + memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 02-01-2019
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // create the memoization array to save results and avoid repeat computations
        Boolean[] canBreak = new Boolean[s.length()];

        // convert the list into set for faster lookup
        Set<String> wordSet = new HashSet<>(wordDict);

        // recursion with memoization
        return helper(s, 0, wordSet, canBreak);
    }

    private boolean helper(String s, int startIdx, Set<String> wordSet, Boolean[] canBreak) {
        // in case we've reached the end of string, return true
        if (startIdx == s.length()) return true;
        // else if we've already computed on current substring before
        if (canBreak[startIdx] != null) return canBreak[startIdx]; // auto-unboxing

        boolean res = false;
        // iterate through all indices after startIdx, explore every possible word
        for (int i = startIdx + 1; i <= s.length(); i++) {
            String currWord = s.substring(startIdx, i);
            // skip if this is not a word in the input dictionary
            if (!wordSet.contains(currWord)) continue;
            // recursively call upon the rest of string
            if (helper(s, i, wordSet, canBreak)) {
                res = true;
                break;
            }
        }
        // add result to memo and return the result
        canBreak[startIdx] = res;
        return res;
    }
}
