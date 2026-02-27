package practice;

// 每日温度
// 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer
// 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后
// 如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 测试链接 : https://leetcode.cn/problems/daily-temperatures/
public class Class52_Code02_DailyTemperatures {

    // [20,1,3,13,21,12,11,30,10]
    // 小压大，遇到比栈顶大的就结算
    // [0-20,1-1] 2-3来了，1-1弹出，结算1-1离自己最近的最大日期2-3    [0,1,0,0,0,0,0,0,0]
    // [0-20,2-3] 3-13来了，2-3弹出，结算2-3离自己最近的最大日期3-13  [0,1,1,0,0,0,0,0,0]
    // [0-20,3-13]
    // [0-20,3-13]4-21来了，3-13弹出，结算3-13离自己最近的最大日期4-21[4,1,1,1,0,0,0,0,0]
    // [0-20]
    // [4-21] 5-12来了，入栈
    // [4-21,5-12] 6-11来了，入栈
    // [4-21,5-12, 6-11] 7-30来了，全部出栈并结算
    // [4-21,5-12]                                             [4,1,1,1,0,0,1,0,0]
    // [4-21]                                                  [4,1,1,1,0,2,1,0,0]
    // [7-30]
    // [7-30, 8-10]清算                                         [4,1,1,1,0,2,1,0,0]
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        int[] stack = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            // 违反小压大出栈，允许相等的入栈
            while (r > 0 && temperatures[stack[r - 1]] < temperatures[i]) {
                // 弹出栈顶元素
                int cur = stack[--r];
                // 只找离栈顶元素最近最大的右边元素下标，结算时间
                ans[cur] = i - cur;
            }
            // 当前下标入栈
            stack[r++] = i;
        }
        // 清算阶段不需要，右边没有符合的都是0
        return ans;
    }
}
