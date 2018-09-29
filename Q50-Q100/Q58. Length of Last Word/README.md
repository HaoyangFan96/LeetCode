#### Knowledge Used:
1. String

#### Code
- [js sol](./Solution.js)

#### Complexity
- Time: `O(n)`, in worst case we have to scan the entire string
- Space: `O(1)`, no additional space used

#### Idea:
1. 这道题并未涉及到很复杂的algorithm，trim掉头尾的whitespace之后，从后向前scan string，直到遇到第一个whitespace时停下，返回在此之前经历的non whitespace的字符的数量

#### Mistakes I have made:
1.

#### Review At:
