### Problem  ["Word Break II"](https://leetcode.com/problems/word-break-ii/description/)

#### Knowledge Used:
1.  Dynamic Programming
2.  Depth First Search
3.  Memoization
4.  Backtracking

#### Code
-   [DFS + Memoization solution in java](./MemoizationSolution.java)
-   [DFS + DP + Backtracking solution in java](./BacktrackingSolution.java)

#### Complexity
-   Time: `O(2^n)`
-   Space: `O(2^n)`

#### Idea:
1.  The popular choice for solving this problem is to use `DFS + Memoization`, as it can save the overhead of repeatedly splitting the same substring multiple times, which is kind of a pruning on DFS paths
2.  Another idea is to use regular `DFS + Backtracking`, but adding `bottom-up DP` in order to avoid spend lots of time on those invalid substrings multiple times (see: <https://leetcode.com/problems/word-break-ii/discuss/291579/Concise-and-clean-Java-DFS-+-Backtracking-+-DP-(pre-processing)-Solution>)

#### Mistakes I have made:

#### Review At:
-   05-31-2019
