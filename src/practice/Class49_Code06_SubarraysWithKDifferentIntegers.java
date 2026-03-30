package practice;

import java.util.Arrays;

// K个不同整数的子数组
// 给定一个正整数数组 nums和一个整数 k，返回 nums 中 「好子数组」 的数目。
// 如果 nums 的某个子数组中不同整数的个数恰好为 k
// 则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
// 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
// 子数组 是数组的 连续 部分。
// 测试链接 : https://leetcode.cn/problems/subarrays-with-k-different-integers/
public class Class49_Code06_SubarraysWithKDifferentIntegers {

    // 示例 1：
    //
    //输入：nums = [1,2,1,2,3], k = 2
    //输出：7
    //解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
    //示例 2：
    //
    //输入：nums = [1,2,1,3,4], k = 3
    //输出：3
    //解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].

    // 恰好不好维护
    // 使用 F(K)=不超过k个重复元素的数量  F(K-1)=不超过k-1个重复元素的数量 答案 = F(K) - F(K - 1)
    // 设
    //F(K) = 子数组中“最多 K 种不同整数”的个数
    //那么题目要的“恰好 K 种”就是：
    //exactly(K)=F(K)−F(K−1)
    //原因：
    //F(K) 包含了“1种、2种、...、K种”
    //F(K-1) 包含了“1种、2种、...、K-1种”
    //一减，正好只剩“恰好 K 种”。
    // 对每个右端点 r，维护一个窗口 [l..r]，让它始终满足“不同数个数 <= K”。
    //如果加上 nums[r] 后不同数超过 K，就移动左端点 l，直到重新 <= K。
    //当窗口满足 <= K 时，以 r 结尾的合法子数组个数就是：
    //r−l+1
    //（因为起点可以是 l, l+1, ..., r）
    //把每个 r 的 r-l+1 累加就是 F(K)。

    // [1,2,1,2,3] 举例小于等于2的子数组
    // [1] [1,2] [1,2,1] [1,2,1,2] [2,3]
    // 统计 [1] [1,2][2] [1,2,1][2,1][1] [1,2,1,2][2,1,2][1,2][2] [2,3][3]  12种
    // [1,2,1,2,3] 举例小于等于1的子数组
    // [1][2][1][2][3] 5种
    // 答案12-5=7

    final int MAXN = 20001;
    // 计数表
    final int[] cnt = new int[MAXN];
    public int subarraysWithKDistinct(int[] nums, int k) {
        return f(nums, k) - f(nums, k - 1);
    }

    // 滑动窗口统计小于等于k的子数组数量
    int f(int[] nums, int k) {
        // 清理词频表
        Arrays.fill(cnt, 0);

        int n = nums.length;
        int ans = 0;
        int distinct = 0;
        // 右窗口右扩
        for (int l = 0, r = 0; r < n; r++) {
            // 扩右边界
            if (cnt[nums[r]]++ == 0) {
                distinct++;
            }
            // 不同数量超了缩减左边界
            while (distinct > k) {
                if (cnt[nums[l++]]-- == 1) {
                    distinct--;
                }
            }
            ans += (r - l + 1);
        }
        return ans;
    }

}
