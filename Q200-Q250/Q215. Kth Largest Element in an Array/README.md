### Problem  ["Kth Largest Element in an Array"](https://leetcode.com/problems/kth-largest-element-in-an-array/description/)

#### Knowledge Used:
1.  Heap
2.  QuickSelect
3.  Array

#### Code
-   [java heap solution](./HeapSolution.java)

#### Complexity
-   For heap solution:
    -   Time: `O(nlogk)`, where n is the size of input array and k is the input value, mostly as a result of pushing element into the heap
    -   Space: `O(k)`, we need to allocate a heap of capacity at least k
-   For QuickSelect solution:
    -   Time: ``
    -   Space: ``

#### Idea:
1.  此题有两种主流的写法：QuickSelect 与 Heap，目前我只给出了Heap的解法
2.  关于QuickSelect，具体可以参考:
    1.  <https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60309/C%2B%2B-PartitionMax-Heappriority_queuemultiset/190413>
    2.  <https://github.com/awangdev/LintCode/blob/master/Java/Kth%20Largest%20Element%20in%20an%20Array.java>

3.  关于Heap solution：其思路为针对kth largest element，维护一个size总为k的min heap，是的，没错，min heap而不是max heap，在这个min heap中存储array中较大的k个数。在遍历完array之后，将min heap中的topmost element poll出来作为结果即可
4.  相似的思路，kth min element可参考：<https://www.lintcode.com/problem/kth-smallest-numbers-in-unsorted-array/>

#### Mistakes I have made:
1.  Try to use max heap instead of min heap for the heap solution, understanding the problem in a wrong way

#### Review At:
