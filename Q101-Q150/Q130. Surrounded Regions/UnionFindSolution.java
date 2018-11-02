// Java solution to Q130 "Surrounded Regions"
// Reference: https://leetcode.com/problems/surrounded-regions/description/
// Date: 10-30-2018

/**
 * Thoughts:
 * 1. 依照题目所说，只有边界上的O和与其相连的O才能不被flip
 * 2. 我们需要一种数据结构，可以用来记录连通性，这样我们可以记录所有与边界上的O相连的O
 * 3. 针对于连通性的数据结构为Union Find (中文：并查集)
 * 4. 遍历二维数组，将所有的相邻的O联合起来，对于其中有在边界上的O，我们需要特殊记录，因为
 * 他们在最后是无需翻转的
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
 * Initial union find solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-30-2018
 */
class UnionFind {
    // use 1D array (m * n) to keep track all spots on the board
    protected int[] parent;
    // record whether
    protected boolean[] noFlip;
    // keep track of ranks (sizes) of connected components
    protected int[] rank;
    protected int rows;
    protected int cols;
    protected char[][] board;

    public UnionFind (char[][] board) {
        rows = board.length;
        cols = board[0].length;
        parent = new int[rows * cols];
        noFlip = new boolean[rows * cols];
        rank = new int[rows * cols];
        this.board = board;
        for (int i = 0; i < rows * cols; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find (int p) {
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (p != root) {
            int pp = parent[p];
            parent[p] = root;
            p = pp;
        }
        return root;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
            noFlip[rootQ] |= noFlip[rootP];
        } else {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
            noFlip[rootP] |= noFlip[rootQ];
        }
    }

    public void flip() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    int curr = i * cols + j;
                    int top = (i - 1) * cols + j, bot = (i + 1) * cols + j;
                    int left = i * cols + j - 1, right = i * cols + j + 1;
                    if (onBorder(curr)) {
                        noFlip[find(curr)] = true;
                    }
                    if (isValid(top) && board[i-1][j] == 'O') {
                        union(top, curr);
                    }
                    if (isValid(bot) && board[i+1][j] == 'O') {
                        union(bot, curr);
                    }
                    if (isValid(left) && curr % cols != 0 && board[i][j-1] == 'O') {
                        union(left, curr);
                    }
                    if (isValid(right) && (curr + 1) % cols != 0 && board[i][j+1] == 'O') {
                        union(right, curr);
                    }
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O' && !noFlip[find(i*cols+j)]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public boolean onBorder(int p) {
        if (p >= 0 && p < cols) {
            return true;
        }
        if (p >= cols * (rows - 1) && p < cols * rows) {
            return true;
        }
        if (p % cols == 0 || (p + 1) % cols == 0) {
            return true;
        }
        return false;
    }

    public boolean isValid(int p) {
        return p >= 0 && p < cols * rows;
    }
}

class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        UnionFind uf = new UnionFind(board);
        uf.flip();
    }
}
