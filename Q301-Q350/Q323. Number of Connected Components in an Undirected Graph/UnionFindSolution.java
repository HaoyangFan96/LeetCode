// Java solution to Q323 "Number of Connected Components in an Undirected Graph"
// Reference: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/
// Date: 11-1-2018

/**
 * Thoughts:
 * This problem is pretty straightforward that it just describes the basic idea
 * of union find.
 * We can just implement a very plain union find without too much specification
 * for this particular problem.
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
You can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges
 */

/**
 * Initial union find solution in Java
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-1-2018
 */

// this time I am going to use HashMap as the base data structure
class UnionFind {
    protected Map<Integer, Integer> parent;
    protected Map<Integer, Integer> rank;
    protected int count;    // keep track of number of connected components

    public UnionFind(int n) {
        count = n;
        parent = new HashMap<>(n);
        rank = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            parent.putIfAbsent(i, i);   // each node initially points to itself
            rank.putIfAbsent(i, 1); // initial rank (size) for each component is 1
        }
    }
    // here I am going to define the find recursively
    public int find (int p) {
        int father = parent.get(p);
        if (p == father) {
            return p;
        }
        // path compression: attach each node visited in the path to the root
        parent.put(p, find(father));
        return parent.get(p);
    }

    // optimization: union by rank
    public void union (int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        int rankP = rank.get(rootP);
        int rankQ = rank.get(rootQ);
        if (rankP < rankQ) {
            parent.put(rootP, rootQ);
            rank.put(rootQ, rankP + rankQ);
        } else {
            parent.put(rootQ, rootP);
            rank.put(rootP, rankP + rankQ);
        }
        count--;
    }

    public int query() {
        return count;
    }

    public void addEdges(int[][] edges) {
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
    }
}

class Solution {
    public int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return n;
        }
        UnionFind uf = new UnionFind(n);
        uf.addEdges(edges);
        return uf.query();
    }
}
