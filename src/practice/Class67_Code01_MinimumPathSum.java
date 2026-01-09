package practice;

// 最小路径和
// 给定一个包含非负整数的 m x n 网格 grid
// 请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
// 测试链接 : https://leetcode.cn/problems/minimum-path-sum/
public class Class67_Code01_MinimumPathSum {

    public int minPathSum1(int[][] grid) {
        return f1(grid,grid.length - 1, grid[0].length - 1);
    }

    /**
     * 爆搜
     * @param grid
     * @return
     */
    int f1(int[][] grid, int i, int j) {
        // 终点条件
        if (i == 0 && j == 0)
            return grid[0][0];

        int up = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;

        // 上边不为空
        if (i - 1 >= 0)
            up = Math.min(up, (grid[i][j] + f1(grid,i - 1,j)));
        // 左边不为空
        if (j - 1 >= 0)
            left = Math.min(left, (grid[i][j] + f1(grid,i, j - 1)));

        return Math.min(left, up);
    }

    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(grid, m - 1, n - 1, dp);
    }

    /**
     * 加缓存
     * @param grid
     * @return
     */
    int f2(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1)
            return dp[i][j];
        int ans;
        // 终点条件
        if (i == 0 && j == 0)
            ans = grid[0][0];
        else {
            int up = Integer.MAX_VALUE;
            int left = Integer.MAX_VALUE;

            // 上边不为空
            if (i - 1 >= 0)
                up = Math.min(up, (grid[i][j] + f2(grid,i - 1, j, dp)));
            // 左边不为空
            if (j - 1 >= 0)
                left = Math.min(left, (grid[i][j] + f2(grid,i, j - 1, dp)));

            ans = Math.min(left, up);
            dp[i][j] = ans;
        }
        return ans;
    }

    public int minPathSum3(int[][] grid) {
        return f3(grid);
    }

    /**
     * 二维动态规划
     * @param grid
     * @return
     */
    public int f3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // 更新第一列
        for (int i = 1; i < m; i++) {
           dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 更新第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int minPathSum4(int[][] grid) {
        return f4(grid);
    }

    /**
     * 二维动态规划
     * 空间优化
     * @param grid
     * @return
     */
    public int f4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];
        dp[0] = grid[0][0];

        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            dp[0] = dp[0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
            }
        }

        return dp[n - 1];
    }


}
