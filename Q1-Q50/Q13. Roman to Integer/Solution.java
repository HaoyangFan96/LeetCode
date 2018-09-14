// java solution to Q13 "Roman to Integer"
// reference: https://leetcode.com/problems/roman-to-integer/description/
// date: 09-08-2018

/*
Example 1:

Input: "III"
Output: 3
Example 2:

Input: "IV"
Output: 4
Example 3:

Input: "IX"
Output: 9
Example 4:

Input: "LVIII"
Output: 58
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/

class Solution {
    public int romanToInt(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            char next = (i < s.length() - 1) ? s.charAt(i+1) : '0';
            switch (curr) {
                case 'M':
                    res += 1000;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'C':
                    if (next == 'M') {
                        res += 900;
                        i++;
                    }
                    else if (next == 'D') {
                        res += 400;
                        i++;
                    }
                    else {
                        res += 100;
                    }
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'X':
                    if (next == 'L') {
                        res += 40;
                        i++;
                    }
                    else if (next == 'C') {
                        res += 90;
                        i++;
                    }
                    else {
                        res += 10;
                    }
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'I':
                    if (next == 'V') {
                        res += 4;
                        i++;
                    }
                    else if (next == 'X') {
                        res += 9;
                        i++;
                    }
                    else {
                        res += 1;
                    }
                    break;
                default:
                    break;
            }
        }
        return res;
    }
}
