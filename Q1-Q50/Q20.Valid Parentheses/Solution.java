// Solution to the Q20 "Valid Parentheses"
// https://leetcode.com/problems/valid-parentheses/description/

class Solution {
    private boolean isPair(char open, char close) {
        if (open == '(' && close == ')') {
            return true;
        }
        else if (open == '{' && close == '}') {
            return true;
        }
        else if (open == '[' && close == ']') {
            return true;
        }
        return false;
    }

    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        List<Character> stack = new ArrayList<Character>();
        char[] chs = s.toCharArray();
        for (char c : chs) {
            if (c == '{' || c == '(' || c == '[') {
                stack.add(c);
            }
            else {
                if (stack.isEmpty()) {
                    return false;
                }
                char open = stack.remove(stack.size() - 1);
                if (!isPair(open, c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
