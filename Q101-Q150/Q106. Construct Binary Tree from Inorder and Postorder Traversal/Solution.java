// Java solution to Q106 "Construct Binary Tree from Inorder and Postorder Traversal"
// Reference: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
// Date: 05-16-2019

/**
 * Thoughts:
 *
 * The idea is pretty similar to Q105 "Construct Binary Tree from Preorder and Inorder Traversal"
 *
 * Inorder: visit the left subtree first, then visit current node, finally visit the right subtree
 *
 * Postorder: visit the left subtree first, then visit the right subtree, finally visit current node
 *
 * Based on the property of post-order traversal, we know that the last element of postorder array must
 * be the root of whole binary tree.
 *
 * Once we determine the root of whole binary tree, we can then search  and locate it
 * in the inorder array.
 *
 * Based on the property of in-order traversal, the part on the left to the root is
 * the left subtree, the part to the right of the root is the right subtree.
 *
 * Hence we will be able to determine the size of right subtree. Once we determine the
 * size, we can then determine the range for left subtree and right subtree respectively
 * in the postorder array.
 *
 * Once we determine the range of left subtree and right subtree, because of the property
 * of postorder traversal, we can then determine the respective root of left subtree
 * and right subtree. We then again search these roots in the inorder array, to determine
 * their left and right subtrees...
 *
 * TODO: use HashMap to record the index to reduce search time
 *
 * Time: O(n^2) as in the worst case we have to scan through array n times, if we don't
 * use HashMap to record the index
 *
 * Space: O(n) as in the worst case the depth of tree is O(n)
 *
 */

/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

 */

 /**
  * Definition for a binary tree node.
  * public class TreeNode {
  *     int val;
  *     TreeNode left;
  *     TreeNode right;
  *     TreeNode(int x) { val = x; }
  * }
  */


/**
 * Initial DFS solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 05-16-2019
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }

    private TreeNode dfs(int[] inorder, int inStart, int inEnd, int[] postorder, int postEnd) {
        // if inStart exceeds inEnd, we know that no more node should be constructed from here
        if (inStart > inEnd) return null;
        // if inStart == inEnd, we know that this is going to be a leaf node
        else if (inStart == inEnd) return new TreeNode(inorder[inStart]);

        // search through inorder from inStart to inEnd
        // look for node whose value is postorder[postEnd]
        int i;
        for (i = inStart; i <= inEnd; ++i) {
            if (inorder[i] == postorder[postEnd]) break;
        }

        // construct the root of current subtree
        TreeNode root = new TreeNode(postorder[postEnd]);

        // calculate the size of right subtree
        int rightSubtreeSize = inEnd - i;

        // construct the left subtree
        root.left = dfs(inorder, inStart, i - 1, postorder, postEnd - 1 - rightSubtreeSize);
        // construct the left subtree
        root.right = dfs(inorder, i + 1, inEnd, postorder, postEnd - 1);

        // return the root of current subtree
        return root;
    }
}
