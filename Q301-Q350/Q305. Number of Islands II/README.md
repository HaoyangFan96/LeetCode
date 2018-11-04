### Problem  ["Number of Islands II"](https://leetcode.com/problems/number-of-islands-ii/description/)

#### Knowledge Used:
1.  Union Find
2.  HashMap
3.  Array

#### References:
1.  <https://github.com/awangdev/LintCode/blob/master/Java/Number%20of%20Islands%20II.java>
2.  <https://leetcode.com/problems/number-of-islands-ii/solution/>
3.  <https://leetcode.com/problems/number-of-islands-ii/discuss/75459/JavaPython-clear-solution-with-UnionFind-Class-(Weighting-and-Path-compression)>
4.  <https://mnmunknown.gitbooks.io/algorithm-notes/content/66,_union-findff0c_bing_cha_ji_ji_chu.html>

#### Code
-   [Union Find Java solution](./UnionFindSolution.java)

#### Complexity
-   Time: `O(Nlog*N)` where `N` is the number of positions to turn into land. When there are `N` positions to turn into land, for each operation there will be several calls of `union` and `find`, and there will be `N` objects in union find data structure. With path compression and union by rank optimization, the tree will be very flat so that `union` and `find` operation will take `O(log*N)` time. Since there are total `N` operations to turn sea into land, the total time complexity will be `O(Nlog*N)` which is almost `O(N)`
-   Space: `O(mn)` where `m` is the number of rows and `n` is the number of columns because in worst case all positions could turn into land.

#### Idea:
1.  由于这题中陆地是逐步加入的，所以使用DFS或者BFS这样的离线算法并不合适，而应该使用Union Find这种实现动态连通性的在线算法
2.  Union Find用HashMap或者array做底层数据结构都可以，唯一的区别是当grid特别大的时候，array的话initialization的时间成本过高，因此我们可以使用HashMap来省略这个initialization的步骤，而在之后用类似于`getOrDefault`这样的method来达到相同的目的(具体对比详见V3.0 V2.0)


#### Mistakes I have made:
1.  在这题里降维成一维 index 是可以的，不过要注意边界处理，否则某一行的最后一个元素会连通到下一行的第一个元素上去或者某一行的第一个元素会联通到上一行的最后一个元素，而这实际上是不符合逻辑的
2.  LintCode 此题多加了个情况，即重复添加同一个位置为陆地，这样的话，在addLand中需要检查当前的位置是否已经在之前被添加为陆地了

#### Review At:
1.  11-3-2018
