// JS solution to Q58 "Length of Last Word"
// Reference: https://leetcode.com/problems/length-of-last-word/description/
// Date: 09-29-2018

/**
 * Thoughts:
 * This is a simple problem that does not involve any algorithm, just search
 * from the end, keep track of the length of first word (which is actually the
 * last word since we are starting from the end)
 *
 * Some corner cases:
 * 1. " Hello" => 5
 * 2. "Hello " => 5
 * 3. " " => 0
 */

/*
Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
*/

/**
 * @author Haoyang Fan
 * @version 2.0
 * @since 09-29-2018
 * Improved version, searching from the back, so in some cases there is no need
 * to scan the entire string
 */

 /**
  * @param {string} s
  * @return {number}
  */
 var lengthOfLastWord = function(s) {
     // stripp the annoying whitespaces from both ends of string
     s = s.trim();
     // iterate from the end of string, stop until the first whitespace encounter
     let len = 0;
     for (let i = s.length - 1; i >= 0; i--) {
         if (s[i] === " ") {
             break;
         }
         else {
             len++;
         }
     }
     return len
 };


/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-29-2018
 * Initial solution in js
 */

 /**
  * @param {string} s
  * @return {number}
  */
 var lengthOfLastWord = function(s) {
     if (s == null || s.length === 0) {
         return 0;
     }
     let len = 0;
     let spaceBefore = false;
     // iterate through the string
     for (const ch of s) {
         if (ch === " ") {
             spaceBefore = true;
         }
         else if (spaceBefore) {
             len = 1;
             spaceBefore = false;
         }
         else {
             len++;
         }
     }
     return len;
 };
