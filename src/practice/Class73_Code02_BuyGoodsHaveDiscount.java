package practice;

import java.io.*;
import java.util.Arrays;

// 夏季特惠
// 某公司游戏平台的夏季特惠开始了，你决定入手一些游戏
// 现在你一共有X元的预算，平台上所有的 n 个游戏均有折扣
// 标号为 i 的游戏的原价a_i元，现价只要b_i元
// 也就是说该游戏可以优惠 a_i - b_i，并且你购买该游戏能获得快乐值为w_i
// 由于优惠的存在，你可能做出一些冲动消费导致最终买游戏的总费用超过预算
// 只要满足 : 获得的总优惠金额不低于超过预算的总金额
// 那在心理上就不会觉得吃亏。
// 现在你希望在心理上不觉得吃亏的前提下，获得尽可能多的快乐值。
// 测试链接 : https://leetcode.cn/problems/tJau2o/
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"，可以直接通过
public class Class73_Code02_BuyGoodsHaveDiscount {

    static final int MAXT = 100001;
    static final int MAXN = 501;

    // 需要进行背包筛选的数组
    static final int[] cost = new int[MAXN];
    // 需要统计背包的价值
    static final long[] value = new long[MAXN];
    static final long[] dp = new long[MAXT];
    // t,背包容量，n所有数据的量，len需要进行背包算法的数据长度
    static int t,n,len;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(in);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int)st.nval;
            st.nextToken();
            t = (int)st.nval;

            int origin, now;
            long happy, ans = 0;

            for (int i = 1; i <= n; i++) {
                st.nextToken();
                origin = (int)st.nval;
                st.nextToken();
                now = (int)st.nval;
                st.nextToken();
                happy = (long)st.nval;

                int well = origin - 2 * now;
                // 必买
                if (well >= 0) {
                    // 直接加入答案快乐值
                    ans += happy;
                    // 拉高预算
                    t += well;
                } else {
                    len++;
                    // 交给背包决定要不要买
                    cost[len] = -well;
                    value[len] = happy;
                }
            }

            ans += compute();
            out.println(ans);
        }
        out.flush();
        out.close();
        in.close();
    }




    /*
     * 获得的总优惠金额不低于超过预算的总金额 ->  sum(ai - bi) >= sum(bi) - X  ->  sum(ai - 2bi) >= -X
     * 即
     * 如果ai-2bi >= 0 则这个游戏是必买的，它可以拉高游戏的预算 （ai-2bi >= 0， -X - (ai - 2bi) 会越来越大，直观感受就是游戏的优惠力度都大于它的现价了，买了肯定不亏）
     * 如果ai-2bi < 0 则这个游戏就要背包决策了，在这个决策范围内取最大快乐值
     * 即 sum(2bi - ai) <= X,背包容量 X 的情况下，怎么装可以收货最大价值
     */
    static long compute() {
        // 初始化DP
        Arrays.fill(dp, 0, t + 1, 0);
        for (int i = 1; i <= len; i++) {
            for (int j = t; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + value[i]);
            }
        }
        return dp[t];
    }

//    // 输入：
//    //- 第一行包含两个数 n 和 X 。
//    //- 接下来 n 行包含每个游戏的信息，原价 ai,现价 bi，能获得的快乐值为 wi 。
//    //输出：
//    //- 输出一个数字，表示你能获得的最大快乐值。
//    // 输入：
//    //     4 100
//    //     100 73 60    27
//    //     100 89 35    11
//    //     30 21 30      9
//    //     10 8 10       2
//    // 输出：100
//    // 解释：买 1、3、4 三款游戏，获得总优惠 38 元，总金额 102 元超预算 2 元，满足条件，获得 100 快乐值。
//    // 提示：
//    //
//    // 所有输入均为整型数
//    // 1 <= n <= 500
//    // 0 <= x <= 10,000
//    // 0 <= b_i <= a_i <= 500
//    // 1 <= w_i <= 1,000,000,000
//    // 关于数据集：
//    // 前 30% 的数据， 小数据集 (n<=15)
//    // 中间 30% 的数据，中等数据集 (n<=100)
//    // 后 40% 的数据，大数据集 (n<=500)
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StreamTokenizer st = new StreamTokenizer(in);
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
//        while (st.nextToken() != StreamTokenizer.TT_EOF) {
//            n = (int)st.nval;
//            st.nextToken();
//            t = (int)st.nval;
//            for (int i = 1; i <= n; i++) {
//                st.nextToken();
//                origin[i] = (int)st.nval;
//                st.nextToken();
//                now[i] = (int)st.nval;
//                st.nextToken();
//                happy[i] = (long)st.nval;
//            }
//
//            long ans = compute();
//            out.println(ans);
//        }
//        out.flush();
//        out.close();
//        in.close();
//    }
//
//    static final int MAXT = 10000 + 500*500 + 1;
//    static final int MAXN = 501;
//    // 原价
//    static final int[] origin = new int[MAXN];
//    // 现价
//    static final int[] now = new int[MAXN];
//    // 快乐
//    static final long[] happy = new long[MAXN];
//
//
//    // 需要进行背包筛选的数组
//    static final int[] cost = new int[MAXN];
//    // 需要统计背包的价值
//    static final long[] value = new long[MAXN];
//    static final long[] dp = new long[MAXT];
//    // t,背包容量，n所有数据的量，len需要进行背包算法的数据长度
//    static int t,n,len;
//
//
//    /*
//     * 获得的总优惠金额不低于超过预算的总金额 ->  sum(ai - bi) >= sum(bi) - X  ->  sum(ai - 2bi) >= -X
//     * 即
//     * 如果ai-2bi >= 0 则这个游戏是必买的，它可以拉高游戏的预算 （ai-2bi >= 0， -X - (ai - 2bi) 会越来越大，直观感受就是游戏的优惠力度都大于它的现价了，买了肯定不亏）
//     * 如果ai-2bi < 0 则这个游戏就要背包决策了，在这个决策范围内取最大快乐值
//     * 即 sum(2bi - ai) <= X,背包容量 X 的情况下，怎么装可以收货最大价值
//     */
//    static long compute() {
//        long ans = 0;
//        len = 0;
//        for (int i = 1; i <= n; i++) {
//            int well = origin[i] - 2 * now[i];
//            // 必买
//            if (well >= 0) {
//                // 直接加入答案快乐值
//                ans += happy[i];
//                // 拉高预算
//                t += well;
//            } else {
//                len++;
//                // 交给背包决定要不要买
//                cost[len] = -well;
//                value[len] = happy[i];
//            }
//        }
//        // 初始化DP
//        Arrays.fill(dp, 0, t + 1, 0);
//        for (int i = 1; i <= len; i++) {
//            for (int j = t; j >= cost[i]; j--) {
//                dp[j] = Math.max(dp[j], dp[j - cost[i]] + value[i]);
//            }
//        }
//        return ans + dp[t];
//    }


}
