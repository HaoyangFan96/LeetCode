#### Knowledge Used:
1. Priority Queue (heap)
2. Sort
3. Array

#### Code
- [priority queue java sol](./PriorityQueueSolution.java)
- [brute force js sol](./Solution.js)

#### Complexity
- Time: `O(nlogn)`
    1. Sorting of the array that takes `O(nlogn)` considering that the array consists of n elements.
    2. Since we have the min-heap, in the worst case, all n meetings will collide with each other. In any case we have n `add` operations on the heap. In the worst case we will have n `extract-min` operations as well. Overall complexity being `(nlogn)` since extract-min operation on a heap takes `O(logn)`
- Space: `O(n)` because we construct the min-heap and that can contain n elements in the worst case as described above in the time complexity section. Hence, the space complexity is `O(n)`.



#### Idea:
1. https://leetcode.com/problems/meeting-rooms-ii/solution/ gives awesome explanation. For now I only implement the first solution
2. 利用PriorityQueue来追踪所有的房间，顺序为房间的meeting的end time，每当有一个新的meeting，我们去查看结束时间最早的房间(topmost element of the heap)
    - 如果那个房间的meeting在此时已经结束，那么我们即可以使用这个房间：我们更新这个房间的meeting信息为当前的meeting，然后再把这个房间重新添加到PriorityQueue中
    - 如果这个房间的meeting并未结束，那么目前并没有可用的房间（因为我们检查的这个房间本身就是结束时间最早的那个，那么我们只得添加一个新的房间来容纳当前的meeting，并把这个房间添加到PriorityQueue中去

#### Mistakes I have made:
1. Forget to [sort](./PriorityQueueSolution.java#L51) the intervals by their start time before proceeding to the next step


#### Review At:
