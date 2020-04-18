// meeting rooms 2, 求最少的会议室数量，能满足所有会议的需求
// intervals按照开始时间排序遍历，但是放入的堆按照结束时间排序,途中需要maintain一个pq的最大值，就是最少需要的会议室

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
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (a,b)->(Integer.compare(a[0], b[0])));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int max = 0;
        for(int[] interval : intervals){
            if(pq.isEmpty()) pq.add(interval[1]);
            else{
                if(pq.peek() <= interval[0]) {
                    pq.poll();
                }
                pq.add(interval[1]);
            }
            max = Math.max(max, pq.size());
        }
        return max;
    }
}