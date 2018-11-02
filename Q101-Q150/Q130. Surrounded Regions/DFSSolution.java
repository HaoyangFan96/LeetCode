// Java solution to Q130 "Surrounded Regions"
// Reference: https://leetcode.com/problems/surrounded-regions/description/
// Date: 10-31-2018

/**
 * Thoughts:
 * 1. 我们需要找到所有在边界上的点，与边界上的点相连的点，与边界上的点相连的点相连点。。
 * 2. 找到边界上的所有"O"很容易
 * 3. 我们可以从边界上的每一个"O"出发，利用DFS来寻找所有与边界上的"O"点相连的点
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

class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int rows = board.length, cols = board[0].length;
        // starting from all O in the top/bottom border
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == 'O') {
                dfs (board, 0, i);
            }
            if (board[rows-1][i] == 'O') {
                dfs (board, rows-1, i);
            }
        }
        // start from all O in the left/right board
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                dfs (board, i, 0);
            }
            if (board[i][cols-1] == 'O') {
                dfs (board, i, cols-1);
            }
        }
        // finally, rescan every spot, convert all O left to X, all 1 to O
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

    private void dfs(char[][] board, int x, int y) {
        // mark current index as visited
        board[x][y] = '1';
        // common trick: use of directional vectors for up, down, left, right
        int[] directX = {-1, 1, 0, 0};
        int[] directY = {0, 0, -1, 1};
        for (int i = 0; i < directX.length; i++) {
            if (isValid(board, x + directX[i], y + directY[i])) {
                dfs(board, x + directX[i], y + directY[i]);
            }
        }
    }

    private boolean isValid(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O';
    }
}
