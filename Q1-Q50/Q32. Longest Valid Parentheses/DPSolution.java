// DP Solution to Q32 "Longest Valid Parentheses"
// Reference: https://leetcode.com/problems/longest-valid-parentheses/description/
// Date: 08-28-2018
// Idea:
// 1. see "Dynamic Programming" of https://leetcode.com/problems/longest-valid-parentheses/solution/
// 2. https://leetcode.com/problems/longest-valid-parentheses/discuss/14133/My-DP-O(n)-solution-without-using-stack

/*
Given a string containing just the characters '(' and ')',
find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
*/

class DPSolution {
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty())   return 0;
        // DP: create a array of the same size as s
        // each index of the array will be used to record the longest valid
        // parentheses string ending at the same index of string s
        // NOTE: in java, the int array's default value is 0, no need to manually set
        int[] dp = new int[s.length()];
        int max = 0;
        // iterate through the string, apparently there won't be a valid par
        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            char prev = s.charAt(i-1);
            // if the ending character is '(', then apparently a valid parentheses cannot end there
            if (curr == '(') {
                continue;
            }
            else {
                // if the previous character is '('
                if (prev == '(') {
                    // be careful to not move out of bound
                    dp[i] = (i-2 >= 0) ? dp[i-2] + 2 : 2;

                }
                // if the previous character is ')'
                else {
                    // be careful to not move out of bound
                    if (i - dp[i-1] - 1 >= 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                        dp[i] = (i - dp[i-1] - 2 >= 0) ? dp[i - dp[i-1] - 2] + dp[i-1] + 2 : dp[i-1] + 2;
                    }
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }
}
