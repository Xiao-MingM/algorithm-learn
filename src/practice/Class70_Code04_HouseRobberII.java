package practice;
// 环形数组中不能选相邻元素的最大累加和
// 给定一个数组nums，长度为n
// nums是一个环形数组，下标0和下标n-1是连在一起的
// 可以随意选择数字，但是不能选择相邻的数字
// 返回能得到的最大累加和
// 测试链接 : https://leetcode.cn/problems/house-robber-ii/
public class Class70_Code04_HouseRobberII {

    //示例 1：
    //
    //输入：nums = [2,3,2]
    //输出：3
    //解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
    //示例 2：
    //
    //输入：nums = [1,2,3,1]
    //输出：4
    //解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
    //     偷窃到的最高金额 = 1 + 3 = 4 。
    //示例 3：
    //
    //输入：nums = [1,2,3]
    //输出：3
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(best(nums, 0, n - 2), best(nums, 1, n - 1));
    }

    // 在l-r区间偷
    private int best(int[] nums, int l, int r) {
        // 只有一个
        if (l == r) {
            return nums[r];
        }
        // 只有两个
        if (r - l == 1) {
            return Math.max(nums[l], nums[r]);
        }
        int prePre = nums[l];
        int pre = Math.max(nums[l], nums[l + 1]);
        for (int i = l + 2, cur; i <= r; i++) {
            cur = Math.max(pre, prePre + nums[i]);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }
}
