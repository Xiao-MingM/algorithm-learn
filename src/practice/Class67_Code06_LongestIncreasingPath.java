package practice;


// 矩阵中的最长递增路径
// 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度
// 对于每个单元格，你可以往上，下，左，右四个方向移动
// 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）
// 测试链接 : https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
public class Class67_Code06_LongestIncreasingPath {


    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int length = f1(matrix, i, j, dp);
                max = Math.max(length, max);
            }
        }
        return max;
    }


    int f1(int[][] matrix, int i, int j, int[][] dp) {
        int length = 0;
        if (dp[i][j] != 0)
            return dp[i][j];
        // 上边不为空向上走
        if (i - 1 >= 0 && matrix[i][j] < matrix[i - 1][j])
            length = Math.max(length, f1(matrix, i - 1, j, dp));
        // 下
        if (i + 1 < matrix.length && matrix[i][j] < matrix[i + 1][j])
            length = Math.max(length, f1(matrix, i + 1, j, dp));
        // 左
        if (j - 1 >= 0 && matrix[i][j] < matrix[i][j - 1])
            length = Math.max(length, f1(matrix, i, j - 1, dp));
        // 右
        if (j + 1 < matrix[0].length && matrix[i][j] < matrix[i][j + 1])
            length = Math.max(length, f1(matrix, i, j + 1, dp));
        int ans = length + 1;
        dp[i][j] = ans;
        return ans;
    }

}
