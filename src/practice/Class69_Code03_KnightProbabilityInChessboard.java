package practice;

// 骑士在棋盘上的概率
// n * n的国际象棋棋盘上，一个骑士从单元格(row, col)开始，并尝试进行 k 次移动
// 行和列从0开始，所以左上单元格是 (0,0)，右下单元格是 (n-1, n-1)
// 象棋骑士有8种可能的走法。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格
// 每次骑士要移动时，它都会随机从8种可能的移动中选择一种，然后移动到那里
// 骑士继续移动，直到它走了 k 步或离开了棋盘
// 返回 骑士在棋盘停止移动后仍留在棋盘上的概率
// 测试链接 : https://leetcode.cn/problems/knight-probability-in-chessboard/
public class Class69_Code03_KnightProbabilityInChessboard {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int k_ = 0; k_ <= k; k_++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[k_][i][j] = -1;
                }
            }
        }

        return f2(n, k, row, column, dp);
    }

    /**
     * n * n 的格子从i,j出发留在格子的概率
     * @param n
     * @param k
     * @param i
     * @param j
     * @return
     */
    double f2(int n, int k, int i, int j, double[][][] dp) {
        // 越界说明留下的概率为0
        if (i < 0 || i >= n || j < 0 || j >= n) {
            return 0;
        }
        if (dp[k][i][j] != -1) {
            return dp[k][i][j];
        }
        // 走完了还在格子上说明留下的概率为1
        double ans = 0;
        if (k == 0) {
            ans = 1;
        } else {
            ans += f2(n, k - 1, i + 1, j + 2, dp) / 8;
            ans += f2(n, k - 1, i + 2, j + 1, dp) / 8;
            ans += f2(n, k - 1, i + 2, j - 1, dp) / 8;
            ans += f2(n, k - 1, i + 1, j - 2, dp) / 8;
            ans += f2(n, k - 1, i - 1, j - 2, dp) / 8;
            ans += f2(n, k - 1, i - 2, j - 1, dp) / 8;
            ans += f2(n, k - 1, i - 2, j + 1, dp) / 8;
            ans += f2(n, k - 1, i - 1, j + 2, dp) / 8;
        }
        dp[k][i][j] = ans;
        return ans;
    }

    /**
     * n * n 的格子从i,j出发留在格子的概率
     * @param n
     * @param k
     * @param i
     * @param j
     * @return
     */
    double f1(int n, int k, int i, int j) {
        // 越界说明留下的概率为0
        if (i < 0 || i == n || j < 0 || j == n) {
            return 0;
        }
        // 走完了还在格子上说明留下的概率为1
        if (k == 0) {
            return 1;
        } else {
            double p = f1(n, k - 1, i + 1, j + 2) / 8;
            p += f1(n, k - 1, i + 2, j + 1) / 8;
            p += f1(n, k - 1, i + 2, j - 1) / 8;
            p += f1(n, k - 1, i + 1, j - 2) / 8;
            p += f1(n, k - 1, i - 1, j - 2) / 8;
            p += f1(n, k - 1, i - 2, j - 1) / 8;
            p += f1(n, k - 1, i - 2, j + 1) / 8;
            p += f1(n, k - 1, i - 1, j + 2) / 8;
            return p;
        }
    }
}
