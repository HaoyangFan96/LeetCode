// java solution to Q3 "Longest Substring Without Repeating Characters"
// reference: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
// Date: 09-08-2018

// Solution: https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/

/*
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", which the length is 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/

/**
 * HashSet solution in Java, which will be less efficient compared with HashMap
 * solution in OptimizedSolution.java
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 12-20-2018
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())   return 0;
        Set<Character> set = new HashSet<>();
        char[] chs = s.toCharArray();
        int max = 1;
        int j = 0;
        for (int i = 0; i < chs.length; i++) {
            while (j < chs.length && !set.contains(chs[j])) {
                set.add(chs[j++]);
            }
            max = Math.max(max, j - i);
            set.remove(chs[i]);
        }
        return max;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial HashSet solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-08-2018
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        // In java, contains and remove for HashSet costs constant operation
        Set<Character> set = new HashSet<>();
        // two pointers
        int i = 0, j = 0;
        while(j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                max = Math.max(max, j - i + 1);
                // add current character to the set
                set.add(s.charAt(j++));
            }
            else{
                // else there are some duplicates characters in the current
                // substring, then try to advance the position of i
                // NOTE: in the meanwhile, don't forget to remove that char from set
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }
}
