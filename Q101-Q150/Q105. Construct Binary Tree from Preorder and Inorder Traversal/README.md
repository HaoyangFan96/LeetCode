### Problem  ["Construct Binary Tree from Preorder and Inorder Traversal"](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)

#### Knowledge Used:
1.  Binary Tree
2.  Depth First Search

#### Code
-   [DFS in Java, without HashMap](./Solution.java)

#### Complexity
-   Time: `O(n^2)` in the worst case we will have to scan entire array n times
-   Space: `O(n)` in the worst case the depth of tree is `O(n)`

#### Idea:
1.  For pre-order array, if we look from the start, we can determine the root of current tree
2.  For in-order array, the left part to the root represents the left subtree, the
right part to the root represents the right subtree.
3.  We can use HashMap to pre-record the indices, which will save the time for searching through in-order array for current root

#### Mistakes I have made:

#### Review At:
-   05-16-2019
