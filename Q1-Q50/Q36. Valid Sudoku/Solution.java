// Solution to Q36 "Valid Sudoku"
// Reference: https://leetcode.com/problems/valid-sudoku/description/
// Date: 08-29-2018

/*
Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated
according to the following rules:
1. Each row must contain the digits 1-9 without repetition.
2. Each column must contain the digits 1-9 without repetition.
3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
*/

class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }
        // Use HashSet to ensure
        // Docs: https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html
        HashSet<Character> row = new HashSet<>(9);
        HashSet<Character> col = new HashSet<>(9);
        HashSet<Character> block = new HashSet<>(9);
        for (int i = 0; i < 9; i++) {
            row.clear();
            col.clear();
            block.clear();
            for (int j = 0; j < 9; j++) {
                // different rows of the same column
                char r = board[j][i];
                // different columns of the same row
                char c = board[i][j];
                // different spot of the same block
                char b = board[i / 3 * 3 + j % 3][i % 3 * 3 + j % 3];
                // check row
                if (c != '.' &&  row.contains(c)) {
                    return false;
                }
                else {
                    row.add(c);
                }
                // check column
                if (r != '.' && col.contains(r)) {
                    return false;
                }
                else {
                    col.add(r);
                }
                // check block
                if (b != '.' && block.contains(b)) {
                    return false;
                }
                else {
                    block.add(b);
                }
            }
        }
        return true;
    }
}
