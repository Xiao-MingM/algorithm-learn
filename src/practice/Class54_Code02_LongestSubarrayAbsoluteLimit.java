package practice;

// 绝对差不超过限制的最长连续子数组
// 给你一个整数数组 nums ，和一个表示限制的整数 limit
// 请你返回最长连续子数组的长度
// 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit
// 如果不存在满足条件的子数组，则返回 0
// 测试链接 : https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
public class Class54_Code02_LongestSubarrayAbsoluteLimit {
    // 分析单调性
    // l-r区间的子数组若任意两个数绝对值差小于limit, 区间变大，绝对值的差只会变大，相反，如果区间变小，绝对值的差值只会变小
    // 区间一直更新，如果区间绝对值大小满足不超过limit，就一直扩，超了左边窗口就缩小
    // 这时候需要实时知道区间的最大值和最小值==>维护两个单调队列

    private static final int MAXN = 100001;
    private static final int[] maxQueue = new int[MAXN];
    private static final int[] minQueue = new int[MAXN];

    private static int[] arr;
    // 四个队列指针
    private static int maxH,maxT,minH,minT;

    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        arr = nums;
        // 初始化队列
        maxH = maxT = minH = minT = 0;
        int ans = 0;
        // 以左为边界向右看是否有合适的，扩进来
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && isOk(r, limit)) {
                push(r++);
            }
            // 统计答案前面r++过了
            ans = Math.max(ans, r - l);
            // 弹出l
            pop(l);
        }
        return ans;
    }

    private boolean isOk(int r, int limit) {
        // 最大值
        int max = maxH == maxT ? arr[r] : Math.max(arr[maxQueue[maxH]], arr[r]);
        // 最小值
        int min = minH == minT ? arr[r] : Math.min(arr[minQueue[minH]], arr[r]);
        return max - min <= limit;
    }

    private void push(int r) {
        while (maxH < maxT && arr[maxQueue[maxT - 1]] <= arr[r]) {
            maxT--;
        }
        maxQueue[maxT++] = r;

        while (minH < minT && arr[minQueue[minT - 1]] >= arr[r]) {
            minT--;
        }
        minQueue[minT++] = r;
    }

    private void pop(int l) {
        if (maxH < maxT && maxQueue[maxH] == l) {
            maxH++;
        }
        if (minH < minT && minQueue[minH] == l) {
            minH++;
        }
    }

}
