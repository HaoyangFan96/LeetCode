### Problem  ["Surrounded Regions"](https://leetcode.com/problems/surrounded-regions/description/)

#### Knowledge Used:
1.  DFS
2.  BFS
3.  Union Find
4.  Array

#### References:
1.  <https://leetcode.com/problems/surrounded-regions/discuss/41633/Java-DFS-+-boundary-cell-turning-solution-simple-and-clean-code-commented.>
2.  <https://github.com/awangdev/LintCode/blob/master/Java/Surrounded%20Regions.java>


#### Code
1.  [DFS Java solution](./DFSSolution.java)
2.  [BFS Java solution](./BFSSolution.java)
3.  [Union Find Java solution](./UnionFindSolution.java)

#### Complexity
-   Time: `O(mn)` since every element in the board needs to be scanned at least several times, for DFS, BFS and Union Find
-   Space: `O(mn)`, BFS need to use a queue which in worst case almost every node in board are in the queue. For UnionFind, it is definitely `O(mn)` because its implementation will need a array of size `mn` to store all nodes. For DFS, the stack space it used will be similar to BFS.

#### Idea:
1.  与number of islands I类似，这道题用DFS, BFS，Union Find都可以
2.  大概的思路就是从边界上的O出发，找所有能与其有连接的O，将除此之外的O翻转成X。核心就是找连接的这个过程，可以用DFS，BFS或者Union Find来处理。

#### Mistakes I have made:
1.

#### Review At:
1.  11-1-2018
