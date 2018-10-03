#### Knowledge Used:
1. Array
2. HashMap
3. BinarySearch

#### Code
- [java sol](./Solution.java)
- [js sol](./Solution.js)

#### Complexity
- Time: `O(n)` need to iterate through the array at least once
- Space: `O(n)` use a HashMap to store the previous scanned values and indices. In the worst cases, nearly the entire array's elements will be stored in that HashMap

#### Idea:
1. 从左往右扫描一遍，然后将数及坐标，存到map中。然后再扫描一遍即可。时间复杂度O(n)
2. 此题另外有一种binary search的解法，别忘到时了解一下

#### Mistakes I have made:
1.

#### Review At:
