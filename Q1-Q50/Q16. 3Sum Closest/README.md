#### Knowledge Used:
1. Two pointers
2. Array

#### Code
- [java sol](./Solution.java)

#### Complexity
- Time: `O(n^2)`, because of the use of nested loop
- Space: `O(1)`, no additional space used

#### Idea:
1. 3sum问题的变种。一样的遍历每个数，对剩余数组进行双指针扫描。区别仅仅在于当：  
sum = A[left] + A[right]  
(1) sum = target时直接返回  
(2) sum != target时，在相应移动left/right指针之前，先计算abs(sum-target)的值，并更新结果。

#### Mistakes I have made:
1.

#### Review At:
