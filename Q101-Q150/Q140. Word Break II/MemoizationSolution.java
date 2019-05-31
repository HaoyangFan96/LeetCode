// Java solution to Q140 "Word Break II"
// Reference: https://leetcode.com/problems/word-break-ii/description/
// Date: 02-01-2019

/**
 * Thoughts:
 *
 * This problem is the follow-up problem of Q139 "Word Break", the idea is basically
 * the same: we can still apply either recursion + memoization or bottom-up DP
 * to solve this problem. The only problem is that since this problem is asking
 * for us to output all possible sentences, when we store the information of
 * current substring, we won't again store just true or false, instead, we'll be
 * storing a list of "subsentences" that can be made from the current substring
 *
 * Time: O(2^n) see: https://leetcode.com/problems/word-break-ii/solution/ 评论区的讨论
 *
 * Space: O(2^n) see: https://leetcode.com/problems/word-break-ii/solution/ 评论区的讨论
 *
 */

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty
words, add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

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
 * Review DFS + Memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 4.0
 * @since 05-31-2019
 */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
         int len = s.length();

         // convert list of words into set for faster lookup speed
         Set<String> dict = new HashSet<>(wordDict);

         // initialize memoization in order to save overhead from repeated calculation
         // on the same part of substring multiple times later in DFS
         List<String>[] memo = new ArrayList[len];

         return dfs(s, 0, dict, memo);
    }

    private List<String> dfs(String s, int index, Set<String> dict, List<String>[] memo) {
        // check to see if result is already calculated and cached in memo
        if (memo[index] != null) return memo[index];

        int len = s.length();
        List<String> rst = new ArrayList<>();
        for (int i = index; i < len; ++i) {
            // get current substring
            String subStr = s.substring(index, i + 1);
            // check to see if it is a word from input
            if (!dict.contains(subStr)) continue;

            // check to see if we have reached to the end of string
            if (i == len - 1) {
                rst.add(subStr);
            } else {
                List<String> restSentences = dfs(s, i + 1, dict, memo);
                for (String restSentence : restSentences) {
                    rst.add(subStr + " " + restSentence);
                }
            }
        }

        // add result to the memo
        memo[index] = rst;
        return rst;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial bottom-up / tabulation solution in Java.
 * NOTE: this solution won't word, and get MLE. I am not sure what's the cause of
 * this issue even if this solution is following the idea of third approach in the official solution.
 *
 * @author Haoyang Fan
 * @version 3.0
 * @since 02-12-2019
 */
class Solution {
     public List<String> wordBreak(String s, List<String> wordDict) {
         int n = s.length();
         List<String>[] dp = new ArrayList[n + 1];
         // add base case:
         List<String> init = new ArrayList<>();
         init.add("");
         dp[0] = init;

         Set<String> words = new HashSet<>(wordDict);

         // tabulation
         for (int i = 1; i <= n; i++) {
             List<String> rst = new ArrayList<>();
             // try all previous possible words
             for (int j = 0; j < i; j++) {
                 String currWord = s.substring(j, i);
                 if (!words.contains(currWord)) continue;
                 List<String> subSentences = dp[j];
                 for (String subSentence : subSentences) {
                     if (subSentence.isEmpty()) rst.add(currWord);
                     else rst.add(subSentence + " " + currWord);
                 }
             }
             // add to dp array
             dp[i] = rst;
         }

         return dp[n];
     }
}

/******************************************************************************/

/**
 * Review top-down + memoization solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 02-12-2019
 */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // key: the startIdx of current substring in the original string
        // value: all the sub-sentences that can be made from current substring
        Map<Integer, List<String>> memo = new HashMap<>();

        // add the base case where the current substring is a empty string
        List<String> base = new ArrayList<>();
        base.add("");
        memo.put(s.length(), base);

        // convert list to set for a faster lookup speed
        return dfs(s, 0, new HashSet<>(wordDict), memo);
    }

    private List<String> dfs(String s, int startIdx, Set<String> words, Map<Integer, List<String>> memo) {
        if (memo.containsKey(startIdx)) return memo.get(startIdx);

        List<String> rst = new ArrayList<>();
        int n = s.length();
        for (int i = startIdx + 1; i <= n; i++) {
            String currWord = s.substring(startIdx, i);
            // if current substring is not a valid word, skip it
            if (!words.contains(currWord)) continue;
            List<String> subsentences = dfs(s, i, words, memo);
            // add current word to the subsentence
            for (String subsentence : subsentences) {
                if (subsentence.isEmpty()) rst.add(currWord);
                else rst.add(currWord + " " + subsentence);
            }
        }
        // add the result to the memoization
        memo.put(startIdx, rst);
        return rst;
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
    public List<String> wordBreak(String s, List<String> wordDict) {
        // this time, the memo will be different:
        // key is still the start index of substring we are exploring
        // but value becomes the list of "sub-sentences" that can be made based
        // on the current substring
        Map<Integer, List<String>> memo = new HashMap<>();

        // add the base case to memo in advance
        List<String> l = new ArrayList<>();
        l.add("");
        memo.put(s.length(), l);

        // conver the list of strings into set for faster look-up by hashing
        return helper(s, 0, new HashSet<String>(wordDict), memo);
    }

    private List<String> helper(String s, int startIdx, Set<String> wordSet, Map<Integer, List<String>> memo) {
        if (memo.containsKey(startIdx)) return memo.get(startIdx);
        List<String> rst = new ArrayList<>();

        // iterate over all indices after current starting index
        for (int i = startIdx + 1; i <= s.length(); i++) {
            String curr = s.substring(startIdx, i);
            // check if the substring is in the dictionary
            if (wordSet.contains(curr)) {
                List<String> subSentences = helper(s, i, wordSet, memo);
                for (String subSentence : subSentences) {
                    rst.add(curr + (subSentence.isEmpty() ? "" : (" " + subSentence)));
                }
            }
        }
        // add result subSentences to the memo
        memo.put(startIdx, rst);
        return rst;
    }
}
