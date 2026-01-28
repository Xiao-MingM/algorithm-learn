package practice;

// 删除至少几个字符可以变成另一个字符串的子串
// 给定两个字符串s1和s2
// 返回s1至少删除多少字符可以成为s2的子串
// 对数器验证
public class Class68_Code05_MinimumDeleteBecomeSubstring {

    /**
     * 动态规划
     * dp[i][j] s1串的前i位至少删掉多少位可以变成s2的后缀串
     * s1[i-1]==s2[j-1] dp[i][j] = d[i - 1][j - 1] 否则 dp[i][j] = 1 + dp[i - 1][j]
     * @param str1
     * @param str2
     * @return
     */
    public static int minDelete2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int m = s1.length;
        int n = s2.length;
        int[][] dp = new int[m + 1][n + 1];
        // 初始化第一行，全0，因为空串删除0个串就可以成为s2的空串
        // 初始化第一列，删除i个就可以成为空串s2
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i-1] == s2[j-1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + dp[i - 1][j];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, dp[m][i]);
        }
        return ans;
    }
}
