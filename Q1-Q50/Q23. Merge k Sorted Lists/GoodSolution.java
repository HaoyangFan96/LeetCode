// Solution to Q23 "Merge k Sorted Lists"
// Reference: https://leetcode.com/problems/merge-k-sorted-lists/solution/
// This is an improved solution, compared to my initial solution "Solution.java"
// Date: 08-20-2018

// NOTE: instead of using Collections.min which has a time complexity of O(n) each
// time to find the minimum node, why not maintain all nodes in the priority queue
// so that the enqueue and dequeue take log(n) and find-min take only constant time
class GoodSolution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        else if (lists.length == 1) {
            return lists[0];
        }
        // define the comparator using lambda expression
        Comparator<ListNode> comp = (l1, l2) -> l1.val - l2.val;
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(comp);
        ListNode head = null, curr = null;
        // put head of each linkedlist into the priority queue (log(k)),
        // where k is the number of linkedlist
        for (ListNode l : lists) {
            if (l != null) {
                // enqueue
                pq.add(l);
            }
        }
        while (pq.size() > 0) {
            // find-min O(1)
            ListNode min = pq.poll();
            if (curr == null) {
                head = min;
                curr = min;
            }
            else {
                curr.next = min;
                curr = min;
            }
            // see if the ListNode that just dequeue from the pq still has child
            if (min.next != null) {
                // re-enqueue
                pq.add(min.next);
            }
        }
        return head;
    }
}
