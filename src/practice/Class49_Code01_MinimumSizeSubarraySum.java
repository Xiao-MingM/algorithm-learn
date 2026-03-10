package practice;

// 累加和大于等于target的最短子数组长度
// 给定一个含有 n 个正整数的数组和一个正整数 target
// 找到累加和 >= target 的长度最小的子数组并返回其长度
// 如果不存在符合条件的子数组返回0
// 测试链接 : https://leetcode.cn/problems/minimum-size-subarray-sum/
public class Class49_Code01_MinimumSizeSubarraySum {

    // 输入：target = 7, nums = [2,3,1,2,4,3]
    // 输出：2
    // 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    // [2,3,1,2,4,3] l = 0, r = 0 , 2 < 7, r++
    //  l
    //  r
    // [2,3,1,2,4,3] l = 0, r = 1 , 2 + 3 < 7, r++
    //  l
    //    r
    // [2,3,1,2,4,3] l = 0, r = 2 , 2 + 3 + 1 < 7, r++
    //  l
    //      r
    // [2,3,1,2,4,3] l = 0, r = 3 , 2 + 3 + 1 + 2 > 7, l++, ans = min(4,4) = 4
    //  l
    //        r
    // [2,3,1,2,4,3] l = 1, r = 3 , 3 + 1 + 2 < 7, r++
    //    l
    //        r
    // [2,3,1,2,4,3] l = 1, r = 4 , 3 + 1 + 2 + 4 > 7, l++, ans = min(4,4) = 4
    //    l
    //          r
    // [2,3,1,2,4,3] l = 2, r = 4 , 1 + 2 + 4 = 7, l++, ans = min(3, 4) = 3
    //      l
    //          r
    // [2,3,1,2,4,3] l = 3, r = 4 , 2 + 4 < 7, r++,
    //        l
    //          r
    // [2,3,1,2,4,3] l = 3, r = 5 , 2 + 4 + 3 > 7, l++, ans = min(3, 4) = 3
    //        l
    //            r
    // [2,3,1,2,4,3] l = 4, r = 5 , 4 + 3 = 7, l++, ans = min(3, 2) = 2
    //          l
    //            r
    // [2,3,1,2,4,3] l = 5, r = 5 , 3 < 7, r++
    //            l
    //            r
    // 结算答案 2
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int l = 0,r = 0, sum = 0; r < n; r++) {
            // 右边扩一个
            sum += nums[r];
            // 如果左边界可以缩则循环缩
            while (sum - nums[l] >= target) {
                sum -= nums[l++];
            }
            // 如果达标记录答案，只针对刚开始的数据，后续维护一个sum始终>target的窗口
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
