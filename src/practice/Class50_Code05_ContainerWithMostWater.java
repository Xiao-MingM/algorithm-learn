package practice;

// 盛最多水的容器
// 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
// 返回容器可以储存的最大水量
// 说明：你不能倾斜容器
// 测试链接 : https://leetcode.cn/problems/container-with-most-water/
public class Class50_Code05_ContainerWithMostWater {

    // [1,8,6,2,5,4,8,3,7]
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int ans = 0;
        while (l <= r) {
            // 柱子不存水，中间的间隔才存
            int cur = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, cur);
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}
