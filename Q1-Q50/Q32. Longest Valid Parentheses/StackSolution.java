// Stack Solution to Q32 "Longest Valid Parentheses"
// Reference: https://leetcode.com/problems/longest-valid-parentheses/description/
// Date: 08-28-2018
// Idea:
// 1. see "Using Stack" of https://leetcode.com/problems/longest-valid-parentheses/solution/
// 2. https://leetcode.com/problems/longest-valid-parentheses/discuss/14167/Simple-JAVA-solution-O(n)-time-one-stack

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

class StackSolution {
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty())   return 0;
        // Use ArrayDeque to implement the stack
        // NOTE: specify the capacity in advance to avoid costly array resizing
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        int max = 0;
        // thinking like that: there is a "nonexisting )" that exists at index "-1"
        stack.push(-1);
        // iterate through the String
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            // in case current character is a "(", add its index to the stack
            if (curr == '(') {
                stack.push(i);
            }
            // in case current character is a ")", pop one element off the stack
            // if the stack is already empty, then add the index of this character
            // to the stack, which indicates no valid parentheses at i
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
