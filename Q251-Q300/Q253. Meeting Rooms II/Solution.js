// JS solution to Q253 "Meeting Rooms II"
// Reference: https://leetcode.com/problems/meeting-rooms-ii/description/
// Date: 09-28-2018

/**
 * Thoughts:
 * Basically a brute-force solution that keep track of the number of rooms in
 * used in every time point, compared with the maximum number of Rooms. In any case
 * that the number of rooms in used will exceed the maximum number of rooms,
 * increase the total number of rooms by one
 * Time: O(n^2)
 * Space: O(n)
 */

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1]
,[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/

// NOTE: actually the optimized solution will be using PriorityQueue so in that case
// JS is not a good for implementing that solution

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-28-2018
 * Initial solution in JS, unoptimized O(n^2) time and O(n) space
 */

 /**
  * Definition for an interval.
  * function Interval(start, end) {
  *     this.start = start;
  *     this.end = end;
  * }
  */
 /**
  * @param {Interval[]} intervals
  * @return {number}
  */
 var minMeetingRooms = function(intervals) {
     if (intervals == null || intervals.length === 0) {
         return 0;
     }
     let max = -1;
     for (const interval of intervals) {
         max = Math.max(max, interval.end);
     }
     let timeslot = Array(max+1).fill(0);
     let currRoom = 1;
     for (const interval of intervals) {
         for (let i = interval.start; i <= interval.end - 1; i++) {
            timeslot[i]++;
            if (timeslot[i] > currRoom) {
                currRoom++;
            }
         }
     }
     return currRoom;
 };
