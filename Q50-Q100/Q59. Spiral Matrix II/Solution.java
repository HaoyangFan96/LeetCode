// Java solution to Q59 "Spiral Matrix II"
// Reference: https://leetcode.com/problems/spiral-matrix-ii/description/
// Date: 09-27-2018

/**
 * Thoughts:
 * the idea is basically the same as the ones I used in Q54 "Spiral Matrix"
 * Refer: https://leetcode.com/problems/spiral-matrix/solution/
 * The only difference is that now the whole process is reversed
 * Time: O(n), since I need to bascially iterate through each element in the
 * result array once
 * Space: O(n), since I need to create a result array to return
 */

/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-27-2018
 * Initial Solution in Java
 */

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        // total number of values that need to be initialized
        int total = n * n;
        int curr = 0;
        // using the idea similar to Solution.java in Q54 "Spiral Matrix"
        // initialize four counters to keep tracck of the current top, bottom, left, right position
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        while (curr < total) {
            // first step: top, go from left to right
            for (int i = left; i <= right; i++) {
                res[top][i] = ++curr;
            }
            top++;
            // second step: rightmost, go from top to bottom
            for (int i = top; i <= bottom; i++) {
                res[i][right] = ++curr;
            }
            right--;
            // avoid to repeat some elements
            if (top > bottom || left > right) {
                break;
            }
            // third step: bottom, go from right to left
            for (int i = right; i >= left; i--) {
                res[bottom][i] = ++curr;
            }
            bottom--;
            // fourth step: leftmost, go from bottom to top
            for (int i = bottom; i >= top; i--) {
                res[i][left] = ++curr;
            }
            left++;
        }
        return res;
    }
}
