package practice;

// 乘积最大子数组
// 给你一个整数数组 nums
// 请你找出数组中乘积最大的非空连续子数组
// 并返回该子数组所对应的乘积
// 测试链接 : https://leetcode.cn/problems/maximum-product-subarray/
public class Class71_Code01_MaximumProductSubarray {
    // 正的越来越大
    // 负的×正的越来越小
    // 负的×负的又会变大
    public int maxProduct1(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] maxDp = new int[n];
        int[] minDp = new int[n];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];
        int ans = maxDp[0];
        for (int i = 1; i < n; i++) {
            //1.自己待着
            //2.×上左边最大的
            //3.×上左边最小的
            maxDp[i] = Math.max(nums[i], Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            minDp[i] = Math.min(nums[i], Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            // 收集答案
            ans = Math.max(maxDp[i], ans);
        }
        return ans;
    }

    // 正的越来越大
    // 负的×正的越来越小
    // 负的×负的又会变大
    public int maxProduct(int[] nums) {
        int n = nums.length;
        double maxPre, minPre, curMax, curMin, ans;
        maxPre = minPre = ans = nums[0];
        for (int i = 1; i < n; i++) {
            curMax = Math.max(nums[i], Math.max(maxPre * nums[i], minPre * nums[i]));
            curMin = Math.min(nums[i], Math.min(maxPre * nums[i], minPre * nums[i]));
            maxPre = curMax;
            minPre = curMin;
            ans = Math.max(ans, curMax);
        }
        return (int) ans;
    }

}
