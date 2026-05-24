package practice;

// 接雨水
// 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water/
public class Class50_Code03_TrappingRainWater {

    // height = [0,1,0,2,1,0,1,3,2,1,2, 1]
    //           0 1 2 3 4 5 6 7 8 9 10 11
    // lmax = 0 rmax = 1 l=0 r = 11 -> 0
    // lmax = 1 rmax = 1 l=1 r = 11 -> 0
    // lmax = 1 rmax = 1 l=2 r = 11 -> 1-0 = 0
    // lmax = 2 rmax = 1 l=3 r = 11 -> 1-1 = 0
    // lmax = 2 rmax = 2 l=3 r = 10 -> 2-2 = 0
    // lmax = 2 rmax = 2 l=4 r = 10 -> 2-1 = 1
    // lmax = 2 rmax = 2 l=5 r = 10 -> 2-0 = 2
    // lmax = 2 rmax = 2 l=6 r = 10 -> 2-1 = 1
    // lmax = 3 rmax = 2 l=7 r = 10 -> 2-2 = 0
    // lmax = 3 rmax = 2 l=7 r = 9  -> 2-1 = 1
    // lmax = 3 rmax = 2 l=7 r = 8  -> 2-2 = 0
    // lmax = 3 rmax = 3 l=7 r = 7  -> 3-3 = 0
    public int trap(int[] height) {
        int n = height.length;
        int l = 1, r = n - 2, lmax = height[0], rmax = height[n - 1];
        int ans = 0;
        while (l <= r) {
            if (lmax <= rmax) {
                ans += Math.max(0, lmax - height[l]);
                lmax = Math.max(lmax, height[l++]);
            } else {
                ans += Math.max(0, rmax - height[r]);
                rmax = Math.max(rmax, height[r--]);
            }
        }
        return ans;
    }

    // height = [0,1,0,2,1,0,1,3,2,1,2,1]
    // lMax =   [0,1,1,2,2,2,2,3,3,3,3,3]
    // rMax =   [3,3,3,3,3,3,3,3,2,2,2,1]
    public int trap1(int[] height) {
        int n = height.length;
        int[] lmax = new int[n];
        int[] rmax = new int[n];
        lmax[0] = height[0];
        rmax[n - 1] = height[n - 1];
        // 初始化以i结尾数组的最大高度
        for (int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], height[i]);
        }
        // 初始化以i为起点数组的最大高度
        for (int i = n - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], height[i]);
        }
        int ans = 0;
        // 靠边的地方是接不到雨水的
        for (int i = 1; i < n - 1; i++) {
            // 找左右最小边界
            int min = Math.min(lmax[i - 1], rmax[i + 1]);
            // 能接到的雨水为最小边界水位-当前格子的高度
            ans += height[i] > min ? 0 : min - height[i];
        }
        return ans;
    }
}
