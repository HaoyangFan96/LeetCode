### Problem  ["Accounts Merge"](https://leetcode.com/problems/accounts-merge/description/)

#### Knowledge Used:
1.  DFS
2.  Union Find
3.  HashMap
4.  TreeSet

#### References:
1.  九章强化版第二节课下半部分笔记开头部分
2.  <https://www.jiuzhang.com/solution/accounts-merge/#tag-highlight-lang-java>
3.  <https://leetcode.com/problems/accounts-merge/solution/>

#### Code
-   [Union Find Java Solution](./UnionFindSolution.java)

#### Complexity
-   Time: `O(AlogA)`, where A is the sum of all account's length (i.e. A = len(accounts[0]) + len(accounts[1])...). If we used union-by-rank, this complexity improves to almost O(A)
-   Space: `O(A)`, the space used by different HashMap and union find data structure

#### Idea:
1.  九章算法强化班的思路 （参见九章算法版第二节课下半部分笔记开头部分）：
    1.  通常来说，对于这样的问题，我们往往是将owner name当作index(类似于SQL中的primary key)即一个owner name名下对应了所有的email (正向索引 forward index)  
    e.g. John : [[john1@gmail.com], [john2@gmail.com], [john3@gmail.com]]
    2.  那么反过来说我们可不可以设立一个inverted index，即每个email对应了所有与其关联的人的ID(这里我们可以使用一个小技巧将string name转化为int index, 使得union find更好处理。  
        e.g. john1@gmail.com : [[1, 2, 3]]  "1" => John "2" => "Johnson" "3" => "John" （反向索引 inverted index）
    3.  接下来我们可以将反向索引得到的index table翻转过来，变为正向索引，即我们要将每一个email对应的index union到一起
    4.  接下来我们遍历一遍input list，对于其中每一个user的index，我们在第三部生成的union find中找到其的root index，将所有的email合并到root index对应的email set中
    5.  接下来我们只要将第四步中得到的map的key index转换成对应的user name即可输出最终结果

2.  TODO：此题也可以用DFS来解决

#### Mistakes I have made:


#### Review At:
1.  11-4-2018
