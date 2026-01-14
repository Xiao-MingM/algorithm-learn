package practice;

import java.io.*;

// 节点数为n高度不大于m的二叉树个数
// 现在有n个节点，计算出有多少个不同结构的二叉树
// 满足节点个数为n且树的高度不超过m的方案
// 因为答案很大，所以答案需要模上1000000007后输出
// 测试链接 : https://www.nowcoder.com/practice/aaefe5896cce4204b276e213e725f3ea
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过
public class Class67_Code05_NodenHeightNotLargerThanm {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;
            out.println(compute1(n, m));
        }
        out.flush();
        out.close();
        br.close();
    }

    final static long mod = 1000000007L;

    public static int MAXN = 51;;


    static int[][] dp2 = new int[MAXN][MAXN];
    public static int compute2(int n, int m) {
        // 第一行初始化为1
        for (int j = 0; j <= m; j++) {
            dp2[0][j] = 1;
        }
        // 从上到下推
        for (int i = 1; i <= n; i++) {
            // 从左到右推
            for (int j = 1; j <= m; j++) {
                // 初始ans
                dp2[i][j] = 0;
                // 遍历所有情形
                for (int k = 0; k < i; k++) {
                    dp2[i][j] = (int) ((dp2[i][j] + (long) dp2[k][j - 1] * dp2[i - k - 1][j - 1] % mod) % mod);
                }
            }
        }
        return dp2[n][m];
    }

    // 空间数组压缩
    // 表示列
    static int[] dp3 = new int[MAXN];
    public static int compute3(int n, int m) {
        dp3[0] = 1;
        // 第一行初始化为1
        for (int i = 1; i <= n; i++) {
            dp3[i] = 0;
        }

        // 从左到右
        for (int j = 1; j <= m; j++) {
            for (int i = n; i > 0; i--) {
                dp3[i] = 0;
                // 遍历所有情形
                for (int k = 0; k < i; k++) {
                    dp3[i] = (int) ((dp3[i] + (long) dp3[k] * dp3[i - k - 1] % mod) % mod);
                }
            }
        }
        return dp3[n];
    }

    static int[][] dp1 = new int[MAXN][MAXN];

    static {
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                dp1[i][j] = -1;
            }
        }
    }


    // 记忆化搜索的递归
    public static int compute1(int n, int m) {
        if (n == 0)
            return 1;
        if (m == 0)
            return 0;
        if (dp1[n][m] != -1)
            return dp1[n][m];
        int ans = 0;
        // 遍历所有情形
        for (int k = 0; k < n; k++) {
            ans = (int) ((ans + (long) compute1(k, m - 1) * compute1(n - k - 1, m - 1) % mod) % mod);
        }
        dp1[n][m] = ans;
        return ans;
    }


}
