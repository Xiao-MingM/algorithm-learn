package practice;

import java.util.HashMap;
import java.util.Map;

// 吃掉N个橘子的最少天数
// 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子
// 1）吃掉一个橘子
// 2) 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子
// 3) 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子
// 每天你只能从以上 3 种方案中选择一种方案
// 请你返回吃掉所有 n 个橘子的最少天数
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/
public class Class89_Code03_MinimumNumberEatOranges {

    // 缓存优化查询，key=还剩几个橘子，value=吃橘子需要的天数
    Map<Integer,Integer> dp = new HashMap<>();
    // 递归吃橘子
    public int minDays(int n) {
        // 一天直接吃
        if (n <= 1) {
            return n;
        }
        // 缓存命中直接返回结果
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        // n % 2还需额外吃0,1天橘子凑够2的整数倍，+ 1当天吃橘子的天数，+ minDays(n / 2)吃剩下橘子的天数
        // n % 3还需额外吃0,1,2天橘子凑够3的整数倍，+ 1当天吃橘子的天数，+ minDays(n / 3)吃剩下橘子的天数
        // pk出最小值去返回
        int minDays = Math.min(n % 2 + 1 + minDays(n / 2), n % 3 + 1 + minDays(n / 3));
        dp.put(n, minDays);
        return minDays;
    }

}
