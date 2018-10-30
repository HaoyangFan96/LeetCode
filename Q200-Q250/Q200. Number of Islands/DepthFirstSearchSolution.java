// Java solution to Q200 "Number of Islands"
// Reference: https://leetcode.com/problems/number-of-islands/description/
// Date: 10-26-2018

/**
 * Thoughts:
 * Iterate through all spots, for each spot which is land and is not visited, do
 * a dfs on it and increment the number of island by 1
 */

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands
horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-26-2018
 * Initial recursive DFS solution in java
 */

 class Solution {
     public int numIslands(char[][] grid) {
         if (grid == null || grid.length == 0 || grid[0].length == 0) {
             return 0;
         }
         int numOfIslands = 0;
         boolean[][] visited = new boolean[grid.length][grid[0].length];
         for (int i = 0; i < grid.length; i++) {
             for (int j = 0; j < grid[0].length; j++) {
                 if (!visited[i][j] && grid[i][j] == '1') {
                     dfs(grid, visited, i, j);
                     numOfIslands++;
                 }
             }
         }
         return numOfIslands;
     }

     public void dfs (char[][] grid, boolean[][] visited, int x, int y) {
         visited[x][y] = true;
         // common trick to go up, down, left, right four directions
         int[] directX = new int[]{0, 0, -1, 1};
         int[] directY = new int[]{-1, 1, 0, 0};
         for (int i = 0; i < directX.length; i++) {
             int xx = x + directX[i];
             int yy = y + directY[i];
             // check to see all adjacent lands that are not visited yet
             if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length
             && !visited[xx][yy] && grid[xx][yy] == '1') {
                 // visited[xx][yy] = true;
                 dfs(grid, visited, xx, yy);
             }
         }
     }
 }
