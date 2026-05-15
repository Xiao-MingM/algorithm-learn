package practice;

// 环形数组的子数组最大累加和
// 给定一个数组nums，长度为n
// nums是一个环形数组，下标0和下标n-1是连在一起的
// 返回环形数组中，子数组最大累加和
// 测试链接 : https://leetcode.cn/problems/maximum-sum-circular-subarray/
public class Class70_Code03_MaximumSumCircularSubarray {

    //输入：nums = [1,-2,3,-2]
    //输出：3
    //解释：从子数组 [3] 得到最大和 3
    //
    //输入：nums = [5,-3,5]
    //输出：10
    //解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
    //
    //输入：nums = [3,-2,2,-3]
    //输出：3
    //解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
    // [-1,-2,-3,-4] maxSum = -1, minSum = -10, all = 10 答案肯定不是0
    // [-1,-2,-3, 3] maxSum = 3, minSum = -6, all = -3
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int minSum = nums[0];
        int all = nums[0];
        for (int i = 1, maxPre = nums[0], minPre = nums[0]; i < n; i++) {
            maxPre = Math.max(nums[i], maxPre + nums[i]);
            minPre = Math.min(nums[i], minPre + nums[i]);
            maxSum = Math.max(maxPre, maxSum);
            minSum = Math.min(minPre, minSum);
            all += nums[i];
        }
        return minSum == all ? maxSum : Math.max(maxSum, all - minSum);
    }

}
