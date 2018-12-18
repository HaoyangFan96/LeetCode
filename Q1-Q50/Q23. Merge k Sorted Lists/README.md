### Problem  ["Merge k Sorted Lists"](https://leetcode.com/problems/merge-k-sorted-lists/description/)

#### Knowledge Used:
1.  Divide and Conquer
2.  Linked List
3.  Heap

#### References:
1.  <https://leetcode.com/problems/merge-k-sorted-lists/solution/>

#### Code
-   [Divide and Conquer Java solution](./DivideAndConquerSolution.java)
-   [PriorityQueue Java solution](./PriorityQueueSolution.java)

#### Complexity
-   Divide and Conquer:
    -   Time: `O(Nlogk)` where `N` is total number of nodes and `k` is the number of input sorted linked list
    -   Space: `O(1)` unlike merge on array, merge on linked list is in-place without using auxiliary space
-   Priority Queue:
    -   Time: `O(Nlogk)` as the comparison cost will be reduced to `O(logk)` for every pop and insertion to priority queue. But finding the node with the smallest value just costs `O(1)` time, and there are total N nodes in the final linked list
    -   Space: `O(k)` as initially all heads of `k` sorted linked list will be put into the priority queue

#### Idea:
1.  <https://leetcode.com/problems/merge-k-sorted-lists/solution/>: see the explanation of priority queue and divide-and-conquer solution

#### Mistakes I have made:
1.

#### Review At:
1.  12-18-2018
