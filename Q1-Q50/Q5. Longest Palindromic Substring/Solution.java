// Solution to Q25 "Longest Palindromic Substring"
// Reference: https://leetcode.com/problems/longest-palindromic-substring/description/
// Idea from: https://leetcode.com/problems/longest-palindromic-substring/solution/
// Date: 08-21-2018

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        // start and end index of longest substring
        int start = 0, end = 0;
        // iterate through all possible indices (see the definition of possible
        // "indices" in https://leetcode.com/problems/longest-palindromic-substring/solution/)
        for (int i = 0; i < s.length() - 1; i++) {
            // check for even-length palindrome
            int a = palindromeLengthCenterHere(s, i, i+1);
            // check for odd-length palindrome
            int b = palindromeLengthCenterHere(s, i, i);
            int max = (a >= b) ? a : b;
            // check if the new palindrome is longer than current max
            if (max > end - start + 1) {
                if (max == a) {
                    end = i + max / 2;
                    start = i - max / 2 + 1;
                }
                else {
                    end = i + (max - 1) / 2;
                    start = i - (max - 1) / 2;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    private int palindromeLengthCenterHere(String s, int l, int r) {
        int left = l, right = r;
        // expand the check of palindrome
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
