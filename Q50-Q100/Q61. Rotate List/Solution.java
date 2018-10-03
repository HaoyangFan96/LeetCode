// Solution to Q61 "Rotate List"
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
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0 ) {
            return head;
        }
        ListNode right = null, left = head;
        int len = 1;
        for(right = head; right.next != null;) {
            right = right.next;
            len++;
        }
        int moveDist = len - 1;
        right.next = left;
        // the first for loop is completely unnecessary, see “Better Solution”
        for(int i = 0; i < k % len; i++) {
            for (int j = 0; j < moveDist; j++) {
                right = right.next;
                left = left.next;
            }
        }
        right.next = null;
        return left;
    }
}
