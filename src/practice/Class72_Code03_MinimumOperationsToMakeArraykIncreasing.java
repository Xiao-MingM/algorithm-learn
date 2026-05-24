package practice;
// 使数组K递增的最少操作次数
// 给你一个下标从0开始包含n个正整数的数组arr，和一个正整数k
// 如果对于每个满足 k <= i <= n-1 的下标 i
// 都有 arr[i-k] <= arr[i] ，那么称 arr 是K递增的
// 比方说，arr = [4, 1, 5, 2, 6, 2] 对于 k = 2 是 K 递增的，因为：
// arr[0] <= arr[2] (4 <= 5)
// arr[1] <= arr[3] (1 <= 2)
// arr[2] <= arr[4] (5 <= 6)
// arr[3] <= arr[5] (2 <= 2)
// 但是，相同的数组 arr 对于 k = 1 不是 K 递增的（因为 arr[0] > arr[1]），对于 k = 3 也不是 K 递增的（因为 arr[0] > arr[3] ）。
// 每一次操作中，你可以选择一个下标i并将arr[i]改成任意正整数
// 请你返回对于给定的 k ，使数组变成K递增的最少操作次数
// 测试链接 : https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing/
public class Class72_Code03_MinimumOperationsToMakeArraykIncreasing {

    // 求分完组后的最长不下降子序列长度，总长度-各个分组的最长不下降子序列就是答案
    public int kIncreasing(int[] arr, int k) {
        int n = arr.length;
        // 统计每个分组的单调不减子序列长度
        int count = 0;
        int[] ends = new int[n];
        int len;
        // 分组遍历,i遍历组数的一个元素，j从i出发遍历每一组
        for (int i = 0; i < k; i++) {
            // 每一组的len清0
            len = 0;
            for (int j = i; j < n; j += k) {
                int find = bs(ends, len, arr[j]);
                if (find == -1) {
                    ends[len++] = arr[j];
                } else {
                    ends[find] = arr[j];
                }
            }
            count += len;
        }
        return n - count;
    }


    private int bs(int[] ends, int len, int num) {
        int l = 0, r = len - 1, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 这里找严格>num的最左位置
            if (ends[mid] > num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
