// Soluton to Q76 "Minimum Window Substring"
// Reference: https://leetcode.com/problems/minimum-window-substring/description/
// NOTE: this is a typical sliding window problem, refer to the note of it
// when review for this question
// Date: 08-22-2018

// Idea from: https://medium.com/leetcode-patterns/leetcode-pattern-2-sliding-windows-for-strings-e19af105316b
// https://blog.csdn.net/linhuanmars/article/details/20343903
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }
        // step 1: create a hash table (map) to store the condition that will be tested
        HashMap<Character, Integer> expected = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            expected.put(c, expected.getOrDefault(c, 0) + 1);
        }
        // keep track of the number of characters that are required to be included
        // but haven't been satisfied so far
        int unsatisfied = expected.size();
        // hash table that later will be used to record stats for current window
        HashMap<Character, Integer> actual = new HashMap<Character, Integer>();
        // NOTE: 典型的two pointer情景，此题核心思路：

        // 当目前的window满足条件的情况下，左端pointer可以尝试向右移动来试图减少
        // window的size

        // 当目前的window并未满足条件的情况下，右端pointer可以尝试向右移动，扩大window
        // 的size，以试图满足条件
        int left = 0, right = 0;
        // we only keep track of the smallest substring founded so far
        int start = 0, end = s.length();
        for (; right < s.length(); right++) {
            Character curr = new Character(s.charAt(right));
            // 检查当前字母是否依然被需要
            if (expected.containsKey(curr)) {
                int count = expected.get(curr);
                if (--count == 0) {
                    // 对当前字母的需求已被满足
                    unsatisfied --;
                }
                expected.replace(curr, count);
            }
            // 检查当前window是否满足要求，如果满足的话，可以尝试减少window size，通过移动左端的pointer
            while(unsatisfied == 0) {
                if ((right - left) < (end - start)) {
                    start = left;
                    end = right;
                }
                // 移动左端pointer
                if (left < right) {
                    Character exclude = new Character(s.charAt(left++));
                    if (expected.containsKey(exclude)) {
                        int count2 = expected.get(exclude);
                        // 如果刨除这个字母后，当前的slide window又开始不满足条件了
                        if (++count2 > 0) {
                            unsatisfied ++;
                        }
                        expected.put(exclude, count2);
                    }
                }
                else {
                    break;
                }
            }
        }
        return (end == s.length()) ? "" : s.substring(start, end + 1);
    }
}
