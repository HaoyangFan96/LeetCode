// Java solution to Q130 "Surrounded Regions"
// Reference: https://leetcode.com/problems/surrounded-regions/description/
// Date: 10-31-2018

/**
 * Thoughts:
 * 1. 我们需要找到所有在边界上的点，与边界上的点相连的点，与边界上的点相连的点相连点。。
 * 2. 找到边界上的所有"O"很容易
 * 3. 我们可以从边界上的每一个"O"出发，利用BFS来寻找所有与边界上的"O"点相连的点
 */

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the
border of the board are not flipped to 'X'.
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

/**
 * Initial BFS solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-30-2018
 */
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int rows = board.length, cols = board[0].length;
        // first check the top and bottom board, find all O
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                bfs(board, 0, i);
            }
            if (board[rows-1][i] == 'O') {
                bfs(board, rows - 1, i);
            }
        }
        // then check the left and right board, find all O
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                bfs(board, i, 0);
            }
            if (board[i][cols-1] == 'O') {
                bfs(board, i, cols - 1);
            }
        }
        // iterate through the all elements
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs(char[][] board, int x, int y) {
        // mark current spot as "visited"
        board[x][y] = '1';
        int rows = board.length, cols = board[0].length;
        // common trick: initialize a queue that just hold the x index
        Queue<Integer> xs = new ArrayDeque<>();
        // common trick: initialize a queue that just hold the x index
        Queue<Integer> ys = new ArrayDeque<>();
        // common trick: direction vectors that control up, bottom, left, right
        int[] directX = {-1, 1, 0, 0};
        int[] directY = {0, 0, -1, 1};
        xs.add(x);
        ys.add(y);
        while (!xs.isEmpty()) {
            int currX = xs.remove();
            int currY = ys.remove();
            // check each four directions
            for (int i = 0; i < directX.length; i++) {
                if (isValid(board, rows, cols, currX + directX[i], currY + directY[i])) {
                    xs.add(currX + directX[i]);
                    ys.add(currY + directY[i]);
                    board[currX + directX[i]][currY + directY[i]] = '1';
                }
            }
        }
    }

    public boolean isValid (char[][] board, int rows, int cols, int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && board[x][y] == 'O';
    }
}
