// Java solution to Q378 "Kth Smallest Element in a Sorted Matrix"
// Reference: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
// Date: 10-24-2018

/**
 * Thoughts:
 * 跟Q215 heap解法类似，具体的过程描述可参考九章算法强化版第一节课笔记的下班部分
 * 1. 创建一个min heap
 * 2. 将头一行或者头一列的element全部添加进去
 * 3. poll out掉头k-1个element，接下来的heap的topmost element即为kth smallest one
 */

/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/

/**
 * @author Haoyang Fan
 * @version 2.0
 * @since 10-24-2018
 * Revised heap solution in java, changing the idea and refactoring code
 */
class Coord implements Comparable<Coord>{
    protected int x;    // x coordinate
    protected int y;    // y coordinate
    protected int val;  // value that corresponds to this coordinate
    public Coord (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    // in order to make a min heap
    @Override
    public int compareTo (Coord other) {
        return this.val - other.val;
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        Queue<Coord> maxHeap = new PriorityQueue<>(matrix.length);
        // add all elements on the first column into the heap
        for (int i = 0; i < matrix.length; i++) {
            maxHeap.offer(new Coord(i, 0, matrix[i][0]));
        }
        // poll out the first k - 1 smallest elmeent
        for (int i = 0; i < k - 1; i++) {
            Coord s = maxHeap.poll();
            if (s.y + 1 < matrix[0].length) {
                // in case some values that are supposed to be in the first k
                // smallest values are not actually in the first column
                maxHeap.offer(new Coord(s.x, s.y + 1, matrix[s.x][s.y + 1]));
            }
        }
        // poll out the kth smallest element
        return maxHeap.poll().val;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-24-2018
 * My initial heap solution in java
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        // initialize a max heap
        Queue<Integer> maxHeap = new PriorityQueue<>(k, (e1, e2) -> e2 - e1);
        int maxRow = matrix.length - 1;
        int maxCol = matrix[0].length - 1;
        boolean[][] visited = new boolean[maxRow + 1][maxCol + 1];
        Deque<Coord> candidates = new ArrayDeque<>();
        // starting with top-left position
        candidates.addLast(new Coord(0, 0));
        while (!candidates.isEmpty()) {
            Coord curr = candidates.pollFirst();
            if (maxHeap.size() < k || matrix[curr.x][curr.y] < maxHeap.peek()) {
                maxHeap.offer(matrix[curr.x][curr.y]);
            }
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
            // add new candidates
            if (curr.x + 1 <= maxRow && !visited[curr.x + 1][curr.y]) {
                candidates.addLast(new Coord(curr.x + 1, curr.y));
                visited[curr.x + 1][curr.y] = true;
            }
            if (curr.y + 1 <= maxCol && !visited[curr.x][curr.y + 1]) {
                candidates.addLast(new Coord(curr.x, curr.y + 1));
                visited[curr.x][curr.y + 1] = true;
            }
        }
        return maxHeap.poll();
    }
}

class Coord {
    protected int x;
    protected int y;

    public Coord (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
