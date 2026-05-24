package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 会议室II
// 给你一个会议时间安排的数组 intervals
// 每个会议时间都会包括开始和结束的时间intervals[i]=[starti, endi]
// 返回所需会议室的最小数量
// 测试链接 : https://leetcode.cn/problems/meeting-rooms-ii/
public class Class89_Code04_MeetingRoomsII {

    // [1,3][1,2][3,4][3,7][5,6]
    // 3       ans = 1
    // 2,3     ans = 2
    // 4       ans = 2
    // 4,7     ans = 2
    // 5,7     ans = 2
    public static int minMeetingRooms(int[][] meeting) {
        Arrays.sort(meeting, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int[] meet : meeting) {
            // 当瞅见结尾已经赶上下一个出来了直接释放掉
            while (!heap.isEmpty() && heap.peek() <= meet[0]) {
                heap.poll();
            }
            heap.offer(meet[1]);
            ans = Math.max(heap.size(), ans);
        }
        return ans;
    }
}
