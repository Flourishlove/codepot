// meeting rooms 2, 求最少的会议室数量，能满足所有会议的需求
// intervals按照开始时间排序遍历，但是放入的堆按照结束时间排序

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
        Arrays.sort(intervals, (a,b)->(a.start - b.start));
        PriorityQueue<Interval> heap = new PriorityQueue<>((a,b)->(a.end - b.end));

        if(intervals.length == 0) return 0;
        heap.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            Interval early = heap.peek();
            if(early.end <= intervals[i].start){
                heap.poll();
            }
            heap.offer(intervals[i]);
        }
        return heap.size();
    }
}