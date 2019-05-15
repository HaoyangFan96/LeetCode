// JS solution to Q40 "Combination Sum II"
// Reference: https://leetcode.com/problems/combination-sum-ii/description/
// Date: 09-27-2018

/**
 * Thoughts:
 * Very similar to Q39 "Combination Sum", we should also apply backtracking on
 * this question. NOTE that we should be careful with the fact that duplicates
 * are allowed for this problem. Also NOTE that we are restricted to use each
 * number only once
 */

/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

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
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-27-2018
 * Initial backtracing solution in JS
 */

/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function(candidates, target) {
    if (candidates == null || candidates.length === 0) {
        return [];
    }
    // sort the candidates
    candidates.sort();
    let res = [];
    for (let i = 0; i < candidates.length; i++) {
        if (i === 0 || candidates[i] !== candidates[i-1]) {
            backtracking(res, [], i, candidates, target);
        }
    }
    return res;
};

// Recursive backtracking function to probe each possible list combination
// l: current partial list
// idx: the index of element to try
var backtracking = function (res, l, idx, candidates, target) {
    let ll = [...l, candidates[idx]];
    let sum = ll.reduce((acc, v) => acc + v, 0);
    if (sum === target) {
        return res.push(ll);
    }
    else if (sum > target) {
        return false;
    }

    for(let i = idx + 1; i < candidates.length; i++) {
        if (i === idx + 1 || candidates[i] !== candidates[i-1]) {
            backtracking(res, ll, i, candidates, target);
        }
    }
};
