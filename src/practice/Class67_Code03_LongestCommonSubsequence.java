package practice;

// 最长公共子序列
// 给定两个字符串text1和text2
// 返回这两个字符串的最长 公共子序列 的长度
// 如果不存在公共子序列，返回0
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列
// 测试链接 : https://leetcode.cn/problems/longest-common-subsequence/
public class Class67_Code03_LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        return f1(s1, s2, s1.length - 1, s2.length - 1);

    }

    // 定义一个函数返回对应下标下的最长公共子序列长度
    static int f1(char[] s1, char[] s2, int i1, int i2) {
        // 下标越界则返回0
        if (i1 < 0 || i2 < 0)
            return 0;
        // 最后一位都不要
        int p1 = f1(s1, s2, i1 - 1, i2 - 1);
        // s1 的最后一位不要了去比较
        int p2 = f1(s1, s2, i1 - 1, i2);
        // s2 的最后一位不要了去比较
        int p3 = f1(s1, s2, i1, i2 - 1);
        // 最后一位相同的时候前面的+1否则为0
        int p4 = s1[i1] == s2[i2] ? (p1 + 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        return f2(s1, s2, s1.length, s2.length);

    }

    // 定义一个函数返回对应长度下的最长公共子序列长度
    static int f2(char[] s1, char[] s2, int len1, int len2) {
        // 下标越界则返回0
        if (len1 == 0 || len2 == 0)
            return 0;
        int ans;
        // 最后一位相同的情况
        if (s1[len1 - 1] == s2[len2 - 1])
            ans = f2(s1, s2, len1 - 1, len2 - 1) + 1;
        else
            ans = Math.max(f2(s1, s2, len1 - 1, len2), f2(s1, s2, len1, len2 - 1));
        return ans;
    }

    public static int longestCommonSubsequence3(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++)
                dp[i][j] = -1;
        }
        return f3(s1, s2, s1.length, s2.length, dp);

    }

    // 定义一个函数返回对应长度下的最长公共子序列长度
    static int f3(char[] s1, char[] s2, int len1, int len2, int[][] dp) {
        // 下标越界则返回0
        if (len1 == 0 || len2 == 0)
            return 0;
        if (dp[len1][len2] != -1)
            return dp[len1][len2];

        int ans;
        // 最后一位相同的情况
        if (s1[len1 - 1] == s2[len2 - 1])
            ans = f3(s1, s2, len1 - 1, len2 - 1, dp) + 1;
        else
            ans = Math.max(f3(s1, s2, len1 - 1, len2, dp), f3(s1, s2, len1, len2 - 1, dp));

        dp[len1][len2] = ans;
        return ans;
    }

    // 转动态规划
    public static int longestCommonSubsequence4(String text1, String text2) {
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int m = s1.length;
        int n = s2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1[i - 1] == s2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }

    // 转动态规划，优化空间
    public static int longestCommonSubsequence5(String text1, String text2) {
        // 定义s1为大数组，s2为小数组，则s2的长度拿来滚
        char[] s1, s2;
        if (text1.length() < text2.length()) {
            s2 = text1.toCharArray();
            s1 = text2.toCharArray();
        } else {
            s1 = text1.toCharArray();
            s2 = text2.toCharArray();
        }
        // 小的长度
        int m = s1.length;
        int n = s2.length;
        // 小的长度的dp数组
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            // 每一行的左上角清零
            int leftUp = 0;
            for (int j = 1, backup; j <= n; j++) {
                // 先把当前的原始存下来
                backup = dp[j];
                if (s1[i - 1] == s2[j - 1])
                    // 左上角的
                    dp[j] = leftUp + 1;
                else
                    dp[j] = Math.max(dp[j - 1], dp[j]);

                leftUp = backup;
            }
        }

        return dp[n];
    }



}
