class Solution {
    public String convert(String s, int numRows) {
        // first, do some basic checks
        if (s == null) {
            return "";
        }
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        // create a StringBuilder for each row
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        // variable to keep track of the direction
        int inc = -1;
        // variable to keep track the current number of numRows
        int currRow = 0;
        // iterate through each character, big-O : O(n)
        for (char c : s.toCharArray()) {
            rows.get(currRow).append(c);

            // hard part: when to change the direction
            if (currRow == 0 || currRow == numRows - 1) {
                inc *= -1;
            }
            // update the current row number
            currRow += inc;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : rows) {
            res.append(sb.toString());
        }
        return res.toString();
    }
}
