package practice;

// 一和零(多维费用背包)
// 给你一个二进制字符串数组 strs 和两个整数 m 和 n
// 请你找出并返回 strs 的最大子集的长度
// 该子集中 最多 有 m 个 0 和 n 个 1
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集
// 测试链接 : https://leetcode.cn/problems/ones-and-zeroes/
public class Class69_Code01_OnesAndZeroes {

    // 暴力递归
    public int findMaxForm1(String[] strs, int m, int n) {
        return f1(strs, 0, m, n);
    }

    /**
     * 统计最大元素数量
     * @param strs
     * @param i 从strs从i出发到结尾
     * @param o 1最大为o
     * @param z 限制0最大z
     * @return
     */
    int f1(String[] strs, int i, int z, int o) {
        // 边界条件，越界有0个满足
        if (i == strs.length) {
            return 0;
        }
        // 当前串不要了
        int p1 = f1(strs, i + 1, z, o);
        // 当前考虑要
        countOneAndZero(strs[i].toCharArray());
        // 符合条件
        int p2 = 0;
        if (zero <= z && one <= o) {
            // 可以要
            p2 = 1 + f1(strs, i + 1, z - zero, o - one);
        }
        return Math.max(p1, p2);
    }

    // 记忆化搜索
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 0; i <= len; i++) {
            for (int z = 0; z <= m; z++) {
                for (int o = 0; o <= n; o++) {
                    dp[i][z][o] = -1;
                }
            }
        }
        return f2(strs, 0, m, n, dp);
    }

    /**
     * 统计最大元素数量
     * @param strs
     * @param i 从strs从i出发到结尾
     * @param o 1最大为o
     * @param z 限制0最大z
     * @return
     */
    int f2(String[] strs, int i, int z, int o, int[][][] dp) {
        // 边界条件，越界有0个满足
        if (i == strs.length) {
            return 0;
        }
        if (dp[i][z][o] != -1) {
            return dp[i][z][o];
        }
        // 当前串不要了
        int p1 = f2(strs, i + 1, z, o, dp);
        // 当前考虑要
        countOneAndZero(strs[i].toCharArray());
        // 符合条件
        int p2 = 0;
        if (zero <= z && one <= o) {
            // 可以要
            p2 = 1 + f2(strs, i + 1, z - zero, o - one, dp);
        }
        int ans = Math.max(p1, p2);
        dp[i][z][o] = ans;
        return ans;
    }

    public int zero,one;

    public void countOneAndZero(char[] s) {
        zero = 0;
        one = 0;
        for (char c : s) {
            if (c == '0') {
                zero++;
            }
            if (c == '1') {
                one++;
            }
        }
    }

    // 动态规划
    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        // 初始化最后一层全0
//        for (int z = 0; z <= m; z++) {
//            for (int o = 0; o <= n; o++) {
//                dp[len][z][o] = 0;
//            }
//        }
        for (int i = len - 1; i >= 0; i--) {
            countOneAndZero(strs[i].toCharArray());
            for (int z = 0; z <= m; z++) {
                for (int o = 0; o <= n; o++) {
                    int p1 = dp[i + 1][z][o];
                    int p2 = 0;
                    if (zero <= z && one <= o) {
                        p2 = 1 + dp[i + 1][z - zero][o - one];
                    }
                    dp[i][z][o] = Math.max(p1, p2);
                }
            }
        }

        return dp[0][m][n];
    }

    // 动态规划-空间压缩
    public int findMaxForm4(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        // 默认最后一层（第一层也能用）就是0
        for (String str : strs) {
            countOneAndZero(str.toCharArray());
            for (int z = m; z >= zero; z--) {
                for (int o = n; o >= one; o--) {
                    // 就是上层的自己
                    // dp[z][o] = dp[z][o];
                    dp[z][o] = Math.max(dp[z][o], 1 + dp[z - zero][o - one]);
                }
            }
        }
        return dp[m][n];
    }



}
