// Java solution to Q200 "Number of Islands"
// Reference: https://leetcode.com/problems/number-of-islands/description/
// Date: 06-28-2019

/**
 * Thoughts:
 *
 * 这道题思路非常简单而且非常高频，可以用来练习union find，dfs，bfs
 *
 * Time:
 *
 * Space:
 *
 */

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

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
 * Initial Union Find solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 06-28-2019
 */
class Solution {

    private static final int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private int[] grids; // union find data structure
    private int[] sizes; // keep track of sizes for each connected component, used for weighted union
    private int numOfIslands; // keep track of number of islands

    private void buildUnionFind(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        grids = new int[rows * cols];
        for (int i = 0; i < grids.length; ++i) {
            grids[i] = i; // initialize each grid to be in its own connected components
        }
        sizes = new int[rows * cols];
        Arrays.fill(sizes, 1);

        numOfIslands = 0;
    }

    private int find(int idx) {
        while (idx != grids[idx]) {
            grids[idx] = grids[grids[idx]]; // path compression
            idx = grids[idx];
        }

        return idx;
    }

    private void union(int p, int q) {
        // first determine the connected components that contain p and q
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        numOfIslands -= 1;
        if (sizes[rootP] <= sizes[rootQ]) {
            grids[rootP] = rootQ;
            sizes[rootQ] += sizes[rootP];
        } else {
            grids[rootQ] = rootP;
            sizes[rootP] += sizes[rootQ];
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length, cols = grid[0].length;

        // build union find
        buildUnionFind(grid);

        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (grid[r][c] != '1') continue;
                numOfIslands += 1;
                // try out each direction
                for (int[] direction : directions) {
                    int rr = r + direction[0], cc = c + direction[1];
                    if (!isValid(rr, cc, grid)) continue;
                    union(r * cols + c, rr * cols + cc);
                }
            }
        }

        return numOfIslands;
    }

    private boolean isValid(int r, int c, char[][] grid) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == '1';
    }
}
