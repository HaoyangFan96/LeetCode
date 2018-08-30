// Solution to Q39 "Combination Sum"
// Reference: https://leetcode.com/problems/combination-sum/description/
// Date: 08-28-2018

/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

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
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum = function(candidates, target) {
    let result = [];
    // sort the candidates
    candidates.sort();
    for (let i = 0; i < candidates.length; i++) {
        accumulateSum(result, [], i, candidates, target);
    }
    return result;
};

// l: current partial list
// idx: current index of the value to try
function accumulateSum (result, l, idx, candidates, target) {
    let sum = l.reduce((acc, curr) => acc + curr, 0);
    if (sum + candidates[idx] === target) {
        return result.push([...l, candidates[idx]]);
    }
    else if (sum + candidates[idx] > target) {
        return false;
    }
    // backtracking
    for(let i = idx; i < candidates.length; i++) {
        accumulateSum(result, [...l, candidates[idx]], i, candidates, target);
    }
}
