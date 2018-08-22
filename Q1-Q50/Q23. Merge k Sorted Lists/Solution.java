// My Own solution for Q23 "Merge k Sorted Lists"
// Reference: https://leetcode.com/problems/merge-k-sorted-lists/description/
// Date: 08-20-2018

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        else if (lists.length == 1) {
            return lists[0];
        }
        List<ListNode> dummies = new ArrayList<ListNode>();
        ListNode start = new ListNode(0);
        ListNode curr = start;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                dummies.add(lists[i]);
            }
        }
        while(dummies.size() > 0) {
            // using lambda expression introduced since Java 8
            ListNode m = Collections.min(dummies, (left, right) -> left.val - right.val);
            int idx = dummies.indexOf(m);
            curr.next = new ListNode(m.val);
            curr = curr.next;
            if (m.next == null) {
                dummies.remove(idx);
            }
            else {
                dummies.set(idx, m.next);
            }
        }
        return start.next;
    }
}
