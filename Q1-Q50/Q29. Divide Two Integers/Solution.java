// Solution to Q29 "Divide Two Integers"
// Reference: https://leetcode.com/problems/divide-two-integers/description/
// Idea from: 1. http://bangbingsyb.blogspot.com/2014/11/divide-two-integers-divide-two-integers.html
// 2. https://kingsfish.github.io/2017/10/11/Leetcode-29-Divide-Two-Integers/
// Date: 08-21-2018

/**
 * Example 34 / 3 :
 * 3 * 2^3 = 24 < 34 < 3 * 2^4 = 48
 * 1. result += 2^3 && 34 - 3 * 2^3 = 10
 * 2. 10 < 3 * 2^2, no action
 * 3. 10 > 3 * 2^1 => result += 2^1 && 10 - 3*2^1 = 4
 * 4. 4 > 3 * 2^0 => result += 2^0 && 4 - 3*2^0 = 1
 * since 2^0 has already been explored, the loop shall stop now
 *
 * Overflow:
 * 只有一个边界特例需要考虑Integer.MIN_VALUE和-1。此时的结果超过了Integer所能表示的最大范围，
 * 因此需要特殊处理。其次，为了简单起见，我们将除数和被除数的符号进行记录，然后将其转换为正数进行计算，
 * 这也涉及到溢出的问题，Integer.MIN_VALUE转换为正数之后会超过32位Integer所能表示的范围，
 * 因此在代码中使用long类型进行计算防止溢出
 */
class Solution {
    public int divide(int dividend, int divisor) {
        // if the divisor is 0, directly return Integer.MAX_VALUE
        if (divisor == 0){
            return Integer.MAX_VALUE;
        }
        // in case the dividend is 0
        else if (dividend == 0) {
            return 0;
        }
        // in case for the overflow: dividend == Integer.MIN_VALUE && divisor == -1
        else if (dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        // record the sign
        boolean positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        // NOTE: important, convert them to long first before passing to Math.abs
        // to avoid the overflow as Integer.MIN_VALUE when converting to positive value
        long lDivident = Math.abs((long)dividend);
        long lDivisor = Math.abs((long)divisor);
        // variable that keep track of the sum (result)
        int res = 0;
        while (lDivident >= lDivisor) {
            // record the number of digits of left shifting each time
            int shiftAmount = 0;
            while (lDivisor << shiftAmount <= lDivident) {
                shiftAmount++;
            }
            res += 1 << --shiftAmount;
            lDivident -= lDivisor << shiftAmount;
        }
        return positive ? res : -1 * res;
    }
}
