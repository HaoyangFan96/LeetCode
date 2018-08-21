// solution to Q19. "Remove Nth Node From End of List"
// Reference: https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
// Date: 08-20-2018

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class OwnSolution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // NOTE: in this type of question, typcially there is a need for a dummy node
        // to deal with the case that the list only has one node which is the head
        // and it is going to get removed/other operations
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        countFromEnd(dummy.next, dummy, n);
        return dummy.next;
    }

    private int countFromEnd(ListNode curr, ListNode prev, int n) {
        // check if the current node is the last ListNode
        if (curr.next == null) {
            if (n == 1) {
                prev.next = null;
            }
            return 1;
        }
        int count = 1 + countFromEnd(curr.next, curr, n);
        if (count == n) {
            prev.next = curr.next;
        }
        return count;
    }
}
