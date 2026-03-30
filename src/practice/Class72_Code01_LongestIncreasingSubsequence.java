package practice;

// 最长递增子序列和最长不下降子序列
// 给定一个整数数组nums
// 找到其中最长严格递增子序列长度、最长不下降子序列长度
// 测试链接 : https://leetcode.cn/problems/longest-increasing-subsequence/
public class Class72_Code01_LongestIncreasingSubsequence {

    // [10,9,2,5,3,7,101,18] dp[1] i = 0;
    // [10,9,2,5,3,7,101,18] dp[1, 1] i = 1;
    // [10,9,2,5,3,7,101,18] dp[1, 1, 1] i = 2;
    // [10,9,2,5,3,7,101,18] dp[1, 1, 1, 2] i = 3;
    // [10,9,2,5,3,7,101,18] dp[1, 1, 1, 2, 2] i = 4;
    // [10,9,2,5,3,7,101,18] dp[1, 1, 1, 2, 2, 3] i = 5;
    // [10,9,2,5,3,7,101,18] dp[1, 1, 1, 2, 2, 3, 4] i = 6;
    // [10,9,2,5,3,7,101,18] dp[1, 1, 1, 2, 2, 3, 4, 4] i = 7;
    public int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //
    // [10,9,2,5,3,7,101,18]
    //              end[] r = 0
    //  i=0 10 bs=0 end[10] r = 1
    //  i=1 9  bs=0 end[9] r = 1
    //  i=2 2  bs=0 end[2] r = 1
    //  i=3 5  bs=-1 end[2,5] r = 2
    //  i=4 3  bs=1 end[2,3] r = 2
    //  i=5 7  bs=-1 end[2,3,7] r = 3
    //  i=6 101  bs=-1 end[2,3,7,101] r = 4
    //  i=7 18  bs=3 end[2,3,7,18] r = 4
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // 存放以长度为i+1结尾的最小数字
        int[] ends = new int[n];
        int len = 0;
        // 遍历
        for (int num : nums) {
            // 查找>=p 的最左元素
            int target = bs(ends, len, num);
            // 查找不到直接追加到最后面
            if (target == -1) {
                ends[len++] = num;
            } else {
                ends[target] = Math.min(ends[target], num);
            }
        }
        return len;
    }

    // 查找比 >=p 的最左元素
    int bs(int[] ends, int len, int target) {
        int l = 0, r = len - 1, mid, ans = -1;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (ends[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
