// Solution to Q31 "Next Permutation"
// Reference: https://leetcode.com/problems/next-permutation/description/
// Date: 08-28-2018

/*
Pick One

Implement next permutation, which rearranges numbers into the lexicographically
next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible
order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

class OriginalSolution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int tail = nums.length - 2;
        Queue<Integer> pq = new PriorityQueue<>();
        pq.add(nums[tail + 1]);
        List<Integer> temp = new ArrayList<>();
        boolean found = false;
        for(; tail >= 0; tail--) {
            int curr = nums[tail];
            temp = new ArrayList<>();
            while(pq.size() > 0) {
                if (curr < pq.peek()) {
                    nums[tail] = pq.poll();
                    pq.add(curr);
                    found = true;
                    break;
                }
                else {
                    temp.add(pq.poll());
                }
            }
            for (Integer i : temp) {
                pq.add(i);
            }
            if (found) {
                break;
            }
            pq.add(curr);
        }
        for(int i = tail + 1; i < nums.length; i++) {
            nums[i] = pq.poll();
        }
    }
}
