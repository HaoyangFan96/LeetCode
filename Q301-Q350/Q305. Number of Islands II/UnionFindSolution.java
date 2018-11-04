// Java solution to Q305 "Number of Islands II"
// Reference: https://leetcode.com/problems/number-of-islands-ii/description/
// Date: 10-30-2018

/**
 * Thoughts:
 * 此题很明显不能像number of islands I一样用bfs或者dfs这样的离线算法，因为全部的陆地
 * 并不是一开始就都确定的，而是逐步加入的
 * 因为如果继续用bfs或者dfs这样的离线算法的话，每当新增一个岛屿，那么我们都需要重新跑一遍整个grid，
 * 很明显这样做的cost会非常高
 * 而union find，作为一个在线算法，能够动态的接受新加的陆地，而无需从头开始整个跑一遍
 *
 * 对于这道题而言，input坐标是2D的，所以我们要将2D转化为1D的坐标，公式为：
 * 1D = x * cols + y
 * 我们可以用HashMap也可以用array来做底层数据结构，HashMap的好处在于，在rows和cols非常大的
 * 情况下，我们可以免去initilization这一O(mn)的操作，具体请对比v3.0和v2.0写法的区别
 */

/*
2d grid map of m rows and n columns is initially filled with water.
We may perform an addLand operation which turns the water at position (row, col) into a land.
Given a list of positions to operate, count the number of islands after each addLand operation.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */


/**
 * Use HashMap to replace the original 1D array in order to save time on initialization.
 * The optimization only works when m and n are extreme large. However, it seems
 * not be the case for this problem...
 *
 * @author Haoyang Fan
 * @version 3.0
 * @since 11-3-2018
 */
class UnionFind {
    protected Map<Integer, Integer> parent;
    protected Map<Integer, Integer> rank;
    protected int rows;
    protected int cols;
    protected int count;

    public UnionFind(int m, int n) {
        rows = m;
        cols = n;
        parent = new HashMap<>(m * n);
        rank = new HashMap<>(m * n);
        count = 0;
    }

    public int find(int p) {
        int root = p;
        while (root != parent.get(root)) {
            root = parent.get(root);
        }
        while (p != root) {
            int pp = parent.get(p);
            parent.put(p, root);
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
        int rankP = rank.getOrDefault(rootP, 1);
        int rankQ = rank.getOrDefault(rootQ, 1);
        if (rankP < rankQ) {
            parent.put(rootP, rootQ);
            rank.put(rootQ, rankP + rankQ);
        } else {
            parent.put(rootQ, rootP);
            rank.put(rootP, rankP + rankQ);
        }
        count--;
    }

    public void addLand(int[] position) {
        int x = position[0], y = position[1];
        int curr = convertTo1D(x, y);
        // check to see if current position is already a land
        if (parent.containsKey(curr)) {
            return;
        }
        parent.put(curr, curr);
        // temporarily increment the count by 1
        count++;
        int top = convertTo1D(x-1, y);
        int bot = convertTo1D(x+1, y);
        int left = convertTo1D(x, y-1);
        int right = convertTo1D(x, y+1);
        // check the top position
        if (isValid(x-1, y, top)) {
            union(curr, top);
        }
        // check the bottom position
        if (isValid(x+1, y, bot)) {
            union(curr, bot);
        }
        // check left position: make sure current position is not on the left border
        if (isValid(x, y-1, left)) {
            union(curr, left);
        }
        // check right position: make sure current position is not on the right border
        if (isValid(x, y+1, right)) {
            union(curr, right);
        }
    }

    public int convertTo1D(int x, int y) {
        return x * cols + y;
    }

    public boolean isValid(int x, int y, int v) {
        return x >= 0 && x < rows && y >= 0 && y < cols && parent.containsKey(v);
    }

    public int query() {
        return count;
    }
}

class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> rst = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return rst;
        }
        UnionFind uf = new UnionFind(m,n);
        for (int[] pos : positions) {
            uf.addLand(pos);
            rst.add(uf.query());
        }
        return rst;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Improved union find solution in Java, refactoring the code.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 11-3-2018
 */
class UnionFind {
    protected int[] parent;
    protected int[] rank;
    protected int rows;
    protected int cols;
    protected int count;

    public UnionFind(int m, int n) {
        rows = m;
        cols = n;
        parent = new int[rows * cols];
        rank = new int[rows * cols];
        for (int i = 0; i < rows * cols; i++) {
            // trick to record whether current position is land (>= 0) or sea (-1)
            parent[i] = -1;
            rank[i] = 1;
        }
    }

    public int find(int p) {
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
        } else {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
        count--;
    }

    public void addLand(int[] position) {
        int x = position[0], y = position[1];
        int curr = convertTo1D(x, y);
        // check to see if current position is already a land
        if (parent[curr] >= 0) {
            return;
        }
        parent[curr] = curr;
        // temporarily increment the count by 1
        count++;
        int top = convertTo1D(x-1, y);
        int bot = convertTo1D(x+1, y);
        int left = convertTo1D(x, y-1);
        int right = convertTo1D(x, y+1);
        // check the top position
        if (isValid(x-1, y)) {
            union(curr, top);
        }
        // check the bottom position
        if (isValid(x+1, y)) {
            union(curr, bot);
        }
        // check left position: make sure current position is not on the left border
        if (isValid(x, y-1)) {
            union(curr, left);
        }
        // check right position: make sure current position is not on the right border
        if (isValid(x, y+1)) {
            union(curr, right);
        }
    }

    public int convertTo1D(int x, int y) {
        return x * cols + y;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && parent[convertTo1D(x,y)] >= 0;
    }

    public int query() {
        return count;
    }
}

class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> rst = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0].length == 0) {
            return rst;
        }
        UnionFind uf = new UnionFind(m,n);
        for (int[] pos : positions) {
            uf.addLand(pos);
            rst.add(uf.query());
        }
        return rst;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial union find solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-30-2018
 */
class UnionFind {
    protected int[] parent;
    protected int[] rank;
    protected int count;
    protected int rows;
    protected int cols;

    public UnionFind (int m, int n) {
        parent = new int[m * n];
        rank = new int[m * n];
        count = 0;
        rows = m;
        cols = n;
        for (int i = 0; i < m * n; i++) {
            parent[i] = -1;
            rank[i] = 1;
        }
    }

    public int find (int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connected (int p, int q) {
        return find(p) == find(q);
    }

    public int query() {
        return count;
    }

    public void union (int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        } else {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
        count--;
    }

    public void addLand(int m, int n) {
        count ++;
        int curr = m * cols + n;
        parent[curr] = curr;
        int top = curr - cols, bot = curr + cols;
        int left = curr - 1, right = curr + 1;
        // check if the top curr is valid
        if (top >= 0 && top < rows * cols && parent[top] != -1) {
            union(top, curr);
        }
        // check if the bottom curr is valid
        if (bot >= 0 && bot < rows * cols && parent[bot] != -1) {
            union(bot, curr);
        }
        // NOTE: for the left position, we need to additionally check if current
        // curr is on the left border, if so, there is no left curr
        if (curr % cols != 0 && left >= 0 && left < rows * cols && parent[left] != -1) {
            union(left, curr);
        }
        // NOTE: for the right position, we need to additionally check if current
        // position is on the right border, if so, there is no right position
        if ((curr+1) % cols != 0 && right >= 0 && right < rows * cols && parent[right] != -1) {
            union(right, curr);
        }
    }
}

class Solution {
     public List<Integer> numIslands2(int m, int n, int[][] currs) {
         List<Integer> rst = new ArrayList<>();
         if (currs == null || currs.length == 0 || currs[0].length <= 1) {
             return rst;
         }
         UnionFind uf = new UnionFind(m, n);
         for (int[] curr : currs) {
             uf.addLand(curr[0], curr[1]);
             rst.add(uf.query());
         }
         return rst;
     }
}
