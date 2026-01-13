package practice;

// 最长回文子序列
// 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度
// 测试链接 : https://leetcode.cn/problems/longest-palindromic-subsequence/
public class Class67_Code04_LongestPalindromicSubsequence {
    public int longestPalindromeSubseq1(String s) {
        return f1(s.toCharArray(), 0, s.length() - 1);
    }

    /**
     * 递归暴力搜索
     * @param s
     * @param l
     * @param r
     * @return
     */
    int f1(char[] s, int l, int r) {
        // 单个字符是1个
        if (l == r)
            return 1;
        // 两个相等则为2不相等则为1
        if (l + 1 == r)
            return s[l] == s[r] ? 2 : 1;
        int ans;
        if (s[l] == s[r])
            ans = 2 + f1(s, l + 1, r - 1);
        else
            ans = Math.max(f1(s, l + 1, r), f1(s, l, r - 1));
        return ans;
    }

    public int longestPalindromeSubseq2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(chars, 0, n - 1, dp);
    }

    /**
     * 递归暴力搜索
     * @param s
     * @param l
     * @param r
     * @return
     */
    int f2(char[] s, int l, int r, int[][] dp) {
        // 单个字符是1个
        if (l == r)
            return 1;
        // 两个相等则为2不相等则为1
        if (l + 1 == r)
            return s[l] == s[r] ? 2 : 1;
        if (dp[l][r] != -1)
            return dp[l][r];
        int ans;
        if (s[l] == s[r])
            ans = 2 + f2(s, l + 1, r - 1, dp);
        else
            ans = Math.max(f2(s, l + 1, r, dp), f2(s, l, r - 1, dp));
        dp[l][r] = ans;
        return ans;
    }

    // 转动态规划
    public int longestPalindromeSubseq3(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] dp = new int[n][n];
        // 从最后一行网上推
        for (int l = n - 1; l >= 0; l--) {
            // 每次更新到新的一行先更新对角线
            dp[l][l] = 1;
            // 更新l+1=r
            if (l + 1 < n)
                dp[l][l + 1] = chars[l] == chars[l+1] ? 2 : 1;
            // 从第二个开始更
            for (int r = l + 2; r < n; r++) {
                if (chars[l] == chars[r])
                    // 取左下角
                    dp[l][r] = 2 + dp[l + 1][r - 1];
                else
                    // 分别取左边和下边
                    dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
            }
        }
        // 右上角就是更新好的
        return dp[0][n - 1];
    }

    // 转动态规划
    // 空间数组优化
    public int longestPalindromeSubseq4(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 申请长度为n的数组
        int[] dp = new int[n];
        // 从最后一行往上推
        for (int l = n - 1, leftDown = 0, backup; l >= 0; l--) {
            // 每次更新到新的一行先更新对角线
            // dp[l] : 想象中的dp[l][l]
            dp[l] = 1;
            // 更新l+1=r
            if (l + 1 < n) {
                // l+1更新前还是左下角
                // dp[l+1] : 想象中的dp[l][l+1]
                leftDown = dp[l + 1];
                dp[l + 1] = chars[l] == chars[l + 1] ? 2 : 1;
            }
            // 从第二个开始更
            for (int r = l + 2; r < n; r++) {
                // 备份当前作为左下角
                backup = dp[r];
                if (chars[l] == chars[r]) {
                    // 取左下角
                    dp[r] = 2 + leftDown;
                } else {
                    // 分别取左边和下边
                    dp[r] = Math.max(dp[r], dp[r - 1]);
                }
                // 把左下角的记下来
                leftDown = backup;
            }
        }
        // 右上角就是更新好的
        return dp[n - 1];
    }



}
