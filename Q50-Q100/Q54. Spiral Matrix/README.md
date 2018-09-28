#### Knowledge Used:
1. Array

#### Code
- [sol1 java](./Solution.java)
- [sol2 java](./Solution2.java)

#### Complexity
- Time: `O(n)` 因为我们需要把matrix中的每一个element全部遍历一遍
- Space: `O(n)`
    - 首先我们需要`O(n)`space来存放结果(maintain a ArrayList to hold all elements added in spiral order so far)
    - 对于[第二钟解法](./Solution2.java)我们还需要一个与original matrix同样大小的matrix用来记录original matrix中的每一个element是否已经访问过

#### Idea:
1. https://leetcode.com/problems/spiral-matrix/solution/ 官方的solution已经讲解的很清楚了
2. 我一开始自己的解法是[layer by layer](./Solution.java)而后来发现其实[simulation](./Solution2.java)的思路要更容易理解并记住
3. 这道题并不难，只是很难一次性bug free写对，有很多需要注意的细节

#### Mistakes I have made:
1. [Solution 1](./Solution.java)一开始在走完top，rightmost两个layer之后并没有检查`top`与`bottom`，`left`与`right`的大小关系
导致某些cases中如果`top`与`bottom`或者`left`与`right`是指的同一layer的话，会把其中的element重复添加  
Should have:
    ```
    ...
    if (top > bottom || left > right) {
        break;
    }
    ...
    ```
2. [Solution 2](./Solution2.java)中忘记更新[currentRow](./Solution2.java#L69)和[currentCol](./Solution2.java#L70)
3. [Solution 2](./Solution2.java)中忘记检查`nextRow`和`nextCol`是否大于等于0,
[see](./Solution2.java#L64)
#### Review At:
