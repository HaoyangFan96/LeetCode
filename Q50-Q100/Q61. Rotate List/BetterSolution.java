// A better solution than my own to Q61 "Rotate List"
// Reference: https://leetcode.com/problems/rotate-list/description/
// Date: 08-24-2018

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 /**
  * Definition for singly-linked list.
  * public class ListNode {
  *     int val;
  *     ListNode next;
  *     ListNode(int x) { val = x; }
  * }
  */
 class BetterSolution {
     public ListNode rotateRight(ListNode head, int k) {
         if (head == null || head.next == null || k <= 0) {
             return head;
         }
         ListNode tail = head;
         int len = 1;
         while(tail.next != null) {
             tail = tail.next;
             len ++;
         }
         // quick return condition
         if (k % len == 0) {
             return head;
         }
         // make the list become circular
         tail.next = head;
         // remove the unnecessary values
         k = k % len;
         // 因为是singly linkedlist， 所以相对来说反向移动并不是很好实现
         // 所以我们可以反向将tail pointer移动（len - k）distance
         // 在那个时刻，tail所指向的node即为tail node，而它的下一个node即为head
         for (int i = 0; i < len - k; i++) {
             tail = tail.next;
         }
         // at this point, tail is pointing to the actual tail node and next
         // node after the tail node will be the actual head
         head = tail.next;
         tail.next = null;
         return head;
     }
 }
