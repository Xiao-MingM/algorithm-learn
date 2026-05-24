package practice;

import java.util.Arrays;

// 找出第K小的数对距离
// 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
// 给你一个整数数组 nums 和一个整数 k
// 数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length
// 返回 所有数对距离中 第 k 小的数对距离。
// 测试链接 : https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
public class Class51_Code04_FindKthSmallestPairDistance {
    //示例 1：
    //
    //输入：nums = [1,3,1], k = 1
    //输出：0
    //解释：数对和对应的距离如下：
    //(1,3) -> 2
    //(1,1) -> 0
    //(3,1) -> 2
    //距离第 1 小的数对是 (1,1) ，距离为 0 。
    //示例 2：
    //
    //输入：nums = [1,1,1], k = 2
    //输出：0
    //示例 3：
    //
    //输入：nums = [1,6,1], k = 3
    //输出：5

    // 答案针对的目标是 距离绝对值 范围
    // 原数组排序不影响答案 答案的范围是ans: 0 ~ nums[n - 1] - nums[0]
    // 第k大 -> 定义count(limit) 在ans区间上二分找到limit，要求按照差值不超过limit能拆出来多少对，拆出来对数比K大，说明limit大了可以缩小，否则需要扩大limit
    // 单调性 随着limit的变大，可以拆出来的对数会很多，随着limit变小，对数会变少，属于单调不减
    // 问题在于如何更快的得知给定既定大小的limit如何得知可以拆出来多少份（滑动窗口）维护一个nums[r]-nums[l] <= limit的窗口 在这个窗口里以r为结尾的符合条件的数量就是r-l，计入结果当中
    //
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int l = 0, r = nums[nums.length - 1] - nums[0];
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (count(nums, mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 指定limit，返回可以拆出来多少份
    int count(int[] nums, int limit) {
        int ans = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            // 不符合条件就左边界吐数
            while (nums[r] - nums[l] > limit) {
                l++;
            }
            ans += r - l;
        }
        return ans;
    }

}
