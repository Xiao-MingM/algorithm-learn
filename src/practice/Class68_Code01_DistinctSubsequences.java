package practice;

// 不同的子序列
// 给你两个字符串s和t ，统计并返回在s的子序列中t出现的个数
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences/
public class Class68_Code01_DistinctSubsequences {


    public int numDistinct1(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(s.toCharArray(), t.toCharArray(), m, n, dp);
    }

    /**
     * 传统递归
     * [0~i-1][0~j-1]
     *
     * @param s
     * @param t
     * @param len1
     * @param len2
     * @return
     */
    int f1(char[] s, char[] t, int len1, int len2, int[][] dp) {
        // 总有一个空集会出现在t串中
        if (len2 == 0)
            return 1;
        // 不可能有串出现在t中
        if (len1 == 0)
            return 0;
        if (dp[len1][len2] != -1)
            return dp[len1][len2];
        int ans = f1(s, t, len1 - 1, len2, dp);
        // 讨论递归思路，最后一位相等直接匹配上
        if (s[len1 - 1] == t[len2 - 1])
            ans += f1(s, t, len1 - 1, len2 - 1, dp);
        dp[len1][len2] = ans;
        return ans;
    }

    /**
     * 动态规划
     * 定义dp[i][j] s串长度为i且t长度为j时的子串出现数量
     * 状态转移方程
     * dp[i[[j] = dp[i - 1][j] + s[i] == s[j] ? dp[i - 1][j - 1] : 0;
     * 依赖左边的格子和左上的格子
     * 从上到下从右往左填就可以
     * @param s
     * @param t
     * @return
     */
    public int numDistinct2(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        // 当s串长度为0，t串长度大于0时不可能有串能匹配t，dp[0][j] = 0
        for (int j = 1; j <= n; j++) {
            dp[0][j] = 0;
        }
        // 当t串为0时，总有空串匹配，数量为1
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        // 从上到下
        for (int i = 1; i <= m; i++) {
            // 从右到左
            for (int j = n; j > 0; j--) {
                // 不用最后一个的情况
                dp[i][j] = dp[i - 1][j];
                // 最后一个匹配上的情况
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 动态规划-空间优化
     * 依赖左边的格子和左上的格子
     * 从上到下从右往左填就可以
     * @param s
     * @param t
     * @return
     */
    public int numDistinct3(String s, String t) {
        int m = s.length();
        int n = t.length();
        // 定义一行
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // 从上到下
        for (int i = 1; i <= m; i++) {
            // 从右到左
            for (int j = n; j > 0; j--) {
                // 之前的自己是还没自我更新的格子
                // dp[j] = dp[j];
                // 最后一个匹配上的情况
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 左上的格子
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }
}
