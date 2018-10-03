Knowledge used: two pointers (for my own initial solution) while the better solution is in the similar idea but does not need those two pointer, see [BetterSolution.java](BetterSolution.java)

There is also a better two pointer solution by maintaining a slow pointer and a fast pointer, see [BetterTwoPointersSolution.java](BetterTwoPointersSolution.java)

NOTES: 需要注意的一点时，当rotate list时，真正有意义的移动距离总是为实际给的移动距离 % list length
举例：当list长度为3的时候，向右rotate这个list 1000次与向右rotate这个list 1次取得的效果是一样的，注意到这点以后，可以提高不少的效率
