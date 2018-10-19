### Problem  ["Minimum Size Subarray Sum"](https://leetcode.com/problems/minimum-size-subarray-sum/description/)

#### Knowledge Used:
1. two pointers
2. arrays

#### Code
- [two pointer solution](./Solution.java)

#### Complexity
- Time: `O(n)`, although there looks like a nested loop, but actually both two pointers are always monotonously increasing. Each element in the array is at most scanned twice.
- Space: `O(1)`, with no additional space used

#### Idea:
1. 一开始的思路为九章算法强化班讲的“同向双指针”模版，[参考视频](https://drive.google.com/drive/u/0/folders/18f-pe8Bl-kzTXLaYssq12TjN5YsjFFYP)的前15分钟
2. 全部是positive integer, 那么preSum一定是增长的.
3. right++ until a solution where sum >= s is reached，之后记录当前的长度，然后剔除subarray最左边的element (left++), 继续increment right pointer，而无需将right pointer回调到left + 1
4. 因为right的循环不回头，一直往前走，这两重循环是叠加关系，而不是嵌套关系，所以最终solution的time complexity是`O(n)`
5. TODO: 这道题其实还有一个binary search的思路

#### Mistakes I have made:
1. 一开始并没有想到memorize一个cumulative sum，所以会导致solution变为`O(n^2)`
2. 一开始以为nums[j]也包含在当前的subarray，而实际上nums[j]并不算在当前的subarray中，而是当前的subarray之外右边element的第一个
3. 试图去加一个early termination condition

#### Review At:
