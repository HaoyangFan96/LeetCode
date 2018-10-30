// Java solution to Q200 "Number of Islands"
// Reference: https://leetcode.com/problems/number-of-islands/description/
// Date: 10-30-2018

/**
 * Thoughts:
 * Iterate through all spots, for each spot which is land and is not visited, do
 * a bfs on it and increment the number of island by 1
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
 * BFS solution in java
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-30-2018
 */
class Coord {
    protected int x;
    protected int y;

    public Coord (final int x, final int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // count the number of islands
        int numOfIslands = 0;
        // iterate through all islands
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, visited, i, j);
                    numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }

    private void bfs (char[][] grid, boolean[][] visited, int x, int y) {
        // make current land as visited
        visited[x][y] = true;
        int rows = grid.length, cols = grid[0].length;
        // common trick to get direction vectors of up, down, left, right
        int[] directX = {0, 0, -1, 1};
        int[] directY = {-1, 1, 0, 0};
        // initialize a queue for bfs
        Queue<Coord> queue = new ArrayDeque<>();
        // add current land to queue
        queue.add(new Coord(x, y));
        // while the queue is not empty
        while (!queue.isEmpty()) {
            Coord curr = queue.remove();
            // check land that is adjacent to current land
            for (int i = 0; i < directX.length; i++) {
                int tempX = curr.x + directX[i];
                int tempY = curr.y + directY[i];
                if (tempX < 0 || tempX >= rows) {
                    continue;
                }
                if (tempY < 0 || tempY >= cols) {
                    continue;
                }
                if (visited[tempX][tempY] || grid[tempX][tempY] != '1') {
                    continue;
                }
                queue.add(new Coord(tempX, tempY));
                visited[tempX][tempY] = true;
            }
        }
    }
}
