Â### Problem  ["Longest Substring with At Most K Distinct Characters"](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/)

#### Knowledge Used:
1. two pointers
2. HashMap
3. string

#### Code
- [java array solution](./Solution.java)

#### Complexity
- Time: `O(n)`, 同向双指针问题, 没有回退，每个element最多被遍历两次
- Space: `O(m)`, for array solution where m is the size of charset and `O(min(m,n))` for HashMap solution where m is the size of charset and n is the length of input string

#### Idea:
1. 典型的同向双指针问题：用双指针来维护一个滑动窗口 (sliding window)，右移右端指针来扩大窗口，一旦窗口中包含多于k个独特字符，右移左端指针来缩小窗口，直到窗口中包含小于等于k个独特字符
2. 利用Array或者是HashMap来存储当前窗口中字符的信息，用来判断窗口是否依旧满足条件

#### Mistakes I have made:
1. 里层的while循环在检查条件时，忘记检查到当前右端指针对应的字符会不会破坏条件：
假设当前的window中已包含k个独特的字符，那么如果当前右端指针对应的字符为一个全新的，之前没有见过的字符
的话，那么while循环的条件已不满足，循环应该中止  
之前的while循环条件：
    ```
    while(right < n && numOfDistinct <= k) {
        ...
    }
    ```
现改为：
    ```
    while (right < n && (numOfDistinct < k ? true : counts[s.charAt(right)] > 0)) {
        ...
    }
    ```

#### Review At:
