// Solution for Question 19 "Remove Nth Node From End of List"

// good strategy of solving this question by maintaining a fast-moving pointer
// and a slow-moving pointer and keep the gap between the fast-moving pointer
// and the slow-moving pointer to be always n
// Reference: https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/8804/Simple-Java-solution-in-one-pass

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class GoodSolution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }
        // make a dummy start node whose next pointer pointing to the head
        ListNode start = new ListNode(0);
        start.next = head;
        // a fast-moving node pointer and a slow-moving node pointer
        ListNode fast = start, slow = start;
        // move fast first, to make a gap of n
        // NOTE: why n + 1, because we want the slower pointer to stop before the
        // the nth node from the end (which is the previous node of the nth node)
        // so that it is easy for us to manipulate the pointers
        for(int i = 1; i <= n + 1; i++) {
            // in case n is greater than the number of nodes in the list
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }
        // move both fast-moving and slow-moving pointers
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // when the loop above ends, we know that slow stop at the node that is
        // previous to the node which we try to remove(because we move fast by
        // n+1 more turns not n turns)
        slow.next = slow.next.next;
        return start.next;
    }
}
