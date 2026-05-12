package practice;

// 满足不等式的最大值
// 给你一个数组 points 和一个整数 k
// 数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序
// 也就是说 points[i] = [xi, yi]
// 并且在 1 <= i < j <= points.length 的前提下，xi < xj 总成立
// 请你找出 yi + yj + |xi - xj| 的 最大值，
// 其中 |xi - xj| <= k 且 1 <= i < j <= points.length
// 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
// 测试链接 : https://leetcode.cn/problems/max-value-of-equation/
public class Class55_Code02_MaxValueOfEquation {
    //示例 1：
    //
    //输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
    //输出：4
    //解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
    //没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
    //示例 2：
    //
    //输入：points = [[0,0],[3,0],[9,2]], k = 3
    //输出：3
    //解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。

    // yi + yj + |xi - xj| ==> yi + yj + xj - xi ==> xj + yj + yi - xi
    // 在 xj - xi <= k 的窗口范围内求最大值，在j处的位置xj + yj 是固定的，yi - xi 越大越靠右越容易出结果
    // 单调队列维持一种可能 ya-xa <= yb -xb a<b 就右侧淘汰掉ya-xa因为右边一定比左边好，左侧结算过就左侧弹出

    private static final int MAXN = 100001;
    private static final int[][] queue = new int[MAXN][2];
    private static int h,t;
    public int findMaxValueOfEquation(int[][] points, int k) {
        h = t = 0;
        int n = points.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // 左扩弹出越界的数据
            while (h < t && points[i][0] - points[queue[h][0]][0] > k) {
                h++;
            }
            if (h < t) {
                ans = Math.max(ans, points[i][0] + points[i][1] + queue[h][1]);
            }
            // 当前节点入队列，保持从大到小
            while (h < t && queue[t - 1][1] <= points[i][1] - points[i][0]) {
                t--;
            }
            queue[t][0] = i;
            queue[t++][1] = points[i][1] - points[i][0];
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }

}
