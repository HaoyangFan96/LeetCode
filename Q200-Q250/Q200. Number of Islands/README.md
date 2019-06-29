### Problem  ["Number of Islands"](https://leetcode.com/problems/number-of-islands/description/)

#### Knowledge Used:
1.  BFS
2.  DFS
3.  Union Find
4.  Array

#### References:
1.  <https://leetcode.com/problems/number-of-islands/solution/>
2.  <https://www.jiuzhang.com/solutions/number-of-islands/>
3.  <https://aaronice.gitbooks.io/lintcode/graph_search/number_of_islands.html>

#### Code
-   [DFS Java solution](./DepthFirstSearchSolution.java)
-   [BFS Java solution](./BreadthFirstSearchSolution.java)
-   [Union Find Java Solution](./UnionFindSolution.java)

#### Complexity
-   Time: `O(mn)` where m is the number of rows and n is the number of cols
-   Space: `O(min(M,N))` because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N). Note that although I also use a boolean 2D array to mark each land has been visited or not, which will take `O(mn)` space for sure. However, this can be also achieved via directly modifying the input grid so that we can neglect the space taken by that 2D array

#### Idea:
1.  此题非常经典，难度不高，可以分别利用DFS，BFS, Union Find解决
2.  (DFS/BFS)具体的思路非常简单：遍历一遍grid，对于每一个新发现的陆地，将岛屿的数量加一，然后基于这个当前的陆地进行DFS或者BFS。在此过程中将访问的陆地标记，这样的话之后我们便不会重复访问之前已经访问过的陆地
3.  DFS: 用recursion（自带stack）或者自己弄个stack来iterative的写
4.  BFS: 直接起queue
5.  Union Find: 基本就是检测自己对于模版的使用熟悉程度

#### Mistakes I have made:
1.  BFS中不需要在solution里面创建queue然后把它pass到bfs helper method中，直接在helper method中起一个queue就好了

#### Review At:
1.  10-30-2018
2.  06-28-2019
