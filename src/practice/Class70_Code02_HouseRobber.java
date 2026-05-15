package practice;

// 数组中不能选相邻元素的最大累加和
// 给定一个数组，可以随意选择数字
// 但是不能选择相邻的数字，返回能得到的最大累加和
// 测试链接 : https://leetcode.cn/problems/house-robber/
public class Class70_Code02_HouseRobber {
    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // dp[i]表示选择偷0-i区间的屋子可以偷到的最大值
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            // 情况1：第i间屋子我就是不偷，我在0 - i-1区间找偷到的最大值
            // 情况2：第i间屋子要偷, 第i-1间就偷不了了，从0 - i-2 区间找偷到的最大值+当前屋子的价值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    // 空间优化
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // dp[i]表示选择偷0-i区间的屋子可以偷到的最大值
        int prePre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        for (int i = 2, cur; i < n; i++) {
            // 情况1：第i间屋子我就是不偷，我在0 - i-1区间找偷到的最大值
            // 情况2：第i间屋子要偷, 第i-1间就偷不了了，从0 - i-2 区间找偷到的最大值+当前屋子的价值
            cur = Math.max(pre, prePre + nums[i]);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }
}
