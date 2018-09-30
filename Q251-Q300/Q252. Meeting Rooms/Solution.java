// Java solution to Q252 "Meeting Rooms"
// Reference: https://leetcode.com/problems/meeting-rooms/description/
// Date: 09-29-2018

/**
 * Thoughts:
 * This problem is very simple and can be solved in a few steps:
 * 1) sort the intervals by their start
 * 2) traverse through intervals one by one, check the previous interval and
 * current interval to see if there is any overlapping
 */

/*
Given an array of meeting time intervals consisting of start and end times
[[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

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
 * @since 09-29-2018
 * Review solution in java
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
        // iterate through the interval, compare current one with the previous
        // one to see if there is any overlapping
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i-1].end) {
                return false;
            }
        }
        return true;
    }
}
