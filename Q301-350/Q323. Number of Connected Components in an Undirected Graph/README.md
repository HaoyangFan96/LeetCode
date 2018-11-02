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

#### Complexity
-   Union Find
    -   Time: `O(mlog*n)` where m is the number of edges and n is the number of nodes. Since each find operation takes `O(log*n)` times, and time complexity of union is the same as the time complexity of find (we call find in union), when we call union on `m` edges, the time complexity will be `O(mlog*n)`
    -   Space: `O(n)` since for each of n nodes, we need to store their parent node references as well as their ranks

#### Idea:
1.  此题比较简单, Union Find, BFS, DFS 都可以做
2.  TODO: BFS, DFS

#### Mistakes I have made:
1.  Union Find 的 union 中，一开始找p和q的root居然忘记了用`find`, 而是错误地使用了`parent.get()`:
    ```
    int rootP = parent.get(p); // should be: int rootP = find(p)
    int rootQ = parent.get(q); // should be: int rootQ = find(q)
    ```

#### Review At:
