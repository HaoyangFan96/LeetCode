// JS solution to Q22 "Generate Parentheses"
// Reference: https://leetcode.com/problems/generate-parentheses/description/
// Date: 08-20-2018

/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function(n) {
    let res = [];
    balanceParenthesis(res, '(', false, 1, 0, n);
    return res;
};

/**
 * @param {string[]} res the result array to hold all possible patterns
 * @param {String} currStr the current string that has been generated
 * @param {boolean} balanced the flag to indicate whether the current string is balanced or not
 * @param {number} numOfOpen the number of open parenthesis in the current string
 * @param {number} numOfClose the number of close parenthesis in the current string
 * @param {number} n the number of pairs of parentheses
 */
function balanceParenthesis(res, currStr, balanced, numOfOpen, numOfClose, n) {
    if (balanced && numOfOpen === n && numOfClose === n) {
        return res.push(currStr);
    }
    // in case current string is not balanced yet
    if (!balanced) {
        if (numOfOpen < n) {
            // we can still add more '(' to current String, even if it is not balanced
            balanceParenthesis(res, currStr + '(', false, numOfOpen + 1, numOfClose, n);
        }
        // we can also add some ')' to see if we can balance the current string
        if (numOfClose < numOfOpen) {
            if (numOfClose + 1 === numOfOpen) {
                // in case the number of '(' and ')' are equal, we know that
                // the current string now is balanced
                balanceParenthesis(res, currStr + ')', true, numOfOpen, numOfClose + 1, n);
            }
            else {
                // otherwise, the current string is still not balanced
                balanceParenthesis(res, currStr + ')', false, numOfOpen, numOfClose + 1, n);
            }
        }
    }
    else {
        // if the current string is balanced and it is not done, we can only add '(' in this case
        balanceParenthesis(res, currStr + '(', false, numOfOpen + 1, numOfClose, n);
    }
};
