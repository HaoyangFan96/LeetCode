// Solution to Q21 "Merge Two Sorted Lists"
// Reference: https://leetcode.com/problems/merge-two-sorted-lists/description/
// Date: 08/28/2018

/*
Merge two sorted linked lists and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists.
Example:
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/

/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function(l1, l2) {
    if (!l1) {
        return l2;
    }
    else if (!l2) {
        return l1;
    }

    let dummy = new ListNode(0), curr = dummy;
    while(l1 && l2) {
        if (l1.val <= l2.val) {
            curr.next = l1;
            curr = l1;
            l1 = l1.next;
        }
        else {
            curr.next = l2;
            curr = l2;
            l2 = l2.next;
        }
    }
    if (l1) {
        curr.next = l1;
    }
    else if (l2) {
        curr.next = l2;
    }
    return dummy.next;
};
