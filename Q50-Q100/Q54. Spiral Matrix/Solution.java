// Java solution to Q54 "Spiral Matrix"
// Reference: https://leetcode.com/problems/spiral-matrix/description/
// Date: 09-27-2018

/**
 * Thoughts:
 * 将整个螺旋形的过程看作如下的方式：
 * 最外圈顺时针360度走一圈(按上->右->下->左的顺序),将沿途所经的element加入到list
 * 之后逐步缩小范围，进行同样的顺时针操作，直到matrix中的所有element都被添加到list中为止
 * Time: O(n) since we basically scan each element once
 * Space: O(n) need a list to hold all elements
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
 * Initial solution in java
 */

 class Solution {
     public List<Integer> spiralOrder(int[][] matrix) {
         List<Integer> res = new ArrayList<>();
         if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
             return res;
         }
         // total number of elements in the matrix
         int total = matrix.length * matrix[0].length;
         // initialize four counters to keep tracck of the current top, bottom, left, right position
         int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
         while (res.size() < total) {
             // first step: top, go from left to right
             for(int i = left; i <= right; i++) {
                 res.add(matrix[top][i]);
             }
             top++;
             // second step: rightmost, go from top to bottom
             for (int i = top; i <= bottom; i++) {
                 res.add(matrix[i][right]);
             }
             right--;
             // avoid adding element that have already been travesed
             if (left > right || top > bottom) {
                 break;
             }
             // third step: bottom, go from right to left
             for (int i = right; i >= left; i--) {
                 res.add(matrix[bottom][i]);
             }
             bottom--;
             // fourth step: leftmost, go from bottom to top
             for (int i = bottom; i >= top; i--) {
                 res.add(matrix[i][left]);
             }
             left++;
         }
         return res;
     }
 }
