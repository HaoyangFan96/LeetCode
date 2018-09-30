#### Knowledge Used:
1. Two pointers
2. Array

#### Code
- [java sol](./Solution.java)

#### Complexity
- Time: `O(n^2)`, because solution used nested loop
- Space: `O(n)`, because we will need to maintain a list to hold all possible combinations

#### Idea:
1. 注意一定要先排序，升序降序皆可，现有的solution是按升序来做的
2. 对于 num[i]，寻找另外两个数时，只要从i+1到结尾的窗口内开始找就可以了，这里分为以下三种情况
    1. 当前三数之和为0，则记录下这三个数，但这里还没完事，之后两边继续向里走（压缩当前的窗口），找是否还有其他合适的另两个数
    2. 当前三数之和大于0，窗口右边朝左压缩，来减少三数之和
    3. 当前三数之和小于0，窗口左边朝右压缩，来增加三数之和
3. 去掉duplicate: check用过的同样的数字，都跳掉，不需要用同样的数字再计算一边已有结果

#### Mistakes I have made:
1. When find the answer, we should not just stop at here and try the next number. For the current number,
the given array may have multiple two values that sum up to 0. So need to update both left and right (shrink the window maintained by the two pointers) to find other possible combinations  
[code](./Solution.java#L74)
2. Forget to check duplicates values. Always be careful to not count duplicate values multiple times

#### Review At:
1. 09-29-2018
