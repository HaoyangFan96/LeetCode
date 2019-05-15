// Java solution to Q46 "Permutations"
// Reference: https://leetcode.com/problems/permutations/description/
// Date: 05-15-2019

/**
 * Thoughts:
 *
 * The idea of doing basic permutation is explained pretty well in jiuzhang tutorial
 * https://www.jiuzhang.com/tutorial/algorithm/440
 *
 * The template here using is very similar to the one used to solve for combination
 * problems, except for the only difference: for permutation, it is possible to
 * look back and re-select the elements at the beginning of array, as the order does
 * matter. However, since the order doesn't matter for combination problems, we don't
 * need to restart from the beginning.
 * So as for permutation, it is possible to re-pick element from the beginning,
 * we will be using extra storage, a boolean array that mark each element in the input
 * is used or not in the process of generating current permutation.
 *
 *
 * NOTE: the basis for time complexity of permutation is O(n!) which is even higher
 * than the basis for time complexity of combination, which is O(2^n)
 *
 * Time: O(n!)
 *
 * Space: O(n!)
 *
 */

/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

/**
 * Initial DFS + backtracking solution in Java, using the standard template for
 * permutation offered by jiuzhang algorithm class tutorial.
 *
 * @author Haoyang Fan
 * @version
 * @since
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> rst = new ArrayList<>();
         if (nums == null || nums.length == 0) return rst;

         dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), rst);
         return rst;
    }

    private void dfs(int[] nums, boolean[] used, List<Integer> permutation, List<List<Integer>> rst) {
        if (nums.length == permutation.size()) { // check to see if we have generated a complete permutation
            rst.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            // if this element is already used in generating current permutation
            // then we should not use it again
            if (used[i]) continue;

            // add this element to current permutation
            permutation.add(nums[i]);
            used[i] = true; // mark it as used

            dfs(nums, used, permutation, rst);

            // backtrack
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }
}
