// Solution to Q239 "Sliding Window Maximum"
// Reference: https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
// Date: 08-23-2018

// 此题核心思路，我们需要保持deque使其head(first)一直为最大的element，且deque为单调递减
// 每次滑动窗口，来当当前新的node的时候，把小于当前node的，全部剔除，剩下的，自然就是:最大的>第二大的>第三大的...ETC
// 我们只在乎最大值的存在；而任何小于当前（正要新就加进去的）值的，反正以后也成不了最大值，于是扔掉！

// exmaple [2 3 4 1 8 7 2] k = 4
// for first window, the deque will be formed in this way:
// 2 -> | 2 |, 2 is at both head and tail
// 3 -> | 3 |, throw away 2 since 2 is less than 3, for the future windows, 2
// won't have any impact due to the presence of 3
// 4 -> | 4 |, same idea as above step
// 1 -> | 4 1 |, since 4 is larger than 1, we cannot remove it
// OK, right now we have scan the first window, as we can see, the queue is
// monotonous decreasing and the maximum is at the head
// next window:
// 2 is out of window, since it is not the maximum, do nothing
// 8 -> | 8 |, 1 and 4 are both less than 8, throw them away because they also have
// no impact on future's windows because of the presense of 8
// so on...

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }
        // initialize the ArrayDeque
        Deque<Integer> ad = new ArrayDeque<Integer>();
        // do some initialization
        for (int i = 0; i < k - 1; i++) {
            enqueue(ad, nums[i]);
        }
        // variable to hold the result, whose size can be determined in advance
        int[] res = new int[nums.length - k + 1];
        // two pointers, left => the element just out of current window
        // right => the element just enter current window
        int left = -1, right = k - 1;
        // begin iteration
        for (int i = 0; right < nums.length; left++, right++, i++) {
            if (left >= 0) {
                int leave = nums[left];
                // if the leaving element is the max, just drop it
                if (ad.peekFirst() == leave) {
                    ad.pollFirst();
                }
            }
            int enter = nums[right];
            enqueue(ad, enter);
            // add the max of current window to the result
            res[i] = ad.peekFirst();
        }
        return res;
    }
    private void enqueue(Deque<Integer> ad, int curr) {
        // remove all ints at the tail that are less than the current one
        while(!ad.isEmpty() && ad.peekLast() < curr) {
            ad.pollLast();
        }
        ad.offerLast(curr);
    }
 }
