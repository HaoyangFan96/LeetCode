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
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} x
 * @return {ListNode}
 */
var partition = function(head, x) {
    if (!head || head.length === 1) {
        return head;
    }
    let prev = null, p = prev;
    let after = null, a = after;
    // 开始遍历一遍这个list
    for(let curr = head; curr != null; curr = curr.next) {
        // if current node's value is less than x
        if (curr.val < x) {
            if (prev) {
                p.next = curr;
                p = curr;
            }
            else {
                prev = curr;
                p = prev;
            }
        }
        else {
            if (after) {
                a.next = curr;
                a = curr;
            }
            else {
                after = curr;
                a = after;
            }
        }
    }
    if (a) {
        a.next = null;
    }
    if (p) {
        p.next = after;
        return prev;
    }
    else {
        return after;
    }
};
