package practice;

// 和至少为K的最短子数组
// 给定一个数组arr，其中的值有可能正、负、0
// 给定一个正数k
// 返回累加和>=k的所有子数组中，最短的子数组长度
// 测试链接 : https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
public class Class55_Code01_ShortestSubarrayWithSumAtLeastK {

    //   [1, 2, -1, 2, 3] k = 5
    //[0, 1, 3,  2, 4, 7]
    // l = 0, r = 0 [0 - 0 = 0] [0]
    // l = 0, r = 1 [1 - 0 = 1] [0,1]
    // l = 0, r = 2 [3 - 0 = 3] [0,1,2]
    // l = 0, r = 3 [2 - 0 = 2] [0,1,3]
    // l = 0, r = 4 [4 - 0 = 4] [0,1,3,4]
    // l = 0, r = 5 [7 - 0 = 7] [0,1,3,4,5] ans = 5
    // l = 1, r = 5 [7 - 1 = 6] [1,3,4,5]
    // l = 3, r = 5 [7 - 2 = 5] [3,4,5] ans = 5 - 3 = 2
    // l = 4, r = 5 [7 - 4 = 3] [4,5]
    // l = 5, r = 5 [7 - 7 = 0]

    private static final int MAXN = 100001;
    // 维护从小到大的队列,头的位置放的是子数组的最小值，从
    private static final int[] queue = new int[MAXN];
    private static int h,t;
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        h = t = 0;
        // 求累加和数组
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        // 遍历数组，r表示右边界，每次走到r，就尽可能的在队列中找到个h最小的当左边界，如果sum[r] - sum[l] >= k 就符合计算一次答案,此时r还没入队列
        int ans = Integer.MAX_VALUE;
        for (int r = 0; r <= n; r++) {
            // 左扩结算答案
            while (h < t && sums[r] - sums[queue[h]] >= k) {
                // 结算一个就h向右走一个
                ans = Math.min(ans, r - queue[h++]);
            }
            // 入队列，遇到更小的弹出前面的，因为后面的更容易出答案
            while (h < t && sums[queue[t - 1]] >= sums[r]) {
                t--;
            }
            queue[t++] = r;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
