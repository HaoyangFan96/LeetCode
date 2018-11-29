### Problem  ["Wildcard Matching"](https://leetcode.com/problems/wildcard-matching/description/)

#### Knowledge Used:
1.  Dynamic Programming
2.  Memoization
3.  DFS
4.  Array

#### References:
1.  <https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java>
2.  <https://www.jiuzhang.com/solution/wildcard-matching/>
3.  九章算法班第六节课下半部分笔记对于这道题的讲解

#### Code
-   [Java DP 2D Array](./DPSolution.java)
-   [Java DFS + Memoization](./MemoizationSolution.java)
-   [Java Iterative O(n)](./IterativeSolution.java)
-   [JS solution](./Solution.js)

#### Complexity
-   Time:
    -   `O(mn)`, assuming m = len(s), n = len(p) for DP solution
    -   `O(n)` for iterative solution
-   Space:
    -   `O(mn)`, assuming m = len(s), n = len(p) which can be optimized using rolling array, for DP solution
    -   `O(1)` for iterative solution

#### Idea:
1.  <https://www.tianmaying.com/tutorial/LC44> for DP solution
2.  <https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java> for DP solution
已经相应的优化方式：滚动数组，来reduce space complexity
3.  <https://blog.csdn.net/niushuai666/article/details/6677982> 滚动数组思想
4.  <https://www.youtube.com/watch?v=-8QnRMyHo_o> for O(n) iterative solution
5.  重点来了解 O(n)的solution, which is most optimized


#### Mistakes I have made:
1.  DP中array的size应该是`s.length()+1 * p.length()+1`而不是`s.length()*p.length()`

#### Review At:
1.  11-29-2018
