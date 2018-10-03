// JS solution to Q61 "Rotate List"
// Reference: https://leetcode.com/problems/rotate-list/description/
// Date: 10-02-2018

/**
 * Thoughts:
 * 1. Use two pointers, one quick pointer and one slow pointer to get
 * to the node which has n distance to the end. By using this way, we don'
 * have to scan the whole array and track back to find the desire ending node
 * after the move
 * 2. Move k places is effectively same as move (k % linkedlist length) places
 */

/*
Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-02-2018
 * Two pointer solution in js
 */

 /**
  * Definition for singly-linked list.
  * function ListNode(val) {
  *     this.val = val;
  *     this.next = null;
  * }
  */
 /**
  * @param {ListNode} head
  * @param {number} k
  * @return {ListNode}
  */
 var rotateRight = function(head, k) {
     if (head == null || head.next == null) {
         return head;
     }
     // get the length of linked list
     let len = 0;
     // actually we don't have to use dummy node for this problem
     // let dummy = new ListNode(0);
     // dummy.next = head;
     for (let i = head; i != null; i = i.next) {
         len ++;
     }
     // get the "real" moving distance
     k %= len;
     if (k === 0) {
         return head;
     }
     // use of slow and faster pointer
     let slow = head, quick = head;
     for (let i = 0; i < k; i++) {
         quick = quick.next;
     }
     while (quick.next != null) {
         quick = quick.next;
         slow = slow.next;
     }
     quick.next = head;
     head = slow.next;
     slow.next = null;
     return head;
 };
