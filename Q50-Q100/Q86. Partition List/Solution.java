// Solution to Q86 "Parition List"
// Reference: https://leetcode.com/problems/partition-list/description/
// Date: 08-28-2018

/*
Given a linked list and a value x, partition it such that all nodes less than x
come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode d1 = new ListNode(0), l1 = d1;
        ListNode d2 = new ListNode(0), l2 = d2;
        // iterate through the linked list
        for(ListNode i = head; i != null; i = i.next) {
            int curr = i.val;
            // if current node's value is less than target
            if (curr < x) {
                l1.next = i;
                l1 = i;
            }
            else {
                l2.next = i;
                l2 = i;
            }
        }
        // connect two linked list together by making the next of l1 to point
        // to the start of l2
        l1.next = d2.next;
        l2.next = null;
        return d1.next;
    }
}
