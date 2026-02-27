package practice;

import java.io.*;

// 单调栈求每个位置左右两侧，离当前位置最近、且值严格小于的位置
// 给定一个可能含有重复值的数组 arr
// 找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置
// 返回所有位置相应的信息。
// 输入描述：
// 第一行输入一个数字 n，表示数组 arr 的长度。
// 以下一行输入 n 个数字，表示数组的值
// 输出描述：
// 输出n行，每行两个数字 L 和 R，如果不存在，则值为 -1，下标从 0 开始。
// 测试链接 : https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Class52_Code01_LeftRightLess {

    private static final int MAXN = 1000000;

    private static final int[] arr = new int[MAXN];

    private static final int[][] ans = new int[MAXN][2];

    private static final int[] stack = new int[MAXN];

    // 栈顶指针
    private static int r;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StreamTokenizer st = new StreamTokenizer(in);
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) st.nval;
            // 给数组赋值
            for (int i = 0,element; i < n; i++) {
                st.nextToken();
                element = (int) st.nval;
                arr[i] = element;
            }
            compute(n);
            for (int i = 0; i < n; i++) {
                out.println(ans[i][0] + " " + ans[i][1]);
            }
        }
        out.flush();
        out.close();
        in.close();
    }

    // 计算左右最近的最小值
    private static void compute(int n) {
        // 初始栈顶
        r = 0;
        // 遍历阶段
        for (int i = 0; i < n; i++) {
            // 严格大压小的栈，不符合弹出
            while (r > 0 && arr[stack[r - 1]] >= arr[i]) {
                // 出栈并读取栈顶元素
                int cur = stack[--r];
                // 结算左侧最小值,出栈元素压到那个最小值
                ans[cur][0] = r == 0 ? -1 : stack[r - 1];
                // 结算右边最小值，破坏大压小的那个值就是最近离他最小的
                ans[cur][1] = i;
            }
            // 当前元素入栈
            stack[r++] = i;
        }
        // 清算阶段
        while (r > 0) {
            // 出栈并读取栈顶元素
            int cur = stack[--r];
            ans[cur][0] = r == 0 ? -1 : stack[r - 1];
            // 右边没有让他出栈的元素等于没有最近最小值了
            ans[cur][1] = -1;
        }
        // 修复阶段
        for (int i = n - 1; i >= 0; i--) {
            // 如果当前元素的离自己最近的最小的下标元素和自己相等则用右边的最近最小值替换它
            if (ans[i][1] != -1 && arr[i] == arr[ans[i][1]]) {
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }
}
