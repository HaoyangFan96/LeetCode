Knowledge: two pointer

思路来源：
https://github.com/awangdev/LintCode/blob/master/Java/Sort%20Colors.java#L16

**这个思路并不适用与3个value以上的情况，consider to use partition in quicksort instead**

这道题的思路挺巧的，也挺有意思的，有0，1，2三个class，我们要将他们按顺序排列在一起

也就是说如果input是
```
[1,0,2,1,0,1,2]
```
那么output应该就是
```
[0,0,1,1,1,2,2]
```
思路是这样的：
双指针问题，在最左端和最右端分别创建一个指针,各自对应着0，2将被swap到的位置，然后开始遍历array by maintaining a counter

1. counter遇到1，什么也不用做，counter++
2. counter遇到0，将当前值与left pointer对应值交换，counter++
3. counter遇到2，将当前值与right pointer对应值交换，⚠️注意这一步不要increment counter，因为我们不知道交换过来的值是什么，所以需要重新再检查一次交换完以后的这个index
而在2中，我们则不担心，因为换过来的一定是1或者0（如果之前路径上有2的话也早就被换到右边了）所以我们不需要再次检查这个index

whenever counter > right, terminal the loop

[my code here](./Solution.java)
