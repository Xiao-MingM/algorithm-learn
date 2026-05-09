package practice;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

// 接取落水的最小花盆
// 老板需要你帮忙浇花。给出 N 滴水的坐标，y 表示水滴的高度，x 表示它下落到 x 轴的位置
// 每滴水以每秒1个单位长度的速度下落。你需要把花盆放在 x 轴上的某个位置
// 使得从被花盆接着的第 1 滴水开始，到被花盆接着的最后 1 滴水结束，之间的时间差至少为 D
// 我们认为，只要水滴落到 x 轴上，与花盆的边沿对齐，就认为被接住
// 给出 N 滴水的坐标和 D 的大小，请算出最小的花盆的宽度 W
// 测试链接 : https://www.luogu.com.cn/problem/P2698
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Class54_Code03_FallingWaterSmallestFlowerPot {

    private static final int MAXN = 100005;
    // 坐标
    private static final int[][] arr = new int[MAXN][2];

    private static final int[] maxQueue = new int[MAXN];

    private static final int[] minQueue = new int[MAXN];

    private static int maxH,maxT,minH,minT;

    // [2,4] [4,10] [6,3] [12,15]
    // [2,4]                      0
    // [2,4] [4,10]               6
    // [2,4] [4,10] [6,3]         7  4
    //       [4,10] [6,3]         7  2 ok
    //              [6,3]         0
    //              [6,3] [12,15] 12 6

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StreamTokenizer in = new StreamTokenizer(br);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int limit = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
            }
            int ans = compute(n, limit);
            out.println(ans);
        }
        out.flush();
        out.close();
        br.close();
    }

    private static int compute(int n, int limit) {
        Arrays.sort(arr, 0, n, Comparator.comparingInt(a -> a[0]));
        // 初始化队列
        maxH = maxT = minH = minT = 0;
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && !ok(limit)) {
                push(r++);
            }
            if (ok(limit)) {
                ans = Math.min(ans, arr[r - 1][0] - arr[l][0]);
            }
            pop(l);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static boolean ok(int limit) {
        int max = maxH < maxT ? arr[maxQueue[maxH]][1] : 0;
        int min = minH < minT ? arr[minQueue[minH]][1] : 0;
        return max - min >= limit;
    }

    private static void push(int r) {
        while (maxH < maxT && arr[maxQueue[maxT - 1]][1] <= arr[r][1]) {
            maxT--;
        }
        maxQueue[maxT++] = r;

        while (minH < minT && arr[minQueue[minT - 1]][1] >= arr[r][1]) {
            minT--;
        }
        minQueue[minT++] = r;
    }

    private static void pop(int l) {
        if (maxH < maxT && maxQueue[maxH] == l) {
            maxH++;
        }
        if (minH < minT && minQueue[minH] == l) {
            minH++;
        }
    }
}
