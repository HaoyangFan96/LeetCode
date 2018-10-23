### Problem  ["Longest Substring Without Repeating Characters"](https://leetcode.com/problems/longest-substring-without-repeating-characters/description/)

#### Knowledge Used:
1. two pointers
2. HashMap
3. HashSet
4. string

#### Code
- [java HashSet solution](./Solution.java)
- [java HashMap solution](./OptimizedSolution.java)

#### Complexity
- Time: `O(n)` since each character in the string will be at most visited two times
- Space: `O(m)` if use array as the "hashMap" for the HashMap solution, where m is the size of the charset   `O(min(m,n))` if use java's own HashMap implementation where
m is the size of the charset and n is the size of input string


#### Idea:
1. 这道题需要通过two pointers来maintain一个sliding window，属于同向双指针类问题，右端的指针向右移动来扩展window, 当遇到重复的字符时，左端的指针向右前进来缩小window，直到跳过之前重复的字符时当前的window不再有重复
2. 利用HashSet可以存储之前扫描过的字符的信息，来用于判断当前的window是否有重复的字符
3. 可以利用HashMap来进一步优化，key-value为字符和其在字符串中的index，这样一旦遇到重复字符，可以使左端指针一次性跳到之前重复字符的下一个字符处，来提高效率
4. 同向双指针问题可以套用九章算法强化版第一节课上半部分给的模版来解

#### Mistakes I have made:
1. forget to check the validity of pointer positions, e.g. if j < n
2. forget to store the information of scanned character into HashSet or HashMap

#### Review At:
1. 10-19-2018
2. 10-23-2018
