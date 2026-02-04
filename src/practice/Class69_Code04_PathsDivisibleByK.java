package practice;

// 矩阵中和能被 K 整除的路径
// 给一个下标从0开始的 n * m 整数矩阵 grid 和一个整数 k
// 从起点(0,0)出发，每步只能往下或者往右，你想要到达终点(m-1, n-1)
// 请你返回路径和能被 k 整除的路径数目
// 答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
public class Class69_Code04_PathsDivisibleByK {

    int mod = 1000000007;

    public int numberOfPaths1(int[][] grid, int k) {
        return f1(grid, grid.length, grid[0].length, k, 0, 0, 0);
    }

    /**
     * 递归返回
     * @param grid
     * @param k
     * @param i
     * @param j
     * @param r 余数
     * @return
     */
    int f1(int[][] grid,int m, int n, int k, int i, int j, int r) {
        // 走到底了，判断是否可以余0
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] % k == r ? 1 : 0;
        }
        int need;
        int mod = grid[i][j] % k;
        if (mod <= r) {
            need = r - mod;
        } else {
            need = r + k - mod;
        }
        int ans = 0;
        if (i + 1 < m) {
            ans += f1(grid, m, n, k, i + 1, j, need);
        }
        if (j + 1 < n) {
            ans += f1(grid, m, n, k, i, j + 1, need);
        }
        return ans;
    }

    public int numberOfPaths2(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    dp[i][j][r] = -1;
                }
            }
        }
        return f2(grid, m, n, k, 0, 0, 0, dp);
    }

    /**
     * 递归返回
     * @param grid
     * @param k
     * @param i
     * @param j
     * @param r 余数
     * @return
     */
    int f2(int[][] grid,int m, int n, int k, int i, int j, int r, int[][][] dp) {
        // 走到底了，判断是否可以余0
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] % k == r ? 1 : 0;
        }
        if (dp[i][j][r] != -1) {
            return dp[i][j][r];
        }
        int need = (k + r - (grid[i][j] % k)) % k;
        int ans = 0;
        if (i + 1 < m) {
            ans += f2(grid, m, n, k, i + 1, j, need, dp);
        }
        if (j + 1 < n) {
            ans = (ans + f2(grid, m, n, k, i, j + 1, need, dp)) % mod;
        }
        dp[i][j][r] = ans;
        return ans;
    }

    // 动态规划版本
    public int numberOfPaths3(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // m*n的二维网格上坐落着高度为k的小格子，其中只有某一层有值
        int[][][] dp = new int[m][n][k];
        // 最后一格子只有第（grid[m - 1][n - 1] % k）层的格子是1，其余的格子都是0
        dp[m - 1][n - 1][grid[m - 1][n - 1] % k] = 1;
        // 填好最后一列的所有层级格子
        for (int i = m - 2; i >= 0; i--) {
            for (int r = 0; r < k; r++) {
                dp[i][n - 1][r] = dp[i + 1][n - 1][(k + r - (grid[i][n - 1] % k)) % k] % mod;
            }
        }
        // 填好最后一行的所有层级格子
        for (int j = n - 2; j >= 0; j--) {
            for (int r = 0; r < k; r++) {
                dp[m - 1][j][r] = dp[m - 1][j + 1][(k + r - (grid[m - 1][j] % k)) % k] % mod;
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                for (int r = 0, need; r < k; r++) {
                    need = (k + r - (grid[i][j] % k)) % k;
                    dp[i][j][r] = (dp[i + 1][j][need] + dp[i][j + 1][need]) % mod;
                }
            }
        }

        return dp[0][0][0];
    }
}
