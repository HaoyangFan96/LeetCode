#### Knowledge Used:
1. string
2. dynamic programming
3. backtracking
4. 滚动数组(优化DP space)

#### Code
- [java DP](./DPSolution.java)
- [java DP optimized](./DPOptimizedSolution.java)
- [java iterative+backtrack](./IterativeSolution.java)
- [js DP](./Solution.js)

#### Complexity
- Time: `O(n)` for iterative solution, `O(mn)` for DP solution, assuming m = len(s), n = len(p)
- Space: `O(1)` for iterative solution, `O(mn)` for DP solution, `O(n)` for optimized DP solution

#### Idea:
1. https://www.tianmaying.com/tutorial/LC44  for DP solution
2. https://github.com/awangdev/LintCode/blob/master/Java/Wildcard%20Matching.java for DP solution
已经相应的优化方式：滚动数组，来reduce space complexity
3. https://blog.csdn.net/niushuai666/article/details/6677982 滚动数组思想
4. https://www.youtube.com/watch?v=-8QnRMyHo_o for O(n) iterative solution
5. 重点来了解 O(n)的solution, which is most optimized

#### Mistakes I have made:
1. DP中array的size应该是`s.length()+1 * p.length()+1`而不是`s.length()*p.length()`

#### Review At:
1. 09-15-2018
