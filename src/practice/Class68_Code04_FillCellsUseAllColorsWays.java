package practice;

// 有效涂色问题
// 给定n、m两个参数
// 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
// 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
// 求一共有多少种有效的涂色方法
// 1 <= n, m <= 5000
// 结果比较大请 % 1000000007 之后返回
// 对数器验证
public class Class68_Code04_FillCellsUseAllColorsWays {

    public static int MAXN = 5001;

    public static int[][] dp = new int[MAXN][MAXN];

    public static int mod = 1000000007;

    /**
     * dp[5][3] dp[5][4] dp[4][5]
     * dp[i][j] 填i个格子用了m种颜色
     * dp[i][j] = dp[i - 1][j] * n + dp[i - 1][j - 1] * (n - (j - 1))
     * @param m
     * @param n
     * @return
     */
    public int ways2(int m, int n) {
        for (int i = 1; i <= m; i++) {
            dp[i][0] = n;
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j < n; j++) {
                dp[i][j] = (int)(((long) dp[i - 1][j] * n + mod) % mod);
                dp[i][j] = (int)((dp[i][j] + (long) dp[i - 1][j - 1] * (n - (j - 1))) % mod);
            }
        }
        return dp[m][n];
    }
}
