// Java solution to Q40 "Combination Sum II"
// Reference: https://leetcode.com/problems/combination-sum-ii/description/
// Date: 09-29-2018

/**
 * Thoughts:
 * This is a typical backtracking problem and should be solved by following steps:
 * 1) Sort the input numbers
 * 2) Begin the backtracking recursive DFS step
 * 3) If there is any combination whose sum is equal to target, add that combination
 * to the result set
 *
 * As you can see, the idea above should be pretty much similar to the one used in
 * Combination Sum. The only difference here is: candidates may contain duplicate values.
 * However, when we are generating combinations whose sum is equal to the target,
 * we don't want to add duplicate combinations.
 *
 * How to do that? The idea is explained pretty well during the first half of jiuzhang
 * algorithm class, lecture 6. The basic idea is following:
 * Suppose we have n duplicate values, and for combinations whose sum is equal to
 * target, we will need k (k < n) out of these duplicate values. In such cases, we
 * will always restrict ourselves to pick the first k out of n duplicates. By doing
 * so, we will avoid add any duplicate k values
 *
 * Time: O(n * 2^n)
 *
 * Space: O(n * 2^n)
 *
 */

/*
Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

/**
 * Review DFS + backtracking solution in Java, using the standard template offered
 * by jiuzhang
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 05-14-2019
 */
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        // deal with the edge case
        if (candidates == null || candidates.length == 0) return rst;

        // sort the input array
        Arrays.sort(candidates);

        dfs(candidates, 0, target, new ArrayList<Integer>(), rst);
        return rst;
    }

    private void dfs(int[] candidates, int startIndex, int remaining, List<Integer> comb, List<List<Integer>> rst) {
        if (remaining == 0) {
            rst.add(new ArrayList<>(comb)); // create a deep copy of combination
            return;
        }

        for (int i = startIndex; i < candidates.length; ++i) {
            // if we know remaining - candidates[i] < 0, then we don't need to
            // consider current candidate as well as all candidates after it
            // since we already sort the input array
            if (remaining - candidates[i] < 0) break;

            // if in the same call, we have used previous candidate whose value
            // is the same as current's, then we don't need to consider current
            // candidate --> this is how we avoid generating duplicate combinations
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue;


            comb.add(candidates[i]);
            dfs(candidates, i + 1, remaining - candidates[i], comb, rst);

            // backtrack
            comb.remove(comb.size() - 1);
        }
    }
}

/*----------------------------------------------------------------------------*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-29-2018
 * Backtracking solution in java
 */

 class Solution {
     public List<List<Integer>> combinationSum2(int[] candidates, int target) {
         List<List<Integer>> res = new ArrayList<>();
         if (candidates == null || candidates.length == 0) {
             return res;
         }
         // sort the candidates
         Arrays.sort(candidates);
         backtracking(res, new ArrayList<Integer>(), 0, candidates, 0, target);
         return res;
     }

     private void backtracking (List<List<Integer>> res, List<Integer> curr, int currSum, int[] candidates, int idx, int target) {
         if (currSum == target) {
             res.add(curr);
             return;
         }
         for (int i = idx; i < candidates.length; i++) {
            // skip the repeating numbers
            if (i == idx || candidates[i] != candidates[i-1]) {
                if (currSum + candidates[i] <= target) {
                    List<Integer> temp = new ArrayList<>(curr);
                    temp.add(candidates[i]);
                    backtracking(res, temp, currSum + candidates[i], candidates, i+1, target);
                }
                else {
                    return;
                }
            }
        }
    }
 }
