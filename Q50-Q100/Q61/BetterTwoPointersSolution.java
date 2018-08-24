// A better solution using two pointers than my own to Q61 "Rotate List"
// Reference: https://leetcode.com/problems/rotate-list/description/
// Date: 08-24-2018

// Key: using fasting and slowing pointer to get the tail node, and another
// target node, respectively
// move the list after the (l-n%l )th node to the front to finish the rotation
// Ex: {1,2,3} k=2 Move the list after the 1st node to the front
// Ex: {1,2,3} k=5, In this case Move the list after (3-5%3=1)st node
// to the front.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class BetterTwoPointersSolution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head, slow = dummy;
        int len = 1;
        while(fast.next != null) {
            fast = fast.next;
            len ++;
        }
        // quick return condition
        if (k % len == 0) {
            return head;
        }
        for(int i = 0; i < len - k % len; i++) {
            slow = slow.next;
        }
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;
        return dummy.next;
    }
}
