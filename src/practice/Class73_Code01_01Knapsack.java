package practice;

import java.io.*;

// 01背包(模版)
// 给定一个正数t，表示背包的容量
// 有m个货物，每个货物可以选择一次
// 每个货物有自己的体积costs[i]和价值values[i]
// 返回在不超过总容量的情况下，怎么挑选货物能达到价值最大
// 返回最大的价值
// 测试链接 : https://www.luogu.com.cn/problem/P1048
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"，可以直接通过
public class Class73_Code01_01Knapsack {

    static final int MAXN = 101;
    static final int MAXT = 1001;

    static final int[] cost = new int[MAXN];
    static final int[] value = new int[MAXN];
    /** dp[i]表示i位置物品所能获取的最大价值 **/
    static final int[] dp = new int[MAXT];

    static int t, n;

    // 输入
    // 70 3
    // 71 100
    // 69 1
    // 1 2
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StreamTokenizer st = new StreamTokenizer(in);
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            t = (int)st.nval;

            st.nextToken();
            n = (int)st.nval;

            for (int i = 1; i <= n; i++) {
                st.nextToken();
                cost[i] = (int)st.nval;
                st.nextToken();
                value[i] = (int)st.nval;
            }

            int ans = compute2();

            out.println(ans);
        }
        out.flush();
        out.close();
        in.close();
    }

    // 状态转移方程
    // dp[i][j]表示i位置的物品，总重量不超过重量j的情况下，获取的最大价值
    // 直接不要当前i位置的物品的最大值和要当前物品的最大值求max
    // dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + value[i])
    static int compute1() {
        int[][] dp_ = new int[n + 1][t + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= t; j++) {
                dp_[i][j] = dp_[i - 1][j];
                if (j - cost[i] >= 0) {
                    dp_[i][j] = Math.max(dp_[i][j], dp_[i - 1][j - cost[i]] + value[i]);
                }
            }
        }
        return dp_[n][t];
    }

    // 状态转移方程 压缩数组
    // dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + value[i]) 依赖左边和左上
    // dp[j] = Math.max(dp[j], dp[j - cost[i]] + value[i])
    // [ ..............]
    // [ ....?.........]
    // [ ......x' x ...]
    // [ ..............]
    static int compute2() {
        for (int i = 1; i <= n; i++) {
            for (int j = t; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + value[i]);
            }
        }
        return dp[t];
    }

}
