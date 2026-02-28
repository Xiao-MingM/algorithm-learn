package practice;

// 柱状图中最大的矩形
// 给定 n 个非负整数，用来表示柱状图中各个柱子的高度
// 每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积
// 测试链接：https://leetcode.cn/problems/largest-rectangle-in-histogram
public class Class52_Code04_LargestRectangleInHistogram {

    // [2,1,5,6,2,3]
    //  0 1 2 3 4 5
    // 0-2以2为高度的最大矩形有多大，找到比0-2小的1-1，结算max = 2*(1-(-1) - 1) = 2 * 1 = 2
    // 1-1以1为为高度的最大矩形有多大，找到-1和6，结算max = 1*(6 -(-1) - 1) = 1 * 6 = 6
    // 2-5以5为为高度的最大矩形有多大，找到1和4，结算max = 5*(4-1-1)=5*2=10
    // 3-6...
    // 相等直接弹出不结算，
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n];
        int r = 0;
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
