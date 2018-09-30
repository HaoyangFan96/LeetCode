#### Knowledge Used:
1. Dynamic Programming
2. Divide and Conquer
3. Array

#### Code
- [DP java sol](./DPSolution.java)
- [Optimized DP java sol](./OptimizedDPSolution.java)
#### Complexity
- Time: `O(n)` since we have to iterate through the whole array once anyway
- Space: `O(n)` if we are using one-dimensional dp array, which can be optimized by applying the idea of "滚动数组", leading to a `O(1)` space

#### Idea:
1. The best way to solve the problems of maximum subarray problems is to apply [Kadane Algorithm](https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm) which uses the idea of dynamic programming in it
2. 基本思路：对于given array的最终maximum subarray solution,无论为怎样，其一定包括一个最终的ending index，那么这样的话，我们就可以检查所有ending index 为0的solution的和的大小，所有ending index 为1的solution的和的大小，所有ending index 为2的solution的和的大小...所有ending index 为n的solution的和的大小，而global maximum subarray必定在这个检查过程中会被检索到，这样的话，我们即可找出最终的solution（以上的subproblem-n可以由解决之前的subproblem来解决，这个涉及到动态规划的思想）
3. 在动态规划的solution中，我们可以利用滚动数组的思想来压缩所需要的space，从而最终达到`O(1)`的space要求

#### Mistakes I have made:
1. 再没看solution的情况下，我并未想到用动态规划来解决这类问题，而这类优化问题往往是应该通过动态规划来解决

#### Review At:
