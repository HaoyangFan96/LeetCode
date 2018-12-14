// Java solution to Q50 "Pow(x,n)"
// Reference: https://leetcode.com/problems/powx-n/description/
// Date: 09-13-2018

/**
 * Thoughts:
 *
 * 详见：https://leetcode.com/problems/powx-n/solution/ 中的iterative solution
 *
 * Time: O(logn)
 *
 * Space: O(1)
 */

/*
Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]
*/

/**
 * Review iterative solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 12-13-2018
 */
class Solution {
    public double myPow(double x, int n) {
        if (x == 0.0)   return 0.0;
        long ln = n;
        if (n < 0) {
            x = 1/x;
            ln = -ln;
        }
        // check each digit of bit representation of n (ln)
        double ans = 1.0, curr = x;
        while(ln != 0) {
            if ((ln & 1) == 1)  {   // in case current LSB is 1
                ans *= curr;
            }
            curr *= curr;
            ln /= 2;
        }
        return ans;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial iterative solution in java, implemented using fast power algorithm.
 * Reference: https://www.jiuzhang.com/tutorial/algorithm/332
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-20-2018
 */
 class Solution {
     public double myPow(double x, int n) {
         boolean negative = n < 0;
         double rst = 1.0;
         long pow = Math.abs((long) n);
         while (pow > 0) {
             if (pow % 2 == 1) rst *= x;
             x *= x;
             pow /= 2;
         }
         return negative ? 1.0 / rst : rst;
     }
 }
