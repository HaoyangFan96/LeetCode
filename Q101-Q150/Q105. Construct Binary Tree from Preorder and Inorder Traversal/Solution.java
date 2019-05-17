// Java solution to Q105 "Construct Binary Tree from Preorder and Inorder Traversal" (9999)
// Reference: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
// Date: 04-21-2019

/**
 * Thoughts:
 *
 * The idea is pretty similar to Q106 "Construct Binary Tree from Inorder and Postorder Traversal"
 *
 * Preorder: visit the current node first, then visit the left subtree, finally visit the right subtree
 *
 * Inorder: visit the left subtree first, then visit current node, finally visit the right subtree
 *
 * Based on the property of pre-order traversal, we know that the first element of preorder array must
 * be the root of whole binary tree.
 *
 * Once we determine the root of whole binary tree, we can then search and locate it
 * in the inorder array.
 *
 * Based on the property of in-order traversal, the part on the left to the root is
 * the left subtree, the part to the right of the root is the right subtree.
 *
 * Hence we will be able to determine the size of left subtree. Once we determine the
 * size, we can then determine the range for left subtree and right subtree respectively
 * in the preorder array.
 *
 * Once we determine the range of left subtree and right subtree, because of the property
 * of preorder traversal, we can then determine the respective root of left subtree
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
 * Review DFS solution in Java.
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 05-16-2019
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, 0, inorder, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd) {
        // if inStart exceeds inEnd, then we know that current root does not exist
        if (inStart > inEnd) return null;
        // if inStart == inEnd, then we know that current root is actually a leaf node
        else if (inStart == inEnd) return new TreeNode(inorder[inStart]);

        // search through inorder array from inStart to inEnd, looking for preorder[preStart]
        int i;
        for (i = inStart; i <= inEnd; ++i) {
            if (inorder[i] == preorder[preStart]) break;
        }

        // calculate the size of left subtree
        int leftSubtreeSize = i - inStart;

        // construct the root for current subtree
        TreeNode root = new TreeNode(preorder[preStart]);

        // recursively construct the left subtree
        root.left = dfs(preorder, preStart + 1, inorder, inStart, i - 1);
        // recursively construct the right subtree
        root.right = dfs(preorder, preStart + 1 + leftSubtreeSize, inorder, i + 1, inEnd);

        // return the root of current subtree
        return root;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial DFS solution in Java.
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 04-26-2019
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        // deal with the base in which inStart exceeds inEnd
        if (inStart > inEnd) return null;

        int i;
        // search for value of preorder[preStart] among inorder array in range [inStart, inEnd]
        for (i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart]) break;
        }

        // construct the root of current subtree whose value is preorder[preStart]
        TreeNode root = new TreeNode(preorder[preStart]);

        // recursively constructthe left subtree
        // whose root exists in inorder in between [inStart, i - 1]
        root.left = dfs(preorder, inorder, preStart + 1, inStart, i - 1);

        // calculate the total number of elements in the left subtree
        int leftTreeSize = i - 1 - inStart + 1;

        // recursively construct the right subtree
        // whose root exists in inorder in between [i + 1, inEnd]
        root.right = dfs(preorder, inorder, preStart + leftTreeSize + 1, i + 1, inEnd);

        // finally return the root of current subtree
        return root;
    }
}
