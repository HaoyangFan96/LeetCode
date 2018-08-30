#### Knowledge used:
1. two pointers
2. multiple Linked Lists

[code](./Solution.js)

#### Complexity:
- Space: `O(1)`, no additional space used
- Time: `O(n)`, only iterate through the linked list once


#### 关于这道题提供的Linked List:
- linked list 不能像partition array一样从两边遍历
- 把小于value的加在前半段, 把 >= value的加在后半段
- 做法很普通: 建造两个list, midTail pointer, post pointer
- 把满足条件（<x, >=x）的数字分别放到两个list里面
- 记得用dummyNode track head
- 最终midTail.next = post链接起来

#### Mistakes I have made:
1.
```
if (a) {
    a.next = null;
}
```
 注意一定要讲after（右端linked list）的最后一个node的next设定成null，否则linked list会
 一直循环链接下去而不会中断, 造成runtime exception
