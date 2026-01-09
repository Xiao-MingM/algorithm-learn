package practice;

import java.util.Arrays;

// 最低票价
// 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
// 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出
// 每一项是一个从 1 到 365 的整数
// 火车票有 三种不同的销售方式
// 一张 为期1天 的通行证售价为 costs[0] 美元
// 一张 为期7天 的通行证售价为 costs[1] 美元
// 一张 为期30天 的通行证售价为 costs[2] 美元
// 通行证允许数天无限制的旅行
// 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证
// 那么我们可以连着旅行 7 天(第2~8天)
// 返回 你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费
// 测试链接 : https://leetcode.cn/problems/minimum-cost-for-tickets/
public class Class66_Code02_MinimumCostForTickets {

    static int[] duration = new int[]{1, 7, 30};

    /**
     * [1,4,6,7,8,20]
     * @param days
     * @param costs
     * @return
     */
    public static int mincostTickets(int[] days, int[] costs) {
        return f1(days, costs, 0);
    }


    public static int f1(int[] days, int[] costs, int i) {
        // 到达最后一天直接结束
        if (i == days.length) {
            return 0;
        }
        // 结果
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < 3; k++) {
            // 当下标为i的时候才去第cost[k]的方案的花费
            while (j < days.length && days[j] < days[i] + duration[k]) {
                // 找到方案对应天数后的一天
                j++;
            }
            ans = Math.min(ans, costs[k] + f1(days, costs, j));
        }
        return ans;
    }

    public static int mincostTickets2(int[] days, int[] costs) {
        int[] dp = new int[days.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return f2(days, costs, 0, dp);
    }


    public static int f2(int[] days, int[] costs, int i, int[] dp) {
        // 到达最后一天直接结束
        if (i == days.length) {
            return 0;
        }
        if (dp[i] != Integer.MAX_VALUE) {
            return dp[i];
        }
        // 结果
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < 3; k++) {
            // 当下标为i的时候才去第cost[k]的方案的花费
            while (j < days.length && days[j] < days[i] + duration[k]) {
                // 找到方案对应天数后的一天
                j++;
            }
            ans = Math.min(ans, costs[k] + f2(days, costs, j, dp));
        }
        dp[i] = ans;
        return ans;
    }


    public static int f3(int[] days, int[] costs) {
        int len = days.length;
        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[len] = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < len && days[i] + duration[k] > days[j])
                    j++;
                dp[i] = Math.min(dp[i], costs[k] + dp[j]);
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int[] days = new int[]{1,4,6,7,8,20};
        int[] costs = new int[]{2,7,15};
        System.out.println(f3(days,costs));
        System.out.println(mincostTickets2(days,costs));
        System.out.println(mincostTickets(days,costs));
    }
}
