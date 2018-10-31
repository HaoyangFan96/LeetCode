// Java solution to Q128 "Longest Consecutive Sequence"
// Reference: https://leetcode.com/problems/longest-consecutive-sequence/description/
// Date 10-06-2018

/**
 * Thoughts:
 * We can use "Union Find" to solve problem, take a look at the algorithms notes
 * offered from Princeton "Algorithms Part I" which talks about the implementation
 * as well as the possible optimization
 * Here I am going to construct a weighted path compression union find algorithms
 * instead choosing the back data structure to be array as what they did in
 * "Algorithms Part I" class, I am going to use HashMap as the back data structure
 *
 * The idea is doing the following steps:
 * 1. Iterate through the array
 * 2. During the iteration, for each of value v scanned, first is to check whther
 * v+1 or v-1 has already existed:
 *    1. If it does exist, union them together
 *    2. Create a new connected component with the only element to be v
 *    3. update the global maximum size in the meanwhile
 * 3. After iteration, simply return the global maximum size
 */

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 10-06-2018
 * Initial Union Find solution in java
 */

 class Solution {
     public int longestConsecutive(int[] nums) {
         if (nums == null || nums.length == 0) {
             return 0;
         }
         int maxSize = Integer.MIN_VALUE;
         WeightedPathCompressionUF uf = new WeightedPathCompressionUF(nums);
         for (int i : nums) {
             int prev = i - 1;
             int next = i + 1;
             int s = 1;
             if (uf.parent.containsKey(prev)) {
                 s = uf.union(prev, i);
             }
             if (uf.parent.containsKey(next)) {
                 s = uf.union(i, next);
             }
             maxSize = Math.max(maxSize, s);
         }
         return maxSize;
     }
 }

class WeightedPathCompressionUF {
    public Map<Integer, Integer> parent;
    public Map<Integer, Integer> size;

    public WeightedPathCompressionUF(int[] nums) {
        parent = new HashMap<>(nums.length);
        size = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            parent.put(nums[i], nums[i]);
            size.put(nums[i], 1);
        }
    }

    public int root(int p) {
        int root = p;
        while(parent.containsKey(root) && root != parent.get(root)) {
            root = parent.get(root);
        }
        // Path compression
        while(parent.containsKey(p) && p != root) {
            int temp = parent.get(p);
            parent.put(p, root);
            p = temp;
        }
        return root;
    }

    public int find(int p) {
        return root(p);
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        // nothing need to be done if they are already connected
        if (connected(p, q)) {
            return size.get(pRoot);
        }
        int sum = 0;
        // append root of smaller connected component to the root of larger one
        if (size.containsKey(pRoot) && size.containsKey(qRoot) && size.get(pRoot) <= size.get(qRoot)) {
            sum = size.get(qRoot) + size.get(pRoot);
            if (parent.containsKey(pRoot)) {
                parent.put(pRoot, qRoot);
                size.put(qRoot, sum);
            }
        }
        else if (size.containsKey(pRoot) && size.containsKey(qRoot) && size.get(pRoot) > size.get(qRoot)) {
            sum = size.get(qRoot) + size.get(pRoot);
            if (parent.containsKey(qRoot)) {
                parent.put(qRoot, pRoot);
                size.put(pRoot, sum);
            }
        }
        return sum;
    }
}
