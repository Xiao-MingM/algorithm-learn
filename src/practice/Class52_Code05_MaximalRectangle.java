package practice;

// 最大矩形
// 给定一个仅包含 0 和 1 、大小为 rows * cols 的二维二进制矩阵
// 找出只包含 1 的最大矩形，并返回其面积
// 测试链接：https://leetcode.cn/problems/maximal-rectangle/
public class Class52_Code05_MaximalRectangle {

    final int MAXN = 200;
    int[] stack = new int[MAXN];
    int r;
    // 压缩数组
    public int maximalRectangle(char[][] matrix) {
        int n = matrix[0].length;
        int[] heights = new int[n];
        int ans = 0;
        for (char[] c : matrix) {
            // 压缩第i行的数组
            for (int j = 0; j < n; j++) {
                // 当出现0的时候柱状图一整列都视为0
                heights[j] = c[j] == '0' ? 0 : heights[j] + 1;
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }
        return ans;
    }

    // [2,1,5,6,2,3]
    //  0 1 2 3 4 5
    // 0-2以2为高度的最大矩形有多大，找到比0-2小的1-1，结算max = 2*(1-(-1) - 1) = 2 * 1 = 2
    // 1-1以1为为高度的最大矩形有多大，找到-1和6，结算max = 1*(6 -(-1) - 1) = 1 * 6 = 6
    // 2-5以5为为高度的最大矩形有多大，找到1和4，结算max = 5*(4-1-1)=5*2=10
    // 3-6...
    // 相等直接弹出不结算，
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        r = 0;
        int ans = 0;
        // 遍历阶段 大压小
        for (int i = 0; i < n; i++) {
            int height = heights[i];
            while (r > 0 && heights[stack[r - 1]] >= height) {
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r - 1];
                // 等于也结算，下一个会修复的
                ans = Math.max(ans, heights[cur] * (i - left - 1));
            }
            stack[r++] = i;
        }
        // 清算阶段
        while (r > 0) {
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            ans = Math.max(ans, heights[cur] * (n - left - 1));
        }
        return ans;
    }
}
