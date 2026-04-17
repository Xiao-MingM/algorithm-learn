package practice;

import java.io.*;

// 有一次修改机会的最长不下降子序列
// 给定一个长度为n的数组arr，和一个整数k
// 只有一次机会可以将其中连续的k个数全修改成任意一个值
// 这次机会你可以用也可以不用，请返回最长不下降子序列长度
// 1 <= k, n <= 10^5
// 1 <= arr[i] <= 10^6
// 测试链接 : https://www.luogu.com.cn/problem/P8776
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"，可以直接通过
//
public class Class72_Code05_LongestNoDecreaseModifyKSubarray {

    private static final int MAXN = 100001;

    // 目标数组
    private static final int[] arr = new int[MAXN];

    // 记录右边的以j作为开始节点的最长不下降子序列长度
    private static final int[] right = new int[MAXN];

    // 左侧边界的ends数组
    private static final int[] ends = new int[MAXN];
    private static int len;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StreamTokenizer in = new StreamTokenizer(br);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval; in.nextToken();
            int k = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            // 先初始化right数组
            initRight(n);
            int compute = compute(n, k);
            out.println(compute);
        }
        out.flush();
        out.close();
        br.close();

    }

    // 主函数调用求解
    private static int compute(int n, int k) {
        // k比n还大，全改就是答案
        if (k >= n) {
            return n;
        }
        int ans = 0;
        len = 0;
        // 枚举数组
        for (int i = 0; i < n - k; i++) {
            // 先查询左区间符合条件的最长子序列长度
            int left = getLeft(len, arr[i + k]);
            // 计算答案
            ans = Math.max(ans, left + k + right[i + k]);

            // 操作当前元素进入ends数组
            int find = bs(len, arr[i]);
            if (find == -1) {
                ends[len++] = arr[i];
            } else {
                ends[find] = arr[i];
            }
        }
        // 考虑最后一种情况，即和最后k个数全改的情况
        ans = Math.max(ans, len + k);
        return ans;
    }

    // 获取<= arr[j]时的左边最长子序列长度
    private static int getLeft(int len, int max) {
        int find = bs(len, max);
        // find 是“第一个 > max 的位置”，所以 <= max 的数量就是 find，不是 find+1
        return find == -1 ? len : find;
    }

    // 初始化right数组
    // [3,1,2,6,4,5]
    //
    private static void initRight(int n) {
        len = 0;
        for (int r = n - 1; r >= 0; r--) {
            int find = bsRight(len, arr[r]);
            if (find == -1) {
                ends[len++] = arr[r];
                right[r] = len;
            } else {
                ends[find] = arr[r];
                // arr[r]放进去了，所以长度就是find + 1
                right[r] = find + 1;
            }
        }
    }

    private static int bs(int len, int num) {
        int l = 0, r = len - 1, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 找大于num的最左位置
            if (ends[mid] > num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static int bsRight(int len, int num) {
        int l = 0, r = len - 1, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 找小于num的最左位置
            if (ends[mid] < num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

}
