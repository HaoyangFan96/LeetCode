#### Knowledge Used:
1. Array

#### Code
- [java solution](./Solution.java)

#### Complexity
- Time: `O(n^2)`, because of using nested for-loop
- Space: `O(1)`, no additional space used

#### Idea:
1. 顺时针的思路：先将matrix沿着左上到右下的对角线对折，然后再将结果沿着竖着的中线对折即可 （相似的方式有很多）
2. 逆时针的思路：先将matrix沿着右上到左下的对角线对折，然后再将结果沿着横着的中线对折即可
3. 可能的优化：在一个nested for-loop中完成所有swap，而不是两个nested for-loop

#### Mistakes I have made:
1. 第一个for-loop中的j应该从i+1开始，这样保证对角线外的点只swap一次，否则swap两次等于没swap

#### Review At:
