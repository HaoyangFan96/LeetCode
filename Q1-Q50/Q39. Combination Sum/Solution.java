// Java solution to Q39 (Combination Sum)
// Reference: https://leetcode.com/problems/combination-sum/description/
// Date: 05-14-2019

/**
 * Thoughts:
 *
 * This question is analyzed very well in jiuzhang algorithm class, class 6 first
 * half (see the notes). It can be solved relatively easy by applying the standard
 * template offered by jiuzhang.
 *
 * some ideas:
 * 1. when we add a number to the list, we are now considering all possible combinations
 * which have this number as prefix. Once we've considered all such combinations,
 * we will remove this number from the list (Backtracking) and then continue to add
 * other numbers to the list, which means that we are going to consider other combinations
 * that start with those numbers
 *
 * 2. "The same repeated number may be chosen from candidates unlimited number of times":
 * it is acutally quite simple: during the loop, when we do the DFS, instead of
 * starting from "i + 1" (i is the iterating variable), we are starting again from
 * the same "i", which means that the current number can be taken in arbitary number
 * of times
 *
 * Time: O(n * 2^n) 2^n is the number of combinations possible and n is for creating
 * a deep copy of current list
 *
 * Space:O(n * 2^n), same as above
 *
 */

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */

/**
 * Initial DFS + backtracking solution in Java, using the standard template offered
 * by jiuzhang
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 05-14-2019
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        // deal with edge case
        if (candidates == null || candidates.length == 0) return rst;

        Arrays.sort(candidates); // sort the input array

        dfs(candidates, 0, target, new ArrayList<Integer>(), rst);
        return rst;
    }

    private void dfs(int[] candidates, int startIndex, int remaining, List<Integer> comb, List<List<Integer>> rst) {
        if (remaining == 0) {
            rst.add(new ArrayList<>(comb)); // create a deep copy here
            return;
        }

        for (int i = startIndex; i < candidates.length; ++i) {
            // in case remaining - current value is already less than 0
            // then we now that we don't have to explore current value and rest of
            // values in the array since I already sort it
            if (remaining - candidates[i] < 0) break;

            // add current value to the combination list
            comb.add(candidates[i]);

            dfs(candidates, i, remaining - candidates[i], comb, rst);

            // backtrack
            comb.remove(comb.size() - 1);
        }
    }
}
