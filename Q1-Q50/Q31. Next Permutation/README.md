### Problem  ["Next Permutation"](https://leetcode.com/problems/next-permutation/description/)

#### Knowledge Used:
1.  Array

#### Code
-   [Optimized O(n) solution in Java](./BetterSolution.java)
-   [Initial Priority Queue solution](./OriginalSolution.java)

#### Complexity
-   Time: `O(n)` in worst case, only two scans of the whole array are needed.
-   Space: `O(1)`, no extra space is used. In place replacements are done

#### Idea:
1.  ![](http://4.bp.blogspot.com/-4zN0u5JG0vs/UN0xPEkP5yI/AAAAAAAAG9Q/O48ZfwB1i_c/s640/Picture4.png)

#### Mistakes I have made:
-   一开始这道题我是用priority queue来做的，而实际上有更好的solution（O(n)的复杂度)，参见[Solution](https://leetcode.com/problems/next-permutation/solution/)里的思路,多加以理解这个思路

-   **Do not repeat yourself**! 相见`BetterSolution.java`中现有的code与被commented out的code的对比，明显前者更为工整简介容易理解

#### Review At:
-   05-14-2019
-   05-02-2019
-   08-29-2018
