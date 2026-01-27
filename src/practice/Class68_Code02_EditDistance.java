package practice;

// 编辑距离
// 给你两个单词 word1 和 word2
// 请返回将 word1 转换成 word2 所使用的最少代价
// 你可以对一个单词进行如下三种操作：
// 插入一个字符，代价a
// 删除一个字符，代价b
// 替换一个字符，代价c
// 测试链接 : https://leetcode.cn/problems/edit-distance/
public class Class68_Code02_EditDistance {

    public int minDistance1(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return f1(s1, s2, m, n, 1, 1,1, dp);
    }

    /**
     * 定义f1函数，s1长度为i转成s2长度为j的代价
     * @param s1
     * @param s2
     * @param i s1的长度
     * @param j s2的长度
     * @param a 新增
     * @param b 删除
     * @param c 替换
     * @return
     */
    int f1(char[] s1, char[] s2, int i, int j, int a, int b, int c, int[][] dp) {
        // s1长度为0的时候转成s2的代价为新增的代价，即
        if (i == 0) {
            return j * a;
        }
        // s2长度为0的时候转成s2的代价为删除的代价，即
        if (j == 0) {
            return i * b;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int p1;
        // 最后一位参与
        // 分相等和不相等
        if (s1[i - 1] == s2[j - 1]) {
            // 相等的时候直接代价为0
            p1 = f1(s1, s2, i - 1, j - 1, a, b, c, dp);
        } else {
            // 最后一位替换的代价
            p1 = f1(s1, s2, i - 1, j - 1, a, b, c, dp) + c;
        }

        // s1的最后一位只搞定s2的前j-1位然后插入一个
        int p2 = f1(s1, s2, i, j - 1, a, b, c, dp) + a;

        // s1的最后一位i - 1不参与，只用s1的前i-1位去搞，相当于删掉s1的最后一位
        int p3 = f1(s1, s2, i - 1, j, a, b, c, dp) + b;

        int ans = Math.min(p1, Math.min(p2, p3));
        dp[i][j] = ans;
        return ans;
    }

    public int minDistance2(String word1, String word2) {
        return f2(word1, word2, 1, 1, 1);
    }

    /**
     * 动态规划
     * @param word1
     * @param word2
     * @param a
     * @param b
     * @param c
     * @return
     */
    int f2(String word1, String word2, int a, int b, int c) {
        int m = word1.length();
        int n = word2.length();
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        // dp[0][0] = 0;
        // 初始第一行，新增的代价
        for (int j = 1; j <= n; j++) {
            dp[0][j] = a * j;
        }
        // 删除的代价
        for (int i = 1; i <= m; i++) {
            dp[i][0] = b * i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int p1;
                // s1最后一位参与搞定s2最后一个
                if (s1[i - 1] == s2[j - 1]) {
                    p1 = dp[i - 1][j - 1];
                } else {
                    p1 = dp[i - 1][j - 1] + c;
                }
                // s1最后一位参与只搞定s2前j-1位
                int p2 = dp[i][j - 1] + a;
                // s1最后一位不参与搞定s2的全部
                int p3 = dp[i - 1][j] + b;
                dp[i][j] = Math.min(p1, Math.min(p2, p3));
            }
        }
        return dp[m][n];
    }

    public int minDistance3(String word1, String word2) {
        return f3(word1, word2, 1, 1, 1);
    }

    /**
     * 动态规划-空间压缩
     * @param word1
     * @param word2
     * @param a
     * @param b
     * @param c
     * @return
     */
    int f3(String word1, String word2, int a, int b, int c) {
        int m = word1.length();
        int n = word2.length();
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int[] dp = new int[n + 1];
        // dp[0][0] = 0;
        // 初始第一行，新增的代价
        for (int j = 1; j <= n; j++) {
            dp[j] = a * j;
        }
        for (int i = 1, leftUp; i <= m; i++) {
            // 每到一行初始
            leftUp = (i - 1) * b;
            dp[0] = i * b;
            for (int j = 1, tmp; j <= n; j++) {
                tmp = dp[j];
                int p1;
                // s1最后一位参与搞定s2最后一个
                if (s1[i - 1] == s2[j - 1]) {
                    p1 = leftUp;
                } else {
                    p1 = leftUp + c;
                }
                // s1最后一位参与只搞定s2前j-1位
                int p2 = dp[j - 1] + a;
                // s1最后一位不参与搞定s2的全部
                int p3 = dp[j] + b;
                dp[j] = Math.min(p1, Math.min(p2, p3));
                leftUp = tmp;
            }
        }
        return dp[n];
    }

}
