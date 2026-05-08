package practice;

// 滑动窗口最大值（单调队列经典用法模版）
// 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧
// 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
// 返回 滑动窗口中的最大值 。
// 测试链接 : https://leetcode.cn/problems/sliding-window-maximum/
public class Class54_Code01_SlidingWindowMaximum {

    private static final int MAXN = 100001;

    private static final int[] queue = new int[MAXN];

    // h - 头，t - 尾
    private static int h, t;

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        // 初始化队列为空队列
        h = t = 0;
        // 把前k-1个数据入队列，每次遇到窗口k的时候结算窗口答案
        for (int i = 0; i < k - 1; i++) {
            // 不满足队列头尾从大到小弹出
            while (h < t && nums[queue[t - 1]] <= nums[i]) {
                t--;
            }
            // 当前的数据入队列，存下标
            queue[t++] = i;
        }
        // 从k-1结算遍历答案
        for (int l = 0, r = k - 1; r < n; r++) {
            while (h < t && nums[queue[t - 1]] <= nums[r]) {
                t--;
            }
            queue[t++] = r;
            // 结算答案
            ans[l] = nums[queue[h]];
            // 如果窗口左边==队列头，说明队列头的数据马上就要过期了,因为r马上要自增了，l要缩了
            if (l == queue[h]) {
                // 吐出队列头元素
                h++;
            }
            l++;
        }
        return ans;
    }

}
