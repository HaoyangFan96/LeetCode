// Java solution to Q261 "Graph Valid Tree"
// Reference: https://leetcode.com/problems/graph-valid-tree/description/
// Date: 10-26-2018

/**
 * Thoughts:
 * 这道题比较经典，tag中有bfs, dfs, union find这三个tag
 * 仔细想想的话貌似也都可以。因为刚刚学完union find，所以就用它来复习下吧
 *
 * NOTE: in order to be a tree, there are two keys
 * 1) acyclic: there cannot be a cycle in the tree
 * 2) single root: there can be only one root at any time
 */

/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is
a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges.
Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */

/**
 * @author Haoyang Fan
 * @version 2.0
 * @since 10-26-2018
 * Simplified Union Find solution in Java
 */
class Solution {
 /**
  * @param n: An integer
  * @param edges: a list of undirected edges
  * @return: true if it's a valid tree, or false
  */
 private int[] parent;
 private int[] size;
 private int count;
 public boolean validTree(int n, int[][] edges) {
     if (n < 1 || edges == null || edges.length != n - 1) {
         return false;
     }
     parent = new int[n];
     size = new int[n];
     count = n;
     for (int i = 0; i < n; i++) {
         parent[i] = i;
         size[i] = 1;
     }
     for (int[] edge : edges) {
         if (find(edge[0]) == find(edge[1])) {
             return false;
         }
         union(edge[0], edge[1]);
     }
     return count == 1;
 }

 private int find(int p) {
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

 private void union(final int p, final int q) {
     int rootP = find(p);
     int rootQ = find(q);
     if (rootP == rootQ) {
         return;
     }
     if (size[rootP] < size[rootQ]) {
         parent[rootP] = rootQ;
         size[rootQ] += size[rootP];
     } else {
         parent[rootQ] = rootP;
         size[rootP] += size[rootQ];
     }
     count--;
 }
}

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-26-2018
 * Initial Union Find solution in Java
 */

class UnionFind {
    private int[] id; // component identifier for each connected component
    private int[] size; // size of each connected component
    private int count;  // count of total number of connected components

    public UnionFind (final int n) {
        id = new int[n];
        size = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    // key method for this problem
    public int count () {
        return count;
    }

    public int find (int p) {
        int root = p;
        // until root has been reached
        while(root != id[root]) {
            root = id[root];
        }
        // path compression
        while (p != root) {
            int parent = id[p];
            id[p] = root;
            p = parent;
        }
        return root;
    }
    // another key method for this problem
    public boolean connected (int p, int q) {
        return find(p) == find (q);
    }

    public void union (int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // weighted union
        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}

class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize the Union Find tree-like data structure
        UnionFind uf = new UnionFind(n);
        // iterate through all edges to see if there is a cycle
        for (int[] edge : edges) {
            int n1 = edge[0];
            if (n1 < 0 || n1 >= n) {
                return false;
            }
            int n2 = edge[1];
            if (n2 < 0 || n2 >= n) {
                return false;
            }
            if (uf.connected(n1, n2)) {
                return false;
            }
            uf.union(n1, n2);
        }
        return uf.count() == 1;
    }
}
