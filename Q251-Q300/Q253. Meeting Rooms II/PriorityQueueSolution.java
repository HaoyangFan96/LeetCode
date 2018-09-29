// Java solution to Q253 "Meeting Rooms II"
// Reference: https://leetcode.com/problems/meeting-rooms-ii/description/
// Date: 09-28-2018

/**
 * Thoughts:
 * All we care about for solving this problem is that:
 * Whenever there is a new meeting scheduled to happen right now, we need to check
 * if there is a room that is available to use.
 * We don't care which room to use, as long as there is one room that is not in
 * use, we just use it. So that room will be occpuied
 *
 * In order to approach this problem, we need to first approach this problem by
 * sorting the meetings by the start time, so that we can arrange the meetings on
 * the first-come-first-serve basis. That step cannot be ignored
 *
 * Key: we will be using a PriorityQueue(min-heap) to keep track of all "rooms"
 * we have booked so far. Rooms that has meeting with the earlies ending time
 * will be the topmost element of that priority queue, no matter that meeting has
 * ended or not at the moment that the next meeting has just arrived
 *
 * Whenever there is a new meeting coming, we will check with the priority queue
 * of the room with earliest meeting ending time. If that meeting is already ended,
 * we just "use that room: we update the meeting information of that room, and
 * put back it to the queue
 *
 * In the end, the size of priority queue represents the total number of meeting
 * rooms we need to book
 *
 * Reference: https://leetcode.com/problems/meeting-rooms-ii/solution/
 *
 * Time: O(nlogn), mainly because of the sorting
 * Space: O(n), for the use of priority queue
 */

/**
 * @author Haoyang Fan
 * @version 1.0
 * @since 09-28-2018
 * PriorityQueue solution in java
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
     public int minMeetingRooms(Interval[] intervals) {
         if (intervals == null || intervals.length == 0) {
             return 0;
         }
         // NOTE: don't forget to sort the meetings by the start time
         Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
         // initialize a priority queue whose ordering is determined by interval's
         // ending time. The earliest ending interval will be the topmost(min-heap)
         PriorityQueue<Interval> rooms = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);
         // add the first meeting into the rooms
         rooms.offer(intervals[0]);
         // iterate through the rest of meeting schedules
         for(int i = 1; i < intervals.length; i++) {
             // get the information of room with the earliest meeting ending time
             Interval earliestEnding = rooms.peek();
             // check if we can arrange the meeting into this room
             if (intervals[i].start >= earliestEnding.end) {
                 // use that room: update the information of meeting in that room
                 rooms.poll();
                 rooms.offer(intervals[i]);
             }
             else {
                 // have to book a new room
                 rooms.offer(intervals[i]);
             }
         }
         return rooms.size();
     }
 }
