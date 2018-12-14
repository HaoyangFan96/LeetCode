// Java solution to Q50 "Pow(x,n)"
// Reference: https://leetcode.com/problems/powx-n/description/
// Date: 09-13-2018

/**
 * Thoughts:
 * 一个一个地乘，O(n) => Time Limit Exception, 如何加快计算？
 * 用一种类似于binary search的二分法, 不停地对power进行分割
 * 例如 计算 5^7
 * 5^7 = (5^3) * (5^3) * 5 = （5^1*5^1*5)*(5^1*5^1*5)*5
 * 详见: https://en.wikipedia.org/wiki/Exponentiation_by_squaring
 * NOTE: 需要注意的点，对于负power的处理：
 * 由于power的范围是从Integer.MIN_VALUE到Integer.MAX_VALUE，直接将Integer.MIN_VALUE
 * negate的话会overflow
 *
 * Time: O(logn)
 *
 * Space: O(logn), which is due to the use of recursion (non-tail recursion)
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
 * Review problem solution in Java.
 *
 * @author Haoyang Fan
 * @version 5.0
 * @since 12-13-2018
 */
class Solution {
    public double myPow(double x, int n) {
        // base case 1: the base is 0
        if (x == 0.0)   return 0.0;
        // in case the power is negative, we need to negate the power
        // NOTE: be careful with overflow when n == - 2^31 (Integer.MIN_VALUE)
        long ln = n;
        if (n < 0)  return helper(1/x, -ln);
        return helper(x, ln);
    }

    private double helper(double x, long n) {
        if (n == 0) return 1;
        if (n == 1) return x;   // add one more base case to reduce the stack depth
        double temp = helper(x, n/2);
        if ((n & 1) == 1)   {
            return temp * temp * x;
        } else {
            return temp * temp;
        }
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Review problem in Java.
 *
 * @author Haoyang Fan
 * @version 4.0
 * @since 11-25-2018
 */
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0
        if (x == 0.0)   return 0.0
        if (n < 0)  return helper(1.0/x, n);
        return helper(x, n);
    }

    private double helper(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double temp = helper(x, n/2);
        if ((n & 1) == 1)   {
            return x * temp * temp;
        } else {
            return temp * temp;
        }
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Review the recursive solution in java, implemented using fast power algorithm.
 * Reference: https://www.jiuzhang.com/tutorial/algorithm/332
 *
 * @author Haoyang Fan
 * @version 3.0
 * @since 11-20-2018
 */
class Solution {
    public double myPow(double x, int n) {
        if (n < 0)  {
            return 1 / helper(x, -n);
        } else {
            return helper(x, n);
        }
    }
    private double helper(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;

        // remember the result to avoid repetitive computation
        double temp = helper(x, n / 2);
        if ((n & 1) == 1) {
            return x * temp * temp;
        } else {
            return temp * temp;
        }
    }
}

/*----------------------------------------------------------------------------*/

/*
 * Time Limit Exception:
 * NOTE: this way doesn't work because you will repeat the calculation for the same
 * power twice!!! you should memorize the result!
 */
class RecursiveSolutionNotWork {
    public double myPow(double x, int n) {
        if (n > 0) {
            return fastPow(x, n);
        }
        else {
            return 1.0 / fastPow(x, n);
        }
    }
    private double fastPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // in case the current power is odd
        // 用 & 1 来检查奇偶比较帅：
        if ((n & 1) == 1) {
            return fastPow(x, n/2) * fastPow(x, n/2) * x;
        }
        // in case the current power is even
        else {
            return fastPow(x, n/2) * fastPow(x, n/2);
        }
    }
}

class RecursiveSolution {
    public double myPow(double x, int n) {
        if (n > 0) {
            return fastPow(x, n);
        }
        else {
            return 1.0 / fastPow(x, n);
        }
    }

    private double fastPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        // memorization
        double half = fastPow(x, n/2);
        if ((n & 1) == 1) {
            return half * half * x;
        }
        else {
            return half * half;
        }
    }
}
