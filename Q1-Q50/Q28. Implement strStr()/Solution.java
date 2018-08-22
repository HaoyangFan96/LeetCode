// Solution to Q28 "Implement strStr()"
// Reference: https://leetcode.com/problems/implement-strstr/description/
// Date: 08-21-2018

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()){
            return 0;
        }
        else if (haystack == null || haystack.isEmpty()) {
            return -1;
        }
        for (int i = 0;; i++) {
            if (i > haystack.length() - needle.length()) {
                return -1;
            }
            for (int j = 0;; j++) {
                if (j == needle.length()) {
                    return i;
                }
                else if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
        }
    }
}
