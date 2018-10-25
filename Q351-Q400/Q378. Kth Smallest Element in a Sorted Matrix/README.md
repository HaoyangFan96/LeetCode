### Problem  ["Kth Smallest Element in a Sorted Matrix"](https://leetcode.com/submissions/detail/185197145/)

#### Knowledge Used:
1.  Heap
2.  Binary Search
3.  Array

#### Code
-   [Java Heap Solution](./HeapSolution.java)

#### Complexity
-   Heap solution:
    -   Time: `O(klogmin(k,m,n))` where k is input value, m is number of rows of the input matrix, n is number of columns of the input matrix, for reason, see lecture notes of first lecture last half at about 29:00
    -   Space: `O(m) / O(n)`

#### Idea:
1.  此题有两种思路：1）Heap 2) Binary Search (TBD), 其中Binary Search的解法可能更有效一些
2.  对于Heap的做法，我的大概理解（在参看了一些别人的code之后）为：生成一个min heap，后将第一行或第一列的全部element加入到heap中，之后在poll out头k-1个最小的element
3.  非常需要tricky的部分：在poll out头k-1个最小element的时候，不能只poll，因为你无法保证头k-1个最小的element一定全部集中在第一行或第一列，正确的做法为一边poll一边将当前poll出来的element沿着所在行/列的下一个element加入到heap中, 此时会有两种情况：
    1.  这个element确实是头k-1个最小element之一，那么之后它也同样会被poll出来，符合题意
    2.  这个element不是头k-1个最小的element之一，那么即时它被加入到queue中也并不会影响我们的逻辑

#### Mistakes I have made:
1.  Use max-heap instead of min-heap for this problem

#### Review At:
