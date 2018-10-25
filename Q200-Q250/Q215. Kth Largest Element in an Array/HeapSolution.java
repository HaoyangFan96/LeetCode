// Java solution to Q215 "Kth Largest Element in an Array"
// Reference: https://leetcode.com/problems/kth-largest-element-in-an-array/description/
// Date: 10-24-2018

/**
 * Thoughts:
 * 根据九章算法提高班的思路：
 * 1. check if the given array is sorted or unsorted. In this case, it is unsorted
 * 2. to find kth largest element, we can use "minHeap" instead of "maxHeap".
 *    Same idea, when the question is to find kth smallest element, we use "maxHeap"
 *    instead of "minHeap"
 * 3. we must maintain the size of min heap to be k, and it must contains the larger
 *    k elements of the input array. 这一步最为关键
 * 4. once we've done with iterating the input array and the k-size min heap
 *    has been constructed. We just poll the topmost one from the heap, which
 *    is the kth largest element of the input array
 *
 *  NOTE: 这道题最经典的解法还是QuickSort中的QuickSelect，利用partition函数来解决
 */

/*
Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-24-2018
 * Initial maxHeap solution in Java
 */

 class Solution {
     public int findKthLargest(int[] nums, int k) {
         // initialize the min heap
         Queue<Integer> minHeap = new PriorityQueue<>(k);
         // iterate through the array
         for (int num : nums) {
             // NOTE: the heap will always contain "larger" k elements of array
             if (minHeap.size() < k || num > minHeap.peek()) {
                 minHeap.offer(num);
             }
             if (minHeap.size() > k) {
                 minHeap.poll();
             }
         }
         return minHeap.poll();
     }
 }
