### Problem  ["Graph Valid Tree"](https://leetcode.com/problems/graph-valid-tree/description/)

#### Knowledge Used:
1.  BFS
2.  DFS
3.  Union Find
4.  Graph

#### Code
-   [Union Find Java solution](./UnionFindSolution.java)

#### Complexity
-   For Union Find solution:
    -   Time: `O(nlg*n)` where `lg*n` is almost equal to 1
    -   Space: `O(n)` because of use of Union Find data structure, which is implemented based on array

#### Idea:
1.  A valid tree with n nodes must have exactly n-1 edges !!! 切记
2.  此题可以用BFS(TODO), DFS(TODO), UnionFind 来解
3.  利用Union Find来解的时候并不一定要写出全部的functionality，仅仅写出与此题相关的即可：利用find来判断两个node是否连接，在两个node已经存在连接的情况下，如果仍然有一条新的edge两端为这两个node，那么我们可以判断该Graph存在cycle，无法形成树状结构。在最后需要判断下是否所有的node已连接在一起，无孤立node存在(即connected component数量为1)

#### Mistakes I have made:
1.  仅仅考虑到了tree的必备条件为无环 (acyclic), 而并未考虑到tree在任何时刻应该有且仅有一个root

#### Review At:
