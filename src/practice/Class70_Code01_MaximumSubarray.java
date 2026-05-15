package practice;

// 子数组最大累加和
// 给你一个整数数组 nums
// 返回非空子数组的最大累加和
// 测试链接 : https://leetcode.cn/problems/maximum-subarray/
public class Class70_Code01_MaximumSubarray {

    // 示例 1：
    //
    //输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    //输出：6
    //解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
    // [-2]
    // [-2, 1]
    // [-2, 1, -2]
    // [-2, 1, -2, 4]
    // []
    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        // dp[i]表示以i结尾的最大累加和
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            // dp[i] = 不要前面的只要自己 和 前面的最大值加上自己
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 压缩空间
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        for (int i = 1, pre = nums[0]; i < n; i++) {
            // dp[i] = 不要前面的只要自己 和 前面的最大值加上自己
            pre = Math.max(nums[i], pre + nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    // 如下代码为附加问题的实现
    // 子数组中找到拥有最大累加和的子数组
    // 并返回如下三个信息:
    // 1) 最大累加和子数组的开头left
    // 2) 最大累加和子数组的结尾right
    // 3) 最大累加和子数组的累加和sum
    // 如果不止一个子数组拥有最大累加和，那么找到哪一个都可以
    public static int left;

    public static int right;

    public static int sum;

    // 找到拥有最大累加和的子数组
    // 更新好全局变量left、right、sum
    // 上游调用函数可以直接使用这三个变量
    // 相当于返回了三个值
    public static void extra(int[] nums) {
        int n = nums.length;
        sum = Integer.MIN_VALUE;
        for (int l = 0, r = 0, pre = 0; r < n; r++) {
            if (pre >= 0) {
                // 吸收掉pre
                pre += nums[r];
            } else {
                // 原地不动
                pre = nums[r];
                l = r;
            }
            if (pre > sum) {
                sum = pre;
                left = l;
                right = r;
            }
        }
    }
}
