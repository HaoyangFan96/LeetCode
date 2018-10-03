#### Knowledge Used:
1. Two pointers
2. Linked List

#### Code
- [java sol 1](./BetterSolution.java)
- [java sol 2](./BetterTwoPointersSolution.java)
- [js sol](./Solution.js) with idea the same as the one above

#### Complexity
- Time: `O(n)`, at least iterate through the entire array once no matter which way used to solve this problem
- Space: `O(1)`, with no additional space used

#### Idea:
1. Knowledge used: two pointers (for my own initial solution) while the better solution is in the similar idea but does not need those two pointer, see [BetterSolution.java](BetterSolution.java)  
 There is also a better two pointer solution by maintaining a slow pointer and a fast pointer, see [BetterTwoPointersSolution.java](BetterTwoPointersSolution.java)
2. 需要注意的一点时，当rotate list时，真正有意义的移动距离总是为实际给的移动距离 % list length
举例：当list长度为3的时候，向右rotate这个list 1000次与向右rotate这个list 1次取得的效果是一样的，注意到这点以后，可以提高不少的效率
3. 解法1: 首先从head开始跑，直到最后一个节点，这时可以得出链表长度len。然后将尾指针指向头指针，将整个圈连起来，接着往前跑len – k%len，从这里断开，就是要求的结果了
4. 解法2: 用快慢pointer的方法，快pointer指向tail node, 满pointer指向shift完成后新的tail node，在此处断开:
    1. 将原来tail node的next指向head node
    2. head pointer改指为慢pointer的next
    3. 将慢pointer的next设为null

#### Mistakes I have made:

#### Review At:
1. 10-02-2018
