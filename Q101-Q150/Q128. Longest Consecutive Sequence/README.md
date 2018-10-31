### Problem 128 ["Longest Consecutive Sequence"](https://leetcode.com/problems/longest-consecutive-sequence/description/)

#### Knowledge Used:
1.  Union Find
2.  HashMap
3.  HashSet
4.  Array

#### Code
-   [HashMap Java solution](./HashMapSolution.java)
-   [HashSet Java solution](./HashSetSolution.java)
-   [UnionFind Java solution](./UnionFindSolution.java)

#### References:
1.  <https://leetcode.com/problems/longest-consecutive-sequence/solution/>
2.  <https://github.com/awangdev/LintCode/blob/master/Java/Longest%20Consecutive%20Sequence.java>
3.  <https://mnmunknown.gitbooks.io/algorithm-notes/content/66,_union-findff0c_bing_cha_ji_ji_chu.html>

#### Complexity
-   HashSet/HashMap:
    -   Time: `O(n)`, since each element in the array will be visited at most twice, and operations of HashSet/HashSet used are all `O(1)` time
    -   Space: `O(n)` since we need to allocate space for HashSet/HashMap
-   Union Find:
    -   Time: `O(n)`, if we are using optimized union find implementation, the `find` and `union` both take almost constant time. Because we have to iterate through the array, the overall time complexity will be `O(n)`
    -   Space: `O(n)` since we need to allocate space for union find

#### Idea:
1.  此题可以用HashSet或者HashMap来记录数组中元素的信息，这样之后在我们遍历每一个元素的时候，我们可以得知与其相邻的元素是否存在，如果存在的话，我们可以继续查询其他相邻的元素。在这个过程中，我们通过记录查询到的相邻元素的数量即可得知每一个存在的consequence sequence的长度
2.  此题也可以用Union Find，将相邻元素Union起来，最后返回最大connected component的size

#### Mistakes I have made:


#### Review At:
1.  10-30-2018
