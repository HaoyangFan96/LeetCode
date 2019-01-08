### Problem  ["Number of Connected Components in an Undirected Graph"](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/)

#### Knowledge Used:
1.  BFS
2.  DFS
3.  Union Find
4.  Array

#### References:
1.  <https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Connected%20Components%20in%20an%20Undirected%20Graph.java>


#### Code
-   [Union Find Java solution](./UnionFindSolution.java)
-   [BFS Java solution](./BFSSolution.java)

#### Complexity
-   Union Find:
    -   Time: `O(mlog*n)` where m is the number of edges and n is the number of nodes. Since each find operation takes `O(log*n)` times, and time complexity of union is the same as the time complexity of find (we call find in union), when we call union on `m` edges, the time complexity will be `O(mlog*n)`
    -   Space: `O(n)` since for each of n nodes, we need to store their parent node references as well as their ranks

-   BFS:
    -   Time: `O(E + n)` where E is the number of edges and n is the number of nodes. O(E) is for the construction of graph and O(n) is for BFS since each node will be visited at exactly once
    -   Space: `O(E + n)`, O(E) for extra space used for representing the graph and O(n) for space used by queue in the worst case there could be O(n) nodes in the queue (think of a root node connected to all other nodes)

#### Idea:
1.  此题比较简单, Union Find, BFS, DFS 都可以做
2.  TODO: DFS

#### Mistakes I have made:
1.  Union Find 的 union 中，一开始找p和q的root居然忘记了用`find`, 而是错误地使用了`parent.get()`:
    ```
    int rootP = parent.get(p); // should be: int rootP = find(p)
    int rootQ = parent.get(q); // should be: int rootQ = find(q)
    ```

#### Review At:
-   01-07-2019
