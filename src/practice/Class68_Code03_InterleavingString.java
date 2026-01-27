package practice;

// 97. 交错字符串
// 交错字符串
// 给定三个字符串 s1、s2、s3
// 请帮忙验证s3是否由s1和s2交错组成
// 测试链接 : https://leetcode.cn/problems/interleaving-string/
public class Class68_Code03_InterleavingString {

    /**
     * 动态规划 = 路径问题
     * dp[i][j] : s1的前长度为i的串和s2的前长度为j的串能不能搞定s3长度i+j的串
     * dp[i][j] = (s1[i - 1] == s3[i + j - 1] && dp[i - 1][j]) || (s2[j - 1] == s3[i + j - 1] && dp[i][j - 1])
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public boolean isInterleave1(String str1, String str2, String str3) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] s3 = str3.toCharArray();
        int m = s1.length;
        int n = s2.length;
        if (m + n != s3.length) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 长度为0的s1和长度为0的s2搞定长度为0的s3，可以搞定
        dp[0][0] = true;
        // 初始第一行，只用s2搞定s3可以搞定吗
        for (int j = 1; j <= n; j++) {
            // 出现不相等的说明搞不定
            if (s2[j - 1] != s3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        // 初始化第一列，只用s1搞定s3可以搞定吗
        for (int i = 1; i <= m; i++) {
            // 出现不相等的说明搞不定
            if (s1[i - 1] != s3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        // 填格子
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (s1[i - 1] == s3[i + j - 1] && dp[i - 1][j]) || (s2[j - 1] == s3[i + j - 1] && dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    /**
     * 动态规划 = 路径问题
     * 空间压缩
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public boolean isInterleave2(String str1, String str2, String str3) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        char[] s3 = str3.toCharArray();
        int m = s1.length;
        int n = s2.length;
        if (m + n != s3.length) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        // 长度为0的s1和长度为0的s2搞定长度为0的s3，可以搞定
        dp[0] = true;
        // 初始第一行，只用s2搞定s3可以搞定吗
        for (int j = 1; j <= n; j++) {
            // 出现不相等的说明搞不定
            if (s2[j - 1] != s3[j - 1]) {
                break;
            }
            dp[j] = true;
        }
        // 填格子
        for (int i = 1; i <= m; i++) {
            // 初始化每一行的第一个
            dp[0] = s1[i - 1] == s3[i - 1] && dp[0];
            for (int j = 1; j <= n; j++) {
                        // 上面的格子，自己更新前就是上面的格子
                dp[j] = (s1[i - 1] == s3[i + j - 1] && dp[j])
                        ||
                        // 左边的格子，已经更新过了
                        (s2[j - 1] == s3[i + j - 1] && dp[j - 1]);
            }
        }
        return dp[n];
    }


}
