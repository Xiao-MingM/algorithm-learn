package practice;

import java.util.Arrays;

// 子矩阵最大累加和问题
// 给定一个二维数组grid，找到其中子矩阵的最大累加和
// 返回拥有最大累加和的子矩阵左上角和右下角坐标
// 如果有多个子矩阵都有最大累加和，返回哪一个都可以
// 测试链接 : https://leetcode.cn/problems/max-submatrix-lcci/
public class Class70_Code06_MaximumSubmatrix {

    // [3,  1, 2]
    // [-1, 2, 1]
    // [2,  3, 3]

    private int l_x, l_y, r_x, r_y, maxSum;

    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        maxSum = Integer.MIN_VALUE;
        int[] nums = new int[n];
        for (int up = 0; up < m; up++) {
            Arrays.fill(nums, 0);
            for (int down = up; down < m; down++) {
                // 压一层数组去计算
                for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < n; r++) {
                    // 边压边算
                    nums[r] += matrix[down][r];
                    if (pre >= 0) {
                        pre += nums[r];
                    } else {
                        pre = nums[r];
                        l = r;
                    }
                    if (pre > maxSum) {
                        l_x = l;
                        r_x = r;
                        l_y = up;
                        r_y = down;
                        maxSum = pre;
                    }
                }
            }
        }
        // 收集答案
        return new int[]{l_y, l_x, r_y, r_x};
    }

    public int[] getMaxMatrix1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        maxSum = Integer.MIN_VALUE;
        int[] nums = new int[n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(nums, 0);
            for (int j = i; j < m; j++) {
                // 压一层数组去计算
                for (int k = 0; k < n; k++) {
                    nums[k] += matrix[j][k];
                }
                findMaxSum(nums, i, j);
            }
        }
        // 收集答案
        return new int[]{l_y, l_x, r_y, r_x};
    }

    private void findMaxSum(int[] nums, int y1, int y2) {
        int n = nums.length;
        for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < n; r++) {
           if (pre >= 0) {
               pre += nums[r];
           } else {
               pre = nums[r];
               l = r;
           }
           if (pre > maxSum) {
               l_x = l;
               r_x = r;
               l_y = y1;
               r_y = y2;
               maxSum = pre;
           }
        }
    }
}
