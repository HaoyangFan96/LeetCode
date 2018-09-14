// Java solution to Q48 "Rotate Image"
// Reference: https://leetcode.com/problems/rotate-image/description/
// Date: 09-13-2018

/**
 * Thought:
 * 这题其实只要观察到了套路并不是很难：
 * 起始：
 * [
 *  [7,4,1],
 *  [8,5,2],
 *  [9,6,3]
 * ]
 * 先沿着从左上到右下的diagonal对折，变为
 * [
 * [7,8,9],
 * [4,5,6],
 * [1,2,3],
 * ]
 * 接下来，再沿着竖着的中线对折，即可变为我们想要的结果
 * [
 * [9,8,7],
 * [6,5,4],
 * [3,2,1]
 * ]
 * 可能的优化：只用一次nested for-loop做完所有swap
 */

/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input
2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-13-2018
 * NOTE: time O(n^2)
 *       space O(1)
 */
class Solution {
    public void rotate(int[][] matrix) {
        // step 1: swap along the main diagonal
        for (int row = 0; row < matrix.length; row++) {
            // 易错：将col起始为row+1而不是0
            for (int col = row + 1; col < matrix[0].length; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
        // step 2: swap along the middle vertical line
        for (int row = 0; row < matrix.length; row++) {
            // 把除以2写成向右位移更帅一些
            for (int col = 0; col < matrix[0].length >> 1; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][matrix[0].length-1-col];
                matrix[row][matrix[0].length-1-col] = temp;
            }
        }
    }
}
