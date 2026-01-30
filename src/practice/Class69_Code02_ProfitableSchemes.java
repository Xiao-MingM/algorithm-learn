package practice;

// 盈利计划(多维费用背包)
// 集团里有 n 名员工，他们可以完成各种各样的工作创造利润
// 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与
// 如果成员参与了其中一项工作，就不能参与另一项工作
// 工作的任何至少产生 minProfit 利润的子集称为 盈利计划
// 并且工作的成员总数最多为 n
// 有多少种计划可以选择？因为答案很大，答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/profitable-schemes/
public class Class69_Code02_ProfitableSchemes {
    // 暴力递归
    public int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        return f1(profit, group, 0, n, minProfit);
    }

    /**
     * f1表示从 i开始的项目，剩的人g能完成利益p的可能性数量
     * @param profit
     * @param group
     * @param i
     * @param r
     * @param p
     * @return
     */
    int f1(int[] profit, int[] group, int i, int r, int p) {
        // 边界条件
        // 当需要的人不够了，无法再开启项目制造受益，依旧看受益是否已经达标
        if (r <= 0) {
            return p <= 0 ? 1 : 0;
        }
        // 当所有的任务都已经干完了，利益是否已经清0了，还没清0说明还没有达到最小利益，小于等于0说明利益目标已经完结了
        if (i == profit.length) {
            return p <= 0 ? 1 : 0;
        }
        // 当前这个活儿不干直接跳过，人还是这些人，需要的受益还是这些受益
        int p1 = f1(profit, group, i + 1, r, p);
        // 选择干这个活，干活的前提是人手得够
        int p2 = 0;
        if (group[i] <= r) {
            p2 = f1(profit, group, i + 1, r - group[i], p - profit[i]);
        }
        // 选择干的可能加上选择不干的可能性相加
        return p1 + p2;
    }

    public static int mod = 1000000007;

    // 记忆化搜索
    public int profitableSchemes2(int n, int minProfit, int[] group, int[] profit) {
        int[][][] dp = new int[group.length][n + 1][minProfit + 1];
        for (int i = 0; i < group.length; i++) {
            for (int r = 0; r <= n; r++) {
                for (int p = 0; p <= minProfit; p++) {
                    dp[i][r][p] = -1;
                }
            }
        }
        return f2(profit, group, 0, n, minProfit, dp);
    }

    /**
     * f2表示从 i开始的项目，剩的人g能完成利益p的可能性数量
     * @param profit
     * @param group
     * @param i 项目下标
     * @param r 剩余人数
     * @param p 利益
     * @param dp 缓存
     * @return
     */
    int f2(int[] profit, int[] group, int i, int r, int p, int[][][] dp) {
        // 边界条件
        // 当需要的人不够了，无法再开启项目制造受益，依旧看受益是否已经达标
        if (r <= 0) {
            return p <= 0 ? 1 : 0;
        }
        // 当所有的任务都已经干完了，利益是否已经清0了，还没清0说明还没有达到最小利益，小于等于0说明利益目标已经完结了
        if (i == profit.length) {
            return p <= 0 ? 1 : 0;
        }
        if (dp[i][r][p] != -1) {
            return dp[i][r][p];
        }
        // 当前这个活儿不干直接跳过，人还是这些人，需要的受益还是这些受益
        int p1 = f2(profit, group, i + 1, r, p, dp);
        // 选择干这个活，干活的前提是人手得够
        int p2 = 0;
        if (group[i] <= r) {
            // 防止越界
            p2 = f2(profit, group, i + 1, r - group[i], Math.max(0, p - profit[i]), dp);
        }
        // 选择干的可能加上选择不干的可能性相加
        int ans = (p1 + p2) % mod;
        dp[i][r][p] = ans;
        return ans;
    }

    // 动态规划
    public int profitableSchemes3(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length;
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int r = 0; r <= n; r++) {
            // 当r == 0 时且 p == 0 时有一种可能
            dp[r][0] = 1;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int r = n; r >= 0; r--) {
                for (int p = minProfit; p >= 0; p--) {
                    // 不选就是自己
                    // dp[r][p] = dp[r][p];
                    if (group[i] <= r) {
                        // 反向填写依赖的都是比自己小的，也就是上层还没更新的格子
                        dp[r][p] = (dp[r][p] + dp[r - group[i]][Math.max(0, p - profit[i])]) % mod;
                    }
                }
            }
        }
        return dp[n][minProfit];
    }

}
