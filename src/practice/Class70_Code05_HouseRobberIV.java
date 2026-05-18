package practice;

// 打家劫舍 IV
// 沿街有一排连续的房屋。每间房屋内都藏有一定的现金
// 现在有一位小偷计划从这些房屋中窃取现金
// 由于相邻的房屋装有相互连通的防盗系统，所以小偷不会窃取相邻的房屋
// 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额
// 给你一个整数数组 nums 表示每间房屋存放的现金金额
// 第i间房屋中放有nums[i]的钱数
// 另给你一个整数k，表示小偷需要窃取至少 k 间房屋
// 返回小偷需要的最小窃取能力值
// 测试链接 : https://leetcode.cn/problems/house-robber-iv/
public class Class70_Code05_HouseRobberIV {
    //示例 1：
    //
    //输入：nums = [2,3,5,9], k = 2
    //输出：5
    //解释：
    //小偷窃取至少 2 间房屋，共有 3 种方式：
    //- 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
    //- 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
    //- 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
    //因此，返回 min(5, 9, 9) = 5 。
    //示例 2：
    //
    //输入：nums = [2,7,9,3,1], k = 2
    //输出：2
    //解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
    public int minCapability(int[] nums, int k) {
        // 二分 小偷的能力范围是金额的最小值~最大值
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int num : nums) {
            l = Math.min(num, l);
            r = Math.max(num, r);
        }
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (rob(nums, mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 告诉能力和要求看能不能偷够
    private int rob1(int[] nums, int ability) {
        int n = nums.length;
        if (n == 1) {
            return ability >= nums[0] ? 1 : 0;
        }
        if (n == 2) {
            return ability >= nums[0] || ability >= nums[1] ? 1 : 0;
        }
        int[] dp = new int[n];
        dp[0] = ability >= nums[0] ? 1 : 0;
        dp[1] = ability >= nums[0] || ability >= nums[1] ? 1 : 0;
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + (ability >= nums[i] ? 1 : 0));
        }
        return dp[n - 1];
    }

    // 告诉能力和要求看能不能偷够
    // 空间优化
    private int rob2(int[] nums, int ability) {
        int n = nums.length;
        if (n == 1) {
            return ability >= nums[0] ? 1 : 0;
        }
        if (n == 2) {
            return ability >= nums[0] || ability >= nums[1] ? 1 : 0;
        }
        int prePre = ability >= nums[0] ? 1 : 0;
        int pre = ability >= nums[0] || ability >= nums[1] ? 1 : 0;
        for (int i = 2, cur; i < n; i++) {
            cur = Math.max(pre, prePre + (ability >= nums[i] ? 1 : 0));
            prePre = pre;
            pre = cur;
        }
        return pre;
    }

    // 贪心
    private int rob(int[] nums, int ability) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 发现合适立即偷，不要犹豫
            if (ability >= nums[i]) {
                ans++;
                i++;
            }
        }
        return ans;
    }

}
