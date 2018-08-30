// Solution to Q38 "Count and Say"
// Reference: https://leetcode.com/problems/count-and-say/description/
// Date: 08-29-2018

/*
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"
*/

/**
 * @param {number} n
 * @return {string}
 */
var countAndSay = function(n) {
    if (n <= 0) {
        return "";
    }
    let prevStr = "1", currStr = "1";
    for(let i = 2; i <= n; i++) {
        currStr = "";
        let count = 1;
        // iterate through the previous string
        for(let j = 0; j < prevStr.length; j++) {
            if (j + 1 < prevStr.length && prevStr[j] === prevStr[j+1]) {
                count++;
            }
            else {
                currStr += count;
                currStr += prevStr[j];
                // reset the count
                count = 1;
            }
        }
        prevStr = currStr;
    }
    return currStr;
};
