/** 
* My initial solution to Q2: Add Two Numbers without viewing any solutions or hints
* @since 2018-06-24
* @version 1.0
*/
class InitialSol {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        int prev_overflow = 0;
        ListNode head = null;
        ListNode tail = null;
        while(l1 != null && l2 != null){   
            ListNode temp = new ListNode((l1.val + l2.val + prev_overflow) % 10);
            if (tail != null){
                tail.next = temp;
                tail = temp;
            }
            else {
                head = temp;
                tail = temp;
            }
            prev_overflow = (l1.val + l2.val + prev_overflow) / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (prev_overflow != 0){
            ListNode temp = null;
            if (l1 == null && l2 == null){
                tail.next = new ListNode(1);
                break;
            }
            else if (l1 != null){
                temp = new ListNode((l1.val + prev_overflow) % 10);
                prev_overflow = (l1.val + prev_overflow) / 10;
                l1 = l1.next;
            }
            else if (l2 != null){
                temp = new ListNode((l2.val + prev_overflow) % 10);
                prev_overflow = (l2.val + prev_overflow) / 10;
                l2 = l2.next;
            }
            tail.next = temp;
            tail = temp;
        }
        if (l1 != null){
            tail.next = l1;
        }
        else if (l2 != null){
            tail.next = l2;
        }
        return head;
    }
}