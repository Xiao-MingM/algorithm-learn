package practice;

import java.util.Arrays;

// 统计全1子矩形的数量
// 给你一个 m * n 的矩阵 mat，其中只有0和1两种值
// 请你返回有多少个 子矩形 的元素全部都是1
// 测试链接 : https://leetcode.cn/problems/count-submatrices-with-all-ones/
public class Class53_Code04_CountSubmatricesWithAllOnes {

    final int MAXN = 150;
    final int[] stack = new int[MAXN];
    int r;
    int[] arr = new int[MAXN];


    public int numSubmat(int[][] mat) {
        Arrays.fill(arr, 0);
        // 压缩数组
        int n = mat[0].length;
        int ans = 0;
        for (int[] data : mat) {
            for (int j = 0; j < n; j++) {
                arr[j] = data[j] == 0 ? 0 : arr[j] + 1;
            }
            ans += countSubmat(n);
        }
        return ans;
    }

    // 直方图问题2.0，依旧找离自己最近最小的
    // [3,1,6,5,2,2,4]
    //  0 1 2 3 4 5 6
    // 0-3离自己最近最小的是 -1,1位置，构成的矩形数量为 1 * (3-1) = 2
    // 1-1离自己最近最小的是 -1,7越界位置，构成矩形的数量为 （7 + 6 + 5 + 4 + 3 + 2 + 1）* 1 = 7 * (1 + 7)/2 * 1 = 28
    // 2-6离自己最近最小的是 1,3，构成矩形的数量为 1*(6-max(1,5)) = 1
    // 3-5离自己最近最小的是 1,4，构成矩形的数量为 (1+(4-1-1))(4-1-1)*/2*(5-max(1,2)) = (1 + 2) * (3) = 9
    // 4-2离自己最近最小的是 1,7，结算区间为[2,6]后面的2再结算
    // 5-2离自己最近最小的是 1,7，结算区间为[2,6],
    int countSubmat(int n) {
        int ans = 0;
        r = 0;
        // 遍历阶段
        for (int i = 0; i < n; i++) {
            // 大压小
            while (r > 0 && arr[stack[r - 1]] >= arr[i]) {
                // 弹出
                int cur = stack[--r];
                int left = r == 0 ? -1 : stack[r - 1];
                // 不相等结算
                if (arr[cur] != arr[i]) {
                    // 等差数列计算公式Sn = n*(a1+an)/2 这里a1 = 1,an = i - left - 1
                    ans += (1 + (i - left - 1)) * (i - left - 1) / 2 * (arr[cur] - Math.max(left == -1 ? 0 : arr[left], arr[i]));
                }
            }
            stack[r++] = i;
        }
        // 结算阶段
        while (r > 0) {
            // 弹出
            int cur = stack[--r];
            int left = r == 0 ? -1 : stack[r - 1];
            ans += (1 + (n - left - 1)) * (n - left - 1) / 2 * (arr[cur] - Math.max(left == -1 ? 0 : arr[left], 0));
        }
        return ans;
    }
}
