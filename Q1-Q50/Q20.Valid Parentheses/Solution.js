// JS solution to Q20. "Valid Parentheses"
// Reference: https://leetcode.com/problems/valid-parentheses/description/

/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function(s) {
    let stack = [];
    // to use array as stack:
    // push: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/push
    // pop: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/pop

    // iterate through each character of the string
    for (const c of s) {
        switch (c) {
            // if it is a open-character, add it to the stack
            case '(':
                stack.push('(');
                break;
            case '[':
                stack.push('[');
                break;
            case '{':
                stack.push('{');
                break;
            // NOTE: when the array is empty, the pop will return "undefined",
            // so it will also immediately return false
            case ']':
                if (stack.pop() !== '[') {
                    return false;
                }
                break;
            case ')':
                if (stack.pop() !== '(') {
                    return false;
                }
                break;
            case '}':
                if (stack.pop() !== '{') {
                    return false;
                }
                break;
            default:
                break;
        }
    }
    return stack.length === 0;
};
