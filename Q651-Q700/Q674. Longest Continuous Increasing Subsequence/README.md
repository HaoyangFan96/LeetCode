### Problem  ["Longest Continuous Increasing Subsequence"](https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/)

#### Knowledge Used:
1.  Array

#### References:
1.  <https://github.com/mintycc/OnlineJudge-Solutions/blob/master/Leetcode/674_Longest_Continuous_Increasing_Subsequence.java>
2.  <https://leetcode.com/problems/longest-continuous-increasing-subsequence/solution/>

#### Code
-   [Array Java Solution](./Solution.java)

#### Complexity
-   Time: `O(n)` since we only iterate through the array once
-   Space: `O(1)` with no additional space used

#### Idea:
1.  一开始我以为这道题是用同向双指针来维护一个滑动窗口，实际上发现根本不需要那么复杂:  
遍历一遍数组，在过程总当遇到的value处于增长状态的时候，增加当前increasing subsequence记录的长度，打擂台。当遇到non-increasing元素的时候，重置长度为1即可
2.  此题有两个follow-up [source](https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=452048&extra=&page=1):
    1.  允许跳过一个字母，输出连续增subarray最长长度，参见[code](./GapOne.java)
    2.  允许跳过k个字母，输出连续增subarray最长长度，参见[code](./GapK.java)

#### Mistakes I have made:
1.

#### Review At:
