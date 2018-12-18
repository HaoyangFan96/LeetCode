// Java solution to Q23 "Merge k Sorted Lists"
// Reference: https://leetcode.com/problems/merge-k-sorted-lists/solution/
// Date: 11-24-2018

/**
 * Thoughts:
 *
 * The idea is very similar to the merge sort:
 * Divide current problem (sort k lists) into subproblems of sorting first k/2 lists
 * and sorting last k/2 lists ... recursively solve for the subproblems, and then
 * merge the result back into one single sorted linked list
 *
 * Time:O(Nlogk) where N is total number of nodes and k is the number of linked lists.
 * We can merge two sorted linked list in O(n)O(n) time where nn is the total number of nodes in two lists
 * Sum up the merge process and we can get O(Nlogk)
 *
 * Space: O(1)
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Review Divide and Conquer solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 12-18-2018
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return sort(lists, 0, lists.length - 1);
    }

    // helper method to perform the divide and conquer
    private ListNode sort(ListNode[] lists, int start, int end) {
        if (start == end)   return lists[start]; // subproblem only has 1 list

        // DIVIDE
        int mid = start + (end - start) / 2;
        ListNode l = sort(lists, start, mid);
        ListNode r = sort(lists, mid + 1, end);

        // MERGE
        return merge2List(l, r);
    }

    private ListNode merge2List(ListNode l, ListNode r) {
        if (l == null)  return r;
        if (r == null)  return l;
        ListNode head = null, curr = null;
        while (l != null && r != null) {
            ListNode smaller = null;
            if (l.val <= r.val) {
                smaller = l;
                l = l.next;
            } else {
                smaller = r;
                r = r.next;
            }
            if (head == null) {
                head = smaller;
                curr = smaller;
            } else {
                curr.next = smaller;
                curr = curr.next;
            }
        }
        if (l != null) curr.next = l;
        if (r != null) curr.next = r;
        return head;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial Divide and Conquer solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-24-2018
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return DivideAndConquer(lists, 0, lists.length - 1);
    }

    private ListNode DivideAndConquer(ListNode[] lists, int start, int end) {
        // base case 1: start == end, only 1 list with no need to merge
        if (start == end)   return lists[start];
        // base case 2: start + 1 == end, just merge that two lists and return the result
        if (start + 1 == end)   return merge2List(lists[start], lists[end]);

        int mid = start + (end - start) / 2;
        // Divide
        ListNode l1 = DivideAndConquer(lists, start, mid);
        ListNode l2 = DivideAndConquer(lists, mid + 1, end);

        // Conquer (merge)
        return merge2List(l1, l2);
    }

    private ListNode merge2List(ListNode l1, ListNode l2) {
        // NOTE: be careful about the corner case where input list can be null
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // no need to use dummy node
        ListNode curr = null, head = null;
        while (l1 != null && l2 != null) {
            ListNode smaller = null;
            if (l1.val < l2.val) {
                smaller = l1;
                l1 = l1.next;
            } else {
                smaller = l2;
                l2 = l2.next;
            }
            if (head == null) {
                head = smaller;
                curr = smaller;
            } else {
                curr.next = smaller;
                curr = smaller;
            }
        }

        // in case one of lists has some remainings
        if (l1 != null) curr.next = l1;
        if (l2 != null) curr.next = l2;
        return head;
    }
}
