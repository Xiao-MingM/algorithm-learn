package practice;

// 子序列累加和必须被7整除的最大累加和
// 给定一个非负数组nums，
// 可以任意选择数字组成子序列，但是子序列的累加和必须被7整除
// 返回最大累加和
// 对数器验证
public class Class71_Code02_MaxSumDividedBy7 {

    public static int maxSum1(int[] nums) {
        return f(nums, 0, 0);
    }

    private static int f(int[] nums, int i, int s) {
        if (i == nums.length) {
            return s % 7 == 0 ? s : 0;
        }
        // 要i位置的数和不要pk最大值
        return Math.max(f(nums, i + 1, s), f(nums, i + 1, s + nums[i]));
    }

    public static int maxSum2(int[] nums) {
        int n = nums.length;
        // dp[i][j] 表示 取 0 - i-1 前i个数且和mod 7余数 == j的最大子序列累加和
        int[][] dp = new int[n + 1][7];
        // 一个数都不选且余数为0的最大累加和为0
        dp[0][0] = 0;
        // 一个数都不选余数还>0的情况不存在，累加和记为-1
        // 初始第一行
        for (int i = 1; i < 7; i++) {
            dp[0][i] = -1;
        }
        // 依次填格子
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 7; j++) {
                // 当前不选
                dp[i][j] = dp[i - 1][j];

                int left = nums[i - 1] % 7;
                int need = (7 + j - left) % 7;

                if (dp[i - 1][need] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][need] + nums[i - 1]);
                }
            }
        }
        return dp[n][0];
    }

    // 为了测试
    // 生成随机数组
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    // 为了测试
    // 对数器
    public static void main(String[] args) {
        int n = 15;
        int v = 30;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[] nums = randomArray(len, v);
            int ans1 = maxSum1(nums);
            int ans2 = maxSum2(nums);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }


}
