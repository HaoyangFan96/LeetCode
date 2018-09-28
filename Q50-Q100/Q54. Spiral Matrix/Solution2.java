// Java solution to Q54 "Spiral Matrix"
// Reference: https://leetcode.com/problems/spiral-matrix/description/
// Date: 09-27-2018

/**
 * Thoughts:
 * Idea: https://leetcode.com/problems/spiral-matrix/solution/
 * 模拟题目描述的整个过程：
 * 从左上角出发，初始方向为从左到右，每当即将出界或者即将遇到已经经历过的element的时候，顺时针
 * 旋转90度，沿旋转后的方向继续前行，直到matrix中的所有element都被添加到list中为止
 * Time: O(n) since we basically scan each element once
 * Space: O(n) 1) we need another matrix in the same size to mark each element
 * as seen or not seen 2) we need a list to hold all elements
 */

 /*
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 Example 1:

 Input:
 [
  [ 1, 2, 3 ],
  [ 4, 5, 6 ],
  [ 7, 8, 9 ]
 ]
 Output: [1,2,3,6,9,8,7,4,5]
 Example 2:

 Input:
 [
   [1, 2, 3, 4],
   [5, 6, 7, 8],
   [9,10,11,12]
 ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */

 /**
  * @author Haoyang Fan
  * @version 1.0
  * @since 09-27-2018
  * Another solution (not I initially think of) in java
  */

  class Solution2{
     public List<Integer> spiralOrder(int[][] matrix) {
         List<Integer> res = new ArrayList<>();
         if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
             return res;
         }
         // create a matrix in the same size as the original to mark each element
         // in the original matrix as seen or not seen
         boolean[][] seen = new boolean[matrix.length][matrix[0].length];
         int total = matrix.length * matrix[0].length;
         // mimic the direction (initially left to right)
         int row = 0, col = 1;
         // mark the starting position to be the top left corner
         int currRow = 0, currCol = -1;
         while(res.size() < total) {
             int nextRow = currRow + row, nextCol = currCol + col;
             // check whether the next element is inside the box and not seen before
             // if not, simply change the direction
             if (nextRow < matrix.length && nextRow >= 0 && nextCol < matrix[0].length
                 && nextCol >= 0 && !seen[nextRow][nextCol]) {
                 res.add(matrix[nextRow][nextCol]);
                 // mark it as seen
                 seen[nextRow][nextCol] = true;
                 currRow = nextRow;
                 currCol = nextCol;
             }
             else {
                 // left to right ==> top to bottom
                 if (row == 0 && col == 1) {
                     row = 1;
                     col = 0;
                 }
                 // top to bottom ==> right to left
                 else if (row == 1 && col == 0) {
                     row = 0;
                     col = -1;
                 }
                 // right to left ==> bottom to top
                 else if (row == 0 && col == -1) {
                     row = -1;
                     col = 0;
                 }
                 // bottom to top ==> left to right
                 else {
                     row = 0;
                     col = 1;
                 }
             }
         }
         return res;
     }
 }
