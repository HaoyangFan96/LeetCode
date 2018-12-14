#### Knowledge Used:
1.  Math
2.  Binary Search

#### Code
-   [Recursive Java Solution](./RecursiveSolution.java)
-   [Iterative Java Solution](./IterativeSolution.java)

#### Complexity
-   Time: `O(logn)` for both iterative solution and non-iterative solution
because we are doing something that is similar to binary search
-   Space: `O(logn)` for recursive solution because of its use of stack space,
`O(1)` for iterative solution

#### Idea:
1.  具体思想，pseudo code详见: <https://en.wikipedia.org/wiki/Exponentiation_by_squaring>
2.  Iterative Solution的具体思路：<https://leetcode.com/problems/powx-n/solution/>
3.  Binary Search 思想的一种体现与应用，并不一定局限于sorted array

#### Mistakes I have made:
1.  本来想用 >> 1 来表示除以2，可并没想到 -1 >> 1 居然还是 -1
2.  不要将同一个function call执行两次，因为这样会再stack上堆积大量的frame，而我们应该使用
memorization来记住结果。详见 RecursiveSolution中的fail case与ac case的比较

#### Review At:
