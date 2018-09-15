// Java solution to Q44 "Wildcard Matching"
// Reference: https://leetcode.com/problems/wildcard-matching/description/
// Date: 09-15-2018
// Idea: https://www.youtube.com/watch?v=-8QnRMyHo_o

/**
 * Thoughts:
 * 用iterative + backtrack的方式来进行string的比较，通过几个变量来记录关键的信息：
 * 1）ss: 当前string s中当前的用于比较的character的index
 * 2）pp：当前string p中当前的用于比较的character的index
 * 3）star：最新的一个star的index
 * 4）match：与star对应，s中将要用于match的character的index, 从match开始，一直往后match下去，
 * 直至遇到无法match的情况，当时参考case 3 below，进行backtrack
 *
 * iterate through string s and p
 * 以下几种情况：
 * case 1：当遇到ss与pp对应的character相同或p.charAt(pp) == '?'的时候，ss++， pp++
 * case 2: 当遇到一个star的时候，更新star的index，前进到p的下一个character
 * case 3: 当ss与pp对应的character不相同的时候，backtrack到前一个star的位置，advance match
 * 来增加这个star对应的match的数量，重新开始比较
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
 * Initial version of the iterative backtracking solution
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        int ss = 0, pp = 0, star = -1, match = 0;
        // iterate through the string s
        while(ss < s.length()) {
            // case 1
            if (pp < p.length() && (s.charAt(ss) == p.charAt(pp) || p.charAt(pp) == '?')) {
                ss++;
                pp++;
            }
            // case 2
            else if (pp < p.length() && p.charAt(pp) == '*') {
                match = ss;
                star = pp;
                pp++;
            }
            // case 3: check if we do meet star before
            else if (star != -1) {
                match++;
                ss = match;
                pp = star + 1;
            }
            else {
                return false;
            }
        }
        while (pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }
        return (pp == p.length());
    }
}
