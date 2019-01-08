// Java solution to Q323 "Number of Connected Components in an Undirected Graph"
// Reference: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
// Date: 01-07-2018

/**
 * Thoughts:
 *
 * The idea for this problem is relatively straightforward: first construct the
 * graph based on the given input. Then for each node that hasn't been visited yet,
 * start BFS on it, and mark it as well as nodes appeared in BFS as visited. Keep
 * track of the number of BFS need to carry out to get every node as visited. The
 * number of connected component is just equal to the number of BFS.
 *
 * Time: O(E + n) where E is the number of edges, for constructing the graph. O(n)
 * for BFS since each node will be visited exactly once
 *
 * Space: O(E + n) O(E) for the extra space used to represent the graph and O(n)
 * for BFS (in worst case)
 *
 */

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */

/**
 * Initial BFS solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 01-07-2018
 */
class Solution {
    public int countComponents(int n, int[][] edges) {
        int num = 0;
        boolean[] visited = new boolean[n];
        // 第一步，建图
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);
        // 第二步，对于每一个未处理过的node进行BFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(graph, i, visited);
                num++;
            }
        }
        return num;
    }

    private void bfs(Map<Integer, List<Integer>> graph, int i, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visited[i] = true;
        // BFS:
        while (!queue.isEmpty()) {
            // no need to traverse by level order
            int curr = queue.poll();
            // check every of its neighbors
            for (int j : graph.get(curr)) {
                if (!visited[j]) {
                    queue.offer(j);
                    visited[j] = true;
                }
            }
        }
    }

    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<Integer>());
        }
        // iterate over all edges
        for (int[] edge : edges) {
            int i1 = edge[0], i2 = edge[1];
            // add edges in both direction
            graph.get(i1).add(i2);
            graph.get(i2).add(i1);
        }
        return graph;
    }
}
