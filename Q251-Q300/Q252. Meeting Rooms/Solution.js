// JS solution to Q252 "Meeting Rooms"
// Reference: https://leetcode.com/problems/meeting-rooms/description/
// Date: 09-28-2018

/**
 * Thoughts:
 *
 */

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
(si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true
*/

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-28-2018
 * Initial solution in JS
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
  * @return {boolean}
  */
 var canAttendMeetings = function(intervals) {
     if (intervals == null || intervals.length === 0) {
         return true;
     }
     // sort the intervals according to the start
     // O(nlogn)
     intervals.sort((i1, i2) => i1.start - i2.start);
     let prev = null, curr = null;
     // iterate through sorted intervals
     for (let i = 0; i < intervals.length; i++) {
         if (prev == null) {
             prev = intervals[i];
         }
         else {
             curr = intervals[i];
             // check for overlapping
             // NOTE: for this problem, if the end of one interval is equal to
             // the start of another interval, these two intervals are not considered
             // to be overlapping
             if (curr.start < prev.end) {
                 return false;
             }
             prev = curr;
         }
     }
     return true;
 };
